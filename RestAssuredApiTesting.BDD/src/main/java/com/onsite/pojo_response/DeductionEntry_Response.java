package com.onsite.pojo_response;

import java.time.LocalDateTime;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DeductionEntry_Response {
	
	private String id;
	private String salesorder_invoice_id;
	private String deduction_item_id;
	private String project_id;
	private String company_id;
	private String creator_company_user_id;
	private Double amount;
	private Integer delete;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String source_add;
	private String source_edit;
	//private SalesOrderInvoice monkey_patch_salesorder_invoice;
	private Interface[] monkey_patch_deduction_item;
	private Project monkey_patch_project;
	private Integer pre_tax;
	private String feature_id;
	private String feature_type;
	private String invoice_type;
	private String party_company_user_id;
	//private SubConExpence monkey_patch_subcon_expense;
	private LocalDateTime invoice_date;
	private String approval_flag;
	private Integer is_percent;
	private Double percentage;
	private String percent_of;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalesorder_invoice_id() {
		return salesorder_invoice_id;
	}
	public void setSalesorder_invoice_id(String salesorder_invoice_id) {
		this.salesorder_invoice_id = salesorder_invoice_id;
	}
	public String getDeduction_item_id() {
		return deduction_item_id;
	}
	public void setDeduction_item_id(String deduction_item_id) {
		this.deduction_item_id = deduction_item_id;
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
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
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
	public Interface[] getMonkey_patch_deduction_item() {
		return monkey_patch_deduction_item;
	}
	public void setMonkey_patch_deduction_item(Interface[] monkey_patch_deduction_item) {
		this.monkey_patch_deduction_item = monkey_patch_deduction_item;
	}
	public Project getMonkey_patch_project() {
		return monkey_patch_project;
	}
	public void setMonkey_patch_project(Project monkey_patch_project) {
		this.monkey_patch_project = monkey_patch_project;
	}
	public Integer getPre_tax() {
		return pre_tax;
	}
	public void setPre_tax(Integer pre_tax) {
		this.pre_tax = pre_tax;
	}
	public String getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(String feature_id) {
		this.feature_id = feature_id;
	}
	public String getFeature_type() {
		return feature_type;
	}
	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public LocalDateTime getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(LocalDateTime invoice_date) {
		this.invoice_date = invoice_date;
	}
	public String getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(String approval_flag) {
		this.approval_flag = approval_flag;
	}
	public Integer getIs_percent() {
		return is_percent;
	}
	public void setIs_percent(Integer is_percent) {
		this.is_percent = is_percent;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentag) {
		this.percentage = percentag;
	}
	public String getPercent_of() {
		return percent_of;
	}
	public void setPercent_of(String percent_of) {
		this.percent_of = percent_of;
	}

}
