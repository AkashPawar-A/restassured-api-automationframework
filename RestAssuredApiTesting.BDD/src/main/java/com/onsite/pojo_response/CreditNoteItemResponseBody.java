package com.onsite.pojo_response;

public class CreditNoteItemResponseBody {
	
	private String id;
	private String name;
	private String credit_note_id;
	private String project_id;
	private String creator;
	private String creator_company_user_id;
	private String unit_id;
	private String unit;
	private String unit_price;
	private String quantity;
	private String work_amount;
	private String gst_percent;
	private String gst_amount;
	private String total_amount;
	private String sub_category_id;
	private String hsn_code;
	private String notes;
	private String delete;
	private String created;
	private String updated;

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

	public String getCredit_note_id() {
		return credit_note_id;
	}

	public void setCredit_note_id(String credit_note_id) {
		this.credit_note_id = credit_note_id;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
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

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getWork_amount() {
		return work_amount;
	}

	public void setWork_amount(String work_amount) {
		this.work_amount = work_amount;
	}

	public String getGst_percent() {
		return gst_percent;
	}

	public void setGst_percent(String gst_percent) {
		this.gst_percent = gst_percent;
	}

	public String getGst_amount() {
		return gst_amount;
	}

	public void setGst_amount(String gst_amount) {
		this.gst_amount = gst_amount;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
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

	public MonkeyPatchSubCategory getMonkey_patch_sub_category() {
		return monkey_patch_sub_category;
	}

	public void setMonkey_patch_sub_category(MonkeyPatchSubCategory monkey_patch_sub_category) {
		this.monkey_patch_sub_category = monkey_patch_sub_category;
	}

	private MonkeyPatchSubCategory monkey_patch_sub_category;

	public static class MonkeyPatchSubCategory {
		
		private String id;
		private String parent_id;
		private String creator;
		private String creator_company_user_id;
		private String company_id;
		private String type;
		private String text_en;
		private String text_hi;
		private String index;
		private String hidden;
		private String created;
		private String updated;
		private String monkey_patch_children;
		private String monkey_patch_parent;
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
		public String getIndex() {
			return index;
		}
		public void setIndex(String index) {
			this.index = index;
		}
		public String getHidden() {
			return hidden;
		}
		public void setHidden(String hidden) {
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
		public String getMonkey_patch_children() {
			return monkey_patch_children;
		}
		public void setMonkey_patch_children(String monkey_patch_children) {
			this.monkey_patch_children = monkey_patch_children;
		}
		public String getMonkey_patch_parent() {
			return monkey_patch_parent;
		}
		public void setMonkey_patch_parent(String monkey_patch_parent) {
			this.monkey_patch_parent = monkey_patch_parent;
		}
		public String getColor_hex() {
			return color_hex;
		}
		public void setColor_hex(String color_hex) {
			this.color_hex = color_hex;
		}

	}

}
