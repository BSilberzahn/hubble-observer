package pfe.bouygues.construction;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
     * récupère la liste des jalons
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
    	StringWriter w = new StringWriter();
		Ical cal = new Ical("hubble-reminder", "bouygues");
		cal.addEvent(new GregorianCalendar(2015,4,23), new GregorianCalendar(2015,4,23), "liste a checker du projet " + projetName, "contenu de la liste");
		try {
			cal.print(w);
		} catch (IOException e) {
			logger.error("Can't write the icalendar", e);
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
