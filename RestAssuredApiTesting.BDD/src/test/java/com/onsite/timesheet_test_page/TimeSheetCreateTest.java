package com.onsite.timesheet_test_page;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.payloadbuilder.TimeSheetCreatePayload;
import com.onsite.pojo_request.TimeSheetCreateRequest;
import com.onsite.pojo_response.TimeSheetResponseBody;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import com.onsite.utilities_page.BaseToken;

public class TimeSheetCreateTest extends BaseToken {
	
	@Test(priority=1)
	public void timesheet() throws Exception {
		
		TimeSheetCreateRequest payload = TimeSheetCreatePayload.buildtimeSheetCreatePayload();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(payload);
		System.out.println("Final json payload :" + jsonPayload);
		
		Response response = 
				given()
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
				  .body("duration", equalTo("20"))
				  .body("end_time", equalTo("2025-07-04T12:51:27.206Z"))
				  .body("notes", equalTo("party_company_user_id"))
				  .body("project_id", equalTo("5ff6dd18-061c-43cc-9847-79cff6ee1222"))
				  .body("start_time", equalTo("2025-07-03T11:51:27.206Z"))
				  .body("timesheet_date", equalTo("2025-07-02T18:30:00.001Z"))
				  .extract().response();
	}
}
