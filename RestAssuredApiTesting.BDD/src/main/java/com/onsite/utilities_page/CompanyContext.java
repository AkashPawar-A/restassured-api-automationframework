package com.onsite.utilities_page;

public class CompanyContext {
	
	private static String companyId;

    public static void setCompanyId(String id) {
        companyId = id;
    }

    public static String getCompanyId() {
        return companyId;
    }

}
