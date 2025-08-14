package com.onsite.creditnote_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Common_Api;
import com.onsite.endpoints.CompanyUser;
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
			CreditNoteAddItemRequest creditItemPayload) throws Exception {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		//1. select companyUser
		Response companyUserResponse =
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("priority_type", "customer")
				.log().all()
				
			.when()
				.get(CompanyUser.companyUser)

			.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(companyUserResponse.getStatusCode(), 200, "companyUser response failed");
		
		List<Map<String, Object>> companyUserList = companyUserResponse.jsonPath().getList("data");
		Assert.assertNotNull(companyUserList, "companyUser list is null");
		Assert.assertFalse(companyUserList.isEmpty(), "companyUser list is empty");
		
		//logic :1
		Map<String, Object> selectCompanyUser = companyUserList.get(2);

		String companyUserId = selectCompanyUser.get("id").toString();
		String companyUserName = selectCompanyUser.get("name").toString();
		Object hiddenFlag = selectCompanyUser.get("hidden");
		Object deleteFlag = selectCompanyUser.get("delete");
		int hiddenCompanyUser = hiddenFlag != null ? Integer.parseInt(hiddenFlag.toString()) : 0;
		int deletedCompanyUser = deleteFlag != null ? Integer.parseInt(deleteFlag.toString()) : 0;
		
		Assert.assertNotNull(companyUserId, "company user id is null");
		Assert.assertNotNull(companyUserName, "company user name is null");

		Assert.assertEquals(hiddenCompanyUser, 0, "Selected user is hidden (hidden = 1)");
		Assert.assertEquals(deletedCompanyUser, 0, "Selected user is deleted (delete = 1)");

		System.out.println("Selected Company User Details :");
		System.out.println("ID :" + companyUserId);
		System.out.println("partyName :" + companyUserName);
		System.out.println("IS Hidden :" + hiddenFlag);
		System.out.println("Id Delete :" + deleteFlag);

		// 2. Create Credit Note
		creditNotePayload.setParty_company_user_id(companyUserId);

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

		// 3. Get Subcategory List
		Response subCategoryResponse = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.queryParam("company_id", companyId)
				.queryParam("type", "costcode")
				.contentType(ContentType.JSON)
				.log().all()		
			.when()
				.get(Common_Api.SubCategory)
			.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(subCategoryResponse.getStatusCode(), 200, "SubCategory API failed");

		List<Map<String, Object>> subcategoryList = subCategoryResponse.jsonPath().getList("subcategories");
		Assert.assertNotNull(subcategoryList);
		Assert.assertFalse(subcategoryList.isEmpty());
		//logic :2
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> selectedSubcategory = subcategoryList.get(2);

		String subcategoryDetails = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(selectedSubcategory);
		System.out.println("Selected compamy user :" + subcategoryDetails);

		String subcategoryId = selectedSubcategory.get("id").toString();
		System.out.println("Subcategory ID: " + subcategoryId);
		
		Assert.assertNotNull(subcategoryId, "subCategory id is null");
		
		// 4. select category
		Response categoryResponse = 
			given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("type", "billing_unit")
				.log().all()
			.when()
				.get(Common_Api.ListCategory)
			.then()
				.statusCode(200)
				.log().all()
				.extract().response();

		List<Map<String, Object>> categoryList = categoryResponse.jsonPath().getList("categories");
		Assert.assertNotNull(categoryList, "category list is null");
		Assert.assertFalse(categoryList.isEmpty(), "category list is empty");
		//logic : 3
		int selectedIndex = -1;
		Map<String, Object> selectedCategory = null;
		
		for (int i = 0; i < categoryList.size(); i++) {
			Map<String, Object> category = categoryList.get(i);

			int hiddenCategory = category.get("hidden") != null ? Integer.parseInt(category.get("hidden").toString()) : 0;
			int deletedCategory = category.get("delete") != null ? Integer.parseInt(category.get("delete").toString()) : 0;

			if (hiddenCategory == 0 && deletedCategory == 0) {
				selectedCategory = category;
				selectedIndex = i;
				break;
			}
		}

		if (selectedCategory == null) {
			throw new RuntimeException("No active company user found");
		}

		String categoryId = selectedCategory.get("id").toString();
		System.out.println("Selected Index: " + selectedIndex);
		System.out.println("User ID: " + categoryId);

		// 5. Prepare item payload
		creditItemPayload.setCredit_note_id(creditNoteId);
		creditItemPayload.setSub_category_id(subcategoryId);
		creditItemPayload.setUnit_id(categoryId);

		// 6. Add Credit Note Item
		Response creditItemResponse = 
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

		System.out.println("Item Response: " + creditItemResponse.getBody().asString());

		String creditItemId = creditItemResponse.jsonPath().getString("id"); // Or adjust path if nested/array
		Assert.assertNotNull(creditItemId, "Credit note item creation failed");
		System.out.println("Credit Note Item created with ID: " + creditItemId);
	}
}
