package com.onsite.transaction.materialPurchase_test;

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
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_request.MaterialPurchaseRequest;
import com.onsite.pojo_response.MaterialPurchaseResponse;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import com.onsite.utilities_page.JsonUtils;
import com.onsite.utilities_page.SchemaValidator;

public class Create_materialPurchaseTest {
	
	@DataProvider(name="purchaseData")
	public Object[][] testData(){
		
		Map<String, Object> purchase = JsonUtils.readJson("src/test/resources/testdata_materialpurchase/add_materialpurchase.json");
		Map<String, Object> material = JsonUtils.readJson("src/test/resources/testdata_material/material.json");
	
		List<Map<String, Object>> materials = new ArrayList<>();
		materials.add(material);
	
		purchase.put("materials", materials);
		System.out.println("final purchase payload : " + purchase);
		
		String requestJson = null;
		try {
			requestJson = new ObjectMapper().writeValueAsString(purchase);
		} catch (JsonProcessingException e) {
			Assert.fail("Schema validation failed: " + e.getMessage());
		}
		try {
			SchemaValidator.validateSchema("requestSchemas_files/MaterialPurchaseRequestSchema.json", requestJson);
		} catch (Exception e) {
			Assert.fail("Request Schema validation failed: " + e.getMessage());
		}
		
		MaterialPurchaseRequest purchaseCreate = JsonUtils.converMaptoPojo(purchase, MaterialPurchaseRequest.class);
		
		return new Object[][]{
			{purchaseCreate}
		};
	}
	
	@Test(dataProvider="purchaseData")
	public void addMaterialPurchase(MaterialPurchaseRequest materialpurchasePayload) {
		
		String loginCompanyId = CompanyContext.getCompanyId();
	
		Response purchaseResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(materialpurchasePayload)
				.log().all()
				
				.when()
				.post(MaterialPurchase.add_materialPurchase)
				
				.then()
				.log().all()
				.extract().response();
		
		MaterialPurchaseResponse response = purchaseResponse.as(MaterialPurchaseResponse.class);
		
		JsonSchemaValidator.matchesJsonSchemaInClasspath(
			    "responseSchema_files/materialPurchaseResponseSchema.json"
			);
	
		int responseStatusCode = purchaseResponse.getStatusCode();
		if(responseStatusCode == 200) {
			System.out.println("successfull response status code is :" + responseStatusCode);
		} else {
			System.out.println("failure response status code is :" + responseStatusCode);
		}
		
		String responseMessage = purchaseResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response successful message :" + responseMessage);
		} else {
			System.out.println("failure response message :" + responseMessage);
		}
		
