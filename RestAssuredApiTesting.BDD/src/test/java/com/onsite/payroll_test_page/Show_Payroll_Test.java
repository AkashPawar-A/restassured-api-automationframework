package com.onsite.payroll_test_page;

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
		System.out.println("response status code : " + responseStatusCode);
		Assert.assertEquals(responseStatusCode, 200, "expected status coed not found in response");
		
		String responseMessage = payrollResponse.jsonPath().getString("message");
		if(responseMessage != null) {
			System.out.println("response message :" + responseMessage);
		}else {
			System.out.println("response message is not added");
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
