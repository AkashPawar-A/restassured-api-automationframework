package com.onsite.salarybreakupcomponant_testpage;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryBreakupComponant_Api;
import com.onsite.pojo_request.Salary_BreakupRequest;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class AddSalaryBreakupComponant_test_page {
	
	@DataProvider(name="salaryTempalteComponant")
	public Object[][] dataprovider(){
		
		String salaryComponantFilePath = "src/test/resources/testdata_salaryBreakupComponant/Create_SalaryBreakupComponanat.json";
		
		Object[][] templateComponantData = JsonDataProvider.getDataFromJson(salaryComponantFilePath, Salary_BreakupRequest.class);
		
		Object[][] dataObject = new Object[templateComponantData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = templateComponantData[i][0];
		}
		return dataObject;
	}
	
	@Test(priority=1)
	public void create_salarycomponant(Salary_BreakupRequest salaryTemplateComponanat) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String finalPayload = mapper.writeValueAsString(salaryTemplateComponanat);
		System.out.println("finl payload :\n" + finalPayload);
		
		Response createSalaryComponant = 
				given()
				.basePath(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().uri()
				
				.when()
				.post(SalaryBreakupComponant_Api.ADD_SALARYBREAKUP_COMPONENT)
				
				.then()
				.extract().response();
		
		String responsedata = createSalaryComponant.getBody().asString();
		System.out.println("final response print :\n" + responsedata);
		
		int responseStatusCode = createSalaryComponant.getStatusCode();
		System.out.println("response status code :" + responseStatusCode);
		Assert.assertEquals(createSalaryComponant.getStatusCode(), 200, 
				"expected status code not found is response");
		
		String responseMessage = createSalaryComponant.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}
		
		long responsetime = createSalaryComponant.getTime();
		System.out.println("response time :" + responsetime);
		
		String allowedMethod = createSalaryComponant.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowedMethod.contains("POST"), "expected method is not allowed");
		
		String allowedContentType = createSalaryComponant.getHeader("Content-Type");
		Assert.assertTrue(allowedContentType.contains("application/json"), "expected contenttype si not allowed");
		
		List<String> updateFiles = Arrays.asList(
				"src/test/resources/testdata_salaryTemplate/Create_salaryTemplate.json"
				);
					
	}

}
