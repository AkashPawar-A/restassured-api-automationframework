package com.onsite.pojo_request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MaterialPurchaseRequest {
	
	private String id;  //binding-required
	private String sub_category_id;
	private String remark;
	private String party_company_user_id;
	private List<Material> materials;
	private Double gst_amount;
	private Double other_amount;
	private Double material_amount;
	private Double discount;
	private Double total_payable; //binding-required
	private List<String> photos;
	private String purchase_date;  //binding-requried
	private String vendor_reference_number;
	private Double due_days;
	private String ship_to_address_id;
	private String bill_to_address_id;
	private String ship_from_address_id;
	private String bill_from_address_id;
	private String project_id;
	private Double net_amount;
	private Integer is_roundoff;
	private String other_amount_text;
	private Double other_amount_gst_percentage;
	private Double other_amount_gst_amount; 
	
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public Double getNet_amount() {
		return net_amount;
	}
	public void setNet_amount(Double net_amount) {
		this.net_amount = net_amount;
	}
	public Integer getIs_roundoff() {
		return is_roundoff;
	}
	public void setIs_roundoff(Integer is_roundoff) {
		this.is_roundoff = is_roundoff;
	}
	public String getOther_amount_text() {
		return other_amount_text;
	}
	public void setOther_amount_text(String other_amount_text) {
		this.other_amount_text = other_amount_text;
	}
	public Double getOther_amount_gst_percentage() {
		return other_amount_gst_percentage;
	}
	public void setOther_amount_gst_percentage(Double other_amount_gst_percentage) {
		this.other_amount_gst_percentage = other_amount_gst_percentage;
	}
	public Double getOther_amount_gst_amount() {
		return other_amount_gst_amount;
	}
	public void setOther_amount_gst_amount(Double other_amount_gst_amount) {
		this.other_amount_gst_amount = other_amount_gst_amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
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
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
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
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getTotal_payable() {
		return total_payable;
	}
	public void setTotal_payable(Double total_payable) {
		this.total_payable = total_payable;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getVendor_reference_number() {
		return vendor_reference_number;
	}
	public void setVendor_reference_number(String vendor_reference_number) {
		this.vendor_reference_number = vendor_reference_number;
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

}
