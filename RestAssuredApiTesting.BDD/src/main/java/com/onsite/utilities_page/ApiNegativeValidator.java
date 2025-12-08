package com.onsite.utilities_page;

import io.restassured.response.Response;

import org.testng.Assert;

public class ApiNegativeValidator {

	public static void validateResponse(Response resp,int expectedStatus,String expectedMsgSubString) {

		int actualStatus = resp.getStatusCode();
		String actualMsg  = "";

		try {
			actualMsg = resp.jsonPath().getString("message");
		} catch (Exception e) {
			actualMsg = resp.getBody().asString();
		}

		System.out.println("Status  : " + actualStatus);
		System.out.println("Message : " + actualMsg);

		if (actualStatus == expectedStatus &&
				actualMsg   != null &&
				actualMsg.contains(expectedMsgSubString)) {

			System.out.println("Negative case passed");
			Assert.assertTrue(true);

		} else {
			System.out.println("Negative case failed");
			System.out.println("Expected status : " + expectedStatus);
			System.out.println("Expected msg    : " + expectedMsgSubString);

			Assert.fail("Status/message mismatch");
		}
	}

}
