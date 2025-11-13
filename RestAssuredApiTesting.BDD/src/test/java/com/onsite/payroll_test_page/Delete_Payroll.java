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
		
		String responseMessage = payrollResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("expectes response message :" + responseMessage);
		}else {
			System.out.println("expected response is null");
		}
		
		int responseStatusCode = payrollResponse.getStatusCode();
		System.out.println("response status code :" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
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
