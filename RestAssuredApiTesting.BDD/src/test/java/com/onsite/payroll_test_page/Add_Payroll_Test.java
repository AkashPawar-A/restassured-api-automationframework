package com.onsite.payroll_test_page;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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

public class Add_Payroll_Test {

	@DataProvider(name="payrollData")
	public Object[][] getpayrollData(){

		String addPayrollPath = "src/test/resources/testdata_payroll/Create_Payroll.json";

		Object[][] payrollData = JsonDataProvider.getDataFromJson(addPayrollPath, Add_Payroll_Request.class);

		Object[][] dataObject = new Object[payrollData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = payrollData[i][0];
		}
		return dataObject;
	}

	@Test(priority=1, dataProvider="payrollData")
	public void addPayroll(Add_Payroll_Request addPayrollRequest) throws Exception {

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

		String message = addPayrollResponse.jsonPath().getString("message");
		if(message != null) {
			System.out.println("Response message : " + message);
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
		PayrollDetails.project_ids = projectList.toArray(new String[0]);
		
		String salaryBreakupFilePath = "src/test/resources/testdata_salarybreakup/Create_SalaryBreakup.json";
		
		// Step 1: Read existing file (if exists)
		JSONObject existingData = new JSONObject();
		File objFile = new File(salaryBreakupFilePath);
		if(objFile.exists()) {
			String content = new String(Files.readAllBytes(Paths.get(salaryBreakupFilePath)));
			if(!content.isEmpty()){
				existingData = new JSONObject(content);
			}
		}

		// Step 2: Create new data
		JSONObject newData = new JSONObject();
		newData.put("company_user_id", PayrollDetails.party_company_user_id);
		newData.put("payroll_id", PayrollDetails.id);

		// Step 3: Merge
		for(String key : newData.keySet()) {
			existingData.put(key, newData.get(key));
		}

		// Step 4: Save merged data back
		try(FileWriter file = new FileWriter(salaryBreakupFilePath)) {
			file.write(existingData.toString(4));
			file.flush();
		}
		System.out.println("Saved party_company_user_id & payroll_id to Create_SalaryBreakup.json file");
		
	}

	@Test(priority=2, dependsOnMethods="addPayroll")
	public void detailPayroll() throws Exception {

		if(PayrollDetails.id==null || PayrollDetails.id.isEmpty()) {
			System.out.println("payroll id is null or empty");
			return;
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

		System.out.println("Response Body :" + DetailPayrollResponse.getBody().asString());

		String message = DetailPayrollResponse.jsonPath().getString("message");
		if(message != null) {
			System.out.println("Response message : " + message);
		}

		System.out.println("Status code : " + DetailPayrollResponse.getStatusCode());
		Assert.assertEquals(DetailPayrollResponse.getStatusCode(),  200, 
				"expected statsus not foud in response" + DetailPayrollResponse.getStatusCode());

		long responseTime = DetailPayrollResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "response time is too long");

		String responseContentType = DetailPayrollResponse.getHeader("Content-Type");
		Assert.assertTrue(responseContentType.contains("application/json"), "content type header missmatch");

		String allowMethod = DetailPayrollResponse.getHeader("Access-Control-Allow-Methods");
		if(allowMethod != null) {
			Assert.assertTrue(allowMethod.contains("GET"), "get method is ot allow");
		}

		String json_PayrollResponse = DetailPayrollResponse.asString();
		SchemaValidator.validateSchema("schemas_files/Payroll.json", json_PayrollResponse);

		Payroll_Response payrollObj = DetailPayrollResponse.as(Payroll_Response.class);

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

		//Nested objects assertions (optional, add as needed)
		if (payrollObj.getMonkey_patch_party_company_user() != null) {
			Payroll_Response.CompanyUserNormal user = payrollObj.getMonkey_patch_party_company_user();
			System.out.println("Nested monkey_patch_party_company_user: " + mapper.writeValueAsString(user));
			Assert.assertNotNull(user.getId(), "Nested user id should not be null");
			Assert.assertNotNull(user.getCompany_id(), "Nested user company_id should not be null");
		}

		if (payrollObj.getMonkey_patch_workforce() != null) {
			System.out.println("Nested monkey_patch_workforce: " + mapper.writeValueAsString(payrollObj.getMonkey_patch_workforce()));
		}

		if (payrollObj.getMonkey_patch_associate_project() != null) {
			System.out.println("Nested monkey_patch_associate_project: " + mapper.writeValueAsString(payrollObj.getMonkey_patch_associate_project()));
		}

		if (payrollObj.getMonkey_patch_workforcestock() != null) {
			System.out.println("Nested monkey_patch_workforcestock: " + mapper.writeValueAsString(payrollObj.getMonkey_patch_workforcestock()));
		}

		if (payrollObj.getMonkey_patch_salary_breakup() != null) {
			System.out.println("Nested monkey_patch_salary_breakup: " + mapper.writeValueAsString(payrollObj.getMonkey_patch_salary_breakup()));
		}

		if (payrollObj.getMonkey_patch_face_info() != null) {
			System.out.println("Nested monkey_patch_face_info: " + mapper.writeValueAsString(payrollObj.getMonkey_patch_face_info()));
		}
	}
}
