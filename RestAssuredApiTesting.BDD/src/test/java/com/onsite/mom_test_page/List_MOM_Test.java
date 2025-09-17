package com.onsite.mom_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.context.MomDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Mom_Api;
import com.onsite.utilities_page.AuthUtils;

public class List_MOM_Test {

	@Test(priority=1)
	public void getlistMomWithPagination() throws Exception {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		int perPage = 30;
		int pageNumber = 1;
		boolean morePages = true;

		Set<String> idsObj = new HashSet<>();
		boolean validMomFound = false;
		int totalRecords = 0;
		List<String> recordLogs = new ArrayList<>();

		while (morePages) {

			Response listMomResponse =
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("page", pageNumber)
					.queryParam("per_page", perPage)
					.log().uri()

					.when()
					.get(Mom_Api.List_MON)

					.then()
					.extract().response();

			//STATUS CODE VALIDATION
			Assert.assertEquals(listMomResponse.statusCode(), 200, "Expected status code not found");

			//RESPONSE TIME VALIDATION
			long responseTime = listMomResponse.getTime();
			Assert.assertTrue(responseTime < 5000, "Response time is too long: " + responseTime);
			
			String contentType = listMomResponse.getHeader("Content-Type");
			System.out.println("contentType : " + contentType);
			Assert.assertTrue(contentType.contains("application/json; charset=utf-8"), "application ka contenttyep is not json");

			//GET TOTAL COUNT
			int totalCount = listMomResponse.jsonPath().getInt("page.count");
			List<Map<String, Object>> momList = listMomResponse.jsonPath().getList("data");

			//PAGE SIZE VALIDATION
			Assert.assertTrue(momList.size() <= perPage, "More than expected records returned on page " + pageNumber);
			totalRecords += momList.size();
			if (momList == null || momList.isEmpty()) {
				morePages = false;
				break;
			}

			//RECORD VALIDATIONS
			for (Map<String, Object> momItem : momList) {

				Object idObj = momItem.get("id");
				if (idObj == null) {
					Assert.fail("MOM id is null in response");
				}

				String momId = idObj.toString();
				if (!idsObj.add(momId)) {
					Assert.fail("Duplicate MOM id found: " + momId);
				}

				try {
					UUID.fromString(momId);
				} catch (Exception e) {
					Assert.fail("Invalid UUID found: " + momId);
				}

				int deleteFlag = Integer.parseInt(momItem.get("delete").toString());
				if (deleteFlag != 0) 
					continue;

				List<String> requiredFields = Arrays.asList(
						"id", "name", "project_id", "company_id", "creator",
						"creator_company_user_id", "attendee_cu_ids",
						"mom_date", "delete", "search", "created", "updated"
						);

				for (String key : requiredFields) {
					if (!momItem.containsKey(key)) {
						Assert.fail("Required key missing: " + key);
					}
					Object value = momItem.get(key);
					if (value == null) {
						Assert.fail("Required key-value is null for: " + key);
					}
					if (value instanceof String && ((String) value).trim().isEmpty()) {
						Assert.fail("Empty string value for key: " + key);
					}
				}
				
				List<String> nonRequiredfield = Arrays.asList("notes", "photos");
				for(String nonkey : nonRequiredfield) {
					if(!momItem.containsKey(nonkey)) {
						Assert.fail("required field is missing :" + nonkey);
					}
					Object value = momItem.get(nonkey);
					if(value==null) {
						continue;
					}
					if(value instanceof String && ((String) value).trim().isEmpty()) {
						continue;
					}
				}

				if (!validMomFound) {
					MomDetails.momId = momId;
					MomDetails.companyId = momItem.get("company_id").toString();
					MomDetails.attendeeCuIds = momItem.get("attendee_cu_ids").toString();
					MomDetails.projectId = momItem.get("project_id").toString();
					MomDetails.creatorId = momItem.get("creator").toString();
					MomDetails.creatorCompanyUserId = momItem.get("creator_company_user_id").toString();
					System.out.println("Valid MOM selected for deletion: " + MomDetails.momId);
					validMomFound = true;
				}

				//Logging for debug
				String logEntry = "Name: " + momItem.get("name") +
						", Id: " + momId +
						", AttendeeId: " + momItem.get("attendee_cu_ids") +
						", ProjectId: " + momItem.get("project_id") +
						", CompanyId: " + momItem.get("company_id") +
						", DeleteFlag: " + deleteFlag + 
						", creatorId: " + momItem.get("creator") + 
						", creator_company_user_Id: " + momItem.get("creator_company_user_id") +
						", Notes: " + momItem.get("notes") +
						", photos: " + momItem.get("photos") + 
						", mom_date: " + momItem.get("mom_date");
				recordLogs.add(logEntry);
			}

			//PAGINATION CHECK using totalPages
			int totalPages = (totalCount + perPage - 1) / perPage;
			if (pageNumber >= totalPages) {
				morePages = false;
			} else {
				pageNumber++;
			}

			System.out.println("Page " + pageNumber + " validated successfully with " + momList.size() + " records");
		}

		//PRINT ALL RECORDS
		System.out.println("All MOM records:");
		for (String logs : recordLogs) {
			System.out.println(logs);
		}

		System.out.println("Total records fetched: " + totalRecords);

		if (!validMomFound) {
			Assert.fail("No valid MOM ID found with delete flag = 0 across all pages");
		} else {
			System.out.println("All paginated MOM records validated successfully.");
		}
	}
}
