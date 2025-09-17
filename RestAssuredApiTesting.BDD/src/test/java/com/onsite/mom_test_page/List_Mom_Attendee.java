package com.onsite.mom_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

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

public class List_Mom_Attendee {
	
	int validatedCount = 0;

	@Test
	public void listMomAttendee() {

		String companyId ="75916659-9cbe-4ca7-812e-181a29229772";
		List<String> recordList = new ArrayList<>();

		Response attendeeListResponse = 
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

		Assert.assertEquals(attendeeListResponse.getStatusCode(), 200, "expected status code not found");

		long responseTime = attendeeListResponse.getTime();
		Assert.assertTrue(responseTime < 2000, "expected response time is too much long");

		String contentType = attendeeListResponse.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
		Assert.assertTrue(contentType.contains("application/json; charset=utf-8"), "application contenttype is not json format");

		List<Map<String, Object>> attendeeIds = attendeeListResponse.jsonPath().getList("");
		if(attendeeIds==null || attendeeIds.isEmpty()) {
			Assert.fail("attendee id list is emmpty or null in response");
		}

		Set<String> ids= new HashSet<>();

		for(Map<String, Object> attenddeId : attendeeIds) {
			int hiddenFlag = Integer.parseInt(attenddeId.get("hidden").toString());

			if(hiddenFlag==0) {
				
				boolean missingKeyFound=false;
				
				List<String> requiredField = Arrays.asList("id", "company_id", "type", "creator", 
						"name", "user_id", "hidden");

				for(String requiredKey : requiredField) {
					if(!attenddeId.containsKey(requiredKey)) {
						Assert.fail("Mandatory key-value is null for key: " + requiredKey + " :Data: " + attenddeId);
						missingKeyFound = true;
	                    continue;
					}

					Object value = attenddeId.get(requiredKey);
					if(value==null) {
						Assert.fail("required key is null in response" + requiredKey);
					}
				}
				String attendeedata = "Id :" + attenddeId.get("id") +
						", name :" + attenddeId.get("name") +
						", company_id :" + attenddeId.get("company_id") +
						", type :" + attenddeId.get("type") +
						", creator :" + attenddeId.get("creator") +
						", user_id :" + attenddeId.get("user_id") +
						", hidden :" + attenddeId.get("hidden");
				recordList.add(attendeedata);
                validatedCount++;
            }
		}
        recordList.forEach(System.out::println);
        System.out.println("Total validated attendees: " + validatedCount);
    }
}

