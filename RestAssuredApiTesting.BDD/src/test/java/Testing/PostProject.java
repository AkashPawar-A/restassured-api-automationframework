package Testing;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class PostProject {

    HashMap<String, Object> map = new HashMap<>();
    String updateName =  RestUtils.getProjectName();

    @BeforeClass
    public void postProjectTest() {
        map.put("projectId", RestUtils.getProjectName());
        
        RestAssured.baseURI = "https://testapi.onsiteteams.in";
        RestAssured.basePath = "/apis/v3/add/project";
    }

    @Test
    public void testPost() {
        given()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik")
            .contentType("application/json")
            .body(map)
        .when()
            .post()
        .then()
            .log().all()
            .statusLine("HTTP/1.1 200 OK")
            .statusCode(anyOf(is(200), is(201)));
    }
}

