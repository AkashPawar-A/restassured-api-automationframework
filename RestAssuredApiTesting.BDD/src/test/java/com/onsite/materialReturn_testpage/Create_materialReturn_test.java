package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.pojo_request.Material;
import com.onsite.pojo_request.MaterialReturnRequest;
import com.onsite.pojo_response.MaterialReturn_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Create_materialReturn_test {

	private MaterialReturn_Response responseBody;
	private MaterialReturnRequest materialReturnPayload;
	private Response materialReturnResponse;
	private ObjectMapper mapper;
	private String loginCompanyId;

	@DataProvider(name="materialReturn")
	public Object[][] getData() throws IOException{

		Map<String, Object> materialItem = JsonUtils.readJson("src/test/resources/testdata_material/materialitem.json");
		Map<String, Object> materialReturnReq = JsonUtils.readJson("src/test/resources/testdata_materialReturn/add_materialReturn.json");

		List<Map<String, Object>> materialitemList = new ArrayList<>();
		materialitemList.add(materialItem);

		materialReturnReq.put("materials", materialitemList);
		System.out.println("final material return payload :" + materialReturnPayload);

		// REQUEST DESERIALIZATION - JSON → Request POJO
		mapper = new ObjectMapper();
		mapper.registerModules(new JavaTimeModule());
		materialReturnPayload = mapper.convertValue(materialReturnReq, MaterialReturnRequest.class);

		// REQUEST SERIALIZATION - Request POJO → JSON
		String finalPayload = mapper.writeValueAsString(materialReturnPayload);
		System.out.println(finalPayload);

		return new Object[][] {
			{ materialReturnPayload }
		};
	}

	@Test(dataProvider="materialReturn", priority=1)
	public void materialReturn(MaterialReturnRequest finalPayload) {

		loginCompanyId = CompanyContext.getCompanyId();

		materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().uri()

				.when()
				.post(MaterialReturn_Api.add_materialReturn)

				.then()
				.log().all()
				.extract().response();

		responseBody = materialReturnResponse.as(MaterialReturn_Response.class);

	}

	@Test(priority=2)
	public void validStatusCode() {

		int responseStatusCode = materialReturnResponse.getStatusCode();

		if(responseStatusCode != 200) {
			System.out.println("actual status code is 200 but found :" + responseStatusCode);
		}
		Assert.assertEquals(responseStatusCode, 200, "status code does not match with responseStatusCode");
	}

	@Test(priority=2)
	public void validResponseMessage() {

		String responseMessage = materialReturnResponse.jsonPath().getString("message");

		if(responseMessage == null || responseMessage.trim().isEmpty()) {
			System.out.println("Response message is not provided");
		} else {
			System.out.println("response message :" + responseMessage);
		}

		Assert.assertNotNull(responseMessage, "response message should not be null");
		Assert.assertFalse(responseMessage.trim().isEmpty(), "Response message should not be empty");
	}
	
	@Test(priority=3)
	public void validResponseTime() {

		long responseTime = materialReturnResponse.getTime();

		System.out.println("response time :" + responseTime + "ms");

		Assert.assertTrue(responseTime < 2000, "Response time is too long :" + responseTime + "ms");
	}

	@Test(priority=4)
	public void validResponseSchema() {

		materialReturnResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				"responseSchema_files/MaterialReturnResponse_schema.json"));
	}

	@Test(priority=5)
	public void validMaterialReturnId() {

		String materialReturnId = materialReturnResponse.jsonPath().getString("id");

		Assert.assertNotNull(materialReturnId, "material retunt id should not be null");
		Assert.assertFalse(materialReturnId.trim().isEmpty(), "material return id should not be empty");

		System.out.println("Material return id :" + materialReturnId);
	}

	@Test(priority=6)
	public void validCompanyId() {

		String companyId = materialReturnResponse.jsonPath().getString("company_id");

		Assert.assertNotNull(companyId, "company id should not be null");
		Assert.assertFalse(companyId.trim().isEmpty(), "company id should not be empty");

		Assert.assertEquals(companyId, loginCompanyId, "Company ID mismatch");
	}

	@Test(priority=7)
	public void validRemark() {

		String resRemark = materialReturnResponse.jsonPath().getString("remark");
		String reqRemark = materialReturnPayload.getRemark();

		if(reqRemark == null || reqRemark.trim().isEmpty()) {
			
			System.out.println("remark is not provided");
		} else {
			
			Assert.assertNotNull(resRemark, "Response remark should not be null");
			Assert.assertFalse(resRemark.trim().isEmpty(), "Response remark should not be empty");
			
			Assert.assertEquals(resRemark, reqRemark, "Response remark does not match the request remark");

			System.out.println("Request Remark  : " + reqRemark);
			System.out.println("Response Remark : " + resRemark);
		}	
	}

	@Test(priority=8)
	public void validPartyCompanyUserId() {

		String resPartyCompanyUserId = materialReturnResponse.jsonPath().getString("party_company_user_id");
		String reqPartyCompanyUserId = materialReturnPayload.getParty_company_user_id();

		if(reqPartyCompanyUserId == null || reqPartyCompanyUserId.trim().isEmpty()) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "can not find my company user", "incorrect validation message");
			System.out.println("errorMessage : " + errorMessage);
		} else {

			Assert.assertNotNull(resPartyCompanyUserId, "response party company user id should not be null");
			Assert.assertFalse(resPartyCompanyUserId.trim().isEmpty(), "response party company user id should not be empty");
			Assert.assertEquals(resPartyCompanyUserId, reqPartyCompanyUserId, "party company user id missmatch");

			System.out.println("Request PartyCompanyUserId  : " + reqPartyCompanyUserId);
			System.out.println("Response PartyCompanyUserId : " + resPartyCompanyUserId);
		}
	}

	@Test(priority=9)
	public void validCreatorCompanyUserId() {

		String resCreatorCompanyUserId = materialReturnResponse.jsonPath().getString("creator_company_user_id");

		Assert.assertNotNull(resCreatorCompanyUserId, "Creator Company User ID should not be null");
		Assert.assertFalse(resCreatorCompanyUserId.trim().isEmpty(), "Creator Company User ID should not be empty");
		
		System.out.println("creator company user id :" + resCreatorCompanyUserId);
	}

	@Test(priority=10)
	public void validProjectId() {

		String resProjectId = materialReturnResponse.jsonPath().getString("project_id");
		String reqProjectId = materialReturnPayload.getProject_id();

		if(reqProjectId == null || reqProjectId.trim().isEmpty()) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "Invalid project data", "incorrect validation message");
			System.out.println("project id error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resProjectId, "Response Project ID should not be null");
			Assert.assertFalse(resProjectId.trim().isEmpty(), "Response Project ID should not be empty");
			Assert.assertEquals(resProjectId, reqProjectId, "project id missmatch");

			System.out.println("Request ProjectId  : " + reqProjectId);
			System.out.println("Response ProjectId : " + resProjectId);
		} 
	}

	@Test(priority=11)
	public void validInvoiceId() {

		String resInvoiceId = materialReturnResponse.jsonPath().getString("invoice_id");

		if(resInvoiceId != null && !resInvoiceId.trim().isEmpty()) {
			System.out.println("invoice id :" + resInvoiceId);
		} else {
			Assert.fail("invoice id is null or empty");
		}	
	}

	@Test(priority=12)
	public void validMaterial() {

		List<Material> resMaterialList = materialReturnResponse.jsonPath().getList("material", Material.class);

		Material[] reqMaterialList = materialReturnPayload.getMaterials();

		Assert.assertNotNull(reqMaterialList, "Request material list should not be null");
		Assert.assertTrue(reqMaterialList.length > 0, "Request material list should not be empty");
		
		Assert.assertNotNull(resMaterialList, "Response material list should not be null");
		Assert.assertFalse(resMaterialList.isEmpty(), "Response material list should not be empty");
		
		Assert.assertEquals(resMaterialList.size(), reqMaterialList.length, "count is missmatch");

		for (int i = 0; i < reqMaterialList.length; i++) {

			Material resMaterial = resMaterialList.get(i);
			Material reqMaterial = reqMaterialList[i];
			
			// Object validation
			Assert.assertNotNull(reqMaterial, "Request material object should not be null at index " + i);
	        Assert.assertNotNull(resMaterial, "Response material object should not be null at index " + i);
	        
	        // Field validation
	        Assert.assertNotNull(reqMaterial.getMaterial_item_id(), "Request Material ID should not be null");
	        Assert.assertNotNull(resMaterial.getMaterial_item_id(), "Response Material ID should not be null");
	        Assert.assertNotNull(reqMaterial.getQuantity(), "Request Quantity should not be null");
	        Assert.assertNotNull(resMaterial.getQuantity(), "Response Quantity should not be null");
	        Assert.assertNotNull(reqMaterial.getUnit_price(), "Request Unit Price should not be null");
	        Assert.assertNotNull(resMaterial.getUnit_price(), "Response Unit Price should not be null");
	        
	        // Negative value validation
	        Assert.assertTrue(reqMaterial.getQuantity() >= 0, "Request Quantity should not be negative");
	        Assert.assertTrue(resMaterial.getQuantity() >= 0, "Response Quantity should not be negative");
	        Assert.assertTrue(reqMaterial.getUnit_price() >= 0, "Request Unit Price should not be negative");
	        Assert.assertTrue(resMaterial.getUnit_price() >= 0, "Response Unit Price should not be negative");
	        
	        // Value validation
	        Assert.assertEquals(resMaterial.getMaterial_item_id(), reqMaterial.getMaterial_item_id(), "Material ID mismatch at index " + i);
	        Assert.assertEquals(resMaterial.getQuantity(), reqMaterial.getQuantity(), "Quantity mismatch at index " + i);
	        Assert.assertEquals(resMaterial.getUnit_price(), reqMaterial.getUnit_price(), 0.01, "Unit Price mismatch at index " + i);
			
	        // Expected material amount
	        Double expectedMaterialAmount = reqMaterial.getQuantity() * reqMaterial.getUnit_price();
	        Double actualMaterialAmount = resMaterial.getQuantity() * resMaterial.getUnit_price();
	        Assert.assertEquals(actualMaterialAmount, expectedMaterialAmount, 0.01, "Material Amount mismatch at index " + i);
	        
	        System.out.println("Material Index      : " + i);
	        System.out.println("Material Item ID    : " + reqMaterial.getMaterial_item_id());
	        System.out.println("Quantity            : " + reqMaterial.getQuantity());
	        System.out.println("Unit Price          : " + reqMaterial.getUnit_price());
	        System.out.println("Expected Amount     : " + expectedMaterialAmount);
	        System.out.println("Response Amount     : " + actualMaterialAmount);
		}	
	}

	private Double reqMaterialAmount;
	private Double resMaterialAmount;
	@Test(priority=13)
	public void validMaterialAmount() {

		resMaterialAmount = materialReturnResponse.jsonPath().getDouble("material_amount");
		reqMaterialAmount = materialReturnPayload.getMaterial_amount();

		Material[] reqMaterialList = materialReturnPayload.getMaterials();

		if(reqMaterialAmount == null) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "material amount required", "incorrect validation message");
			System.out.println("material amount error message :" + errorMessage);
		} else {
			
			Assert.assertNotNull(reqMaterialList, "Material list should not be null");
			Assert.assertTrue(reqMaterialList.length > 0, "Material list should not be empty");
			
			Double expectedMaterialAmount = 0.0;
			
			for(Material materialItem : reqMaterialList) {
				
				Assert.assertNotNull(materialItem.getQuantity(), "Material item quantity should not be null");
				Assert.assertNotNull(materialItem.getUnit_price(), "Material item unit price should not be null");
				
	            Double itemAmount = materialItem.getQuantity() * materialItem.getUnit_price();

	            expectedMaterialAmount += itemAmount;
			}
			
			// Response validation
	        Assert.assertNotNull(resMaterialAmount, "Response material amount should not be null");
	        Assert.assertTrue(resMaterialAmount >= 0, "Response material amount should not be negative");

	        // Expected vs Request
	        Assert.assertEquals(reqMaterialAmount, expectedMaterialAmount, 0.01, "Request material amount mismatch");
	        
	        // Expected vs Response
	        Assert.assertEquals(resMaterialAmount, expectedMaterialAmount, 0.01, "Response material amount mismatch");

	        // Request vs Response
	        Assert.assertEquals(reqMaterialAmount, resMaterialAmount, 0.01, "Request and Response material amount mismatch");
	        
			System.out.println("Expected Material Amount : " + expectedMaterialAmount);
			System.out.println("Request Material Amount  : " + reqMaterialAmount);
			System.out.println("Response Material Amount : " + resMaterialAmount);
		}
	}

	private Double reqDiscount;
	private Double resDiscount;
	@Test(priority=14)
	public void validDiscount() {

		resDiscount = materialReturnResponse.jsonPath().getDouble("discount");
		reqDiscount = materialReturnPayload.getDiscount();

		if(reqDiscount == null) {
			System.out.println("discount is not provided");
		} else {
			Assert.assertNotNull(resDiscount, "discount should not be null");
			Assert.assertEquals(resDiscount, reqDiscount, "discout is missmatch");

			System.out.println("Request discount :" + reqDiscount);
			System.out.println("Response discount :" + resDiscount);	
		}	
	}

	private Double reqGstAmount;
	private Double resGstAmount;
	@Test(priority=15)
	public void validGstAmount() {

		resGstAmount = materialReturnResponse.jsonPath().getDouble("gst_amount");
		reqGstAmount = materialReturnPayload.getGst_amount();

		if(reqGstAmount == null) {

			System.out.println("gst amount is not provided");
		} else {

			Assert.assertNotNull(resGstAmount, "gst amount should not be null");
			Assert.assertEquals(resGstAmount, reqGstAmount, "gst amount is missmatch");

			System.out.println("Request gst Amount :" + reqGstAmount);
			System.out.println("Response Gst Amount :" + resGstAmount);
		}	
	}

	private Double reqOtherAmount;
	private Double resOtherAmount;
	@Test(priority=16)
	public void validOtherAmount() {

		resOtherAmount = materialReturnResponse.jsonPath().getDouble("other_amount");
		reqOtherAmount = materialReturnPayload.getOther_amount();

		if(reqOtherAmount == null) {

			System.out.println("other amount is not provided");
		} else {

			Assert.assertNotNull(resOtherAmount, "other amount should not be null");
			Assert.assertEquals(resOtherAmount, reqOtherAmount, "other amount is missmatch");

			System.out.println("Request Other amount :" + reqOtherAmount);
			System.out.println("Response Other Amoutn :" + resOtherAmount);	
		}
	}

	@Test(priority=17)
	public void validTotalPayable() {

		Double expectedReqTotalPayable = 0.0;
		Double actualResTotalPayable = 0.0;

		Double resTotalPayable = materialReturnResponse.jsonPath().getDouble("total_payable");
		Double reqTotalPayable = materialReturnPayload.getTotal_payable();

		Assert.assertEquals(expectedReqTotalPayable, actualResTotalPayable, "total payable amount is different");

		if(reqTotalPayable == null) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "total payable required", "incorrect validation message");
			System.out.println("total payable maount error message");
		} else {

			expectedReqTotalPayable = ((reqMaterialAmount-reqDiscount)+reqOtherAmount+reqGstAmount);
			actualResTotalPayable = ((resMaterialAmount-resDiscount)+resOtherAmount+resGstAmount);
			Assert.assertEquals(expectedReqTotalPayable, actualResTotalPayable, "total payable amount is different");

			Assert.assertNotNull(resTotalPayable, "total payable amount should not be bull");

			Assert.assertEquals(reqTotalPayable, expectedReqTotalPayable, "req total payable amount missmatch");
			Assert.assertEquals(resTotalPayable, actualResTotalPayable, "res total payable amount missmatch");
			Assert.assertEquals(reqTotalPayable, resTotalPayable, "total payable amount missmatch");

			System.out.println("Request total Payable amount :" + reqTotalPayable);
			System.out.println("Response total Payable amount :" + resTotalPayable);
			System.out.println("Expected request total Payable amount :" + expectedReqTotalPayable);
			System.out.println("Expected Response total Payable amount :" + actualResTotalPayable);
		}
	}

	@Test(priority=18)
	public void validPhoto() {

		List<String> resPhotoList = materialReturnResponse.jsonPath().getList("photos");
		String[] reqPhotos = materialReturnPayload.getPhotos();

		if(reqPhotos == null || reqPhotos.length==0) {
			System.out.println("photos not provided");
		} else {
			
			List<String> reqPhotosList = Arrays.asList(reqPhotos);

			Assert.assertNotNull(resPhotoList, "photos list should not be null");
			Assert.assertFalse(resPhotoList.isEmpty(), "photos list should not be empty");

			Assert.assertEquals(reqPhotosList.size(), resPhotoList.size());
			Assert.assertEquals(resPhotoList, reqPhotosList, "photos list should not be match");

			System.out.println("Request Photos  : " + reqPhotosList);
			System.out.println("Response Photos : " + resPhotoList);
			;
		}	
	}

	@Test(priority=19)
	public void validReturnDate() {

		String resReturnDate = materialReturnResponse.jsonPath().getString("return_date");
		String reqReturnDate = materialReturnPayload.getReturn_date();

		if(reqReturnDate == null || reqReturnDate.trim().isEmpty()) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "Invalid start date data", "validation error message wrong");
			System.out.println("Error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resReturnDate, "response return date should not be null");
			Assert.assertFalse(resReturnDate.trim().isEmpty(), "response return date should not be empty");

			try {
				Instant.parse(reqReturnDate);
				Instant.parse(resReturnDate);
			} catch(DateTimeParseException e) {
				Assert.fail("Invalid date format : " + e.getMessage());
			}
			Assert.assertEquals(Instant.parse(reqReturnDate), Instant.parse(resReturnDate), "Return date mismatch");

			System.out.println("Request return date :" + reqReturnDate);
			System.out.println("Response return date : " + resReturnDate);
		}
	}

	@Test(priority=20)
	public void validRefrenceNumber() {

		String resRefrenceNumber = materialReturnResponse.jsonPath().getString("vendor_reference_number");
		String reqRefrenceNumber = materialReturnPayload.getVendor_reference_number();

		if(reqRefrenceNumber == null || reqRefrenceNumber.trim().isEmpty()) {

			System.out.println("Refrence number is not provided");
		} else {

			Assert.assertNotNull(resRefrenceNumber, "vendor refrence number should not be null");
			Assert.assertFalse(resRefrenceNumber.trim().isEmpty(), "vendor refrence number should not be empty");

			Assert.assertEquals(reqRefrenceNumber, resRefrenceNumber, "Vendor reference number mismatch");

			System.out.println("Request refrence number :" + reqRefrenceNumber);
			System.out.println("Response refrence number :" + resRefrenceNumber);
		}
	}
	
	@Test(priority=21)
	public void validDueDays() {
		
		Double resDueDays = materialReturnResponse.jsonPath().getDouble("due_days");
		Double reqDueDays = materialReturnPayload.getDue_days();
		
		if(reqDueDays == null) {
			
			System.out.println("due days not provided");
		} else {
			
			Assert.assertNotNull(resDueDays, "response due days should not be null");
			
			Assert.assertEquals(resDueDays, reqDueDays, "due days should not be match");
			
			System.out.println("request due days :" + reqDueDays);
			System.out.println("response due days :" + resDueDays);
		}
	}
}
