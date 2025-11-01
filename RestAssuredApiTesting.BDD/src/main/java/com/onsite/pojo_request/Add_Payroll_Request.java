package com.onsite.pojo_request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Add_Payroll_Request {
	
	private String party_company_user_id;
	private String type;
	private List<String> project_ids;
	private Integer punch_effect;
	private String workforce_id;

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
	public List<String> getProject_ids() {
		return project_ids;
	}
	public void setProject_ids(List<String> project_ids) {
		this.project_ids = project_ids;
	}
	public Integer getPunch_effect() {
		return punch_effect;
	}
	public void setPunch_effect(Integer punch_effect) {
		this.punch_effect = punch_effect;
	}
	public String getWorkforce_id() {
		return workforce_id;
	}
	public void setWorkforce_id(String workforce_id) {
		this.workforce_id = workforce_id;
	}
	
	private String id;
	private String salary_breakup_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalary_breakup_id() {
		return salary_breakup_id;
	}
	public void setSalary_breakup_id(String salary_breakup_id) {
		this.salary_breakup_id = salary_breakup_id;
	}
	
}
