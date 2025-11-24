package com.onsite.payroll_test_page;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListCompanyUserforPayroll {

	int pageNumber = 1;
	int totalRecord = 0;
	boolean morePages = true;

	List<Map<String, Object>> allRecord = new ArrayList<>();

	@Test(priority=1)
	public void labourPayrollList() {
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		while(morePages) {
			System.out.println("Validating page number :" + pageNumber);

			Response payrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
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

			String responseList = payrollListResponse.getBody().asString();

			int responseStatusCode = payrollListResponse.getStatusCode();
			String responseMessage = payrollListResponse.jsonPath().getString("message");
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

			long responseTime = payrollListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response tiem :" + responseTime);
			}else {
				Assert.fail("response time is too long :" + responseTime);
			}

			List<Map<String, Object>> dataList = payrollListResponse.jsonPath().getList("data");
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
					Assert.fail("user id is empty or null :" + user_id);
				}

				if(companyUser_id != null && !companyUser_id.isEmpty()) {
					System.out.println("company id :" + companyUser_id);
				} else {
					Assert.fail("company id is empty or null :" + companyUser_id);
				}

				if("labour".equalsIgnoreCase(user_type)) {
					System.out.println("user party type :" + user_type);
				} else {
					Assert.fail("user party type is empty or null :" + user_type);
				}

				if(user_creator_id != null && !user_creator_id.isEmpty()) {
					System.out.println("user creator id :" + user_creator_id);
				} else {
					Assert.fail("user created id is empty or null :" + user_creator_id);
				}

				if(user_name != null && !user_name.isEmpty()) {
					System.out.println("user name :" + user_name);
				} else {
					Assert.fail("user name is empty or null : " + user_name);
				}

				if(user_hidden_flag != 1) {
					System.out.println("user hidden flag :" + user_hidden_flag);
				} else {
					Assert.fail("hidden flag is 1 :" + user_hidden_flag);
				}
			}
			String nextUrl = payrollListResponse.jsonPath().getString("page.next_url");
			if (nextUrl != null && !nextUrl.isEmpty()) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				break;
			}
		}
		System.out.println("total record collected :" + totalRecord);
	}

	@Test(priority=2)
	public void staffPayrollList() {
		
		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		while(morePages) {
			System.out.println("validating page number :" + pageNumber);

			Response payrollResponse = 

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

			String payrollresponse = payrollResponse.getBody().asString();

			int responseStatusCode = payrollResponse.getStatusCode();
			String responseMessage = payrollResponse.jsonPath().getString("message");

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

			long responseTime = payrollResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time :" + responseTime);
			} else {
				Assert.fail("response time is too long : " + responseTime);
			}

			//List<Map<String, Object>> staffList = new ArrayList<>();
			List<Map<String, Object>> staffList = payrollResponse.jsonPath().getList("data");
			if(staffList != null || staffList.isEmpty()) {
				System.out.println("staffList is empty id response ");
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
			String nextUrl = payrollResponse.jsonPath().getString("next_url");
			if(nextUrl != null || !nextUrl.isEmpty()) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				break;
			}
		}
		System.out.println("total record collected :" + totalRecord);
	}

}
