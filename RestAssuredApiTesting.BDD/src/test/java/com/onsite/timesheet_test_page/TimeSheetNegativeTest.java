package com.onsite.timesheet_test_page;

import org.testng.annotations.Test;
import com.onsite.pojo_request.TimeSheetCreateRequest;
import com.onsite.utilities_page.ApiNegativeValidator;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.negativePayloadbuilder.TimeSheetPayload;
import com.onsite.utilities_page.BaseToken;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class TimeSheetNegativeTest extends BaseToken {

    @Test(priority=1)
    public void test_Unauthorized() {
        TimeSheetCreateRequest payload = TimeSheetPayload.buildtimeSheetCreatePayload();

        Response response = given()
            .baseUri(ApiBasePath.BASE_URL)
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(TimeSheet_Api.create_timeSheet)
        .then()
            .extract().response();

        System.out.println("Response body: " + response.getBody().asString());
        ApiNegativeValidator.validateResponse(response, 401, "no token present in request");
    }

    @Test(priority=2)
    public void test_MissingFields() {
        TimeSheetCreateRequest payload = new TimeSheetCreateRequest(); // Empty payload

        Response response = given()
            .baseUri(ApiBasePath.BASE_URL)
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(TimeSheet_Api.create_timeSheet)
        .then()
            .extract().response();

        System.out.println("Response body: " + response.getBody().asString());
        ApiNegativeValidator.validateResponse(response, 400, "Missing required");
    }

    @Test(priority=3)
    public void test_InvalidProjectId() {
        TimeSheetCreateRequest payload = TimeSheetPayload.buildtimeSheetCreatePayload();
        payload.setProject_id("Invalid project id");

        Response response = given()
            .baseUri(ApiBasePath.BASE_URL)
            .header("Authorization", "Bearer " + token)
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post(TimeSheet_Api.create_timeSheet)
        .then()
            .extract().response();

        System.out.println("Response body: " + response.getBody().asString());
        ApiNegativeValidator.validateResponse(response, 400, "Invalid project id");
    }
    
    @Test(priority=4)
    public void test_InvalidCompanyId() {
    	TimeSheetCreateRequest payload = TimeSheetPayload.buildtimeSheetCreatePayload();
    	payload.setCompany_id("Invalid company id");
    	
    	Response response = given()
    			.baseUri(ApiBasePath.BASE_URL)
    			.header("Authorization", "Bearer " + token)
    			.contentType(ContentType.JSON)
    			.body(payload)
    		 .when()
    		    .post(TimeSheet_Api.create_timeSheet)
    		 .then()
    		    .extract().response();
    	
    	System.out.println("Response body: " + response.getBody().asString());
    	ApiNegativeValidator.validateResponse(response, 400, "Invalid company id");
    }
    
    @Test(priority=5)
    public void test_InvalidPartyCompanyUserId() {
    	TimeSheetCreateRequest payload = TimeSheetPayload.buildtimeSheetCreatePayload();
    	payload.setParty_company_user_id("Invalid party company user id");
    	
    	Response response = given()
    			.baseUri(ApiBasePath.BASE_URL)
    			.header("Authorization", "Bearer " + token)
    			.contentType(ContentType.JSON)
    			.body(payload)
    		.when()
    			.post(TimeSheet_Api.create_timeSheet)
    		.then()
    		    .extract().response();
    	
    	System.out.println("Response body: " + response.getBody().asString());
    	ApiNegativeValidator.validateResponse(response, 400, "Invalid party company user id");
    }
}
