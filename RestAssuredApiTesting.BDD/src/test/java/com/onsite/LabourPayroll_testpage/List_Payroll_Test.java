package com.onsite.LabourPayroll_testpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class List_Payroll_Test extends BaseToken{

	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;
	int totalCount = 0;

	List<Map<String, Object>> allRecord = new ArrayList<>();
	List<String> payrollIds = new ArrayList<>();

	@Test
	public void labourPayroll() throws Exception {
		
	    String companyId = CompanyContext.getCompanyId();

		while(morePages) {
			Response labourPayrollListResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.headers("Authorization", BaseToken.token)
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "labour")
					.queryParam("hidden", 0)
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL)

					.then()
					.log().all()
					.extract().response();

			//test case 1 = schema validation
			List<Map<String, Object>> staffPayrollList = labourPayrollListResponse.jsonPath().getList("data");
			if(staffPayrollList != null && !staffPayrollList.isEmpty()) {		   
				ObjectMapper mapper = new ObjectMapper();
				String payrollArrayJson = mapper.writeValueAsString(staffPayrollList);
				SchemaValidator.validateSchemaFromString(payrollArrayJson, "schemas_files/Payroll_Type_Array.json");
			}

			//test case 2 = status code & response message validation 
			int statusCode = labourPayrollListResponse.getStatusCode();
			String responseMessage = labourPayrollListResponse.jsonPath().get("message");
			if(statusCode == 200) {
				System.out.println("succesfull status code :" + statusCode + ": successfull message in response :" + responseMessage);
			} else {
				throw new AssertionError("failure status code :" + statusCode + ": failure message in response :" + responseMessage);
			}

			//test case 2 = response time validation 
			long responseTime = labourPayrollListResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time is : " + responseTime);
			}else {
				throw new AssertionError("fail : response time is too long :" + responseTime);
			}

			//test case 3 = staff list empty & count validation 
			List<Map<String, Object>> labourList = labourPayrollListResponse.jsonPath().getList("data");
			int labourCount = labourPayrollListResponse.jsonPath().get("page.count");

			if(labourList == null && labourList.isEmpty()) {
			    throw new AssertionError("Fail : staff list is null and empty ");
			}

			allRecord.addAll(labourList);
			totalRecord = totalRecord + labourList.size();

			//test case 5 = staff payroll list data validation 
			for(int i=0; i<labourList.size(); i++) {
				
				Map<String, Object> item = labourList.get(i);

				String payrollId = (String) item.get("id");	
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
				
				String salaryBreakupId = (String) item.get("salary_breakup_id");
				if(salaryBreakupId != null && !salaryBreakupId.isEmpty()) {
					System.out.println("salaryBreakup id is not null or empty :" + salaryBreakupId);
				}else {
					throw new AssertionError("fail : salary breakup id is null or empty");
				}
				
				String partyCompanyUserId = (String) item.get("party_company_user_id");
				if(partyCompanyUserId != null && !partyCompanyUserId.isEmpty()) {
					System.out.println("party company user id is not null or empty :" + partyCompanyUserId);
				} else {
					throw new AssertionError("fail : party company user id null or empty");
				}
				
				String payrollType = (String) item.get("type");
				if("labour".equals(payrollType)) {
					System.out.println("payroll type is match with staff :" + payrollType);
				}else {
					throw new AssertionError("fail : payroll type is not match :" + payrollType);
				}
				
				Integer PayrollDeleteFlag =(Integer) item.get("delete");
				if(PayrollDeleteFlag != null && PayrollDeleteFlag == 0) {
					System.out.println("payroll delete flag is 0 : " + PayrollDeleteFlag);
				} else {
					throw new AssertionError("fail : payroll delete flag is 1");
				}
				
				Integer payrollHiddenFlag = (Integer) item.get("hidden");
				if(payrollHiddenFlag != null && payrollHiddenFlag == 0) {
					System.out.println("payroll hidden flag is 0 : " + payrollHiddenFlag);
				} else {
					throw new AssertionError("payroll hidden flag is 1");
				}
				
				Map<String, Object> companyUser = (Map<String, Object>) item.get("monkey_patch_party_company_user");

				if (companyUser == null) {
				    throw new AssertionError("Fail : monkey_patch_party_company_user is null");
				}

				String partyType = (String) companyUser.get("type");
				if ("employee".equals(partyType)) {
				    System.out.println("payroll party type is : " + partyType);
				} else {
				    throw new AssertionError("Fail : payroll party type mismatch : " + partyType);
				}

				/* company id validation */
				String staffCompanyId = (String) companyUser.get("company_id");
				if (staffCompanyId != null && staffCompanyId.equals(companyId)) {
				    System.out.println("company id is match");
				} else {
				    throw new AssertionError("Fail : company id mismatch or null");
				}

				/* party name validation */
				String payrollPartyName = (String) companyUser.get("name");
				if (payrollPartyName != null && !payrollPartyName.isEmpty()) {
				    System.out.println("payroll party name is : " + payrollPartyName);
				} else {
				    throw new AssertionError("Fail : payroll party name is null or empty");
				}

				/* hidden flag validation */
				Integer payrollPartyHiddenFlag = (Integer) companyUser.get("hidden");
				if (payrollPartyHiddenFlag != null && payrollPartyHiddenFlag == 0) {
				    System.out.println("party hidden flag is 0");
				} else {
				    throw new AssertionError("Fail : party hidden flag is not 0");
				}
			}
			
			if(labourCount == totalCount) {
				System.out.println("labour count  : " + labourCount + ": totalCount : " + totalCount + ": is match");
			} else {
				System.out.println("labour count  : " + labourCount + ": totalCount : " + totalCount + ": is match");
			}

			//test case 4 = pegination check 
			String nextUrl = labourPayrollListResponse.jsonPath().get("page.next_url");
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

