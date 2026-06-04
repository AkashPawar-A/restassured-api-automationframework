package com.onsite.pojo_response;

import java.time.LocalDateTime;

public class MaterialReturn_Response {
	
	private String id;
	private String company_id;
	private String remark;
	private String party_company_user_id;
	private String creator_company_user_id;
	private String project_id;
	private String sub_category_id;
	private String invoice_id;
	private String[] material_ids;
	private Double discount;
	private Double gst_amount;
	private Double other_amount;
	private Double material_amount;
	private Double total_payable;
	private Integer sequence;
	private String[] photos;
	private LocalDateTime return_date;
	private String vendor_reference_number;
	private Integer delete;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String approval_flag;
	private Material[] monkey_patch_materials;
	private CompanyUserNormal monkey_patch_creator_company_user;
	private CompanyUserNormal monkey_patch_party_company_user;
	private Integer monkey_patch_is_editable;
	private Integer monkey_patch_is_paid_amount;
	private String monkey_patch_status;
	private String source_add;
	private String source_edit;
	private SubCategory monkey_patch_sub_category;
	private Project monkey_patch_project;
	private Invoice monkey_patch_invoice;
	private CompanyUserNormal monkey_patch_approved_by;
	private String approval_comment;
	private String approved_by;
	private Double due_days;
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String[] getMaterial_ids() {
		return material_ids;
	}
	public void setMaterial_ids(String[] material_ids) {
		this.material_ids = material_ids;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(Double gst_amount) {
		this.gst_amount = gst_amount;
	}
	public Double getOther_amount() {
		return other_amount;
	}
	public void setOther_amount(Double other_amount) {
		this.other_amount = other_amount;
	}
	public Double getMaterial_amount() {
		return material_amount;
	}
	public void setMaterial_amount(Double material_amount) {
		this.material_amount = material_amount;
	}
	public Double getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(Double total_payable) {
		this.total_payable = total_payable;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public LocalDateTime getReturn_date() {
		return return_date;
	}
	public void setReturn_date(LocalDateTime return_date) {
		this.return_date = return_date;
	}
	public String getVendor_reference_number() {
		return vendor_reference_number;
	}
	public void setVendor_reference_number(String vendor_reference_number) {
		this.vendor_reference_number = vendor_reference_number;
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
	public String getApproval_flag() {
		return approval_flag;
	}
	public void setApproval_flag(String approval_flag) {
		this.approval_flag = approval_flag;
	}
	public Material[] getMonkey_patch_materials() {
		return monkey_patch_materials;
	}
	public void setMonkey_patch_materials(Material[] monkey_patch_materials) {
		this.monkey_patch_materials = monkey_patch_materials;
	}
	public CompanyUserNormal getMonkey_patch_creator_company_user() {
		return monkey_patch_creator_company_user;
	}
	public void setMonkey_patch_creator_company_user(CompanyUserNormal monkey_patch_creator_company_user) {
		this.monkey_patch_creator_company_user = monkey_patch_creator_company_user;
	}
	public CompanyUserNormal getMonkey_patch_party_company_user() {
		return monkey_patch_party_company_user;
	}
	public void setMonkey_patch_party_company_user(CompanyUserNormal monkey_patch_party_company_user) {
		this.monkey_patch_party_company_user = monkey_patch_party_company_user;
	}
	public Integer getMonkey_patch_is_editable() {
		return monkey_patch_is_editable;
	}
	public void setMonkey_patch_is_editable(Integer monkey_patch_is_editable) {
		this.monkey_patch_is_editable = monkey_patch_is_editable;
	}
	public Integer getMonkey_patch_is_paid_amount() {
		return monkey_patch_is_paid_amount;
	}
	public void setMonkey_patch_is_paid_amount(Integer monkey_patch_is_paid_amount) {
		this.monkey_patch_is_paid_amount = monkey_patch_is_paid_amount;
	}
	public String getMonkey_patch_status() {
		return monkey_patch_status;
	}
	public void setMonkey_patch_status(String monkey_patch_status) {
		this.monkey_patch_status = monkey_patch_status;
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
	public SubCategory getMonkey_patch_sub_category() {
		return monkey_patch_sub_category;
	}
	public void setMonkey_patch_sub_category(SubCategory monkey_patch_sub_category) {
		this.monkey_patch_sub_category = monkey_patch_sub_category;
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
	public CompanyUserNormal getMonkey_patch_approved_by() {
		return monkey_patch_approved_by;
	}
	public void setMonkey_patch_approved_by(CompanyUserNormal monkey_patch_approved_by) {
		this.monkey_patch_approved_by = monkey_patch_approved_by;
	}
	public String getApproval_comment() {
		return approval_comment;
	}
	public void setApproval_comment(String approval_comment) {
		this.approval_comment = approval_comment;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public Double getDue_days() {
		return due_days;
	}
	public void setDue_days(Double due_days) {
		this.due_days = due_days;
	}

}
