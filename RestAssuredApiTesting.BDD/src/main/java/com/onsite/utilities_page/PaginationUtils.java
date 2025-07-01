package com.onsite.utilities_page;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class PaginationUtils {

    public static <T> List<T> getAllPaginatedDataAsPojo(
            String endpoint, String dataPathKey, String token, String companyId, Class<T> responseType) {

        int page = 1;
        int limit = 10;
        boolean morePages = true;
        
        List<T> allData = new ArrayList<>();

        while (morePages) {
            Response response =
                given()
                    .baseUri("https://testapi.onsiteteams.in/apis/v3")
                    .header("Authorization", "Bearer " + token)
                    .queryParam("page", page)
                    .queryParam("count", 10)
                    .queryParam("company_id", companyId)
                    .log().all()
                .when()
                    .get(endpoint)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

            // 🔎 Print response JSON
            System.out.println("JSON Response (Page " + page + "):");
            System.out.println(response.asPrettyString());

            List<T> currentPageData = response.jsonPath().getList(dataPathKey, responseType);
            allData.addAll(currentPageData);

            System.out.println("Fetched page " + page + ": " + currentPageData.size() + " records");

            morePages = currentPageData.size() == limit;
            page++;
        }

        return allData;
    }
}

