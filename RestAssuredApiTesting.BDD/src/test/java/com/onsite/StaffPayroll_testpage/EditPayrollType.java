package com.onsite.StaffPayroll_testpage;

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

public class EditPayrollType {
	
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
		
		int responseStatusCode = payrolltypeResponse.getStatusCode();
		String responseMessage = payrolltypeResponse.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
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
	
		Integer payroll_DeleteFlag = payrolltypeResponse.jsonPath().get("delete");
		Integer payroll_HiddenFlag = payrolltypeResponse.jsonPath().get("hidden");
		String payroll_Id = payrolltypeResponse.jsonPath().get("id");
		String payroll_CreatorId = payrolltypeResponse.jsonPath().get("creator");
		String payroll_CreatorCompanyUserId= payrolltypeResponse.jsonPath().get("creator_company_user_id");
		String payroll_SalaryBreakupId = payrolltypeResponse.jsonPath().get("salary_breakup_id");
		String payroll_PartyCompanyUserId = payrolltypeResponse.jsonPath().get("party_company_user_id");
		
		String party_UserType = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.type");
		String company_PartyUserId = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.id");
		String party_UserId = payrolltypeResponse.jsonPath().get("user_id");
		String party_UserName = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.name");
		String party_CompanyId = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.company_id");
		Integer party_UserHiddenFlag = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.hidden");
		String workforce_Id = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.workforce_id");
		String recentProjectId = payrolltypeResponse.jsonPath().get("monkey_patch_party_company_user.recent_project_id");
		
		Assert.assertNotNull(payroll.getId(), "Payroll ID is missing in response");
		Assert.assertEquals(payroll.getId(), payrollTypeRequest.getId(), "Payroll ID mismatch");
		
		if(payroll_HiddenFlag != 1 || payroll_DeleteFlag != 1) {
			System.out.println("payroll is not deleted");
		}else {
			System.out.println("payroll is deleted");
		}
		if(party_UserType != null && "staff".equalsIgnoreCase(party_UserType) || "labour".equalsIgnoreCase(party_UserType)) {
			Assert.assertEquals(payroll.getType(), payrollTypeRequest.getType(), "payroll type mismatch");
		}else {
			System.out.println("party user type is not match with staff or labour");
		}
	}

}
