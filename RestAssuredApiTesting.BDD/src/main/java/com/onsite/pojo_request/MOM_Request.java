package com.onsite.pojo_request;

public class MOM_Request {
	
	private String id;
	private String name;
	private String company_id;
	private String project_id;
	private String[] attendee_cu_ids;
	private String notes;
	private String[] photos;
	private String mom_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String[] getAttendee_cu_ids() {
		return attendee_cu_ids;
	}
	public void setAttendee_cu_ids(String[] attendee_cu_ids) {
		this.attendee_cu_ids = attendee_cu_ids;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public String getMom_date() {
		return mom_date;
	}
	public void setMom_date(String mom_date) {
		this.mom_date = mom_date;
	}

}
