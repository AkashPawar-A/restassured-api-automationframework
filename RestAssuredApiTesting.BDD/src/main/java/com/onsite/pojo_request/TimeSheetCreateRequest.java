package com.onsite.pojo_request;

import java.util.List;

public class TimeSheetCreateRequest {
	
	private String company_id;
	private double duration;
	private String end_time;
	private String notes;
	private String party_company_user_id;
	private String project_id;
	private List<String> photo;
	private String start_time;
	private String timesheet_date;
	
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
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
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public List<String> getPhoto() {
		return photo;
	}
	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getTimesheet_date() {
		return timesheet_date;
	}
	public void setTimesheet_date(String timesheet_date) {
		this.timesheet_date = timesheet_date;
	}

}
