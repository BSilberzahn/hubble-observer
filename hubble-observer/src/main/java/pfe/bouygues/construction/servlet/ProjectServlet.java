package pfe.bouygues.construction.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;

import pfe.bouygues.construction.DataUtil;
import pfe.bouygues.construction.Project;
import pfe.bouygues.construction.SendMailTLS;

/**
 * Servlet implementation class ProjectServlet
 */
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Project p = new Project(request.getParameter("name"));
    	DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE);
    	Map<String, String[]> params = request.getParameterMap();
    	Map<Integer, String> jalons = new HashMap<Integer, String>();
    	Map<Integer, Calendar> dates = new HashMap<Integer, Calendar>();
    	for(Entry<String, String[]> param : params.entrySet()){
    		if(param.getKey().startsWith("email.")){
    			p.addMail(param.getValue()[0]);
    		}
    		if(param.getKey().startsWith("date.")){
    			Calendar cal = new GregorianCalendar();
    			try {
					cal.setTime(format.parse(param.getValue()[0]));
				} catch (ParseException e) {e.printStackTrace();}
    			dates.put(Integer.parseInt(param.getKey().substring(5)), cal);
    		}
			if(param.getKey().startsWith("jalon.")){
				jalons.put(Integer.parseInt(param.getKey().substring(6)),
						param.getValue()[0]);
			}
    	}
    	for(Entry<Integer, String> i : jalons.entrySet()){
    		if(dates.containsKey(i.getKey()))
    			p.addDate(i.getValue(), dates.get(i.getKey()));
    	}
    	DataUtil.addProject(p);
    	

    	
    	System.out.println("name : " + p.getName());
    	System.out.println("mails :");
    	for(String m : p.getMails())
    		System.out.println(m);
    	System.out.println("\ndates : ");
    	for(Entry<String, Calendar> d : p.getDates())
    		System.out.println(d.getKey() + " : " + format.format(d.getValue().getTime()));
    	
    	
	}
}
