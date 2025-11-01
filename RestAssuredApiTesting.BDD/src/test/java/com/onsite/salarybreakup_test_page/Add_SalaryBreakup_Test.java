package com.onsite.salarybreakup_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.onsite.context.SalaryBreakupDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryBreakup_Api;
import com.onsite.pojo_request.Salary_BreakupRequest;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import static io.restassured.RestAssured.*;

import java.io.FileReader;
import java.io.IOException;

public class Add_SalaryBreakup_Test {
	
	private Salary_BreakupRequest salaryBreakup_id;
	
	String filePath = "src/test/resources/testdata_salarybreakup/Create_SalaryBreakup.json";
	
	@DataProvider(name="salaryData")
	public Object[][] salaryData(){
		
		Object[][] salaryBreakupData = JsonDataProvider.getDataFromJson(filePath, Salary_BreakupRequest.class);
		
		Object[][] dataObject = new Object[salaryBreakupData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = salaryBreakupData[i][0];
		}
		return dataObject;
	}

	@Test(priority=1, dataProvider="salaryData")
	public void salaryBreakup(Salary_BreakupRequest salaryBreakupRequest) throws IOException, ParseException {

		//Step 1: Read JSON dynamically before test runs
		String breakupFilePath  = "src/test/resources/testdata_salarybreakup/Create_SalaryBreakup.json";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(breakupFilePath));
		JSONObject jsonObject = (JSONObject) obj;

		String partyCompanyUserId = (String) jsonObject.get("company_user_id");
		String payrollId = (String) jsonObject.get("payroll_id");

		System.out.println("party_company_user_id: " + partyCompanyUserId);
		System.out.println("payroll_id: " + payrollId);

		//Step 2: Prepare request
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(salaryBreakupRequest);
		System.out.println("Final JSON payload : " + jsonPayload);
		
		if(salaryBreakupRequest.getPayroll_id()==null || salaryBreakupRequest.getPayroll_id().isEmpty()) {
			throw new IllegalArgumentException("payroll id is null or empty");
		}
		if(salaryBreakupRequest.getCompany_user_id()==null || salaryBreakupRequest.getCompany_user_id().isEmpty()) {
			throw new IllegalArgumentException("company user id is null or empty");
		}
		
		Response salaryBreakupResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(jsonPayload)
				.log().uri()
				
				.when()
				.post(SalaryBreakup_Api.ADD_SALARYBREAKUP)
				
				.then()
				.extract().response();
		
		System.out.println("Response body : " + salaryBreakupResponse.getBody().asString());
		
		System.out.println("Response status code :" + salaryBreakupResponse.getStatusCode());
		Assert.assertEquals(salaryBreakupResponse.getStatusCode(), 200, "Expected status code not found in response");
		
		long responseTime = salaryBreakupResponse.getTime();
		Assert.assertTrue(salaryBreakupResponse.getTime() < 2000, "response time is too long");
		
		String responseMessage = salaryBreakupResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("Response message :" + responseMessage);
		}
		
		String contentType = salaryBreakupResponse.getHeader("Content-Type");
		Assert.assertTrue(contentType.contains("application/json"), "contentType is mismatch");
		
		String allowMethod = salaryBreakupResponse.getHeader("Access-Control-Allow-Methods");
		if (allowMethod == null)
		    allowMethod = salaryBreakupResponse.getHeader("access-control-allow-methods");
		if(allowMethod != null)
		    Assert.assertTrue(allowMethod.contains("POST"), "POST method not allowed");	
		
		String salaryBreakupId = salaryBreakupResponse.jsonPath().getString("id");
		System.out.println("SalaryBreakupId : " + salaryBreakupId);
		salaryBreakupRequest.setId(salaryBreakupId);
		this.salaryBreakup_id = salaryBreakupRequest;
		
		SalaryBreakupDetails.id = salaryBreakupResponse.jsonPath().get("id");
		SalaryBreakupDetails.creator_company_user_id = salaryBreakupResponse.jsonPath().get("creator_company_user_id");
		SalaryBreakupDetails.company_id = salaryBreakupResponse.jsonPath().get("company_id");
		SalaryBreakupDetails.creator = salaryBreakupResponse.jsonPath().get("creator");
		SalaryBreakupDetails.payroll_id = salaryBreakupResponse.jsonPath().get("payroll_id");
		SalaryBreakupDetails.sub_category_id = salaryBreakupResponse.jsonPath().get("sub_category_id");
		SalaryBreakupDetails.template_id = salaryBreakupResponse.jsonPath().get("template_id");
		SalaryBreakupDetails.type = salaryBreakupResponse.jsonPath().get("type");
	}
	
	@Test(priority=2, dependsOnMethods="salaryBreakup")
	public void detailsSalaryBreakup() throws Exception {
		
		if (salaryBreakup_id == null || salaryBreakup_id.getId() == null || salaryBreakup_id.getId().isEmpty()) {
		    throw new IllegalArgumentException("salary breakup iss is null or empty");
		}
        
        String id = salaryBreakup_id.getId();
        System.out.println("salary breakup id : " + id);
		
		Response DetailsalaryBreakupResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", SalaryBreakupDetails.id)
				.log().uri()
				
				.when()
				.get(SalaryBreakup_Api.DETAIL_SALARYBREAKUP)
				
				.then()
				.extract().response();
		
		String rawJson = DetailsalaryBreakupResponse.getBody().asString();
		System.out.println(" json response :" + rawJson);
		
		String responseMessage = DetailsalaryBreakupResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}
		
		System.out.println("response statuc code : " + DetailsalaryBreakupResponse.getStatusCode());
		Assert.assertEquals(DetailsalaryBreakupResponse.getStatusCode(), 200, "expected status code not found");
		
		long responseTime = DetailsalaryBreakupResponse.getTime();
		System.out.println("response time :" + responseTime);
		Assert.assertTrue(responseTime < 2000, "response time is too long");
		
		String contentType = DetailsalaryBreakupResponse.getHeader("content-type");
		Assert.assertTrue(contentType.contains("application/json"), "contentType is not match");
		
		String allowMethod = DetailsalaryBreakupResponse.getHeader("access-control-allow-methods");
		if(allowMethod != null) {
			Assert.assertTrue(allowMethod.contains("GET"), "get method is ot allow");
		}
		
		com.onsite.pojo_response.SalaryBreakup salarybreakupObj =
			    DetailsalaryBreakupResponse.as(com.onsite.pojo_response.SalaryBreakup.class);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		com.onsite.pojo_response.SalaryBreakup salarybreakupRes =
		        mapper.readValue(rawJson, com.onsite.pojo_response.SalaryBreakup.class);
		String formattedJson = mapper.writeValueAsString(salarybreakupRes);
		System.out.println("final response : " + formattedJson);
		
	}
}
