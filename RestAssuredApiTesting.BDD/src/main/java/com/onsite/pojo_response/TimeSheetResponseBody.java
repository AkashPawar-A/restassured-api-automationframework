package com.onsite.pojo_response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onsite.pojo_request.TimeSheetCreateRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSheetResponseBody extends ProjectResponseBody {

	public String id;
	public String company_id;
	public String creator;
	public String created;
	public String updated;
	public int delete;
	public String creator_company_user_id;
	public String project_id;
	public String timesheet_date;
	public String start_time;
	public String end_time;
	public int duration;
	public String billing_activity_id;
	public String notes;
	public List<Object> photos;

	public MonkeyPatchProject monkey_patch_project;
	public String party_company_user_id;
	public MonkeyPatchPartyCompanyUser monkey_patch_party_company_user;
	public String search;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class MonkeyPatchProject {
		public String id;
		public String type;
		public String name;
		public String contractor;
		public String contractor_company_user_id;
		public String creator;
		public String creator_company_user_id;
		public String bg_image;
		public List<String> admins;
		public List<String> admins_company_user_ids;
		public List<Object> workers_company_user_ids;
		public String customer_name;
		public int customer_contact;
		public String customer_email;
		public String customer_company_name;
		public String customer_company_address;
		public String customer_gst;
		public String customer_profile_image;
		public String address;
		public String city;
		public String state;
		public String status;
		public List<Object> contact_book;
		public int is_engine;
		public String duplicate_from;
		public String created;
		public String updated;
		public Object contact_data;
		public Object photos;
		public Location location;
		public Object google_address;
		public Object allowed_features;
		public int estimated_cost;
		public int progress;
		public String start_date;
		public String end_date;
		public String company_id;
		public int attendance_radius;
		public int distance;

		public static class Location {
			public String type;
			public List<Double> coordinates;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class MonkeyPatchPartyCompanyUser {
		public String id;
		public String company_id;
		public String type;
		public String creator;
		public String name;
		public long mobile;
		public int mobile_verified;
		public String user_id;
		public List<Object> assigned_project_ids;
		public List<Object> pinned_project_ids;
		public List<CustomField> custom_fields;
		public List<String> bank_account_ids;
		public List<String> company_user_bank_account_ids;
		public List<Object> upi_ids;
		public List<Object> address_ids;
		public String created;
		public String updated;
		public int rating;

		public static class CustomField {
			public String id;
			public String name;
			public String data_type;
			public String custom_field_type;
			public String default_value;
			public int default_value_enabled;
			public String company_id;
			public int is_published;
			public int delete;
			public String created;
			public String updated;
		}
	}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public String getCreated() {
			return created;
		}

		public void setCreated(String created) {
			this.created = created;
		}

		public String getUpdated() {
			return updated;
		}

		public void setUpdated(String updated) {
			this.updated = updated;
		}

		public int getDelete() {
			return delete;
		}

		public void setDelete(int delete) {
			this.delete = delete;
		}

		public String getCreator_company_user_id() {
			return creator_company_user_id;
		}

		public void setCreator_company_user_id(String creator_company_user_id) {
			this.creator_company_user_id = creator_company_user_id;
		}

		public String getProject_id() {
			return project_id;
		}

		public void setProject_id(String project_id) {
			this.project_id = project_id;
		}

		public String getTimesheet_date() {
			return timesheet_date;
		}

		public void setTimesheet_date(String timesheet_date) {
			this.timesheet_date = timesheet_date;
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

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getBilling_activity_id() {
			return billing_activity_id;
		}

		public void setBilling_activity_id(String billing_activity_id) {
			this.billing_activity_id = billing_activity_id;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public List<Object> getPhotos() {
			return photos;
		}

		public void setPhotos(List<Object> photos) {
			this.photos = photos;
		}

		public MonkeyPatchProject getMonkey_patch_project() {
			return monkey_patch_project;
		}

		public void setMonkey_patch_project(MonkeyPatchProject monkey_patch_project) {
			this.monkey_patch_project = monkey_patch_project;
		}

		public String getParty_company_user_id() {
			return party_company_user_id;
		}

		public void setParty_company_user_id(String party_company_user_id) {
			this.party_company_user_id = party_company_user_id;
		}

		public MonkeyPatchPartyCompanyUser getMonkey_patch_party_company_user() {
			return monkey_patch_party_company_user;
		}

		public void setMonkey_patch_party_company_user(MonkeyPatchPartyCompanyUser monkey_patch_party_company_user) {
			this.monkey_patch_party_company_user = monkey_patch_party_company_user;
		}

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}
	
}
