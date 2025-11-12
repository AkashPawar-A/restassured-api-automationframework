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
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229773";
		
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
		
		String responseMessage = payrollResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}else {
			System.out.println("response message is null : " + responseMessage);
		}
		
		int responseStatusCode = payrollResponse.getStatusCode();
		System.out.println("response status code :" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
		long responseTime = payrollResponse.getTime();
		System.out.println("Respose Time :" + responseTime);
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		String responseContentType = payrollResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "response content type is missmatch");
		
		String allowsMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowsMethod.contains("GET"), "Get method is not allowed");
		
	}

}
