package com.onsite.pojo_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalaryTemplateComponant {
	
	private String id; //allowance
	private String name;  ///basic
	private String relation_type; // ctc & fixed
	private Float relation_value; // % & rupees
	private Float amount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}
	public Float getRelation_value() {
		return relation_value;
	}
	public void setRelation_value(Float relation_value) {
		this.relation_value = relation_value;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
}
