package com.onsite.pojo_request;

import java.time.LocalDateTime;

public class MaterialStock_Request {
	
	private String project_id;
	private String material_item_id;
	private Double opening_stock;
	private String[] material_item_ids;
	private Double estimated_quatity;
	private String opening_stock_date;
	private String id;
	private Double quantity;
	private Double budget_unit_rate;
	
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
	public Double getOpening_stock() {
		return opening_stock;
	}
	public void setOpening_stock(Double opening_stock) {
		this.opening_stock = opening_stock;
	}
	public String[] getMaterial_item_ids() {
		return material_item_ids;
	}
	public void setMaterial_item_ids(String[] material_item_ids) {
		this.material_item_ids = material_item_ids;
	}
	public Double getEstimated_quatity() {
		return estimated_quatity;
	}
	public void setEstimated_quatity(Double estimated_quatity) {
		this.estimated_quatity = estimated_quatity;
	}
	public String getOpening_stock_date() {
		return opening_stock_date;
	}
	public void setOpening_stock_date(String opening_stock_date) {
		this.opening_stock_date = opening_stock_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getBudget_unit_rate() {
		return budget_unit_rate;
	}
	public void setBudget_unit_rate(Double budget_unit_rate) {
		this.budget_unit_rate = budget_unit_rate;
	}
}
