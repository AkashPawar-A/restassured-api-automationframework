package com.onsite.timesheet_test_page;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.TimeSheet_Api;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

public class TimeSheetListTest extends BaseToken{

	@Test
	public void listtimesheet() throws Exception {

        String companyId = "75916659-9cbe-4ca7-812e-181a29229772";
        int count = 30;
        int pageNumber = 1;
        boolean morePages = true;

        while (morePages) {
            System.out.println("Validating page: " + pageNumber);

            Response response = 
                given()
                    .baseUri(ApiBasePath.BASE_URL)
                    .header("Authorization", AuthUtils.getToken())
                    .contentType(ContentType.JSON)
                    .queryParam("count", count)
                    .queryParam("page", pageNumber)
                    .queryParam("company_id", companyId)
                    .log().uri()

                .when()
                    .get(TimeSheet_Api.list_timesheet)

                .then()
                    .log().status()
                    .extract().response();
            
            String jsonResponse = response.asString();
            SchemaValidator.validateSchema("schemas_files/timesheet_schema.json", jsonResponse);
            
            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status 200 on page " + pageNumber);

            List<Map<String, Object>> dataList = response.jsonPath().getList("data");
            Assert.assertNotNull(dataList, "Data list should not be null on page " + pageNumber);
            
            Assert.assertTrue(dataList.size() <= count, "More than expected records returned on page " + pageNumber);

            for (Map<String, Object> item : dataList) {
                String projectId = (String) item.get("project_id");
                String actualCompanyId = (String) item.get("company_id");

                Assert.assertNotNull(projectId, "Project ID should not be null on page " + pageNumber);
                Assert.assertEquals(actualCompanyId, companyId, "Company ID mismatch on page " + pageNumber);
            }

            System.out.println("Page " + pageNumber + " validated successfully with " + dataList.size() + " records");

            // Move to next page
            String nextUrl = response.jsonPath().getString("page.next_url");
            morePages = (nextUrl != null && !nextUrl.isEmpty());
            pageNumber++;
        }

        System.out.println("All paginated timesheet records validated successfully");
    }
}
