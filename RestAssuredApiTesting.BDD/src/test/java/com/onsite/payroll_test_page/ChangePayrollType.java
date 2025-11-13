package com.onsite.payroll_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.pojo_request.Edit_payroll_type_Request;
import com.onsite.pojo_response.Payroll_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangePayrollType {
	
	@DataProvider(name="changePayrollType")
	public Object[][] getPayrolllData(){
		
		String payrollfilePath = "src/test/resources/testdata_payroll/Change_Payroll_Type.json";
		
		Object[][] payrollData = JsonDataProvider.getDataFromJson(payrollfilePath, Edit_payroll_type_Request.class);
		
		Object[][] changeType = new Object[payrollData.length][1];
		for(int i=0; i<changeType.length; i++) {
			changeType[i][0] = payrollData[i][0];
		}
		return changeType;
	}
	
	@Test(priority=1, dataProvider="changePayrollType")
	public void payrollType(Edit_payroll_type_Request payrollTypeRequest) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		String finalJsonPayload = mapper.writeValueAsString(payrollTypeRequest);
		System.out.println("final json payload : " + finalJsonPayload);
		
		Response payrolltypeResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.body(finalJsonPayload)
				.contentType(ContentType.JSON)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.EDIT_PAYROLL_TYPE)
				
				.then()
				.log().all()
				.extract().response();
		
		String payrollResponse = payrolltypeResponse.getBody().asString();
		
		String responseMessage = payrolltypeResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}else {
			System.out.println("response message null in response");
		}
		
		int responseStatusCode = payrolltypeResponse.getStatusCode();
		System.out.println("response status code :" + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status code not found in response");
		
		long responseTime = payrolltypeResponse.getTime();
		System.out.println("Response Time : " + responseTime);
		Assert.assertTrue(responseTime < 2000, "resonse time is too long");
		
		String responseContentType = payrolltypeResponse.getContentType();
		Assert.assertTrue(responseContentType.contains("application/json"), "response contenttype is missmatch");
		
		String allwedMethod = payrolltypeResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allwedMethod.contains("PATCH"), "allowed method is missmatch");
		
		Payroll_Response payroll = mapper.readValue(payrollResponse, Payroll_Response.class);
		
		String jsonResponse = payrolltypeResponse.asString();
		try {
		    SchemaValidator.validateSchema("schemas_files\\Payroll_Type.json", jsonResponse);
		    System.out.println("JSON Schema validation passed successfully");
		} catch (Exception e) {
		    Assert.fail("Schema validation failed: " + e.getMessage());
		}
		
		Assert.assertNotNull(payroll.getId(), "Payroll ID is missing in response");
		Assert.assertEquals(payroll.getId(), payrollTypeRequest.getId(), "Payroll ID mismatch");
		
		Integer payrollDeleteFlag = payrolltypeResponse.jsonPath().get("delete");
		Integer payrollHiddenFlag = payrolltypeResponse.jsonPath().get("hidden");
		if(payrollHiddenFlag != 1 || payrollDeleteFlag != 1) {
			System.out.println("payroll is not deleted");
		}else {
			System.out.println("payroll is deleted");
		}
		
		String partyUserType = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.type");
		if(partyUserType != null && "staff".equalsIgnoreCase(partyUserType) || "labour".equalsIgnoreCase(partyUserType)) {
			Assert.assertEquals(payroll.getType(), payrollTypeRequest.getType(), "payroll type mismatch");
		}else {
			System.out.println("party user type is not match with staff or labour");
		}
	}

}
