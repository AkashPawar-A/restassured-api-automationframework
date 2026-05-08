package com.onsite.transaction.materialPurchase_test;

import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Delete_materialPurchase {

	private static final String filePath =
			"src/test/resources/testdata_materialpurchase/materialPurchaseData.json";

	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {

		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(filePath)));

		JSONObject jsonObj = new JSONObject(json);
		String materialPurchaseId = jsonObj.getString("id");

		if(materialPurchaseId != null && !materialPurchaseId.isEmpty()) {
			System.out.println("materialpurchase id is :" + materialPurchaseId);
		} else {
			Assert.fail("materialPurchaseId is null or empty");
		}

		return new Object[][]{
			{materialPurchaseId}
		};
	}

	// =========================================================
	// POSITIVE TEST CASE
	// =========================================================

	@Test(priority=1, dataProvider = "testData", description="validId test case")
	public void verifyDeleteWithValidId(String materialPurchaseId) {

		Response materialPurchaseResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", materialPurchaseId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		// Status Code Validation
		Assert.assertEquals(materialPurchaseResponse.getStatusCode(), 200, "Status code not matched");

		// Response Message Validation
		String responseMessage = materialPurchaseResponse.jsonPath().getString("message");
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("response message : " + responseMessage);
		} else {
			System.out.println("response message is null or empty");
		}

		// Business Validation
		int deleteFlag = materialPurchaseResponse.jsonPath().getInt("delete");
		if(deleteFlag != 0) {
			System.out.println("deleteFlag : " + deleteFlag);
		} else {
			Assert.fail("deleteFlag : " + deleteFlag);
		}

		// Schema Validation
		materialPurchaseResponse.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath(
						"responseSchema_files/materialPurchaseResponseSchema.json"
						));

		// Response Time Validation
		long responseTime = materialPurchaseResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("successfull response time is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
	}

	// =========================================================
	// NEGATIVE TEST CASE
	// =========================================================

	@Test(priority=2, description="INVALID ID test case")
	public void verifyDeleteWithInvalidId() {

		String invalidId = "invalid123";

		Response materialPurchaseResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", invalidId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialPurchaseResponse.getStatusCode(), 400, "Status code not matched");
	}

	@Test(priority=3, dataProvider = "testData", description="WITHOUT TOKEN test case")
	public void verifyDeleteWithoutToken(String materialPurchaseId) {

		Response materialPurchaseResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.contentType(ContentType.JSON)
				.pathParam("id", materialPurchaseId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialPurchaseResponse.getStatusCode(), 401, "Status code not matched");
	}

	@Test(priority=4, description = "EMPTY ID test case")
	public void verifyDeleteWithEmptyId() {

		String emptyId = "";

		Response materialPurchaseResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", emptyId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(materialPurchaseResponse.getStatusCode(), 404, "Status code not matched"
				);
	}

	@Test(priority=5, dataProvider = "testData", description = "ALREADY DELETED ID test case")
	public void verifyDeleteWithAlreadyDeletedId(
			String materialPurchaseId) {

		// First Delete

		RestAssured
		.given()
		.baseUri(ApiBasePath.BASE_URL)
		.header("Authorization", "Bearer " + AuthUtils.getToken())
		.contentType(ContentType.JSON)
		.pathParam("id", materialPurchaseId)

		.when()
		.delete(MaterialPurchase.delete_materialPurchase);

		// Second Delete

		Response materialPurchaseResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", materialPurchaseId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		Assert.assertTrue(materialPurchaseResponse.getStatusCode() == 404 || 
				materialPurchaseResponse.getStatusCode() == 400, "Unexpected status code");
	}

}
