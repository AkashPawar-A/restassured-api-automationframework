package com.onsite.pojo_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubCategory {
	
	private String id;
	private String parent_id;
	private String creator;
	private String creator_company_user_id;
	private String company_id;
	private String type;
	private String text_en;
	private String text_hi;
	private int index;
	private int used;
	private int delete;
	private int hidden;
	private String created;
	private String updated;
	private Object monkey_patch_children;
	private Object monkey_patch_parent;
	private String color_hex;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator_company_user_id() {
		return creator_company_user_id;
	}
	public void setCreator_company_user_id(String creator_company_user_id) {
		this.creator_company_user_id = creator_company_user_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText_en() {
		return text_en;
	}
	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	public String getText_hi() {
		return text_hi;
	}
	public void setText_hi(String text_hi) {
		this.text_hi = text_hi;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public Object getMonkey_patch_children() {
		return monkey_patch_children;
	}
	public void setMonkey_patch_children(Object monkey_patch_children) {
		this.monkey_patch_children = monkey_patch_children;
	}
	public Object getMonkey_patch_parent() {
		return monkey_patch_parent;
	}
	public void setMonkey_patch_parent(Object monkey_patch_parent) {
		this.monkey_patch_parent = monkey_patch_parent;
	}
	public String getColor_hex() {
		return color_hex;
	}
	public void setColor_hex(String color_hex) {
		this.color_hex = color_hex;
	}

}
