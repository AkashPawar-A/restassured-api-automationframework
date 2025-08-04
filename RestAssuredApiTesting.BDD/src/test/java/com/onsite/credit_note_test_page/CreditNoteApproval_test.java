package com.onsite.credit_note_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

public class CreditNoteApproval_test extends BaseToken{

	@Test
	public void approvalCreditNote() throws Exception {

		String creditNoteId = "ab3edbb0-2940-4db3-b3f7-9458f20af6c0";
		
		List<String> approvalFlags = Arrays.asList("pending");

		for(String status : approvalFlags) {
			System.out.println("testing approval flag :" + status.toUpperCase());

			Response response = 
				given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.body(Map.of(
							"approval_flag", status,
							"id", creditNoteId
							))
					.log().body()

				.when()
					.patch(CreditNote_Api.approval_CreditNote)

				.then()
					.log().status()
					.log().body()
					.extract().response();

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200, "Expected Status Code");
			
			String approvalStatus = response.jsonPath().getString("approval_flag");
			Assert.assertEquals(approvalStatus.toLowerCase(), status.toLowerCase(),
					"mismatch approval flag");
			
			System.out.println("status :" + status.toUpperCase());
		}

	}

}
