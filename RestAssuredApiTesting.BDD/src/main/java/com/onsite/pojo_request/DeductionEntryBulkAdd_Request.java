package com.onsite.pojo_request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DeductionEntryBulkAdd_Request {
	
	private String feature_id;
	private String feature_type;
	private String project_id;
	private List<DeductionEntry_Request> deduction_entry_data;
	
	public List<DeductionEntry_Request> getDeduction_entry_data() {
		return deduction_entry_data;
	}
	public void setDeduction_entry_data(List<DeductionEntry_Request> deduction_entry_data) {
		this.deduction_entry_data = deduction_entry_data;
	}
	public String getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(String feature_id) {
		this.feature_id = feature_id;
	}
	public String getFeature_type() {
		return feature_type;
	}
	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

}
