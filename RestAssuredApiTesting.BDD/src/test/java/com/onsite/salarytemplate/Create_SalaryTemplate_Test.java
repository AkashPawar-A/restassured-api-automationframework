package com.onsite.salarytemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.pojo_request.Add_SalaryTemplate;
import com.onsite.pojo_response.SalaryTemplate;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.response.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.onsite.utilities_page.CompanyContext;

public class Create_SalaryTemplate_Test extends BaseToken{

	private Response salaryTemplateResponse;
	private Add_SalaryTemplate salaryTemplate_Payload;

	@DataProvider(name="salaryTemplateData")
	public Object[][] getDtata() throws Exception{

		String createTemplate_path = "src/test/resources/testdata_salaryTemplate/Create_salaryTemplate.json";

		String jsonPayload = new String(Files.readAllBytes(Paths.get(createTemplate_path)));

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		salaryTemplate_Payload = mapper.readValue(jsonPayload, Add_SalaryTemplate.class);

		String finalPayload = mapper.writeValueAsString(salaryTemplate_Payload);

		SchemaValidator.validateSchema("requestSchemas_files/SalaryTemplateRequest.json", finalPayload);

		return new Object[][] {
			{ finalPayload }
		};
	}

	@Test(dataProvider="salaryTemplateData", priority=1)
	public void create_salarytemplate(String finalPayload) throws Exception {

		salaryTemplateResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().uri()

				.when()
				.post(SalaryTemplate_Api.IN_ADD_SALARYTEMPLATE)

				.then()
				.log().all()
				.extract().response();

		SalaryTemplate response_salaryTemplate = salaryTemplateResponse.as(SalaryTemplate.class);
	}

	@Test(priority=2, dependsOnMethods="create_salarytemplate")
	public void validStatusCode() {

		int ResponseStatusCode = salaryTemplateResponse.getStatusCode();

		Assert.assertEquals(ResponseStatusCode, 200, "in/add/salary-templte api fail so response status code :" + ResponseStatusCode);
	}

