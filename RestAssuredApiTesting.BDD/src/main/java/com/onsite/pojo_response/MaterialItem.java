package com.onsite.pojo_response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown= true)
public class MaterialItem {
	
	private String id;
	private String name;
	private String company_id;
	private String creator;
	private String creator_company_id;
	private String sub_category_id;
	private String material_sub_category_id;
	private String unit_id;
	private String unit;
	private Integer is_engine;
	private Integer used;
	private Double gst_percent;
	private String notes;
	private Integer delete;
	private Double last_unit_price;
	private Integer hidden;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String search;
	private Integer monkey_patch_sub_category;
	private SubCategory monkey_patch_material_sub_category;
	//private Materialstock monkey_patch_materialstock;
	private String source_add;
	private String source_edit;
	private String hsn_code;
	//private MaterialBreakup[] material_breakup;
	private String type;
	private String item_Code;
	private String material_item_id;
	
	public String getMaterial_item_id() {
		return material_item_id;
	}
	public void setMaterial_item_id(String material_item_id) {
		this.material_item_id = material_item_id;
	}
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
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreator_company_id() {
		return creator_company_id;
	}
	public void setCreator_company_id(String creator_company_id) {
		this.creator_company_id = creator_company_id;
	}
	public String getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	public String getMaterial_sub_category_id() {
		return material_sub_category_id;
	}
	public void setMaterial_sub_category_id(String material_sub_category_id) {
		this.material_sub_category_id = material_sub_category_id;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getIs_engine() {
		return is_engine;
	}
	public void setIs_engine(Integer is_engine) {
		this.is_engine = is_engine;
	}
	public Integer getUsed() {
		return used;
	}
	public void setUsed(Integer used) {
		this.used = used;
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
	public Integer getDelete() {
		return delete;
	}
	public void setDelete(Integer delete) {
		this.delete = delete;
	}
	public Double getLast_unit_price() {
		return last_unit_price;
	}
	public void setLast_unit_price(Double last_unit_price) {
		this.last_unit_price = last_unit_price;
	}
	public Integer getHidden() {
		return hidden;
	}
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
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
	public Integer getMonkey_patch_sub_category() {
		return monkey_patch_sub_category;
	}
	public void setMonkey_patch_sub_category(Integer monkey_patch_sub_category) {
		this.monkey_patch_sub_category = monkey_patch_sub_category;
	}
	public SubCategory getMonkey_patch_material_sub_category() {
		return monkey_patch_material_sub_category;
	}
	public void setMonkey_patch_material_sub_category(SubCategory monkey_patch_material_sub_category) {
		this.monkey_patch_material_sub_category = monkey_patch_material_sub_category;
	}
	public String getSource_add() {
		return source_add;
	}
	public void setSource_add(String source_add) {
		this.source_add = source_add;
	}
	public String getSource_edit() {
		return source_edit;
	}
	public void setSource_edit(String source_edit) {
		this.source_edit = source_edit;
	}
	public String getHsn_code() {
		return hsn_code;
	}
	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(String item_Code) {
		this.item_Code = item_Code;
	}

}
