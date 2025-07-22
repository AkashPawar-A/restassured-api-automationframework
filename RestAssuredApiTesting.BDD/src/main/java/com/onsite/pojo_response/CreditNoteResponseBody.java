package com.onsite.pojo_response;

public class CreditNoteResponseBody {
	
	private String id;
	private String project_id;
	private String company_id;
	private String creator;
	private String creator_company_user_id;
	private String party_company_user_id;
	private String notes;
	private int total_amount;
	private int work_amount;
	private int gst_amount;
	private Object photos;
	private int delete;
	private String invoice_id;
	private String invoice_date;
	private String reference_number;
	private String approval_flag;
	private String approved_by;
	private String approval_comment;
	private String created;
	private String updated;
	private CompanyUser monkey_patch_creator_company_user;
	private CompanyUser monkey_patch_party_company_user;
	private Project monkey_patch_project;
	private Invoice monkey_patch_invoice;
	private Object monkey_patch_credit_note_items;
	private CompanyUser monkey_patch_approved_by;
	private MetaData meta_data;

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
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getWork_amount() {
		return work_amount;
	}
	public void setWork_amount(int work_amount) {
		this.work_amount = work_amount;
	}
	public int getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(int gst_amount) {
		this.gst_amount = gst_amount;
	}
	public Object getPhotos() {
		return photos;
	}
	public void setPhotos(Object photos) {
		this.photos = photos;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getReference_number() {
		return reference_number;
	}
	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}
	public String getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(String approval_flag) {
		this.approval_flag = approval_flag;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getApproval_comment() {
		return approval_comment;
	}
	public void setApproval_comment(String approval_comment) {
		this.approval_comment = approval_comment;
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
	public CompanyUser getMonkey_patch_creator_company_user() {
		return monkey_patch_creator_company_user;
	}
	public void setMonkey_patch_creator_company_user(CompanyUser monkey_patch_creator_company_user) {
		this.monkey_patch_creator_company_user = monkey_patch_creator_company_user;
	}
	public CompanyUser getMonkey_patch_party_company_user() {
		return monkey_patch_party_company_user;
	}
	public void setMonkey_patch_party_company_user(CompanyUser monkey_patch_party_company_user) {
		this.monkey_patch_party_company_user = monkey_patch_party_company_user;
	}
	public Project getMonkey_patch_project() {
		return monkey_patch_project;
	}
	public void setMonkey_patch_project(Project monkey_patch_project) {
		this.monkey_patch_project = monkey_patch_project;
	}
	public Invoice getMonkey_patch_invoice() {
		return monkey_patch_invoice;
	}
	public void setMonkey_patch_invoice(Invoice monkey_patch_invoice) {
		this.monkey_patch_invoice = monkey_patch_invoice;
	}
	public Object getMonkey_patch_credit_note_items() {
		return monkey_patch_credit_note_items;
	}
	public void setMonkey_patch_credit_note_items(Object monkey_patch_credit_note_items) {
		this.monkey_patch_credit_note_items = monkey_patch_credit_note_items;
	}
	public CompanyUser getMonkey_patch_approved_by() {
		return monkey_patch_approved_by;
	}
	public void setMonkey_patch_approved_by(CompanyUser monkey_patch_approved_by) {
		this.monkey_patch_approved_by = monkey_patch_approved_by;
	}
	public MetaData getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(MetaData meta_data) {
		this.meta_data = meta_data;
	}

}

class CompanyUser {
	
	private String id;
	private String company_id;
	private String company_role_id;
	private String role;
	private String type;
	private String creator;
	private String name;
	private long mobile;
	private int mobile_verified;
	private String user_id;
	private String gstin;
	private String party_id;
	private String legal_business_name;
	private String billing_address;
	private int hidden;
	private String profile_pic;
	private Object monkey_patch_policy_ids;
	private CompanyRole monkey_patch_company_role;
	private User monkey_patch_user;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public int getMobile_verified() {
		return mobile_verified;
	}
	public void setMobile_verified(int mobile_verified) {
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
	public CompanyRole getMonkey_patch_company_role() {
		return monkey_patch_company_role;
	}
	public void setMonkey_patch_company_role(CompanyRole monkey_patch_company_role) {
		this.monkey_patch_company_role = monkey_patch_company_role;
	}
	public User getMonkey_patch_user() {
		return monkey_patch_user;
	}
	public void setMonkey_patch_user(User monkey_patch_user) {
		this.monkey_patch_user = monkey_patch_user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}

class CompanyRole {
	
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
}

class User {
	
	private String id;
	private String name;
	private long mobile;
	private String country_code;
	private String profile_pic;
	private int mobile_verified;
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
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
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
	public int getMobile_verified() {
		return mobile_verified;
	}
	public void setMobile_verified(int mobile_verified) {
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
}

class Project {
	
	private String id;
	private String type;
	private String name;
	private String contractor;
	private String contractor_company_user_id;
	private String creator;
	private String creator_company_user_id;
	private String bg_image;
	private Object admins;
	private Object admins_company_user_ids;
	private Object workers_company_user_ids;
	private String customer_name;
	private long customer_contact;
	private String customer_email;
	private String customer_company_name;
	private String customer_company_address;
	private String customer_gst;
	private String customer_profile_image;
	private String address;
	private String city;
	private String state;
	private String status;
	private Object contact_book;
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
	private String monkey_patch_contractor_name;
	private String monkey_patch_contractor_company_user_name;
	private long monkey_patch_contractor_contact;
	private String monkey_patch_contractor_profile_pic;
	private int monkey_patch_in_amount;
	private int monkey_patch_out_amount;
	private int monkey_patch_transaction_in_amount;
	private int monkey_patch_transaction_out_amount;
	private String company_id;
	private CompanyUser monkey_patch_my_company_user;
	private String monkey_patch_company_name;
	private Object monkey_patch_team_member;
	private int monkey_patch_todo_count;
	private int attendance_radius;
	private int distance;
	private PrimaryAddress monkey_patch_primary_address;
	private String dimension;
	private String orientation;
	private String code;
	
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
	public Object getAdmins() {
		return admins;
	}
	public void setAdmins(Object admins) {
		this.admins = admins;
	}
	public Object getAdmins_company_user_ids() {
		return admins_company_user_ids;
	}
	public void setAdmins_company_user_ids(Object admins_company_user_ids) {
		this.admins_company_user_ids = admins_company_user_ids;
	}
	public Object getWorkers_company_user_ids() {
		return workers_company_user_ids;
	}
	public void setWorkers_company_user_ids(Object workers_company_user_ids) {
		this.workers_company_user_ids = workers_company_user_ids;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public long getCustomer_contact() {
		return customer_contact;
	}
	public void setCustomer_contact(long customer_contact) {
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
	public Object getContact_book() {
		return contact_book;
	}
	public void setContact_book(Object contact_book) {
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
	public int getEstimated_cost() {
		return estimated_cost;
	}
	public void setEstimated_cost(int estimated_cost) {
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
	public long getMonkey_patch_contractor_contact() {
		return monkey_patch_contractor_contact;
	}
	public void setMonkey_patch_contractor_contact(long monkey_patch_contractor_contact) {
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
	public CompanyUser getMonkey_patch_my_company_user() {
		return monkey_patch_my_company_user;
	}
	public void setMonkey_patch_my_company_user(CompanyUser monkey_patch_my_company_user) {
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
	public PrimaryAddress getMonkey_patch_primary_address() {
		return monkey_patch_primary_address;
	}
	public void setMonkey_patch_primary_address(PrimaryAddress monkey_patch_primary_address) {
		this.monkey_patch_primary_address = monkey_patch_primary_address;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

class Location {
	
	private String type;
	private Object coordinates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Object coordinates) {
		this.coordinates = coordinates;
	}

}

class PrimaryAddress {
	
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
	private Location location;
	private Object google_address;
	private int primary;
	private int delete;
	private String created;
	private String updated;
	private String search;
	private CountryConfig monkey_patch_country_config;
	
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
	public CountryConfig getMonkey_patch_country_config() {
		return monkey_patch_country_config;
	}
	public void setMonkey_patch_country_config(CountryConfig monkey_patch_country_config) {
		this.monkey_patch_country_config = monkey_patch_country_config;
	}

}

class CountryConfig {
	
	private String country_code;
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
	private String timezone;

	
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
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}

class Invoice {
	
	private String id;
	private String company_id;
	private String creator_company_user_id;
	private String party_company_user_id;
	private String project_id;
	private String category_id;
	private String sub_category_id;
	private String feature_type;
	private String feature_id;
	private String invoice_type;
	private String status;
	private int total_payable;
	private int paid_amount;
	private int sequence;
	private int delete;
	private String invoice_date;
	private String created;
	private String updated;
	private String approval_flag;
	private Settlement monkey_patch_settlement;
	private Object monkey_patch_retention_entry;
	private Object monkey_patch_feature;
	private Project monkey_patch_project;
	
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
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public String getFeature_type() {
		return feature_type;
	}
	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
	}
	public String getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(String feature_id) {
		this.feature_id = feature_id;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(int total_payable) {
		this.total_payable = total_payable;
	}
	public int getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(int paid_amount) {
		this.paid_amount = paid_amount;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public String getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
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
	public String getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(String approval_flag) {
		this.approval_flag = approval_flag;
	}
	public Settlement getMonkey_patch_settlement() {
		return monkey_patch_settlement;
	}
	public void setMonkey_patch_settlement(Settlement monkey_patch_settlement) {
		this.monkey_patch_settlement = monkey_patch_settlement;
	}
	public Object getMonkey_patch_retention_entry() {
		return monkey_patch_retention_entry;
	}
	public void setMonkey_patch_retention_entry(Object monkey_patch_retention_entry) {
		this.monkey_patch_retention_entry = monkey_patch_retention_entry;
	}
	public Object getMonkey_patch_feature() {
		return monkey_patch_feature;
	}
	public void setMonkey_patch_feature(Object monkey_patch_feature) {
		this.monkey_patch_feature = monkey_patch_feature;
	}
	public Project getMonkey_patch_project() {
		return monkey_patch_project;
	}
	public void setMonkey_patch_project(Project monkey_patch_project) {
		this.monkey_patch_project = monkey_patch_project;
	}

}

class Settlement {
	
	private String id;
	private String creator_company_user_id;
	private String party_company_user_id;
	private String project_id;
	private String invoice_id;
	private String cashbooktransaction_id;
	private int settled_amount;
	private int delete;
	private String created;
	private String updated;
	private String monkey_patch_settlement_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
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
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getCashbooktransaction_id() {
		return cashbooktransaction_id;
	}
	public void setCashbooktransaction_id(String cashbooktransaction_id) {
		this.cashbooktransaction_id = cashbooktransaction_id;
	}
	public int getSettled_amount() {
		return settled_amount;
	}
	public void setSettled_amount(int settled_amount) {
		this.settled_amount = settled_amount;
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
	public String getMonkey_patch_settlement_date() {
		return monkey_patch_settlement_date;
	}
	public void setMonkey_patch_settlement_date(String monkey_patch_settlement_date) {
		this.monkey_patch_settlement_date = monkey_patch_settlement_date;
	}

}

class MetaData {
	private int can_edit;
	private int can_update_approval_flag;
	
	public int getCan_edit() {
		return can_edit;
	}
	public void setCan_edit(int can_edit) {
		this.can_edit = can_edit;
	}
	public int getCan_update_approval_flag() {
		return can_update_approval_flag;
	}
	public void setCan_update_approval_flag(int can_update_approval_flag) {
		this.can_update_approval_flag = can_update_approval_flag;
	}
}

