package com.onsite.mockserver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.RestAssured;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServerTest {
	
    WireMockServer wireMockServer;

    @BeforeClass
    public void setup() {
        wireMockServer = new WireMockServer(8089); // Port can be changed if needed
        wireMockServer.start();
        configureFor("localhost", 8089);

        //Stub 1: Stubbed Response
        stubFor(get(urlEqualTo("/mock-api/user/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 1, \"name\": \"Akash Pawar\" }")));
        
        //Stub 2: Credit Note API
        stubFor(get(urlEqualTo("/mock-api/credit-note/123"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withBody("{ \"id\": \"123\", \"amount\": 1000 }")));
        
        //Stub 3: Project API
        stubFor(get(urlEqualTo("/mock-api/project/456"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("{ \"project_id\": \"456\", \"name\": \"Tower Construction\" }")));

        //Point RestAssured to your mock server
        RestAssured.baseURI = "http://localhost:8089";
    }

    @Test
    public void testMockedUserAPI() {
        RestAssured
            .given()
            .when()
                .get("/mock-api/user/1")
            .then()
                .statusCode(200)
                .log().body();
    }

    @AfterClass
    public void teardown() {
        if (wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

}
