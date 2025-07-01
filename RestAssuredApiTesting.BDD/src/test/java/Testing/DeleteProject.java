package Testing;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteProject {

	private String baseURI = "https://testapi.onsiteteams.in";
	private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NTQ5Mzk5MTUsImlkIjoiYzViMGJkY2EtNjk0MS00NDExLWFhNjQtMzY5ZjZiYjhjYzMyIiwidXVpZCI6ImU2ZjE3YzliLWYzZWEtNGNjYy05N2E2LTVjY2IyNGZjOGYyNSJ9.ZhvbK8PV3cvM6OSOLNeFrcIW0ABBY6jyi3xbkQVYiik";
	private String projectId = "6d768230-4897-4f7c-b1be-5f7ef3d10f31";

	@BeforeClass
	public void setUp() {
		
		RestAssured.baseURI = baseURI;
	}

	@Test
	public void deleteProjectTest() {

		Response response = 

				given()
				     .header("Authorization", "Bearer " + token)
				     
				.when()
				     .delete("/apis/v3/delete/project/" + projectId)

				.then()
				     .statusCode(200)
				     .statusLine("HTTP/1.1 200 OK")
				     .log().all()
				     .extract().response();
 
		String jsonString = response.asString();
		Assert.assertEquals(jsonString.contains("successfully delete project"), true);
	}
	
	/*   RestAssured.config = RestAssured.config()
	    		  .connectionConfig(connectionConfig().closeIdleConnectionsAfterEachResponse())
	    		  .httpClient(httpClientConfig()
	    		      .setParam("http.connection.timeout", 5000)
	    		      .setParam("http.socket.timeout", 5000)
	    		      .setParam("http.connection-manager.timeout", 5000L));
    */
}
