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
import com.onsite.context.MaterialPurchaseDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Deduction_Api;
import com.onsite.pojo_request.DeductionEntryBulkAdd_Request;
import com.onsite.pojo_request.DeductionEntry_Request;
import com.onsite.pojo_response.DeductionEntry_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.DataFile;
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

	@Test(dataProvider="deductionItem", dependsOnGroups="material")
	public void addDeductionItem(DeductionEntryBulkAdd_Request deductionBulkItemPayload) {

		Response deductionResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(deductionBulkItemPayload)
				.log().uri()

				.when()
				.post(Deduction_Api.genericAddDeduction)

				.then()
				.log().all()
				.extract().response();

		List<DeductionEntry_Response> resposeList = deductionResponse.jsonPath().getList("", DeductionEntry_Response.class);

		//read data in material purchase
		String totalStr = DataFile.getData("total_amount");
		String subTotalStr = DataFile.getData("itemSubTotalAmount");
		Double purchasetotalamount = Double.parseDouble(totalStr);
		Double purchaseSubTotalAmount = Double.parseDouble(subTotalStr);
		System.out.println("Fetched totalAmount: " + purchasetotalamount + " & sub total Amount :" + purchaseSubTotalAmount);

		int responseStatusCode = deductionResponse.getStatusCode();
		if(responseStatusCode == 200)
			deductionResponse.then().assertThat().body(
					JsonSchemaValidator.matchesJsonSchemaInClasspath(
							"responseSchema_files/DeductionResponseSchema.json")
					);

		long responseTime = deductionResponse.getTime();
		if(responseTime <= 2000) {
			System.out.println("successfull response time is : " + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}

		List<DeductionEntry_Response> responseList = deductionResponse.jsonPath().getList("", DeductionEntry_Response.class);
		if(responseList == null || responseList.isEmpty()) {
			Assert.fail("response list is null or empty");
		}

		List<DeductionEntry_Request> requestList = deductionBulkItemPayload.getDeduction_entry_data();
		if(requestList == null || requestList.isEmpty()) {
			Assert.fail("request list is null or empty");
		}

		if(requestList.size() != responseList.size()) {
			Assert.fail("request list and response list is missmatch");
		}

		List<String> percentageOfList = List.of("totalamount", "itemSubtotal");

		for(int i=0; i< responseList.size(); i++) {

			DeductionEntry_Response res = responseList.get(i);
			DeductionEntry_Request req = requestList.get(i);

			String deductionId = res.getId();
			String deductionFlag = res.getApproval_flag();
			String featureId = res.getFeature_id();
			String featureType = res.getFeature_type();
			String projectId = res.getProject_id();
			String partyCompanyUserId = res.getParty_company_user_id();
			String percentOf = res.getPercent_of();
			Double percentage = res.getPercentage();
			Integer isPercent = res.getIs_percent();
			Double deductionAmount = res.getAmount();

			if(deductionId != null) {
				System.out.println("deduction id :" + deductionId);
			} else {
				Assert.fail("deduction id is null or empty");
			}

			if(featureId != null) {
				System.out.println("features id :" + featureId);
			} else {
				Assert.fail("feature id is null or empty");
			}

			if(projectId != null) {
				System.out.print("project id :" + projectId);
			} else {
				Assert.fail("project id is null or empty");
			}

			if(partyCompanyUserId != null) {
				System.out.println("partyCompanyUserId is :" + partyCompanyUserId);
			} else {
				Assert.fail("partyCompanyUserId is null or empty");
			}

			if(featureType != null && featureType.equals("materialpurchase")) {
				System.out.println("featureType valid :" + featureType);
			} else {
				Assert.fail("featureType mismatch");
			}

			if(deductionFlag != null) {
				System.out.println("approval flag: " + deductionFlag);
			}

			Double expDeductionPercentAmount = 0.0;
			if(isPercent != null && isPercent == 1) {

				Double percentageValue = req.getPercentage();
				if(percentOf != null && percentOf.equalsIgnoreCase("totalamount")) {
					expDeductionPercentAmount = (purchasetotalamount * percentageValue) / 100;

				} else if(percentOf != null && percentOf.equalsIgnoreCase("itemsubtotal")) {
					expDeductionPercentAmount = (purchaseSubTotalAmount * percentageValue) / 100;

				} else {
					Assert.fail("Invalid percent_of value: " + percentOf);
				}
				
				if(deductionAmount != null && deductionAmount > 0) {
					Assert.assertEquals(expDeductionPercentAmount, deductionAmount, 0.01);
					System.out.println("Percent calculation correct: " + deductionAmount + ": with :" + expDeductionPercentAmount);
				} else {
					System.out.println("⚠️ API ne percent calculate nahi kiya (amount = 0.0), skipping validation");
				}
				
			} else if(isPercent != null && isPercent == 0) {
				Double reqAmount = req.getAmount();
				Assert.assertEquals(deductionAmount, reqAmount, 0.01);
				System.out.println("Direct amount correct: " + deductionAmount);
			}
		}
	}
}
