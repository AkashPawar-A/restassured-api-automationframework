package com.onsite.pojo_response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Material {
	
	private String id;
	private String company_id;
	private String project_id;
	private String type;
	private String remark;
	private String party_name;
	private Integer pary_mobile;
	private String party_id;
	private String creator_company_user_id;
	private String party_company_user_id;
	private String materialstock_id;
	private String creator;
	private Double quantity;
	private Double unit_price;
	private Double total_payable;
	private LocalDateTime receiving_date;
	private String role;
	private Integer index;
	private List<String> photos;
	private Integer is_engine;
	private Integer is_purchased;
	private Integer is_material_returend;
	private Integer is_material_sold;
	private Integer is_received;
	private Integer is_discount_percent;
	private Double gst_amount;
	private Double gst_percent;
	private Double discount_value;
	private Double discount_amount;
	private String material_purchase_id;
	private String material_return_id;
	private String material_sale_id;
	private String material_transfer_id;
	private Integer is_material_transferred;
	private Integer delete;
	private String billing_activity_id;
	private String billing_sub_activity_id;
	private String equipment_stock_id;
	private String grn_id;
	private String purchase_order_item_id;
	private Integer is_grn;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String search;
	private String monkey_patch_creator_name;
	private String monkey_patch_creator_company_user_name;
	private String monkey_patch_party_company_user_name;
	//private MaterialStock monkey_patch_materialstock;
	private MaterialItem monkey_patch_materialitem;
	private String monkey_patch_project_name;
	private String monkey_patch_billing_activity_name;
	private String monkey_patch_equipment_name;
	private String monkey_patch_equipment_number;
	private Project monkey_patch_sender_project;
	private Project monkey_patch_receiver_project;
	//private PurchaseOrder monkey_patch_purchase_order;
	//private PurchaseOrderItem monkey_patch_purchase_order_item;
	private String source_add;
	private String source_edit;
	//private GRN monkey_patch_grn;
	private Double damage_quantity;
	private String workorder_id;
	private Integer is_material_issued;
	//private WorkOrder monkey_patch_work_order;
	//private bool monkey_patch_is_editable;
	private String prefix;
	private Integer sequence;
	private String parent_material_id;
	private String poduction_type;
	private CompanyUserNormal monkey_patch_party_company_user;
	private Material[] monkey_patch_production_breakup_material_entry;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public int getPary_mobile() {
		return pary_mobile;
	}
	public void setPary_mobile(Integer pary_mobile) {
		this.pary_mobile = pary_mobile;
	}
	public String getParty_id() {
		return party_id;
	}
	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
	}
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public String getMaterialstock_id() {
		return materialstock_id;
	}
	public void setMaterialstock_id(String materialstock_id) {
		this.materialstock_id = materialstock_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public double getQuanitity() {
		return quantity;
	}
	public void setQuanitity(Double quanitity) {
		this.quantity = quanitity;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}
	public double getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(Double total_payable) {
		this.total_payable = total_payable;
	}
	public LocalDateTime getReceiving_date() {
		return receiving_date;
	}
	public void setReceiving_date(LocalDateTime receiving_date) {
		this.receiving_date = receiving_date;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	public int getIs_engine() {
		return is_engine;
	}
	public void setIs_engine(Integer is_engine) {
		this.is_engine = is_engine;
	}
	public int getIs_purchased() {
		return is_purchased;
	}
	public void setIs_purchased(Integer is_purchased) {
		this.is_purchased = is_purchased;
	}
	public int getIs_material_returend() {
		return is_material_returend;
	}
	public void setIs_material_returend(Integer is_material_returend) {
		this.is_material_returend = is_material_returend;
	}
	public int getIs_material_sold() {
		return is_material_sold;
	}
	public void setIs_material_sold(Integer is_material_sold) {
		this.is_material_sold = is_material_sold;
	}
	public int getIs_received() {
		return is_received;
	}
	public void setIs_received(Integer is_received) {
		this.is_received = is_received;
	}
	public int getIs_discount_percent() {
		return is_discount_percent;
	}
	public void setIs_discount_percent(Integer is_discount_percent) {
		this.is_discount_percent = is_discount_percent;
	}
	public double getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(Double gst_amount) {
		this.gst_amount = gst_amount;
	}
	public double getGst_percent() {
		return gst_percent;
	}
	public void setGst_percent(Double gst_percent) {
		this.gst_percent = gst_percent;
	}
	public double getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(Double discount_value) {
		this.discount_value = discount_value;
	}
	public double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public String getMaterial_purchase_id() {
		return material_purchase_id;
	}
	public void setMaterial_purchase_id(String material_purchase_id) {
		this.material_purchase_id = material_purchase_id;
	}
	public String getMaterial_return_id() {
		return material_return_id;
	}
	public void setMaterial_return_id(String material_return_id) {
		this.material_return_id = material_return_id;
	}
	public String getMaterial_sale_id() {
		return material_sale_id;
	}
	public void setMaterial_sale_id(String material_sale_id) {
		this.material_sale_id = material_sale_id;
	}
	public String getMaterial_transfer_id() {
		return material_transfer_id;
	}
	public void setMaterial_transfer_id(String material_transfer_id) {
		this.material_transfer_id = material_transfer_id;
	}
	public int getIs_material_transferred() {
		return is_material_transferred;
	}
	public void setIs_material_transferred(Integer is_material_transferred) {
		this.is_material_transferred = is_material_transferred;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	public String getBilling_activity_id() {
		return billing_activity_id;
	}
	public void setBilling_activity_id(String billing_activity_id) {
		this.billing_activity_id = billing_activity_id;
	}
	public String getBilling_sub_activity_id() {
		return billing_sub_activity_id;
	}
	public void setBilling_sub_activity_id(String billing_sub_activity_id) {
		this.billing_sub_activity_id = billing_sub_activity_id;
	}
	public String getEquipment_stock_id() {
		return equipment_stock_id;
	}
	public void setEquipment_stock_id(String equipment_stock_id) {
		this.equipment_stock_id = equipment_stock_id;
	}
	public String getGrn_id() {
		return grn_id;
	}
	public void setGrn_id(String grn_id) {
		this.grn_id = grn_id;
	}
	public String getPurchase_order_item_id() {
		return purchase_order_item_id;
	}
	public void setPurchase_order_item_id(String purchase_order_item_id) {
		this.purchase_order_item_id = purchase_order_item_id;
	}
	public int getIs_grn() {
		return is_grn;
	}
	public void setIs_grn(Integer is_grn) {
		this.is_grn = is_grn;
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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
	public String getMonkey_patch_party_company_user_name() {
		return monkey_patch_party_company_user_name;
	}
	public void setMonkey_patch_party_company_user_name(String monkey_patch_party_company_user_name) {
		this.monkey_patch_party_company_user_name = monkey_patch_party_company_user_name;
	}
	public MaterialItem getMonkey_patch_materialitem() {
		return monkey_patch_materialitem;
	}
	public void setMonkey_patch_materialitem(MaterialItem monkey_patch_materialitem) {
		this.monkey_patch_materialitem = monkey_patch_materialitem;
	}
	public String getMonkey_patch_project_name() {
		return monkey_patch_project_name;
	}
	public void setMonkey_patch_project_name(String monkey_patch_project_name) {
		this.monkey_patch_project_name = monkey_patch_project_name;
	}
	public String getMonkey_patch_billing_activity_name() {
		return monkey_patch_billing_activity_name;
	}
	public void setMonkey_patch_billing_activity_name(String monkey_patch_billing_activity_name) {
		this.monkey_patch_billing_activity_name = monkey_patch_billing_activity_name;
	}
	public String getMonkey_patch_equipment_name() {
		return monkey_patch_equipment_name;
	}
	public void setMonkey_patch_equipment_name(String monkey_patch_equipment_name) {
		this.monkey_patch_equipment_name = monkey_patch_equipment_name;
	}
	public String getMonkey_patch_equipment_number() {
		return monkey_patch_equipment_number;
	}
	public void setMonkey_patch_equipment_number(String monkey_patch_equipment_number) {
		this.monkey_patch_equipment_number = monkey_patch_equipment_number;
	}
	public Project getMonkey_patch_sender_project() {
		return monkey_patch_sender_project;
	}
	public void setMonkey_patch_sender_project(Project monkey_patch_sender_project) {
		this.monkey_patch_sender_project = monkey_patch_sender_project;
	}
	public Project getMonkey_patch_receiver_project() {
		return monkey_patch_receiver_project;
	}
	public void setMonkey_patch_receiver_project(Project monkey_patch_receiver_project) {
		this.monkey_patch_receiver_project = monkey_patch_receiver_project;
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
	public double getDamage_quantity() {
		return damage_quantity;
	}
	public void setDamage_quantity(Double damage_quantity) {
		this.damage_quantity = damage_quantity;
	}
	public String getWorkorder_id() {
		return workorder_id;
	}
	public void setWorkorder_id(String workorder_id) {
		this.workorder_id = workorder_id;
	}
	public int getIs_material_issued() {
		return is_material_issued;
	}
	public void setIs_material_issued(Integer is_material_issued) {
		this.is_material_issued = is_material_issued;
	}

}
