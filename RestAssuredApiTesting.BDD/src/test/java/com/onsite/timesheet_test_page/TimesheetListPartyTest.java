package com.onsite.timesheet_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.SchemaValidator;

public class TimesheetListPartyTest {
	
	@Test
	public void timesheetListParty() throws Exception {
		
		final String schemaPath = "schemas_files/timesheetListParty_schema.json";
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		int count = 30;
		int pageNumber = 1;
		boolean morePage = true;
		
		int totalRecords = 0;
		
		while(morePage) {
			System.out.println("vilidate page :" + pageNumber);
			
			Response response = 
				given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("count", count)
					.queryParam("page", pageNumber)
					.queryParam("company_id", companyId)
					.log().uri()
					
				.when()
					.get(TimeSheet_Api.list_timesheet_party)
					
				.then()
					.log().all()
					.extract().response();
			
			String jsonResponse = response.asString();
			SchemaValidator.validateSchema(schemaPath, jsonResponse);
			
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200, "Expected status code 200 on page :" + pageNumber);
			
			List<Map<String, Object>> partyList = response.jsonPath().getList("$");
			Assert.assertNotNull(partyList, "list should not be null :" + pageNumber);
			
			totalRecords += partyList.size();
			
			Assert.assertTrue(partyList.size() <= count, "more expected record number on page :" + pageNumber);
			
			for(Map<String, Object> item : partyList) {
				String userId = (String) item.get("user_id");
				String actualCompanyId = (String) item.get("company_id");
				
				Assert.assertNotNull(userId, "userId is not null or empty :" + pageNumber);
				Assert.assertEquals(companyId, actualCompanyId, "CompanyId is missmatch on page :" + pageNumber);
			}
			System.out.println("Page " + pageNumber + "validated successfully with " + partyList.size() + " recoreds");
			System.out.println("Total records fetched: " + totalRecords);
			
			morePage = (partyList.size() == count);
			pageNumber++;
		}
		System.out.println("All pagination party list record validate successfully");
	}
}
