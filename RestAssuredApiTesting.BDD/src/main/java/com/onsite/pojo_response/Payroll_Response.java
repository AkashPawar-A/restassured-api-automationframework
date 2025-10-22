package com.onsite.pojo_response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payroll_Response {

	private String id;
	private String creator;
	private String creator_company_user_id;
	private String company_id;
	private String salary_breakup_id;
	private String party_company_user_id;
	private String type;
	private Integer punch_effect;
	private String[] project_ids;
	private String[] track_project_ids;

	private Integer delete;
	private String created;
	private String updated;
	private String search;
	private String source_add;
	private String source_edit;
	private Object monkey_patch_source;
	private CompanyUserNormal monkey_patch_party_company_user;
	private Attendance monkey_patch_attendance;
	private Object monkey_patch_project_list;
	private Object monkey_patch_payroll;
	private Workforcestock[] monkey_patch_workforcestock;
	private SalaryBreakup monkey_patch_salary_breakup;
	private CompanyUserfaceInfo monkey_patch_face_info;
	private Project[] monkey_patch_associate_project;

	private String recent_project_id;
	private String workforce_id;
	private Workforce monkey_patch_workforce;
	private Integer hidden;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getSalary_breakup_id() {
		return salary_breakup_id;
	}

	public void setSalary_breakup_id(String salary_breakup_id) {
		this.salary_breakup_id = salary_breakup_id;
	}

	public String getParty_company_user_id() {
		return party_company_user_id;
	}

	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPunch_effect() {
		return punch_effect;
	}

	public void setPunch_effect(Integer punch_effect) {
		this.punch_effect = punch_effect;
	}

	public String[] getProject_ids() {
		return project_ids;
	}

	public void setProject_ids(String[] project_ids) {
		this.project_ids = project_ids;
	}

	public String[] getTrack_project_ids() {
		return track_project_ids;
	}

	public void setTrack_project_ids(String[] track_project_ids) {
		this.track_project_ids = track_project_ids;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
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

	public Object getMonkey_patch_source() {
		return monkey_patch_source;
	}

	public void setMonkey_patch_source(Object monkey_patch_source) {
		this.monkey_patch_source = monkey_patch_source;
	}

	public CompanyUserNormal getMonkey_patch_party_company_user() {
		return monkey_patch_party_company_user;
	}

	public void setMonkey_patch_party_company_user(CompanyUserNormal monkey_patch_party_company_user) {
		this.monkey_patch_party_company_user = monkey_patch_party_company_user;
	}

	public Attendance getMonkey_patch_attendance() {
		return monkey_patch_attendance;
	}

	public void setMonkey_patch_attendance(Attendance monkey_patch_attendance) {
		this.monkey_patch_attendance = monkey_patch_attendance;
	}

	public Object getMonkey_patch_project_list() {
		return monkey_patch_project_list;
	}

	public void setMonkey_patch_project_list(Object monkey_patch_project_list) {
		this.monkey_patch_project_list = monkey_patch_project_list;
	}

	public Object getMonkey_patch_payroll() {
		return monkey_patch_payroll;
	}

	public void setMonkey_patch_payroll(Object monkey_patch_payroll) {
		this.monkey_patch_payroll = monkey_patch_payroll;
	}

	public Workforcestock[] getMonkey_patch_workforcestock() {
		return monkey_patch_workforcestock;
	}

	public void setMonkey_patch_workforcestock(Workforcestock[] monkey_patch_workforcestock) {
		this.monkey_patch_workforcestock = monkey_patch_workforcestock;
	}

	public SalaryBreakup getMonkey_patch_salary_breakup() {
		return monkey_patch_salary_breakup;
	}

	public void setMonkey_patch_salary_breakup(SalaryBreakup monkey_patch_salary_breakup) {
		this.monkey_patch_salary_breakup = monkey_patch_salary_breakup;
	}

	public CompanyUserfaceInfo getMonkey_patch_face_info() {
		return monkey_patch_face_info;
	}

	public void setMonkey_patch_face_info(CompanyUserfaceInfo monkey_patch_face_info) {
		this.monkey_patch_face_info = monkey_patch_face_info;
	}

	public Project[] getMonkey_patch_associate_project() {
		return monkey_patch_associate_project;
	}

	public void setMonkey_patch_associate_project(Project[] monkey_patch_associate_project) {
		this.monkey_patch_associate_project = monkey_patch_associate_project;
	}

	public String getRecent_project_id() {
		return recent_project_id;
	}

	public void setRecent_project_id(String recent_project_id) {
		this.recent_project_id = recent_project_id;
	}

	public String getWorkforce_id() {
		return workforce_id;
	}

	public void setWorkforce_id(String workforce_id) {
		this.workforce_id = workforce_id;
	}

	public Workforce getMonkey_patch_workforce() {
		return monkey_patch_workforce;
	}

	public void setMonkey_patch_workforce(Workforce monkey_patch_workforce) {
		this.monkey_patch_workforce = monkey_patch_workforce;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	// ---------- Nested Classes ----------

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CompanyUserNormal {
		
		public String id;
		public String company_id;
		public String company_role_id;
		public String role;
		public String type;
		public String creator;
		public String name;
		public Long mobile;
		public Integer mobile_verified;
		public String user_id;
		public String gstin;
		public String party_id;
		public String legal_business_name;
		public String billing_address;
		public Integer hidden;
		public String profile_pic;
		public Object monkey_patch_policy_ids;
		public MonkeyPatchCompanyRole monkey_patch_company_role;
		public MonkeyPatchUser monkey_patch_user;
		public String email;
		
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
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public Integer getMobile_verified() {
			return mobile_verified;
		}
		public void setMobile_verified(Integer mobile_verified) {
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
		public Integer getHidden() {
			return hidden;
		}
		public void setHidden(Integer hidden) {
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
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchCompanyRole {
		
		public String id;
		public String role;
		public String name;
		public String description;
		public String company_id;
		public String creator_company_user_id;
		public Object policy_ids;
		public Integer hidden;
		public Integer delete;
		public String created;
		public String updated;
		public Object monkey_patch_policies;
		
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
		public Integer getHidden() {
			return hidden;
		}
		public void setHidden(Integer hidden) {
			this.hidden = hidden;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchUser {
		
		public String id;
		public String name;
		public Long mobile;
		public String country_code;
		public String profile_pic;
		public Integer mobile_verified;
		public Integer invited;
		public Integer invitation_count;
		public String profession;
		
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
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
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
		public Integer getMobile_verified() {
			return mobile_verified;
		}
		public void setMobile_verified(Integer mobile_verified) {
			this.mobile_verified = mobile_verified;
		}
		public Integer getInvited() {
			return invited;
		}
		public void setInvited(Integer invited) {
			this.invited = invited;
		}
		public Integer getInvitation_count() {
			return invitation_count;
		}
		public void setInvitation_count(Integer invitation_count) {
			this.invitation_count = invitation_count;
		}
		public String getProfession() {
			return profession;
		}
		public void setProfession(String profession) {
			this.profession = profession;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Attendance {
		
		public String id;
		public String company_id;
		public String status;
		public String remark;
		public String party_name;
		public Long party_mobile;
		public String party_id;
		public String workforcestock_id;
		public String payroll_id;
		public String salary_breakup_id;
		public String project_id;
		public String creator;
		public String creator_company_user_id;
		public String party_company_user_id;
		public Double wage;
		public Double workforce_count;
		public Double man_day;
		public Double shift_count;
		public Double man_hours;
		public Double shift_hours;
		public Double total_salary_payable;
		public Double total_ot_payable;
		public Double total_lt_payable;
		public Double total_allowance_payable;
		public Double total_deduction_payable;
		public Double total_payable;
		public String attendance_date;
		public Object project_meta;
		public Double over_time_hours;
		public Object photos;
		public Integer is_engine;
		public Integer is_absent;
		public Integer delete;
		public String created;
		public String updated;
		public Workforcestock monkey_patch_workforcestock;
		
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getParty_name() {
			return party_name;
		}
		public void setParty_name(String party_name) {
			this.party_name = party_name;
		}
		public Long getParty_mobile() {
			return party_mobile;
		}
		public void setParty_mobile(Long party_mobile) {
			this.party_mobile = party_mobile;
		}
		public String getParty_id() {
			return party_id;
		}
		public void setParty_id(String party_id) {
			this.party_id = party_id;
		}
		public String getWorkforcestock_id() {
			return workforcestock_id;
		}
		public void setWorkforcestock_id(String workforcestock_id) {
			this.workforcestock_id = workforcestock_id;
		}
		public String getPayroll_id() {
			return payroll_id;
		}
		public void setPayroll_id(String payroll_id) {
			this.payroll_id = payroll_id;
		}
		public String getSalary_breakup_id() {
			return salary_breakup_id;
		}
		public void setSalary_breakup_id(String salary_breakup_id) {
			this.salary_breakup_id = salary_breakup_id;
		}
		public String getProject_id() {
			return project_id;
		}
		public void setProject_id(String project_id) {
			this.project_id = project_id;
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
		public Double getWage() {
			return wage;
		}
		public void setWage(Double wage) {
			this.wage = wage;
		}
		public Double getworkforce_count() {
			return workforce_count;
		}
		public void setworkforce_count(Double workforce_count) {
			this.workforce_count = workforce_count;
		}
		public Double getMan_day() {
			return man_day;
		}
		public void setMan_day(Double man_day) {
			this.man_day = man_day;
		}
		public Double getShift_count() {
			return shift_count;
		}
		public void setShift_count(Double shift_count) {
			this.shift_count = shift_count;
		}
		public Double getMan_hours() {
			return man_hours;
		}
		public void setMan_hours(Double man_hours) {
			this.man_hours = man_hours;
		}
		public Double getShift_hours() {
			return shift_hours;
		}
		public void setShift_hours(Double shift_hours) {
			this.shift_hours = shift_hours;
		}
		public Double getTotal_salary_payable() {
			return total_salary_payable;
		}
		public void setTotal_salary_payable(Double total_salary_payable) {
			this.total_salary_payable = total_salary_payable;
		}
		public Double getTotal_ot_payable() {
			return total_ot_payable;
		}
		public void setTotal_ot_payable(Double total_ot_payable) {
			this.total_ot_payable = total_ot_payable;
		}
		public Double getTotal_lt_payable() {
			return total_lt_payable;
		}
		public void setTotal_lt_payable(Double total_lt_payable) {
			this.total_lt_payable = total_lt_payable;
		}
		public Double getTotal_allowance_payable() {
			return total_allowance_payable;
		}
		public void setTotal_allowance_payable(Double total_allowance_payable) {
			this.total_allowance_payable = total_allowance_payable;
		}
		public Double getTotal_deduction_payable() {
			return total_deduction_payable;
		}
		public void setTotal_deduction_payable(Double total_deduction_payable) {
			this.total_deduction_payable = total_deduction_payable;
		}
		public Double getTotal_payable() {
			return total_payable;
		}
		public void setTotal_payable(Double total_payable) {
			this.total_payable = total_payable;
		}
		public String getAttendance_date() {
			return attendance_date;
		}
		public void setAttendance_date(String attendance_date) {
			this.attendance_date = attendance_date;
		}
		public Object getProject_meta() {
			return project_meta;
		}
		public void setProject_meta(Object project_meta) {
			this.project_meta = project_meta;
		}
		public Double getOver_time_hours() {
			return over_time_hours;
		}
		public void setOver_time_hours(Double over_time_hours) {
			this.over_time_hours = over_time_hours;
		}
		public Object getPhotos() {
			return photos;
		}
		public void setPhotos(Object photos) {
			this.photos = photos;
		}
		public Integer getIs_engine() {
			return is_engine;
		}
		public void setIs_engine(Integer is_engine) {
			this.is_engine = is_engine;
		}
		public Integer getIs_absent() {
			return is_absent;
		}
		public void setIs_absent(Integer is_absent) {
			this.is_absent = is_absent;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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
		public Workforcestock getMonkey_patch_workforcestock() {
			return monkey_patch_workforcestock;
		}
		public void setMonkey_patch_workforcestock(Workforcestock monkey_patch_workforcestock) {
			this.monkey_patch_workforcestock = monkey_patch_workforcestock;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Workforcestock {
		public String id;
		public String type;
		public String name;
		public String party_name;
		public Long party_mobile;
		public String party_id;
		public String creator_company_user_id;
		public String party_company_user_id;
		public String payroll_id;
		public Double wage;
		public Double shift_hours;
		public String project_id;
		public String workforce_id;
		public String creator;
		public Integer is_engine;
		public Integer delete;
		public String created;
		public String updated;
		public Integer disabled;
		public Double overtime_wage;
		public String sub_category_id;
		public String monkey_patch_creator_name;
		public String monkey_patch_party_name;
		public String monkey_patch_party_company_user_name;
		public String monkey_patch_workforce_name;
		public Double monkey_patch_workforce_wage;
		public Object monkey_patch_attendance;
		public MonkeyPatchSubCategory monkey_patch_sub_category;
		public MonkeyPatchProject monkey_patch_project;
		
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
		public String getParty_name() {
			return party_name;
		}
		public void setParty_name(String party_name) {
			this.party_name = party_name;
		}
		public Long getParty_mobile() {
			return party_mobile;
		}
		public void setParty_mobile(Long party_mobile) {
			this.party_mobile = party_mobile;
		}
		public String getParty_id() {
			return party_id;
		}
		public void setParty_id(String party_id) {
			this.party_id = party_id;
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
		public String getPayroll_id() {
			return payroll_id;
		}
		public void setPayroll_id(String payroll_id) {
			this.payroll_id = payroll_id;
		}
		public Double getWage() {
			return wage;
		}
		public void setWage(Double wage) {
			this.wage = wage;
		}
		public Double getShift_hours() {
			return shift_hours;
		}
		public void setShift_hours(Double shift_hours) {
			this.shift_hours = shift_hours;
		}
		public String getProject_id() {
			return project_id;
		}
		public void setProject_id(String project_id) {
			this.project_id = project_id;
		}
		public String getWorkforce_id() {
			return workforce_id;
		}
		public void setWorkforce_id(String workforce_id) {
			this.workforce_id = workforce_id;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public Integer getIs_engine() {
			return is_engine;
		}
		public void setIs_engine(Integer is_engine) {
			this.is_engine = is_engine;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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
		public Integer getDisabled() {
			return disabled;
		}
		public void setDisabled(Integer disabled) {
			this.disabled = disabled;
		}
		public Double getOvertime_wage() {
			return overtime_wage;
		}
		public void setOvertime_wage(Double overtime_wage) {
			this.overtime_wage = overtime_wage;
		}
		public String getSub_category_id() {
			return sub_category_id;
		}
		public void setSub_category_id(String sub_category_id) {
			this.sub_category_id = sub_category_id;
		}
		public String getMonkey_patch_creator_name() {
			return monkey_patch_creator_name;
		}
		public void setMonkey_patch_creator_name(String monkey_patch_creator_name) {
			this.monkey_patch_creator_name = monkey_patch_creator_name;
		}
		public String getMonkey_patch_party_name() {
			return monkey_patch_party_name;
		}
		public void setMonkey_patch_party_name(String monkey_patch_party_name) {
			this.monkey_patch_party_name = monkey_patch_party_name;
		}
		public String getMonkey_patch_party_company_user_name() {
			return monkey_patch_party_company_user_name;
		}
		public void setMonkey_patch_party_company_user_name(String monkey_patch_party_company_user_name) {
			this.monkey_patch_party_company_user_name = monkey_patch_party_company_user_name;
		}
		public String getMonkey_patch_workforce_name() {
			return monkey_patch_workforce_name;
		}
		public void setMonkey_patch_workforce_name(String monkey_patch_workforce_name) {
			this.monkey_patch_workforce_name = monkey_patch_workforce_name;
		}
		public Double getMonkey_patch_workforce_wage() {
			return monkey_patch_workforce_wage;
		}
		public void setMonkey_patch_workforce_wage(Double monkey_patch_workforce_wage) {
			this.monkey_patch_workforce_wage = monkey_patch_workforce_wage;
		}
		public Object getMonkey_patch_attendance() {
			return monkey_patch_attendance;
		}
		public void setMonkey_patch_attendance(Object monkey_patch_attendance) {
			this.monkey_patch_attendance = monkey_patch_attendance;
		}
		public MonkeyPatchSubCategory getMonkey_patch_sub_category() {
			return monkey_patch_sub_category;
		}
		public void setMonkey_patch_sub_category(MonkeyPatchSubCategory monkey_patch_sub_category) {
			this.monkey_patch_sub_category = monkey_patch_sub_category;
		}
		public MonkeyPatchProject getMonkey_patch_project() {
			return monkey_patch_project;
		}
		public void setMonkey_patch_project(MonkeyPatchProject monkey_patch_project) {
			this.monkey_patch_project = monkey_patch_project;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchProject {
		
		public String id;
		public String type;
		public String name;
		public String contractor;
		public String contractor_company_user_id;
		public String creator;
		public String creator_company_user_id;
		public String bg_image;
		public String[] admins;
		public String[] admins_company_user_ids;
		public String[] workers_company_user_ids;
		public String customer_name;
		public Long customer_contact;
		public String customer_email;
		public String customer_company_name;
		public String customer_company_address;
		public String customer_gst;
		public String customer_profile_image;
		public String address;
		public String city;
		public String state;
		public String status;
		public String[] contact_book;
		public Integer is_engine;
		public String duplicate_from;
		public String created;
		public String updated;
		public Object contact_data;
		public Object photos;
		public Location location;
		public Object google_address;
		public Object allowed_features;
		public Double estimated_cost;
		public Double progress;
		public String start_date;
		public String end_date;
		public String monkey_patch_contractor_name;
		public String monkey_patch_contractor_company_user_name;
		public Long monkey_patch_contractor_contact;
		public String monkey_patch_contractor_profile_pic;
		public Double monkey_patch_in_amount;
		public Double monkey_patch_out_amount;
		public Double monkey_patch_transaction_in_amount;
		public Double monkey_patch_transaction_out_amount;
		public String company_id;
		public MonkeyPatchMyCompanyUser monkey_patch_my_company_user;
		public String monkey_patch_company_name;
		public Object monkey_patch_team_member;
		public Integer monkey_patch_todo_count;
		public Double attendance_radius;
		public Double distance;
		public MonkeyPatchPrimaryAddress monkey_patch_primary_address;
		public String dimension;
		public String orientation;
		public String code;
		public String[] key_personnel_cu_ids;
		public Double billed_amount;
		public Object monkey_patch_key_personnel;
		
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
		public String[] getAdmins() {
			return admins;
		}
		public void setAdmins(String[] admins) {
			this.admins = admins;
		}
		public String[] getAdmins_company_user_ids() {
			return admins_company_user_ids;
		}
		public void setAdmins_company_user_ids(String[] admins_company_user_ids) {
			this.admins_company_user_ids = admins_company_user_ids;
		}
		public String[] getWorkers_company_user_ids() {
			return workers_company_user_ids;
		}
		public void setWorkers_company_user_ids(String[] workers_company_user_ids) {
			this.workers_company_user_ids = workers_company_user_ids;
		}
		public String getCustomer_name() {
			return customer_name;
		}
		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}
		public Long getCustomer_contact() {
			return customer_contact;
		}
		public void setCustomer_contact(Long customer_contact) {
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
		public String[] getContact_book() {
			return contact_book;
		}
		public void setContact_book(String[] contact_book) {
			this.contact_book = contact_book;
		}
		public Integer getIs_engine() {
			return is_engine;
		}
		public void setIs_engine(Integer is_engine) {
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
		public Double getEstimated_cost() {
			return estimated_cost;
		}
		public void setEstimated_cost(Double estimated_cost) {
			this.estimated_cost = estimated_cost;
		}
		public Double getProgress() {
			return progress;
		}
		public void setProgress(Double progress) {
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
		public Long getMonkey_patch_contractor_contact() {
			return monkey_patch_contractor_contact;
		}
		public void setMonkey_patch_contractor_contact(Long monkey_patch_contractor_contact) {
			this.monkey_patch_contractor_contact = monkey_patch_contractor_contact;
		}
		public String getMonkey_patch_contractor_profile_pic() {
			return monkey_patch_contractor_profile_pic;
		}
		public void setMonkey_patch_contractor_profile_pic(String monkey_patch_contractor_profile_pic) {
			this.monkey_patch_contractor_profile_pic = monkey_patch_contractor_profile_pic;
		}
		public Double getMonkey_patch_in_amount() {
			return monkey_patch_in_amount;
		}
		public void setMonkey_patch_in_amount(Double monkey_patch_in_amount) {
			this.monkey_patch_in_amount = monkey_patch_in_amount;
		}
		public Double getMonkey_patch_out_amount() {
			return monkey_patch_out_amount;
		}
		public void setMonkey_patch_out_amount(Double monkey_patch_out_amount) {
			this.monkey_patch_out_amount = monkey_patch_out_amount;
		}
		public Double getMonkey_patch_transaction_in_amount() {
			return monkey_patch_transaction_in_amount;
		}
		public void setMonkey_patch_transaction_in_amount(Double monkey_patch_transaction_in_amount) {
			this.monkey_patch_transaction_in_amount = monkey_patch_transaction_in_amount;
		}
		public Double getMonkey_patch_transaction_out_amount() {
			return monkey_patch_transaction_out_amount;
		}
		public void setMonkey_patch_transaction_out_amount(Double monkey_patch_transaction_out_amount) {
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
		public Integer getMonkey_patch_todo_count() {
			return monkey_patch_todo_count;
		}
		public void setMonkey_patch_todo_count(Integer monkey_patch_todo_count) {
			this.monkey_patch_todo_count = monkey_patch_todo_count;
		}
		public Double getAttendance_radius() {
			return attendance_radius;
		}
		public void setAttendance_radius(Double attendance_radius) {
			this.attendance_radius = attendance_radius;
		}
		public Double getDistance() {
			return distance;
		}
		public void setDistance(Double distance) {
			this.distance = distance;
		}
		public MonkeyPatchPrimaryAddress getMonkey_patch_primary_address() {
			return monkey_patch_primary_address;
		}
		public void setMonkey_patch_primary_address(MonkeyPatchPrimaryAddress monkey_patch_primary_address) {
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
		public String[] getKey_personnel_cu_ids() {
			return key_personnel_cu_ids;
		}
		public void setKey_personnel_cu_ids(String[] key_personnel_cu_ids) {
			this.key_personnel_cu_ids = key_personnel_cu_ids;
		}
		public Double getBilled_amount() {
			return billed_amount;
		}
		public void setBilled_amount(Double billed_amount) {
			this.billed_amount = billed_amount;
		}
		public Object getMonkey_patch_key_personnel() {
			return monkey_patch_key_personnel;
		}
		public void setMonkey_patch_key_personnel(Object monkey_patch_key_personnel) {
			this.monkey_patch_key_personnel = monkey_patch_key_personnel;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchMyCompanyUser {
		
		public String id;
		public String company_id;
		public String company_role_id;
		public String role;
		public String type;
		public String creator;
		public String name;
		public Long mobile;
		public Integer mobile_verified;
		public String user_id;
		public String gstin;
		public String party_id;
		public String legal_business_name;
		public String billing_address;
		public Integer hidden;
		public String profile_pic;
		public Object monkey_patch_policy_ids;
		public MonkeyPatchCompanyRole monkey_patch_company_role;
		public MonkeyPatchUser monkey_patch_user;
		public String email;
		
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
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public Integer getMobile_verified() {
			return mobile_verified;
		}
		public void setMobile_verified(Integer mobile_verified) {
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
		public Integer getHidden() {
			return hidden;
		}
		public void setHidden(Integer hidden) {
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
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchPrimaryAddress {
		
		public String id;
		public String company_id;
		public String creator_company_user_id;
		public String owner_id;
		public String address_type;
		public String address_title;
		public String address_gst;
		public String address_line_1;
		public String address_line_2;
		public String city;
		public String state;
		public String postal_code;
		public String country_code;
		public Location location;
		public Object google_address;
		public Integer primary;
		public Integer delete;
		public String created;
		public String updated;
		public String search;
		public MonkeyPatchCountryConfig monkey_patch_country_config;
		
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
		public Integer getPrimary() {
			return primary;
		}
		public void setPrimary(Integer primary) {
			this.primary = primary;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Location {
		
		public String type;
		public Double[] coordinates;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Double[] getCoordinates() {
			return coordinates;
		}
		public void setCoordinates(Double[] coordinates) {
			this.coordinates = coordinates;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchCountryConfig {
		
		public String country_code;
		public String country_iso;
		public String country_name;
		public String created;
		public Integer minimum_digits;
		public Integer maximum_digits;
		public Object login_channels;
		public String currency;
		public String flag;
		public Object tax_slabs;
		public String tax_display_name;
		public String tax_value_display_name;
		public Integer published;
		public Integer decimal_digit;
		public String timezone;
		
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
		public Integer getMinimum_digits() {
			return minimum_digits;
		}
		public void setMinimum_digits(Integer minimum_digits) {
			this.minimum_digits = minimum_digits;
		}
		public Integer getMaximum_digits() {
			return maximum_digits;
		}
		public void setMaximum_digits(Integer maximum_digits) {
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
		public Integer getPublished() {
			return published;
		}
		public void setPublished(Integer published) {
			this.published = published;
		}
		public Integer getDecimal_digit() {
			return decimal_digit;
		}
		public void setDecimal_digit(Integer decimal_digit) {
			this.decimal_digit = decimal_digit;
		}
		public String getTimezone() {
			return timezone;
		}
		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchSubCategory {
		
		public String id;
		public String parent_id;
		public String creator;
		public String creator_company_user_id;
		public String company_id;
		public String type;
		public String text_en;
		public String text_hi;
		public Integer index;
		public Integer hidden;
		public String created;
		public String updated;
		public Object monkey_patch_children;
		public Object monkey_patch_parent;
		public String color_hex;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
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
		public String getCompany_id() {
			return company_id;
		}
		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getText_en() {
			return text_en;
		}
		public void setText_en(String text_en) {
			this.text_en = text_en;
		}
		public String getText_hi() {
			return text_hi;
		}
		public void setText_hi(String text_hi) {
			this.text_hi = text_hi;
		}
		public Integer getIndex() {
			return index;
		}
		public void setIndex(Integer index) {
			this.index = index;
		}
		public Integer getHidden() {
			return hidden;
		}
		public void setHidden(Integer hidden) {
			this.hidden = hidden;
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
		public Object getMonkey_patch_children() {
			return monkey_patch_children;
		}
		public void setMonkey_patch_children(Object monkey_patch_children) {
			this.monkey_patch_children = monkey_patch_children;
		}
		public Object getMonkey_patch_parent() {
			return monkey_patch_parent;
		}
		public void setMonkey_patch_parent(Object monkey_patch_parent) {
			this.monkey_patch_parent = monkey_patch_parent;
		}
		public String getColor_hex() {
			return color_hex;
		}
		public void setColor_hex(String color_hex) {
			this.color_hex = color_hex;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SalaryBreakup {
		
		public String id;
		public String template_id;
		public String payroll_id;
		public String creator;
		public String creator_company_user_id;
		public String company_id;
		public String type;
		public String[] dayoff;
		public Double ctc_amount;
		public Basic basic;
		public Double basic_salary;
		public String[] allowances;
		public FixedAllowance fixed_allowance;
		public Double gross_salary;
		public String[] deductions;
		public Double net_salary;
		public Double shift_hour;
		public Double overtime_amount;
		public String sub_category_id;
		public Integer delete;
		public String created;
		public String updated;
		public Object monkey_patch_source;
		public MonkeyPatchCreatorCompanyUser monkey_patch_creator_company_user;
		public MonkeyPatchSubCategory monkey_patch_sub_category;
		public Object monkey_patch_salarybreakup_components;
		public Integer attendance_count;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTemplate_id() {
			return template_id;
		}
		public void setTemplate_id(String template_id) {
			this.template_id = template_id;
		}
		public String getPayroll_id() {
			return payroll_id;
		}
		public void setPayroll_id(String payroll_id) {
			this.payroll_id = payroll_id;
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
		public String getCompany_id() {
			return company_id;
		}
		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String[] getDayoff() {
			return dayoff;
		}
		public void setDayoff(String[] dayoff) {
			this.dayoff = dayoff;
		}
		public Double getCtc_amount() {
			return ctc_amount;
		}
		public void setCtc_amount(Double ctc_amount) {
			this.ctc_amount = ctc_amount;
		}
		public Basic getBasic() {
			return basic;
		}
		public void setBasic(Basic basic) {
			this.basic = basic;
		}
		public Double getBasic_salary() {
			return basic_salary;
		}
		public void setBasic_salary(Double basic_salary) {
			this.basic_salary = basic_salary;
		}
		public String[] getAllowances() {
			return allowances;
		}
		public void setAllowances(String[] allowances) {
			this.allowances = allowances;
		}
		public FixedAllowance getFixed_allowance() {
			return fixed_allowance;
		}
		public void setFixed_allowance(FixedAllowance fixed_allowance) {
			this.fixed_allowance = fixed_allowance;
		}
		public Double getGross_salary() {
			return gross_salary;
		}
		public void setGross_salary(Double gross_salary) {
			this.gross_salary = gross_salary;
		}
		public String[] getDeductions() {
			return deductions;
		}
		public void setDeductions(String[] deductions) {
			this.deductions = deductions;
		}
		public Double getNet_salary() {
			return net_salary;
		}
		public void setNet_salary(Double net_salary) {
			this.net_salary = net_salary;
		}
		public Double getShift_hour() {
			return shift_hour;
		}
		public void setShift_hour(Double shift_hour) {
			this.shift_hour = shift_hour;
		}
		public Double getOvertime_amount() {
			return overtime_amount;
		}
		public void setOvertime_amount(Double overtime_amount) {
			this.overtime_amount = overtime_amount;
		}
		public String getSub_category_id() {
			return sub_category_id;
		}
		public void setSub_category_id(String sub_category_id) {
			this.sub_category_id = sub_category_id;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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
		public Object getMonkey_patch_source() {
			return monkey_patch_source;
		}
		public void setMonkey_patch_source(Object monkey_patch_source) {
			this.monkey_patch_source = monkey_patch_source;
		}
		public MonkeyPatchCreatorCompanyUser getMonkey_patch_creator_company_user() {
			return monkey_patch_creator_company_user;
		}
		public void setMonkey_patch_creator_company_user(MonkeyPatchCreatorCompanyUser monkey_patch_creator_company_user) {
			this.monkey_patch_creator_company_user = monkey_patch_creator_company_user;
		}
		public MonkeyPatchSubCategory getMonkey_patch_sub_category() {
			return monkey_patch_sub_category;
		}
		public void setMonkey_patch_sub_category(MonkeyPatchSubCategory monkey_patch_sub_category) {
			this.monkey_patch_sub_category = monkey_patch_sub_category;
		}
		public Object getMonkey_patch_salarybreakup_components() {
			return monkey_patch_salarybreakup_components;
		}
		public void setMonkey_patch_salarybreakup_components(Object monkey_patch_salarybreakup_components) {
			this.monkey_patch_salarybreakup_components = monkey_patch_salarybreakup_components;
		}
		public Integer getAttendance_count() {
			return attendance_count;
		}
		public void setAttendance_count(Integer attendance_count) {
			this.attendance_count = attendance_count;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Basic {
		
		public String id;
		public String name;
		public String relation_type;
		public Double relation_value;
		public Double amount;
		
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
		public String getRelation_type() {
			return relation_type;
		}
		public void setRelation_type(String relation_type) {
			this.relation_type = relation_type;
		}
		public Double getRelation_value() {
			return relation_value;
		}
		public void setRelation_value(Double relation_value) {
			this.relation_value = relation_value;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FixedAllowance {
		
		public String id;
		public String name;
		public String relation_type;
		public Double relation_value;
		public Double amount;
		
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
		public String getRelation_type() {
			return relation_type;
		}
		public void setRelation_type(String relation_type) {
			this.relation_type = relation_type;
		}
		public Double getRelation_value() {
			return relation_value;
		}
		public void setRelation_value(Double relation_value) {
			this.relation_value = relation_value;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchCreatorCompanyUser {
		
		public String id;
		public String company_id;
		public String company_role_id;
		public String role;
		public String type;
		public String creator;
		public String name;
		public Long mobile;
		public Integer mobile_verified;
		public String user_id;
		public String gstin;
		public String party_id;
		public String legal_business_name;
		public String billing_address;
		public Integer hidden;
		public String profile_pic;
		public Object monkey_patch_policy_ids;
		public MonkeyPatchCompanyRole monkey_patch_company_role;
		public MonkeyPatchUser monkey_patch_user;
		public String email;
		
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
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public Integer getMobile_verified() {
			return mobile_verified;
		}
		public void setMobile_verified(Integer mobile_verified) {
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
		public Integer getHidden() {
			return hidden;
		}
		public void setHidden(Integer hidden) {
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
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CompanyUserfaceInfo {
		
		public String id;
		public String created;
		public String updated;
		public String company_id;
		public String company_user_id;
		public String creator;
		public String creator_company_user_id;
		public Integer is_embedded;
		public Object photos;
		public Object embedding_vector;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getCompany_id() {
			return company_id;
		}
		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}
		public String getCompany_user_id() {
			return company_user_id;
		}
		public void setCompany_user_id(String company_user_id) {
			this.company_user_id = company_user_id;
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
		public Integer getIs_embedded() {
			return is_embedded;
		}
		public void setIs_embedded(Integer is_embedded) {
			this.is_embedded = is_embedded;
		}
		public Object getPhotos() {
			return photos;
		}
		public void setPhotos(Object photos) {
			this.photos = photos;
		}
		public Object getEmbedding_vector() {
			return embedding_vector;
		}
		public void setEmbedding_vector(Object embedding_vector) {
			this.embedding_vector = embedding_vector;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class MonkeyPatchAssociateProject extends MonkeyPatchProject { }

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Workforce {
		
		public String id;
		public String name;
		public String company_id;
		public String creator;
		public String type;
		public String creator_company_user_id;
		public String party_company_user_id;
		public Double wage;
		public Double shift_hours;
		public Integer is_engine;
		public String sub_category_id;
		public Integer delete;
		public String created;
		public String updated;
		public Integer monkey_patch_used_by_project;
		public MonkeyPatchSubCategory monkey_patch_sub_category;
		public Integer monkey_patch_present_count;
		
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
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		public Double getWage() {
			return wage;
		}
		public void setWage(Double wage) {
			this.wage = wage;
		}
		public Double getShift_hours() {
			return shift_hours;
		}
		public void setShift_hours(Double shift_hours) {
			this.shift_hours = shift_hours;
		}
		public Integer getIs_engine() {
			return is_engine;
		}
		public void setIs_engine(Integer is_engine) {
			this.is_engine = is_engine;
		}
		public String getSub_category_id() {
			return sub_category_id;
		}
		public void setSub_category_id(String sub_category_id) {
			this.sub_category_id = sub_category_id;
		}
		public Integer getDelete() {
			return delete;
		}
		public void setDelete(Integer delete) {
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
		public Integer getMonkey_patch_used_by_project() {
			return monkey_patch_used_by_project;
		}
		public void setMonkey_patch_used_by_project(Integer monkey_patch_used_by_project) {
			this.monkey_patch_used_by_project = monkey_patch_used_by_project;
		}
		public MonkeyPatchSubCategory getMonkey_patch_sub_category() {
			return monkey_patch_sub_category;
		}
		public void setMonkey_patch_sub_category(MonkeyPatchSubCategory monkey_patch_sub_category) {
			this.monkey_patch_sub_category = monkey_patch_sub_category;
		}
		public Integer getMonkey_patch_present_count() {
			return monkey_patch_present_count;
		}
		public void setMonkey_patch_present_count(Integer monkey_patch_present_count) {
			this.monkey_patch_present_count = monkey_patch_present_count;
		}
	}
}
