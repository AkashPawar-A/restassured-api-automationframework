package com.onsite.credit_note_test_page;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.CreditNoteDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.pojo_request.CreditNoteEditRequest;
import com.onsite.pojo_response.CreditNoteResponseBody;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreditNoteEditTest extends BaseToken{

	@DataProvider(name="CreditNote_EditTestData")
	public Object[][] getCreditNoteData() {
		String path = "src/test/resources/test_data/CreditNote_EditTestData.json";	
		return JsonDataProvider.getDataFromJson(path, CreditNoteEditRequest.class);
	}

	@Test(priority=1, dataProvider="CreditNote_EditTestData")
	public void editCreditNote(CreditNoteEditRequest payload) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(payload);
		System.out.println("final json payload :" + jsonPayload);

		Response response = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(payload)
				.log().all()

			.when()
				.patch(CreditNote_Api.Edit_CreditNote)

			.then()
				.log().all()
				.extract().response();

		int statusCode = response.statusCode();
		String Message = response.jsonPath().getString("message");

		System.out.println("Status Code :" + statusCode);
		System.out.println("Message :" + Message);
		
		if(statusCode==200) {
			CreditNoteDetails.project_id = response.jsonPath().getString("project_id");
			CreditNoteDetails.company_id = response.jsonPath().getString("company_id");
			CreditNoteDetails.creator_id = response.jsonPath().getString("creator");
			CreditNoteDetails.creator_company_user_id = response.jsonPath().getString("creator_company_user_id");
			CreditNoteDetails.creditNote_id = response.jsonPath().getString("id");
			CreditNoteDetails.party_company_user_id = response.jsonPath().getString("party_company_user_id");
			CreditNoteDetails.invoice_id = response.jsonPath().getString("invoice_id");
			CreditNoteDetails.invoice_date = response.jsonPath().getString("invoice_date");
		}
	}

	@Test(priority=2, dependsOnMethods="editCreditNote")
	public void getCreditNote() throws Exception {

		if(CreditNoteDetails.creditNote_id == null || CreditNoteDetails.creditNote_id.isEmpty()) {
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
				.log().all()
				.extract().response();
		
		int statusCode = response.statusCode();
		String message = response.jsonPath().getString("message");
		
		System.out.println("status code :" + statusCode);
		System.out.println("message :" + message);
		
		CreditNoteResponseBody creditNoteObj = response.as(CreditNoteResponseBody.class);
		
		String jsonResponse = response.asString();
		SchemaValidator.validateSchema("schemas_files\\CreditNote.json", jsonResponse);
	}
}
