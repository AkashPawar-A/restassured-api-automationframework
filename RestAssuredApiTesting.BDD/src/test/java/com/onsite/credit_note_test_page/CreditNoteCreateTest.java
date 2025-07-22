package com.onsite.credit_note_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.CreditNoteDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.payloadbuilder.CreditNotePayload;
import com.onsite.pojo_request.CreditNoteRequest;
import com.onsite.pojo_response.CreditNoteResponseBody;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreditNoteCreateTest extends BaseToken{

	@Test(priority=1)
	public void postCreditNote() throws Exception {

		CreditNoteRequest payload = CreditNotePayload.buildCreditNotePayload();

		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(payload);
		System.out.println("final json payload" + jsonPayload);

		Response response = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(payload)
				.log().all()

			.when()
				.post(CreditNote_Api.Create_CreaditNote)

			.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		CreditNoteDetails.project_id = response.jsonPath().getString("project_id");
		CreditNoteDetails.company_id = response.jsonPath().getString("company_id");
		CreditNoteDetails.creator_id = response.jsonPath().getString("creator");
		CreditNoteDetails.creator_company_user_id = response.jsonPath().getString("creator_company_user_id");
		CreditNoteDetails.creditNote_id = response.jsonPath().getString("id");
		CreditNoteDetails.party_company_user_id = response.jsonPath().getString("party_company_user_id");
		CreditNoteDetails.invoice_id = response.jsonPath().getString("invoice_id");
	}
	
	@Test(priority=2, dependsOnMethods="postCreditNote")
	public void getCreditNote() {
		
		CreditNoteResponseBody creditNoteObj = 
				
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", CreditNoteDetails.creditNote_id)
				
			.when()
				.get(CreditNote_Api.Get_CreditNote)
				
			.then()
				.statusCode(200)
				.log().all()
				.extract().as(CreditNoteResponseBody.class);
		System.out.println();
	}
}
