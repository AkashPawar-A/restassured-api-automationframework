package com.onsite.project_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.ProjectDetail;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Project_levelApi;
import com.onsite.payloadbuilder.ProjectEditStatusPayload;
import com.onsite.pojo_request.ProjectEditStatausRequest;
import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectEditStatusTest extends BaseToken{
	
	@Test(priority=1)
	public void editStatusProject() throws Exception {
	
		ProjectEditStatausRequest statusPayload = ProjectEditStatusPayload.projectEditStatusPayload();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(statusPayload);
		System.out.println("final json payload : " + jsonPayload);
		
		Response response = 
				given()
				  .baseUri(ApiBasePath.BASE_URL)
				  .header("Authorization", "Bearer " + token)
				  .contentType(ContentType.JSON)
				  .body(jsonPayload)
				  .log().all()
				.when()
				  .patch(Project_levelApi.UPDATE_STATUS)
				.then()
				  .statusCode(200)
				  .log().body()
				  .extract().response();
	}
	
	@Test(priority=2, dependsOnMethods="editStatusProject")
	public void getProjectStatus() {
		
		Response response = 
				given()
				  .baseUri(ApiBasePath.BASE_URL)
				  .header("Authorization", "Bearer " + token)
				  .contentType(ContentType.JSON)
				  .pathParam("projectId", ProjectDetail.projectId)
				.when()
				  .get(Project_levelApi.GET_PROJECT)
				.then()
				  .statusCode(200)
				  .log().body()
				  .extract().response();
				  
	    ProjectResponseBody responseObj = response.as(ProjectResponseBody.class);
	    
	    System.out.println("Projectccurrent Status :" + responseObj.getStatus());
		Assert.assertEquals(responseObj.getStatus(), "Complete");	  
	}
}
