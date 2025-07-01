package com.onsite.pojo_response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponseBody {
		
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
	    private List<String> workers_company_user_ids;
	    private String customer_name;
	    private String customer_contact;
	    private String customer_email;
	    private String customer_company_name;
	    private String customer_company_address;
	    private String customer_gst;
	    private String customer_profile_image;
	    private String address;
	    private String city;
	    private String state;
	    private String status;
	    private List<String> contact_book;
	    private int is_engine;
	    private String duplicate_from;
	    private String created;
	    private String updated;
	    private Object contact_data;
	    private Object photos;
	    private Location location;
	    private Object google_address;
	    private Object allowed_features;
	    private double estimated_cost;
	    private int progress;
	    private String start_date;
	    private String end_date;
	    private String monkey_patch_contractor_name;
	    private String monkey_patch_contractor_company_user_name;
	    private String monkey_patch_contractor_contact;
	    private String monkey_patch_contractor_profile_pic;
	    private int monkey_patch_in_amount;
	    private int monkey_patch_out_amount;
	    private int monkey_patch_transaction_in_amount;
	    private int monkey_patch_transaction_out_amount;
	    private String company_id;
	    private MonkeyPatchMyCompanyUser monkey_patch_my_company_user;
	    private String monkey_patch_company_name;
	    private Object monkey_patch_team_member;
	    private int monkey_patch_todo_count;
	    private int attendance_radius;
	    private int distance;
	    private MonkeyPatchPrimaryAddress monkey_patch_primary_address;

	    public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContractor() {
			return contractor;
		}

		public void setContractor(String contractor) {
			this.contractor = contractor;
		}

		public String getContractor_company_user_id() {
			return contractor_company_user_id;
		}

		public void setContractor_company_user_id(String contractor_company_user_id) {
			this.contractor_company_user_id = contractor_company_user_id;
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

		public String getBg_image() {
			return bg_image;
		}

		public void setBg_image(String bg_image) {
			this.bg_image = bg_image;
		}

		public List<String> getAdmins() {
			return admins;
		}

		public void setAdmins(List<String> admins) {
			this.admins = admins;
		}

		public List<String> getAdmins_company_user_ids() {
			return admins_company_user_ids;
		}

		public void setAdmins_company_user_ids(List<String> admins_company_user_ids) {
			this.admins_company_user_ids = admins_company_user_ids;
		}

		public List<String> getWorkers_company_user_ids() {
			return workers_company_user_ids;
		}

		public void setWorkers_company_user_ids(List<String> workers_company_user_ids) {
			this.workers_company_user_ids = workers_company_user_ids;
		}

		public String getCustomer_name() {
			return customer_name;
		}

		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}

		public String getCustomer_contact() {
			return customer_contact;
		}

		public void setCustomer_contact(String customer_contact) {
			this.customer_contact = customer_contact;
		}

		public String getCustomer_email() {
			return customer_email;
		}

		public void setCustomer_email(String customer_email) {
			this.customer_email = customer_email;
		}

		public String getCustomer_company_name() {
			return customer_company_name;
		}

		public void setCustomer_company_name(String customer_company_name) {
			this.customer_company_name = customer_company_name;
		}

		public String getCustomer_company_address() {
			return customer_company_address;
		}

		public void setCustomer_company_address(String customer_company_address) {
			this.customer_company_address = customer_company_address;
		}

		public String getCustomer_gst() {
			return customer_gst;
		}

		public void setCustomer_gst(String customer_gst) {
			this.customer_gst = customer_gst;
		}

		public String getCustomer_profile_image() {
			return customer_profile_image;
		}

		public void setCustomer_profile_image(String customer_profile_image) {
			this.customer_profile_image = customer_profile_image;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<String> getContact_book() {
			return contact_book;
		}

		public void setContact_book(List<String> contact_book) {
			this.contact_book = contact_book;
		}

		public int getIs_engine() {
			return is_engine;
		}

		public void setIs_engine(int is_engine) {
			this.is_engine = is_engine;
		}

		public String getDuplicate_from() {
			return duplicate_from;
		}

		public void setDuplicate_from(String duplicate_from) {
			this.duplicate_from = duplicate_from;
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

		public Object getContact_data() {
			return contact_data;
		}

		public void setContact_data(Object contact_data) {
			this.contact_data = contact_data;
		}

		public Object getPhotos() {
			return photos;
		}

		public void setPhotos(Object photos) {
			this.photos = photos;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		public Object getGoogle_address() {
			return google_address;
		}

		public void setGoogle_address(Object google_address) {
			this.google_address = google_address;
		}

		public Object getAllowed_features() {
			return allowed_features;
		}

		public void setAllowed_features(Object allowed_features) {
			this.allowed_features = allowed_features;
		}

		public double getEstimated_cost() {
			return estimated_cost;
		}

		public void setEstimated_cost(double estimated_cost) {
			this.estimated_cost = estimated_cost;
		}

		public int getProgress() {
			return progress;
		}

		public void setProgress(int progress) {
			this.progress = progress;
		}

		public String getStart_date() {
			return start_date;
		}

		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}

		public String getEnd_date() {
			return end_date;
		}

		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}

		public String getMonkey_patch_contractor_name() {
			return monkey_patch_contractor_name;
		}

		public void setMonkey_patch_contractor_name(String monkey_patch_contractor_name) {
			this.monkey_patch_contractor_name = monkey_patch_contractor_name;
		}

		public String getMonkey_patch_contractor_company_user_name() {
			return monkey_patch_contractor_company_user_name;
		}

		public void setMonkey_patch_contractor_company_user_name(String monkey_patch_contractor_company_user_name) {
			this.monkey_patch_contractor_company_user_name = monkey_patch_contractor_company_user_name;
		}

		public String getMonkey_patch_contractor_contact() {
			return monkey_patch_contractor_contact;
		}

		public void setMonkey_patch_contractor_contact(String monkey_patch_contractor_contact) {
			this.monkey_patch_contractor_contact = monkey_patch_contractor_contact;
		}

		public String getMonkey_patch_contractor_profile_pic() {
			return monkey_patch_contractor_profile_pic;
		}

		public void setMonkey_patch_contractor_profile_pic(String monkey_patch_contractor_profile_pic) {
			this.monkey_patch_contractor_profile_pic = monkey_patch_contractor_profile_pic;
		}

		public int getMonkey_patch_in_amount() {
			return monkey_patch_in_amount;
		}

		public void setMonkey_patch_in_amount(int monkey_patch_in_amount) {
			this.monkey_patch_in_amount = monkey_patch_in_amount;
		}

		public int getMonkey_patch_out_amount() {
			return monkey_patch_out_amount;
		}

		public void setMonkey_patch_out_amount(int monkey_patch_out_amount) {
			this.monkey_patch_out_amount = monkey_patch_out_amount;
		}

		public int getMonkey_patch_transaction_in_amount() {
			return monkey_patch_transaction_in_amount;
		}

		public void setMonkey_patch_transaction_in_amount(int monkey_patch_transaction_in_amount) {
			this.monkey_patch_transaction_in_amount = monkey_patch_transaction_in_amount;
		}

		public int getMonkey_patch_transaction_out_amount() {
			return monkey_patch_transaction_out_amount;
		}

		public void setMonkey_patch_transaction_out_amount(int monkey_patch_transaction_out_amount) {
			this.monkey_patch_transaction_out_amount = monkey_patch_transaction_out_amount;
		}

		public String getCompany_id() {
			return company_id;
		}

		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}

		public MonkeyPatchMyCompanyUser getMonkey_patch_my_company_user() {
			return monkey_patch_my_company_user;
		}

		public void setMonkey_patch_my_company_user(MonkeyPatchMyCompanyUser monkey_patch_my_company_user) {
			this.monkey_patch_my_company_user = monkey_patch_my_company_user;
		}

		public String getMonkey_patch_company_name() {
			return monkey_patch_company_name;
		}

		public void setMonkey_patch_company_name(String monkey_patch_company_name) {
			this.monkey_patch_company_name = monkey_patch_company_name;
		}

		public Object getMonkey_patch_team_member() {
			return monkey_patch_team_member;
		}

		public void setMonkey_patch_team_member(Object monkey_patch_team_member) {
			this.monkey_patch_team_member = monkey_patch_team_member;
		}

		public int getMonkey_patch_todo_count() {
			return monkey_patch_todo_count;
		}

		public void setMonkey_patch_todo_count(int monkey_patch_todo_count) {
			this.monkey_patch_todo_count = monkey_patch_todo_count;
		}

		public int getAttendance_radius() {
			return attendance_radius;
		}

		public void setAttendance_radius(int attendance_radius) {
			this.attendance_radius = attendance_radius;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public MonkeyPatchPrimaryAddress getMonkey_patch_primary_address() {
			return monkey_patch_primary_address;
		}

		public void setMonkey_patch_primary_address(MonkeyPatchPrimaryAddress monkey_patch_primary_address) {
			this.monkey_patch_primary_address = monkey_patch_primary_address;
		}
	    // All Getters and Setters (main POJO)

	    // Location class
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class Location {
	        private String type;
	        private List<Double> coordinates;

	        public String getType() { return type; }
	        public void setType(String type) { this.type = type; }

	        public List<Double> getCoordinates() { return coordinates; }
	        public void setCoordinates(List<Double> coordinates) { this.coordinates = coordinates; }
	    }

	    // monkey_patch_my_company_user
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class MonkeyPatchMyCompanyUser {
	    	
			private String id;
	        private String company_id;
	        private String company_role_id;
	        private String role;
	        private String type;
	        private String creator;
	        private String name;
	        private String mobile;
	        private String mobile_verified;
	        private String user_id;
	        private String gstin;
	        private String party_id;
	        private String legal_business_name;
	        private String billing_address;
	        private int hidden;
	        private String profile_pic;
	        private Object monkey_patch_policy_ids;
	        private MonkeyPatchCompanyRole monkey_patch_company_role;
	        private MonkeyPatchUser monkey_patch_user;
	        private String email;
	        
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
			public String getCompany_role_id() {
				return company_role_id;
			}
			public void setCompany_role_id(String company_role_id) {
				this.company_role_id = company_role_id;
			}
			public String getRole() {
				return role;
			}
			public void setRole(String role) {
				this.role = role;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public String getCreator() {
				return creator;
			}
			public void setCreator(String creator) {
				this.creator = creator;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getMobile() {
				return mobile;
			}
			public void setMobile(String mobile) {
				this.mobile = mobile;
			}
			public String getMobile_verified() {
				return mobile_verified;
			}
			public void setMobile_verified(String mobile_verified) {
				this.mobile_verified = mobile_verified;
			}
			public String getUser_id() {
				return user_id;
			}
			public void setUser_id(String user_id) {
				this.user_id = user_id;
			}
			public String getGstin() {
				return gstin;
			}
			public void setGstin(String gstin) {
				this.gstin = gstin;
			}
			public String getParty_id() {
				return party_id;
			}
			public void setParty_id(String party_id) {
				this.party_id = party_id;
			}
			public String getLegal_business_name() {
				return legal_business_name;
			}
			public void setLegal_business_name(String legal_business_name) {
				this.legal_business_name = legal_business_name;
			}
			public String getBilling_address() {
				return billing_address;
			}
			public void setBilling_address(String billing_address) {
				this.billing_address = billing_address;
			}
			public int getHidden() {
				return hidden;
			}
			public void setHidden(int hidden) {
				this.hidden = hidden;
			}
			public String getProfile_pic() {
				return profile_pic;
			}
			public void setProfile_pic(String profile_pic) {
				this.profile_pic = profile_pic;
			}
			public Object getMonkey_patch_policy_ids() {
				return monkey_patch_policy_ids;
			}
			public void setMonkey_patch_policy_ids(Object monkey_patch_policy_ids) {
				this.monkey_patch_policy_ids = monkey_patch_policy_ids;
			}
			public MonkeyPatchCompanyRole getMonkey_patch_company_role() {
				return monkey_patch_company_role;
			}
			public void setMonkey_patch_company_role(MonkeyPatchCompanyRole monkey_patch_company_role) {
				this.monkey_patch_company_role = monkey_patch_company_role;
			}
			public MonkeyPatchUser getMonkey_patch_user() {
				return monkey_patch_user;
			}
			public void setMonkey_patch_user(MonkeyPatchUser monkey_patch_user) {
				this.monkey_patch_user = monkey_patch_user;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}

	        // Getters and Setters for MonkeyPatchMyCompanyUser
	    }

	    // monkey_patch_company_role
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class MonkeyPatchCompanyRole {
	    	
			private String id;
	        private String role;
	        private String name;
	        private String description;
	        private String company_id;
	        private String creator_company_user_id;
	        private Object policy_ids;
	        private int hidden;
	        private int delete;
	        private String created;
	        private String updated;
	        private Object monkey_patch_policies;
	        
	        public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getRole() {
				return role;
			}
			public void setRole(String role) {
				this.role = role;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public String getCompany_id() {
				return company_id;
			}
			public void setCompany_id(String company_id) {
				this.company_id = company_id;
			}
			public String getCreator_company_user_id() {
				return creator_company_user_id;
			}
			public void setCreator_company_user_id(String creator_company_user_id) {
				this.creator_company_user_id = creator_company_user_id;
			}
			public Object getPolicy_ids() {
				return policy_ids;
			}
			public void setPolicy_ids(Object policy_ids) {
				this.policy_ids = policy_ids;
			}
			public int getHidden() {
				return hidden;
			}
			public void setHidden(int hidden) {
				this.hidden = hidden;
			}
			public int getDelete() {
				return delete;
			}
			public void setDelete(int delete) {
				this.delete = delete;
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
			public Object getMonkey_patch_policies() {
				return monkey_patch_policies;
			}
			public void setMonkey_patch_policies(Object monkey_patch_policies) {
				this.monkey_patch_policies = monkey_patch_policies;
			}
	        // Getters and Setters for MonkeyPatchCompanyRole
	    }

	    // monkey_patch_user
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class MonkeyPatchUser {
	    	
			private String id;
	        private String name;
	        private String mobile;
	        private String country_code;
	        private String profile_pic;
	        private String mobile_verified;
	        private int invited;
	        private int invitation_count;
	        private String profession;
	        
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
			public String getMobile() {
				return mobile;
			}
			public void setMobile(String mobile) {
				this.mobile = mobile;
			}
			public String getCountry_code() {
				return country_code;
			}
			public void setCountry_code(String country_code) {
				this.country_code = country_code;
			}
			public String getProfile_pic() {
				return profile_pic;
			}
			public void setProfile_pic(String profile_pic) {
				this.profile_pic = profile_pic;
			}
			public String getMobile_verified() {
				return mobile_verified;
			}
			public void setMobile_verified(String mobile_verified) {
				this.mobile_verified = mobile_verified;
			}
			public int getInvited() {
				return invited;
			}
			public void setInvited(int invited) {
				this.invited = invited;
			}
			public int getInvitation_count() {
				return invitation_count;
			}
			public void setInvitation_count(int invitation_count) {
				this.invitation_count = invitation_count;
			}
			public String getProfession() {
				return profession;
			}
			public void setProfession(String profession) {
				this.profession = profession;
			}
	        // Getters and Setters for MonkeyPatchUser
	    }

	    // monkey_patch_primary_address
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class MonkeyPatchPrimaryAddress {
	    	
	    	private String id;
	        private String company_id;
	        private String creator_company_user_id;
	        private String owner_id;
	        private String address_type;
	        private String address_title;
	        private String address_gst;
	        private String address_line_1;
	        private String address_line_2;
	        private String city;
	        private String state;
	        private String postal_code;
	        private String country_code;
	        private InnerLocation location;
	        private Object google_address;
	        private int primary;
	        private int delete;
	        private String created;
	        private String updated;
	        private String search;
	        private MonkeyPatchCountryConfig monkey_patch_country_config;
	        
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
			public String getCreator_company_user_id() {
				return creator_company_user_id;
			}
			public void setCreator_company_user_id(String creator_company_user_id) {
				this.creator_company_user_id = creator_company_user_id;
			}
			public String getOwner_id() {
				return owner_id;
			}
			public void setOwner_id(String owner_id) {
				this.owner_id = owner_id;
			}
			public String getAddress_type() {
				return address_type;
			}
			public void setAddress_type(String address_type) {
				this.address_type = address_type;
			}
			public String getAddress_title() {
				return address_title;
			}
			public void setAddress_title(String address_title) {
				this.address_title = address_title;
			}
			public String getAddress_gst() {
				return address_gst;
			}
			public void setAddress_gst(String address_gst) {
				this.address_gst = address_gst;
			}
			public String getAddress_line_1() {
				return address_line_1;
			}
			public void setAddress_line_1(String address_line_1) {
				this.address_line_1 = address_line_1;
			}
			public String getAddress_line_2() {
				return address_line_2;
			}
			public void setAddress_line_2(String address_line_2) {
				this.address_line_2 = address_line_2;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getState() {
				return state;
			}
			public void setState(String state) {
				this.state = state;
			}
			public String getPostal_code() {
				return postal_code;
			}
			public void setPostal_code(String postal_code) {
				this.postal_code = postal_code;
			}
			public String getCountry_code() {
				return country_code;
			}
			public void setCountry_code(String country_code) {
				this.country_code = country_code;
			}
			public InnerLocation getLocation() {
				return location;
			}
			public void setLocation(InnerLocation location) {
				this.location = location;
			}
			public Object getGoogle_address() {
				return google_address;
			}
			public void setGoogle_address(Object google_address) {
				this.google_address = google_address;
			}
			public int getPrimary() {
				return primary;
			}
			public void setPrimary(int primary) {
				this.primary = primary;
			}
			public int getDelete() {
				return delete;
			}
			public void setDelete(int delete) {
				this.delete = delete;
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
			public String getSearch() {
				return search;
			}
			public void setSearch(String search) {
				this.search = search;
			}
			public MonkeyPatchCountryConfig getMonkey_patch_country_config() {
				return monkey_patch_country_config;
			}
			public void setMonkey_patch_country_config(MonkeyPatchCountryConfig monkey_patch_country_config) {
				this.monkey_patch_country_config = monkey_patch_country_config;
			}
	        // Getters and Setters for MonkeyPatchPrimaryAddress
	    }

	    // InnerLocation class for nested location
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class InnerLocation {
	        private String type;
	        private List<Double> coordinates;

	        public String getType() { return type; }
	        public void setType(String type) { this.type = type; }

	        public List<Double> getCoordinates() { return coordinates; }
	        public void setCoordinates(List<Double> coordinates) { this.coordinates = coordinates; }
	    }

	    // monkey_patch_country_config
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class MonkeyPatchCountryConfig {
	    	
	    	private String country_iso;
	        private String country_name;
	        private String created;
	        private int minimum_digits;
	        private int maximum_digits;
	        private Object login_channels;
	        private String currency;
	        private String flag;
	        private Object tax_slabs;
	        private String tax_display_name;
	        private String tax_value_display_name;
	        private int published;
	        private int decimal_digit;
	        private String country_code;
	        
	        public String getCountry_code() {
				return country_code;
			}
			public void setCountry_code(String country_code) {
				this.country_code = country_code;
			}
			public String getCountry_iso() {
				return country_iso;
			}
			public void setCountry_iso(String country_iso) {
				this.country_iso = country_iso;
			}
			public String getCountry_name() {
				return country_name;
			}
			public void setCountry_name(String country_name) {
				this.country_name = country_name;
			}
			public String getCreated() {
				return created;
			}
			public void setCreated(String created) {
				this.created = created;
			}
			public int getMinimum_digits() {
				return minimum_digits;
			}
			public void setMinimum_digits(int minimum_digits) {
				this.minimum_digits = minimum_digits;
			}
			public int getMaximum_digits() {
				return maximum_digits;
			}
			public void setMaximum_digits(int maximum_digits) {
				this.maximum_digits = maximum_digits;
			}
			public Object getLogin_channels() {
				return login_channels;
			}
			public void setLogin_channels(Object login_channels) {
				this.login_channels = login_channels;
			}
			public String getCurrency() {
				return currency;
			}
			public void setCurrency(String currency) {
				this.currency = currency;
			}
			public String getFlag() {
				return flag;
			}
			public void setFlag(String flag) {
				this.flag = flag;
			}
			public Object getTax_slabs() {
				return tax_slabs;
			}
			public void setTax_slabs(Object tax_slabs) {
				this.tax_slabs = tax_slabs;
			}
			public String getTax_display_name() {
				return tax_display_name;
			}
			public void setTax_display_name(String tax_display_name) {
				this.tax_display_name = tax_display_name;
			}
			public String getTax_value_display_name() {
				return tax_value_display_name;
			}
			public void setTax_value_display_name(String tax_value_display_name) {
				this.tax_value_display_name = tax_value_display_name;
			}
			public int getPublished() {
				return published;
			}
			public void setPublished(int published) {
				this.published = published;
			}
			public int getDecimal_digit() {
				return decimal_digit;
			}
			public void setDecimal_digit(int decimal_digit) {
				this.decimal_digit = decimal_digit;
			}
	    }
}
