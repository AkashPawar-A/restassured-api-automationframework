package com.onsite.utilities_page;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RateLimitUtils {
	
    public static List<Integer> hitApiMultipleTimes(
            String baseUrl,
            String endpoint,
            String token,
            String requestBody,
            int numberOfRequests) {

        List<Integer> statusCodes = new ArrayList<>();

        for (int i = 1; i <= numberOfRequests; i++) {

            Response response = RestAssured
                    .given()
                    .baseUri(baseUrl)
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(endpoint);

            int statusCode = response.getStatusCode();

            statusCodes.add(statusCode);

            System.out.println(
                    "Request " + i + " → " + statusCode
            );
        }

        return statusCodes;
    }

}
