package pfe.bouygues.construction;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ical {
	
	private List<Journal> jlist = new ArrayList<Journal>();
	private List<Event> elist = new ArrayList<Event>();
	private String product;
	private String organisation;
	
	public Ical(String product, String organisation){
		this.product = product;
		this.organisation = organisation;
	}
	
	public void addJournal(Calendar date, String title, String description){
		this.jlist.add(new Journal(date, title, description));
	}
	
	public void addEvent(Calendar dateBegin, Calendar dateEnd, String title, String description){
		this.elist.add(new Event(dateBegin, dateEnd, title, description));
	}
	
	public void print(Writer w) throws IOException{
		w.write("BEGIN:VCALENDAR\n");
		w.write("VERSION:2.0\n");
		w.write("CALSCALE:GREGORIAN\n");
		w.write("PRODID:-//" + this.organisation + "//NONSGML " + this.product + "//FR\n");
		for(int i = 0; i < this.jlist.size(); i++)
			this.jlist.get(i).print(w);
		for(int i = 0; i < this.elist.size(); i++)
			this.elist.get(i).print(w);
		w.write("END:VCALENDAR\n");
	}
	
	public static class Journal{
		private Calendar date;
		private String title;
		private String description;
		
		public Journal(Calendar date, String title, String description) {
			this.date = date;
			this.title = title;
			this.description = description;
		}
		
		public void print(Writer w) throws IOException{
			w.write("BEGIN:VJOURNAL\n");
			w.write("UID:" + Math.random() + "@hubble\n");
			StringBuilder dt = new StringBuilder();
			dt.append("DTSTAMP:");
			dt.append(new Integer(this.date.get(Calendar.YEAR)).toString());
			String str = new Integer(this.date.get(Calendar.MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.date.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T120000Z\n");
			w.write(dt.toString());
			w.write("SUMMARY:" + this.title + "\n");
			w.write("DESCRIPTION:" + this.description + "\n");
			w.write("END:VJOURNAL\n");
		}
	}
	
	public static class Event{
		private Calendar dateBegin;
		private Calendar dateEnd;
		private String title;
		private String description;
		
		public Event(Calendar dateBegin, Calendar dateEnd, String title, String description) {
			this.dateBegin = dateBegin;
			this.dateEnd = dateEnd;
			this.title = title;
			this.description = description;
		}
		
		public void print(Writer w) throws IOException{
			w.write("BEGIN:VEVENT\n");
			w.write("UID:" + Math.random() + "@hubble\n");
			StringBuilder dt = new StringBuilder();
			dt.append("DTSTART:");
			dt.append(new Integer(this.dateBegin.get(Calendar.YEAR)).toString());
			String str = new Integer(this.dateBegin.get(Calendar.MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.dateBegin.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T120000Z\n");
			w.write(dt.toString());
			dt = new StringBuilder();
			dt.append("DTEND:");
			dt.append(new Integer(this.dateEnd.get(Calendar.YEAR)).toString());
			str = new Integer(this.dateEnd.get(Calendar.MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.dateEnd.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T130000Z\n");
			w.write(dt.toString());
			w.write("SUMMARY:" + this.title + "\n");
			w.write("DESCRIPTION:" + this.description + "\n");
			w.write("END:VEVENT\n");
		}
	}
}
