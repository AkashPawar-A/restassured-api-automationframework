package com.onsite.StaffPayroll_testpage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class List_Payroll_Test extends BaseToken{

	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;
	int totalCount = 0;

	List<Map<String, Object>> allRecord = new ArrayList<>();
	List<String> payrollIds = new ArrayList<>();

	@Test
	public void staffPayroll() throws Exception {
		
	    String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			Response staffPayrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.headers("Authorization", BaseToken.token)
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "staff")
					.queryParam("hidden", 0)
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL)

					.then()
					.log().all()
					.extract().response();

			//test case 1 = schema validation
			List<Map<String, Object>> staffPayrollList = staffPayrollListResponse.jsonPath().getList("data");
			if(staffPayrollList != null && !staffPayrollList.isEmpty()) {		   
				ObjectMapper mapper = new ObjectMapper();
				String payrollArrayJson = mapper.writeValueAsString(staffPayrollList);
				SchemaValidator.validateSchemaFromString(payrollArrayJson, "schemas_files/Payroll_Type_Array.json");
			}

			//test case 2 = status code & response message validation 
			int statusCode = staffPayrollListResponse.getStatusCode();
			String responseMessage = staffPayrollListResponse.jsonPath().get("message");
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

			if(staffList == null) {
			    throw new AssertionError("Fail : staff list is null");
			}

			if(staffList.isEmpty()) {
			    System.out.println("Info : staff list empty on this page, pagination continue");
			} else {
			    System.out.println("staff list size on this page : " + staffList.size());
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

				if(payrollId != null && !payrollId.isEmpty()) {
					totalCount++;
					//test case 3 = duplicate payroll id validation 
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
				
				// validate = monkey_patch_party_company_user
				Map<String, Object> companyUser = (Map<String, Object>) item.get("monkey_patch_party_company_user");
				String partyType = (String) companyUser.get("type");
				String staffCompanyId = (String) companyUser.get("company_id");
				String payrollPartyName = (String) companyUser.get("name");
				Integer payrollPartyHiddenFlag = (Integer) companyUser.get("hidden");
				String partyUserId = (String) companyUser.get("user_id");

				if (companyUser == null) {
				    throw new AssertionError("Fail : monkey_patch_party_company_user is null");
				}

				if ("employee".equals(partyType)) {
				    System.out.println("payroll party type is : " + partyType);
				} else {
				    throw new AssertionError("Fail : payroll party type mismatch : " + partyType);
				}
				if (staffCompanyId != null && staffCompanyId.equals(companyId)) {
				    System.out.println("company id is match");
				} else {
				    throw new AssertionError("Fail : company id mismatch or null");
				}

				if (payrollPartyName != null && !payrollPartyName.isEmpty()) {
				    System.out.println("payroll party name is : " + payrollPartyName);
				} else {
				    throw new AssertionError("Fail : payroll party name is null or empty");
				}
				
				if (payrollPartyHiddenFlag != null && payrollPartyHiddenFlag == 0) {
				    System.out.println("party hidden flag is 0");
				} else {
				    throw new AssertionError("Fail : party hidden flag is not 0");
				}
				
				if(partyUserId != null && !partyUserId.isEmpty()) {
					System.out.println("payroll party user id :" + partyUserId);
				} else {
					throw new AssertionError("Fail : payroll party user is is null or empty");
				}
			}
			
			if(staffCount == totalCount) {
				System.out.println("staff count  : " + staffCount + ": totalCount : " + totalCount + ": is match");
			} else {
				System.out.println("staff count  : " + staffCount + ": totalCount : " + totalCount + ": is match");
			}

			//test case 4 = pegination check 
			String nextUrl = staffPayrollListResponse.jsonPath().get("page.next_url");
			if(nextUrl != null && !nextUrl.isEmpty()) {
				morePages = true;
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			} else {
				morePages = false;
			}
		}
		System.out.println("total record collected :" + totalRecord);
	}
}
