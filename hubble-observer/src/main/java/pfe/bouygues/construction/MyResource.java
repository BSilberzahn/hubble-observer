package pfe.bouygues.construction;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pfe.bouygues.construction.ControlFile.Field;
import pfe.bouygues.construction.DataUtil.Constraint;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class MyResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * envoie la liste des jalons
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getJson() {
    	return this.buildHierarchcalJson(Field.service, Field.batch, Field.domain, Field.marker).toString();
    }
    
    
    /**
     * renvoi le calendrier corespondant au projet
     *
     * @return le calendrier au format ical
     */
    @GET
    @Path("{projet}.ics")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public String getIcal(@PathParam("projet") String projetName) {
    	Project p = DataUtil.getProject(projetName);
    	if(p == null)
    		throw new WebApplicationException(404);
    	
    	StringWriter w = new StringWriter();
		Ical cal = new Ical("hubble-reminder", "bouygues");
		for(Entry<Integer, ControlFile> doc : XmlDocument.getDocument().entrySet()){
			ControlFile f = doc.getValue();
			Calendar date = p.getDate(f.getMarker());
			if(date != null){
				Calendar d = new GregorianCalendar();
				d.setTime(date.getTime());
				d.add(Calendar.DAY_OF_MONTH, f.getOfset());
				cal.addEvent(d, d, f.getName(),
						"contrôler la fiche : " + f.getName() + "\\ndu lot " + f.getBatch());
			}
		}
		
		
		try {
			cal.print(w);
			cal.write();
		} catch (IOException e) {
			logger.error("Can't write the icalendar", e);
			throw new WebApplicationException(500);
		}
        return w.toString();
    }
    
    private JSONArray buildHierarchcalJson(Field... hiera){
    	return this.buildHierarchcalJson(new ArrayList<Constraint>(), hiera);
    }
    
    private JSONArray buildHierarchcalJson(List<Constraint> cts, Field... hiera){
    	JSONArray array = new JSONArray();
    	DataUtil du = new DataUtil();
    	Collection<String> valList;
    	if(cts.isEmpty()){
    		valList = du.getAllValuesOf(hiera[0]);
    	} else {
    		Constraint[] ctsArray = new Constraint[1];
    		ctsArray = cts.toArray(ctsArray);
    		valList = du.getAllValuesOfWithConstraint(hiera[cts.size()], ctsArray);
    	}
    	if(!valList.isEmpty()){
	    	if(cts.size() == hiera.length - 1){
	    		for(String val : valList){
	    			JSONObject obj = new JSONObject();
	        		obj.put("name", val);
	        		array.put(obj);
	    		}
	    	} else {
	    		for(String val : valList){
	    			List<Constraint> newCts = new ArrayList<Constraint>(cts);
	    			JSONObject obj = new JSONObject();
	        		newCts.add(new Constraint(hiera[cts.size()], val));
	        		JSONArray subArray = this.buildHierarchcalJson(newCts, hiera);
	        		obj.put("data", subArray);
	        		obj.put("name", val);
	        		array.put(obj);
	    		}
	    	}
    	}
    	return array;
    }
    
}
