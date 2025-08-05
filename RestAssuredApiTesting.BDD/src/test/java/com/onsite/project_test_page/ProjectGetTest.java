package com.onsite.project_test_page;

import com.onsite.context.ProjectDetail;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Project_Api;
import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectGetTest extends BaseToken{

	@Test(priority=1)
	public void getProjectDeatils() {
		
		if(ProjectDetail.projectId == null || ProjectDetail.projectId.isEmpty()) {
			throw new IllegalStateException("Project ID is null");
		}
		
		ProjectResponseBody response = 
		
		given()
		  .baseUri(ApiBasePath.BASE_URL)
		  .header("Authorization", "Bearer " + token)
		  .contentType("application/json; charset=utf-8")
		  .pathParam("projectId", ProjectDetail.projectId)

		.when()
		  .get(Project_Api.GET_PROJECT)
		
		.then()
		   .statusCode(200)
		   .statusLine("HTTP/1.1 200 OK")
		   .log().all()
		   .extract().as(ProjectResponseBody.class);
		
    	Assert.assertEquals(response.getName(), "API Test Project - t7");
    	Assert.assertEquals(response.getCompany_id(), "75916659-9cbe-4ca7-812e-181a29229772");
    	Assert.assertEquals(response.getContractor(), "c5b0bdca-6941-4411-aa64-369f6bb8cc32");
    	Assert.assertEquals(response.getStart_date(), "2025-06-10T00:00:00Z");
    	Assert.assertEquals(response.getEnd_date(), "2026-05-11T00:00:00Z");
    	Assert.assertEquals(response.getAddress(), "Pawar Mala");
    	Assert.assertEquals(response.getCity(), "Nashik");
    	Assert.assertEquals(response.getState(), "Maharashtra");
    	Assert.assertEquals(response.getStatus(), "Ongoing");
    	Assert.assertEquals(response.getEstimated_cost(), 5000.0);
    	Assert.assertEquals(response.getAttendance_radius(), 400);
    	Assert.assertEquals(response.getAdmins(), Arrays.asList("c5b0bdca-6941-4411-aa64-369f6bb8cc32"));
    	Assert.assertEquals(response.getLocation().getCoordinates().get(0), 1.1, 0.0001);
    	Assert.assertEquals(response.getLocation().getCoordinates().get(1), 1.1, 0.0001);

	}
}
