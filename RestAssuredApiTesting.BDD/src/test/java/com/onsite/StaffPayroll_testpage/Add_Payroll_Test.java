package com.onsite.StaffPayroll_testpage;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Add_Payroll_Test {
	
	int totalRecord = 0;
	List<Map<String, Object>> allCount = new ArrayList<>();

	@Test
	public void staffPayrollList() {

		String comapanyId = CompanyContext.getCompanyId();
				
		Response companyUserResponse =
			given()
			.baseUri(ApiBasePath.BASE_URL)
			.header("Authorization", BaseToken.token)
			.queryParam("company_id", comapanyId)
			.queryParam("type", "staff")
			.contentType(ContentType.JSON)
			.log().uri()

			.when()
			.get(Payroll_Api.LIST_COMPANYUSER_FOR_PAYROLL)

			.then()
			.log().all()
			.extract().response();
		
		String userData = companyUserResponse.getBody().asString();
		
		//test case 1 = status code with message 
		int responseStatusCode = companyUserResponse.getStatusCode();
		String responseMessage = companyUserResponse.jsonPath().getString("message");
		
		if(responseStatusCode == 200) {
			System.out.println("successfull status code :" + responseStatusCode 
					+ ": with successfull message :" + responseMessage);
		}else {
			System.out.println("failure status code :" + responseStatusCode 
					+ ": with failure message :" + responseMessage);
		}
		
		//test case 2 = response Time 
		long responseTime = companyUserResponse.getTime();
		if(responseTime < 2000) {
			System.out.println("actual response time is :" + responseTime);
		}else {
			System.out.println("response time is too long :" + responseTime);
		}
		
		//test case 3 = company user list empty or null
		List<Map<String, Object>> userList = companyUserResponse.jsonPath().getList("data");
		if(userList == null || userList.isEmpty()) {
			System.out.println("company user list is empty or null");
		}
		
		allCount.addAll(userList);
		totalRecord = totalRecord + userList.size();
		
		for(int i=0; i<userList.size(); i++) {
			Map<String, Object> item = userList.get(i);
			
			String id = (String) item.get("id");
			String userPartyType = (String) item.get("type");
			String userName = (String) item.get("name");
			String userId = (String) item.get("user_id");
			String creatorId = (String) item.get("creator");
			
		}
		
	}

}
