package com.onsite.salarytemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.pojo_response.SalaryTemplate;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Delete_SalaryTemplate extends BaseToken{
		
	private Response DeleteSalaryTemplateResponse;
	
	@DataProvider(name="salaryTemplateData")
	public Object[][] getData() throws Exception{
		
		String filePath = "src/test/resources/testdata_salaryTemplate/Details.CreateTest_SalaryTemplate.json";
		String jsonPayload = new String(Files.readAllBytes(Paths.get(filePath)));
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(jsonPayload);
		String salaryTemplateId = jsonNode.get("id").asText();
		
		System.out.println("Salary Breakup id :" + salaryTemplateId);

		return new Object[][] {
			{salaryTemplateId}
		};
	}
	
	@Test(priority=1, dataProvider="salaryTemplateData")
	public void deleteSalaryTemplate(String salaryTemplateId) {
		
		DeleteSalaryTemplateResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("id", salaryTemplateId)
				.log().uri()
				
				.when()
				.delete(SalaryTemplate_Api.DELETE_SALARYTEMPLATE)
				
				.then()
				.log().all()
				.extract().response();
		
		SalaryTemplate DeleteSalaryTemplate = DeleteSalaryTemplateResponse.as(SalaryTemplate.class);
	}
	
	@Test(priority=2, dependsOnMethods="deleteSalaryTemplate")
	public void validStatusCode() {

		int ResponseStatusCode = DeleteSalaryTemplateResponse.getStatusCode();

		Assert.assertEquals(ResponseStatusCode, 200, "add/salary-templte api fail so response status code :" + ResponseStatusCode);
	}

	@Test(priority=3, dependsOnMethods="deleteSalaryTemplate")
	public void validMessage() {

		String responseMessage = DeleteSalaryTemplateResponse.jsonPath().getString("message");

		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("validation message :" + responseMessage);
		} else {
			System.out.println("validation message null or empty in response");
		}	
	}

	@Test(priority=4, dependsOnMethods="deleteSalaryTemplate")
	public void validResponseTime() {

		long responseTime = DeleteSalaryTemplateResponse.getTime();

		Assert.assertTrue(responseTime <= 2000, "add/salary-template api response time too much long" + responseTime);
	}
	
	
}
