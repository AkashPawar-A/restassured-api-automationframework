package com.onsite.payroll_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.pojo_request.Edit_Project_Associate_Request;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class EditPayrollDeassociateProject {
	
	@DataProvider(name="removeProject")
	public Object[][] testData(){
		
		String filePath = "src/test/resources/testdata_payroll/EditPayrollDeassociateProject.json";
		Object[][] deassociateProject = JsonDataProvider.getDataFromJson(filePath, Edit_Project_Associate_Request.class);
		
		Object[][] testData = new Object[deassociateProject.length][1];
		for(int i=0; i<deassociateProject.length; i++) {
			testData[i][0] = deassociateProject[i][0];
		}
		return testData;
	}
	
	@Test(priority=1, dataProvider="removeProject")
	public void removePayrollProject(Edit_Project_Associate_Request editProject) {
		
		ObjectMapper mapper = new ObjectMapper();
		String finalJsonPayload = null;
		try {
			finalJsonPayload = mapper.writeValueAsString(editProject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("final json payload :" + finalJsonPayload);
		
		Response payrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.body(finalJsonPayload)
				.contentType(ContentType.JSON)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.EDIT_PAYROLL_DEASSOCIATE_PROJECT)
				
				.then()
				.log().all()
				.extract().response();
		
		String responseData = payrollResponse.getBody().asString();
		
		int responseStatusCode = payrollResponse.getStatusCode();
		String responseMessage = payrollResponse.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("response status code :" + responseStatusCode);;
			System.out.println("successfull message in response :" + responseMessage);
		} else {
			Assert.fail("failure status code :" + responseStatusCode + ": failure message in response :" + responseMessage);;
		}
		
		long responseTime = payrollResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("actual response time :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
		
		String responseContenttype = payrollResponse.getContentType();
		if("application/json; charset=utf-8".contains(responseContenttype)) {
			System.out.println("response content type is match");
		} else {
			Assert.fail("content type is missmatch in response");
		}
		
		String allowedMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		if(allowedMethod.contains("PATCH")) {
			System.out.println("patch method is allowed in header");
		} else {
			Assert.fail("patch method is not allowed in header");
		}
	}
	
}
