package com.onsite.utilities_page;

import org.json.JSONObject;

import com.onsite.endpoints.ApiBasePath;

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
            .post(ApiBasePath.BASE_URL + "/detail/anon/mobile");

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
            .post(ApiBasePath.BASE_URL + "/login/password/mobile");

        System.out.println("Second API Status: " + secondResponse.getStatusCode());
        System.out.println("Second API Response: " + secondResponse.asString());

        if (secondResponse.getStatusCode() != 200) {
            throw new RuntimeException("Second API failed: " + secondResponse.getStatusLine());
        }

        String token = secondResponse.jsonPath().getString("token");
        System.out.println("Extracted token: " + token);

        return token;
    }
}

