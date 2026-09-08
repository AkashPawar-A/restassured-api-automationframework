package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialReturn_Api;
import com.onsite.pojo_request.MaterialReturnRequest;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RateLimit_MaterialRequest_Test extends BaseToken{
	
	private MaterialReturnRequest materialReturnPayload;
	private Response materialReturnResponse;

	@DataProvider(name="materialReturn")
	public Object[][] getData() throws IOException{

		// Material Return main request
		Map<String, Object> materialReturnReq =
				JsonUtils.readJson("src/test/resources/testdata_materialReturn/add_materialReturn.json");

		// Material payload
		List<Map<String, Object>> materials =
				JsonUtils.readJsonArray("src/test/resources/testdata_material/materials.json");

		// Main payload
		materialReturnReq.put("materials", materials);

		// Convert JSON -> POJO = pojo mapping
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		materialReturnPayload = mapper.convertValue(materialReturnReq, MaterialReturnRequest.class);
		
		// POJO → JSON = SERIALIZATION
		String finalPayload = mapper.writeValueAsString(materialReturnPayload);

		return new Object[][] {
			{ finalPayload }
		};
	}
	
	@Test(dataProvider = "materialReturn", priority = 2)
	public void rateLimitTest(String finalPayload) throws Exception {

	    // API Rate Limit Configuration
	    int rateLimit = 10;          // Example: 10 requests
	    int timeWindowSeconds = 60;  // Example: within 60 seconds

	    int successCount = 0;
	    int rateLimitCount = 0;

	    long startTime = System.currentTimeMillis();

	    for (int i = 1; i <= rateLimit + 2; i++) {

	        Response response = RestAssured
	                .given()
	                .baseUri(ApiBasePath.BASE_URL)
	                .header("Authorization", "Bearer " + BaseToken.token)
	                .contentType(ContentType.JSON)
	                .body(finalPayload)
	                .when()
	                .post(MaterialReturn_Api.add_materialReturn);

	        int statusCode = response.getStatusCode();

	        System.out.println(
	                "Request " + i +
	                " | Status Code: " + statusCode
	        );

	        if (statusCode == 200 || statusCode == 201) {
	            successCount++;
	        }

	        if (statusCode == 429) {
	            rateLimitCount++;

	            System.out.println(
	                    "Rate Limit Triggered at Request: " + i
	            );

	            System.out.println(
	                    "Response: " + response.asString()
	            );
	        }
	    }

	    long endTime = System.currentTimeMillis();

	    long totalTime = endTime - startTime;

	    System.out.println("--------------------------------");
	    System.out.println("Total Requests      : " + (rateLimit + 2));
	    System.out.println("Successful Requests : " + successCount);
	    System.out.println("429 Responses       : " + rateLimitCount);
	    System.out.println("Total Time          : " + totalTime + " ms");
	    System.out.println("--------------------------------");

	    // Verify that rate limiting was triggered
	    Assert.assertTrue(
	            rateLimitCount > 0,
	            "Rate limit was not triggered. Expected HTTP 429."
	    );

	    // Verify requests completed within configured time window
	    Assert.assertTrue(
	            totalTime <= (timeWindowSeconds * 1000),
	            "Requests did not complete within the configured rate-limit window."
	    );
	}

}
