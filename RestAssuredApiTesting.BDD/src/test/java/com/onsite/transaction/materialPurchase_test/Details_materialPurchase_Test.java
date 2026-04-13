package com.onsite.transaction.materialPurchase_test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.io.File;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_request.Material;
import com.onsite.pojo_request.MaterialPurchaseRequest;
import com.onsite.pojo_response.MaterialPurchaseResponse;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.DataFile;

@Test
public class Details_materialPurchase_Test {

	@DataProvider(name = "purchaseData")
	public Object[][] getData() throws Exception {

	    String json = new String(java.nio.file.Files.readAllBytes(
	            Paths.get("src/test/resources/testdata_materialpurchase/details_materialPurchase.json")));

	    JSONObject obj = new JSONObject(json);
	    String materialpurchaseId = obj.getString("id");

	    return new Object[][] {
	        { materialpurchaseId }
	    };
	}
	
	@Test(dataProvider="purchaseData")
	public void detailsMaterialPurchase(String materialpurchaseId) {

	Response detailsMaterialPurchaseResponse = 
			given()
			.baseUri(ApiBasePath.BASE_URL)
			.header("Authorization", "Bearer " + AuthUtils.getToken())
			.contentType(ContentType.JSON)
			.pathParam("id", materialpurchaseId)
			.log().uri()

			.when()
			.get(MaterialPurchase.detail_materialPurchase)

			.then()
			.log().all()
			.extract().response();
	
	int responseStatusCode = detailsMaterialPurchaseResponse.getStatusCode();
	if(responseStatusCode == 200) {
		detailsMaterialPurchaseResponse.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchemaInClasspath(
						"responseSchema_files/materialPurchaseResponseSchema.json"));
		System.out.println("successfull response status code is :" + responseStatusCode);
	} else {
		System.out.println("failure response status code is :" + responseStatusCode);
	}

	String responseMessage = detailsMaterialPurchaseResponse.jsonPath().getString("message");
	if(responseMessage != null) {
		System.out.println("response successful message :" + responseMessage);
	} else {
		System.out.println("failure response message :" + responseMessage);
	}

	long responseTime = detailsMaterialPurchaseResponse.getTime();
	if(responseTime < 2000) {
		System.out.println("successfull response time is :" + responseTime);
	} else {
		System.out.println("failuer response time is :" + responseTime);
	}

	String materialPurchaseId = detailsMaterialPurchaseResponse.jsonPath().get("id");
	String companyId = detailsMaterialPurchaseResponse.jsonPath().get("company_id");
	String creatorCompanyUserId = detailsMaterialPurchaseResponse.jsonPath().get("creator_company_user_id");
	String partyCompanyUserId = detailsMaterialPurchaseResponse.jsonPath().get("party_company_user_id");
	String projectId = detailsMaterialPurchaseResponse.jsonPath().get("project_id");
	String invoiceId = detailsMaterialPurchaseResponse.jsonPath().get("invoice_id");
	Double discount = detailsMaterialPurchaseResponse.jsonPath().getDouble("discount");   
	Double gst_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("gst_amount");
	Double other_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("other_amount");
	Double material_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("material_amount");
	Double total_payable = detailsMaterialPurchaseResponse.jsonPath().getDouble("total_payable");
	Integer sequence = detailsMaterialPurchaseResponse.jsonPath().get("sequence");
	List<String> photos = detailsMaterialPurchaseResponse.jsonPath().getList("photos");
	List<String> materialIds = detailsMaterialPurchaseResponse.jsonPath().getList("material_ids");
	String purchase_date = detailsMaterialPurchaseResponse.jsonPath().get("purchase_date");
	String vendor_reference_number = detailsMaterialPurchaseResponse.jsonPath().get("vendor_reference_number");
	Integer delete = detailsMaterialPurchaseResponse.jsonPath().get("delete");
	String approval_flag = detailsMaterialPurchaseResponse.jsonPath().get("approval_flag");
	String monkey_patch_materials = detailsMaterialPurchaseResponse.jsonPath().get("monkey_patch_materials");
	Integer monkey_patch_is_editable= detailsMaterialPurchaseResponse.jsonPath().get("monkey_patch_is_editable");
	Integer monkey_patch_paid_amount= detailsMaterialPurchaseResponse.jsonPath().get("monkey_patch_paid_amount");
	//String monkey_patch_status = purchaseResponse.jsonPath().get("monkey_patch_status");
	Integer can_edit = detailsMaterialPurchaseResponse.jsonPath().get("meta_data.can_edit");
	//Integer can_update_approval_flag = purchaseResponse.jsonPath().get("can_update_approval_flag");
	//String approval_comment = purchaseResponse.jsonPath().get("approval_comment");
	//String approved_by = purchaseResponse.jsonPath().get("approved_by");
	Integer due_days = detailsMaterialPurchaseResponse.jsonPath().get("due_days");
	String ship_to_address_id = detailsMaterialPurchaseResponse.jsonPath().get("ship_to_address_id");
	String bill_to_address_id = detailsMaterialPurchaseResponse.jsonPath().get("bill_to_address_id");
	String ship_from_address_id = detailsMaterialPurchaseResponse.jsonPath().get("ship_from_address_id");
	String bill_from_address_id = detailsMaterialPurchaseResponse.jsonPath().get("bill_from_address_id");
	String due_date = detailsMaterialPurchaseResponse.jsonPath().get("due_date");
	Double pre_tax_deduction_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("pre_tax_deduction_amount");
	Double post_tax_deduction_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("post_tax_deduction_amount");
	Double deduction_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("deduction_amount");
	Double net_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("net_amount");
	Integer is_roundoff = detailsMaterialPurchaseResponse.jsonPath().get("is_roundoff");
	Double other_amount_gst_percentage = detailsMaterialPurchaseResponse.jsonPath().getDouble("other_amount_gst_percentage");
	Double other_amount_gst_amount = detailsMaterialPurchaseResponse.jsonPath().getDouble("other_amount_gst_amount");
	String other_amount_text = detailsMaterialPurchaseResponse.jsonPath().get("other_amount_text");
	
