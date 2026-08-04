package com.onsite.materialReturn_testpage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.pojo_request.MaterialReturnRequest;
import com.onsite.pojo_request.Materials;
import com.onsite.pojo_response.MaterialReturn_Response;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Details_MaterialRequest_Test extends BaseToken {

	private Response materialReturnResponse;
	private String materialReturnId;
	private MaterialReturnRequest materialReturnRequestPayload;

	@DataProvider(name="materialReturn")
	public Object[][] getMaterialReturn() throws IOException{
		
		String detailMaterialReturnFilePath = "src/test/resources/testdata_materialReturn/details_materialReturn.json";
		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(detailMaterialReturnFilePath)));
		JSONObject objId = new JSONObject(json);
		materialReturnId = objId.getString("id");

		String addMaterialReturnFilePath = "src/test/resources/testdata_materialReturn/add_materialReturn.json";
		File materialReturnPayloadPath = new File(addMaterialReturnFilePath);
		ObjectMapper mapper = new ObjectMapper();
		materialReturnRequestPayload = mapper.readValue(materialReturnPayloadPath, MaterialReturnRequest.class);

		return new Object[][] {
			{materialReturnId}
		};
	}

	@Test(priority=1, dataProvider="materialReturn")
	public void detailMaterialReturn(String materialReturnId) {

		materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("id", materialReturnId)
				.log().uri()

				.when()
				.get(MaterialReturn_Api.detail_materialreturn)

				.then()
				.log().all()
				.extract().response();

		MaterialReturn_Response responseBody = materialReturnResponse.as(MaterialReturn_Response.class);
		System.out.println(materialReturnResponse.asPrettyString());
	}

	@Test(priority=2, dependsOnMethods="detailMaterialReturn")
	public void validStatusCode() {

		int responseStatusCode = materialReturnResponse.statusCode();

		Assert.assertEquals(responseStatusCode, 200, "Expected status code should be 200");
	}

	@Test(priority=3, dependsOnMethods="detailMaterialReturn")
	public void validResponseMessage() {

		String responseMessage = materialReturnResponse.jsonPath().getString("message");
		if(responseMessage == null || responseMessage.isEmpty()) {
			
			System.out.println("response message is not provided");
		} else {

			Assert.assertNotNull(responseMessage, "Response message should not be null");
			Assert.assertFalse(responseMessage.trim().isEmpty(), "Response message should not be empty");
			System.out.println("Response message : " + responseMessage);
		}
	}

	@Test(priority=4, dependsOnMethods="detailMaterialReturn")
	public void validResponseTime() {

		long responseTime = materialReturnResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "resonse time is too long :" + responseTime + " : ms");
	}

	@Test(priority=5, dependsOnMethods="detailMaterialReturn")
	public void validResponseSchema() {

		String schemaPath = "responseSchema_files/MaterialReturnResponse_schema.json";
		materialReturnResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				schemaPath));
	}

	@Test(priority=6, dependsOnMethods="detailMaterialReturn")
	public void validMaterialReturnId() {

		String resMaterialReturnId = materialReturnResponse.jsonPath().getString("id");
		String reqMaterialReturnId = materialReturnId;

		Assert.assertNotNull(resMaterialReturnId, "response material return id should not be null");
		Assert.assertFalse(resMaterialReturnId.trim().isEmpty(), "response material return id should not be empty");
		Assert.assertNotNull(reqMaterialReturnId, "request material return id should not be null");
		Assert.assertFalse(reqMaterialReturnId.trim().isEmpty(), "request material return id should not be empty");

		Assert.assertEquals(resMaterialReturnId, reqMaterialReturnId, "resMaterialReturnId and reqMaterialReturnId missmatch");
	}

	@Test(priority=7, dependsOnMethods="detailMaterialReturn")
	public void validRemark() {

		String resRemark = materialReturnResponse.jsonPath().getString("remark");
		String reqRemark = materialReturnRequestPayload.getRemark();

		Assert.assertEquals(resRemark, reqRemark, "remark missmatch");

		System.out.println("reques remark :" + reqRemark);
		System.out.println("response remark :" + resRemark);
	}

	@Test(priority=8, dependsOnMethods="detailMaterialReturn")
	public void validPartyCompanyUserId() {

		String resPartyCompanyUserId = materialReturnResponse.jsonPath().getString("party_company_user_id");
		String reqPartyCompanyUserId = materialReturnRequestPayload.getParty_company_user_id();

		Assert.assertNotNull(resPartyCompanyUserId, "response party company user id should not be null");
		Assert.assertFalse(resPartyCompanyUserId.trim().isEmpty(), "response party company user id should not be empty");
		Assert.assertEquals(resPartyCompanyUserId, reqPartyCompanyUserId, "party company user id missmatch");

		System.out.println("response party company user id :" + resPartyCompanyUserId);
		System.out.println("request party company user id : " + reqPartyCompanyUserId);
	}

	@Test(priority=9, dependsOnMethods="detailMaterialReturn")
	public void validProjectId() {

		String resProjectId = materialReturnResponse.jsonPath().getString("project_id");
		String reqProjectId = materialReturnRequestPayload.getProject_id();

		Assert.assertNotNull(resProjectId, "response project id should not be null");
		Assert.assertFalse(resProjectId.trim().isEmpty(), "response project id should not be empty");
		Assert.assertEquals(resProjectId, reqProjectId, "project id missmatch");

		System.out.println("response project id : " + resProjectId);
		System.out.println("request project id :" + reqProjectId);
	}

	@Test(priority=10, dependsOnMethods="detailMaterialReturn")
	public void validSubCategoryId() {

		String resSubCategoryId = materialReturnResponse.jsonPath().getString("sub_category_id");
		String reqSubCategoryId = materialReturnRequestPayload.getSub_category_id();

		Assert.assertNotNull(resSubCategoryId, "response sub category id should not be null");
		Assert.assertEquals(resSubCategoryId, reqSubCategoryId, "sub category id missmatch");

		System.out.println("response sub category id :" + resSubCategoryId);
		System.out.println("request sub category id :" + reqSubCategoryId);
	}

	@Test(priority=11, dependsOnMethods="detailMaterialReturn")
	public void validMaterial() {

		List<String> resMaterialList = materialReturnResponse.jsonPath().getList("monkey_patch_materials");
		Materials[] reqMaterialList = materialReturnRequestPayload.getMaterials();

		Assert.assertNotNull(resMaterialList, "response material list should not be null");
		Assert.assertFalse(resMaterialList.isEmpty(), "responsre material list should not be empty");
		Assert.assertEquals(resMaterialList.size(), reqMaterialList.length, "material list count missmatch");

		for(int i=0; i < resMaterialList.size(); i++) {

			String resMaterialId = resMaterialList.get(i);
			Materials reqMaterialId = reqMaterialList[i];

			Assert.assertNotNull(resMaterialId, "material item id should not be null");
			Assert.assertEquals(resMaterialId, reqMaterialId.getMaterialItem_id(), "Material Item ID mismatch at index " + i);
		}
		System.out.println("responae material id :" + resMaterialList);
		System.out.println("request material id :" + reqMaterialList);
	}

	private Double resMaterialAmount;
	private Double reqMaterialAmount;
	@Test(priority=12, dependsOnMethods="detailMaterialReturn")
	public void validMaterialAmount() {

		resMaterialAmount = materialReturnResponse.jsonPath().getDouble("material_amount");
		reqMaterialAmount = materialReturnRequestPayload.getMaterial_amount();

		Assert.assertNotNull(resMaterialAmount, "response material amount should not be null");

		List<Materials> resMaterialList = materialReturnResponse.jsonPath().getList("monkey_patch_materials", Materials.class);

		Assert.assertNotNull(resMaterialList, "material list should not be null in response");
		Assert.assertFalse(resMaterialList.isEmpty(), "material list should not be empty");

		Double expMaterialAmount = 0.0;
		for(Materials materials : resMaterialList) {
			
			Assert.assertNotNull(materials.getQuantity(), "Quantity should not be null");
			Assert.assertNotNull(materials.getUnit_price(), "Unit price should not be null");
			
			Assert.assertTrue(materials.getQuantity() >=0, "Quantity should not be negative");
			Assert.assertTrue(materials.getUnit_price() >=0, "Unit price should not be negative");
			
			expMaterialAmount += materials.getQuantity() * materials.getUnit_price();
			Assert.assertTrue(expMaterialAmount >=0, "expected material amount should not be negative");
		}
		
		Assert.assertEquals(resMaterialAmount, expMaterialAmount, 0.01, "Response material amount missmatch");
		Assert.assertEquals(reqMaterialAmount, expMaterialAmount, 0.01, "Request material amount missmatch");
		Assert.assertEquals(resMaterialAmount, reqMaterialAmount, 0.01, "material amount missmatch");
		
		System.out.println("request material amount :" + reqMaterialAmount);
		System.out.println("Response material amount :" + resMaterialAmount);
		System.out.println("expected material amount :" + expMaterialAmount);
	}

	private Double resGstAmount;
	private Double reqGstAmount;
	@Test(priority=13, dependsOnMethods="detailMaterialReturn")
	public void validGstAmount() {

		resGstAmount = materialReturnResponse.jsonPath().getDouble("gst_amount");
		reqGstAmount = materialReturnRequestPayload.getGst_amount();

		if(reqGstAmount == null) {
			
			Assert.assertTrue(resGstAmount == null || resGstAmount == 0, 
					"response Gst amount should be null or 0");
		} else {
			
			Assert.assertNotNull(resGstAmount, "response gst amount shoukd not be null");
			Assert.assertEquals(resGstAmount, reqGstAmount, "gst amount missmatch");
			Assert.assertTrue(resGstAmount >=0, "response gst amout should not be negative");
		}

		System.out.println("respons Gst amount :" + resGstAmount);
		System.out.println("request Gst amount :" + reqGstAmount);
	}

	private Double resOtherAmount;
	private Double reqOtherAmount;
	@Test(priority=14, dependsOnMethods="detailMaterialReturn")
	public void validOtherAmount() {

		resOtherAmount = materialReturnResponse.jsonPath().getDouble("other_amount");
		reqOtherAmount = materialReturnRequestPayload.getOther_amount();
		
		if(reqOtherAmount == null) {
			
			Assert.assertTrue(resOtherAmount == null || resOtherAmount == 0, 
					"response other amount should be null or 0");
		} else {
			
			Assert.assertNotNull(resOtherAmount, "response other amount should not be null");
			Assert.assertEquals(resOtherAmount, reqOtherAmount, "other amount missmatch");
			Assert.assertTrue(resOtherAmount >=0, "response other amount should not be negative");
		}

		System.out.println("respons other amount :" + resOtherAmount);
		System.out.println("request other amount :" + reqOtherAmount);
	}
	
	private Double resDiscount;
	private Double reqDiscount;
	@Test(priority=15, dependsOnMethods="detailMaterialReturn")
	public void validDiscount() {
		
		resDiscount = materialReturnResponse.jsonPath().getDouble("discount");
		reqDiscount = materialReturnRequestPayload.getDiscount();
		
		if(reqDiscount == null) {
			
			Assert.assertTrue(resDiscount == null || resDiscount == 0, 
					"response discount should be null or 0");
		} else {
			
			Assert.assertNotNull(resDiscount, "response discount amount should not be null");
			Assert.assertEquals(resDiscount, reqDiscount, "discount amount missmatch");
			Assert.assertTrue(resDiscount >=0, "response discount amount should not be negative");
		}
		
		System.out.println("response discount amount :" + resDiscount);
		System.out.println("request discount amount :" + reqDiscount);
	}
	
	@Test(priority=16, dependsOnMethods="detailMaterialReturn")
	public void validTotalPayable() {
		
		Double expResTotalPayableAmount = 0.0;
		Double expReqTotalPayableAmount = 0.0;
		
		Double resTotalPayable = materialReturnResponse.jsonPath().getDouble("total_payable");
		Double reqTotalPayable = materialReturnRequestPayload.getTotal_payable();
		
		Assert.assertNotNull(resTotalPayable, "response total payable amount should not be null");
		Assert.assertTrue(resTotalPayable >=0, "response total payable amount should not be negative");
		
		expResTotalPayableAmount = ((resMaterialAmount-resDiscount)+resGstAmount+resOtherAmount);
		expReqTotalPayableAmount = ((reqMaterialAmount-reqDiscount)+reqGstAmount+reqOtherAmount);
		
		Assert.assertTrue(expResTotalPayableAmount >=0, "expected response total payable amount should not be negative");
		Assert.assertTrue(expReqTotalPayableAmount >=0, "expected request total payable amount should not be negative");
		
		Assert.assertEquals(resTotalPayable, reqTotalPayable, "total payable amount missmatch");
		Assert.assertEquals(expResTotalPayableAmount, expReqTotalPayableAmount, "expected total maount missmatch");
		Assert.assertEquals(expResTotalPayableAmount, resTotalPayable, "expected response total payable amount missmatch");
		Assert.assertEquals(expReqTotalPayableAmount, reqTotalPayable, "expected request total payable amount missmatch");
		
		System.out.println("respected response total payable amount :" + expResTotalPayableAmount);
		System.out.println("respected request total payable amount :" + expReqTotalPayableAmount);
		System.out.println("response total payable amount :" + resTotalPayable);
		System.out.println("request total payable amount :" + reqTotalPayable);
	}
	
	@Test(priority=17, dependsOnMethods="detailMaterialReturn")
	public void validPhotos() {
		
		List<String> resPhotoList = materialReturnResponse.jsonPath().getList("photos");
		String[] reqPhotoList = materialReturnRequestPayload.getPhotos();
		
		if(reqPhotoList == null || reqPhotoList.length == 0) {
			
			Assert.assertTrue(resPhotoList == null || resPhotoList.isEmpty(), 
					"response photo list should be null");
		} else {
			
			Assert.assertNotNull(resPhotoList, "response photos list should not be null");
			
			Assert.assertEquals(resPhotoList.size(), reqPhotoList.length, "photos count missmatch");
			
			for(int i=0; i<reqPhotoList.length; i++) {
				Assert.assertEquals(resPhotoList.get(i), reqPhotoList[i], "photos missmatch at index :" + i);
			}
		}
		System.out.println("Request Photos : " + Arrays.toString(reqPhotoList));
	    System.out.println("Response Photos : " + resPhotoList);
	}
	
	@Test(priority=18, dependsOnMethods="detailMaterialReturn")
	public void validReturnDate() {
		
		String resReturnDate = materialReturnResponse.jsonPath().getString("return_date");
		String reqReturnDate = materialReturnRequestPayload.getReturn_date();
		
		if(reqReturnDate == null || reqReturnDate.trim().isEmpty()) {
			Assert.assertTrue(resReturnDate == null || resReturnDate.trim().isEmpty(), 
					"response return date should be null or 0");
		} else {
			
			Assert.assertNotNull(resReturnDate, "response return date should not be null");
			Assert.assertFalse(resReturnDate.trim().isEmpty(), "response return date is not empty");
			Assert.assertEquals(resReturnDate, reqReturnDate, "return date is missmatch");
		}
		
		System.out.println("response return date :" + resReturnDate);
		System.out.println("Request return date :" + reqReturnDate);
	}
	
	@Test(priority=19, dependsOnMethods="detailMaterialReturn")
	public void validRefrenceNumber() {
		
		String resRefrenceNumber = materialReturnResponse.jsonPath().getString("vendor_reference_number");
		String reqRefrenceNumber = materialReturnRequestPayload.getVendor_reference_number();
		
		if(reqRefrenceNumber == null || reqRefrenceNumber.trim().isEmpty()) {
			
			Assert.assertTrue(resRefrenceNumber == null || resRefrenceNumber.trim().isEmpty(), 
					"response refrence number should be null or 0");
		} else {
			
			Assert.assertEquals(resRefrenceNumber, reqRefrenceNumber, "refrence number missmatch");
		}
		
		System.out.println("response refrence number :" + resRefrenceNumber);
		System.out.println("request refrence number :" + reqRefrenceNumber);
	}
	
	@Test(priority=20, dependsOnMethods="detailMaterialReturn")
	public void validDueDays() {
		
		Double resDueDays = materialReturnResponse.jsonPath().getDouble("due_days");
		Double reqDueDays = materialReturnRequestPayload.getDue_days();
		
		if(reqDueDays == null) {
			
			Assert.assertTrue(resDueDays == null || resDueDays == 0, 
					"Response due days should be null or 0");
		} else {
			
			Assert.assertNotNull(resDueDays, "response due days should not be null");
			Assert.assertEquals(resDueDays, reqDueDays, "due daya misssmatch");
		}
		
		System.out.println("response due days :" + resDueDays);
		System.out.println("request due days :" + reqDueDays);
	}

}
