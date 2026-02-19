package com.onsite.transaction.materialPurchase_test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.pojo_request.MaterialPurchaseRequest;
import com.onsite.pojo_response.MaterialPurchaseResponse;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
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
		
		String schemaResponse = purchaseResponse.asString();
		try {
			SchemaValidator.validateSchema("schemas_files/MaterialPurchase.json", schemaResponse);
		} catch(Exception e) {
			System.out.println("schema response is fail");
		}
		
		String materialPurchaseId = purchaseResponse.jsonPath().get("id");
		String companyId = purchaseResponse.jsonPath().get("company_id");
		String creatorCompanyUserId = purchaseResponse.jsonPath().get("creator_company_user_id");
		String partyCompanyUserId = purchaseResponse.jsonPath().get("party_company_user_id");
		String projectId = purchaseResponse.jsonPath().get("project_id");
		String invoiceId = purchaseResponse.jsonPath().get("invoice_id");
		List<String> materialIds = purchaseResponse.jsonPath().getList("material_ids");
		
		if(materialPurchaseId != null) {
			System.out.println("material purchase id :" + materialPurchaseId);
		} else {
			Assert.fail("material purchase id is null or empty");
		}
		
		if(loginCompanyId.equals(companyId)) {
			System.out.println("material purchase company id : " + companyId);
		} else {
			System.out.println("company id missamtch");
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
		
		if(materialpurchasePayload.getMaterials().equals(materialIds)) {
			System.out.println("material ids is match :" + materialIds);
		} else {
			Assert.fail("material ids missmatch");
		}
		
	}

}