	@Test(priority=3, dependsOnMethods="create_salarytemplate")
	public void validMessage() {

		String responseMessage = salaryTemplateResponse.jsonPath().getString("message");

		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("validation message :" + responseMessage);
		} else {
			System.out.println("validation message null or empty in response");
		}	
	}

	@Test(priority=4, dependsOnMethods="create_salarytemplate")
	public void validResponseTime() {

		long responseTime = salaryTemplateResponse.getTime();

		Assert.assertTrue(responseTime <= 2000, "in/add/salary-template api response time too much long" + responseTime);
	}

	@Test(priority=5, dependsOnMethods="create_salarytemplate")
	public void validResponseSchema() throws Exception {

		SchemaValidator.validateSchema("responseSchema_files/SalaryTemplateResponse.json", salaryTemplateResponse.asString());
	}

	@Test(priority=6, dependsOnMethods="create_salarytemplate")
	public void validCompanyId() {

		String getCompanyId = CompanyContext.getCompanyId();
		String resCompanyId = salaryTemplateResponse.jsonPath().getString("company_id");
		String reqCompanyId = salaryTemplate_Payload.getCompany_id();

		if(reqCompanyId == null || reqCompanyId.isEmpty()) {

			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "company_id required");
			String erreMessage = salaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(erreMessage, "company id required", "incorrcet validation message");
			System.out.println("erreMessage : " + erreMessage);
		} else {

			Assert.assertNotNull(getCompanyId, "company context company id should not be null");
			Assert.assertFalse(getCompanyId.trim().isEmpty(), "company context company id should not be empty");
			Assert.assertNotNull(resCompanyId, "response company id should not be null");
			Assert.assertFalse(resCompanyId.trim().isEmpty(), "response company id should not be empty");

			Assert.assertEquals(getCompanyId, resCompanyId, "Compnay Context company id and response company id should not be match");
		}
	}

	@Test(priority=7, dependsOnMethods="create_salarytemplate")
	public void validSalaryTemplateName() {

		String reqTemplateName= salaryTemplate_Payload.getName();
		String resTemplateName = salaryTemplateResponse.jsonPath().getString("name");
		
		if(reqTemplateName == null || reqTemplateName.trim().isEmpty()) {
			
			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "template name required");
			String errorMessage = salaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "template name required", "error message is not match");
			System.out.println("error message :" + errorMessage);
		} else {
			
			Assert.assertNotNull(resTemplateName, "response template name should not be null");
			Assert.assertFalse(resTemplateName.trim().isEmpty(), "response template name should not be empty");

			Assert.assertEquals(reqTemplateName, resTemplateName, "request name and response template name should not be match");
		}
	}
	
	@Test(priority=8, dependsOnMethods="create_salarytemplate")
	public void validCtcAmount() {
		
		Double reqCtcAmount = salaryTemplate_Payload.getCtc_amount();
		Double resCtcAmount= salaryTemplateResponse.jsonPath().getDouble("ctc_amount");
		
		if(reqCtcAmount == null) {
			
			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "ctc_amount required");
			String errorMessage = salaryTemplateResponse.jsonPath().getString("ctc_amount required");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {
			
			Assert.assertNotNull(resCtcAmount, "ctc_amount should not be null");
			Assert.assertEquals(reqCtcAmount, resCtcAmount, "ctc amount should not be match");
		}
	}
	
	@Test(priority=9, dependsOnMethods="create_salarytemplate")
	public void validSalaryTemplateType() {
		
		String reqType = salaryTemplate_Payload.getType();
		String resType = salaryTemplateResponse.jsonPath().getString("type");
		
		if(reqType == null || reqType.isEmpty()) {
			
			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "ctc_amount required");
			String errorMessage = salaryTemplateResponse.jsonPath().getString("ctc_amount required");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {
			
			List<String> allowedType = Arrays.asList("monthly", "daily");
			
			Assert.assertTrue(allowedType.contains(reqType), "invalid request type :" + reqType);
			Assert.assertTrue(allowedType.contains(resType), "invalid response type :" + resType);
			Assert.assertNotNull(resType, "salary template type is should not be null");
			Assert.assertFalse(resType.trim().isEmpty(), "salary template si should not be empty");
			
			Assert.assertEquals(reqType, resType, "salary template type is missmatch");
		}
	}
	
	@Test(priority=10, dependsOnMethods="create_salarytemplate")
	public void validDayoff() {
		
		Integer[] reqDayoff = salaryTemplate_Payload.getDayoff();
		Integer[] resDayOff = salaryTemplateResponse.jsonPath().get("dayoff");
		
		Assert.assertEquals(reqDayoff, resDayOff, "request and response dayoff mismatch");
	}
	
	@Test(priority=11, dependsOnMethods="create_salarytemplate")
	public void validBasicName() {
		
		String reqBasicName = salaryTemplate_Payload.getBasic().getName();
		String resBasicName = salaryTemplateResponse.jsonPath().getString("basic.name");
		
		if(reqBasicName == null || reqBasicName.isEmpty()) {
			
			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "basic_name required");
			String errorMessage = salaryTemplateResponse.jsonPath().getString("basic_name required");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {
			
			List<String> allowedName = Arrays.asList("basic");
			
			Assert.assertTrue(allowedName.contains(reqBasicName), "invalid request basic name");
			Assert.assertTrue(allowedName.contains(resBasicName), "invalid response basic name");
			Assert.assertNotNull(resBasicName, "response basic name should not be null");
			Assert.assertFalse(resBasicName.trim().isEmpty(), "response basic name should not be empty");
			
			Assert.assertEquals(reqBasicName, resBasicName, "request and response basic name missmatch");	
		}
	}
	
	@Test(priority=12, dependsOnMethods="create_salarytemplate")
	public void validBasicRelationType() {
		
		String reqBasicRelationType = salaryTemplate_Payload.getBasic().getRelation_type();
		String resBasicRelationType = salaryTemplateResponse.jsonPath().getString("basic.relation_type");
		
		if(reqBasicRelationType == null || reqBasicRelationType.isEmpty()) {
			
			Assert.assertEquals(salaryTemplateResponse.getStatusCode(), 400, "basic_relationType required");
			String errorMessage = salaryTemplateResponse.jsonPath().getString("basic_relationType required");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {
			
			List<String> allowedType = Arrays.asList("fixed", "ctc");
			
			Assert.assertTrue(allowedType.contains(reqBasicRelationType), "invalid request basic relation type");
			Assert.assertTrue(allowedType.contains(resBasicRelationType), "invalid response basic relation type");
			Assert.assertNotNull(resBasicRelationType, "basic_relation type should not be null");
			Assert.assertFalse(resBasicRelationType.trim().isEmpty(), "basic_relation type should not be empty");
			
			Assert.assertEquals(reqBasicRelationType, resBasicRelationType, "request and response basic relation type missmatch");
		}
	}
	
	@Test(priority=13, dependsOnMethods="create_salarytemplate")
	public void validBasicRelationValue() {
		
		String reqBasicRelationType = salaryTemplate_Payload.getBasic().getRelation_type();
		Float reqBasicRelationValue = salaryTemplate_Payload.getBasic().getRelation_value();
		Double reqCtcAmount = salaryTemplate_Payload.getCtc_amount();
		
		String resBasicRelationType = salaryTemplateResponse.jsonPath().getString("basic.relation_type");
		Float resBasicRelationValue = salaryTemplateResponse.jsonPath().getFloat("basic.relation_value");
		Double resCtcAmount = salaryTemplateResponse.jsonPath().getDouble("ctc_amount");
		
		List<String> allowedType = Arrays.asList("fixed", "ctc");
		
		Assert.assertTrue(allowedType.contains(reqBasicRelationType), "invalid Request basic relation type :" + reqBasicRelationType);
		Assert.assertTrue(allowedType.contains(resBasicRelationType), "invalid response basic relation type" + resBasicRelationType);
		
		Assert.assertEquals(reqBasicRelationType, resBasicRelationType, "request and response relation tyep missmatch");
		
		if ("fixed".equalsIgnoreCase(reqBasicRelationType)) {

	        Assert.assertTrue(reqBasicRelationValue <= reqCtcAmount,
	                "Request Basic fixed amount should not be greater than Request CTC amount"
	        );
	    } else if ("ctc".equalsIgnoreCase(reqBasicRelationType)) {

	        Assert.assertTrue(reqBasicRelationValue <= 100,
	                "Request Basic CTC percentage should not be greater than 100%"
	        );
	    }
		
	    if ("fixed".equalsIgnoreCase(resBasicRelationType)) {

	        Assert.assertTrue(resBasicRelationValue <= resCtcAmount,
	                "Response Basic fixed amount should not be greater than Response CTC amount"
	        );
	    } else if ("ctc".equalsIgnoreCase(resBasicRelationType)) {

	        Assert.assertTrue(resBasicRelationValue <= 100,
	                "Response Basic CTC percentage should not be greater than 100%"
	        );
	    }
	    
	    Assert.assertEquals(reqBasicRelationValue, resBasicRelationValue, "request and response basic relation value missmatch");
	}
	
	@Test(priority=14, dependsOnMethods="create_salarytemplate")
	public void validBasicAmount() {
		
		Float reqBasicAmount = salaryTemplate_Payload.getBasic().getAmount();
		Float resBasicAmount = salaryTemplateResponse.jsonPath().getFloat("basic.amount");
		
		Assert.assertNotNull(resBasicAmount, "response basic amount sould not be null");
		
		Assert.assertEquals(reqBasicAmount, resBasicAmount, "basic amount missmatch");
	}

}
