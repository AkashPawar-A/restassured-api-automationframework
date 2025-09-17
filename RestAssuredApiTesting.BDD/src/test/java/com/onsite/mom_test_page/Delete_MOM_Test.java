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

public class Delete_MOM_Test {

	@Test(priority=1)
	public void getValidMoMId() {

		String companyId ="75916659-9cbe-4ca7-812e-181a29229772";

		Response momResponseList =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.log().uri()

				.when()
				.get(Mom_Api.List_MON)

				.then()
				.extract().response();

		Assert.assertEquals(momResponseList.getStatusCode(), 200, "expected status code is not found in response" + momResponseList);

		long responseTime = momResponseList.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long" + responseTime);

		List<Map<String, Object>> idsList = momResponseList.jsonPath().getList("data");
		if(idsList==null || idsList.isEmpty()) {
			Assert.fail("mom list is empty or null in response" + idsList);
		}

		Set<String> idObj  = new HashSet<>();
		for(Map<String, Object> momId : idsList) {

			Object momObj = momId.get("id");
			if(momObj==null) {
				Assert.fail("momObj id is empty :" + momId);
			}

			String momIds = momObj.toString();
			if(!idObj.add(momIds)) {
				Assert.fail("mom id is duplicate in mom list" + idObj);
			}

			try {
				UUID.fromString(momIds);
			}catch(Exception e) {
				Assert.fail("mom is is not string format in response" + momIds);
			}
		}

		List<Map<String, Object>> ids = momResponseList.jsonPath().getList("data");
		List<String> selectedIds = new ArrayList<>();

		for (Map<String, Object> selectedId : idsList) {
			int momDeleteFlag = Integer.parseInt(selectedId.get("delete").toString());

			if (momDeleteFlag != 0) {
				continue;
			}

			MomDetails.momId = selectedId.get("id").toString();
			MomDetails.projectId = selectedId.get("project_id").toString();
			MomDetails.companyId = selectedId.get("company_id").toString();
			MomDetails.attendeeCuIds = selectedId.get("attendee_cu_ids").toString();
			MomDetails.userId = selectedId.get("creator_company_user_id").toString();

			String momName = selectedId.get("name").toString();
			String momNotes = selectedId.get("notes").toString();
			String momPhoto = selectedId.get("photos").toString();
			String momDate = selectedId.get("mom_date").toString();
			String momSearch = selectedId.get("search").toString();

			List<String> reqField = Arrays.asList(
					"id", "name", "project_id", "company_id", "creator", 
					"creator_company_user_id", "attendee_cu_ids", "notes",
					"photos", "mom_date", "search", "delete"
					);

			for (String key : reqField) {
				if (!selectedId.containsKey(key)) {
					Assert.fail("Required field missing: " + key);
				}

				Object value = selectedId.get(key);
				if (value == null) {
					Assert.fail("Required value is null for field: " + key);
				}

				if (value instanceof String && ((String) value).trim().isEmpty()) {
					Assert.fail("Mandatory field is empty in response: " + key);
				}
			}

			System.out.println("Valid MOM selected for deletion: " + MomDetails.momId);
			return;
		}

		Assert.fail("No valid MOM ID found with delete flag = 0");
	}


	@Test(priority=2)
	public void deleteValidMoMId() {
		
		if(MomDetails.momId==null || MomDetails.momId.isEmpty()) {
			throw new IllegalArgumentException("mom id is null or empty");
		}

		Response momDeleteResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", MomDetails.momId)
				.log().uri()

				.when()
				.delete(Mom_Api.Delete_MOM)

				.then()
				.extract().response();

		Assert.assertEquals(momDeleteResponse.getStatusCode(), 200, "expected status code is not found in response" + momDeleteResponse);

		long responseTime = momDeleteResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long" + responseTime);
	}
}
