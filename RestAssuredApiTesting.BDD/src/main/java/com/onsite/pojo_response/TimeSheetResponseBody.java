package com.onsite.pojo_response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onsite.pojo_request.TimeSheetCreateRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSheetResponseBody extends ProjectResponseBody {

	private String id;
	private String company_id;
	private String creator;
	private String created;
	private String updated;
	private int delete;
	private String creator_company_user_id;
	private String project_id;
	private String timesheet_date;
	private String start_time;
	private String end_time;
	private int duration;
	private String billing_activity_id;
	private String notes;
	private List<Object> photos;

	private MonkeyPatchProject monkey_patch_project;
	private String party_company_user_id;
	private MonkeyPatchPartyCompanyUser monkey_patch_party_company_user;
	private String search;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class MonkeyPatchProject {
		private String id;
		private String type;
		private String name;
		private String contractor;
		private String contractor_company_user_id;
		private String creator;
		private String creator_company_user_id;
		private String bg_image;
		private List<String> admins;
		private List<String> admins_company_user_ids;
		public List<Object> workers_company_user_ids;
		private String customer_name;
		private int customer_contact;
		private String customer_email;
		private String customer_company_name;
		private String customer_company_address;
		private String customer_gst;
		private String customer_profile_image;
		private String address;
		private String city;
		private String state;
		private String status;
		private List<Object> contact_book;
		private int is_engine;
		private String duplicate_from;
		private String created;
		private String updated;
		private Object contact_data;
		private Object photos;
		private Location location;
		private Object google_address;
		private Object allowed_features;
		private int estimated_cost;
		private int progress;
		private String start_date;
		private String end_date;
		private String company_id;
		private int attendance_radius;
		private int distance;

		public static class Location {
			private String type;
			private List<Double> coordinates;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class MonkeyPatchPartyCompanyUser {
		private String id;
		private String company_id;
		private String type;
		private String creator;
		private String name;
		private long mobile;
		private int mobile_verified;
		private String user_id;
		private List<Object> assigned_project_ids;
		private List<Object> pinned_project_ids;
		private List<CustomField> custom_fields;
		private List<String> bank_account_ids;
		private List<String> company_user_bank_account_ids;
		private List<Object> upi_ids;
		private List<Object> address_ids;
		private String created;
		private String updated;
		private int rating;

		public static class CustomField {
			private String id;
			private String name;
			private String data_type;
			private String custom_field_type;
			private String default_value;
			private int default_value_enabled;
			private String company_id;
			private int is_published;
			private int delete;
			private String created;
			private String updated;
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
