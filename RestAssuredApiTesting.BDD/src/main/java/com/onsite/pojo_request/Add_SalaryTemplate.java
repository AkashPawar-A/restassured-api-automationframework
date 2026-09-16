package com.onsite.pojo_request;

import com.onsite.pojo_response.SalaryTemplateComponant;

public class Add_SalaryTemplate {
	
	private String company_id;
	private String name;
	private Double ctc_amount;
	private Double gross_amount;
	private Double net_amount;
	private Integer[] dayoff;
	private String type;    //monthly, Daily
	private SalaryTemplateComponant basic;
	private SalaryTemplateComponant[] allowance;
	private SalaryTemplateComponant fixed_allowance;
	private SalaryTemplateComponant[] deductions;
	private String notes;
	private Integer is_active;
	private Integer is_pf;
	private Integer is_esic;
	private Integer is_pt;
	private Integer is_lwf;
	private String work_state;
	
	public Integer getIs_pf() {
		return is_pf;
	}
	public void setIs_pf(Integer is_pf) {
		this.is_pf = is_pf;
	}
	public Integer getIs_esic() {
		return is_esic;
	}
	public void setIs_esic(Integer is_esic) {
		this.is_esic = is_esic;
	}
	public Integer getIs_pt() {
		return is_pt;
	}
	public void setIs_pt(Integer is_pt) {
		this.is_pt = is_pt;
	}
	public Integer getIs_lwf() {
		return is_lwf;
	}
	public void setIs_lwf(Integer is_lwf) {
		this.is_lwf = is_lwf;
	}
	public String getWork_state() {
		return work_state;
	}
	public void setWork_state(String work_state) {
		this.work_state = work_state;
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
	public Double getCtc_amount() {
		return ctc_amount;
	}
	public void setCtc_amount(Double ctc_amount) {
		this.ctc_amount = ctc_amount;
	}
	public Double getGross_amount() {
		return gross_amount;
	}
	public void setGross_amount(Double gross_amount) {
		this.gross_amount = gross_amount;
	}
	public Double getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(Double net_amount) {
		this.net_amount = net_amount;
	}
	public Integer[] getDayoff() {
		return dayoff;
	}
	public void setDayoff(Integer[] dayoff) {
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getIs_active() {
		return is_active;
	}
	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}
}
