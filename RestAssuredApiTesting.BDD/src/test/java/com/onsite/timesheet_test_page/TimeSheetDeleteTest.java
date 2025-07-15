package com.onsite.timesheet_test_page;

import com.onsite.context.TimeSheetDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.pojo_response.TimeSheetResponseBody;
import com.onsite.utilities_page.BaseToken;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TimeSheetDeleteTest extends BaseToken {
	
	@Test(priority=1)
	public void deleteTimesheet()
	{
		if(TimeSheetDetails.timesheet_id == null || TimeSheetDetails.timesheet_id.isEmpty()) {
			throw new IllegalStateException("timesheet id is null");
		}
		
		TimeSheetResponseBody deleteObj = 
		
		given()
		    .baseUri(ApiBasePath.BASE_URL)
		    .header("Authorization", "Bearer " + token)
		    .contentType(ContentType.JSON)
		    .pathParam("timesheet_id", TimeSheetDetails.timesheet_id)
		
		.when()
		    .delete(TimeSheet_Api.delet_timeSheet)
		    
		.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all()
		    .extract().as(TimeSheetResponseBody.class);
		
		Assert.assertNotNull(deleteObj);
		Assert.assertEquals(deleteObj.getId(), TimeSheetDetails.timesheet_id);
	}
}
