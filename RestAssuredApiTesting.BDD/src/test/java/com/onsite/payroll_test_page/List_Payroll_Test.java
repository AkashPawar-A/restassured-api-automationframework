package com.onsite.payroll_test_page;

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

import static io.restassured.RestAssured.*;

public class List_Payroll_Test {

	String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;

	List<Map<String, Object>> allRecord = new ArrayList<>();

	@Test(priority=1)
	public void labour_listpayroll() {

		int nullIdCount = 0;

		while(morePages) {
			System.out.println("validating pages :" + pageNumber);

			Response listPayrollResponse = 
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "labour")
					.queryParam("hidden", 0)
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL)

					.then()
					.extract().response();

			int responseStatusCode = listPayrollResponse.getStatusCode();
			String responseMessage = listPayrollResponse.jsonPath().getString("message");
			
			if(responseStatusCode == 200) {
				System.out.println("success status code is 200");	
				System.out.println("response Message: " + responseMessage);
			} else {
				System.out.println("failure status code is " + responseStatusCode);		
				System.out.println("failure message :" + responseMessage);
				
				Assert.fail("API failed with status code: " + responseStatusCode + 
		                " and message: " + responseMessage);
			}

			String payrollList = listPayrollResponse.getBody().asString();	
			System.out.println("all payroll list :\n" + payrollList);

			List<Map<String, Object>> dataList = listPayrollResponse.jsonPath().get("data");
			if(dataList == null || dataList.isEmpty()) {
				System.out.println("payroll list is empty or null");
				break;
			}

			allRecord.addAll(dataList);
			totalRecord += dataList.size();

			for(int i=0; i<dataList.size(); i++) {
				Map<String,Object> item = dataList.get(i);

				String payroll_id = (String) item.get("id");
				String salaryBreakupId = (String) item.get("salary_breakup_id");
				String payroll_type = (String) item.get("type");

				String partyCompanyUserType = listPayrollResponse.jsonPath()
						.getString("data[" + i + "].monkey_patch_party_company_user.type");

				Integer payrolll_dalateObj = (Integer) item.get("delete");
				Integer payroll_hiddenObj = (Integer) item.get("hidden");


				Integer partyCompanyHiddenObj = listPayrollResponse.jsonPath()
						.getInt("data[" + i + "].monkey_patch_party_company_user.hidden");

				int payroll_delete_flag = payrolll_dalateObj != null ? payrolll_dalateObj : 0;
				int payroll_hidden_flag = payroll_hiddenObj !=null ? payroll_hiddenObj : 0;
				int party_company_user_hidden = partyCompanyHiddenObj != null ? partyCompanyHiddenObj : 0;

				if(payroll_id ==null || payroll_id.isEmpty()) {
					//throw new RuntimeException("payroll id is null or empty : " + i + ": " + item);
					nullIdCount++;
					System.out.println("Null Payroll ID at index=" + i + " : " + item);
					continue;
				}
				if(salaryBreakupId==null || salaryBreakupId.isEmpty()) {
					//throw new RuntimeException("salary breakup is is null or empty :" + i + ": " + item);
					nullIdCount++;
					System.out.println("Null salaryBreakup ID at index=" + i + " : " + item);
					continue;
				}
				if(payroll_type==null || !payroll_type.equalsIgnoreCase("labour")) {
					throw new RuntimeException("labour type is missamtch : " + i + ": " + item);
				}
				if(partyCompanyUserType==null || partyCompanyUserType.trim().isEmpty() || !partyCompanyUserType.equalsIgnoreCase("labour")) {
					throw new RuntimeException("partyCompanyUserType is missamtch :" + i + " : " + item);
				}
				if(payroll_delete_flag != 0 || payroll_hidden_flag !=0 || party_company_user_hidden != 0) {
					throw new RuntimeException("Delete flag must be 0 : " + i + ": " + item);
				}
				System.out.println("Total Records Collected: " + totalRecord);
				System.out.println("Total Null Payroll IDs: " + nullIdCount);
				System.out.println("Total Null SalaryBreakup IDs: " + nullIdCount);
			}

			String nextUrl = listPayrollResponse.jsonPath().getString("page.next_url");

