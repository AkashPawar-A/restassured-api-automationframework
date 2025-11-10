package com.onsite.endpoints;

public class Payroll_Api {
	
	public static final String ADD_PAYROLL = "/add/payroll";
	public static final String EDIT_PAYROLL = "/edit/payroll";
	public static final String DETAIL_PAYROLL = "/detail/payroll/{id}";
	public static final String LIST_PAYROLL = "/list/payroll";
	public static final String PAYROLL_COUNT = "/payroll/count";
	public static final String HIDE_PAYROLL = "/hide/payroll/{id}";

	public static final String SHOW_PAYROLL = "/show/payroll/{id}";
	
	public static final String EDIT_PAYROLL_TYPE = "/edit/payroll/type";
	public static final String EDIT_PAYROLL_ASSOCIATE_PROJECT = "/edit/payroll/associate-project";
	public static final String EDIT_PAYROLL_DEASSOCIATE_PROJECT = "/edit/payroll/dissociate-project";
	public static final String MY_PAYROLL = "/my/payroll/{company_id}";
	public static final String DETAIL_PAYROLL_BYCOMPANYUSER = "/detail/payroll/bycompanyuser";
	public static final String LIST_LABOUR_PAYROLL_FORPUNCH = "/list/labour-payroll/forpunch";
	public static final String DELETE_PAYROLL = "/delete/payroll/{id}";
	public static final String IS_PAYROLL_EXIST = "/is-payroll-exist/{company_user_id}";
	public static final String LIST_PAYROLL_EXCLUDE_PROJECT = "/list/payroll/exclude_project";
	public static final String LIST_COMPANYUSER_FOR_PAYROLL = "/list/companyuser/for-payroll";
	public static final String LIST_COMPANYUSER_FOR_EXIST_PAYROLL = "/list/companyuser/for-exist-payroll/{company_id}";

}
