package com.onsite.payroll_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

public class Is_Payroll_Exist_Test {
	
	@Test(priority=1)
	public void existPayroll() {
		
		String companyUserId = "8f1e9ece-d44c-4ca2-ae3f-986267a8e567";
		
		Response payrollResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("creator_company_user_id", companyUserId)
				.log().uri()
				
				.when()
				.get(Payroll_Api.IS_PAYROLL_EXIST)
				
				.then()
				.log().all()
				.extract().response();
		
		String companyUserResponse = payrollResponse.getBody().asString();
		
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
		System.out.println("response time : " + responseTime);
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		String responseContentType = payrollResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "content typw is missmatch");
		
		String allowMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "get method is not allowed");
		
		Integer payrollExistFlag = payrollResponse.jsonPath().get("is_exist");
		Integer payrollDeleteFlag = payrollResponse.jsonPath().get("delete");
		Integer companyUserHiddenFlag = payrollResponse.jsonPath().get("payroll.monkey_patch_party_company_user.hidden");
		String companyUserName = payrollResponse.jsonPath().get("payroll.monkey_patch_party_company_user.name");
		
		if(payrollExistFlag != 1 && payrollDeleteFlag != 0 
				&& companyUserHiddenFlag != 0) {
			System.out.println("payroll party : " + companyUserName + ": payroll exist flag is :" + payrollExistFlag);
		}else {
			System.out.println("payroll party : " + companyUserName + ": payroll exist flag is :" + payrollExistFlag);
		}
	}

}