		long responseTime = purchaseResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("successfull response time is :" + responseTime);
		} else {
			System.out.println("failuer response time is :" + responseTime);
		}
		
		String materialPurchaseId = purchaseResponse.jsonPath().get("id");
		String companyId = purchaseResponse.jsonPath().get("company_id");
		String creatorCompanyUserId = purchaseResponse.jsonPath().get("creator_company_user_id");
		String partyCompanyUserId = purchaseResponse.jsonPath().get("party_company_user_id");
		String projectId = purchaseResponse.jsonPath().get("project_id");
		String invoiceId = purchaseResponse.jsonPath().get("invoice_id");
		Integer discount = purchaseResponse.jsonPath().get("discount");
		Integer gst_amount = purchaseResponse.jsonPath().get("gst_amount");
		Integer other_amount = purchaseResponse.jsonPath().get("other_amount");
		Integer material_amount = purchaseResponse.jsonPath().get("material_amount");
		Integer total_payable = purchaseResponse.jsonPath().get("total_payable");
		Integer sequence = purchaseResponse.jsonPath().get("sequence");
		List<String> photos = purchaseResponse.jsonPath().getList("photos");
		String purchase_date = purchaseResponse.jsonPath().get("purchase_date");
		String vendor_reference_number = purchaseResponse.jsonPath().get("vendor_reference_number");
		String delete = purchaseResponse.jsonPath().get("delete");
		String approval_flag = purchaseResponse.jsonPath().get("approval_flag");
		String monkey_patch_materials = purchaseResponse.jsonPath().get("monkey_patch_materials");
		Integer monkey_patch_is_editable= purchaseResponse.jsonPath().get("monkey_patch_is_editable");
		Integer monkey_patch_paid_amount= purchaseResponse.jsonPath().get("monkey_patch_paid_amount");
		String monkey_patch_status = purchaseResponse.jsonPath().get("monkey_patch_status");
		Integer can_edit = purchaseResponse.jsonPath().get("meta_data.can_edit");
		Integer can_update_approval_flag = purchaseResponse.jsonPath().get("can_update_approval_flag");
		String approval_comment = purchaseResponse.jsonPath().get("approval_comment");
		String approved_by = purchaseResponse.jsonPath().get("approved_by");
		Integer due_days = purchaseResponse.jsonPath().get("due_days");
		String ship_to_address_id = purchaseResponse.jsonPath().get("ship_to_address_id");
		String bill_to_address_id = purchaseResponse.jsonPath().get("bill_to_address_id");
		String ship_from_address_id = purchaseResponse.jsonPath().get("ship_from_address_id");
		String bill_from_address_id = purchaseResponse.jsonPath().get("bill_from_address_id");
		String due_date = purchaseResponse.jsonPath().get("due_date");
		String pre_tax_deduction_amount = purchaseResponse.jsonPath().get("pre_tax_deduction_amount");
		String post_tax_deduction_amount = purchaseResponse.jsonPath().get("post_tax_deduction_amount");
		Integer deduction_amount = purchaseResponse.jsonPath().get("deduction_amount");
		Integer net_amount = purchaseResponse.jsonPath().get("net_amount");
		Integer is_roundoff = purchaseResponse.jsonPath().get("is_roundoff");
		Integer other_amount_gst_percentage = purchaseResponse.jsonPath().get("other_amount_gst_percentage");
		Integer other_amount_gst_amount = purchaseResponse.jsonPath().get("other_amount_gst_amount");
		String other_amount_text = purchaseResponse.jsonPath().get("other_amount_text");
		
		
		if(materialPurchaseId != null) {
			System.out.println("material purchase id :" + materialPurchaseId);
		} else {
			Assert.fail("material purchase id is null or empty");
		}
		
		if(creatorCompanyUserId != null) {
			System.out.println("created company user id :" + creatorCompanyUserId);
		} else {
			Assert.fail("created company user id is null or empty");
		}
		
		if(partyCompanyUserId != null) {
			System.out.println("party company user id :" + partyCompanyUserId);
		} else{
			Assert.fail("party company user id is null or empty");
		}
		
		if(materialpurchasePayload.getProject_id().equals(projectId)) {
			System.out.println("project id is match :" + projectId);
		} else{
			Assert.fail("project id is missmatch");
		}
		
		if(invoiceId != null) {
			System.out.println("material purchase invoice id :" + invoiceId);
		} else {
			Assert.fail("material purchase invoice id is null or empty");
		}
		
		List<String> materialIds = purchaseResponse.jsonPath().getList("material_ids");
		if(materialIds != null) {
			int materialitemCount = materialIds.size();
			if(materialitemCount == materialpurchasePayload.getMaterials().size()) {
				System.out.println("material count is match with material ids count");
			} else {
				Assert.fail("material count is not match with material ids");
			}
		} else {
			Assert.fail("matreial ids null");
		}
		
		Map<String, Object> invoice = purchaseResponse.jsonPath().getMap("monkey_patch_invoice");
		String invoiceCompanyId = (String) invoice.get("company_id");
		String invoiceCreator_company_user_id = (String) invoice.get("creator_company_user_id");
		String invoiceParty_company_user_id = (String) invoice.get("party_company_user_id");
		String invoiceProject_id = (String) invoice.get("project_id");
		String invoiceCategory_id = (String) invoice.get("category_id");
		String invoiceSub_category_id = (String) invoice.get("sub_category_id");
		String invoiceFeature_type = (String) invoice.get("feature_type");
		String invoiceFeature_id = (String) invoice.get("feature_id");
		String invoice_type= (String) invoice.get("invoice_type");
		String invoiceStatus = (String) invoice.get("status");
		Integer invoiceTotal_payable = (Integer) invoice.get("total_payable");
		Integer invoicePaid_amount = (Integer) invoice.get("paid_amount");
		Integer invoiceSequence = (Integer) invoice.get("sequence");
		Integer invoiceDelete = (Integer) invoice.get("delete");
		String invoice_date = (String) invoice.get("invoice_date");
		String incoiceCreated = (String) invoice.get("created");
		String invoiceUpdated = (String) invoice.get("updated");
		String invoiceApproval_flag = (String) invoice.get("approval_flag"); 
		
		Map<String, Object> invoiceSettlement = (Map<String, Object>) invoice.get("monkey_patch_settlement");
		String settlementId = (String) invoiceSettlement.get("id");
		String settlementCompany_id = (String) invoiceSettlement.get("company_id");
		String settlementCreator_company_user_id = (String) invoiceSettlement.get("creator_company_user_id");
		String settlementParty_company_user_id = (String) invoiceSettlement.get("party_company_user_id");
		String settlementProject_id = (String) invoiceSettlement.get("project_id");
		String settlementInvoiceId = (String) invoiceSettlement.get("invoice_id");
		String settlementCashbooktransaction_id = (String) invoiceSettlement.get("cashbooktransaction_id");
		Integer settled_amount = (Integer) invoiceSettlement.get("settled_amount");
		Integer settlementDelete = (Integer) invoiceSettlement.get("delete");
		String settlementCreated = (String) invoiceSettlement.get("created");
		String settlementUpdated = (String) invoiceSettlement.get("updated");
		String monkey_patch_settlement_date = (String) invoiceSettlement.get("monkey_patch_settlement_date");
		
	}

}
