package com.onsite.mom_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.MomDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Mom_Api;
import com.onsite.pojo_request.MOM_Request;
import com.onsite.pojo_response.MOM_Response;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import com.onsite.utilities_page.SchemaValidator;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Edit_MOM_Test {

	@DataProvider(name="editMomData")
	public Object[][] editData(){
		String momPath = "src/test/resources/test_data/EditMOM_TestData.json";
		Object[][] momData = JsonDataProvider.getDataFromJson(momPath, MOM_Request.class);

		Object[][] dataObject = new Object[momData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = momData[i][0];
		}
		return dataObject;    
	}

	@Test(priority=1, dataProvider="editMomData")
	public void getlistMom(MOM_Request momRequestPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		Response momListResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.queryParam("company_id", companyId)
				.contentType(ContentType.JSON)
				.log().uri()

				.when()
				.get(Mom_Api.List_MON)

				.then()
				.extract().response();

		Assert.assertEquals(momListResponse.getStatusCode(), 200, "expectes status code not found");

		long responseTime = momListResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long : " + responseTime);

		List<Map<String, Object>> momList = momListResponse.jsonPath().getList("data");
		if(momList==null || momList.isEmpty()) {
			Assert.fail("mom list is empty or null in response");
		}

		Set<String> ids = new HashSet<>();
		for(Map<String, Object> momIdList : momList) {

			Object idObj = momIdList.get("id");
			if(idObj==null) {
				Assert.fail("mon id is null in response : " + momIdList);
			}

			String momIds = idObj.toString();
			if(!ids.add(momIds)) {
				Assert.fail("duplicate ids found : " + momIds);
			}

			try {
				UUID.fromString(momIds);
			}catch(Exception e) {
				Assert.fail("invalid UUID form id found : " + momIds);
			}
		}

		int reqIndex = 0;
		if(momList.size() > reqIndex) {
			Map<String, Object> selecetMomEntry = momList.get(reqIndex);

			List<String> reqFieldList = Arrays.asList("id", "name", "project_id", 
					"company_id", "creator", "creator_company_user_id", "attendee_cu_ids", 
					"mom_date", "delete", "created", "updated", "search");

			for(String key : reqFieldList) {
				if(!selecetMomEntry.containsKey(key)) {
					Assert.fail("mandotory field is missing in keyList : " + key);
				}

				Object value = selecetMomEntry.get(key);
				if(value == null) {
					Assert.fail("key is a null : " + value);
				}

				if (value instanceof String && ((String) value).trim().isEmpty()) {
					Assert.fail("field '" + key + "' is empty string");
				}
			}

			String momId = selecetMomEntry.get("id").toString();
			String Name = selecetMomEntry.get("name").toString();
			String projectId = selecetMomEntry.get("project_id").toString();
			String companyID = selecetMomEntry.get("company_id").toString();
			String creatorId = selecetMomEntry.get("creator").toString();
			String companyUserId = selecetMomEntry.get("creator_company_user_id").toString();
			String attendeeId = selecetMomEntry.get("attendee_cu_ids").toString();
			String momdate = selecetMomEntry.get("mom_date").toString();
			String cretedDate = selecetMomEntry.get("created").toString();
			String updatedDate = selecetMomEntry.get("updated").toString();
			String search = selecetMomEntry.get("search").toString();
			int deleteFlag = Integer.parseInt(selecetMomEntry.get("delete").toString());

			for (String uuidField : Arrays.asList(momId, projectId, companyID, creatorId, companyUserId)) {
				try {
					UUID.fromString(uuidField);
				} catch(Exception e) {
					Assert.fail("Invalid UUID format in field: " + uuidField);
				}
			}

			if(deleteFlag == 0) {
				momRequestPayload.setCompany_id(companyID);
				momRequestPayload.setId(momId);
				momRequestPayload.setProject_id(projectId);
				momRequestPayload.setMom_date(momdate);
			} else if(deleteFlag==1){
				Assert.assertTrue(selecetMomEntry.containsKey("updated"), "Deleted record updated date");
				String createdDate = selecetMomEntry.get("created").toString();
				String updatedDate1 = selecetMomEntry.get("updated").toString();
				Assert.assertTrue(updatedDate1.compareTo(createdDate) >= 0, 
						"Deleted record should have updated date >= created date");
			} else {
				Assert.fail("mom id is not available");
			}
		}
	}

	@Test(priority=2, dataProvider="editMomData")
	public void getlistAttendeeMom(MOM_Request momRequestPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		Response momAttendeelistResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.log().uri()

				.when()
				.get(Mom_Api.List_MOM_Attendee)

				.then()
				.extract().response();

		Assert.assertEquals(momAttendeelistResponse.getStatusCode(), 200, "Expected stataus code is not found");

		long responseTime = momAttendeelistResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long : " + responseTime);
		
		
		List<String> attendsIds = momAttendeelistResponse.jsonPath().getList("id");
	    for(String attendeeId : attendsIds) {
	        if(attendeeId == null || attendeeId.isEmpty()) {
	            Assert.fail("attendee ids list is empty ya null");
	        }
	    }

	    Set<String> id = new HashSet<>();
	    for(String attendeeId : attendsIds) {
	        if(attendeeId == null) {
	            Assert.fail("attendee id is null");
	        }
	        if(!id.add(attendeeId)) {
	            Assert.fail("attendee id duplicate found : " + attendeeId);
	        }
	        try {
	            UUID.fromString(attendeeId);
	        } catch(Exception e) {
	            Assert.fail("invalid UUID format id found : " + attendeeId);
	        }
	    }

	    // object validate (full attendee)
	    List<Map<String, Object>> attendees = momAttendeelistResponse.jsonPath().getList("$");
	    List<String> selectedAttendees = new ArrayList<>();

	    for(Map<String, Object> selectAttendee : attendees) {
			String attendeeId = selectAttendee.get("id").toString();
			String attendeeCompanyId = selectAttendee.get("company_id").toString();
			String attendeeType = selectAttendee.get("type").toString();
			String attendeeCreator = selectAttendee.get("creator").toString();
			String attendeeeName = selectAttendee.get("name").toString();
			String attendeeUserId = selectAttendee.get("user_id").toString();
			String attendeepartyId = selectAttendee.get("party_id").toString();
			int attendeeSequence = Integer.parseInt(selectAttendee.get("sequence").toString());
			int attendeeHidden = Integer.parseInt(selectAttendee.get("hidden").toString());

	        // validate required fields
	        List<String> reqField = Arrays.asList("id", "company_id", "type", "creator",
	                "name", "user_id", "sequence", "hidden");
	        for(String key : reqField) {
	            if(!selectAttendee.containsKey(key)) {
	                Assert.fail("mandatory field missing: " + key);
	            }
	            Object value = selectAttendee.get(key);
	            if(value == null) {
	                Assert.fail("mandatory field is null: " + key);
	            }
	            if(value instanceof String && ((String) value).trim().isEmpty()) {
	                Assert.fail("mandatory field is empty: " + key);
	            }
	        }

	        if(attendeeHidden == 0) {  // only active attendees
	            selectedAttendees.add(attendeeId);
	        }
	    }

	    if(!selectedAttendees.isEmpty()) {
	        momRequestPayload.setAttendee_cu_ids(selectedAttendees.toArray(new String[0]));
	        System.out.println("Selected attendees: " + selectedAttendees);
	    } else {
	        Assert.fail("No valid attendees found to add in MOM");
	    }
	}

	@Test(priority=3, dataProvider="editMomData")
	public void getlistProjectMom(MOM_Request momRequestPayload) {

		String companyId = "75916659-9cbe-4ca7-812e-181a29229772";

		Response projectlistResponse = 
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

		Assert.assertEquals(projectlistResponse.getStatusCode(), 200, "expectes status code not found");

		long responseTime = projectlistResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long :" + responseTime);

		List<String> projectIds = projectlistResponse.jsonPath().getList("id");
		for(String projectId : projectIds) {
			if(projectId == null || projectId.isEmpty()) {
				Assert.fail("project list is empty or null");
			}
			try {
				UUID.fromString(projectId);
			}catch(Exception e) {
				Assert.fail("invalid project id found");
			}
		}

		Set<String> id = new HashSet<>();
		for(String projectId : projectIds) {			
			if(!id.add(projectId)) {
				Assert.fail("duplicate project id found :" + id);
			}
		}

		List<Map<String, Object>> projectList = projectlistResponse.jsonPath().getList("$");
		int projectIndex = 1;
		if(projectList.size() > projectIndex) {
			Map<String, Object> selectedProject = projectList.get(projectIndex);

			List<String> reqfield = Arrays.asList("id", "type", "name", "creator", "creator_company_user_id",
					"status");
			for(String key : reqfield) {
				if(!selectedProject.containsKey(key)) {
					Assert.fail("mandatory field is not visibel in response");
				}

				Object value = selectedProject.get(key);
				if(value==null) {
					Assert.fail("mandatry field is null in response");
				}

				if(value instanceof String && ((String ) value).trim().isEmpty()) {
					Assert.fail("mandatory field is empty in response");
				}
			}

			String projectID = selectedProject.get("id").toString();
			String projectType = selectedProject.get("type").toString();
			String projectName = selectedProject.get("name").toString();
			String projectCreator = selectedProject.get("creator").toString();
			String companyUser = selectedProject.get("creator_company_user_id").toString();
			String projectStatus = selectedProject.get("status").toString();

			List<String> statusList = Arrays.asList("Ongoing", "Completed", "On Hold", "Not Started");
			for(String list : statusList) {
				if(projectStatus.equals(list)) {
					momRequestPayload.setProject_id(projectID);
					break;
				}
			}
		}
	}

	@Test(priority=4, dataProvider="editMomData")
	public void editMom(MOM_Request momRequestPayload) throws Exception {

		getlistMom(momRequestPayload);
		getlistAttendeeMom(momRequestPayload);
		getlistProjectMom(momRequestPayload);

		ObjectMapper mapper = new ObjectMapper();
		String jsonObjpayload = mapper.writeValueAsString(momRequestPayload);
		System.out.println("final jason payload : " + jsonObjpayload);

		Response editMomResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(jsonObjpayload)
				.log().uri()

				.when()
				.patch(Mom_Api.Edit_MOM)

				.then()
				.extract().response();

		System.out.println("Get MOM After Edit: " + editMomResponse.asString());
		MomDetails.momId=editMomResponse.jsonPath().getString("id");

		Assert.assertEquals(editMomResponse.getStatusCode(), 200, "Expc=ected sttaus code not found ");

		long responseTime = editMomResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is toolong");

		List<String> requireField = Arrays.asList("id", "name", "project_id", "company_id", "creator", "creator_company_user_id", 
				"attendee_cu_ids");
		for(String key : requireField) {
			Assert.assertNotNull(editMomResponse.jsonPath().get(key), "mandatory field is missing:" + requireField);
		}
		
		MomDetails.momId = editMomResponse.jsonPath().getString("id");
		MomDetails.projectId = editMomResponse.jsonPath().getString("project_id");
		MomDetails.companyId = editMomResponse.jsonPath().getString("company_id");
		MomDetails.creatorId = editMomResponse.jsonPath().getString("creator");
		MomDetails.creatorCompanyUserId = editMomResponse.jsonPath().getString("creator_company_user_id");
		MomDetails.attendeeCuIds = editMomResponse.jsonPath().getString("attendee_cu_ids");
		
		Assert.assertEquals(MomDetails.momId, momRequestPayload.getId(), "mismatch mom id ");
		Assert.assertEquals(MomDetails.projectId, momRequestPayload.getProject_id(), "mismatch project id");
		Assert.assertEquals(MomDetails.attendeeCuIds, MomDetails.attendeeCuIds, "mismatch attendee id");
	}

	@Test(priority=5, dataProvider="editMomData")
	public void geteditdata(MOM_Request momRequestPayload) throws Exception {

		if (MomDetails.momId == null || MomDetails.momId.isEmpty()) {
			throw new IllegalArgumentException("mom id is null or empty");
		}

		Response getMomResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.pathParam("momId", MomDetails.momId)

				.when()
				.get(Mom_Api.Details_MOM)

				.then()
				.extract().response();

		System.out.println("MOM after edit: " + getMomResponse.asString());

		Assert.assertEquals(getMomResponse.getStatusCode(), 200, "Expc=ected sttaus code not found ");

		long responseTime = getMomResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is toolong");

		List<String> requireField = Arrays.asList("id", "name", "project_id", "company_id", "creator", "creator_company_user_id", 
				"attendee_cu_ids");
		for(String key : requireField) {
			Assert.assertNotNull(getMomResponse.jsonPath().get(key), "mandatory field is missing:" + requireField);
		}
		
		MOM_Response getMomObj = getMomResponse.as(MOM_Response.class);
		
		String jsonResponse = getMomResponse.asString();
		SchemaValidator.validateSchema("schemas_files/mom.json", jsonResponse);
		
		Assert.assertEquals(getMomObj.getId(), MomDetails.momId, ": mom id mismatch");
		Assert.assertEquals(getMomObj.getProject_id(), MomDetails.projectId, ": mom project id mismatch");
		Assert.assertEquals(getMomObj.getCompany_id(), MomDetails.companyId, ": mom company id mismatch");
		Assert.assertEquals(getMomObj.getCreator(), MomDetails.creatorId, ": mom creator id si mismatch");
	}
}


