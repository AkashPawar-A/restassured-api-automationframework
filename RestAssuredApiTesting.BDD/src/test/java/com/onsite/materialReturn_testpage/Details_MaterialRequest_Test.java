package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.utilities_page.BaseToken;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Details_MaterialRequest_Test {

	@DataProvider(name="materialReturn")
	public Object[][] getMaterialId() throws IOException{

		String filePath = "src/test/resources/testdata_materialReturn/details_materialReturn.json";

		String json = new String(java.nio.file.Files.readAllBytes(Paths.get(filePath)));

		JSONObject objId = new JSONObject(json);
		String materialReturnId = objId.getString("id");

		return new Object[][] {
			{materialReturnId}
		};
	}
	
	@Test(priority=1, dataProvider="materialReturn")
	public void detailMaterialReturn(String materialReturnId) {
		
		Response materialReturnResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer :" + BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("id", materialReturnId)
				.log().uri()
				
				.when()
				.get(MaterialReturn_Api.detail_materialreturn)
				
				.then()
				.log().all()
				.extract().response();
	}

}
