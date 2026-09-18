package com.onsite.salarytemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.pojo_request.Add_SalaryTemplate;
import com.onsite.pojo_request.SalaryTemplateComponant;
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
	private Float reqBasicAmount;
	private Float resBasicAmount;
	private Double reqCtcAmount;
	private Double resCtcAmount;

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

		reqCtcAmount = salaryTemplate_Payload.getCtc_amount();
		resCtcAmount = salaryTemplateResponse.jsonPath().getDouble("ctc_amount");

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
		Integer[] resDayOff = salaryTemplateResponse.jsonPath().getObject("dayoff", Integer[].class);

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

		Assert.assertNotNull(reqBasicRelationValue, "request basic relation value should not be null");
		Assert.assertNotNull(resBasicRelationValue, "response basic relation value should not be null");

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

		Float ExpReqBasicAmount;
		Float expResBasicAmount;

		String reqBasicRelationType = salaryTemplate_Payload.getBasic().getRelation_type();
		Double reqCtcAmount = salaryTemplate_Payload.getCtc_amount();
		Float reqRelationValue = salaryTemplate_Payload.getBasic().getRelation_value();
		reqBasicAmount = salaryTemplate_Payload.getBasic().getAmount();

		String resBasicRelationType = salaryTemplateResponse.jsonPath().getString("basic.relation_type");
		Double resCtcAmount = salaryTemplateResponse.jsonPath().getDouble("ctc_amount");
		Float resRelationValue = salaryTemplateResponse.jsonPath().getFloat("basic.relation_value");
		resBasicAmount = salaryTemplateResponse.jsonPath().getFloat("basic.amount");

		Assert.assertNotNull(reqBasicAmount, "reqponse basic amount should not be null");
		Assert.assertNotNull(resBasicAmount, "response basic amount should not be null");

		List<String> allowedType = Arrays.asList("fixed", "ctc");

		Assert.assertTrue(allowedType.contains(reqBasicRelationType.toLowerCase()),
				"Invalid request basic relation type: " + reqBasicRelationType
				);

		Assert.assertTrue(allowedType.contains(resBasicRelationType.toLowerCase()),
				"Invalid response basic relation type: " + resBasicRelationType
				);

		if("ctc".equalsIgnoreCase(reqBasicRelationType)) {

			ExpReqBasicAmount = (float) (reqCtcAmount*reqRelationValue)/100;

		} else {

			ExpReqBasicAmount = reqRelationValue;
		}

		Assert.assertEquals(ExpReqBasicAmount, reqBasicAmount, "Request basic amount calculation is incorrect");

		if("ctc".equalsIgnoreCase(resBasicRelationType)) {

			expResBasicAmount = (float) (resCtcAmount*resRelationValue)/100;

		} else {

			expResBasicAmount = resRelationValue;
		}

		Assert.assertEquals(expResBasicAmount, resBasicAmount, "Response basic amount calculation is incorrect");

		Assert.assertEquals(reqBasicAmount, resBasicAmount, "basic amount missmatch");
	}

	public void validAllowance() {

		SalaryTemplateComponant[] reqAllowance = salaryTemplate_Payload.getAllowance();
		SalaryTemplateComponant[] resAllowance = salaryTemplateResponse.jsonPath().
				getObject("allowance", SalaryTemplateComponant[].class);
		
		if(reqAllowance == null) {
			reqAllowance = new SalaryTemplateComponant[0];
		}
		
		if(resAllowance == null) {
			resAllowance = new SalaryTemplateComponant[0];
		}

		Assert.assertEquals(reqAllowance.length, resAllowance.length, 
				"request and response allowance count missmatch");

		for(int i=0; i<reqAllowance.length; i++) {

			SalaryTemplateComponant req = reqAllowance[i];
			SalaryTemplateComponant res = resAllowance[i];

			// allowance id
			Assert.assertNotNull(req.getId(), "request allownace id should not be null");
			Assert.assertNotNull(res.getId(), "response allowance id should not be null");

			Assert.assertEquals(req.getId(), res.getId(), "request and response id missmatch");

			// allowance name
			Assert.assertNotNull(req.getName(), "request allowance name should not be null");
			Assert.assertNotNull(res.getName(), "response allowance name should not be null");
			Assert.assertFalse(req.getName().trim().isEmpty(), "request allowance name should not be empty");
			Assert.assertFalse(res.getName().trim().isEmpty(), "response allowance name should not be empty");

			Assert.assertEquals(req.getName(), res.getName(), "requset and response allowance name missmatch");

			// allowance relation type
			List<String> allowedType = Arrays.asList("ctc", "basic", "fixed");
			Assert.assertTrue(allowedType.contains(req.getRelation_type()), "invlaid request allowance relation type :" + req.getRelation_type());
			Assert.assertTrue(allowedType.contains(res.getRelation_type()), "invalid response allowance relation type :" + res.getRelation_type());
			Assert.assertNotNull(req.getRelation_value(), "request relation type should not be null");
			Assert.assertNotNull(res.getRelation_type(), "response relation type should not be null");

			Assert.assertEquals(req.getRelation_type(), res.getRelation_type(), "request and response relation type missmatch");

			// allowance relation value
			Assert.assertNotNull(req.getRelation_value(), "requset allowance relation value should not be null");
			Assert.assertNotNull(res.getRelation_value(), "response allowance relation value should not be null");

			Assert.assertEquals(req.getRelation_value(), res.getRelation_value(), "request and response relation value missmatch");

			// allowance amount
			
			Double expReqAmount;
			Double expResAmount;
			
			if("basic".equalsIgnoreCase(req.getRelation_type())) {

				expReqAmount = ((reqBasicAmount * req.getRelation_value().doubleValue()) / 100);
				
			} else if("ctc".equalsIgnoreCase(req.getRelation_type())) {

				expReqAmount = ((reqCtcAmount * req.getRelation_value().doubleValue()) / 100);
				
			} else {
				
				//fixed
				expReqAmount = req.getRelation_value().doubleValue();
			}

			if("basic".equalsIgnoreCase(res.getRelation_type())) {

				expResAmount = ((resBasicAmount * res.getRelation_value().doubleValue()) / 100);
				
			} else if("ctc".equalsIgnoreCase(res.getRelation_type())) {

				expResAmount = ((resCtcAmount * res.getRelation_value().doubleValue()) / 100);
				
			} else {
				
				//fixed
				expResAmount = res.getRelation_value().doubleValue();
			}

			Assert.assertEquals(expReqAmount, req.getAmount(), "requset and expReqAmount missmatch");
			Assert.assertEquals(expResAmount, res.getAmount(), "response and expResAmount missmatch");
			Assert.assertEquals(req.getAmount(), res.getAmount(), "requset and response allowance amount missmatch");

			// allowance include in
			String[] reqIncludingIn = req.getInclude_in();
			String[] resIncludingIn = res.getInclude_in();

			List<String> allowedIn = Arrays.asList("pf", "esic");

			if(reqIncludingIn != null) {

				for (String include : reqIncludingIn) {

					Assert.assertNotNull(include, "Request include_in value should not be null");

					Assert.assertTrue(allowedIn.contains(include.toLowerCase()), "Invalid request include_in value: " + include);
				}
			}

			if (resIncludingIn != null) {

				for (String include : resIncludingIn) {

					Assert.assertNotNull(include, "Response include_in value should not be null");

					Assert.assertTrue(allowedIn.contains(include.toLowerCase()), "Invalid response include_in value: " + include);
				}
			}

			Assert.assertEquals(reqIncludingIn, resIncludingIn, "Request and Response include_in mismatch");
		}
	}
}
