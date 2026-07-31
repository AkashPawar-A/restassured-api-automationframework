package com.onsite.pojo_response;

import java.time.LocalDateTime;

public class MaterialStock_Response {
	
	private String id;
	private String project_id;
	private String material_item_id;
	private String creator;
	private String creator_company_user_id;
	private Double quantity_balance;
	private Double in_quantity_amount;
	private Double out_quantity_amount;
    private Double used_quantity;
    private Double returned_quantity;
    private Double sold_quantity;
    private Double recevied_quantity;
    private Double is_received_quantity;
    private Double opening_stock;
    private Double estimated_quantity;
    private Double transfer_in_quantity;
    private Double transfer_out_quantity;
    private LocalDateTime opening_stock_date;
    private Integer is_engine;
    private Integer delete;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String monkey_patch_creator_name;
    private String monkey_patch_creator_company_user_name;
    private String monkey_patch_material_name;
    private String monkey_patch_material_category;
    private String monkey_patch_material_sub_category;
    private String monkey_patch_material_unit;
    private String monkey_patch_material_item;
    private String monkey_patch_material;
    private Double issued_out_quantity;
    private Double issued_in_quantity;
    private Double pending_requested_quantity;
    private Double approved_requested_quantity;
    private Double approved_po_quantity;
    private Double pending_po_quantity;
    private Double budget_unit_rate;
    private String company_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getMaterial_item_id() {
		return material_item_id;
	}
	public void setMaterial_item_id(String material_item_id) {
		this.material_item_id = material_item_id;
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
	public Double getQuantity_balance() {
		return quantity_balance;
	}
	public void setQuantity_balance(Double quantity_balance) {
		this.quantity_balance = quantity_balance;
	}
	public Double getIn_quantity_amount() {
		return in_quantity_amount;
	}
	public void setIn_quantity_amount(Double in_quantity_amount) {
		this.in_quantity_amount = in_quantity_amount;
	}
	public Double getOut_quantity_amount() {
		return out_quantity_amount;
	}
	public void setOut_quantity_amount(Double out_quantity_amount) {
		this.out_quantity_amount = out_quantity_amount;
	}
	public Double getUsed_quantity() {
		return used_quantity;
	}
	public void setUsed_quantity(Double used_quantity) {
		this.used_quantity = used_quantity;
	}
	public Double getReturned_quantity() {
		return returned_quantity;
	}
	public void setReturned_quantity(Double returned_quantity) {
		this.returned_quantity = returned_quantity;
	}
	public Double getSold_quantity() {
		return sold_quantity;
	}
	public void setSold_quantity(Double sold_quantity) {
		this.sold_quantity = sold_quantity;
	}
	public Double getRecevied_quantity() {
		return recevied_quantity;
	}
	public void setRecevied_quantity(Double recevied_quantity) {
		this.recevied_quantity = recevied_quantity;
	}
	public Double getIs_received_quantity() {
		return is_received_quantity;
	}
	public void setIs_received_quantity(Double is_received_quantity) {
		this.is_received_quantity = is_received_quantity;
	}
	public Double getOpening_stock() {
		return opening_stock;
	}
	public void setOpening_stock(Double opening_stock) {
		this.opening_stock = opening_stock;
	}
	public Double getEstimated_quantity() {
		return estimated_quantity;
	}
	public void setEstimated_quantity(Double estimated_quantity) {
		this.estimated_quantity = estimated_quantity;
	}
	public Double getTransfer_in_quantity() {
		return transfer_in_quantity;
	}
	public void setTransfer_in_quantity(Double transfer_in_quantity) {
		this.transfer_in_quantity = transfer_in_quantity;
	}
	public Double getTransfer_out_quantity() {
		return transfer_out_quantity;
	}
	public void setTransfer_out_quantity(Double transfer_out_quantity) {
		this.transfer_out_quantity = transfer_out_quantity;
	}
	public LocalDateTime getOpening_stock_date() {
		return opening_stock_date;
	}
	public void setOpening_stock_date(LocalDateTime opening_stock_date) {
		this.opening_stock_date = opening_stock_date;
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
	public String getMonkey_patch_creator_name() {
		return monkey_patch_creator_name;
	}
	public void setMonkey_patch_creator_name(String monkey_patch_creator_name) {
		this.monkey_patch_creator_name = monkey_patch_creator_name;
	}
	public String getMonkey_patch_creator_company_user_name() {
		return monkey_patch_creator_company_user_name;
	}
	public void setMonkey_patch_creator_company_user_name(String monkey_patch_creator_company_user_name) {
		this.monkey_patch_creator_company_user_name = monkey_patch_creator_company_user_name;
	}
	public String getMonkey_patch_material_name() {
		return monkey_patch_material_name;
	}
	public void setMonkey_patch_material_name(String monkey_patch_material_name) {
		this.monkey_patch_material_name = monkey_patch_material_name;
	}
	public String getMonkey_patch_material_category() {
		return monkey_patch_material_category;
	}
	public void setMonkey_patch_material_category(String monkey_patch_material_category) {
		this.monkey_patch_material_category = monkey_patch_material_category;
	}
	public String getMonkey_patch_material_sub_category() {
		return monkey_patch_material_sub_category;
	}
	public void setMonkey_patch_material_sub_category(String monkey_patch_material_sub_category) {
		this.monkey_patch_material_sub_category = monkey_patch_material_sub_category;
	}
	public String getMonkey_patch_material_unit() {
		return monkey_patch_material_unit;
	}
	public void setMonkey_patch_material_unit(String monkey_patch_material_unit) {
		this.monkey_patch_material_unit = monkey_patch_material_unit;
	}
	public String getMonkey_patch_material_item() {
		return monkey_patch_material_item;
	}
	public void setMonkey_patch_material_item(String monkey_patch_material_item) {
		this.monkey_patch_material_item = monkey_patch_material_item;
	}
	public String getMonkey_patch_material() {
		return monkey_patch_material;
	}
	public void setMonkey_patch_material(String monkey_patch_material) {
		this.monkey_patch_material = monkey_patch_material;
	}
	public Double getIssued_out_quantity() {
		return issued_out_quantity;
	}
	public void setIssued_out_quantity(Double issued_out_quantity) {
		this.issued_out_quantity = issued_out_quantity;
	}
	public Double getIssued_in_quantity() {
		return issued_in_quantity;
	}
	public void setIssued_in_quantity(Double issued_in_quantity) {
		this.issued_in_quantity = issued_in_quantity;
	}
	public Double getPending_requested_quantity() {
		return pending_requested_quantity;
	}
	public void setPending_requested_quantity(Double pending_requested_quantity) {
		this.pending_requested_quantity = pending_requested_quantity;
	}
	public Double getApproved_requested_quantity() {
		return approved_requested_quantity;
	}
	public void setApproved_requested_quantity(Double approved_requested_quantity) {
		this.approved_requested_quantity = approved_requested_quantity;
	}
	public Double getApproved_po_quantity() {
		return approved_po_quantity;
	}
	public void setApproved_po_quantity(Double approved_po_quantity) {
		this.approved_po_quantity = approved_po_quantity;
	}
	public Double getPending_po_quantity() {
		return pending_po_quantity;
	}
	public void setPending_po_quantity(Double pending_po_quantity) {
		this.pending_po_quantity = pending_po_quantity;
	}
	public Double getBudget_unit_rate() {
		return budget_unit_rate;
	}
	public void setBudget_unit_rate(Double budget_unit_rate) {
		this.budget_unit_rate = budget_unit_rate;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

}
