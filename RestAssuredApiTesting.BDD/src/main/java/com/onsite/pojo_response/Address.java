package com.onsite.pojo_response;

import java.time.LocalDateTime;

import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	
	private String id;
	private String company_id;
	private String creator_company_user_id;
	private String owner_id;
	private String address_type;
	private String address_title;
	private String address_gst;
	private String address_line_1;
	private String address_line_2;
	private String city;
	private String state;
	private String postal_code;
	private String country_code;
	//private GeoJson location;
	private Interface[] google_address;
	private Integer primary;
	private Integer delete;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String search;
	//private CountryConfiguration monkey_patch_country_config;
	
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
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
	}
	public String getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public String getAddress_title() {
		return address_title;
	}
	public void setAddress_title(String address_title) {
		this.address_title = address_title;
	}
	public String getAddress_gst() {
		return address_gst;
	}
	public void setAddress_gst(String address_gst) {
		this.address_gst = address_gst;
	}
	public String getAddress_line_1() {
		return address_line_1;
	}
	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}
	public String getAddress_line_2() {
		return address_line_2;
	}
	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
//	public GeoJson getLocation() {
//		return location;
//	}
//	public void setLocation(GeoJson location) {
//		this.location = location;
//	}
	public Interface[] getGoogle_address() {
		return google_address;
	}
	public void setGoogle_address(Interface[] google_address) {
		this.google_address = google_address;
	}
	public Integer getPrimary() {
		return primary;
	}
	public void setPrimary(Integer primary) {
		this.primary = primary;
	}
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
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
//	public CountryConfiguration getMonkey_patch_country_config() {
//		return monkey_patch_country_config;
//	}
//	public void setMonkey_patch_country_config(CountryConfiguration monkey_patch_country_config) {
//		this.monkey_patch_country_config = monkey_patch_country_config;
//	}

}
