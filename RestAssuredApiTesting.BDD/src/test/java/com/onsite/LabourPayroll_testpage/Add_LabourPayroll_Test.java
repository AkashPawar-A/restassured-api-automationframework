package com.onsite.LabourPayroll_testpage;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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

public class Add_LabourPayroll_Test {

	@DataProvider(name="LabourPayrollData")
	public Object[][] getpayrollData(){

		String addPayrollPath = "src/test/resources/testdata_payroll/Labour_Create_Payroll.json";

		Object[][] payrollData = JsonDataProvider.getDataFromJson(addPayrollPath, Add_Payroll_Request.class);

		Object[][] dataObject = new Object[payrollData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = payrollData[i][0];
		}
		return dataObject;
	}

	@Test(priority=1, dataProvider="LabourPayrollData")
	public void createLabourPayroll(Add_Payroll_Request addPayrollRequest) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(addPayrollRequest);
		System.out.println("Payroll Object as JSON:\n" + jsonPayload);

		Response addPayrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(jsonPayload)
				.log().all()

				.when()
				.post(Payroll_Api.ADD_PAYROLL)

				.then()
				.extract().response();

		System.out.println("Response Body: " + addPayrollResponse.getBody().asString());

		String responseMessage = addPayrollResponse.jsonPath().getString("message");
		int responseStatusCode = addPayrollResponse.getStatusCode();
		
		if(responseStatusCode==200) {
			System.out.println("success status code in response :" + responseStatusCode + ": success response message in response :" + responseMessage);
		}else {
			Assert.fail("failure status code in response :" + responseStatusCode 
					+ ": failure message in response :" + responseMessage);
		}
		
		System.out.println("Status code : " + addPayrollResponse.getStatusCode());
		Assert.assertEquals(addPayrollResponse.getStatusCode(), 200, 
				"Expected status code not found. Actual: " + addPayrollResponse.getStatusCode());

		long responseTime = addPayrollResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");

		String ContentType = addPayrollResponse.getHeader("Content-Type");
		Assert.assertTrue(ContentType.contains("application/json"), "content type header missmatch");

		String allowMethod = addPayrollResponse.getHeader("Access-Control-Allow-Methods");
		if (allowMethod != null) {
			Assert.assertTrue(allowMethod.contains("POST"), "Post method is not allowed");
		}

		PayrollDetails.id = addPayrollResponse.jsonPath().get("id");
		PayrollDetails.creator_id = addPayrollResponse.jsonPath().get("creator");
		PayrollDetails.creator_company_user_id = addPayrollResponse.jsonPath().get("creator_company_user_id");
		PayrollDetails.company_id = addPayrollResponse.jsonPath().get("company_id");
		PayrollDetails.salary_breakup_id = addPayrollResponse.jsonPath().get("salary_breakup_id");
		PayrollDetails.party_company_user_id = addPayrollResponse.jsonPath().get("party_company_user_id");
		PayrollDetails.payroll_type = addPayrollResponse.jsonPath().get("type");
		PayrollDetails.punch_effect = addPayrollResponse.jsonPath().get("punch_effect");

		List<String> projectList = addPayrollResponse.jsonPath().getList("project_ids");
		if (projectList != null) {
		    PayrollDetails.project_ids = projectList.toArray(new String[0]);
		}
		
		List<String> fileUpdateList = Arrays.asList(
				"src/test/resources/testdata_salarybreakup/Create_SalaryBreakup.json"
		);
		
	    //Data to update in all files
	    JSONObject newData = new JSONObject();
	    newData.put("company_user_id", PayrollDetails.party_company_user_id);
	    newData.put("payroll_id", PayrollDetails.id);

