package com.onsite.transaction.materialPurchase_test;

import static io.restassured.RestAssured.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_request.Material;
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
	public void addMaterialPurchase(MaterialPurchaseRequest materialpurchasePayload) throws Exception, DatabindException, IOException {

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

		int responseStatusCode = purchaseResponse.getStatusCode();
		if(responseStatusCode == 200) {
			purchaseResponse.then().assertThat().body(
					JsonSchemaValidator.matchesJsonSchemaInClasspath(
							"responseSchema_files/materialPurchaseResponseSchema.json"));
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
		Double discount = purchaseResponse.jsonPath().getDouble("discount");   
		Double gst_amount = purchaseResponse.jsonPath().getDouble("gst_amount");
		Double other_amount = purchaseResponse.jsonPath().getDouble("other_amount");
		Double material_amount = purchaseResponse.jsonPath().getDouble("material_amount");
		Double total_payable = purchaseResponse.jsonPath().getDouble("total_payable");
		Integer sequence = purchaseResponse.jsonPath().get("sequence");
		List<String> photos = purchaseResponse.jsonPath().getList("photos");
		List<String> materialIds = purchaseResponse.jsonPath().getList("material_ids");
		String purchase_date = purchaseResponse.jsonPath().get("purchase_date");
		String vendor_reference_number = purchaseResponse.jsonPath().get("vendor_reference_number");
		Integer delete = purchaseResponse.jsonPath().get("delete");
		String approval_flag = purchaseResponse.jsonPath().get("approval_flag");
		String monkey_patch_materials = purchaseResponse.jsonPath().get("monkey_patch_materials");
		Integer monkey_patch_is_editable= purchaseResponse.jsonPath().get("monkey_patch_is_editable");
		Integer monkey_patch_paid_amount= purchaseResponse.jsonPath().get("monkey_patch_paid_amount");
		//String monkey_patch_status = purchaseResponse.jsonPath().get("monkey_patch_status");
		Integer can_edit = purchaseResponse.jsonPath().get("meta_data.can_edit");
		//Integer can_update_approval_flag = purchaseResponse.jsonPath().get("can_update_approval_flag");
		//String approval_comment = purchaseResponse.jsonPath().get("approval_comment");
		//String approved_by = purchaseResponse.jsonPath().get("approved_by");
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

		ObjectMapper writemapper = new ObjectMapper();

		String deductionBulkAddFile = "src/test/resources/testdata_deductionentry/deductionEntryBulkAdd.json";
		String deductionEntryDataFile = "src/test/resources/testdata_deductionentry/deduction_entry_data.json";

		File bulkFile = new File(deductionBulkAddFile);
		File entryFile = new File(deductionEntryDataFile);

		Map<String, Object> deductionBulkAdd = new HashMap<>();
		Map<String, Object> deductionEntryData = new HashMap<>();

		if (responseStatusCode == 200 && materialPurchaseId != null && projectId != null) {
			try {
				if (bulkFile.exists()) {
					deductionBulkAdd = writemapper.readValue(bulkFile, Map.class);
				}
				if (entryFile.exists()) {
					deductionEntryData = writemapper.readValue(entryFile, Map.class);
				}
				deductionBulkAdd.put("feature_id", materialPurchaseId);
				deductionBulkAdd.put("project_id", projectId);
				deductionEntryData.put("project_id", projectId);

				writemapper.writerWithDefaultPrettyPrinter().writeValue(bulkFile, deductionBulkAdd);

				writemapper.writerWithDefaultPrettyPrinter().writeValue(entryFile, deductionEntryData);

				System.out.println("JSON files updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("JSON update failed: " + e.getMessage());
			}
		} else {
			System.out.println("JSON file not updated due to invalid response");
		}

		if(materialPurchaseId != null) {
			System.out.println("material purchase id :" + materialPurchaseId);
		} else {
			Assert.fail("material purchase id is null");
		}

		if(companyId != null) {
			System.out.println("companyId :" + companyId);
		} else {
			Assert.fail("company id is null");
		}

		if(creatorCompanyUserId != null) {
			System.out.println("creatorCompanyUserId :" + creatorCompanyUserId);
		} else {
			Assert.fail("creator company user id is null");
		}

		if(partyCompanyUserId != null) {
			System.out.println("partyCompanyUserId : " + partyCompanyUserId);
		} else {
			Assert.fail("party company user is is null");
		}

		if(invoiceId != null) {
			System.out.println("invoiceId : " + invoiceId);
		} else {
			System.out.println("invoiceId is null or empty");
		}

		if(sequence != null) {
			System.out.println("sequence :" + sequence);
		} else {
			Assert.fail("sequence is null");
		}

		if(projectId != null && !projectId.isEmpty()) {
			Assert.assertEquals(projectId, materialpurchasePayload.getProject_id(), "project id is mismatch");
			System.out.println("project id :" + projectId);
		} else {
			Assert.fail("project id is null or empty");
		}

		if(discount != null) {
			Assert.assertEquals(discount, materialpurchasePayload.getDiscount(), "discount is mismatch");
			System.out.println("discount :" + discount);
		} else {
			System.out.println("discount is null or empty");
		}

		Double expectedGstAmount = 0.0;
		if(materialpurchasePayload.getMaterials() != null) {
			for(Material matGstAmount : materialpurchasePayload.getMaterials()) {
				Double unitPrice = matGstAmount.getUnit_price();
				Double qty = matGstAmount.getQuantity();
				Double discountAmount = matGstAmount.getDiscount_amount();
				Double gstPercent = matGstAmount.getGst_percent();

				expectedGstAmount += (((unitPrice*qty)-discountAmount)*gstPercent)/100;
			}
			if(expectedGstAmount.equals(gst_amount)) {
				System.out.println("expected gstAmount :" + expectedGstAmount + ": actual gst_amount :" + gst_amount);
			} else {
				Assert.fail("expected gstAmount :" + expectedGstAmount + ": with actual gst_Amount not match :" + gst_amount);
			}
		}else {
			System.out.println("gst_amount is null or empty");
		}

		if(other_amount != null) {
			Assert.assertEquals(other_amount, materialpurchasePayload.getOther_amount(), "other amount is mismatch");
			System.out.println("other_amount :" + other_amount);
		} else {
			System.out.println("other_amount is null or empty");
		}

		Double expectedMaterialAmount = 0.0;
		if(materialpurchasePayload.getMaterials() != null) {
			for(Material matAmount : materialpurchasePayload.getMaterials()) {
				Double qty = matAmount.getQuantity();
				Double unitPrice = matAmount.getUnit_price();
				Double gstAmount = matAmount.getGst_amount();
				Double discountAmount = matAmount.getDiscount_amount();

				expectedMaterialAmount += (qty*unitPrice)-discountAmount+gstAmount;
			}
			if(material_amount.equals(expectedMaterialAmount)) {
				System.out.println("expected material_amount :" + expectedMaterialAmount + ": actual material amount :" + material_amount);
			} else {
				Assert.fail("material_amount not match with expected_material_amount :" + expectedMaterialAmount + material_amount);
			}	
		} else {
			Assert.fail("material_amount is null or empty");
		}

		Double expectedTotalPayble = 0.0;
		if(total_payable != null) {
			Double materialAmount = expectedMaterialAmount;
			Double purchaseDiscount = materialpurchasePayload.getDiscount();
			Double purchaseGst = materialpurchasePayload.getGst_amount();
			Double purchaseOtherAmount = materialpurchasePayload.getOther_amount();
			Double purchaseOtherGstAmount = materialpurchasePayload.getOther_amount_gst_amount();

			expectedTotalPayble = expectedMaterialAmount-purchaseDiscount+purchaseOtherAmount+purchaseOtherGstAmount;
			if(total_payable.equals(expectedTotalPayble)) {
				System.out.println("expected expectedTotalPayble :" + expectedTotalPayble + ": actual total_payable amount :" + total_payable);
			} else {
				Assert.fail("total_amount not match with expected_total_amount :" + total_payable + expectedTotalPayble);
			}
		} else {
			Assert.fail("total_payable is null or empty");
		}

		Double expectedNetAmount = 0.0;
		if(net_amount != null) {
			Double materialAmount = expectedMaterialAmount;
			Double purchaseDiscount = materialpurchasePayload.getDiscount();
			Double purchaseGst = materialpurchasePayload.getGst_amount();
			Double purchaseOtherAmount = materialpurchasePayload.getOther_amount();
			Double purchaseOtherGstAmount = materialpurchasePayload.getOther_amount_gst_amount();

			expectedNetAmount = expectedMaterialAmount-purchaseDiscount+purchaseOtherAmount+purchaseOtherGstAmount;
			if(expectedNetAmount.equals(net_amount)) {
				System.out.println("expectedNetAmount :" + expectedNetAmount + ": actual net_amount :" + net_amount);
			} else {
				Assert.fail("net_amount not match with expectedNetAmount :" + net_amount + expectedNetAmount);
			}
		} else {
			Assert.fail("net_amount is null");
		}

		if(purchase_date != null && !purchase_date.isEmpty()) {
			Assert.assertEquals(purchase_date, materialpurchasePayload.getPurchase_date(), "purchase date is mismatch");
			System.out.println("purchase_date :" + purchase_date);
		} else {
			Assert.fail("purchase_date is null or empty");
		}

		if(materialIds != null && !materialIds.isEmpty()) {
			Assert.assertEquals(
					materialIds.size(),
					materialpurchasePayload.getMaterials().size(), 
					"material count not match");
			System.out.println("materialIds :" + materialIds);
		} else {
			System.out.println("material ids is null or empty");
		}

		if(vendor_reference_number != null) {
			Assert.assertFalse(vendor_reference_number.trim().isEmpty(), "vendor refrence number is empty");
			System.out.println("vendor_reference_number :" + vendor_reference_number);
		} else {
			System.out.println("vendor refrence number is not present");
		}

		if(delete != null) {
			Assert.assertEquals(delete.intValue(), 0, "delete is 0 at creation time");
			System.out.println("delete :" + delete);
		} else {
			System.out.println("delete is null at creation time");
		}

		List<String> approvalFlagList = List.of("approved", "pending", "rejected", "auto_approved");
		if(approval_flag != null) {
			Assert.assertTrue(approvalFlagList.contains(approval_flag.toLowerCase()),
					"approval flag invalid" + approval_flag);
			System.out.println("approval_flag :" + approval_flag);
		} else {
			System.out.println("approval flg is not present");
		}

		if (photos != null && materialpurchasePayload.getPhotos() != null) {
			Assert.assertEquals(photos.size(), materialpurchasePayload.getPhotos().size(),
					"photos count not match with payload");
			System.out.println("photos :" + photos);
		} else {
			System.out.println("photos is null");
		}

		if(monkey_patch_materials != null) {
			Assert.assertFalse(monkey_patch_materials.trim().isEmpty(), "monkey_patch_materials should not be empty");
			System.out.println("monkey_patch_materials: " + monkey_patch_materials);
		} else {
			System.out.println("monkey_patch_materials is null allowed at creation");
		}

		if(monkey_patch_is_editable != null) {
			Assert.assertEquals(monkey_patch_is_editable.intValue(), 0, "monkey_patch_is_editable is 0 at cretion time");
			System.out.println("monkey_patch_is_editable :" + monkey_patch_is_editable);
		} else {
			Assert.fail("monkey_patch_is_editable field is null");
		}

		if(monkey_patch_paid_amount != null) {
			Assert.assertTrue(monkey_patch_paid_amount >= 0,
					"paid amount should not be negative" + monkey_patch_paid_amount);
			System.out.println("monkey_patch_paid_amount :" + monkey_patch_paid_amount);
		} else {
			Assert.fail("monkey_patch_paid_amount field is null");
		}

		if(can_edit != null) {
			Assert.assertEquals(can_edit.intValue(), 0, "can_edit should be 0 at creation time");
			System.out.println("can_edit :" + can_edit);
		} else {
			Assert.fail("can_edit filed is null");
		}

		if(due_days != null) {
			Assert.assertTrue(due_days >= 0, "due days should not be negative" + due_days);
			System.out.println("due_days : " + due_days);
		} else {
			Assert.fail("due days is null");
		}

		if(ship_to_address_id != null && !ship_to_address_id.isEmpty()) {
			Assert.assertFalse(ship_to_address_id.trim().isEmpty(), "ship_to_address_id not be empty at creation time");
			System.out.println("ship_to_address_id : " + ship_to_address_id);
		} else {
			System.out.println("ship_to_address_id is empty at creation time");
		}

		if(bill_to_address_id != null && !bill_to_address_id.isEmpty()) {
			Assert.assertFalse(bill_to_address_id.trim().isEmpty(), "bill_to_address_id not be empty at creation time");
			System.out.println("bill_to_address_id : " + bill_to_address_id);
		} else {
			System.out.println("bill_to_address_id is empty at creation time");
		}

		if(ship_from_address_id != null && !ship_from_address_id.isEmpty()) {
			Assert.assertFalse(ship_from_address_id.trim().isEmpty(), "ship_from_address_id not be empty at creation time");
			System.out.println("ship_from_address_id : " + ship_from_address_id);
		} else {
			System.out.println("ship_from_address_id is empty at creation time");
		}

		if(bill_from_address_id != null && !bill_from_address_id.isEmpty()) {
			Assert.assertFalse(bill_from_address_id.trim().isEmpty(), "bill_from_address_id not be empty at creation time");
			System.out.println("bill_from_address_id : " + bill_from_address_id);
		} else {
			System.out.println("bill_from_address_id is empty at creation time");
		}

		if(due_date != null && !due_date.isEmpty()) {
			try {
				Instant.parse(due_date);
			} catch(Exception e) {
				Assert.fail("invaild due date format :" + due_date);
			}
			System.out.println("due_date : " + due_date);
		} else {
			Assert.fail("due date is null");
		}

		if(pre_tax_deduction_amount != null) {
			Assert.assertTrue(pre_tax_deduction_amount >= 0, 
					"pre_tax_deduction_amount not be negative at creation time" + pre_tax_deduction_amount);
			System.out.println("pre_tax_deduction_amount : " + pre_tax_deduction_amount);
		} else {
			Assert.fail("pre_tax_deduction_amount is null");
		}

		if(post_tax_deduction_amount != null) {
			Assert.assertTrue(post_tax_deduction_amount >=0, 
					"post_tax_deduction_amount not be negative at creation time" + post_tax_deduction_amount);
			System.out.println("post_tax_deduction_amount : " + post_tax_deduction_amount);
		} else {
			Assert.fail("post_tax_deduction_amount is null");
		}

		if(deduction_amount != null) {
			Assert.assertTrue(deduction_amount >= 0, "deduction_amount not be negative at creation time" + deduction_amount);
			System.out.println("deduction_amount : " + deduction_amount);
		} else {
			Assert.fail("deduction_amount is null");
		}

		if(is_roundoff != null) {
			Assert.assertEquals(is_roundoff, materialpurchasePayload.getIs_roundoff(), "round off maount is mismatch");
			System.out.println("is_roundoff : " + is_roundoff);
		} else {
			Assert.fail("is_roundoff amount is null");
		}

		if(other_amount_gst_percentage != null) {
			Assert.assertEquals(other_amount_gst_percentage, materialpurchasePayload.getOther_amount_gst_percentage(), "other_amount_gst_percentage is mismatch");
			System.out.println("other_amount_gst_percentage : " + other_amount_gst_percentage);
		} else {
			Assert.fail("other_amount_gst_percentage is null");
		}

		if(other_amount_gst_amount != null) {
			Assert.assertEquals(other_amount_gst_amount, materialpurchasePayload.getOther_amount_gst_amount(), "materialpurchasePayload is mismatch");
			System.out.println("other_amount_gst_amount : " + other_amount_gst_amount);
		} else {
			Assert.fail("materialpurchasePayload is null");
		}

		if(other_amount_text != null && !other_amount_text.isEmpty()) {
			Assert.assertEquals(other_amount_text, materialpurchasePayload.getOther_amount_text(), "materialpurchasePayload is missmatch");
			System.out.println("other_amount_text : " + other_amount_text);
		} else {
			System.out.println("other_amount_text is null or empty");
		}



		//
		//		Map<String, Object> invoice = purchaseResponse.jsonPath().getMap("monkey_patch_invoice");
		//		String invoiceCompanyId = (String) invoice.get("company_id");
		//		String invoiceCreator_company_user_id = (String) invoice.get("creator_company_user_id");
		//		String invoiceParty_company_user_id = (String) invoice.get("party_company_user_id");
		//		String invoiceProject_id = (String) invoice.get("project_id");
		//		String invoiceCategory_id = (String) invoice.get("category_id");
		//		String invoiceSub_category_id = (String) invoice.get("sub_category_id");
		//		String invoiceFeature_type = (String) invoice.get("feature_type");
		//		String invoiceFeature_id = (String) invoice.get("feature_id");
		//		String invoice_type= (String) invoice.get("invoice_type");
		//		String invoiceStatus = (String) invoice.get("status");
		//		Integer invoiceTotal_payable = (Integer) invoice.get("total_payable");
		//		Integer invoicePaid_amount = (Integer) invoice.get("paid_amount");
		//		Integer invoiceSequence = (Integer) invoice.get("sequence");
		//		Integer invoiceDelete = (Integer) invoice.get("delete");
		//		String invoice_date = (String) invoice.get("invoice_date");
		//		String incoiceCreated = (String) invoice.get("created");
		//		String invoiceUpdated = (String) invoice.get("updated");
		//		String invoiceApproval_flag = (String) invoice.get("approval_flag"); 
		//
		//		Map<String, Object> invoiceSettlement = (Map<String, Object>) invoice.get("monkey_patch_settlement");
		//		String settlementId = (String) invoiceSettlement.get("id");
		//		String settlementCompany_id = (String) invoiceSettlement.get("company_id");
		//		String settlementCreator_company_user_id = (String) invoiceSettlement.get("creator_company_user_id");
		//		String settlementParty_company_user_id = (String) invoiceSettlement.get("party_company_user_id");
		//		String settlementProject_id = (String) invoiceSettlement.get("project_id");
		//		String settlementInvoiceId = (String) invoiceSettlement.get("invoice_id");
		//		String settlementCashbooktransaction_id = (String) invoiceSettlement.get("cashbooktransaction_id");
		//		Integer settled_amount = (Integer) invoiceSettlement.get("settled_amount");
		//		Integer settlementDelete = (Integer) invoiceSettlement.get("delete");
		//		String settlementCreated = (String) invoiceSettlement.get("created");
		//		String settlementUpdated = (String) invoiceSettlement.get("updated");
		//		String monkey_patch_settlement_date = (String) invoiceSettlement.get("monkey_patch_settlement_date");

	}

}
