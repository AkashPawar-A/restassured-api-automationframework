package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.pojo_request.MaterialReturnRequest;
import com.onsite.pojo_response.MaterialReturn_Response;
import com.onsite.utilities_page.BaseToken;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Edit_materialReturn_test extends BaseToken{
	
	private Response editMaterialReturnResponse;
	
	@DataProvider(name="materialReturn")
	public Object[][] getData() throws IOException{
		
		String detailMaterialReturnFilePath = "src/test/resources/testdata_materialReturn/details_materialReturn.json";
		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(detailMaterialReturnFilePath)));
		JSONObject dataObj = new JSONObject(json);
		String getMaterialReturnId = dataObj.getString("id");
		
		System.out.println("materal return id :" + getMaterialReturnId);
		
		String editMaterilReturnFilePath = "src/test/resources/testdata_materialReturn/edit_materialReturn.json";
		ObjectMapper mapper = new ObjectMapper();
		MaterialReturnRequest materialReturnPayload = mapper.readValue(
				Paths.get(editMaterilReturnFilePath).toFile(), MaterialReturnRequest.class);
		
		materialReturnPayload.setId(getMaterialReturnId);
		
		return new Object[][] {
			{materialReturnPayload}
		};
	}
	
	@Test(dataProvider="materialReturn")
	public void getMaterialReturnEdit(MaterialReturnRequest materialReturnPayload) {
		
		 editMaterialReturnResponse = RestAssured.
				 given()
				 .baseUri(ApiBasePath.BASE_URL)
				 .header("Authorization", "Bearer " + BaseToken.token)
				 .contentType(ContentType.JSON)
				 .body(materialReturnPayload)
				 .log().uri()
				 
				 .when()
				 .patch(MaterialReturn_Api.edit_materialReturn)
				 
				 .then()
				 .log().all()
				 .extract().response();
		 
		 MaterialReturn_Response EditMaterialReturnResponse = editMaterialReturnResponse.as(MaterialReturn_Response.class);
	}
	
	public void validsttausCode() {
		
		int resStatusCode = editMaterialReturnResponse.getStatusCode();
		
		if(resStatusCode != 200) {
			System.out.println("response status code is 200 not found :" + resStatusCode);
		}
		
		Assert.assertEquals(resStatusCode, 200, "status code does not match with response status code");;
	}
	
	public void validResponseMessage() {
		
		String responseMessage = editMaterialReturnResponse.jsonPath().getString("message");

		if(responseMessage == null || responseMessage.trim().isEmpty()) {
			System.out.println("Response message is not provided");
		} else {

			Assert.assertNotNull(responseMessage, "response message should not be null");
			Assert.assertFalse(responseMessage.trim().isEmpty(), "Response message should not be empty");
			System.out.println("response message :" + responseMessage);
		}
	}
	

}