			if (nextUrl != null && !nextUrl.isEmpty()) {
				morePages = true;
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				morePages = false;
			}
		}
		System.out.println("Total Records Collected: " + totalRecord);

	}

	@Test(priority=2)
	public void staff_listpayroll() {

		int nullIdcount = 0;

		while(morePages) {

			System.out.println("Validating page : " + pageNumber);

			Response  satffListPayrollResponse = 
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "staff")
					.queryParam("hidden", 0)
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL)

					.then()
					.extract().response();

			int responseStatusCode = satffListPayrollResponse.getStatusCode();
			String responseMessage = satffListPayrollResponse.jsonPath().getString("message");
			
			if(responseStatusCode == 200) {
				System.out.println("success status code is 200");	
				System.out.println("response Message: " + responseMessage);
			} else {
				System.out.println("failure status code is " + responseStatusCode);		
				System.out.println("failure message :" + responseMessage);
				
				Assert.fail("API failed with status code: " + responseStatusCode + 
		                " and message: " + responseMessage);
			}

			String responseBody = satffListPayrollResponse.getBody().asString();
			System.out.println("response body :\n " + responseBody);

			List<Map<String, Object>> staffDataList = satffListPayrollResponse.jsonPath().get("data");
			if(staffDataList == null || staffDataList.isEmpty()) {
				System.out.println("staff data list is empty or null");
				break;
			}

			allRecord.addAll(staffDataList);
			totalRecord = totalRecord + staffDataList.size();

			for(int i=0; i<staffDataList.size(); i++) {
				Map<String, Object> item = staffDataList.get(i);

				String payroll_id = (String) item.get("id");
				String salary_breakup_Id = (String) item.get("salary_breakup_id");
				String party_company_user_Id = (String) item.get("party_company_user_id");
				String payrollType = (String) item.get("type");
				Integer payroll_hiddenObj = (Integer) item.get("hidden");
				Integer payroll_deleteObj = (Integer) item .get("delete");

				int payroll_hiddenFlag = payroll_hiddenObj != null ? payroll_hiddenObj : 0;
				int payroll_deleteFlag = payroll_deleteObj != null ? payroll_deleteObj : 0;

				String party_Id = (String) satffListPayrollResponse.jsonPath()
						.getString("data[" + i + "].monkey_patch_party_company_user.id");
				String party_type = (String) satffListPayrollResponse.jsonPath()
						.getString("data[" + i + "].monkey_patch_party_company_user.type");
				Integer party_hiddenObj = (Integer) satffListPayrollResponse.jsonPath()
						.getInt("data[" + i + "].monkey_patch_party_company_user.hidden");

				int party_hiddenFlag = party_hiddenObj != null ? party_hiddenObj : 0;

				if(payroll_id==null || payroll_id.isEmpty()) {
					nullIdcount++;
					System.out.println("payroll id is null : " + i + " : " + item);
					continue;
				}
				if(salary_breakup_Id==null || salary_breakup_Id.isEmpty()) {
					nullIdcount++;
					System.out.println("salary_breakup_Id is null or empty : " + i + " : " + item);
					continue;
				}
				if(party_company_user_Id==null || party_company_user_Id.isEmpty()) {
					nullIdcount++;
					System.out.println("party_company_user_Id is null or empty : " + i + " : " + item);
					continue;
				}
				if(payrollType==null || !payrollType.equalsIgnoreCase("staff")) {
					throw new RuntimeException("payroll type is miassmatch : " + i + " : " + item);
				}
				if(payroll_hiddenObj != 0 || payroll_deleteFlag != 0 || party_hiddenObj != 0) {
					throw new RuntimeException("payroll or party hidden ya delete flag is 0 : " + i + " : " + item);
				}
				if(party_Id == null || party_Id.isEmpty()) {
					nullIdcount++;
					System.out.println("party_id is empty or null : " + i + " : " + item);
					continue;
				}
				if(party_type == null || !party_type.equalsIgnoreCase("employee")) {
					throw new RuntimeException("party Type is misamatch : " + i + " : " + item);
				}
				
				System.out.println("total records collected : " + totalRecord);
				System.out.println("total null payroll ids : " + nullIdcount);
				System.out.println("total null salary_breakup ids :" + nullIdcount);
				System.out.println("total null party_company_user ids : " + nullIdcount);
				System.out.println("total null party ids : " + nullIdcount);
			}
				
				String nextUrl = satffListPayrollResponse.jsonPath().getString("page.next_url");
				if(nextUrl != null && !nextUrl.isEmpty()) {
					morePages = true;
					pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
				}else {
					morePages = false;
			}
		}
		System.out.println("total record collected : " + totalRecord);
	}	

}
