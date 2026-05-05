package com.onsite.transaction.materialPurchase_test;

import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_response.MaterialPurchaseResponse;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Edit_materialPurchase_Test {
	
	private static final String edit_data_path = "src/test/resources/testdata_materialpurchase/edit_materialpurchase.json";
	
	@DataProvider(name = "testdata")
	public Object[][] getData() throws IOException{
		
		//step = 1 : read edit_materialpurchase.json file
		String jsonPayload = new String(Files.readAllBytes(
				Paths.get(edit_data_path)));
		
		JSONObject editPayloadData = new JSONObject(jsonPayload);
		
		String materialPurchaseId = editPayloadData.getString("id");
		if(materialPurchaseId != null && !materialPurchaseId.isEmpty()) {
			System.out.println("material purchase id :" + materialPurchaseId);
		} else {
			Assert.fail("material purchase id is null or empty");
		}
		
		//STEP 2: DataProvider se editPayloadData ko return करो
		return new Object[][] {
			{editPayloadData}
		};	
	}
	
	@Test(dataProvider="testdata")
	public void editData(JSONObject editPayloadData) throws IOException, DatabindException, IOException {
		
		Response purchaseResponse = RestAssured.
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken() )
				.contentType(ContentType.JSON)
				.body(editPayloadData.toString())
				.log().uri()
				
				.when()
				.patch(MaterialPurchase.edit_materialPurchase)
				
				.then()
				.log().all()
				.extract().response();
		
		int responseStatusCode = purchaseResponse.getStatusCode();
		if(responseStatusCode == 200) {
			System.out.println("response status code is :" + responseStatusCode);
		} else {
			Assert.fail("response status code is :" + responseStatusCode);
		}
		
		String responseMessage = purchaseResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("successfull response Message is :" + responseMessage);
		} else {
			System.out.println("failure response message : " + responseMessage);
		}
		
		long responseTime = purchaseResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("successfull response time is :" + responseTime);
		} else {
			Assert.fail("failure response time :" + responseTime);
		}
		
		//deserialization case
		ObjectMapper mapper = new ObjectMapper();
		MaterialPurchaseResponse materialPurchaseData = mapper.readValue(
				new File("src/test/resources/testdata_materialpurchase/edit_materialPurchase.json"), MaterialPurchaseResponse.class);
		
		String materialPurchase_Id = purchaseResponse.jsonPath().get("id");
		String companyId = purchaseResponse.jsonPath().get("company_id");
		String creatorCompanyUserId = purchaseResponse.jsonPath().get("creator_company_user_id");
		String partyCompanyUserId = purchaseResponse.jsonPath().get("party_company_user_id");
		String projectId = purchaseResponse.jsonPath().get("project_id");
		String invoiceId = purchaseResponse.jsonPath().get("invoice_id");
		Double discount = purchaseResponse.jsonPath().getDouble("discount");   
		Double gst_amount = purchaseResponse.jsonPath().getDouble("gst_amount");
		Double other_amount = purchaseResponse.jsonPath().getDouble("other_amount");
		Double material_amount = purchaseResponse.jsonPath().getDouble("material_amount");
		Double total_payable = purchaseResponse.jsonPath().getDouble("total_payable");
		Integer sequence = purchaseResponse.jsonPath().get("sequence");
		List<String> photos = purchaseResponse.jsonPath().getList("photos");
		List<String> materialIds = purchaseResponse.jsonPath().getList("materials");
		String purchase_date = purchaseResponse.jsonPath().get("purchase_date");
		String vendor_reference_number = purchaseResponse.jsonPath().get("vendor_reference_number");
		Integer delete = purchaseResponse.jsonPath().get("delete");
		String approval_flag = purchaseResponse.jsonPath().get("approval_flag");
		List<Object> monkey_patch_materials = purchaseResponse.jsonPath().get("monkey_patch_materials");
		Integer monkey_patch_is_editable= purchaseResponse.jsonPath().get("monkey_patch_is_editable");
		Integer monkey_patch_paid_amount= purchaseResponse.jsonPath().get("monkey_patch_paid_amount");
		Integer can_edit = purchaseResponse.jsonPath().get("meta_data.can_edit");
		Integer due_days = purchaseResponse.jsonPath().get("due_days");
		String ship_to_address_id = purchaseResponse.jsonPath().get("ship_to_address_id");
		String bill_to_address_id = purchaseResponse.jsonPath().get("bill_to_address_id");
		String ship_from_address_id = purchaseResponse.jsonPath().get("ship_from_address_id");
		String bill_from_address_id = purchaseResponse.jsonPath().get("bill_from_address_id");
		String due_date = purchaseResponse.jsonPath().get("due_date");
		Double pre_tax_deduction_amount = purchaseResponse.jsonPath().getDouble("pre_tax_deduction_amount");
		Double post_tax_deduction_amount = purchaseResponse.jsonPath().getDouble("post_tax_deduction_amount");
		Double deduction_amount = purchaseResponse.jsonPath().getDouble("deduction_amount");
		Double net_amount = purchaseResponse.jsonPath().getDouble("net_amount");
		Integer is_roundoff = purchaseResponse.jsonPath().get("is_roundoff");
		Double other_amount_gst_percentage = purchaseResponse.jsonPath().getDouble("other_amount_gst_percentage");
		Double other_amount_gst_amount = purchaseResponse.jsonPath().getDouble("other_amount_gst_amount");
		String other_amount_text = purchaseResponse.jsonPath().get("other_amount_text");
			
	}

}
