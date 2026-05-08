package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.OtherExpenses;
import com.onsite.pojo_request.OtherExpensesRequest;
import com.onsite.pojo_response.OtherExpenseResponse;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.http.ContentType;
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

	@Test(dataProvider="testData")
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

		//RESPONSE DESERIALIZATION - Response JSON → Response POJO
		OtherExpenseResponse response = otherExpResponse.as(OtherExpenseResponse.class);

	}

}
