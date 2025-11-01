package com.onsite.pojo_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SalaryBreakup {

	private String id;
	private String template_id;
	private String payroll_id;
	private String creator;
	private String creator_company_user_id;
	private String company_id;
	private String type;
	private Double[] dayoff;
	private SalaryTemplateComponant basic;
	private float basic_salary;
	private SalaryTemplateComponant[] allowances;
	private SalaryTemplateComponant fixed_allowance;
	private float gross_salary;
	private SalaryTemplateComponant[] deductions;
	private float net_salary;
	private float shift_hour;
	private float overtime_amount;
	private String sub_category_id;
	private int delete;
	private String created;
	private String updated;
	private String Search;
	private String SourceAdd;
	private String SourceEdit;
	private int AttendanceCount;
	
	private Object monkey_patch_source;
	private CompanyUserNormal monkey_patch_creator_company_user;
	private SubCategory monkey_patch_sub_category;
	private SalaryBreakupComponent[] monkey_patch_salarybreakup_components;
	
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
	public Double[] getDayoff() {
		return dayoff;
	}
	public void setDayoff(Double[] dayoff) {
		this.dayoff = dayoff;
	}
	public SalaryTemplateComponant getBasic() {
		return basic;
	}
	public void setBasic(SalaryTemplateComponant basic) {
		this.basic = basic;
	}
	public float getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(float basic_salary) {
		this.basic_salary = basic_salary;
	}
	public SalaryTemplateComponant[] getAllowances() {
		return allowances;
	}
	public void setAllowances(SalaryTemplateComponant[] allowances) {
		this.allowances = allowances;
	}
	public SalaryTemplateComponant getFixed_allowance() {
		return fixed_allowance;
	}
	public void setFixed_allowance(SalaryTemplateComponant fixed_allowance) {
		this.fixed_allowance = fixed_allowance;
	}
	public float getGross_salary() {
		return gross_salary;
	}
	public void setGross_salary(float gross_salary) {
		this.gross_salary = gross_salary;
	}
	public SalaryTemplateComponant[] getDeductions() {
		return deductions;
	}
	public void setDeductions(SalaryTemplateComponant[] deductions) {
		this.deductions = deductions;
	}
	public float getNet_salary() {
		return net_salary;
	}
	public void setNet_salary(float net_salary) {
		this.net_salary = net_salary;
	}
	public float getShift_hour() {
		return shift_hour;
	}
	public void setShift_hour(float shift_hour) {
		this.shift_hour = shift_hour;
	}
	public float getOvertime_amount() {
		return overtime_amount;
	}
	public void setOvertime_amount(float overtime_amount) {
		this.overtime_amount = overtime_amount;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
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
		return Search;
	}
	public void setSearch(String search) {
		Search = search;
	}
	public String getSourceAdd() {
		return SourceAdd;
	}
	public void setSourceAdd(String sourceAdd) {
		SourceAdd = sourceAdd;
	}
	public String getSourceEdit() {
		return SourceEdit;
	}
	public void setSourceEdit(String sourceEdit) {
		SourceEdit = sourceEdit;
	}
	public int getAttendanceCount() {
		return AttendanceCount;
	}
	public void setAttendanceCount(int attendanceCount) {
		AttendanceCount = attendanceCount;
	}
	public Object getMonkey_patch_source() {
		return monkey_patch_source;
	}
	public void setMonkey_patch_source(Object monkey_patch_source) {
		this.monkey_patch_source = monkey_patch_source;
	}
	public CompanyUserNormal getMonkey_patch_creator_company_user() {
		return monkey_patch_creator_company_user;
	}
	public void setMonkey_patch_creator_company_user(CompanyUserNormal monkey_patch_creator_company_user) {
		this.monkey_patch_creator_company_user = monkey_patch_creator_company_user;
	}
	public SubCategory getMonkey_patch_sub_category() {
		return monkey_patch_sub_category;
	}
	public void setMonkey_patch_sub_category(SubCategory monkey_patch_sub_category) {
		this.monkey_patch_sub_category = monkey_patch_sub_category;
	}
	public SalaryBreakupComponent[] getMonkey_patch_salarybreakup_components() {
		return monkey_patch_salarybreakup_components;
	}
	public void setMonkey_patch_salarybreakup_components(SalaryBreakupComponent[] monkey_patch_salarybreakup_components) {
		this.monkey_patch_salarybreakup_components = monkey_patch_salarybreakup_components;
	}
	
}
