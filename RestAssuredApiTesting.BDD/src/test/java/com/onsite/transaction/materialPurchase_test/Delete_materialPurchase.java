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

	private static final String filePath = "src/test/resources/testdata_materialpurchase/materialPurchaseData.json";

	@DataProvider(name="testData")
	public Object[][] getData() throws IOException{

		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(filePath)));

		JSONObject jsonObj = new JSONObject(json);
		String materialPurchaseId = jsonObj.getString("id");

		if(materialPurchaseId != null && !materialPurchaseId.isEmpty()) {
			System.out.println("");
		} else {
			Assert.fail("material purchase id is null or empty");
		}

		return new Object[][] {
			{materialPurchaseId}
		};

	}

	@Test(dataProvider="testData")
	public void deleteMaterialPurchase(String materialPurchaseId) {

		Response materialPurchaseResponse = RestAssured.
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken() )
				.contentType(ContentType.JSON)
				.pathParam("id", materialPurchaseId)
				.log().all()

				.when()
				.delete(MaterialPurchase.delete_materialPurchase)

				.then()
				.log().all()
				.extract().response();

		int responseStatusCode = materialPurchaseResponse.getStatusCode();
		if(responseStatusCode == 200) {
			System.out.println("responseStatusCode : " + responseStatusCode);
		} else {
			Assert.fail("responseStatusCode not match with :" + responseStatusCode);
		}

		String responseMessage = materialPurchaseResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message : " + responseMessage);
		} else {
			System.out.println("response message : " + responseMessage);
		}
		
		int deleteFlag = materialPurchaseResponse.jsonPath().get("delete");
		if(deleteFlag == 1) {
			System.out.println("deleteFlag is : " + deleteFlag);
		} else {
			Assert.fail("deleteFlag is :" + deleteFlag);
		}
		
		//deserialization - verify the response structure 
		materialPurchaseResponse.then().and().assertThat().body
		(JsonSchemaValidator.matchesJsonSchemaInClasspath("responseSchema_files/materialPurchaseResponseSchema.json"));
		System.out.println("responseStatusCode :" + responseStatusCode);

		long responseTime = materialPurchaseResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("actual response time is :" + responseTime);
		} else {
			Assert.fail("response time is too long : " + responseTime);
		}
	}
}
