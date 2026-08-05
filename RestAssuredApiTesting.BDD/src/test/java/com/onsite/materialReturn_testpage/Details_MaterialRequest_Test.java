package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.pojo_request.Materials;
import com.onsite.pojo_response.MaterialReturn_Response;
import com.onsite.utilities_page.BaseToken;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Details_MaterialRequest_Test extends BaseToken {

	private Response materialReturnResponse;
	private String materialReturnId;

	@DataProvider(name="materialReturn")
	public Object[][] getMaterialReturn() throws IOException{

		String detailMaterialReturnFilePath = "src/test/resources/testdata_materialReturn/details_materialReturn.json";

		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(detailMaterialReturnFilePath)));

		JSONObject objId = new JSONObject(json);
		materialReturnId = objId.getString("id");

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
		
		Assert.assertNotNull(resRemark);
		Assert.assertFalse(resRemark.trim().isEmpty());
		
		System.out.println("response remark :" + resRemark);
	}

	@Test(priority=8, dependsOnMethods="detailMaterialReturn")
	public void validPartyCompanyUserId() {

		String resPartyCompanyUserId = materialReturnResponse.jsonPath().getString("party_company_user_id");

		Assert.assertNotNull(resPartyCompanyUserId, "response party company user id should not be null");
		Assert.assertFalse(resPartyCompanyUserId.trim().isEmpty(), "response party company user id should not be empty");

		System.out.println("response party company user id :" + resPartyCompanyUserId);
	}

	@Test(priority=9, dependsOnMethods="detailMaterialReturn")
	public void validProjectId() {

		String resProjectId = materialReturnResponse.jsonPath().getString("project_id");

		Assert.assertNotNull(resProjectId, "response project id should not be null");
		Assert.assertFalse(resProjectId.trim().isEmpty(), "response project id should not be empty");

		System.out.println("response project id : " + resProjectId);
	}

	@Test(priority=10, dependsOnMethods="detailMaterialReturn")
	public void validSubCategoryId() {

		String resSubCategoryId = materialReturnResponse.jsonPath().getString("sub_category_id");

		Assert.assertNotNull(resSubCategoryId, "response sub category id should not be null");
		Assert.assertFalse(resSubCategoryId.trim().isEmpty(), "response subcategory id should not be empty");

		System.out.println("response sub category id :" + resSubCategoryId);
	}

	@Test(priority=11, dependsOnMethods="detailMaterialReturn")
	public void validMaterial() {

		List<Map<String,Object>> resMaterial = materialReturnResponse.jsonPath().getList("monkey_patch_materials");
		Double materialAmount = materialReturnResponse.jsonPath().getDouble("material_amount");

		Assert.assertNotNull(resMaterial, "material list should not be null in response");
		Assert.assertFalse(resMaterial.isEmpty(), "material list should not be empty");

		for(Map<String, Object> material : resMaterial) {
			
	        String materialItemId = (String) material.get("id");
	        String materialStockId = (String) material.get("materialstock_id");

	        Number quantity = (Number) material.get("quantity");
	        Number unitPrice = (Number) material.get("unit_price");

	        Assert.assertNotNull(materialItemId, "Material Item Id should not be null");
	        Assert.assertFalse(materialItemId.trim().isEmpty(), "Material Item Id should not be empty");

	        Assert.assertNotNull(materialStockId, "Material Stock Id should not be null");
	        Assert.assertFalse(materialStockId.trim().isEmpty(), "Material Stock Id should not be empty");

	        Assert.assertNotNull(quantity, "Quantity should not be null");
	        Assert.assertTrue(quantity.doubleValue() > 0, "Quantity should be greater than 0");

	        Assert.assertNotNull(unitPrice, "Unit Price should not be null");
	        Assert.assertTrue(unitPrice.doubleValue() > 0, "Unit Price should be greater than 0");

	        System.out.println("Material Item Id : " + materialItemId);
	        System.out.println("Material Stock Id : " + materialStockId);
	        System.out.println("Quantity : " + quantity);
	        System.out.println("Unit Price : " + unitPrice);
		}
	}

	@Test(priority=12, dependsOnMethods="detailMaterialReturn")
	public void validMaterialAmount() {

	    double expectedMaterialAmount = 0.0;

	    Double responseMaterialAmount =
	            materialReturnResponse.jsonPath().getDouble("material_amount");

	    List<Map<String, Object>> resMaterial =
	            materialReturnResponse.jsonPath().getList("monkey_patch_materials");

	    Assert.assertNotNull(resMaterial, "Materials list should not be null");
	    Assert.assertFalse(resMaterial.isEmpty(), "Materials list should not be empty");

	    for (Map<String, Object> material : resMaterial) {

	        Number quantity = (Number) material.get("quantity");
	        Number unitPrice = (Number) material.get("unit_price");

	        Assert.assertNotNull(quantity, "Quantity should not be null");
	        Assert.assertNotNull(unitPrice, "Unit Price should not be null");
	        
	        Assert.assertTrue(quantity.doubleValue() > 0, "Quantity should be greater than 0");
	        Assert.assertTrue(unitPrice.doubleValue() > 0, "Unit Price should be greater than 0");

	        expectedMaterialAmount += quantity.doubleValue() * unitPrice.doubleValue();
	    }

	    Assert.assertEquals(responseMaterialAmount, expectedMaterialAmount, 0.01, "Material amount mismatch");

	    System.out.println("Expected Material Amount : " + expectedMaterialAmount);
	    System.out.println("Response Material Amount : " + responseMaterialAmount);
	}

	private Double resGstAmount;
	@Test(priority=13, dependsOnMethods="detailMaterialReturn")
	public void validGstAmount() {

		resGstAmount = materialReturnResponse.jsonPath().getDouble("gst_amount");

		Assert.assertNotNull(resGstAmount, "response Gst amount should not be null");

		Assert.assertTrue(resGstAmount >= 0);
	}

	private Double resOtherAmount;
	@Test(priority=14, dependsOnMethods="detailMaterialReturn")
	public void validOtherAmount() {

		resOtherAmount = materialReturnResponse.jsonPath().getDouble("other_amount");

		Assert.assertNotNull(resOtherAmount);

		Assert.assertTrue(resOtherAmount >= 0);
	}

	private Double resDiscount;
	@Test(priority=15, dependsOnMethods="detailMaterialReturn")
	public void validDiscount() {

		resDiscount = materialReturnResponse.jsonPath().getDouble("discount");

		Assert.assertNotNull(resDiscount);

		Assert.assertTrue(resDiscount >= 0);

	}

	@Test(priority=16, dependsOnMethods="detailMaterialReturn")
	public void validTotalPayable() {

		Double resTotalPayable = materialReturnResponse.jsonPath().getDouble("total_payable");
		Double expectedMaterialAmount = materialReturnResponse.jsonPath().getDouble("material_amount");

		Assert.assertTrue(resTotalPayable>=0);

		Double expTotalPayable = (expectedMaterialAmount - resDiscount) + resGstAmount + resOtherAmount;

		Assert.assertEquals(resTotalPayable, expTotalPayable, 0.01);
	}

	@Test(priority=17, dependsOnMethods="detailMaterialReturn")
	public void validPhotos() {

		List<String> photos = materialReturnResponse.jsonPath().getList("photos");

		Assert.assertNotNull(photos);

		for(String photo : photos){

			Assert.assertNotNull(photo);

			Assert.assertFalse(photo.trim().isEmpty());
		}
	}

	@Test(priority=18, dependsOnMethods="detailMaterialReturn")
	public void validReturnDate() {

		String date = materialReturnResponse.jsonPath().getString("return_date");

		Assert.assertNotNull(date);

		Assert.assertFalse(date.isBlank());

		Instant.parse(date);
	}

	@Test(priority=19, dependsOnMethods="detailMaterialReturn")
	public void validRefrenceNumber() {

		String resRefrenceNumber = materialReturnResponse.jsonPath().getString("vendor_reference_number");

		Assert.assertNotNull(resRefrenceNumber);

		Assert.assertFalse(resRefrenceNumber.trim().isEmpty());

		System.out.println("response refrence number :" + resRefrenceNumber);
	}

	@Test(priority=20, dependsOnMethods="detailMaterialReturn")
	public void validDueDays() {

		Double due = materialReturnResponse.jsonPath().getDouble("due_days");

		Assert.assertTrue(due >= 0);
	}

}
