package com.onsite.payroll_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.pojo_request.Edit_Project_Associate_Request;
import com.onsite.pojo_response.Payroll_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class EditPayrollAssociateProject {
	
	@DataProvider(name="changeProject")
	public Object[][] payrollData(){
		
		String filepath = "src/test/resources/testdata_payroll/EditPayrollAssociateProject.json";
		
		Object[][] payrollData = JsonDataProvider.getDataFromJson(filepath, Edit_Project_Associate_Request.class);
		
		Object[][] projectChange = new Object[payrollData.length][1];
		for(int i=0; i<projectChange.length; i++) {
			projectChange[i][0] = payrollData[i][0]; 
		}
		return projectChange;
	}
	
	@Test(priority=1, dataProvider="changeProject")
	public void payrollProject(Edit_Project_Associate_Request changeProjectRequest) {
		
		ObjectMapper mapper = new ObjectMapper();
		String finalJsonPayload = null;
		try {
			finalJsonPayload = mapper.writeValueAsString(changeProjectRequest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("final payload :" + finalJsonPayload);
		
		Response payrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.body(finalJsonPayload)
				.contentType(ContentType.JSON)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.EDIT_PAYROLL_ASSOCIATE_PROJECT)
				
				.then()
				.log().all()
				.extract().response();
		
		String responseData = payrollResponse.getBody().asString();
		
		String responseMessage = payrollResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}else {
			System.out.println("response message is null");
		}
		
		int responseStatusCode = payrollResponse.getStatusCode();
		System.out.println("response Status code :" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status coed not ofund in response");

		long responseTime = payrollResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long");
		
		String responseContentType = payrollResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "response content type is missmatch");
		
		String allowedMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowedMethod.contains("PATCH"), "patch method is not allowed");
		
		try {
			Payroll_Response payroll = mapper.readValue(responseData, Payroll_Response.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
