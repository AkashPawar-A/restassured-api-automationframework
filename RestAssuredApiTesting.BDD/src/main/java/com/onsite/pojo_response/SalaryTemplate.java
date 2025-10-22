package com.onsite.pojo_response;

public class SalaryTemplate {
	
	private String id;
	private String creator;
	private String creator_company_user_id;
	private String company_id;
	private String name;
	private float ctc_amount;
	private float gross_amount;
	private float net_amount;
	private int[] dayoff;
	private String type;
	private SalaryTemplateComponant basic;
	private SalaryTemplateComponant[] allowance;
	private SalaryTemplateComponant fixed_allowance;
	private SalaryTemplateComponant[] deductions;
	private int is_active;
	private String notes;
	private int delete;
	private String created;
	private String updated;
	private String search;
	private String source_add;
	private String source_edit;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCtc_amount() {
		return ctc_amount;
	}
	public void setCtc_amount(float ctc_amount) {
		this.ctc_amount = ctc_amount;
	}
	public float getGross_amount() {
		return gross_amount;
	}
	public void setGross_amount(float gross_amount) {
		this.gross_amount = gross_amount;
	}
	public float getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(float net_amount) {
		this.net_amount = net_amount;
	}
	public int[] getDayoff() {
		return dayoff;
	}
	public void setDayoff(int[] dayoff) {
		this.dayoff = dayoff;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SalaryTemplateComponant getBasic() {
		return basic;
	}
	public void setBasic(SalaryTemplateComponant basic) {
		this.basic = basic;
	}
	public SalaryTemplateComponant[] getAllowance() {
		return allowance;
	}
	public void setAllowance(SalaryTemplateComponant[] allowance) {
		this.allowance = allowance;
	}
	public SalaryTemplateComponant getFixed_allowance() {
		return fixed_allowance;
	}
	public void setFixed_allowance(SalaryTemplateComponant fixed_allowance) {
		this.fixed_allowance = fixed_allowance;
	}
	public SalaryTemplateComponant[] getDeductions() {
		return deductions;
	}
	public void setDeductions(SalaryTemplateComponant[] deductions) {
		this.deductions = deductions;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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

}
