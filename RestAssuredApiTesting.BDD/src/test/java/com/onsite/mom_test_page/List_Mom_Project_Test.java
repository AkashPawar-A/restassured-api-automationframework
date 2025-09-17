package com.onsite.mom_test_page;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Mom_Api;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class List_Mom_Project_Test {
	
	@Test
	public void getListMomProject() {
		
		List<String> projectRecords = new ArrayList<>();
		String companyId="75916659-9cbe-4ca7-812e-181a29229772";

		Response getProjectListResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.log().uri()

				.when()
				.get(Mom_Api.List_MOM_Project)

				.then()
				.extract().response();

		Assert.assertEquals(getProjectListResponse.getStatusCode(), 200, "Expected status code not found");

		long responseTime = getProjectListResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "mom project list response time is too long" + responseTime);

		List<Map<String, Object>> projectList = getProjectListResponse.jsonPath().getList("");
		if(projectList==null || projectList.isEmpty()) {
			Assert.fail("mom project list is null or empty in response");
		}

		Set<String> projectIds = new HashSet<>();
		
		List<String> mandatoryFiled = Arrays.asList("id", "company_id" , "type", "name", "contractor", 
				"contractor_company_user_id", "creator", "creator_company_user_id", "status", 
				"created", "updated");
		
		List<String> nonMandatoryFiled = Arrays.asList("address", "city", "state", 
				"google_address", "estimated_cost", "progress", "start_date", "end_date", 
				"monkey_patch_company_name", "monkey_patch_team_member", "monkey_patch_todo_count",
				"attendance_radius", "distance", "dimension", "orientation", "code");
		
		List<String> requiredStatus = Arrays.asList("Ongoing", "Completed", "On Hold", "Not Started");
		
		List<String> requiredType =Arrays.asList("p", "w"); 
		
		for(Map<String, Object> projectId : projectList) {
			
			for(String requiredKey : mandatoryFiled) {
				if(!projectId.containsKey(requiredKey)) {
					Assert.fail("Mandatory key-value is null for key: " + requiredKey + " Data: " + projectId);
				}
				
				Object value = projectId.get(requiredKey);
				if(value==null) {
					Assert.fail("required key-value is null :" + requiredKey);
				}
				
				if(value instanceof String && ((String) value).trim().isEmpty()) {
					Assert.fail("required key-value is empty");
				}
				
				String projectStatus = (String) projectId.get("status");
				Assert.assertTrue(requiredStatus.contains(projectStatus), "invalid status found :" + projectStatus);
				
				String projectType = (String) projectId.get("type");
				Assert.assertTrue(requiredType.contains(projectType), "invalid type found :" + projectType);
				
				if(requiredKey.equals("company_id")) {
					String actualcompanyId = (String) value;
					Assert.assertEquals(actualcompanyId, companyId, "company id mismatch");
				}
			}
			
			for(String nonRequriedKey : nonMandatoryFiled) {
				if(!projectId.containsKey(nonRequriedKey)) {
					Assert.fail("Mandatory key is missing in response: " + projectId.get(nonRequriedKey));
				}
				
				Object value = projectId.get(nonRequriedKey);
				if(value==null) {
					continue;
				}
				
				if(value instanceof String && ((String) value).trim().isEmpty()) {
					continue;
				}
			}
			
			String projectEntry = "projectId :" + projectId.get("id") +
					", projectType :" + projectId.get("type") +
					", Name :" + projectId.get("name") +
					", Creator :" + projectId.get("creator") + 
					", Address :" + projectId.get("address") +
					", City :" + projectId.get("city") + 
					", State:" + projectId.get("state") +
					", Status :" + projectId.get("status") +
					", Google_address :" + projectId.get("google_address") +
					", Location :" + projectId.get("location") + 
					", Estimated_cost : " + projectId.get("estimated_cost") + 
					", progress :" + projectId.get("progress") + 
					", Start_date :" + projectId.get("start_date") +
					", End_date :" + projectId.get("end_date");
			projectRecords.add(projectEntry);
		}	
		for(String record : projectRecords) {
			System.out.println(record);
		}
		
		System.out.println("All projects validated successfully. Total projects: " + projectList.size());
	}
}
