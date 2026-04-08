package com.onsite.deduction_test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Deduction_Api;
import com.onsite.pojo_request.DeductionEntryBulkAdd_Request;
import com.onsite.pojo_response.DeductionEntry_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonUtils;
import com.onsite.utilities_page.SchemaValidator;

public class Add_Deduction_test {

	@DataProvider(name="deductionItem")
	public Object[][] testData(){
		Map<String, Object> deductionBulkItem = JsonUtils.readJson("src/test/resources/testdata_deductionentry/deductionEntryBulkAdd.json"); 
		Map<String, Object> deductionEntryData = JsonUtils.readJson("src/test/resources/testdata_deductionentry/deduction_entry_data.json");

		List<Map<String, Object>> deductionEntryList = new ArrayList<>();
		deductionEntryList.add(deductionEntryData);

		deductionBulkItem.put("deduction_entry_data", deductionEntryList);

		String jsonMapper = null;
		try {
			jsonMapper = new ObjectMapper().writeValueAsString(deductionBulkItem);
		} catch (JsonProcessingException e) {
			System.out.println("final deduction item payload :" + deductionBulkItem);
			e.printStackTrace();
		}

		try {
			SchemaValidator.validateSchema("requestSchemas_files/DeductionRequestSchema.json", jsonMapper);
		} catch (Exception e) {
			Assert.fail("deduction request schema validation fail :" + e.getMessage());
		}

		DeductionEntryBulkAdd_Request deductionAdd = JsonUtils.converMaptoPojo(deductionBulkItem, DeductionEntryBulkAdd_Request.class);

		return new Object[][] {
			{deductionAdd}
		};
	}

	@Test(dataProvider="deductionItem")
	public void addDeductionItem(DeductionEntryBulkAdd_Request deductionBulkItemPayload) {

		Response deductionResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(deductionBulkItemPayload)
				.log().all()

				.when()
				.post(Deduction_Api.genericAddDeduction)

				.then()
				.log().all()
				.extract().response();

		List<DeductionEntry_Response> resposeList = deductionResponse.jsonPath().getList("", DeductionEntry_Response.class);

		int responseStatusCode = deductionResponse.getStatusCode();
		if(responseStatusCode == 200)
			deductionResponse.then().assertThat().body(
					JsonSchemaValidator.matchesJsonSchemaInClasspath(
							"responseSchema_files/DeductionResponseSchema.json")
					);
	}

}
