package Testing;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class UpdateProject {
	
	HashMap<String, Object> map = new HashMap<>();
	
	String updateName = RestUtils.getProjectName();
	String projectId = "6d768230-4897-4f7c-b1be-5f7ef3d10f31";
	
	@BeforeClass
	public void putProjectTest() {
		map.put("name", updateName);
		
		RestAssured.baseURI = "https://testapi.onsiteteams.in";
		RestAssured.basePath = "/apis/v3/edit/project/" + projectId; 
	}
	
	@Test
	public void testput() {
		given()
		    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik")
		    .contentType("application/json; charset=utf-8")
		    .body(map)
		.when()
		    .patch()
		.then()
		    .log().all()
		    .statusCode(200);	
	}
}