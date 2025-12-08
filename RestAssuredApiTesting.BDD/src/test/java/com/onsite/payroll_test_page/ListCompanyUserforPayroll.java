package com.onsite.payroll_test_page;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListCompanyUserforPayroll {

	int pageNumber = 1;
	int totalRecord = 0;
	boolean morePages = true;

	List<Map<String, Object>> allRecord = new ArrayList<>();
	List<String> validUserIds = new ArrayList<>();

	@Test(priority=1)
	public void labourPayrollList() {

		String token = AuthUtils.getToken();
		String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			System.out.println("Validating page number :" + pageNumber);

			Response labourPayrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", "Bearer " + token)
					.queryParam("company_id", companyId)
					.queryParam("type", "labour")
					.queryParam("page", pageNumber)
					.contentType(ContentType.JSON)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_COMPANYUSER_FOR_PAYROLL)

					.then()
					.log().all()
					.extract().response();

			String responseList = labourPayrollListResponse.getBody().asString();

			int responseStatusCode = labourPayrollListResponse.getStatusCode();
			String responseMessage = labourPayrollListResponse.jsonPath().getString("message");
			if(responseStatusCode == 200) {
				System.out.println("success status code :" + responseStatusCode 
						+ " : response success message : " + responseMessage);
			} else if (responseStatusCode == 400 && "No entry found".equalsIgnoreCase(responseMessage)) {
				System.out.println("No more pages available → pagination completed");
				break;

			} else {
				Assert.fail("failure status code :" + responseStatusCode 
						+ " : response failure message : " + responseMessage);
			}

			long responseTime = labourPayrollListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response tiem :" + responseTime);
			}else {
				Assert.fail("response time is too long :" + responseTime);
			}

			List<Map<String, Object>> dataList = labourPayrollListResponse.jsonPath().getList("data");
			if(dataList == null || dataList.isEmpty()) {
				System.out.println("company user payroll list is empty");
				break;
			}

			allRecord.addAll(dataList);
			totalRecord = totalRecord + dataList.size();

			for(int i=0; i<dataList.size(); i++) {
				Map<String, Object> item = dataList.get(i);

				String user_id = (String) item.get("id");
				String companyUser_id =(String) item.get("company_id");
				String user_type =(String) item.get("type");
				String user_creator_id = (String) item.get("creator");
				String user_name = (String) item.get("name");
				Integer user_hidden_flag = (Integer) item.get("hidden");

				if(user_id != null && !user_id.isEmpty()) {
					System.out.println("user id :" + user_id);
				} else {
					System.out.println("Skipping user because user id is different : " + user_id + " : " + user_name);
				}

				if(companyUser_id != null && !companyUser_id.isEmpty()) {
					System.out.println("company id :" + companyUser_id);
				} else {
					System.out.println("Skipping user because companyUser id is empty or null : " + companyUser_id + " : " + user_name);
				}

				if("labour".equalsIgnoreCase(user_type)) {
					System.out.println("user party type :" + user_type);
				} else {
					System.out.println("Skipping user because user type is different : " + user_type + " : " + user_name + " : " + user_id);
				}

				if(user_creator_id != null && !user_creator_id.isEmpty()) {
					System.out.println("user creator id :" + user_creator_id);
				} else {
					System.out.println("Skipping user becauses user creator id is null or empty : " + user_creator_id + " : " + user_name);
				}

				if(user_name != null && !user_name.isEmpty()) {
					System.out.println("user name :" + user_name);
				} else {
					System.out.println("Skipping user because user name is empty or null : " + user_name + " : " + user_id);
				}

				if(user_hidden_flag != null && user_hidden_flag == 0) {
					System.out.println("user hidden flag :" + user_hidden_flag);
					validUserIds.add(user_id);
				} else {
					System.out.println("Skipping user because hidden flag is 1: " + user_id + " : " + user_name + " : " + user_id);
				}
			}
			String nextUrl = labourPayrollListResponse.jsonPath().getString("page.next_url");
			if (nextUrl != null && !nextUrl.isEmpty()) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				break;
			}
		}
		System.out.println("total record collected :" + totalRecord);
		System.out.println("Total valid IDs → " + validUserIds.size());
		System.out.println(validUserIds);
	}

	@Test(priority=2)
	public void saveUserId() throws Exception {

		try {

			if(validUserIds.isEmpty()) {
				Assert.fail("No valid user IDs found to write in JSON file");
			}

			String filePath = "src/test/resources/testdata_payroll/Create_Payroll.json";

			ObjectMapper mapper = new ObjectMapper();
			
			Map<String, Object> existingJson = mapper.readValue(new File(filePath), Map.class);
			
			if(existingJson == null) {
				existingJson = new HashMap<>();
			}
			
			existingJson.put("party_company_user_id", validUserIds);

			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), existingJson);
			
			System.out.println("User IDs updated successfully → " + validUserIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update json file");
		}
	}

	@Test(priority=3)
	public void staffPayrollList() {

		String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			System.out.println("validating page number :" + pageNumber);

			Response staffPayrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "staff")
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_COMPANYUSER_FOR_PAYROLL)

					.then()
					.log().all()
					.extract().response();

			String payrollresponse = staffPayrollListResponse.getBody().asString();

			int responseStatusCode = staffPayrollListResponse.getStatusCode();
			String responseMessage = staffPayrollListResponse.jsonPath().getString("message");

			if(responseStatusCode == 200) {
				System.out.println("response status code : " + responseStatusCode 
						+ ": response message : " + responseMessage);
			} else if(responseStatusCode == 400 && "No Entry found".equalsIgnoreCase(responseMessage)) {
				System.out.println("No more pages available : pagination completed");
				break;
			} else {
				Assert.fail("response statsu code missmatch :" + responseStatusCode 
						+ ": failure message :" + responseMessage);
			}

			long responseTime = staffPayrollListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time :" + responseTime);
			} else {
				Assert.fail("response time is too long : " + responseTime);
			}

			List<Map<String, Object>> staffList = staffPayrollListResponse.jsonPath().getList("data");
			if(staffList == null || staffList.isEmpty()) {
				System.out.println("staff List is empty or null in response ");
				break;
			}

			allRecord.addAll(staffList);
			totalRecord = totalRecord + staffList.size();

			for(int i=0; i<staffList.size(); i++) {
				Map<String, Object> item = staffList.get(i);

				String id = (String) item.get("id");
				String company_id = (String) item.get("company_id");
				String userType = (String) item.get("type");
				String user_created_id = (String) item.get("creator");
				String user_name = (String) item.get("name");
				String user_id = (String) item.get("user_id");
				Integer user_hidden_flag = (Integer) item.get("hidden");

				if(id != null || !id.isEmpty()) {
					System.out.println("user id is not null or empty : " + id);
				} else {
					Assert.fail("user id is null or empty + " + id);
				}

				if(company_id != null || !company_id.isEmpty()) {
					System.out.println("company id is not empty or null : " + company_id);
				} else {
					Assert.fail("company id is null or empty :" + company_id);
				}

				if("staff".equalsIgnoreCase(user_id)) {
					System.out.println("staff type is match" + id + " : " + userType);
				} else {
					Assert.fail("staff type is missmatch : " + id + " : " + userType);
				}

				if(user_created_id != null || !user_created_id.isEmpty()) {
					System.out.println("created id is not null or empty : " + user_created_id);
				} else {
					Assert.fail("created id is null or emoty : " + user_created_id);
				}

				if(user_name != null || !user_name.isEmpty()) {
					System.out.println("user name is null or empty : " + user_name);
				} else {
					Assert.fail("user name is null or empty : " + user_name);
				}

				if(user_id != null || !user_id.isEmpty()) {
					System.out.println("user id is null or empty :" + user_id);
				} else {
					Assert.fail("user id is null or empty : " + user_id);
				}

				if(user_hidden_flag != 1) {
					System.out.println("user hidden flag is 0 : " + user_hidden_flag);
				} else {
					Assert.fail("user hidden flag is 1 :" + user_hidden_flag);
				}
			}
			String nextUrl = staffPayrollListResponse.jsonPath().getString("next_url");
			if(nextUrl != null || !nextUrl.isEmpty()) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				break;
			}
		}
		System.out.println("total record collected :" + totalRecord);
	}

}
