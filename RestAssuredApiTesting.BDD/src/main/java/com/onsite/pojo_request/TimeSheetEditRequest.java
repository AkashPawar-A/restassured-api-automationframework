package com.onsite.pojo_request;

public class TimeSheetEditRequest {
	
	private String billing_activity_id;
	private double Duration;
	private String start_time;
	private String end_time;
	private String id;
	private String project_id;
	private String notes;
	private String party_company_user_id;
	private String[] photos;
	private String timesheet_date;
	private String updated;
	
	public String getBilling_activity_id() {
		return billing_activity_id;
	}
	public void setBilling_activity_id(String billing_activity_id) {
		this.billing_activity_id = billing_activity_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public String getTimesheet_date() {
		return timesheet_date;
	}
	public void setTimesheet_date(String timesheet_date) {
		this.timesheet_date = timesheet_date;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public double getDuration() {
		return Duration;
	}
	public void setDuration(double duration) {
		Duration = duration;
	}
	
}
