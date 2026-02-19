package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MaterialItem {
	
	private String name;
	private String company_id;
	private String unit;
	private String sub_category_id;
	private String unit_id;
	private Double gst_percent;
	private String notes;
	private String material_sub_category_id;
	private String hsn_code;
	private String item_code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public Double getGst_percent() {
		return gst_percent;
	}
	public void setGst_percent(Double gst_percent) {
		this.gst_percent = gst_percent;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getMaterial_sub_category_id() {
		return material_sub_category_id;
	}
	public void setMaterial_sub_category_id(String material_sub_category_id) {
		this.material_sub_category_id = material_sub_category_id;
	}
	public String getHsn_code() {
		return hsn_code;
	}
	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	
}
