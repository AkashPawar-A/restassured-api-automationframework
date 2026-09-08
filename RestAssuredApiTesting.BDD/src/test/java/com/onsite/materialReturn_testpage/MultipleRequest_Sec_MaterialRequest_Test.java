package com.onsite.materialReturn_testpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

public class MultipleRequest_Sec_MaterialRequest_Test extends BaseToken{
	
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
	
	@Test(dataProvider = "materialReturn", priority = 1)
	public void multipleRequestsWithinOneSecond(String finalPayload) throws Exception {

	    int requestCount = 10;

	    ExecutorService executorService = Executors.newFixedThreadPool(requestCount);

	    long startTime = System.currentTimeMillis();

	    List<Future<Response>> responses = new ArrayList<>();

	    for (int i = 1; i <= requestCount; i++) {

	        Future<Response> response = executorService.submit(() ->

	            RestAssured
	                .given()
	                .baseUri(ApiBasePath.BASE_URL)
	                .header("Authorization", "Bearer " + BaseToken.token)
	                .contentType(ContentType.JSON)
	                .body(finalPayload)
	                
	                .when()
	                .post(MaterialReturn_Api.add_materialReturn)
	        );

	        responses.add(response);
	    }

	    int successCount = 0;

	    for (Future<Response> future : responses) {

	        Response response = future.get();

	        if (response.getStatusCode() == 200) {
	            successCount++;
	        }
	    }

	    long endTime = System.currentTimeMillis();

	    long totalTime = endTime - startTime;

	    executorService.shutdown();

	    System.out.println("Total Requests : " + requestCount);
	    System.out.println("Successful Requests : " + successCount);
	    System.out.println("Total Time : " + totalTime + " ms");

	    Assert.assertEquals(
	            successCount,
	            requestCount,
	            "Some requests failed"
	    );

	    Assert.assertTrue(
	            totalTime <= 1000,
	            "10 requests took more than 1 second"
	    );
	}

}
