package com.onsite.payroll_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

public class My_Payroll_Test {
	
	@Test(priority=1)
	public void myPayroll() {
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		
		Response payrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("company_id", companyId)
				.log().uri()
				
				.when()
				.get(Payroll_Api.MY_PAYROLL)
				
				.then()
				.log().all()
				.extract().response();
		
		String payroll_res = payrollResponse.getBody().asString();
		
		int responseStatusCode = payrollResponse.getStatusCode();
		String responseMessage = payrollResponse.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		long responseTime = payrollResponse.getTime();
		System.out.println("Respose Time :" + responseTime);
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		String responseContentType = payrollResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "response content type is missmatch");
		
		String allowsMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowsMethod.contains("GET"), "Get method is not allowed");
		
	}

}
