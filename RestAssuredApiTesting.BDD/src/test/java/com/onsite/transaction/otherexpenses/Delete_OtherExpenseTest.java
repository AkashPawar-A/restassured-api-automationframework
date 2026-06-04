package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.OtherExpenses;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Delete_OtherExpenseTest {

	@DataProvider(name="otherExpenseId")
	public Object[][] getData() throws IOException{

		String fileData = new String(Files.readAllBytes(
				Paths.get("src/test/resources/testdata_otherExpenses/details.otherexpenses.json")));

		JSONObject jsonObj = new JSONObject(fileData);
		String otherExpenseId = jsonObj.getString("id");

		if(otherExpenseId != null && !otherExpenseId.isEmpty()) {
			System.out.println("otherExpenseId is :" + otherExpenseId);
		} else {
			Assert.fail("otherExpenseId is null or empty");
		}

		return new Object[][] {
			{otherExpenseId}
		};
	}

	@Test(priority=1, dataProvider="otherExpenseId", description="Positive test case verify")
	public void deleteOtherExp(String otherExpenseId) {

		Response otherExpResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.pathParam("id", otherExpenseId)
				.contentType(ContentType.JSON)
				.log().uri()

				.when()
				.delete(OtherExpenses.deletePartyearning)

				.then()
				.log().all()
				.extract().response();

		// Status Code Validation
		Assert.assertEquals(otherExpResponse.getStatusCode(), 200, "Status code not matched");

		// Response Message Validation
		String responseMessage = otherExpResponse.jsonPath().getString("message");
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("response message : " + responseMessage);
		} else {
			System.out.println("response message is null or empty");
		}

		// Business Validation
		int deleteFlag = otherExpResponse.jsonPath().getInt("delete");
		if(deleteFlag != 0) {
			System.out.println("deleteFlag : " + deleteFlag);
		} else {
			Assert.fail("deleteFlag : " + deleteFlag);
		}

		// Schema Validation
		otherExpResponse.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath(
						"responseSchema_files/OtherExpensesResponseSchema.json"
						));

		// Response Time Validation
		long responseTime = otherExpResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("successfull response time is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
	}

	@Test(priority=2, description="INVALID ID test case")
	public void verifyDeleteWithInvalidId() {

		String invalidId = "invalid123";

		Response otherExpResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", invalidId)
				.log().all()

				.when()
				.delete(OtherExpenses.deletePartyearning)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(otherExpResponse.getStatusCode(), 400, "Status code not matched");
	}

	@Test(priority=3, dataProvider = "otherExpenseId", description="WITHOUT TOKEN test case")
	public void verifyDeleteWithoutToken(String otherExpenseId) {

		Response otherExpResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.contentType(ContentType.JSON)
				.pathParam("id", otherExpenseId)
				.log().all()

				.when()
				.delete(OtherExpenses.deletePartyearning)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(otherExpResponse.getStatusCode(), 401, "Status code not matched");
	}

	@Test(priority=4, description = "EMPTY ID test case")
	public void verifyDeleteWithEmptyId() {

		String emptyId = "";

		Response otherExpResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", emptyId)
				.log().all()

				.when()
				.delete(OtherExpenses.deletePartyearning)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(otherExpResponse.getStatusCode(), 404, "Status code not matched"
				);
	}

	@Test(priority=5, dataProvider = "otherExpenseId", description = "ALREADY DELETED ID test case")
	public void verifyDeleteWithAlreadyDeletedId(String otherExpenseId) {

		// First Delete
		RestAssured
		.given()
		.baseUri(ApiBasePath.BASE_URL)
		.header("Authorization", "Bearer " + AuthUtils.getToken())
		.contentType(ContentType.JSON)
		.pathParam("id", otherExpenseId)

		.when()
		.delete(OtherExpenses.deletePartyearning);

		// Second Delete
		Response otherExpResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", otherExpenseId)
				.log().all()

				.when()
				.delete(OtherExpenses.deletePartyearning)

				.then()
				.log().all()
				.extract().response();

		Assert.assertTrue(otherExpResponse.getStatusCode() == 404 || otherExpResponse.getStatusCode() == 400, 
				"Unexpected status code");
	}

}
