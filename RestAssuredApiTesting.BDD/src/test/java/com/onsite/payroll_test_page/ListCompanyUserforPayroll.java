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
	public void payrollList() {

		//int count = 0;

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

}
