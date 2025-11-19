package com.onsite.payroll_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

public class Delete_Payroll {
	
	@Test(priority=1)
	public void deletePayroll() {
		
		String payrollId = "429b3e36-b0e2-49da-9f84-bb40e8af8f89";
		
		Response payrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", payrollId)
				.log().uri()
				
				.when()
				.delete(Payroll_Api.DELETE_PAYROLL)
				
				.then()
				.log().all()
				.extract().response();
		
		String deleteResponse = payrollResponse.getBody().asString();
		
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
		System.out.println("response Time :" + responseTime);
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		String responseCpntentType = payrollResponse.getContentType();
		Assert.assertTrue(responseCpntentType.contains("application/json"), "content type is missmatch");
				
		String responseHeader = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(responseHeader.contains("DELETE"), "delete method is not allowed");
		
		String payroll_id = payrollResponse.jsonPath().get("id");
		String payroll_type = payrollResponse.jsonPath().get("type");
		String payroll_name = payrollResponse.jsonPath().get("monkey_patch_party_company_user.name");
		String payroll_user_type= payrollResponse.jsonPath().get("monkey_patch_party_company_user.type");
		Integer payrollDeleteFlag = payrollResponse.jsonPath().get("delete");
		
		if(payrollDeleteFlag != null && payrollDeleteFlag == 1) {
			System.out.println(payroll_name + " : payroll is successfully deleted and deleteFlag : " 
		+ payrollDeleteFlag);
		}else {
			System.out.println("payroll is unsuccessfully deleted");
		}
	}

}
