package com.onsite.pojo_request;

public class Edit_Payroll_Request {
	
	private String id;
	private String salary_breakup_id;
	private String punch_effect;
	private String workforce_id;
	private String project_ids;
	
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
	public String getPunch_effect() {
		return punch_effect;
	}
	public void setPunch_effect(String punch_effect) {
		this.punch_effect = punch_effect;
	}
	public String getWorkforce_id() {
		return workforce_id;
	}
	public void setWorkforce_id(String workforce_id) {
		this.workforce_id = workforce_id;
	}
	public String getProject_ids() {
		return project_ids;
	}
	public void setProject_ids(String project_ids) {
		this.project_ids = project_ids;
	}

}
