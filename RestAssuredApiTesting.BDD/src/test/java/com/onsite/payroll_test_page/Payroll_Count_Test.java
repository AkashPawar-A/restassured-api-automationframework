package com.onsite.payroll_test_page;

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
	
	String token = AuthUtils.getToken();
	String companyId = CompanyContext.getCompanyId();
	int count = 0;
	int num = 0;
	
	@Test(priority=1)
	public void labour_ActiveCount() {
		
		Response response_ActiveCount = 
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
		
	    String responseData = response_ActiveCount.getBody().asString();
	    System.out.println(responseData);
		
		int responseStatusCode = response_ActiveCount.getStatusCode();
		System.out.println("expected status code : " + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
		String responseMessage = response_ActiveCount.jsonPath().getString("message");
		if (responseMessage != null) {
		    System.out.println("Response message: " + responseMessage);
		} else {
		    System.out.println("No 'message' key present in response");
		}
		
		String responseContentType = response_ActiveCount.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		String allowMethod = response_ActiveCount.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "alloed metod is missmatch");
		
		long responseTime = response_ActiveCount.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		int labourPayroll_ActiveCount = response_ActiveCount.jsonPath().getInt("count");
		if(labourPayroll_ActiveCount >= 0) {
			System.out.println("total labour active payroll count : " + labourPayroll_ActiveCount);
		}
		
	}
	
	@Test(priority=2)
	public void labour_InactiveCount() {
		
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
	
	@Test(priority=3)
	public void staff_ActiveCount() {
		
		Response response_count = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("hidden", 0)
				.queryParam("type", "staff")
				.log().uri()
				
				.when()
				.get(Payroll_Api.PAYROLL_COUNT)
				
				.then()
				.extract().response();
		
	    String responseData = response_count.getBody().asString();
	    System.out.println(responseData);
		
		int responseStatusCode = response_count.getStatusCode();
		String responseMessage = response_count.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		String responseContentType = response_count.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		String allowMethod = response_count.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "alloed metod is missmatch");
		
		long responseTime = response_count.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		int staffPayroll_ActiveCount = response_count.jsonPath().getInt("count");
		if(staffPayroll_ActiveCount >= 0) {
			System.out.println("total staff active payroll count : " + staffPayroll_ActiveCount);
		}
		
	}
	
	@Test(priority=4)
	public void staff_InactiveCount() {
		
		Response response_count = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("hidden", 1)
				.queryParam("type", "staff")
				.log().uri()
				
				.when()
				.get(Payroll_Api.PAYROLL_COUNT)
				
				.then()
				.extract().response();
		
	    String responseData = response_count.getBody().asString();
	    System.out.println(responseData);
		
		int responseStatusCode = response_count.getStatusCode();
		String responseMessage = response_count.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		String responseContentType = response_count.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "expected contentType i missmatch");
		
		String allowMethod = response_count.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("GET"), "alloed metod is missmatch");
		
		long responseTime = response_count.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		int staffPayroll_IntiveCount = response_count.jsonPath().getInt("count");
		if(staffPayroll_IntiveCount >= 0) {
			System.out.println("total staff inactive payroll count : " + staffPayroll_IntiveCount);
		}
		
	}

}
