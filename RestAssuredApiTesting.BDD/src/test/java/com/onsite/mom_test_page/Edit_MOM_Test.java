package com.onsite.mom_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Mom_Api;
import com.onsite.pojo_request.MOM_Request;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Edit_MOM_Test {

	@Test(priority=1)
	public void listMom(MOM_Request momRequestPayload) {

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

		int reqIndex = 1;
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

			String momid = selecetMomEntry.get("id").toString();
			String momName = selecetMomEntry.get("name").toString();
			String momProjectId = selecetMomEntry.get("project_id").toString();
			String momCompanyId = selecetMomEntry.get("company_id").toString();
			String momCreatorId = selecetMomEntry.get("creator").toString();
			String momCompanyUserId = selecetMomEntry.get("creator_company_user_id").toString();
			String momattendeeId = selecetMomEntry.get("attendee_cu_ids").toString();
			String momDate = selecetMomEntry.get("mom_date").toString();
			String momCretedDate = selecetMomEntry.get("created").toString();
			String momUpdatedDate = selecetMomEntry.get("updated").toString();
			String momSearch = selecetMomEntry.get("search").toString();
			int momDeleteFlag = Integer.parseInt(selecetMomEntry.get("delete").toString());

			for (String uuidField : Arrays.asList(momid, momProjectId, momCompanyId, momCreatorId, momCompanyUserId)) {
				try {
					UUID.fromString(uuidField);
				} catch(Exception e) {
					Assert.fail("Invalid UUID format in field: " + uuidField);
				}
			}

			if(momDeleteFlag == 0) {
				momRequestPayload.setId(momid);
			} else if(momDeleteFlag==1){
				Assert.assertTrue(selecetMomEntry.containsKey("updated"), "Deleted record updated date");

				String createdDate = selecetMomEntry.get("created").toString();
				String updatedDate = selecetMomEntry.get("updated").toString();
				Assert.assertTrue(updatedDate.compareTo(createdDate) >= 0, 
						"Deleted record should have updated date >= created date");
			} else {
				Assert.fail("mom id is not available");
			}
		}
	}

	@Test(priority=2)
	public void listAttendeeMom(MOM_Request momRequestPayload) {

		Response momAttendeelistResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.log().uri()

				.when()
				.get(Mom_Api.List_MOM_Attendee)

				.then()
				.extract().response();

		Assert.assertEquals(momAttendeelistResponse.getStatusCode(), 200, "Expected sttaus code is not found");

		long responseTime = momAttendeelistResponse.getTime();
		Assert.assertTrue(responseTime < 5000, "response time is too long : " + responseTime);

		List<Map<String, Object>> attendsIds = momAttendeelistResponse.jsonPath().getList("id");
		for(Map<String, Object> Ids : attendsIds) {
			if(Ids == null || Ids.isEmpty()) {
				Assert.fail("attendee ids list is empty ya null");
			}
		}

		Set<String> id = new HashSet<>();
		for(Map<String, Object> attendeeIdList : attendsIds) {

			Object idObj = attendeeIdList.get(id);
			if(idObj==null) {
				Assert.fail("attendee is is null");
			}

			String attendeeId = idObj.toString();
			if(!id.add(attendeeId)) {
				Assert.fail("attendee is duplicate found");
			}

			try {
				UUID.fromString(attendeeId);
			}catch(Exception e) {
				Assert.fail("invaid UUID format id found");
			}
		}

		int attendeeIndex = 1;
		if(attendsIds.size() > attendeeIndex) {
			Map<String, Object> selectAttendee = attendsIds.get(attendeeIndex);
			
			List<String> reqField = Arrays.asList("id", "company_id", "type", "creator",
					"name", "user_id", "party_id", "sequence", "hidden");
			
			for(String key : reqField) {
				if(!selectAttendee.containsKey(key)) {
					Assert.fail("mandotory field are not avialble in response");
				}
				
				Object value = selectAttendee.get(key);
				if(value==null) {
					Assert.fail("mandatory field is null in response");
				}
				
				if(value instanceof String && ((String) value).trim().isEmpty()) {
					Assert.fail("mandatory field is empty in response");
				}
			}
			
			String attendeeId = selectAttendee.get("id").toString();
			String attendeeCompanyId = selectAttendee.get("company_id").toString();
			String attendeeType = selectAttendee.get("type").toString();
			String attendeeCreator = selectAttendee.get("creator").toString();
			String attendeeeName = selectAttendee.get("name").toString();
			String attendeeUserId = selectAttendee.get("user_id").toString();
			String attendeepartyId = selectAttendee.get("party_id").toString();
			int attendeeSequence = Integer.parseInt(selectAttendee.get("sequence").toString());
			int attendeeHidden = Integer.parseInt(selectAttendee.get("hidden").toString());
			
			for(String uuidfiled : Arrays.asList("attendeeId","attendeeCompanyId","attendeeUserId","attendeepartyId")) {
				try {
					UUID.fromString(uuidfiled);
				}catch(Exception e) {
					Assert.fail("invalid uuid format id found");
				}
			}
			
			if(attendeeHidden==0) {
				momRequestPayload.setAttendee_cu_ids(new String[] {attendeeId});
			}else {
				Assert.fail("attendee is hidden==1");
			}
		}

	}
	
	
	
	

	@Test(priority=3)
	public void listProjectMom(MOM_Request momRequestPayload) {

		Response momProjectlistResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.log().uri()

				.when()
				.get(Mom_Api.List_MOM_Project)

				.then()
				.extract().response();
	}

	@DataProvider(name="ediotMomData")
	public Object[][] editData(){

		String momPath = "src/test/resources/test_data/EditMOM_TestData";

		Object[][] momData = JsonDataProvider.getDataFromJson(momPath, MOM_Request.class);

		Object[][] dataObject = new Object[momData.length][1];
		for(int i=0; i<dataObject.length; i++) {
			dataObject[i][0] = momData[i][0];
		}
		return dataObject;	
	}

	@Test(priority=4)
	public void editMom(MOM_Request momRequestPayload) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String jsonObj = mapper.writeValueAsString(momRequestPayload);
		System.out.println("final jason payload : " + jsonObj);

		Response editMomResponse = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.log().uri()

				.when()
				.patch(Mom_Api.Edit_MOM)

				.then()
				.extract().response();
	}

}
