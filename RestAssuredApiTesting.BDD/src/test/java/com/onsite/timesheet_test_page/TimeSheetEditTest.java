package com.onsite.timesheet_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.TimeSheetDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.payloadbuilder.TimeSheetEditPayload;
import com.onsite.pojo_request.TimeSheetEditRequest;
import com.onsite.pojo_response.TimeSheetResponseBody;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TimeSheetEditTest extends BaseToken{

	@Test
	public void editTimeSheet() throws Exception{

		if(TimeSheetDetails.timesheet_id == null || TimeSheetDetails.timesheet_id.isEmpty()) {
			throw new IllegalStateException("timesheet id is null");
		}
		
		TimeSheetEditRequest editObj = TimeSheetEditPayload.builedtimeSheetEditRequest();
		
		ObjectMapper mapper= new ObjectMapper();
		String jsonEditPayload = mapper.writeValueAsString(editObj);

		TimeSheetResponseBody response = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.body(editObj)
				.log().all()
			.when()
			    .patch(TimeSheet_Api.edit_timesheet)
			.then()
			    .statusCode(200)
			    .log().all()
			    .extract().as(TimeSheetResponseBody.class);
	}		
	
	public void getTimeSheet() {
		
		TimeSheetResponseBody response = 
				given()
				    .baseUri(ApiBasePath.BASE_URL)
				    .header("Authorization", "Bearer " + token)
				    .contentType(ContentType.JSON)
				    .log().all()
				    
				.when()
				    .get(TimeSheet_Api.details_timeSheet)
				    
				.then() 
				    .statusCode(200)
				    .log().all()
				    .extract().as(TimeSheetResponseBody.class);
	}
}  

