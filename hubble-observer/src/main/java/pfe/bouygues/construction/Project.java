package pfe.bouygues.construction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Project {
	
	private String name;
	private List<String> mail = new ArrayList<String>();
	private Map<String, Calendar> dates = new HashMap<String, Calendar>();
	
	public Project(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public void addMail(String mail){
		if(!this.mail.contains(mail))
			this.mail.add(mail);
	}
	
	public List<String> getMails(){
		return new ArrayList<String>(this.mail);
	}
	
	public void addDate(String jalon, Calendar date){
		if(!this.dates.containsKey(jalon))
			this.dates.put(jalon, date);
	}
	
	public Calendar getDate(String jalon){
		return this.dates.get(jalon);
	}
	
	public Set<Entry<String, Calendar>> getDates(){
		return this.dates.entrySet();
	}
}
