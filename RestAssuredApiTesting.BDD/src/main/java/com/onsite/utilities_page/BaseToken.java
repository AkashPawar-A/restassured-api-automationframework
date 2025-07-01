package com.onsite.utilities_page;

import org.testng.annotations.BeforeSuite;

import com.onsite.base_page.Base_Page;

public class BaseToken extends Base_Page{

	public static String token;

	@BeforeSuite
	public void generateTokenForSuite() {
		token = AuthUtils.getToken();
		if (token == null) {
			throw new RuntimeException("Token generation failed");
		}
		System.out.println("Generated Token (BeforeSuite): " + token);
	}
}

