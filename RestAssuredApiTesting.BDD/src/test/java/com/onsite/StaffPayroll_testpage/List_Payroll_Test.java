package com.onsite.StaffPayroll_testpage;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class List_Payroll_Test extends BaseToken{

	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;
	int expectedCount = -1;

	List<Map<String, Object>> allRecord = new ArrayList<>();
	List<String> payrollIds = new ArrayList<>();

	@Test
	public void staffPayroll() {

		String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			Response staffPayrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.headers("Authorization", BaseToken.token)
					.queryParam("company_id", companyId)
					.queryParam("type", "staff")
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL)

					.then()
					.log().all()
					.extract().response();

			//test case = schma validation
			String staffPayrollList = staffPayrollListResponse.getBody().asString();
			try {
				SchemaValidator.validateSchema("schemas_files/Payroll_Type.json", staffPayrollList);
			} catch (Exception e) {
				throw new AssertionError("Schema validation failed", e);
			}

			int statusCode = staffPayrollListResponse.getStatusCode();
			String responseMessage = staffPayrollListResponse.jsonPath().get("message");


			//test case 1 = status code & response message validation 
			if(statusCode == 200) {
				System.out.println("succesfull status code :" + statusCode + ": successfull message in response :" + responseMessage);
			} else {
				throw new AssertionError("failure status code :" + statusCode + ": failure message in response :" + responseMessage);
			}

			//test case 2 = response time validation 
			long responseTime = staffPayrollListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time is : " + responseTime);
			}else {
				throw new AssertionError("fail : response time is too long :" + responseTime);
			}

			//test case 3 = staff list empty & count validation 
			List<Map<String, Object>> staffList = staffPayrollListResponse.jsonPath().getList("data");
			int staffCount = staffPayrollListResponse.jsonPath().get("page.count");
			
			if(expectedCount == -1) {
				expectedCount = staffCount;
			}

			if(staffList == null || staffList.isEmpty()) {
				Assert.assertEquals(staffCount, 0, "staff list is empty but count not to 0");
				System.out.println("staff list is empty and count is 0");
			} else {
				Assert.assertTrue(staffCount > 0, "staff list is not empty but count is 0");
				System.out.println("staff list is not null or empty : and staff list count :" + staffCount);	
			}

			allRecord.addAll(staffList);
			totalRecord = totalRecord + staffList.size();

			//test case 5 = staff payroll list data validation 
			for(int i=0; i<staffList.size(); i++) {
				Map<String, Object> item = staffList.get(i);

				String payrollId = (String) item.get("id");
				String salaryBreakupId = (String) item.get("salary_breakup_id");
				String partyCompanyUserId = (String) item.get("party_company_user_id");
				String payrollType = (String) item.get("type");
				Integer PayrollDeleteFlag =(Integer) item.get("delete");
				Integer payrollHiddenFlag = (Integer) item.get("hidden");
				String partyType = (String) item.get("monkey_patch_party_company_user.type");
				String staffCompanyId = (String) item.get("monkey_patch_party_company_user.company_id");
				String payrollPartyName = (String) item.get("monkey_patch_party_company_user.name");
				Integer payrollPartyHiddenFlag= (Integer) item.get("monkey_patch_party_company_user.hidden");
				
				if(expectedCount == -1) {
					expectedCount = staffCount;
				}

				if(payrollId != null && !payrollId.isEmpty()) {	
					//test case = duplicate payroll id validation 
					if(!payrollIds.contains(payrollId)) {
						payrollIds.add(payrollId);
						System.out.println("unique payroll id found");
					} else {
						throw new AssertionError("fail : dupliacet payrolll id found");
					}
				} else {
					throw new AssertionError("fail : payroll id null pr empty");
				}

				if(salaryBreakupId != null && !salaryBreakupId.isEmpty()) {
					System.out.println("salaryBreakup id is not null or empty :" + salaryBreakupId);
				}else {
					throw new AssertionError("fail : salary breakup id is null or empty");
				}

				if(partyCompanyUserId != null && !partyCompanyUserId.isEmpty()) {
					System.out.println("party company user id is not null or empty :" + partyCompanyUserId);
				} else {
					throw new AssertionError("fail : party company user id null or empty");
				}

				if("staff".equals(payrollType)) {
					System.out.println("payroll type is match with staff :" + payrollType);
				}else {
					throw new AssertionError("fail : payroll type is not match :" + payrollType);
				}

				if(PayrollDeleteFlag != null && PayrollDeleteFlag == 0) {
					System.out.println("payroll delete flag is 0 : " + PayrollDeleteFlag);
				} else {
					throw new AssertionError("fail : payroll delete flag is 1");
				}

				if(payrollHiddenFlag != null && payrollHiddenFlag == 0) {
					System.out.println("payroll hidden flag is 0 : " + payrollHiddenFlag);
				} else {
					throw new AssertionError("payroll hidden flag is 1");
				}

				if("employee".equals(partyType)) {
					System.out.println("payroll party type is :" + partyType);
				} else {
					throw new AssertionError("payroll party type is");
				}

				if(staffCompanyId != null && staffCompanyId.equals(companyId)) {
					System.out.println("company id is match ");
				} else {
					throw new AssertionError("company id is not match");
				}

				if(payrollPartyName != null && !payrollPartyName.isEmpty()) {
					System.out.println("payroll party name is not null oe empty :" + payrollPartyName);
				} else {
					throw new AssertionError("payroll party name is null or empty");
				}

				if(payrollPartyHiddenFlag != null && payrollPartyHiddenFlag.equals(0)) {
					System.out.println("party hiddden flag is 0");
				} else {
					throw new AssertionError("party hidden flag is");
				}

			}

			//test case 6 = pegination check 
			String nextUrl = staffPayrollListResponse.jsonPath().get("page.next_url");
			if(nextUrl != null && !nextUrl.isEmpty()) {
				morePages = true;
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				morePages = false;
			}
		}
		
		System.out.println("total record collected :" + totalRecord);
		
		//test case = staff list count and total count same validation
		if(totalRecord == expectedCount) {
			System.out.println("staff count match : " + totalRecord + ": with :" + expectedCount);
		}else {
			throw new AssertionError("fail : staff count not match : " + totalRecord + ": with :" + expectedCount);
		}
		
	}

}
