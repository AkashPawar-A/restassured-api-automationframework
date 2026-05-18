package com.onsite.pojo_response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.onsite.pojo_response.TimeSheetResponseBody.MonkeyPatchPartyCompanyUser.CustomField;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OtherExpenseResponse {
	
	private String id;
	private String company_id;
	private String project_id;
	private String invoice_id;
	private String remark;
	private Double earning_amount;
	private Double discount;
	private Double gst_amount;
	private Double other_amount;
	private Double amount;
	private String[] photos;
	private String category_id;
	private String sub_category_id;
	private String unit_id;
	private Double unit_price;
	private String approval_comment;
	private Double quantity;
	private Integer delete;
	private Integer is_engine;
	private String creator;
	private String creator_company_user_id;
	private String party_compay_user_id;
	private LocalDateTime payment_date;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String approval_flag;
	private String approved_by;
	private String search;
	private String earning_type;
	private CompanyUserNormal monkey_patch_creator_company_user;
	private CompanyUserNormal monkey_patch_party_company_user;
	private Integer monkey_patch_is_editable;
	private Double monkey_patch_paid_amount;
	private String monkey_patch_status;
	private String unit;
	private String source_add;
	private String source_edit;
	private SubCategory monkey_patch_sub_category;
	private Project monkey_patch_project;
	private CompanyUserNormal monkey_patch_approved_by;
	private Invoice monkey_patch_invoice;
	private MetaData meta_data;
	private Double due_days;
	private String ship_to_address_id;
	private String bill_to_address_id;
	private String ship_from_address_id;
	private String bill_from_address_id;
	private Address ship_to_address;
	private Address bill_to_address;
	private Address ship_from_address;
	private Address bill_from_address;
	private LocalDateTime due_date;
	private Double pre_tax_deduction_amount;
	private Double post_tax_deduction_amount;
	private Double deduction_amount;
	private Double net_amount;
	private Integer is_gst_percent;
	private Double gst_percent;
	private Integer is_roundoff;
	private String vendor_bill_number;
	private CustomField[] custom_field;
	private String equipment_stock_id;
	private String billing_activity_id;
//	private EquipmentStock monkey_patch_equipment_stock;
//	private BillingActivity monkey_patch_billing_activity;
	
	
	public Double getGst_percent() {
		return gst_percent;
	}
	public void setGst_percent(Double gst_percent) {
		this.gst_percent = gst_percent;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getEarning_amount() {
		return earning_amount;
	}
	public void setEarning_amount(Double earning_amount) {
		this.earning_amount = earning_amount;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
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
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}
	public String getApproval_comment() {
		return approval_comment;
	}
	public void setApproval_comment(String approval_comment) {
		this.approval_comment = approval_comment;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	public Integer getIs_engine() {
		return is_engine;
	}
	public void setIs_engine(Integer is_engine) {
		this.is_engine = is_engine;
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
	public String getParty_compay_user_id() {
		return party_compay_user_id;
	}
	public void setParty_compay_user_id(String party_compay_user_id) {
		this.party_compay_user_id = party_compay_user_id;
	}
	public LocalDateTime getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDateTime payment_date) {
		this.payment_date = payment_date;
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
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getEarning_type() {
		return earning_type;
	}
	public void setEarning_type(String earning_type) {
		this.earning_type = earning_type;
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
	public Double getMonkey_patch_paid_amount() {
		return monkey_patch_paid_amount;
	}
	public void setMonkey_patch_paid_amount(Double monkey_patch_paid_amount) {
		this.monkey_patch_paid_amount = monkey_patch_paid_amount;
	}
	public String getMonkey_patch_status() {
		return monkey_patch_status;
	}
	public void setMonkey_patch_status(String monkey_patch_status) {
		this.monkey_patch_status = monkey_patch_status;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public CompanyUserNormal getMonkey_patch_approved_by() {
		return monkey_patch_approved_by;
	}
	public void setMonkey_patch_approved_by(CompanyUserNormal monkey_patch_approved_by) {
		this.monkey_patch_approved_by = monkey_patch_approved_by;
	}
	public Invoice getMonkey_patch_invoice() {
		return monkey_patch_invoice;
	}
	public void setMonkey_patch_invoice(Invoice monkey_patch_invoice) {
		this.monkey_patch_invoice = monkey_patch_invoice;
	}
	public MetaData getMeta_data() {
		return meta_data;
	}
	public void setMeta_data(MetaData meta_data) {
		this.meta_data = meta_data;
	}
	public Double getDue_days() {
		return due_days;
	}
	public void setDue_days(Double due_days) {
		this.due_days = due_days;
	}
	public String getShip_to_address_id() {
		return ship_to_address_id;
	}
	public void setShip_to_address_id(String ship_to_address_id) {
		this.ship_to_address_id = ship_to_address_id;
	}
	public String getBill_to_address_id() {
		return bill_to_address_id;
	}
	public void setBill_to_address_id(String bill_to_address_id) {
		this.bill_to_address_id = bill_to_address_id;
	}
	public String getShip_from_address_id() {
		return ship_from_address_id;
	}
	public void setShip_from_address_id(String ship_from_address_id) {
		this.ship_from_address_id = ship_from_address_id;
	}
	public String getBill_from_address_id() {
		return bill_from_address_id;
	}
	public void setBill_from_address_id(String bill_from_address_id) {
		this.bill_from_address_id = bill_from_address_id;
	}
	public Address getShip_to_address() {
		return ship_to_address;
	}
	public void setShip_to_address(Address ship_to_address) {
		this.ship_to_address = ship_to_address;
	}
	public Address getBill_to_address() {
		return bill_to_address;
	}
	public void setBill_to_address(Address bill_to_address) {
		this.bill_to_address = bill_to_address;
	}
	public Address getShip_from_address() {
		return ship_from_address;
	}
	public void setShip_from_address(Address ship_from_address) {
		this.ship_from_address = ship_from_address;
	}
	public Address getBill_from_address() {
		return bill_from_address;
	}
	public void setBill_from_address(Address bill_from_address) {
		this.bill_from_address = bill_from_address;
	}
	public LocalDateTime getDue_date() {
		return due_date;
	}
	public void setDue_date(LocalDateTime due_date) {
		this.due_date = due_date;
	}
	public Double getPre_tax_deduction_amount() {
		return pre_tax_deduction_amount;
	}
	public void setPre_tax_deduction_amount(Double pre_tax_deduction_amount) {
		this.pre_tax_deduction_amount = pre_tax_deduction_amount;
	}
	public Double getPost_tax_deduction_amount() {
		return post_tax_deduction_amount;
	}
	public void setPost_tax_deduction_amount(Double post_tax_deduction_amount) {
		this.post_tax_deduction_amount = post_tax_deduction_amount;
	}
	public Double getDeduction_amount() {
		return deduction_amount;
	}
	public void setDeduction_amount(Double deduction_amount) {
		this.deduction_amount = deduction_amount;
	}
	public Double getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(Double net_amount) {
		this.net_amount = net_amount;
	}
	public Integer getIs_gst_percent() {
		return is_gst_percent;
	}
	public void setIs_gst_percent(Integer is_gst_percent) {
		this.is_gst_percent = is_gst_percent;
	}
	public Integer getIs_roundoff() {
		return is_roundoff;
	}
	public void setIs_roundoff(Integer is_roundoff) {
		this.is_roundoff = is_roundoff;
	}
	public String getVendor_bill_number() {
		return vendor_bill_number;
	}
	public void setVendor_bill_number(String vendor_bill_number) {
		this.vendor_bill_number = vendor_bill_number;
	}
	public CustomField[] getCustom_field() {
		return custom_field;
	}
	public void setCustom_field(CustomField[] custom_field) {
		this.custom_field = custom_field;
	}
	public String getEquipment_stock_id() {
		return equipment_stock_id;
	}
	public void setEquipment_stock_id(String equipment_stock_id) {
		this.equipment_stock_id = equipment_stock_id;
	}
	public String getBilling_activity_id() {
		return billing_activity_id;
	}
	public void setBilling_activity_id(String billing_activity_id) {
		this.billing_activity_id = billing_activity_id;
	}

}
