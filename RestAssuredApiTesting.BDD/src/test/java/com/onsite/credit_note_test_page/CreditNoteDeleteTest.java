package com.onsite.credit_note_test_page;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.context.CreditNoteDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreditNoteDeleteTest extends BaseToken {
	
	@Test(priority = 1)
	public void deleteCreditNote() {
		
		if (CreditNoteDetails.creditNote_id == null || CreditNoteDetails.creditNote_id.isEmpty()) {
		    throw new IllegalArgumentException("creditNote_id is null or empty");
		}
		
		Response response = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", CreditNoteDetails.creditNote_id)
				
			.when()
				.delete(CreditNote_Api.Delete_CreditNote)
				
			.then()
				.log().all()
				.extract().response();
		
		int statusCode = response.statusCode();
		String message = response.jsonPath().getString("message");
		
		System.out.println("status code :" + statusCode);
		System.out.println("messge :" + message);
		
		Assert.assertEquals(statusCode, 200, "Credit note deletion failed");
	}

}
