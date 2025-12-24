package com.onsite.LabourPayroll_testpage;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;

public class Show_Payroll_Test {
	
	@DataProvider(name="payrollIdData")
	public Object[][] getPayrollId() throws Exception, DatabindException, IOException{
		
		String filePath = "src/test/resources/testdata_payroll/PayrollDetail.json";
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonData = mapper.readValue(new File(filePath), Map.class);
		String payrollId = (String) jsonData.get("id");
		
		Object[][] dataObj = new Object[1][1];
		dataObj[0][0] = payrollId;
		
	    return dataObj;
	}
	
	@Test(priority=1, dataProvider="payrollIdData")
	public void showPayroll(String payrollId) {
		
		Response payrollResponse =
				
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("id", payrollId)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.SHOW_PAYROLL)
				
				.then()
				.log().all()
				.extract().response();
		
		String partyIdResponse = payrollResponse.getBody().asString();
		
		int responseStatusCode = payrollResponse.getStatusCode();
		String responseMessage = payrollResponse.jsonPath().getString("message");

		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		String responseContentType = payrollResponse.getContentType();
		System.out.println("response content type :" + responseContentType);
		Assert.assertTrue(responseContentType.contains("application/json"), "response content type is missmatch");
		
		String allowMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("PATCH"), "allows method not added in headerr options ");
		
		long responseTime = payrollResponse.getTime();
		System.out.println("response time is : " + responseTime);
		Assert.assertTrue(responseTime < 2000, "respomse time is to long ");
		
		int payroll_id_hidden_flag = payrollResponse.jsonPath().get("hidden");
		int payroll_id_delete_flag = payrollResponse.jsonPath().get("delete");
		int party_id_hidden_flag = payrollResponse.jsonPath().get("monkey_patch_party_company_user.hidden");
		String partyName = payrollResponse.jsonPath().get("monkey_patch_party_company_user.name");
		
		if(payroll_id_hidden_flag != 1 && payroll_id_delete_flag != 1 
				&& party_id_hidden_flag != 1) {
			System.out.println("party name : " + partyName + " : Payroll party visible in payroll active list");
		}else {
		    System.out.println("party name : " + partyName + " : Payroll party NOT visible in payroll active list");
		}
			
	}

}
