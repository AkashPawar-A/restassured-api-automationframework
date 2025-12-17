package com.onsite.payroll_test_page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class List_Payroll_Test extends BaseToken{

	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;

	List<Map<String, Object>> allRecord = new ArrayList<>();
	public static Set<String> payrollCreatedUser = new HashSet<>();

	@Test(priority=1)
	public void labour_listpayroll() {

		String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			System.out.println("validating pages :" + pageNumber);

			Response labourListPayrollResponse = 
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", BaseToken.token)
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

			String payrollList = labourListPayrollResponse.getBody().asString();	
			System.out.println("all payroll list :\n" + payrollList);

			int responseStatusCode = labourListPayrollResponse.getStatusCode();
			String responseMessage = labourListPayrollResponse.jsonPath().getString("message");

			//test case -> status code validation
			if(responseStatusCode == 200) {
				System.out.println("success status code is : " + responseStatusCode + ": successfull response message : " + responseMessage);	
			} else {
				System.out.println("failure status code is " + responseStatusCode + ": failure message :" + responseMessage);		
			}

			//test case -> response time validation 
			long responseTime = labourListPayrollResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time : " + responseTime);
			}else {
				System.out.println("response time is too long : " + responseTime);
			}

			List<Map<String, Object>> dataList = labourListPayrollResponse.jsonPath().get("data");
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
				String partyCompanyUserType = labourListPayrollResponse.jsonPath().getString("data[" + i + "].monkey_patch_party_company_user.type");
				String partyUserId = labourListPayrollResponse.jsonPath().getString("data[" + i + "].party_company_user_id");

				Integer payrolll_dalateObj = (Integer) item.get("delete");
				Integer payroll_hiddenObj = (Integer) item.get("hidden");
				Integer partyCompanyHiddenObj = labourListPayrollResponse.jsonPath().getInt("data[" + i + "].monkey_patch_party_company_user.hidden");

				int payrollDeleteFlag = payrolll_dalateObj != null ? payrolll_dalateObj : 0;
				int payrollHiddenFlag = payroll_hiddenObj !=null ? payroll_hiddenObj : 0;
				int partyHiddenFlag = partyCompanyHiddenObj != null ? partyCompanyHiddenObj : 0;

				///test case 1-> Payroll already created check
				if(partyUserId != null && !partyUserId.isEmpty()) {
					if(payrollCreatedUser.contains(partyUserId)) {
						Assert.fail("Payroll already created for user: " + partyUserId + ": Payroll ID: " + payroll_id);
					} else {
						payrollCreatedUser.add(partyUserId);
						System.out.println("user payroll is not created : " + partyUserId);
					}
				}

				//test case 1 -> payroll id validation 
				if(payroll_id !=null && !payroll_id.isEmpty()) {
					System.out.println("payroll id " + payroll_id);
				}else {
					System.out.println("payroll id is empty or null :" + payroll_id);
				}

				//test case 2 -> salary breakup id validation  
				if(salaryBreakupId !=null && !salaryBreakupId.isEmpty()) {
					System.out.println("salaray breakup id " + i + " : " + item);
				}else {
					System.out.println("salary breakup id is empty or null :" + i);
				}

				//test case 3 -> payroll type validation 
				if("labour".equalsIgnoreCase(payroll_type)) {
					System.out.println("payroll_type match : " + payroll_type + ": payroll id :" + payroll_id);
				}else {
					System.out.println("payroll type is missmatch :" + payroll_type + ": payroll id " + payroll_id);
				}

				//test case 4 -> party company user type validation
				if("labour".equalsIgnoreCase(partyCompanyUserType)) {
					System.out.println("partyCompanyUserType is : " + partyCompanyUserType + ": payroll id :" + payroll_id);
				}else {
					System.out.println("partyCompanyUserType is missmatch or null : " + partyCompanyUserType + ": payroll id :" + payroll_id);
				}

				//test case 5 -> payroll delete & hidden flag validation
				if (payrollDeleteFlag != 0 || payrollHiddenFlag != 0 || partyHiddenFlag != 0) {
					System.out.println("Payroll or party is deleted/hidden → Payroll ID: " + payroll_id);
				}

				String nextUrl = labourListPayrollResponse.jsonPath().getString("page.next_url");
				if (nextUrl != null && !nextUrl.isEmpty()) {
					morePages = true;
					pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
				} else {
					morePages = false;
				}
			}
			System.out.println("Total Records Collected: " + totalRecord);
		}
	}

	@Test(priority=2)
	public void staff_listpayroll() {

		String companyId = CompanyContext.getCompanyId();

		while(morePages) {

			System.out.println("Validating page : " + pageNumber);

			Response  satffListPayrollResponse = 
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", BaseToken.token)
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

			String responseBody = satffListPayrollResponse.getBody().asString();
			System.out.println("response body :\n " + responseBody);

			int responseStatusCode = satffListPayrollResponse.getStatusCode();
			String responseMessage = satffListPayrollResponse.jsonPath().getString("message");

			//test case -> status code validation 
			if(responseStatusCode == 200) {
				System.out.println("success status code is : " + responseStatusCode + ": successfull response message : " + responseMessage);	
			} else {
				System.out.println("failure status code is " + responseStatusCode + ": failure message :" + responseMessage);		
			}

			//test case -> response time validation 
			long responseTime = satffListPayrollResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time : " + responseTime);
			}else {
				System.out.println("response time is too long : " + responseTime);
			}

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

				String partyUserId = satffListPayrollResponse.jsonPath().getString("data[" + i + "].party_company_user_id");
				String party_Id = (String) satffListPayrollResponse.jsonPath().getString("data[" + i + "].monkey_patch_party_company_user.id");
				String party_type = (String) satffListPayrollResponse.jsonPath().getString("data[" + i + "].monkey_patch_party_company_user.type");
				Integer party_hiddenObj = (Integer) satffListPayrollResponse.jsonPath().getInt("data[" + i + "].monkey_patch_party_company_user.hidden");

				int party_hiddenFlag = party_hiddenObj != null ? party_hiddenObj : 0;

				//test case 1 -> payroll id validation
				if(partyUserId != null && !partyUserId.isEmpty()) {
					if(payrollCreatedUser.contains(partyUserId)) {
						Assert.fail("payroll already created for user : " + partyUserId + ": payroll id :" + payroll_id);
					}else {
						payrollCreatedUser.add(partyUserId);
						System.out.println("user payroll i not created : " + partyUserId);
					}
				}

				//test case -> payroll id validation 
				if(payroll_id !=null && !payroll_id.isEmpty()) {
					System.out.println("payroll id : " + i + " : " + item);
				}else {
					System.out.println("payroll id is null or empty :" + i + " : " + item);
				}

				//test case -> salary breakup id validation
				if(salary_breakup_Id !=null && !salary_breakup_Id.isEmpty()) {
					System.out.println("salary_breakup_Id : " + i + " : " + item);
				}else {
					System.out.println("salary breakup id is null or empty :" + i + " : " + item);
				}

				//test case -> party company user id validation 
				if(party_company_user_Id !=null && !party_company_user_Id.isEmpty()) {
					System.out.println("party_company_user_Id : " + i + " : " + item);
				}else {
					System.out.println("party_company_user_Id is null or empty : " + i + " : " + item);
				}

				//test case -> payroll type validation 
				if("staff".equalsIgnoreCase(payrollType)) {
					System.out.println("payroll type is match :" + payrollType + ": payroll id :" + payroll_id);
				}else {
					System.out.println("payroll type is mismatch :" + payrollType + ": payroll id : " + payroll_id);
				}

				//test case -> payroll delete & hidden flag validation
				if(payroll_hiddenObj != 0 || payroll_deleteFlag != 0 || party_hiddenObj != 0) {
					System.out.println("Payroll or party is deleted/hidden → Payroll ID: " + payroll_id);
				}

				//test case -> party id validation 
				if(party_Id != null && !party_Id.isEmpty()) {
					System.out.println("party_id is : " + i + " : " + item);
				}else {
					System.out.println("party_id is empty or null : " + i + " : " + item);
				}

				//test case -> party type validation
				if("staff".equalsIgnoreCase(party_type)) {
					System.out.println("party Type is match : " + i + " : " + item);
				}else {
					System.out.println("party Type is misamatch : " + i + " : " + item);
				}
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
