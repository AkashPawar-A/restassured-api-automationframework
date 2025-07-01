package responseBody;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

public class ProjectTest {
	
	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik";
	private String projectId;
	
	@BeforeClass
	public void setUp() {
		baseURI = "https://testapi.onsiteteams.in/apis/v3";
	}
	
	@Test(priority=1)
	public void craeteProject() {
		
		SerializationDeserialization testObj = new SerializationDeserialization();

		testObj.setName("API Test Project - t5");
		testObj.setCompany_id("75916659-9cbe-4ca7-812e-181a29229772");
		testObj.setContractor("c5b0bdca-6941-4411-aa64-369f6bb8cc32");
		testObj.setStart_date("2025-06-10T00:00:00Z");
		testObj.setEnd_date("2026-05-11T00:00:00Z");
		testObj.setAddress("Pawar Mala");
		testObj.setCity("Nashik");
		testObj.setState("Maharashtra");
		testObj.setStatus("Ongoing");
		testObj.setEstimated_cost(5000);
		testObj.setAttendance_radius(400);
		testObj.setAdmins(Arrays.asList("c5b0bdca-6941-4411-aa64-369f6bb8cc32"));

		// Location set karo
		SerializationDeserialization.Location loc = new SerializationDeserialization.Location();
		loc.setType("Point");
		loc.setCoordinates(Arrays.asList(1.1, 1.1));
		testObj.setLocation(loc);
		
		Response response = 
		given()
		   .header("Authorization", "Bearer " + token)
		   .contentType(ContentType.JSON)
		   .body(testObj)
		   
	    .when()
		   .post("/add/project")
		   
		.then()
		   .statusCode(200)
		   .statusLine("HTTP/1.1 200 OK")
		   .log().all()
		   .extract().response();
		
		projectId = response.jsonPath().getString("id");
		System.out.println("created project id :" + projectId);
	}
	
	@Test(priority=2, dependsOnMethods="craeteProject")
	public void getProject() {
		
		SerializationDeserialization check =
			  given()
			      .header("Authorization", "Bearer " + token)
			      .contentType(ContentType.JSON)
			  .when()
			      .get("/detail/project/" + projectId)
			  .then()
			      .statusCode(200)
			      .extract()
			      .as(SerializationDeserialization.class);
	}
}
