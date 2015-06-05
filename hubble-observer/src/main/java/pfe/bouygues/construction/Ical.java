package pfe.bouygues.construction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
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
	
	public boolean write(){
		StringBuilder dt = new StringBuilder();
		dt.append("BEGIN:VCALENDAR\n");
		dt.append("VERSION:2.0\n");
		dt.append("CALSCALE:GREGORIAN\n");
		dt.append("PRODID:-//" + this.organisation + "//NONSGML " + this.product + "//FR\n");
		for(int i = 0; i < this.jlist.size(); i++)
			try {
				dt.append(this.jlist.get(i).getString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(int i = 0; i < this.elist.size(); i++)
			try {
				dt.append(this.elist.get(i).getString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dt.append("END:VCALENDAR\n");
		try{

		Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
 
        File file = new File("fiches/calendar.ics");
        System.out.println("fichier créé : "+file.createNewFile());
        OutputStream fos = new FileOutputStream(file);
        String st = dt.toString();
        WritableByteChannel channel = Channels.newChannel(fos);
        ByteBuffer bb = encoder.encode(CharBuffer.wrap(st));
// CAUTION: THE BUFFER MUST NOT BE FLIPPED ANYMORE.
//        bb.flip();
        channel.write(bb);
        channel.close();
        fos.close();
		}catch(Exception e){
			return false;
		}
		return true;
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
		
		public String getString() throws IOException
		{
			StringBuilder dt = new StringBuilder();
			dt.append("BEGIN:VJOURNAL\n");
			dt.append("UID:" + Math.random() + "@hubble\n");
			dt.append("DTSTAMP:");
			dt.append(new Integer(this.date.get(Calendar.YEAR)).toString());
			String str = new Integer(this.date.get(Calendar.MONTH)+1).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.date.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T120000Z\n");
			dt.append("SUMMARY:" + this.title + "\n");
			dt.append("DESCRIPTION:" + this.description + "\n");
			dt.append("END:VJOURNAL\n");
			return dt.toString();
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
		
		public String getString() throws IOException
		{
			StringBuilder dt = new StringBuilder();
			dt.append("BEGIN:VEVENT\n");
			dt.append("UID:" + Math.random() + "@hubble\n");
			dt.append("DTSTART:");
			dt.append(new Integer(this.dateBegin.get(Calendar.YEAR)).toString());
			String str = new Integer(this.dateBegin.get(Calendar.MONTH)+1).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.dateBegin.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T120000Z\n");
			dt.append("DTEND:");
			dt.append(new Integer(this.dateEnd.get(Calendar.YEAR)).toString());
			str = new Integer(this.dateEnd.get(Calendar.MONTH)+1).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			str = new Integer(this.dateEnd.get(Calendar.DAY_OF_MONTH)).toString();
			if(str.length() == 1)
				str = "0" + str;
			dt.append(str);
			dt.append("T130000Z\n");
			dt.append("SUMMARY:" + this.title + "\n");
			dt.append("DESCRIPTION:" + this.description + "\n");
			dt.append("END:VEVENT\n");
			return dt.toString();
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
