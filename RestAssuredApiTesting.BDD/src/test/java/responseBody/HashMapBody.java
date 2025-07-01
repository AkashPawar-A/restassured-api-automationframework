package responseBody;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class HashMapBody {

	HashMap<String, Object> map = new HashMap<>();
	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik";
	private String projectId;

	@BeforeClass
	public void setup() {
		// Set Base URI
		baseURI = "https://testapi.onsiteteams.in/apis/v3";

		// Minimum valid payload
		map.put("name", "API test project-2");
		map.put("company_id", "75916659-9cbe-4ca7-812e-181a29229772");
		map.put("contractor", "c5b0bdca-6941-4411-aa64-369f6bb8cc32");
		map.put("start_date", "2025-06-10T00:00:00Z");
		map.put("end_date", "2026-05-11T00:00:00Z");
		map.put("address", "pawar mala");
		map.put("city", "Nashik");
		map.put("state", "Maharashtra");
		map.put("status", "Ongoing");
		map.put("estimated_cost", 1000);

		// admins array
		ArrayList<String> admins = new ArrayList<>();
		admins.add("c5b0bdca-6941-4411-aa64-369f6bb8cc32");
		map.put("admins", admins);

		// attendance_radius required hai
		map.put("attendance_radius", 500);

		// Location object
		HashMap<String, Object> location = new HashMap<>();
		location.put("type", "Point");
		location.put("coordinates", new ArrayList<>(java.util.Arrays.asList(1.1, 1.1)));
		map.put("location", location);
	}

	@Test(priority=1)
	public void testProject() {

		Response response = 
			given()
				.header("Authorization", "Bearer " + token)
				.contentType(ContentType.JSON)
				.body(map)
			.when()
				.post("/add/project")
			.then()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.log().all()
				.extract().response();

		projectId = response.jsonPath().getString("id");
		System.out.println("Created project id: " + projectId);
	}

	@Test(priority=2, dependsOnMethods="testProject")
	public void getProjectDetails() {

		given()
		    .header("Authorization", "Bearer " + token)
		.when()
		    .get("/detail/project/" + projectId)
		.then()
		    .statusCode(200)
		    .statusLine("HTTP/1.1 200 OK")
		    .log().all();
	}
}

