package com.onsite.pojo_response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MOM_Response {
	
	private String id;
	private String name;
	private String project_id;
	private String company_id;
	private String creator;
	private String creator_company_user_id;
	private String[] attendee_cu_ids;
	private String notes;
	private String[] photos;
	private int delete;
	private String source_add;
	private String source_edit;
	private String search;
	private CompanyUser[] monkey_patch_attendees;
	private Project monkey_patch_project;
	
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime mom_date;
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime created;
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime updated;
	
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
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
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
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public String getSource_add() {
		return source_add;
	}
	public void setSource_add(String source_add) {
		this.source_add = source_add;
	}
	public String getSource_edit() {
		return source_edit;
	}
	public void setSource_edit(String source_edit) {
		this.source_edit = source_edit;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public CompanyUser[] getMonkey_patch_attendees() {
		return monkey_patch_attendees;
	}
	public void setMonkey_patch_attendees(CompanyUser[] monkey_patch_attendees) {
		this.monkey_patch_attendees = monkey_patch_attendees;
	}
	public Project getMonkey_patch_project() {
		return monkey_patch_project;
	}
	public void setMonkey_patch_project(Project monkey_patch_project) {
		this.monkey_patch_project = monkey_patch_project;
	}
	public LocalDateTime getMom_date() {
		return mom_date;
	}
	public void setMom_date(LocalDateTime mom_date) {
		this.mom_date = mom_date;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	
}
