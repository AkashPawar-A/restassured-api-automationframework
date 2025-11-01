package com.onsite.pojo_request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SalaryBreakupComponent_Request {
	
	private String id;
    private String name;
    private String relation_type;
    private String relation_value;
    private String amount;
	
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
	public String getRelation_value() {
		return relation_value;
	}
	public void setRelation_value(String relation_value) {
		this.relation_value = relation_value;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
    
}
