package com.onsite.project_test_page;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.Project_levelApi;
import com.onsite.context.ProjectDetail;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.payloadbuilder.ProjectCreatePayload;
import com.onsite.payloadbuilder.ProjectEditPayload;
import com.onsite.pojo_request.ProjectCreateRequest;
import com.onsite.pojo_response.ProjectResponseBody;
import com.onsite.utilities_page.BaseToken;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;

import static io.restassured.RestAssured.*;

public class ProjectCreateTest extends BaseToken {

    static String projectId;

    @Test(priority = 1)
    public void createProject() throws JsonProcessingException {

    	ProjectCreateRequest payload = ProjectCreatePayload.buildcreateProjectRequest();

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);
        System.out.println("Final JSON Payload: " + jsonPayload);

        Response response =
                given()
                    .baseUri(ApiBasePath.BASE_URL)
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(jsonPayload)
                    .log().all()
                .when()
                    .post(Project_levelApi.CREATE_PROJECT)
                .then()
                    .statusCode(200)  // Always check this
                    .body("name", equalTo("API Test Project - t7"))
                    .body("company_id", equalTo("75916659-9cbe-4ca7-812e-181a29229772"))
                    .body("contractor", equalTo("c5b0bdca-6941-4411-aa64-369f6bb8cc32"))
                    .body("start_date", equalTo("2025-06-10T00:00:00Z"))
                    .body("end_date", equalTo("2026-05-11T00:00:00Z"))
                    .body("address", equalTo("Pawar Mala"))
                    .body("city", equalTo("Nashik"))
                    .body("state", equalTo("Maharashtra"))
                    .body("status", equalTo("Ongoing"))
                    .body("estimated_cost", equalTo(5000))  // it's int/double, not string
                    .body("attendance_radius", equalTo(400)) // same: number not string
                    .body("admins[0]", equalTo("c5b0bdca-6941-4411-aa64-369f6bb8cc32"))
                    .body("location.type", equalTo("Point"))
                    .body("location.coordinates[0]", equalTo(1.1f))  // use float for double match
                    .body("location.coordinates[1]", equalTo(1.1f))
                    .log().all()
                    .extract().response();
                    
        ProjectDetail.projectId = response.jsonPath().getString("id"); 
        ProjectDetail.companyId = response.jsonPath().getString("company_id");
        ProjectDetail.contractorId = response.jsonPath().getString("contractor");
        ProjectDetail.adminsId = response.jsonPath().getString("admins");
        ProjectDetail.createdId = response.jsonPath().getString("creator");
        ProjectDetail.creatorCompanyUserId = response.jsonPath().getString("creator_company_user_id");
        
        ProjectDetail.editPayload = ProjectEditPayload.editProjectRequest();  
        System.out.println("Created Project ID: " + ProjectDetail.projectId);
    }

    @Test(priority = 2, dependsOnMethods = "createProject")
    public void getProject() {

    	ProjectResponseBody responseObj =
    		    given()
    		        .baseUri(ApiBasePath.BASE_URL)
    		        .header("Authorization", "Bearer " + token)
    		        .contentType(ContentType.JSON)
    		        .pathParam("projectId", ProjectDetail.projectId)
    		    .when()
    		        .get(Project_levelApi.GET_PROJECT)
    		    .then()
    		        .statusCode(200)
    		        .extract().as(ProjectResponseBody.class);
    	
    	Assert.assertEquals(responseObj.getName(), "API Test Project - t7");
    	Assert.assertEquals(responseObj.getCompany_id(), "75916659-9cbe-4ca7-812e-181a29229772");
    	Assert.assertEquals(responseObj.getContractor(), "c5b0bdca-6941-4411-aa64-369f6bb8cc32");
    	Assert.assertEquals(responseObj.getStart_date(), "2025-06-10T00:00:00Z");
    	Assert.assertEquals(responseObj.getEnd_date(), "2026-05-11T00:00:00Z");
    	Assert.assertEquals(responseObj.getAddress(), "Pawar Mala");
    	Assert.assertEquals(responseObj.getCity(), "Nashik");
    	Assert.assertEquals(responseObj.getState(), "Maharashtra");
    	Assert.assertEquals(responseObj.getStatus(), "Ongoing");
    	Assert.assertEquals(responseObj.getEstimated_cost(), 5000.0);
    	Assert.assertEquals(responseObj.getAttendance_radius(), 400);
    	Assert.assertEquals(responseObj.getAdmins(), Arrays.asList("c5b0bdca-6941-4411-aa64-369f6bb8cc32"));
    	Assert.assertEquals(responseObj.getLocation().getCoordinates().get(0), 1.1, 0.0001);
    	Assert.assertEquals(responseObj.getLocation().getCoordinates().get(1), 1.1, 0.0001);

        System.out.println("Project Name: " + responseObj.getName());
        System.out.println("City: " + responseObj.getCity());
    }
}
