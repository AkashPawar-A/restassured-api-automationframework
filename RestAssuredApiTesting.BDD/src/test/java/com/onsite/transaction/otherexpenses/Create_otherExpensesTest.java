package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.OtherExpenses;
import com.onsite.pojo_request.OtherExpensesRequest;
import com.onsite.pojo_response.OtherExpenseResponse;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Create_otherExpensesTest {

	@DataProvider(name="testData")
	public Object[][] getData() throws IOException{

		String jsonPayload = new String(Files.readAllBytes(
				Paths.get("src/test/resources/testdata_otherExpenses/add_otherExpenses.json")));
		
		// REQUEST DESERIALIZATION - JSON → Request POJO
		ObjectMapper mapper = new ObjectMapper();
		OtherExpensesRequest otherRequestPayload = mapper.readValue(jsonPayload, OtherExpensesRequest.class);
		
		return new Object[][] {
			{otherRequestPayload}
		};
	}

	@Test(dataProvider="testData", description="valid test cases")
	public void createotherExpense(OtherExpensesRequest otherRequestPayload) throws IOException {

		//// REQUEST SERIALIZATION - Request POJO → JSON
		ObjectMapper mapper = new ObjectMapper();
		String finalPayload = mapper.writeValueAsString(otherRequestPayload);
		System.out.println("final payload :" + otherRequestPayload);

		Response otherExpResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().all()

				.when()
				.post(OtherExpenses.addPartyEarning)

				.then()
				.log().all()
				.extract().response();
		
		// status code validation 
		int rsponseStatusCode = otherExpResponse.getStatusCode();
		if(rsponseStatusCode == 200) {
			System.out.println("response status code is :" + rsponseStatusCode);
		} else {
			Assert.fail("failure status code is :" + rsponseStatusCode);
		}
		
		// response message validation
		String responseMessage = otherExpResponse.jsonPath().getString("message");
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("responseMessage is :" + responseMessage);
		} else {
			System.out.println("responseMessage is null or empty");
		}

		// RESPONSE DESERIALIZATION - Response JSON → Response POJO
		OtherExpenseResponse response = otherExpResponse.as(OtherExpenseResponse.class);
		
		// response time validation
		long responseTime = otherExpResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("responseTime is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
		
		// response schema validation
		otherExpResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				"responseSchema_files/OtherExpensesResponseSchema.json"));
	}

}
