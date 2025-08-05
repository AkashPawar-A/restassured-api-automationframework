package com.onsite.credit_note_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Common_Api;
import com.onsite.endpoints.CreditNote_Api;
import com.onsite.pojo_request.CreditNoteAddItemRequest;
import com.onsite.pojo_request.CreditNoteCreateRequest;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonDataProvider;

public class CreditNoteAddItemTest extends BaseToken {

	@DataProvider(name = "creditNoteData")
	public Object[][] getCreditNoteData() {
		String creditNotePath = "src/test/resources/test_data/CreditNote_CreateTestData.json";
		String creditItemPath = "src/test/resources/test_data/CreditNoteItem_AddTestData.json";

		Object[][] creditNoteData = JsonDataProvider.getDataFromJson(creditNotePath, CreditNoteCreateRequest.class);
		Object[][] creditNoteItemData = JsonDataProvider.getDataFromJson(creditItemPath, CreditNoteAddItemRequest.class);

		Object[][] combined = new Object[creditNoteData.length][2];
		for (int i = 0; i < creditNoteData.length; i++) {
			combined[i][0] = creditNoteData[i][0];
			combined[i][1] = creditNoteItemData[i][0];
		}
		return combined;
	}

	@Test(dataProvider = "creditNoteData")
	public void addCreditItem(CreditNoteCreateRequest creditNotePayload,
			CreditNoteAddItemRequest creditItemPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		// 1. Create Credit Note
		Response creditNoteResponse = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(creditNotePayload)
				.log().all()
			.when()
				.post(CreditNote_Api.Create_CreaditNote)
			.then()
				.log().all()
				.statusCode(200)
				.extract().response();

		String creditNoteId = creditNoteResponse.jsonPath().getString("id");
		Assert.assertNotNull(creditNoteId, "Credit note id is null");

		// 2. Get Subcategory List
		Response subCategoryResponse = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.queryParam("company_id", companyId)
				.queryParam("type", "costcode")
				.contentType(ContentType.JSON)
				.log().all()
			.when()
				.get(Common_Api.SubCategory);

		System.out.println("SubCategory Response: " + subCategoryResponse.getBody().asString());
		Assert.assertEquals(subCategoryResponse.getStatusCode(), 200, "SubCategory API failed");

		List<Map<String, Object>> subcategoryList = subCategoryResponse.jsonPath().getList("subcategories");
		Assert.assertNotNull(subcategoryList);
		Assert.assertFalse(subcategoryList.isEmpty());

		String subcategoryId = subcategoryList.get(1).get("id").toString();
		System.out.println("Subcategory ID: " + subcategoryId);

		// 3. Prepare item payload
		creditItemPayload.setCredit_note_id(creditNoteId);
		creditItemPayload.setSub_category_id(subcategoryId);

		// 4. Add Credit Note Item
		Response itemResponse = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(Collections.singletonList(creditItemPayload))
				.log().all()
			.when()
				.post(CreditNote_Api.AddItem_CreditNote)
			.then()
				.log().all()
				.statusCode(200)
				.extract().response();

		System.out.println("Item Response: " + itemResponse.getBody().asString());

		String itemId = itemResponse.jsonPath().getString("id"); // Or adjust path if nested/array
		Assert.assertNotNull(itemId, "Credit note item creation failed");
		System.out.println("Credit Note Item created with ID: " + itemId);
	}
}
