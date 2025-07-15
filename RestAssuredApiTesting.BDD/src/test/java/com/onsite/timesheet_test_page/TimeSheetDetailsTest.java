package com.onsite.timesheet_test_page;

import com.onsite.context.TimeSheetDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.pojo_response.TimeSheetResponseBody;
import com.onsite.utilities_page.BaseToken;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class TimeSheetDetailsTest extends BaseToken {

	@Test
	public void getTimeSheet() {

		if(TimeSheetDetails.timesheet_id == null || TimeSheetDetails.timesheet_id.isEmpty()) {
			throw new IllegalStateException("timesheet id is null");
		}

		TimeSheetResponseBody response = 
				
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.pathParam("timesheet_id", TimeSheetDetails.timesheet_id)
		   .when()
				.get(TimeSheet_Api.details_timeSheet)
		   .then()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.log().all()
				.extract().as(TimeSheetResponseBody.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getId(), TimeSheetDetails.timesheet_id);
		Assert.assertEquals(response.getCompany_id(), TimeSheetDetails.company_id);
		Assert.assertEquals(response.getParty_company_user_id(), TimeSheetDetails.party_company_user_id);
		Assert.assertEquals(response.getProject_id(), TimeSheetDetails.project_id);
		Assert.assertEquals(response.getCreator_company_user_id(), TimeSheetDetails.creator_company_user_id);	
	}
}
