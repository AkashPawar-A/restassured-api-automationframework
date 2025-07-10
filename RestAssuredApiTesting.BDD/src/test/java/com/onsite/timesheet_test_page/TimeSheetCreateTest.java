package com.onsite.timesheet_test_page;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.TimeSheetDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.payloadbuilder.TimeSheetCreatePayload;
import com.onsite.pojo_request.TimeSheetCreateRequest;
import com.onsite.pojo_response.TimeSheetResponseBody;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

import com.onsite.utilities_page.ApiNegativeValidator;
import com.onsite.utilities_page.BaseToken;

public class TimeSheetCreateTest extends BaseToken {
	
	@Test(priority=1)
	public void postTimesheet() throws Exception {
		
	    TimeSheetCreateRequest payload = TimeSheetCreatePayload.buildtimeSheetCreatePayload();

	    Response response = given()
	        .baseUri(ApiBasePath.BASE_URL)
	        .header("Authorization", "Bearer " + token)
	        .contentType(ContentType.JSON)
	        .body(payload)
	        .log().all()
	    .when()
	        .post(TimeSheet_Api.create_timeSheet)
	    .then()
	        .statusCode(200)
	        .body("company_id", equalTo("75916659-9cbe-4ca7-812e-181a29229772"))
	        .body("duration", equalTo(3600))
	        .body("end_time", equalTo("2025-07-06T08:32:39.077Z"))
	        .body("notes", equalTo("testing frist"))
	        .body("party_company_user_id", equalTo("8f1e9ece-d44c-4ca2-ae3f-986267a8e567"))
	        .body("project_id", equalTo("e3266525-81ad-47e5-9796-d7ffea6568ff"))
	        .body("start_time", equalTo("2025-07-06T07:32:39.077Z"))
	        .body("timesheet_date", equalTo("2025-07-05T18:30:00.001Z"))
	        .log().all()
	        .extract().response();

	    TimeSheetDetails.company_id = response.jsonPath().getString("company_id");
	    TimeSheetDetails.party_company_user_id = response.jsonPath().getString("party_company_user_id");
	    TimeSheetDetails.project_id = response.jsonPath().getString("project_id");
	    TimeSheetDetails.timesheet_id = response.jsonPath().getString("id");
	    TimeSheetDetails.creator_company_user_id = response.jsonPath().getString("creator_company_user_id");
	}
	
	@Test(priority=2, dependsOnMethods="postTimesheet")
	public void getTimeSheet() {
		
		TimeSheetResponseBody timeSheetObj =
				
		given()
		    .baseUri(ApiBasePath.BASE_URL)
			.header("Authorization", "Bearer " + token)
			.contentType(ContentType.JSON)
			.pathParam("timesheet_id", TimeSheetDetails.timesheet_id)
		.when()
			.get(TimeSheet_Api.details_timeSheet)
		.then()
			.statusCode(200)
			.log().all()
			.extract().as(TimeSheetResponseBody.class);	
		
		Assert.assertEquals(timeSheetObj.getCompany_id(), "75916659-9cbe-4ca7-812e-181a29229772");
		Assert.assertEquals(timeSheetObj.getDuration(), 3600);
		Assert.assertEquals(timeSheetObj.getEnd_time(), "2025-07-06T08:32:39.077Z");
		Assert.assertEquals(timeSheetObj.getNotes(), "testing frist");
		Assert.assertEquals(timeSheetObj.getParty_company_user_id(), "8f1e9ece-d44c-4ca2-ae3f-986267a8e567");
		Assert.assertEquals(timeSheetObj.getProject_id(), "e3266525-81ad-47e5-9796-d7ffea6568ff");
		Assert.assertTrue(timeSheetObj.getPhotos().isEmpty());
		Assert.assertEquals(timeSheetObj.getStart_time(), "2025-07-06T07:32:39.077Z");
		Assert.assertEquals(timeSheetObj.getTimesheet_date(), "2025-07-05T18:30:00.001Z");
	}
}
