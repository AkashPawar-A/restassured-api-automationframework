package com.onsite.credit_note_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Common_Api;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

public class CreditNoteUploadFileTest extends BaseToken {
	
	String basePath = "src/test/resources/test_data/UploadTestFiles";
	
	@DataProvider(name="UploadTestFiles")
	public Object[][] uploadFiles(){
		return new Object[][] {
			{Paths.get(basePath, "sample.png").toFile(), "image/png" },
	        {Paths.get(basePath, "sample.xlsx").toFile(), "application.sheet" },
	        {Paths.get(basePath, "sample.pdf").toFile(), "application/pdf" }
	    };
	}
	
	@Test(dataProvider="UploadTestFiles")
	public void uploadTest(File file, String contentType) {
		Assert.assertTrue(file.exists(), "file not found :" + file.getName());
		
		Response response =
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.multiPart("file", file, contentType)
				.multiPart("folder", "material")
				.multiPart("project_id", "undefined")
				.multiPart("task_id", "undefined")
				.multiPart("expense_id", "undefined")
				.multiPart("material_id", "undefined")
				.multiPart("drawing_id", "undefined")
				.multiPart("name", file.getName())
				.accept(ContentType.JSON)
				.log().all()
				
			.when()
				.post(Common_Api.Upload_file)
				
			.then()
				.log().all()
				.extract().response();
		
		int statusCode = response.getStatusCode();
	    Assert.assertEquals(statusCode, 200, "Upload failed for: " + file.getName());

	    String uploadedUrl = response.jsonPath().getString("url");
	    Assert.assertNotNull(uploadedUrl, "URL should not be null for: " + file.getName());
	    
	    System.out.println("Uploaded: " + file.getName() + "URL: " + uploadedUrl);
	}
}
