package com.onsite.mom_test_page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.CompanyUser;
import com.onsite.endpoints.Mom_Api;
import com.onsite.endpoints.Project_Api;
import com.onsite.pojo_request.MOM_Request;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.JsonDataProvider;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

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
		
		Assert.assertEquals(companyUserResponse.getStatusCode(), 200, "Expected Status Code not responded");
		
		List<Map<String, Object>> companyUserList = companyUserResponse.jsonPath().getList("data");
		if(companyUserList==null || companyUserList.isEmpty()) {
			throw new RuntimeException("company user list is empty or null");
		}
		
		int requiredIndex = 2;
		if(companyUserList.size() <= requiredIndex) {
			throw new RuntimeException("required index not match");
		}
		
		Map<String, Object> selectCompanyUser = companyUserList.get(requiredIndex);
		Object idObj = selectCompanyUser.get("id");
		
		if(idObj==null) {
			throw new RuntimeException("company user id is null : " + requiredIndex);
		}
		
		String companuserID = idObj.toString();
		String[] companUserID = new String[] {companuserID};
		String companyID = selectCompanyUser.get("company_id").toString();
		String companyUserName = selectCompanyUser.get("name").toString();
		
		Object hiddenFlag = selectCompanyUser.get("hidden");
		int hiddenCompanyUser = hiddenFlag != null ? Integer.parseInt(hiddenFlag.toString()):0;
		Assert.assertEquals(hiddenFlag, 0, "selected companyUser hidden flag = 1");
		
		Assert.assertNotNull(companuserID, "companuserID is null");
		Assert.assertNotNull(companyUserName, "companyUserName is null");
		
		System.out.println("selected company user details");
		System.out.println("company id : " + companyID);
		System.out.println("company user id : " + companuserID);
		System.out.println("company user name :" + companyUserName);
		
		momRequestPayload.setCompany_id(companyID);
		momRequestPayload.setAttendee_cu_ids(companUserID);	
	}
	
	@Test(priority=2, dataProvider="createMomData")
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
		
		List<Map<String, Object>> projectList = projectIdResponse.jsonPath().getList("projects");
		if(projectList==null || projectList.isEmpty()) {
			throw new RuntimeException("project list is null or empty");
		}
		
		int requriedIndex = 1;
		if(projectList.size() <= requriedIndex) {
			throw new RuntimeException("requriedIndex is not match in projectlist");
		}
		
		Map<String, Object> selectedProject = projectList.get(requriedIndex);
		Object projectIdObj = selectedProject.get("id");
		
		if(projectIdObj==null) {
			throw new RuntimeException("project id is null");
		}
		
		String projectId = projectIdObj.toString();
		
		String projectName = selectedProject.get("name").toString();
		String projectType = selectedProject.get("type").toString();
		
		Assert.assertNotNull(projectName, "project name is null");
		Assert.assertNotNull(projectType, "project type is null");
		
		System.out.println("project name is : " + projectName);
		System.out.println("project type : " + projectType);
		
		momRequestPayload.setProject_id(projectId);
	}
	
	@Test(priority=3, dataProvider="createMomData")
	public void createMOM(MOM_Request momRequestPayload) throws Exception {
		
		getCompanyUserId(momRequestPayload);
		getProjectId(momRequestPayload);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonPayload = mapper.writeValueAsString(momRequestPayload);
		System.out.println("final json paylaod : " + jsonPayload);
		
		Response momResponse = 
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
		
		Assert.assertEquals(momResponse.getStatusCode(), 200, "expected status code is not responeded");
	}

}
