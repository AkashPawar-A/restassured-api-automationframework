package com.onsite.base_page;

import org.testng.annotations.BeforeClass;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Base_Page {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = ConfigReader.getBaseURI();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .addHeader("Authorization", "Bearer " + AuthUtils.getToken())
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
    }
}