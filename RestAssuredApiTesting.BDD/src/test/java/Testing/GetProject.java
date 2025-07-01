package Testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class GetProject {

	@Test
	public void GetProjectTest() {
		
		Response response = given()
		     .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik")

		.when()
		     .get("https://testapi.onsiteteams.in/apis/v3/detail/project/3de02729-9026-45cf-afd2-cedb049e1cea");

		response.then()
		     .statusCode(200)
		     .statusLine("HTTP/1.1 200 OK")
		     .log().all()
		     .assertThat().body("name", equalTo("Api test project_2 test"))
		     .header("Content-Type", containsString("application/json"));
		
		System.out.println(response.getBody().asString());
	}
}