	ObjectMapper mapper = new ObjectMapper();
	MaterialPurchaseResponse materialPurchaseData = mapper.readValue(
			new File("src/test/resources/testdata_materialpurchase/details_materialPurchase.json"), 
			MaterialPurchaseResponse.class);
	
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
		Assert.assertEquals(projectId, materialPurchaseData.getProject_id(), "project id is mismatch");
		System.out.println("project id :" + projectId);
	} else {
		Assert.fail("project id is null or empty");
	}
	
	if(materialPurchaseData.getDiscount() == discount) {
		System.out.println("getDiscount : " + materialPurchaseData.getDiscount() + ": match with : " + discount);
	} else {
		Assert.fail("getDiscount :" + materialPurchaseData.getDiscount() + ": not match with : " + discount);
	}

	Double expectedGstAmount = 0.0;
	if(materialPurchaseData.getMaterials() != null) {
		for(Material matGstAmount : materialPurchaseData.getMaterials()) {
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

	Double expectedMaterialAmount = 0.0;
	if(materialPurchaseData.getMaterials() != null) {
		for(Material matAmount : materialPurchaseData.getMaterials()) {
			Double qty = matAmount.getQuantity();
			Double unitPrice = matAmount.getUnit_price();
			Double gstAmount = matAmount.getGst_amount();
			Double discountAmount = matAmount.getDiscount_amount();

			expectedMaterialAmount += ((qty*unitPrice)-discountAmount)+gstAmount;
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
		Double purchaseDiscount = materialPurchaseData.getDiscount();
		Double purchaseGst = materialPurchaseData.getGst_amount();
		Double purchaseOtherAmount = materialPurchaseData.getOther_amount();
		Double purchaseOtherGstAmount = materialPurchaseData.getOther_amount_gst_amount();

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
		Double purchaseDiscount = materialPurchaseData.getDiscount();
		Double purchaseGst = materialPurchaseData.getGst_amount();
		Double purchaseOtherAmount = materialPurchaseData.getOther_amount();
		Double purchaseOtherGstAmount = materialPurchaseData.getOther_amount_gst_amount();

		expectedNetAmount = expectedMaterialAmount-purchaseDiscount+purchaseOtherAmount+purchaseOtherGstAmount;
		if(expectedNetAmount.equals(net_amount)) {
			System.out.println("expectedNetAmount :" + expectedNetAmount + ": actual net_amount :" + net_amount);
		} else {
			Assert.fail("net_amount not match with expectedNetAmount :" + net_amount + expectedNetAmount);
		}
	} else {
		Assert.fail("net_amount is null");
	}

	if(other_amount != null) {
		Assert.assertEquals(other_amount, materialPurchaseData.getOther_amount(), "other amount is mismatch");
		System.out.println("other_amount :" + other_amount);
	} else {
		System.out.println("other_amount is null or empty");
	}
	
	if(purchase_date != null && !purchase_date.isEmpty()) {
		Assert.assertEquals(purchase_date, materialPurchaseData.getPurchase_date(), "purchase date is mismatch");
		System.out.println("purchase_date :" + purchase_date);
	} else {
		Assert.fail("purchase_date is null or empty");
	}

	if(materialIds != null && !materialIds.isEmpty()) {
		Assert.assertEquals(
				materialIds.size(),
				materialPurchaseData.getMaterials().size(), 
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

	if (photos != null && materialPurchaseData.getPhotos() != null) {
		Assert.assertEquals(photos.size(), materialPurchaseData.getPhotos().size(),
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
		Assert.assertEquals(is_roundoff, materialPurchaseData.getIs_roundoff(), "round off maount is mismatch");
		System.out.println("is_roundoff : " + is_roundoff);
	} else {
		Assert.fail("is_roundoff amount is null");
	}

	if(other_amount_gst_percentage != null) {
		Assert.assertEquals(other_amount_gst_percentage, materialPurchaseData.getOther_amount_gst_percentage(), "other_amount_gst_percentage is mismatch");
		System.out.println("other_amount_gst_percentage : " + other_amount_gst_percentage);
	} else {
		Assert.fail("other_amount_gst_percentage is null");
	}

	if(other_amount_gst_amount != null) {
		Assert.assertEquals(other_amount_gst_amount, materialPurchaseData.getOther_amount_gst_amount(), "materialpurchasePayload is mismatch");
		System.out.println("other_amount_gst_amount : " + other_amount_gst_amount);
	} else {
		Assert.fail("materialpurchasePayload is null");
	}

	if(other_amount_text != null && !other_amount_text.isEmpty()) {
		Assert.assertEquals(other_amount_text, materialPurchaseData.getOther_amount_text(), "materialpurchasePayload is missmatch");
		System.out.println("other_amount_text : " + other_amount_text);
	} else {
		System.out.println("other_amount_text is null or empty");
	}
	}
}
