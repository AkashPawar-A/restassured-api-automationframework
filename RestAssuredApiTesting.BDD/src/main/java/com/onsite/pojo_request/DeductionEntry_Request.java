package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DeductionEntry_Request {
	
	private String deduction_item_id;
	private String salesorder_invoice_id;
	private String project_id;
	private Double amount;
	private Integer pre_tax;
	private Integer is_percent;
	private Double percentage;
	private String percent_of;
	
	public Integer getIs_percent() {
		return is_percent;
	}
	public void setIs_percent(Integer is_percent) {
		this.is_percent = is_percent;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public String getPercent_of() {
		return percent_of;
	}
	public void setPercent_of(String percent_of) {
		this.percent_of = percent_of;
	}
	public String getDeduction_item_id() {
		return deduction_item_id;
	}
	public void setDeduction_item_id(String deduction_item_id) {
		this.deduction_item_id = deduction_item_id;
	}
	public String getSalesorder_invoice_id() {
		return salesorder_invoice_id;
	}
	public void setSalesorder_invoice_id(String salesorder_invoice_id) {
		this.salesorder_invoice_id = salesorder_invoice_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getPre_tax() {
		return pre_tax;
	}
	public void setPre_tax(Integer pre_tax) {
		this.pre_tax = pre_tax;
	}

}
