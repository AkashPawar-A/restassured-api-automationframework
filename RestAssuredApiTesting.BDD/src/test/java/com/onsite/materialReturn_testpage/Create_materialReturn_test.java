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
import com.onsite.pojo_request.Materials;
import com.onsite.pojo_request.MaterialReturnRequest;
import com.onsite.pojo_response.MaterialReturn_Response;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Create_materialReturn_test extends BaseToken{

	private MaterialReturnRequest materialReturnPayload;
	private Response materialReturnResponse;

	@DataProvider(name="materialReturn")
	public Object[][] getData() throws IOException{
		
		// Material Return main request
	    Map<String, Object> materialReturnReq =
	            JsonUtils.readJson("src/test/resources/testdata_materialReturn/add_materialReturn.json");

	    // Material payload
	    Map<String, Object> material =
	            JsonUtils.readJson("src/test/resources/testdata_material/materials.json");

	    // Material Item payload
	    Map<String, Object> materialItem =
	            JsonUtils.readJson("src/test/resources/testdata_materialitem/materialitem.json");

	    // Nested object add
	    material.put("monkey_patch_materialitem", materialItem);

	    // Materials array
	    List<Map<String, Object>> materials = new ArrayList<>();
	    materials.add(material);

	    // Main payload
	    materialReturnReq.put("materials", materials);

	    // Convert JSON -> POJO
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());

	    materialReturnPayload = mapper.convertValue(materialReturnReq, MaterialReturnRequest.class);
	
	    // Print payload
	    String finalPayload = mapper.writerWithDefaultPrettyPrinter()
	                                .writeValueAsString(materialReturnPayload);

	    System.out.println(finalPayload);

		return new Object[][] {
			{ materialReturnPayload }
		};
	}

	@Test(dataProvider="materialReturn", priority=1)
	public void materialReturn(MaterialReturnRequest finalPayload) {

		materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().uri()

				.when()
				.post(MaterialReturn_Api.add_materialReturn)

				.then()
				.log().all()
				.extract().response();

		MaterialReturn_Response responseBody = materialReturnResponse.as(MaterialReturn_Response.class);

	}

	@Test(priority=2, dependsOnMethods="materialReturn")
	public void validStatusCode() {

		int responseStatusCode = materialReturnResponse.getStatusCode();

		if(responseStatusCode != 200) {
			System.out.println("actual status code is 200 but found :" + responseStatusCode);
		}
		Assert.assertEquals(responseStatusCode, 200, "status code does not match with responseStatusCode");
	}

	@Test(priority=2, dependsOnMethods="materialReturn")
	public void validResponseMessage() {

		String responseMessage = materialReturnResponse.jsonPath().getString("message");
		
		if(responseMessage == null || responseMessage.trim().isEmpty()) {
			System.out.println("Response message is not provided");
		} else {
			
			Assert.assertNotNull(responseMessage, "response message should not be null");
			Assert.assertFalse(responseMessage.trim().isEmpty(), "Response message should not be empty");
			System.out.println("response message :" + responseMessage);
		}
	}
	
	@Test(priority=3, dependsOnMethods="materialReturn")
	public void validResponseTime() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		long responseTime = materialReturnResponse.getTime();
		System.out.println("response time :" + responseTime + "ms");

		Assert.assertTrue(responseTime < 2000, "Response time is too long :" + responseTime + "ms");
	}

	@Test(priority=4, dependsOnMethods="materialReturn")
	public void validResponseSchema() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		materialReturnResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				"responseSchema_files/MaterialReturnResponse_schema.json"));
	}

	@Test(priority=5, dependsOnMethods="materialReturn")
	public void validMaterialReturnId() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		String materialReturnId = materialReturnResponse.jsonPath().getString("id");

		Assert.assertNotNull(materialReturnId, "material retunt id should not be null");
		Assert.assertFalse(materialReturnId.trim().isEmpty(), "material return id should not be empty");

		System.out.println("Material return id :" + materialReturnId);
	}

	@Test(priority=6, dependsOnMethods="materialReturn")
	public void validCompanyId() {
		
		String loginCompanyId = CompanyContext.getCompanyId();
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		String companyId = materialReturnResponse.jsonPath().getString("company_id");
		
		System.out.println("Response Company ID : " + companyId);
		System.out.println("Login Company ID   : " + loginCompanyId);

		Assert.assertNotNull(companyId, "company id should not be null");
		Assert.assertFalse(companyId.trim().isEmpty(), "company id should not be empty");
		Assert.assertNotNull(loginCompanyId, "login company id should not be null");;

		Assert.assertEquals(companyId, loginCompanyId, "Company ID mismatch");
	}

	@Test(priority=7, dependsOnMethods="materialReturn")
	public void validRemark() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

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

	@Test(priority=8, dependsOnMethods="materialReturn")
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

	@Test(priority=9, dependsOnMethods="materialReturn")
	public void validCreatorCompanyUserId() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		String resCreatorCompanyUserId = materialReturnResponse.jsonPath().getString("creator_company_user_id");

		Assert.assertNotNull(resCreatorCompanyUserId, "Creator Company User ID should not be null");
		Assert.assertFalse(resCreatorCompanyUserId.trim().isEmpty(), "Creator Company User ID should not be empty");
		
		System.out.println("creator company user id :" + resCreatorCompanyUserId);
	}

	@Test(priority=10, dependsOnMethods="materialReturn")
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

	@Test(priority=11, dependsOnMethods="materialReturn")
	public void validInvoiceId() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		String resInvoiceId = materialReturnResponse.jsonPath().getString("invoice_id");

		Assert.assertNotNull(resInvoiceId, "Invoice Id should not be null");
		Assert.assertFalse(resInvoiceId.trim().isEmpty(), "Invoice Id should not be empty");
		
		System.out.println("invoice id : " + resInvoiceId);	
	}

	@Test(priority=12, dependsOnMethods="materialReturn")
	public void validMaterial() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		List<Materials> resMaterialList = materialReturnResponse.jsonPath().getList("material", Materials.class);

		Materials[] reqMaterialList = materialReturnPayload.getMaterials();

		Assert.assertNotNull(reqMaterialList, "Request material list should not be null");
		Assert.assertTrue(reqMaterialList.length > 0, "Request material list should not be empty");
		
		if(resMaterialList == null || resMaterialList.isEmpty()) {
			
			System.out.println("material list is empty in response : Validating material_ids instead");
			
			List<String> responseMaterialIds = materialReturnResponse.jsonPath().getList("material_ids");
			
		    Assert.assertNotNull(responseMaterialIds, "material_ids should not be null");
		    Assert.assertFalse(responseMaterialIds.isEmpty(), "material_ids should not be empty");
		    
		    Assert.assertEquals(responseMaterialIds.size(), reqMaterialList.length,
		            "Material ID count mismatch");

		    for (int i = 0; i < responseMaterialIds.size(); i++) {
		    	String materialId = responseMaterialIds.get(i);
		    	
		        Assert.assertNotNull(materialId, "Material ID should not be null");
		        Assert.assertFalse(materialId.trim().isEmpty(), "Material ID should not be empty");

		        System.out.println("Generated Material ID : " + materialId);
		    }
		    return;
		}	
	}

	private Double reqMaterialAmount;
	private Double resMaterialAmount;
	@Test(priority=13, dependsOnMethods="materialReturn")
	public void validMaterialAmount() {
		
		this.resMaterialAmount = materialReturnResponse.jsonPath().getDouble("material_amount");
		this.reqMaterialAmount = materialReturnPayload.getMaterial_amount();

		Materials[] reqMaterialList = materialReturnPayload.getMaterials();

		if(reqMaterialAmount == null) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "Expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "material amount required", "incorrect validation message");
			System.out.println("material amount error message :" + errorMessage);
		} else {
			
			Assert.assertNotNull(reqMaterialList, "Material list should not be null");
			Assert.assertTrue(reqMaterialList.length > 0, "Material list should not be empty");
			
			Double expectedMaterialAmount = 0.0;
			
			for(Materials materialItem : reqMaterialList) {
				
				Assert.assertNotNull(materialItem.getQuantity(), "Material item quantity should not be null");
				Assert.assertNotNull(materialItem.getUnit_price(), "Material item unit price should not be null");
				
				Assert.assertTrue(materialItem.getQuantity() >=0, "material quantity should not be negative");
				Assert.assertTrue(materialItem.getUnit_price() >=0, "material unit price should not be negative"); 
				
	            Double itemAmount = materialItem.getQuantity() * materialItem.getUnit_price();
	            Assert.assertTrue(itemAmount >=0, "item Amount should not be negative");

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
	@Test(priority=14, dependsOnMethods="materialReturn")
	public void validDiscount() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		this.resDiscount = materialReturnResponse.jsonPath().getDouble("discount");
		this.reqDiscount = materialReturnPayload.getDiscount();

		if(reqDiscount == null) {
			System.out.println("discount is not provided");
		} else {
			Assert.assertNotNull(resDiscount, "discount should not be null");
			Assert.assertEquals(resDiscount, reqDiscount, "discout is missmatch");
			
			Assert.assertTrue(resDiscount >=0, "response discount amount should not be negative");
			Assert.assertTrue(resDiscount >=0, "request discount amount should not be negative");

			System.out.println("Request discount :" + reqDiscount);
			System.out.println("Response discount :" + resDiscount);	
		}	
	}

	private Double reqGstAmount;
	private Double resGstAmount;
	@Test(priority=15, dependsOnMethods="materialReturn")
	public void validGstAmount() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		this.resGstAmount = materialReturnResponse.jsonPath().getDouble("gst_amount");
		this.reqGstAmount = materialReturnPayload.getGst_amount();

		if(reqGstAmount == null) {

			System.out.println("gst amount is not provided");
		} else {

			Assert.assertNotNull(resGstAmount, "gst amount should not be null");
			Assert.assertEquals(resGstAmount, reqGstAmount, "gst amount is missmatch");
			
			Assert.assertTrue(resGstAmount >=0, "response gst amount should not be negative");
			Assert.assertTrue(resGstAmount >=0, "request gst amount should not be negative");

			System.out.println("Request gst Amount :" + reqGstAmount);
			System.out.println("Response Gst Amount :" + resGstAmount);
		}	
	}

	private Double reqOtherAmount;
	private Double resOtherAmount;
	@Test(priority=16, dependsOnMethods="materialReturn")
	public void validOtherAmount() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		this.resOtherAmount = materialReturnResponse.jsonPath().getDouble("other_amount");
		this.reqOtherAmount = materialReturnPayload.getOther_amount();

		if(reqOtherAmount == null) {

			System.out.println("other amount is not provided");
		} else {

			Assert.assertNotNull(resOtherAmount, "other amount should not be null");
			Assert.assertEquals(resOtherAmount, reqOtherAmount, "other amount is missmatch");
			
			Assert.assertTrue(resOtherAmount >=0, "response other amount should not be negative");
			Assert.assertTrue(resOtherAmount >=0, "request other amount should not be negative");

			System.out.println("Request Other amount :" + reqOtherAmount);
			System.out.println("Response Other Amoutn :" + resOtherAmount);	
		}
	}

	@Test(priority=17, dependsOnMethods="materialReturn")
	public void validTotalPayable() {

		Double expectedReqTotalPayable = 0.0;
		Double actualResTotalPayable = 0.0;

		Double resTotalPayable = materialReturnResponse.jsonPath().getDouble("total_payable");
		Double reqTotalPayable = materialReturnPayload.getTotal_payable();

		if(reqTotalPayable == null) {

			Assert.assertEquals(materialReturnResponse.getStatusCode(), 400, "expected status code should be 400");
			String errorMessage = materialReturnResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "total payable required", "incorrect validation message");
			System.out.println("total payable maount error message");
		} else {

			expectedReqTotalPayable = ((reqMaterialAmount-reqDiscount)+reqOtherAmount+reqGstAmount);
			actualResTotalPayable = ((resMaterialAmount-resDiscount)+resOtherAmount+resGstAmount);
			
			Assert.assertTrue(expectedReqTotalPayable >=0, "expectedReqTotalPayable amount should not be negative");
			Assert.assertTrue(actualResTotalPayable >=0, "actualResTotalPayable amount should not be negative");
			
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

	@Test(priority=18, dependsOnMethods="materialReturn")
	public void validPhoto() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

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

	@Test(priority=19, dependsOnMethods="materialReturn")
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

	@Test(priority=20, dependsOnMethods="materialReturn")
	public void validRefrenceNumber() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());

		String resRefrenceNumber = materialReturnResponse.jsonPath().getString("vendor_reference_number");
		String reqRefrenceNumber = materialReturnPayload.getVendor_reference_number();

		if(reqRefrenceNumber == null || reqRefrenceNumber.trim().isEmpty()) {

			System.out.println("Refrence number is not provided");
		} else {

			Assert.assertNotNull(resRefrenceNumber, "vendor refrence number should not be null");
			Assert.assertFalse(resRefrenceNumber.trim().isEmpty(), "Vendor reference number should not be empty");
			Assert.assertEquals(resRefrenceNumber, reqRefrenceNumber, "Vendor reference number mismatch");

			System.out.println("Request refrence number :" + reqRefrenceNumber);
			System.out.println("Response refrence number :" + resRefrenceNumber);
		}
	}
	
	@Test(priority=21, dependsOnMethods="materialReturn")
	public void validDueDays() {
		
		Assert.assertEquals(materialReturnResponse.getStatusCode(), 200, "Expected Status Code: 200 but found: " + materialReturnResponse.getStatusCode());
		
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
