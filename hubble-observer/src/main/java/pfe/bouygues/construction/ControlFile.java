package pfe.bouygues.construction;

import java.io.File;

public class ControlFile {

	private int number;
	private String name;
	private File file;
	private String location;
	private String batch;
	private String marker;
	private String comment;
	private String domain;
	private int ofset = 0;
	private String service;
	private String eventLocation;
	
	public ControlFile(int number, String name, String path){
		this.number = number;
		this.name = name;
		this.file = new File("fiches" + File.pathSeparator + path);
	}
	
	public String getField(Field f){
		switch (f) {
		case location:
			return this.location;
		case batch:
			return this.batch;
		case marker:
			return this.marker;
		case domain:
			return this.domain;
		case service:
			return this.service;
		case eventLocation:
			return this.eventLocation;
		}
		return null;
	}
	
	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getOfset() {
		return ofset;
	}

	public void setOfset(int ofset) {
		this.ofset = ofset;
	}

	public int getNumber() {
		return number;
	}
	
	public enum Field{
		location,
		batch,
		marker,
		domain,
		service,
		eventLocation;
	}
}
