package com.onsite.utilities_page;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.LoginUserApi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthUtils {

	public static String getToken() {

		System.out.println("Base URI: " + ConfigReader.getBaseURI());

		// STEP 1: First API Call - /detail/anon/mobile
		JSONObject firstRequest = new JSONObject();
		firstRequest.put("country_code", ConfigReader.getCountryCode());
		firstRequest.put("mobile", Long.parseLong(ConfigReader.getUsername()));

		System.out.println("First Request Payload: " + firstRequest.toString());

		Response firstResponse = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(firstRequest.toString())
				.post(ApiBasePath.BASE_URL + LoginUserApi.UserNumber);

		System.out.println("First API Status: " + firstResponse.getStatusCode());
		System.out.println("First API Response: " + firstResponse.asString());

		if (firstResponse.getStatusCode() != 200) {
			throw new RuntimeException("First API failed: " + firstResponse.getStatusLine());
		}

		// STEP 2: Second API Call - /login/password/mobile
		JSONObject secondRequest = new JSONObject();
		secondRequest.put("country_code", ConfigReader.getCountryCode());
		secondRequest.put("mobile", Long.parseLong(ConfigReader.getUsername()));
		secondRequest.put("password", ConfigReader.getPassword());

		System.out.println("Second Request Payload: " + secondRequest.toString());

		Response secondResponse = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(secondRequest.toString())
				.post(ApiBasePath.BASE_URL + LoginUserApi.passowrd);

		System.out.println("Second API Status: " + secondResponse.getStatusCode());
		System.out.println("Second API Response: " + secondResponse.asString());

		if (secondResponse.getStatusCode() != 200) {
			throw new RuntimeException("Second API failed: " + secondResponse.getStatusLine());
		}

		//Extract token
		String token = secondResponse.jsonPath().getString("token");
		System.out.println("Extracted token: " + token);
		System.out.println("Token Length : " + token.length());
		System.out.println("Token Parts : " + token.split("\\.").length);

		// Extract Current Company ID
		String currentCompanyId =
		        secondResponse.jsonPath().getString("user.current_company_id");

		// Extract Company List
		List<Map<String, Object>> companies =
		        secondResponse.jsonPath().getList("user.monkey_patch_company_user");

		String companyId = null;

		for (Map<String, Object> company : companies) {

		    String id = (String) company.get("company_id");

		    Number hidden = (Number) company.get("hidden");
		    int hiddenFlag = hidden != null ? hidden.intValue() : -1;

		    if (currentCompanyId.equals(id) && hiddenFlag == 0) {
		        companyId = id;
		        break;
		    }
		}

		// Validation
		if (companyId == null) {
		    throw new RuntimeException("Current company not found or company is hidden.");
		}

		System.out.println("CURRENT COMPANY ID : " + companyId);

		// Save in CompanyContext
		CompanyContext.setCompanyId(companyId);
		return token;

	}
}

