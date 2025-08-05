package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditNoteAddItemRequest {
	
	private String credit_note_id;
	private double estimated_quantity;
	private double gst_amount;
	private double gst_percent;
	private String hsn_code;
	private String name;
	private String notes;
	private double quantity;
	private String sub_category_id;
	private double total_amount;
	private String unit;
	private String unit_id;
	private double unit_price;
	private double work_amount;
	private int is_old;
	
	public int getIs_old() {
		return is_old;
	}
	public void setIs_old(int is_old) {
		this.is_old = is_old;
	}
	public String getCredit_note_id() {
		return credit_note_id;
	}
	public void setCredit_note_id(String credit_note_id) {
		this.credit_note_id = credit_note_id;
	}
	public double getEstimated_quantity() {
		return estimated_quantity;
	}
	public void setEstimated_quantity(double estimated_quantity) {
		this.estimated_quantity = estimated_quantity;
	}
	public double getGst_amount() {
		return gst_amount;
	}
	public void setGst_amount(double gst_amount) {
		this.gst_amount = gst_amount;
	}
	public double getGst_percent() {
		return gst_percent;
	}
	public void setGst_percent(double gst_percent) {
		this.gst_percent = gst_percent;
	}
	public String getHsn_code() {
		return hsn_code;
	}
	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public double getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}
	public double getWork_amount() {
		return work_amount;
	}
	public void setWork_amount(double work_amount) {
		this.work_amount = work_amount;
	}

}
