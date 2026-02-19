package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Material {
	
	private String id;
	private String material_item_id;
	private String type;
	private String materialstock_id;
	private String remark;
	private String party_name;
	private Integer party_mobile;
	private String party_id;
	private String party_company_user_id;
	private Double quantity;
	private Double unit_price;
	private String receiving_date;
	private Integer index;
	private String[] photos;
	private Integer delete;
	private Double total_payable;
	private Integer is_purchase;
	private Integer is_material_returned;
	private Integer is_material_sold;
	private Integer is_discount_percent;
	private Double gst_amount;
	private Double discount_value;
	private Double discount_amount;
	private Double gst_percent;
	private String billing_activity_id;
	private String billing_sub_activity_id;
	private String equipment_stock_id;
	private String grn_id;
	private String purchase_order_item_id;
	private Double damage_quantity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaterial_item_id() {
		return material_item_id;
	}
	public void setMaterial_item_id(String material_item_id) {
		this.material_item_id = material_item_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaterialstock_id() {
		return materialstock_id;
	}
	public void setMaterialstock_id(String materialstock_id) {
		this.materialstock_id = materialstock_id;
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
	public Integer getParty_mobile() {
		return party_mobile;
	}
	public void setParty_mobile(Integer party_mobile) {
		this.party_mobile = party_mobile;
	}
	public String getParty_id() {
		return party_id;
	}
	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}
	public String getParty_company_user_id() {
		return party_company_user_id;
	}
	public void setParty_company_user_id(String party_company_user_id) {
		this.party_company_user_id = party_company_user_id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}
	public String getReceiving_date() {
		return receiving_date;
	}
	public void setReceiving_date(String receiving_date) {
		this.receiving_date = receiving_date;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	public Double getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(Double total_payable) {
		this.total_payable = total_payable;
	}
	public Integer getIs_purchase() {
		return is_purchase;
	}
	public void setIs_purchase(Integer is_purchase) {
		this.is_purchase = is_purchase;
	}
	public Integer getIs_material_returned() {
		return is_material_returned;
	}
	public void setIs_material_returned(Integer is_material_returned) {
		this.is_material_returned = is_material_returned;
	}
	public Integer getIs_material_sold() {
		return is_material_sold;
	}
	public void setIs_material_sold(Integer is_material_sold) {
		this.is_material_sold = is_material_sold;
	}
	public Integer getIs_discount_percent() {
		return is_discount_percent;
	}
	public void setIs_discount_percent(Integer is_discount_percent) {
		this.is_discount_percent = is_discount_percent;
	}
	public Double getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(Double gst_amount) {
		this.gst_amount = gst_amount;
	}
	public Double getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(Double discount_value) {
		this.discount_value = discount_value;
	}
	public Double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(Double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public Double getGst_percent() {
		return gst_percent;
	}
	public void setGst_percent(Double gst_percent) {
		this.gst_percent = gst_percent;
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
	public Double getDamage_quantity() {
		return damage_quantity;
	}
	public void setDamage_quantity(Double damage_quantity) {
		this.damage_quantity = damage_quantity;
	}

}
