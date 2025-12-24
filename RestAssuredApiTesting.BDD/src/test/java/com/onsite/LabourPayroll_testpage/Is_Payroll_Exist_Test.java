package com.onsite.LabourPayroll_testpage;

import io.restassured.http.ContentType;
import java.io.File;
import java.io.IOException;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;

public class Is_Payroll_Exist_Test extends BaseToken{
	
	@DataProvider(name="testData")
	public Object[][] getUserId() throws Exception, DatabindException, IOException{
		
		String filePath = "src/test/resources/testdata_payroll/Create_LabourPayroll.json";
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonData = mapper.readValue(new File(filePath), Map.class);
		String userIds = (String) jsonData.get("party_company_user_id");
		
		Object[][] dataObj = new Object[1][1];
		dataObj[0][0] = userIds;
		
		return dataObj;
	}
	
	@Test(priority=1, dataProvider="testData")
	public void nonExistPayroll(String userId) {		
				
		Response payrollResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("creator_company_user_id", userId)
				.log().uri()
				
				.when()
				.get(Payroll_Api.IS_PAYROLL_EXIST)
				
				.then()
				.log().all()
				.extract().response();
		
		String companyUserResponse = payrollResponse.getBody().asString();
		
		int responseStatusCode = payrollResponse.getStatusCode();
		String responseMessage = payrollResponse.jsonPath().getString("message");
		
		//test vase -> status code validation
		if(responseStatusCode==200) {
			System.out.println("successfuly status code is : " + responseStatusCode 
					+ ": successfull response message : " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode 
					+ ": failure response message : " + responseMessage);		
		}
		
		//test case -> response time validation 
		long responseTime = payrollResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("Actual response time is : " + responseTime);
		}else {
			System.out.println("Response time is too long : " + responseTime);
		}
		
		String responseContentType = payrollResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "content typw is missmatch");
	
		Integer payrollExistFlag = payrollResponse.jsonPath().get("is_exist");
		Integer payrollDeleteFlag = payrollResponse.jsonPath().get("delete");
		Integer companyUserHiddenFlag = payrollResponse.jsonPath().get("payroll.monkey_patch_party_company_user.hidden");
		String companyUserName = payrollResponse.jsonPath().get("payroll.monkey_patch_party_company_user.name");
		
		//test case -> payroll Exist flag validation 
		if(payrollExistFlag == 0 ) {
			System.out.println("user payroll is not exist flag :" + payrollExistFlag + ": user name : " + companyUserName);
		} else {
			Assert.fail("user payroll is exist flag :" + payrollExistFlag + ": user name : " + companyUserName);
		}
		
	}

}
