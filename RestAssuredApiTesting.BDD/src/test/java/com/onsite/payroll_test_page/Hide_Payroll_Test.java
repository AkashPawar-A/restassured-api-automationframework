package com.onsite.payroll_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

public class Hide_Payroll_Test {
	
	String partyId = "85685124-6dac-4091-8cb7-d43721bfb5fc";
	
	@Test(priority=1)
	public void hiddenPayroll() {
		
		Response hiddenPartyResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", partyId)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.HIDE_PAYROLL)
				
				.then()
				.log().all()
				.extract().response();
		
		String partyResponse = hiddenPartyResponse.getBody().asString();
		
		int responseStatusCode = hiddenPartyResponse.getStatusCode();
		System.out.println("response status code :" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
		String responseMessage = hiddenPartyResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message : " + responseMessage);
		}else {
			System.out.println("No message present in response");
		}
		
		String responseConentType = hiddenPartyResponse.getContentType();
		Assert.assertTrue(responseConentType.contains("application/json"));
		
		String allowedMethod = hiddenPartyResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowedMethod.contains("PATCH"), "method is not allowed in response");
		
		String partyId = hiddenPartyResponse.jsonPath().get("id");
		String partyName = hiddenPartyResponse.jsonPath().get("monkey_patch_party_company_user.name");
		
		int hiddenFlag = hiddenPartyResponse.jsonPath().get("hidden");
		if(hiddenFlag != 0) {
			System.out.println("party id : " + partyId + ": party name : " + partyName 
					+ ": hidden flag :" + hiddenFlag);
		}else {
			System.out.println("hidden flag is 0 : " + partyName);
		}
				
	}

}
