package com.onsite.LabourPayroll_testpage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class Payroll_Count_Test {
	
	private int activeCountBeforeCreate = 0;
	private int activeCountAfterCreate = 0;
	
	String token = AuthUtils.getToken();
	int count = 0;
	int num = 0;
	
	@Test(priority=1)
	public void labourActiveCountBeforeCreate() {
		
		String companyId = CompanyContext.getCompanyId();
		
		Response beforeResponse_ActiveCount = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("hidden", 0)
				.queryParam("type", "labour")
				.log().uri()
				
				.when()
				.get(Payroll_Api.PAYROLL_COUNT)
				
				.then()
				.extract().response();
		
		int responseStatusCode = beforeResponse_ActiveCount.getStatusCode();
		String responseMessage = beforeResponse_ActiveCount.jsonPath().getString("message");
		if (responseStatusCode == 200) {
		    System.out.println("response status code :" + responseStatusCode + "Response message: " + responseMessage);
		} else {
		    System.out.println("failure response status code :" + responseStatusCode + "failure Response message: " + responseMessage);
		}
		
		String responseContentType = beforeResponse_ActiveCount.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		String allowMethod = beforeResponse_ActiveCount.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "alloed metod is missmatch");
		
		long responseTime = beforeResponse_ActiveCount.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		activeCountBeforeCreate = beforeResponse_ActiveCount.jsonPath().getInt("count");
		if(activeCountBeforeCreate >= 0) {
			System.out.println("total labour active payroll count : " + activeCountBeforeCreate);
		}
		
	}
	
	@Test(priority=2)
	public void labourActiveCountAfterCreate() {
		
		String companyId = CompanyContext.getCompanyId();
		
		Response afterResponse_ActiveCount = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("hidden", 0)
				.queryParam("type", "labour")
				.log().uri()
				
				.when()
				.get(Payroll_Api.PAYROLL_COUNT)
				
				.then()
				.extract().response();
		
		int responseStatusCode = afterResponse_ActiveCount.getStatusCode();
		String responseMessage = afterResponse_ActiveCount.jsonPath().getString("message");
		
		if (responseStatusCode == 200) {
		    System.out.println("response status code :" + responseStatusCode + "Response message: " + responseMessage);
		} else {
		    System.out.println("failure response status code :" + responseStatusCode + "failure Response message: " + responseMessage);
		}
		
		String responseContentType = afterResponse_ActiveCount.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		long responseTime = afterResponse_ActiveCount.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		activeCountAfterCreate = afterResponse_ActiveCount.jsonPath().getInt("count");
		
		if(activeCountAfterCreate >= 0) {
			System.out.println("Active Count BEFORE create : " + activeCountBeforeCreate);
			System.out.println("Active Count AFTER create  : " + activeCountAfterCreate);
		}
		
		Assert.assertEquals(activeCountAfterCreate, activeCountBeforeCreate + 1, "Active payroll count did NOT increase after payroll creation");
	}
	
	@Test(priority=3)
	public void labour_InactiveCount() {
		
		String companyId = CompanyContext.getCompanyId();
		
		Response response_InactiveCount = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("hidden", 1)
				.queryParam("type", "labour")
				.log().uri()
				
				.when()
				.get(Payroll_Api.PAYROLL_COUNT)
				
				.then()
				.extract().response();
		
	    String responseData = response_InactiveCount.getBody().asString();
	    System.out.println(responseData);
		
		int responseStatusCode = response_InactiveCount.getStatusCode();
		System.out.println("expected status code : " + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
		String responseMessage = response_InactiveCount.jsonPath().getString("message");
		if (responseMessage != null) {
		    System.out.println("Response message: " + responseMessage);
		} else {
		    System.out.println("No 'message' key present in response");
		}
		
		String responseContentType = response_InactiveCount.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		String allowMethod = response_InactiveCount.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "alloed metod is missmatch");
		
		long responseTime = response_InactiveCount.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		int labourPayroll_InactiveCount = response_InactiveCount.jsonPath().getInt("count");
		if(labourPayroll_InactiveCount >= 0) {
			System.out.println("total labour inactive payroll count : " + labourPayroll_InactiveCount);
		}
	}
	
}
