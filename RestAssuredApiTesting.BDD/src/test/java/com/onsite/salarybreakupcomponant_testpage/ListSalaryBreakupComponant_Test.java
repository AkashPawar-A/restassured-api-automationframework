package com.onsite.salarybreakupcomponant_testpage;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.context.SalaryBreakupComponantDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryBreakupComponant_Api;
import com.onsite.pojo_request.SalaryBreakupComponent_Request;
import com.onsite.pojo_response.SalaryBreakupComponent;
import com.onsite.pojo_response.SalaryTemplate;
import com.onsite.utilities_page.AuthUtils;

public class ListSalaryBreakupComponant_Test {

	String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
	int pageNumber = 1;
	boolean morePages = true;
	int totalRecord = 0;

	List<Map<String, Object>> allRecords = new ArrayList<>();

	@Test(priority = 1)
	public void listAllowanceComponant() {

		while (morePages) {
			System.out.println("Validating page: " + pageNumber);

			Response listAllowanceResponse =
					given()
					.baseUri(ApiBasePath.BASE_URL)
					.header("Authorization", AuthUtils.getToken())
					.contentType(ContentType.JSON)
					.queryParam("company_id", companyId)
					.queryParam("type", "allowance")
					.log().uri()

					.when()
					.get(SalaryBreakupComponant_Api.LIST_SALARYBREAKUP_COMPONENT)

					.then()
					.extract().response();

			int responseStatusCode = listAllowanceResponse.getStatusCode();
			Assert.assertEquals(responseStatusCode, 200, "expetes sttaus code not found in response");

			// Print raw response for debugging
			String allowanceList = listAllowanceResponse.getBody().asString();
			System.out.println("Allowance List Response:\n" + allowanceList);

			// Parse the "data" list
			List<Map<String, Object>> dataList = listAllowanceResponse.jsonPath().getList("data");
			if (dataList == null || dataList.isEmpty()) {
				System.out.println("No allowance data found");
				break;
			}

			// Add all page records to master list
			allRecords.addAll(dataList);
			totalRecord += dataList.size();

			// Validate and print each record
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> item = dataList.get(i);
				int delete = (int) item.get("delete");
				String type = (String) item.get("type");

				Assert.assertEquals(type, "allowance", "Type mismatch for item: " + item);
				Assert.assertEquals(delete, 0, "Delete flag not 0 for item: " + item);

				System.out.println("Record " + (i) + ": " + item);
			}

			// Check for next page
			String nextUrl = listAllowanceResponse.jsonPath().getString("next_url");
			morePages = (nextUrl != null && !nextUrl.isEmpty());
			if (morePages) {
				pageNumber++;
			}
		}

		System.out.println("Total Records Collected: " + totalRecord);

		// Select first index record only
		if (!allRecords.isEmpty()) {
			Map<String, Object> selectedRecord = allRecords.get(0);
			System.out.println("Selected Record for Storage: " + selectedRecord);

			SalaryBreakupComponantDetails.delete = (int) selectedRecord.get("delete");
			SalaryBreakupComponantDetails.id = (String) selectedRecord.get("id");
			SalaryBreakupComponantDetails.name = (String) selectedRecord.get("name");
			SalaryBreakupComponantDetails.type = (String) selectedRecord.get("type");

			System.out.println("Record saved to SalaryBreakupComponantDetails successfully");

		} else {
			System.out.println("No records found to store");
		}
	}
}
