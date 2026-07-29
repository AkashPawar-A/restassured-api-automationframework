package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MaterialReturnRequest {
	
	private String remark;
	private String party_company_user_id;
	private String project_id;
	private String sub_category_id;
	private Material[] materials;
	private Double gst_amount;
	private Double other_amount;
	private Double material_amount;
	private Double discount;
	private Double total_payable;
	private String[] photos;
	private String return_date;
	private String vendor_reference_number;
	private Double due_days;
	
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Material[] getMaterials() {
		return materials;
	}
	public void setMaterials(Material[] materials) {
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
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
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

}
