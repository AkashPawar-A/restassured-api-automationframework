package com.onsite.creditnote_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.CreditNoteDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.pojo_request.CreditNoteCreateRequest;
import com.onsite.pojo_response.CreditNoteResponseBody;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreditNoteCreateTest extends BaseToken{

	@DataProvider(name = "creditNote_CreateTestData")
	public Object[][] getCreditNoteData() {
	    String path = "src/test/resources/test_data/CreditNote_CreateTestData.json";
	    return JsonDataProvider.getDataFromJson(path, CreditNoteCreateRequest.class);
	}

	@Test(priority = 1, dataProvider = "creditNote_CreateTestData")
	public void postCreditNote(CreditNoteCreateRequest payload) throws Exception {

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
				.log().all()
				.extract().response();

		int statusCode = response.statusCode();
		String errorMessage = response.jsonPath().getString("message");

		System.out.println("status code: " + statusCode);
		System.out.println("missing required field: " + errorMessage);	

		if(statusCode==200) {
			CreditNoteDetails.project_id = response.jsonPath().getString("project_id");
			CreditNoteDetails.company_id = response.jsonPath().getString("company_id");
			CreditNoteDetails.creator_id = response.jsonPath().getString("creator");
			CreditNoteDetails.creator_company_user_id = response.jsonPath().getString("creator_company_user_id");
			CreditNoteDetails.creditNote_id = response.jsonPath().getString("id");
			CreditNoteDetails.party_company_user_id = response.jsonPath().getString("party_company_user_id");
			CreditNoteDetails.invoice_id = response.jsonPath().getString("invoice_id");
			CreditNoteDetails.invoice_date = response.jsonPath().getString("invoice_date");

			//Optional filed Validation	
			if(payload.getReference_number() != null && !payload.getReference_number().isEmpty()) {
				String actualRefrence = response.jsonPath().getString("reference_number");
				Assert.assertEquals(actualRefrence, payload.getReference_number(), "reference_number");
			}
			if(payload.getNotes() !=null && !payload.getNotes().isEmpty()) {
				String actualNotes = response.jsonPath().getString("notes");
				Assert.assertEquals(actualNotes, payload.getNotes(), "notes");
			}
		} else {
			// Call helper method for required field validation on error
			validateRequiredFieldsOnError(payload, errorMessage);
		}
	}

	private void validateRequiredFieldsOnError(CreditNoteCreateRequest payload, String errorMessage) {
		if (payload.getProject_id() == null || payload.getProject_id().isEmpty()) {
			System.out.println("error message for project id: " + errorMessage);
			Assert.fail("Project ID is missing: " + errorMessage);
		}
		if (payload.getInvoice_date() == null || payload.getInvoice_date().isEmpty()) {
			System.out.println("error message for Invoice_Date: " + errorMessage);
			Assert.fail("Invalid Invoice_Date: " + errorMessage);
		}
		if (payload.getParty_company_user_id() == null || payload.getParty_company_user_id().isEmpty()) {
			System.out.println("error message for company_user_id: " + errorMessage);
			Assert.fail("Invalid company_user_id: " + errorMessage);
		}
	}

	@Test(priority=2, dependsOnMethods="postCreditNote")
	public void getCreditNote() throws Exception {
		
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
				.get(CreditNote_Api.Get_CreditNote)

			.then()
				.statusCode(200)
				.log().all()
				.extract().response();

		String jsonResponse = response.asString();
		SchemaValidator.validateSchema("schemas_files/CreditNote.json", jsonResponse);

		CreditNoteResponseBody creditNoteObj = response.as(CreditNoteResponseBody.class);
	}
}
