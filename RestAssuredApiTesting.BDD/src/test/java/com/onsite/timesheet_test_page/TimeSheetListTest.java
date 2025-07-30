package com.onsite.timesheet_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

public class TimeSheetListTest extends BaseToken{
	
	@Test
	public void listtimesheet() {
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		
		Response response = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("count", 20)
				.queryParam("page", 1)
				.queryParam("company_id", companyId)
				.log().all()
				
			.when()
				.get(TimeSheet_Api.list_timesheet)
				
			.then()
				.log().all()
				.extract().response();
		
		int statusCode = response.getStatusCode();		
		String message = response.jsonPath().getString("message");
		
		System.out.println("status code :" + statusCode);
		System.out.println("response message :" + message);
		
		Assert.assertEquals(statusCode, 200, "Expected 200 OK status");
		Assert.assertTrue(response.jsonPath().getList("data").size()<=20, "Should return <= 20 records");
		Assert.assertNotNull(response.jsonPath().getString("data[0].project_id"), "Project ID must be present");
	}

}
