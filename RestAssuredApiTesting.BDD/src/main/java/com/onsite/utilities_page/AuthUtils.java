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
		
		// System.out.println("RAW JSON OBJ: " + secondResponse.getBody().asPrettyString());
		
		// Extract company id
		List<Map<String, Object>> companies =
				secondResponse.jsonPath().getList("user.monkey_patch_company_user");

		String companyId = null;

		for (Map<String, Object> company : companies) {

			boolean validCompanyId = true;

			Integer hiddenFlag = (Integer) company.get("hidden");
			String companyid = (String) company.get("company_id");
			String userRole = (String) company.get("role");
			String userType = (String) company.get("type");
			String userName = (String) company.get("name");
			Long userNumber = (Long) company.get("mobile");
			String roleId = (String) company.get("company_role_id");

			if (hiddenFlag == null || hiddenFlag.intValue() != 0) {
				validCompanyId = false;
			}

			if (companyid == null || companyid.isEmpty()) {
				validCompanyId = false;
			}

			if (!"admin".equals(userRole)) {
				validCompanyId = false;
			}

			if (!"employee".equals(userType)) {
				validCompanyId = false;
			}

			if (userName == null || userName.isEmpty()) {
				validCompanyId = false;
			}

			if (userNumber == null) {
				validCompanyId = false;
			}

			if (validCompanyId) {
				companyId = companyid;
				break;
			}
		}
		
		System.out.println("COMPANY ID FROM LOGIN RESPONSE (NON-DELETED): " + companyId);
		CompanyContext.setCompanyId(companyId);
		return token;

	}
}

