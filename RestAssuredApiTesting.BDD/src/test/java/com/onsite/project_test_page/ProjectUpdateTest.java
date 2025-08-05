package com.onsite.project_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.ProjectDetail;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Project_Api;
import com.onsite.payloadbuilder.ProjectEditPayload;
import com.onsite.pojo_request.ProjectEditRequest;
import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectUpdateTest extends BaseToken {

    @Test(priority = 1)
    public void editProject() throws Exception {

        //Check if project ID is set
        if (ProjectDetail.projectId == null || ProjectDetail.projectId.isEmpty()) {
            throw new IllegalStateException("Project ID is null");
        }

        //Build payload
        ProjectEditRequest editPayload = ProjectEditPayload.buildEditProjectRequest();
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(editPayload);

        System.out.println("Final JSON Payload: " + jsonPayload);

        //Send PATCH request
        Response response = 
        		
        	given()
                .baseUri(ApiBasePath.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .log().all()
            .when()
                .patch(Project_Api.UPDATE_PROJECT)
            .then()
                .log().all()
                .extract().response();

        //Handle response status
        int status = response.getStatusCode();
        
        if (status != 200) {
            System.out.println("PATCH failed with status code: " + status);
            System.out.println("Response: " + response.getBody().asString());
        } else {
            System.out.println("PATCH successful");
        }

        //Extract updated project ID (if returned)
        String id = response.jsonPath().getString("id");
        if (id != null) {
            ProjectDetail.projectId = id;
        }

        System.out.println("Updated project id: " + ProjectDetail.projectId);
    }

    @Test(priority = 2, dependsOnMethods = "editProject")
    public void getUpdatedProject() {

        //Get updated project details using GET
        ProjectResponseBody responseObj = 
        		
        	given()
                .baseUri(ApiBasePath.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .pathParam("projectId", ProjectDetail.projectId) 
            .when()
                .get(Project_Api.GET_PROJECT)
            .then()
                .statusCode(200)
                .extract().as(ProjectResponseBody.class);

        //Output project name
        System.out.println("Updated project name: " + responseObj.getName());
    }
}


