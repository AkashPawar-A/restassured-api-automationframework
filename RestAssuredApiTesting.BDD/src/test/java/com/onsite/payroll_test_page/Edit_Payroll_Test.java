package com.onsite.payroll_test_page;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.onsite.context.PayrollDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.pojo_request.Add_Payroll_Request;
import com.onsite.pojo_response.Payroll_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.parser.ParseException;

public class Edit_Payroll_Test {
	
	private Add_Payroll_Request editPayrollRequest;
	
	@DataProvider(name="editPayrollData")
	public Object[][] editpayrolldata() {
		
		String editPayrollPath = "src/test/resources/testdata_payroll/Edit_Payroll.json";
		Object[][] editPayrollData= JsonDataProvider.getDataFromJson(editPayrollPath, Add_Payroll_Request.class);
		
		Object[][] editobj = new Object[editPayrollData.length][1];
		for(int i=0; i<editobj.length; i++) {
			editobj[i][0] = editPayrollData[i][0];
		}
		return editobj;
	}
	
	@Test(priority=1, dataProvider = "editPayrollData")
	public void editPayroll(Add_Payroll_Request request) throws IOException, ParseException {
	    
		this.editPayrollRequest = request;
		
	    // Read IDs from file
	    String editDataPath = "src/test/resources/testdata_payroll/Edit_Payroll.json";
	    JSONObject jsonObj = new JSONObject(Files.readString(Paths.get(editDataPath)).trim());
	    
	    String id = jsonObj.optString("id", "");
	    String workforceId = jsonObj.optString("workforce_id", "");
	    String salaryBreakupId = jsonObj.optString("salary_breakup_id", "");
	    
	    System.out.println("ID : " + id);
	    System.out.println("Workforce ID: " + workforceId);
	    System.out.println("Salary Breakup ID: " + salaryBreakupId);
	    
	    if (id.isEmpty()) {
	        throw new RuntimeException("Payroll ID missing in Edit_Payroll.json");
	    }
	    
	    // Merge JSON with request data
	    ObjectMapper mapper = new ObjectMapper();
	    JSONObject editPayloadObj = new JSONObject(mapper.writeValueAsString(editPayrollRequest));
	    editPayloadObj.put("id", id);
	    editPayloadObj.put("workforce_id", workforceId);
	    editPayloadObj.put("salary_breakup_id", salaryBreakupId);
	    
	    String finalEditPayload = editPayloadObj.toString(4);
	    System.out.println("Final PATCH Payload:\n" + finalEditPayload);
		
		Response editPayrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(finalEditPayload)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.EDIT_PAYROLL)
				
				.then()
				.extract().response();
		
		String editResponse = editPayrollResponse.getBody().asString();
		System.out.println("Final PATCH Payload:\n" + finalEditPayload);
		
		int responseStatusCode = editPayrollResponse.getStatusCode();
		String responseMessage = editPayrollResponse.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		long responseTime = editPayrollResponse.getTime();
		System.out.println("response time :" + responseTime);
		Assert.assertTrue(editPayrollResponse.getTime() < 2000, "response time is too long");
		
		String contentType = editPayrollResponse.getHeader("Content-Type");
		Assert.assertTrue(contentType.contains("application/json"), "content type header is missmatch");
		
		String allowMethod = editPayrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("PATCH"), "patch method is not allowed");
		
		Payroll_Response editObj = editPayrollResponse.as(Payroll_Response.class);
		System.out.println("edited response :" + editObj);
		
		ObjectMapper objprint = new ObjectMapper();
		objprint.enable(SerializationFeature.INDENT_OUTPUT);
		String editRes = objprint.writeValueAsString(editObj);
		System.out.println("final edited response :" + editRes);	
		
		String editPayrollId_1 = editPayrollResponse.jsonPath().getString("id");
		System.out.println("Edited Payroll ID: " + editPayrollId_1);
		
		editPayrollRequest.setId(editPayrollId_1);
		PayrollDetails.id = editPayrollId_1;
		
	}
	
	@Test(priority=2, dependsOnMethods="editPayroll")
	public void editDetailPayroll() throws Exception {

		String editPayrollId_2 = editPayrollRequest.getId();
		
		if(PayrollDetails.id==null || PayrollDetails.id.isEmpty()) {
			throw new IllegalArgumentException("");
		}

		Response editDetailPayrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", editPayrollId_2)
				.log().all()

				.when()
				.get(Payroll_Api.DETAIL_PAYROLL)

				.then()
				.extract().response();

		String jsonResponse = editDetailPayrollResponse.getBody().asString();
		System.out.println("final json response : " + jsonResponse);

		String message = editDetailPayrollResponse.jsonPath().getString("message");
		if(message != null) {
			System.out.println("Response message : " + message);
		}

		int statusCode = editDetailPayrollResponse.getStatusCode();
		System.out.println("final stataus code :" + statusCode);
		Assert.assertEquals(editDetailPayrollResponse.getStatusCode(),  200, 
				"expected statsus not foud in response" + editDetailPayrollResponse.getStatusCode());

		long responseTime = editDetailPayrollResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");

		String responseContentType = editDetailPayrollResponse.getHeader("Content-Type");
		Assert.assertTrue(responseContentType.contains("application/json"), "content type header missmatch");

		String allowMethod = editDetailPayrollResponse.getHeader("Access-Control-Allow-Methods");
		if(allowMethod != null) {
			Assert.assertTrue(allowMethod.contains("GET"), "get method is ot allow");
		}

		String json_PayrollResponse = editDetailPayrollResponse.asString();
		SchemaValidator.validateSchema("schemas_files/Payroll.json", json_PayrollResponse);

		Payroll_Response payrollObj = editDetailPayrollResponse.as(Payroll_Response.class);

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = mapper.writeValueAsString(payrollObj);
		System.out.println("Payroll Object as JSON:\n" + jsonString);

		Assert.assertEquals(payrollObj.getId(), PayrollDetails.id, "Payrolll id mismatch");
		Assert.assertEquals(payrollObj.getCreator(), PayrollDetails.creator_id, "creator id is mismatch");
		Assert.assertEquals(payrollObj.getCreator_company_user_id(), PayrollDetails.creator_company_user_id, "creator company id missmatch");
		Assert.assertEquals(payrollObj.getCompany_id(), PayrollDetails.company_id, "copany id missmatch");
		Assert.assertEquals(payrollObj.getSalary_breakup_id(), PayrollDetails.salary_breakup_id, "salary breakup id mismatch");
		Assert.assertEquals(payrollObj.getParty_company_user_id(), PayrollDetails.party_company_user_id, "party compnay user id missmatch");
		Assert.assertEquals(payrollObj.getProject_ids(), PayrollDetails.project_ids, "project id mismatch");
		Assert.assertEquals(payrollObj.getType(), PayrollDetails.payroll_type, "payroll type is missmatch");
		
		PayrollDetails.id = editDetailPayrollResponse.jsonPath().get("id");
		PayrollDetails.workforce_id = editDetailPayrollResponse.jsonPath().get("workforce_id");
		PayrollDetails.salary_breakup_id = editDetailPayrollResponse.jsonPath().get("salary_breakup_id");
	
	}

}
