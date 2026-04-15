package com.onsite.transaction.materialPurchase_test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_response.MaterialPurchaseResponse;
import com.onsite.utilities_page.AuthUtils;

@Test
public class Details_materialPurchase_Test {

	@DataProvider(name = "purchaseData")
	public Object[][] getData() throws Exception {

		String json = new String(java.nio.file.Files.readAllBytes(
				Paths.get("src/test/resources/testdata_materialpurchase/materialPurchaseData.json")));

		JSONObject obj = new JSONObject(json);
		String materialpurchaseId = obj.getString("id");

		return new Object[][] {
			{ materialpurchaseId }
		};
	}

	@Test(dataProvider="purchaseData")
	public void detailsMaterialPurchase(String materialpurchaseId) throws StreamReadException, DatabindException, IOException {

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
		List<Object> monkey_patch_materials = detailsMaterialPurchaseResponse.jsonPath().get("monkey_patch_materials");
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
		mapper.findAndRegisterModules();
		MaterialPurchaseResponse materialPurchaseData = mapper.readValue(
				new File("src/test/resources/testdata_materialpurchase/materialPurchaseData.json"), 
				MaterialPurchaseResponse.class);

		if(materialPurchaseData.getId() != null && materialPurchaseId != null && 
				materialPurchaseData.getId().equals(materialPurchaseId)) {
			System.out.println("material purchase id :" + materialPurchaseId);
		} else {
			Assert.fail("material purchase id is null");
		}

		if(materialPurchaseData.getCompany_id() != null && companyId != null && 
				materialPurchaseData.getCompany_id().equals(companyId)) {
			System.out.println("companyId :" + companyId);
		} else {
			Assert.fail("company id is null");
		}

		if(materialPurchaseData.getCreator_company_user_id() != null && creatorCompanyUserId != null && 
				materialPurchaseData.getCreator_company_user_id().equals(creatorCompanyUserId)) {
			System.out.println("creatorCompanyUserId :" + creatorCompanyUserId);
		} else {
			Assert.fail("creator company user id is null");
		}

		if(materialPurchaseData.getParty_company_user_id() != null && partyCompanyUserId != null && 
				materialPurchaseData.getParty_company_user_id().equals(partyCompanyUserId)) {
			System.out.println("partyCompanyUserId : " + partyCompanyUserId);
		} else {
			Assert.fail("party company user is is null");
		}

		if(materialPurchaseData.getInvoice_id() != null && invoiceId != null && 
				materialPurchaseData.getInvoice_id().equals(invoiceId)) {
			System.out.println("invoiceId : " + invoiceId);
		} else {
			System.out.println("invoiceId is null or empty");
		}

		if(sequence != null && Integer.valueOf(materialPurchaseData.getSequence()).equals(sequence)) {
			System.out.println("getSequence :" + materialPurchaseData.getSequence() + ": match with :" + sequence);
		} else {
			Assert.fail("getSequence :" + materialPurchaseData.getSequence() + ": not match with :" + sequence);
		}

		if(materialPurchaseData.getProject_id() != null && projectId != null && 
				materialPurchaseData.getProject_id().equals(projectId)) {
			System.out.println("project id :" + projectId + ": match with :" + materialPurchaseData.getProject_id());
		} else {
			Assert.fail("project id is null or empty or missmatch");
		}

		if(materialPurchaseData.getMaterial_ids() != null && materialIds != null &&
				materialPurchaseData.getMaterial_ids().length == materialIds.size()) {

			boolean isMatch = true;
			for(int i = 0; i < materialPurchaseData.getMaterial_ids().length; i++) {
				if(!materialPurchaseData.getMaterial_ids()[i].equals(materialIds.get(i))) {
					isMatch = false;
					break;
				}
			}
			if(isMatch) {
				System.out.println("getMaterial_ids : match with response");
			} else {
				Assert.fail("material ids values mismatch");
			}
		} else {
			Assert.fail("material ids is null or size mismatch");
		}

		if(discount != null && Math.abs(materialPurchaseData.getDiscount() - discount) < 0.01) {
			System.out.println("getDiscount : " + materialPurchaseData.getDiscount() + ": match with : " + discount);
		} else {
			Assert.fail("getDiscount :" + materialPurchaseData.getDiscount() + ": not match with : " + discount);
		}

		if(gst_amount != null && Math.abs(materialPurchaseData.getGst_amount() - gst_amount) < 0.01) {
			System.out.println("getGst_amount :" + materialPurchaseData.getGst_amount() + ": match with :" + gst_amount);
		} else {
			Assert.fail("getGst_amount :" + materialPurchaseData.getGst_amount() + ": not match with :" + gst_amount);
		}

		if(material_amount != null && Math.abs(materialPurchaseData.getMaterial_amount() - material_amount) < 0.01) {
			System.out.println("getMaterial_amount : " + materialPurchaseData.getMaterial_amount() + ": match with :" + material_amount);
		} else {
			Assert.fail("getMaterial_amount : " + materialPurchaseData.getMaterial_amount() + ": not match with :" + material_amount);
		}

		if(total_payable != null && Math.abs(materialPurchaseData.getTotal_payable() - total_payable) < 0.01) {
			System.out.println("getTotal_payable : " + materialPurchaseData.getTotal_payable() + ": match with :" + total_payable);
		} else {
			Assert.fail("getTotal_payable : " + materialPurchaseData.getTotal_payable() + ": not match with :" + total_payable);
		}

		if(other_amount != null && Math.abs(materialPurchaseData.getOther_amount() - other_amount) < 0.01) {
			System.out.println("getOther_amount : " + materialPurchaseData.getOther_amount() + ": match with :" + other_amount);
		} else {
			Assert.fail("getOther_amount : " + materialPurchaseData.getOther_amount() + ": not match with :" + other_amount);
		}

		if(purchase_date != null && materialPurchaseData.getPurchase_date() != null) {
			String responseDate = purchase_date.split("T")[0];
			String pojoDate = materialPurchaseData.getPurchase_date().toLocalDate().toString();

			if(pojoDate.equals(responseDate)) {
				System.out.println("getPurchase_date :" + pojoDate + ": match with :" + responseDate);
			} else {
				Assert.fail("purchase_date mismatch: expected " + responseDate + " but got " + pojoDate);
			}
		} else {
			Assert.fail("purchase_date is null or empty");
		}

		if(materialPurchaseData.getVendor_reference_number().equals(vendor_reference_number)) {
			System.out.println("getVendor_reference_number :" + materialPurchaseData.getVendor_reference_number() + ": match with : " + vendor_reference_number);
		} else {
			System.out.println("getVendor_reference_number :" + materialPurchaseData.getVendor_reference_number() + ": not match with : " + vendor_reference_number);
		}

		if(materialPurchaseData.getDelete() == delete) {
			System.out.println("getDelete :" + materialPurchaseData.getDelete() + ": match with : " + delete);
		} else {
			Assert.fail("getDelete :" + materialPurchaseData.getDelete() + ": not match with : " + delete);
		}

		if(materialPurchaseData.getApproval_flag() != null && approval_flag != null && 
				materialPurchaseData.getApproval_flag().equals(approval_flag)) {
			System.out.println("getApproval_flag :" + materialPurchaseData.getApproval_flag() + ": match with : " + approval_flag);
		} else {
			Assert.fail("getApproval_flag :" + materialPurchaseData.getApproval_flag() + ": not match with : " + approval_flag);
		}

		if(materialPurchaseData.getPhotos() != null && photos != null && materialPurchaseData.getPhotos().length == photos.size()) {
			boolean isMatch = true;
			for(int i = 0; i < materialPurchaseData.getPhotos().length; i++) {
				if(!materialPurchaseData.getPhotos()[i].equals(photos.get(i))) {
					isMatch = false;
					break;
				}
			}
			if(isMatch) {
				System.out.println("getPhotos : match with response");
			} else {
				Assert.fail("photos values mismatch");
			}
		} else if(
				(materialPurchaseData.getPhotos() == null || materialPurchaseData.getPhotos().length == 0) &&
				(photos == null || photos.isEmpty())) {
			// ✅ both empty case
			System.out.println("getPhotos : both are empty");
		} else {
			Assert.fail("getPhotos size mismatch or null");
		}

		if(monkey_patch_materials != null) {
			Assert.assertNotNull(monkey_patch_materials, "monkey_patch_materials should not be null in details API");
			System.out.println("monkey_patch_materials : " + monkey_patch_materials);
		} else {
			System.out.println("monkey_patch_materials is null (allowed case)");
		}

		if(monkey_patch_is_editable != null && 
				(monkey_patch_is_editable == 0 || monkey_patch_is_editable == 1)) {
			System.out.println("getMonkey_patch_is_editable valid value : " + monkey_patch_is_editable);
		} else {
			Assert.fail("getMonkey_patch_is_editable invalid value : " + monkey_patch_is_editable);
		}

		if(monkey_patch_paid_amount != null && materialPurchaseData.getMonkey_patch_paid_amount() == monkey_patch_paid_amount) {
			System.out.println("getMonkey_patch_paid_amount :" + materialPurchaseData.getMonkey_patch_paid_amount() + ": match with :" + monkey_patch_paid_amount);
		} else {
			Assert.fail("getMonkey_patch_paid_amount :" + materialPurchaseData.getMonkey_patch_paid_amount() + ": not match with :" + monkey_patch_paid_amount);
		}

		if(can_edit != null && (can_edit ==0 || can_edit ==1)) {
			System.out.println("can_edit valid value : " + can_edit);
		} else {
			Assert.fail("can_edit invalid value : " + can_edit);
		}

		if(materialPurchaseData.getDue_days() == due_days) {
			System.out.println("getDue_days : " + materialPurchaseData.getDue_days() + ": match with :" + due_days);
		} else {
			Assert.fail("due days is null");
		}

		if(ship_to_address_id != null && materialPurchaseData.getShip_from_address_id() != null && 
				materialPurchaseData.getShip_to_address_id().equals(ship_to_address_id)) {
			System.out.println("getShip_from_address_id : " + materialPurchaseData.getShip_to_address_id() + ": match with : " + ship_to_address_id);
		} else {
			System.out.println("getShip_from_address_id : " + materialPurchaseData.getShip_to_address_id() + ": not match with : " + ship_to_address_id);
		}

		if(bill_to_address_id != null && materialPurchaseData.getBill_to_address_id() != null && 
				materialPurchaseData.getBill_to_address_id().equals(bill_to_address_id)) {
			System.out.println("getBill_to_address_id : " + materialPurchaseData.getBill_to_address_id() + ": match with :" + bill_to_address_id);
		} else {
			System.out.println("getBill_to_address_id : " + materialPurchaseData.getBill_to_address_id() + ": not match with :" + bill_to_address_id);
		}

		if(ship_from_address_id != null && materialPurchaseData.getShip_from_address_id() != null && 
				materialPurchaseData.getShip_from_address_id().equals(ship_from_address_id)) {
			System.out.println("getShip_from_address_id : " + materialPurchaseData.getShip_from_address_id() + ": match with :" + ship_from_address_id);
		} else {
			System.out.println("getShip_from_address_id : " + materialPurchaseData.getShip_from_address_id() + ": not match with :" + ship_from_address_id);
		}

		if(bill_from_address_id != null && materialPurchaseData.getBil_from_address_id() != null && 
				materialPurchaseData.getBil_from_address_id().equals(bill_from_address_id)) {
			System.out.println("getBill_to_address_id : " + materialPurchaseData.getBil_from_address_id() + ": match with :" + bill_from_address_id);
		} else {
			System.out.println("getBill_to_address_id : " + materialPurchaseData.getBil_from_address_id() + ": not match with :" + bill_from_address_id);
		}

		if(due_date != null && materialPurchaseData.getDue_date() != null) {
			try {
				Instant apiDate = Instant.parse(due_date); // has Z
				Instant jsonDate = Instant.parse(materialPurchaseData.getDue_date() + "Z"); 
				// add Z manually because JSON me nahi hai

				if(apiDate.equals(jsonDate)) {
					System.out.println("getDue_date match : " + due_date);
				} else {
					Assert.fail("getDue_date mismatch : " + due_date + " vs " + materialPurchaseData.getDue_date());
				}

			} catch(Exception e) {
				Assert.fail("invalid due date format");
			}
		} else {
			Assert.fail("getDue_date is null");
		}
	}
}
