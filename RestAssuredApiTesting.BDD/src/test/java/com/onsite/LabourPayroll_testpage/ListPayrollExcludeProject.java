package com.onsite.LabourPayroll_testpage;
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

public class ListPayrollExcludeProject {

	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;
	
	List<Map<String, Object>> allRecord = new ArrayList<>();

	@Test(priority=1)
	public void listPayrollExcludeProject() {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
		String projectId = "9da5415f-30de-403e-af95-4fdfe178e44e";

		while(morePages) {
			System.out.println("validating page number :" + pageNumber);

			Response payrollResponse = 

					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "labour")
					.queryParam("project_id", projectId)
					.queryParam("page", pageNumber)
					.log().uri()

					.when()
					.get(Payroll_Api.LIST_PAYROLL_EXCLUDE_PROJECT)

					.then()
					.log().all()
					.extract().response();

			String responseList = payrollResponse.getBody().asString();

			int responseStatusCode = payrollResponse.getStatusCode();
			String responseMessage = payrollResponse.jsonPath().getString("message");

			if(responseStatusCode == 200) {
				System.out.println("success status code :" + responseStatusCode 
						+ ": success message in response :" + responseMessage);
			} else if(responseStatusCode == 400 && "Invalid page query param".equalsIgnoreCase(responseMessage)){
				System.out.println("message : " + "invalid query param pass in request" + "status code :" + responseStatusCode);
				break;
			} else if(responseStatusCode==400 && "Invalid count query param".equalsIgnoreCase(responseMessage)) {
				System.out.println("message : " + "invalid count query param pass in request" + "status code :" + responseStatusCode);
				break;
			} else if(responseStatusCode==400 && "company_id required".equalsIgnoreCase(responseMessage)) {
				System.out.println("message : " + "invalid company id pass in request" + "status code :" + responseStatusCode);
				break;
			} else if(responseStatusCode==400 && "project_id required".equalsIgnoreCase(responseMessage)) {
				System.out.println("message : " + "invalid project id pass in request" + "status code :" + responseStatusCode);
				break;
			} else if(responseStatusCode==400 && "Not Permitted".equalsIgnoreCase(responseMessage)) {
				System.out.println("message : " + "Not Permitted" + "status code :" + responseStatusCode);
				break;
			}else {
				Assert.fail("failure status code in response :" + responseStatusCode 
						+ ": failure message in response " + responseMessage);
			}

			long responseTime = payrollResponse.getTime();
			if(responseTime < 2000) {
				System.out.println("actual response time :" + responseTime);
			} else {
				Assert.fail("response time is too long : " + responseTime);
			}

			List<Map<String, Object>> dataList = payrollResponse.jsonPath().getList("data");
			if(dataList == null || dataList.isEmpty()) {
				System.out.println("data List is empty or null in response ");
			}else {
				System.out.println("data list is not empty or null in response");
			}
			
			allRecord.addAll(dataList);
			totalRecord = totalRecord + dataList.size();
			
			for(int i=0; i<dataList.size(); i++) {
				Map<String, Object> item = dataList.get(i);
				
				String id = (String) item.get("id");
				String created_id = (String) item.get("creator");
				String creatorCompanyUserId = (String) item.get("creator_company_user_id");
				String company_id = (String) item.get("company_id");
				String salaryBreakupId = (String) item.get("salary_breakup_id");
				String partyCompanyUserId = (String) item.get("party_company_user_id");
				String type = (String) item.get("type");
				Integer punchEffect = (Integer) item.get("punch_effect");
				Integer deleteFlag = (Integer) item.get("delete");
				Integer hiddenFlag = (Integer) item.get("hidden");
				List<String> projectIds =(List<String>) item.get("project_ids");
				List<String> trackProjectIds = (List<String>) item.get("track_project_ids");
				
				if(id != null && !id.isEmpty()) {
					System.out.println("payroll id :" + id);
				}else {
					Assert.fail("payrolll id os null or empty :" + id);
				}
				if(created_id != null && !created_id.isEmpty()) {
					System.out.println("payroll created id :" + created_id);
				}else {
					Assert.fail("payroll creaed id is empty or null :" + created_id);
				}
				if(creatorCompanyUserId != null && !creatorCompanyUserId.isEmpty()) {
					System.out.println("creator company user id :" + creatorCompanyUserId);
				}else {
					Assert.fail("creator company uer is null or empty :" + creatorCompanyUserId);
				}
				if(company_id != null && !company_id.isEmpty()) {
					System.out.println("company id :" + company_id);
				}else {
					Assert.fail("company id is null or empty :" + company_id);
				}
				if(salaryBreakupId != null && !salaryBreakupId.isEmpty()) {
					System.out.println("salary breakup id :" + salaryBreakupId);
				}else {
					Assert.fail("salary breakup id is null or empty :" + salaryBreakupId);
				}
				if(partyCompanyUserId != null && !partyCompanyUserId.isEmpty()) {
					System.out.println("party company user id :" + partyCompanyUserId);
				}else {
					Assert.fail("party compnay user id id null or empty :" + partyCompanyUserId);
				}
				if(type != null && type.equalsIgnoreCase("labour")) {
					System.out.println("payroll type is :" + type);
				}else {
					Assert.fail("payroll type is missmatch :" + type);
				}
				if(punchEffect != null) {
					System.out.println("punch effect flag :" + punchEffect);
				}else {
					Assert.fail("punch effect flag is null or empty :" + punchEffect);
				}
				if(deleteFlag == 0) {
					System.out.println("Payroll delete flag is :" + deleteFlag);
				}else {
					Assert.fail("payroll delete flag is :" + deleteFlag);
				}
				if(hiddenFlag == 0) {
					System.out.println("payroll hidden flag is :" + hiddenFlag);
				}else {
					Assert.fail("payroll hidden flag is :" + hiddenFlag);
				}
				if(projectIds != null && !projectIds.isEmpty()) {
					System.out.println("project ids list is :" + projectIds);
				}else {
					Assert.fail("project ids list is empty or null :" + projectIds);
				}
	
			}
			String nextUrl = payrollResponse.jsonPath().getString("page.next_url");
			if(nextUrl != null && !nextUrl.isEmpty() && nextUrl.contains("page=")) {
				pageNumber = Integer.parseInt(nextUrl.split("page=")[1].split("&")[0]);
			}else {
				morePages = false;
			}
		}
		System.out.println("total record collected :" + totalRecord);
	}

}
