package com.onsite.mom_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.MomDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CompanyUser;
import com.onsite.endpoints.Mom_Api;
import com.onsite.endpoints.Project_Api;
import com.onsite.pojo_request.MOM_Request;import com.onsite.pojo_response.MOM_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Create_MOM_Test extends BaseToken{

	@DataProvider(name="createMomData")
	public Object[][] getMomData(){

		String momPath = "src/test/resources/test_data/CreateMOM_TestData.json";

		Object[][] momData = JsonDataProvider.getDataFromJson(momPath, MOM_Request.class);

		Object[][] dataObject = new Object[momData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = momData[i][0];
		}
		return dataObject;
	}

	@Test(priority=1, dataProvider="createMomData")
	public void getCompanyUserId(MOM_Request momRequestPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		Response companyUserResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.queryParam("priority_type", "customer")
				.log().uri()

				.when()
				.get(CompanyUser.companyUser)

				.then()
				.log().all()
				.extract().response();

		//Status code check
		Assert.assertEquals(companyUserResponse.getStatusCode(), 200, "Expected Status Code not responded");

		//response time check
		long responseTime = companyUserResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "Response time is too long > 5s, actual: " + responseTime);

		//Null Empty check
		List<Map<String, Object>> companyUserList = companyUserResponse.jsonPath().getList("data");
		if (companyUserList == null || companyUserList.isEmpty()) {
			Assert.fail("Company user list is empty or null");
		}

		//Duplicate ID check with null safety
		Set<String> ids = new HashSet<>();
		for (Map<String, Object> users : companyUserList) {
			Object idObj = users.get("id");
			if (idObj == null) {
				Assert.fail("User ID is null: " + users);
			}
			String userIds = idObj.toString();
			if (!ids.add(userIds)) {
				Assert.fail("Duplicate user id found: " + userIds);
			}
		}

		//Index check
		int requiredIndex = 2;
		if (companyUserList.size() > requiredIndex) {
			Map<String, Object> selectCompanyUser = companyUserList.get(requiredIndex);

			//Mandatory keys & null validation
			List<String> keyList = Arrays.asList("id", "name", "company_id", "type", "hidden");
			for (String key : keyList) {
				if (!selectCompanyUser.containsKey(key) || selectCompanyUser.get(key) == null) {
					Assert.fail("Mandatory key missing or null: " + key);
				}
			}

			String attendeeId = selectCompanyUser.get("id").toString();
			String attendeeName = selectCompanyUser.get("name").toString();
			String companyID = selectCompanyUser.get("company_id").toString();
			String userType = selectCompanyUser.get("type").toString();
			int hiddenAttendee = Integer.parseInt(selectCompanyUser.get("hidden").toString());

			//UUID validation
			try {
				UUID.fromString(attendeeId);
			} catch (IllegalArgumentException e) {
				Assert.fail("Attendee ID is not valid UUID: " + attendeeId);
			}

			//Name non-empty
			if (attendeeName.trim().isEmpty()) {
				Assert.fail("Attendee name is empty");
			}

			//Company ID consistency
			if (!companyID.equals(companyId)) {
				Assert.fail("Mismatch in requested company_id vs response company_id");
			}

			//User type business rule
			List<String> validTypes = Arrays.asList("customer", "vendor", "employee");
			if (!validTypes.contains(userType)) {
				Assert.fail("Unexpected user type: " + userType);
			}

			//Hidden flag check
			if (hiddenAttendee == 0) {
				if (companyID != null && !companyID.isEmpty()) {
					momRequestPayload.setCompany_id(companyID);
			    } else {
			    	Assert.fail("Company id is null or empty in response");
			    }
				if (attendeeId != null && !attendeeId.isEmpty()) {
					momRequestPayload.setAttendee_cu_ids(new String[]{attendeeId});
			    } else {
			    	Assert.fail("attendee id is null or empty in response");
			    }
			} else {
				Assert.fail("Attendee is hidden=1, test cannot proceed");
			}
		} else {
			Assert.fail("Required index not found in company user list");
		}
	}

	@Test(priority=2, dataProvider="createMomData", dependsOnMethods="getCompanyUserId")
	public void getProjectId(MOM_Request momRequestPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		Response projectIdResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.log().uri()

				.when()
				.get(Project_Api.ALL_PROJECT_LIST)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(projectIdResponse.getStatusCode(), 200, "expected status code not responded");

		long responseTime = projectIdResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long > 5s :" + ", actual :" + responseTime);

		List<Map<String, Object>> projectList = projectIdResponse.jsonPath().getList("projects");
		if(projectList==null || projectList.isEmpty()) {
			throw new RuntimeException("project list is null or empty");
		}

		Set<String> ids = new HashSet<>();
		for(Map<String, Object> users : projectList) {
			Object idobj = users.get("id");
			if(idobj==null) {
				Assert.fail("users id is null :" + idobj);
			}
			String userIds = idobj.toString();
			if(!ids.add(userIds)) {
				Assert.fail("Dulpicate users id found :" + userIds);
			}
		}

		int requriedIndex = 1;
		if(projectList.size() > requriedIndex) {
			Map<String, Object> selectedProject = projectList.get(requriedIndex);

			List<String> mandatoryKeys = Arrays.asList("id", "type", "name", "status", "city", "company_id");
			for(String key : mandatoryKeys) {
				if(!selectedProject.containsKey(key) || selectedProject.get(key) == null) {
					Assert.fail("mandatory field is missing: " + key);
				}
			}

			String projectid = selectedProject.get("id").toString();
			String projectType = selectedProject.get("type").toString();
			String projectName = selectedProject.get("name").toString();
			String projectStatus = selectedProject.get("status").toString();
			String projectCity = selectedProject.get("city").toString();
			String projectCompany = selectedProject.get("company_id").toString();

			try {
				UUID.fromString(projectid);
			} catch(IllegalArgumentException e) {
				Assert.fail("project id is not UUID format :" + projectid);
			}

			if(projectName.trim().isEmpty()) {
				Assert.fail("project name is empty");
			}

			if(!companyId.equals(projectCompany)) {
				Assert.fail("company id missmatch");
			}

			List<String> statusList = Arrays.asList("Ongoing", "Complete", "Pending");
			if(!statusList.contains(projectStatus)) {
				Assert.fail("status is not match");
			}
			
			if(projectid != null && !projectid.isEmpty()) {
				momRequestPayload.setProject_id(projectid);
			}else {
				Assert.fail("project id is null or empty in response");
			}

		}else {
			Assert.fail("required index not found");
		}
	}

	@Test(priority=3, dataProvider="createMomData", dependsOnMethods="getProjectId")
	public void createMOM(MOM_Request momRequestPayload) throws Exception {

		getCompanyUserId(momRequestPayload); 
		getProjectId(momRequestPayload);

		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(momRequestPayload);
		System.out.println("final json paylaod : " + jsonPayload);

		Response createMoMResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(jsonPayload)
				.log().all()

				.when()
				.post(Mom_Api.Add_MOM)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(createMoMResponse.getStatusCode(), 200, "expected status code is not responeded");

		long responseTime = createMoMResponse.getTime();
		Assert.assertTrue(responseTime < 5000, " response time is too long > 5s" + ": actual :" + responseTime);

		List<String> mandatoryFields = Arrays.asList("id", "company_id", "project_id", "created", "creator");
		for(String field : mandatoryFields) {
			Assert.assertNotNull(createMoMResponse.jsonPath().get(field), "Mandatory field missing: " + field);
		}

		try {
			UUID.fromString(createMoMResponse.jsonPath().getString("id"));
		} catch (IllegalArgumentException e) {
			Assert.fail("id is not UUID format");
		}

		MomDetails.companyId=createMoMResponse.jsonPath().getString("company_id");
		MomDetails.projectId=createMoMResponse.jsonPath().getString("project_id");
		MomDetails.attendeeCuIds=createMoMResponse.jsonPath().getString("monkey_patch_attendees.id");
		MomDetails.momId=createMoMResponse.jsonPath().getString("id");

		Assert.assertEquals(MomDetails.companyId, momRequestPayload.getCompany_id(), "Mismatch in companyId");
		Assert.assertEquals(MomDetails.projectId, momRequestPayload.getProject_id(), "Mismatch in projectId");
	}

	@Test(priority=4, dependsOnMethods="createMOM")
	public void getMOM() throws Exception {

		if(MomDetails.momId.isEmpty() || MomDetails.momId == null) {
			throw new IllegalArgumentException("mom id is null or empty");
		}

		Response getMoMResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("momId", MomDetails.momId)
				.log().all()

				.when()
				.get(Mom_Api.Details_MOM)

				.then()
				.log().all()
				.extract().response();

		Assert.assertEquals(getMoMResponse.getStatusCode(), 200, "expected status code is not responede");

		MOM_Response momObj = getMoMResponse.as(MOM_Response.class);

		String jsonResponse = getMoMResponse.asString();
		SchemaValidator.validateSchema("schemas_files/mom.json", jsonResponse);

		Assert.assertEquals(momObj.getCompany_id(), MomDetails.companyId, "company id missmatch");
		Assert.assertEquals(momObj.getProject_id(), MomDetails.projectId, "project id missmatch");
	}
}
