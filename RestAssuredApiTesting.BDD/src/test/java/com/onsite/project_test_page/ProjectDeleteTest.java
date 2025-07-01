package com.onsite.project_test_page;

import com.onsite.context.ProjectDetail;
import com.onsite.endpoints.Project_levelApi;
import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class ProjectDeleteTest extends BaseToken {
	
	@Test(priority=1)
	public void deleteProject() {
		
		if(ProjectDetail.projectId == null || ProjectDetail.projectId.isBlank()) {
			throw new IllegalStateException("project id is null");
		}
		
		ProjectResponseBody response = 
				given()
				   .baseUri(baseURI)
				   .header("Authorization", "Bearer "  + token)
				   .contentType("application/json; charset=utf-8")
				   .pathParam("projectId", ProjectDetail.projectId)
				   
				.when()
				   .delete(Project_levelApi.DELETE_PROJECT)
				   
				.then()
				   .statusCode(200)
				   .statusLine("HTTP/1.1 200 OK")
				   .spec(responseSpec)
				   .extract().as(ProjectResponseBody.class);
		
		System.out.println("Deleted Project ID: " + response.getId());		
	}
}