	    //Loop through each file and update
	    for (String filePath : fileUpdateList) {
	        File objFile = new File(filePath);
	        JSONObject existingData = new JSONObject();

	        // Step 1: Read if exists
	        if (objFile.exists()) {
	            String content = new String(Files.readAllBytes(Paths.get(filePath)));
	            if (!content.isEmpty()) {
	                existingData = new JSONObject(content);
	            }
	        }

	        // Step 2: Merge data
	        for (String key : newData.keySet()) {
	            existingData.put(key, newData.get(key));
	        }

	        // Step 3: Save back
	        try (FileWriter file = new FileWriter(filePath)) {
	            file.write(existingData.toString(4));
	            file.flush();
	        }
	        System.out.println("Updated file: " + filePath);
	    }
	    System.out.println("All JSON files updated with new payroll data.");
		
	}

	@Test(priority=2, dependsOnMethods="createLabourPayroll")
	public void detailPayroll() throws Exception {

		if(PayrollDetails.id != null && !PayrollDetails.id.isEmpty()) {
			System.out.println("payroll id :" + PayrollDetails.id);
		}else {
			Assert.fail("payroll id is null or empty :" + PayrollDetails.id);
		}

		Response DetailPayrollResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", PayrollDetails.id)
				.log().all()

				.when()
				.get(Payroll_Api.DETAIL_PAYROLL)

				.then()
				.extract().response();

		String jsonResponse = DetailPayrollResponse.getBody().asString();
		SchemaValidator.validateSchema("schemas_files/AddPayroll.json",jsonResponse);
		
		int responseStatusCode = DetailPayrollResponse.getStatusCode();
		String responseMessage = DetailPayrollResponse.jsonPath().getString("message");
		
		if(responseStatusCode==200) {
			System.out.println("success status code in response :" + responseStatusCode + ": success response message in response :" + responseMessage);
		}else {
			Assert.fail("failure status code in response :" + responseStatusCode + ": failure message in response :" + responseMessage);
		}

		long responseTime = DetailPayrollResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");

		String responseContentType = DetailPayrollResponse.getHeader("Content-Type");
		Assert.assertTrue(responseContentType.contains("application/json"), "content type header missmatch");

		String allowMethod = DetailPayrollResponse.getHeader("Access-Control-Allow-Methods");
		if(allowMethod != null) {
			Assert.assertTrue(allowMethod.contains("GET"), "get method is ot allow");
		}

		Payroll_Response payrollObj = DetailPayrollResponse.as(Payroll_Response.class);

		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = mapper.writeValueAsString(payrollObj);
		System.out.println("Payroll Object as JSON:\n" + jsonString);

		Assert.assertEquals(payrollObj.getId(), PayrollDetails.id, "Payrolll id mismatch");
		
		String id = payrollObj.getId();
		String salaryBreakupId = payrollObj.getSalary_breakup_id();
		String partyCompanyUserId = payrollObj.getParty_company_user_id();
		String projectIds[] = payrollObj.getProject_ids();
		String payrolltype = payrollObj.getType();
		
		if(id != null && !id.isEmpty()) {
			System.out.println("payroll id :" + id);
		} else{
			Assert.fail("payroll id is null or empty :" + id);
		}
		
		if(salaryBreakupId != null && !salaryBreakupId.isEmpty()) {
			System.out.println("salary breakup id :" + salaryBreakupId);
		} else {
			System.out.println("salary breakup id is null or empty :" + salaryBreakupId);
		}
		
		if(partyCompanyUserId != null && !partyCompanyUserId.isEmpty()) {
			System.out.println("party company user id :" + partyCompanyUserId);
		}else {
			System.out.println("party company user id null or empty :" + partyCompanyUserId);
		}
		
		if(projectIds != null) {
			System.out.println("project ids :" + Arrays.toString(projectIds));
		}else {
			System.out.println("project ids null :" + Arrays.toString(projectIds));
		}
		
		if(payrolltype != null && "labour".equalsIgnoreCase(payrolltype)) {
			System.out.println("payroll type is match :" + payrolltype);
		} else {
			System.out.println("payrolll type is not match :" + payrolltype);
		}
		
		PayrollDetails.id = DetailPayrollResponse.jsonPath().get("id");
		PayrollDetails.workforce_id = DetailPayrollResponse.jsonPath().get("workforce_id");
		PayrollDetails.salary_breakup_id = DetailPayrollResponse.jsonPath().get("salary_breakup_id");
		
		List<String> fileUpdateList = Arrays.asList(
				"src/test/resources/testdata_payroll/Edit_Payroll.json"
		);
		
	    //Data to update in all files
	    JSONObject newData = new JSONObject();
	    newData.put("id", PayrollDetails.id);
	    newData.put("workforce_id", PayrollDetails.workforce_id);
	    newData.put("salary_breakup_id", PayrollDetails.salary_breakup_id);
	    
	    //Loop through each file and update
	    for (String filePath : fileUpdateList) {
	        File objFile = new File(filePath);
	        JSONObject existingData = new JSONObject();

	        // Step 1: Read if exists
	        if (objFile.exists()) {
	            String content = new String(Files.readAllBytes(Paths.get(filePath)));
	            if (!content.isEmpty()) {
	                existingData = new JSONObject(content);
	            }
	        }

	        // Step 2: Merge data
	        for (String key : newData.keySet()) {
	            existingData.put(key, newData.get(key));
	        }

	        // Step 3: Save back
	        try (FileWriter file = new FileWriter(filePath)) {
	            file.write(existingData.toString(4));
	            file.flush();
	        }
	        System.out.println("Updated file: " + filePath);
	    }
	    System.out.println("Updated Payroll ID before saving JSON: " + PayrollDetails.id);
	}

}
