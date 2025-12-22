package com.onsite.LabourPayroll_testpage;
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

	private void resetPegination() {
		this.pageNumber = 1;
		this.totalRecord = 0;
		this.morePages = true;

		this.allRecord.clear();
		this.validLabourUserIds.clear();
		this.validStaffUserIds.clear();
	}

	List<Map<String, Object>> allRecord = new ArrayList<>();
	List<String> validLabourUserIds = new ArrayList<>();
	List<String> validStaffUserIds = new ArrayList<>();

	@Test(priority=1)
	public void labourTypeList() {

		resetPegination();
		String token = AuthUtils.getToken();
		String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			System.out.println("Validating page number :" + pageNumber);

			Response labourTypeListResponse = 

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

			String responseList = labourTypeListResponse.getBody().asString();

			int responseStatusCode = labourTypeListResponse.getStatusCode();
			String responseMessage = labourTypeListResponse.jsonPath().getString("message");

			//test case -> status code validation 
			if(responseStatusCode == 200) {
				System.out.println("success status code :" + responseStatusCode + " : response success message : " + responseMessage);
			} else if (responseStatusCode == 400 && "No entry found".equalsIgnoreCase(responseMessage)) {
				System.out.println("No more pages available → pagination completed");
				break;
			} else {
				Assert.fail("failure status code :" + responseStatusCode + " : response failure message : " + responseMessage);
			}

			//test case -> response time validation
			long responseTime = labourTypeListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response tiem :" + responseTime);
			}else {
				Assert.fail("response time is too long :" + responseTime);
			}

			List<Map<String, Object>> dataList = labourTypeListResponse.jsonPath().getList("data");
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

				//test case 1 -> Payroll already created validation
				if(List_Payroll_Test.payrollCreatedUser.contains(user_id)) {
					String message = "Test Fail because Payroll already created for user iD : " + user_id + ": Name : " + user_name;
					Assert.fail(message);
				}else {
					System.out.println("User payroll not created yet ID: " + user_id + ": Name: " + user_name);
				}

				//test case 2 -> user id validation 
				if(user_id != null && !user_id.isEmpty()) {
					System.out.println("user id :" + user_id);
				} else {
					System.out.println("Skipping user because user id is different : " + user_id + " : " + user_name);
				}

				//test case 3-> company user id validation 
				if(companyUser_id != null && !companyUser_id.isEmpty()) {
					System.out.println("company id :" + companyUser_id);
				} else {
					System.out.println("Skipping user because companyUser id is empty or null : " + companyUser_id + " : " + user_name);
				}

				//test case 4 -> party type "labour" validation
				if("labour".equalsIgnoreCase(user_type)) {
					System.out.println("user party type :" + user_type);
				} else {
					System.out.println("Skipping user because user type is different : " + user_type + " : " + user_name + " : " + user_id);
				}

				//test case 5 -> user created id validation
				if(user_creator_id != null && !user_creator_id.isEmpty()) {
					System.out.println("user creator id :" + user_creator_id);
				} else {
					System.out.println("Skipping user becauses user creator id is null or empty : " + user_creator_id + " : " + user_name);
				}

				//test case 6 -> user name validation
				if(user_name != null && !user_name.isEmpty()) {
					System.out.println("user name :" + user_name);
				} else {
					System.out.println("Skipping user because user name is empty or null : " + user_name + " : " + user_id);
				}

				//test case 7 -> company user hidden flag validation
				if(user_hidden_flag != null && user_hidden_flag == 0) {
					System.out.println("user hidden flag :" + user_hidden_flag);
					validLabourUserIds.add(user_id);
				} else {
					System.out.println("Skipping user because hidden flag is 1: " + user_id + " : " + user_name + " : " + user_id);
				}
			}
			String nextUrl = labourTypeListResponse.jsonPath().getString("page.next_url");
			if (nextUrl != null && !nextUrl.isEmpty()) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				morePages = false;
			}
		}
		System.out.println("total record collected :" + totalRecord);
		System.out.println("Total valid IDs → " + validLabourUserIds.size());
		System.out.println(validLabourUserIds);
	}

	@Test(priority=2, dependsOnMethods="labourTypeList")
	public void saveLabourUserId() throws Exception {

		try {

			String singleUserId = null;

			if(validLabourUserIds != null || !validLabourUserIds.isEmpty()) {
				for(String userId : validLabourUserIds) {
					if(validLabourUserIds != null || !validLabourUserIds.isEmpty()) {
						singleUserId = userId;
						break;
					}
				}
			}

			if(singleUserId == null) {
				Assert.fail("No valid user IDs found");
			}

			String filePath = "src/test/resources/testdata_payroll/Labour_Create_Payroll.json";
			ObjectMapper mapper = new ObjectMapper();

			Map<String, Object> existingJson = mapper.readValue(new File(filePath), Map.class);

			if(existingJson == null) {
				existingJson = new HashMap<>();
			}

			existingJson.put("party_company_user_id", singleUserId);

			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), existingJson);

			System.out.println("User IDs updated successfully → " + validLabourUserIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update json file");
		}
	}

}
