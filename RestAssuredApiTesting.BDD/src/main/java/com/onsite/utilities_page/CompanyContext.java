package com.onsite.utilities_page;

public class CompanyContext {
	
	private static String companyId;

    public static void setCompanyId(String id) {
    	System.out.println("SETTING COMPANY ID: " + id);
        companyId = id;
    }

    public static String getCompanyId() {
    	System.out.println("GETTING COMPANY ID: " + companyId);
        return companyId;
    }

}
