package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Delete_MaterialReturn_test {
	
	private String materialReturnPath = "src/test/resources/testdata_materialReturn/details_materialReturn.json";
	
	@DataProvider(name="materialReturnId")
	public Object[][] getMaterialReturnId() throws IOException{
		
		String json = new String(Files.readAllBytes(Paths.get(materialReturnPath)));
		
		JSONObject jsonObj = new JSONObject(json);
		String materialReturnId = jsonObj.optString("id", "");
		
		if(materialReturnId != null && !materialReturnId.isEmpty()) {
			System.out.println("deleting materials return id :" + materialReturnId);
		} else {
			Assert.fail("material return is is null or empty");
		}
		
		return new Object[][] {
			{ materialReturnId }
		};
	}
	
	// POSITIVE TEST CASE

	@Test(priority=1, dataProvider = "materialReturnId", description="validId test case")
	public void verifyDeleteWithValidId(String materialReturnId) {

		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", materialReturnId)
				.log().all()

				.when()
				.delete(MaterialReturn_Api.delete_materialreturn)

				.then()
				.log().all()
				.extract().response();

		// Status Code Validation
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Status code not matched");

		// Response Message Validation
		String responseMessage = materialReturnResponse.jsonPath().getString("message");
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("response message : " + responseMessage);
		} else {
			System.out.println("response message is null or empty");
		}

		// Business Validation
		int deleteFlag = materialReturnResponse.jsonPath().getInt("delete");
		if(deleteFlag != 0) {
			System.out.println("deleteFlag : " + deleteFlag);
		} else {
			Assert.fail("deleteFlag : " + deleteFlag);
		}

		// Schema Validation
		materialReturnResponse.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath(
						"responseSchema_files/materialPurchaseResponseSchema.json"
						));

		// Response Time Validation
		long responseTime = materialReturnResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("successfull response time is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
	}

	// NEGATIVE TEST CASE

	@Test(priority=2, description="INVALID ID test case")
	public void verifyDeleteWithInvalidId() {

		String invalidId = "invalid123";

		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", invalidId)
				.log().all()

				.when()
				.delete(MaterialReturn_Api.delete_materialreturn)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Status code not matched");
	}

	@Test(priority=3, dataProvider = "testData", description="WITHOUT TOKEN test case")
	public void verifyDeleteWithoutToken(String materialReturnId) {

		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.contentType(ContentType.JSON)
				.pathParam("id", materialReturnId)
				.log().all()

				.when()
				.delete(MaterialReturn_Api.delete_materialreturn)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialReturnResponse.getStatusCode(), 401, "Status code not matched");
	}

	@Test(priority=4, description = "EMPTY ID test case")
	public void verifyDeleteWithEmptyId() {

		String emptyId = "";

		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", emptyId)
				.log().all()

				.when()
				.delete(MaterialReturn_Api.delete_materialreturn)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialReturnResponse.getStatusCode(), 404, "Status code not matched"
				);
	}

	@Test(priority=5, dataProvider = "testData", description = "ALREADY DELETED ID test case")
	public void verifyDeleteWithAlreadyDeletedId(String materialReturnId) {

		// First Delete
		RestAssured
		.given()
		.baseUri(ApiBasePath.BASE_URL)
		.header("Authorization", "Bearer " + AuthUtils.getToken())
		.contentType(ContentType.JSON)
		.pathParam("id", materialReturnId)

		.when()
		.delete(MaterialReturn_Api.delete_materialreturn);

		// Second Delete
		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", materialReturnId)
				.log().all()

				.when()
				.delete(MaterialReturn_Api.delete_materialreturn)

				.then()
				.log().all()
				.extract().response();

		Assert.assertTrue(materialReturnResponse.getStatusCode() == 404 || 
				materialReturnResponse.getStatusCode() == 400, "Unexpected status code");
	}

}
