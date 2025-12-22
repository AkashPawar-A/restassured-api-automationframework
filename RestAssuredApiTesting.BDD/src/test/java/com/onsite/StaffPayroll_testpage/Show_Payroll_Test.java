package com.onsite.StaffPayroll_testpage;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.Payroll_Api;
import com.onsite.utilities_page.AuthUtils;

public class Show_Payroll_Test {
	
	@Test(priority=1)
	public void showPayroll() {
		
		String partyId = "2596311f-c612-453b-947a-9ec42caec15b";
		
		Response payrollResponse =
				
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", partyId)
				.log().uri()
				
				.when()
				.patch(Payroll_Api.SHOW_PAYROLL)
				
				.then()
				.log().all()
				.extract().response();
		
		String partyIdResponse = payrollResponse.getBody().asString();
		
		int responseStatusCode = payrollResponse.getStatusCode();
		String responseMessage = payrollResponse.jsonPath().getString("message");

		if(responseStatusCode == 200) {
			System.out.println("success status code is 200");	
			System.out.println("response Message: " + responseMessage);
		} else {
			System.out.println("failure status code is " + responseStatusCode);		
			System.out.println("failure message :" + responseMessage);
			
			Assert.fail("API failed with status code: " + responseStatusCode + 
	                " and message: " + responseMessage);
		}
		
		String responseContentType = payrollResponse.getContentType();
		System.out.println("response content type :" + responseContentType);
		Assert.assertTrue(responseContentType.contains("application/json"), "response content type is missmatch");
		
		String allowMethod = payrollResponse.getHeader("Access-Control-Allow-Methods");
		Assert.assertTrue(allowMethod.contains("PATCH"), "allows method not added in headerr options ");
		
		long responseTime = payrollResponse.getTime();
		System.out.println("response time is : " + responseTime);
		Assert.assertTrue(responseTime < 2000, "respomse time is to long ");
		
		int payroll_id_hidden_flag = payrollResponse.jsonPath().get("hidden");
		int payroll_id_delete_flag = payrollResponse.jsonPath().get("delete");
		int party_id_hidden_flag = payrollResponse.jsonPath().get("monkey_patch_party_company_user.hidden");
		String partyName = payrollResponse.jsonPath().get("monkey_patch_party_company_user.name");
		
		if(party_id_hidden_flag != 1 && payroll_id_delete_flag != 1 
				&& party_id_hidden_flag != 1) {
			System.out.println("party name : " + partyName + " : Payroll party visible in active list");
		}else {
			System.out.println("party name : " + partyName + " : Payroll party visible in active list");
		}
			
	}

}
