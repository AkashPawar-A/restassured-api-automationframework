package com.onsite.pojo_request;

public class Salary_BreakupRequest {
	
	private String id;
	private String type;
	private String company_user_id;
	private String payroll_id;
	private Double basic_salary;
	private Double gross_salary;
	private SalaryTemplateComponant[] allowances;
	private SalaryTemplateComponant[] deductions;
	private Double[] dayoff;
	private Double shift_hour;
	private Double overtime_amount;
	private String sub_category_id;
	private Double net_salary;
	private SalaryTemplateComponant basic;
	private SalaryTemplateComponant fixed_allowance;
	private Double ctc_amount;
	private String template_id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany_user_id() {
		return company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}

	public String getPayroll_id() {
		return payroll_id;
	}

	public void setPayroll_id(String payroll_id) {
		this.payroll_id = payroll_id;
	}

	public Double getBasic_salary() {
		return basic_salary;
	}

	public void setBasic_salary(Double basic_salary) {
		this.basic_salary = basic_salary;
	}

	public Double getGross_salary() {
		return gross_salary;
	}

	public void setGross_salary(Double gross_salary) {
		this.gross_salary = gross_salary;
	}

	public SalaryTemplateComponant[] getAllowances() {
		return allowances;
	}

	public void setAllowances(SalaryTemplateComponant[] allowances) {
		this.allowances = allowances;
	}

	public SalaryTemplateComponant[] getDeductions() {
		return deductions;
	}

	public void setDeductions(SalaryTemplateComponant[] deductions) {
		this.deductions = deductions;
	}

	public Double[] getDayoff() {
		return dayoff;
	}

	public void setDayoff(Double[] dayoff) {
		this.dayoff = dayoff;
	}

	public Double getShift_hour() {
		return shift_hour;
	}

	public void setShift_hour(Double shift_hour) {
		this.shift_hour = shift_hour;
	}

	public Double getOvertime_amount() {
		return overtime_amount;
	}

	public void setOvertime_amount(Double overtime_amount) {
		this.overtime_amount = overtime_amount;
	}

	public String getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(String sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public Double getNet_salary() {
		return net_salary;
	}

	public void setNet_salary(Double net_salary) {
		this.net_salary = net_salary;
	}

	public SalaryTemplateComponant getBasic() {
		return basic;
	}

	public void setBasic(SalaryTemplateComponant basic) {
		this.basic = basic;
	}

	public SalaryTemplateComponant getFixed_allowance() {
		return fixed_allowance;
	}

	public void setFixed_allowance(SalaryTemplateComponant fixed_allowance) {
		this.fixed_allowance = fixed_allowance;
	}

	public Double getCtc_amount() {
		return ctc_amount;
	}

	public void setCtc_amount(Double ctc_amount) {
		this.ctc_amount = ctc_amount;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public static class SalaryTemplateComponant {
		
		private String company_id;
		private String name;
		private Double ctc_amount;
		private Double gross_amount;
		private Double net_amount;
		private Double[] dayoff;
		private String type;
		private SalaryTemplateComponant basic;
		private SalaryTemplateComponant[] allowance;
		private SalaryTemplateComponant fixed_allowance;
		private SalaryTemplateComponant[] deductions;
		private String notes;
		private int is_active;
		
		public String getCompany_id() {
			return company_id;
		}
		public void setCompany_id(String company_id) {
			this.company_id = company_id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getCtc_amount() {
			return ctc_amount;
		}
		public void setCtc_amount(Double ctc_amount) {
			this.ctc_amount = ctc_amount;
		}
		public Double getGross_amount() {
			return gross_amount;
		}
		public void setGross_amount(Double gross_amount) {
			this.gross_amount = gross_amount;
		}
		public Double getNet_amount() {
			return net_amount;
		}
		public void setNet_amount(Double net_amount) {
			this.net_amount = net_amount;
		}
		public Double[] getDayoff() {
			return dayoff;
		}
		public void setDayoff(Double[] dayoff) {
			this.dayoff = dayoff;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public SalaryTemplateComponant getBasic() {
			return basic;
		}
		public void setBasic(SalaryTemplateComponant basic) {
			this.basic = basic;
		}
		public SalaryTemplateComponant[] getAllowance() {
			return allowance;
		}
		public void setAllowance(SalaryTemplateComponant[] allowance) {
			this.allowance = allowance;
		}
		public SalaryTemplateComponant getFixed_allowance() {
			return fixed_allowance;
		}
		public void setFixed_allowance(SalaryTemplateComponant fixed_allowance) {
			this.fixed_allowance = fixed_allowance;
		}
		public SalaryTemplateComponant[] getDeductions() {
			return deductions;
		}
		public void setDeductions(SalaryTemplateComponant[] deductions) {
			this.deductions = deductions;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public int getIs_active() {
			return is_active;
		}
		public void setIs_active(int is_active) {
			this.is_active = is_active;
		}
	}
}
