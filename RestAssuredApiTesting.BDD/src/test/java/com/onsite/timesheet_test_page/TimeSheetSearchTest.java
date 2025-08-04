package com.onsite.timesheet_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

public class TimeSheetSearchTest extends BaseToken{

	@Test
	public void searchTimeSheet() throws Exception {

		String search="Aish"; //partyCompanyUserId or projectId
		String companyId="75916659-9cbe-4ca7-812e-181a29229772";

		Response response =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("search", search)
				.queryParam("company_id", companyId)
				.log().uri()

				.when()
				.get(TimeSheet_Api.search_timesheet)

				.then()
				.log().all()
				.extract().response();

		//status coed validate 
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Expected status Code 200");

		//Schema validate 
		String jsonResponse = response.asString();
		SchemaValidator.validateSchema("schemas_files/timesheetList_schema.json", jsonResponse);

		//dataList validation
		List<Map<String, Object>> dataList = response.jsonPath().getList("data");
		Assert.assertNotNull(dataList, "data list is not null");

		//check each record
		Set<String> uniqueIds = new HashSet<>();
		for(Map<String, Object> item : dataList) {
			String id = (String) item.get("id");
			String actualCompany_id = (String) item.get("company_id");
			String partyCompanyUserId = (String) item.get("party_company_user_id");
			String project_id = (String) item.get("project_id");
			String searchText = (String) item.get("search");

			//id is not null and unique validation
			Assert.assertNotNull(id, "id should not be null");
			Assert.assertTrue(uniqueIds.add(id), "Duplicate id found :" + id);

			//company id match
			Assert.assertEquals(companyId, actualCompany_id, "company id mismatch");

			//Search field validation
			Assert.assertTrue(
					searchText.toLowerCase().contains(search.toLowerCase()), 
					"Search text not matched in project_id or partyCompanyUserId");

			//handle no record case
			if(dataList.isEmpty()) {
				System.out.println("No records found :" + search);
			}else {
				System.out.println("search return data count:" + dataList.size() + " timesheet list : " + search);
			}
		}
	}
}
