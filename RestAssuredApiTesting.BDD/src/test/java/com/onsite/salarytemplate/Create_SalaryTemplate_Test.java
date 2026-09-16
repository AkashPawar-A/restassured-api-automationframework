package com.onsite.salarytemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.pojo_request.Add_SalaryTemplate;
import com.onsite.pojo_response.SalaryTemplate;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.response.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Create_SalaryTemplate_Test extends BaseToken{
	
	private Response salaryTemplateResponse;
	
	@DataProvider(name="salaryTemplateData")
	public Object[][] getDtata() throws Exception{
		
		String createTemplate_path = "src/test/resources/testdata_salaryTemplate/Create_salaryTemplate.json";
		
		String jsonPayload = new String(Files.readAllBytes(Paths.get(createTemplate_path)));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		Add_SalaryTemplate salaryTemplate_Payload = mapper.readValue(jsonPayload, Add_SalaryTemplate.class);
		
		String finalPayload = mapper.writeValueAsString(salaryTemplate_Payload);
		
		SchemaValidator.validateSchema("requestSchemas_files/SalaryTemplateRequest.json", finalPayload);
		
		return new Object[][] {
			{ finalPayload }
		};
	}
	
	@Test(dataProvider="salaryTemplateData", priority=1)
	public void create_salarytemplate(String finalPayload) throws Exception {
		 
		salaryTemplateResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().uri()
				
				.when()
				.post(SalaryTemplate_Api.IN_ADD_SALARYTEMPLATE)
				
				.then()
				.log().all()
				.extract().response();
		
		SalaryTemplate response_salaryTemplate = salaryTemplateResponse.as(SalaryTemplate.class);
	}
	
	@Test(priority=2, dependsOnMethods="create_salarytemplate")
	public void validStatusCode() {
		
		int ResponseStatusCode = salaryTemplateResponse.getStatusCode();
		
		Assert.assertEquals(ResponseStatusCode, 200, "in/add/salary-templte api fail so response status code :" + ResponseStatusCode);
	}
	
	@Test(priority=3, dependsOnMethods="create_salarytemplate")
	public void validMessage() {
		
		String responseMessage = salaryTemplateResponse.jsonPath().getString("message");
		
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("validation message :" + responseMessage);
		} else {
			System.out.println("validation message null or empty in response");
		}	
	}
	
	@Test(priority=4, dependsOnMethods="create_salarytemplate")
	public void validResponseTime() {
		
		long responseTime = salaryTemplateResponse.getTime();
		
		Assert.assertTrue(responseTime <= 2000, "in/add/salary-template api response time too much long" + responseTime);
	}
	
	@Test(priority=5, dependsOnMethods="create_salarytemplate")
	public void validResponseSchema() throws Exception {
		
		SchemaValidator.validateSchema("responseSchema_files/SalaryTemplateResponse.json", salaryTemplateResponse.asString());
	}

}
