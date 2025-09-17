package com.onsite.timesheet_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

public class TimeSheetListTest extends BaseToken{

	@Test
	public void listTimeSheet() throws Exception {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		int count = 30;
		int pageNumber = 1;
		boolean morePages = true;

		int totalRecords = 0;
		List<String> recordLogs = new ArrayList<>();

		while (morePages) {
			System.out.println("Validating page: " + pageNumber);

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
					.get(TimeSheet_Api.list_timesheet)

				.then()
					.log().status()
					.extract().response();

			//SCHEMA VALIDATION
			String jsonResponse = response.asString();
			SchemaValidator.validateSchema("schemas_files/timesheetList_schema.json", jsonResponse);

			//STATUS CODE VALIDATION
			int statusCode = response.statusCode();
			Assert.assertEquals(statusCode, 200, "Expected status 200 on page " + pageNumber);

			//RESPONSE STRUCTURE VALIDATION
			List<Map<String, Object>> dataList = response.jsonPath().getList("data");
			Assert.assertNotNull(dataList, "Data list should not be null on page " + pageNumber);

			//EMPTY RESPONSE VALIDATION 
			if(dataList.isEmpty()) {
				System.out.println("Page :" + pageNumber + "returned 0 records");
			}

			//PAGE SIZE VALIDATION
			totalRecords += dataList.size(); // add current page size
			Assert.assertTrue(dataList.size() <= count, "More than expected records returned on page " + pageNumber);

			//RECORD VALIDATIONS
			Set<String> uniqueId = new HashSet<>();
			for (Map<String, Object> item : dataList) {
				String projectId = (String) item.get("project_id");
				String actualCompanyId = (String) item.get("company_id");
				String id = (String) item.get("id");
				String partyCompanyUserId = (String) item.get("party_company_user_id");
				Object deleteFlag = item.get("delete");
				String name = (String) item.get("name");

				//Logging record for debug purpose
				recordLogs.add("Name :" + name +
						", Id :" + id +
						", PartyCompanyUserId :" + partyCompanyUserId + 
						", DeleteFlag :" + deleteFlag +
						", ProjectId :" + projectId + 
						", ActualCompanyId :" + actualCompanyId);

				Assert.assertTrue(uniqueId.add(id), "Duplicate id found" + id); //Duplicate ID validation
				Assert.assertNotNull(projectId, "Project ID should not be null on page " + pageNumber);  //Field non-null validation
				Assert.assertEquals(actualCompanyId, companyId, "Company ID mismatch on page " + pageNumber);  //Company ID match validation
				Assert.assertTrue(deleteFlag.equals(0) || deleteFlag.equals(1)); //Delete flag validation (must be 0 or 1)
			}

			System.out.println("Total records fetched: " + totalRecords);
			System.out.println("Page " + pageNumber + " validated successfully with " + dataList.size() + " records");

			//PAGINATION CHECK & Move to next page
			String nextUrl = response.jsonPath().getString("page.next_url");
			morePages = (nextUrl != null && !nextUrl.isEmpty());
			pageNumber++;
		}

		//PRINT ALL RECORDS
		System.out.println("All timesheet record");
		for(String logs : recordLogs) {
			System.out.println(logs);
		}

		//FINAL STATUS
		System.out.println("All paginated timesheet records validated successfully");
	}
}
