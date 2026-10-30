package com.onsite.salarytemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.pojo_request.Add_SalaryTemplate;
import com.onsite.pojo_request.SalaryTemplateComponant;
import com.onsite.pojo_response.SalaryTemplate;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;
import com.onsite.utilities_page.SchemaValidator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Details_SalaryTemplate extends BaseToken{

	private ObjectMapper mapper;
	private Response DetailsSalaryTemplateResponse;
	private Add_SalaryTemplate createSalaryTemplateRequest;
	private Float reqBasicAmount;
	private Float resBasicAmount;
	private Double reqTotalCtcAmount = 0.0;
	private Double resTotalCtcAmount = 0.0;
	private Double reqTotalAllowanceAmount = 0.0;
	private Double resTotalAllowanceAmount = 0.0;
	private Double reqTotalFixedAllowanceAmount = 0.0;
	private Double resTotalFixedAllowanceAmount = 0.0;
	private Double reqTotalGrossAmount = 0.0;
	private Double resTotalGrossAmount = 0.0;
	private Double reqTotalDeductionAmount = 0.0;
	private Double resTotalDeductionAmount = 0.0;

	@DataProvider(name="detailsSalaryTemplateData")
	public Object[][] getData() throws IOException{

		String editSalaryTemplatePath = "src/test/resources/testdata_salaryTemplate/Details.SalaryTemplate.json";
		String detailsPayload = new String(Files.readAllBytes(Paths.get(editSalaryTemplatePath)));

		mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(detailsPayload);
		String salaryTemplateId = jsonNode.get("id").asText();

		System.out.println("salary template id :" + salaryTemplateId);
		SchemaValidator.validateSchemaFromString("requestEditSchema_files/editSalaryTemplate.json", salaryTemplateId);

		String createSalaryTemplatePath = "src/test/resources/testdata_salaryTemplate/Create_salaryTemplate.json";
		String createSalaryTemplatePayload = new String(Files.readAllBytes(Paths.get(createSalaryTemplatePath)));
		createSalaryTemplateRequest = mapper.readValue(createSalaryTemplatePayload, Add_SalaryTemplate.class);

		return new Object[][] {
			{ salaryTemplateId }
		};
	}

	@Test(priority=1, dataProvider="detailsSalaryTemplateData")
	public void detailsSalaryTemplate(String salaryTemplateId) {

		DetailsSalaryTemplateResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.pathParam("id", salaryTemplateId)
				.log().uri()

				.when()
				.get(SalaryTemplate_Api.DETAILS_SALARYTEMPLATE)

				.then()
				.log().all()
				.extract().response();

		SalaryTemplate detailsSalaryTemplate = DetailsSalaryTemplateResponse.as(SalaryTemplate.class);
	}

	@Test(priority=2, dependsOnMethods="detailsSalaryTemplate")
	public void validStatucCode() {

		int resStatusCode = DetailsSalaryTemplateResponse.getStatusCode();

		Assert.assertEquals(resStatusCode, 200, "details/salary-templte api fail so response status code :" + resStatusCode);
	}

	@Test(priority=3, dependsOnMethods="detailsSalaryTemplate")
	public void valudMessage() {

		String resMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");

		if(resMessage != null && !resMessage.isEmpty()) {
			System.out.println("validation message :" + resMessage);
		} else {
			System.out.println("validation message null or empty in response");
		}
	}

	@Test(priority=4, dependsOnMethods="detailsSalaryTemplate")
	public void validResponseTime() {

		long responseTime = DetailsSalaryTemplateResponse.getTime();

		Assert.assertTrue(responseTime <= 2000, "edit/salary-template api response time too much long" + responseTime);
	}

	@Test(priority=5, dependsOnMethods="detailsSalaryTemplate")
	public void validSalaryTemplateId() {

		String resSalaryTemplateId = DetailsSalaryTemplateResponse.jsonPath().getString("id");

		if(resSalaryTemplateId != null || !resSalaryTemplateId.isEmpty()) {

			System.out.println("saary template id :" + resSalaryTemplateId);

		} else {

			Assert.fail("salary template is not generated in api response :" + resSalaryTemplateId);
		}
	}

	@Test(priority=6, dependsOnMethods="detailsSalaryTemplate")
	public void validResponseSchema() throws Exception {

		String responseSchemaPath = "responseSchema_files/SalaryTemplateResponse.json";
		SchemaValidator.validateSchema(responseSchemaPath, DetailsSalaryTemplateResponse.asString());
	}

	@Test(priority=6, dependsOnMethods="detailsSalaryTemplate")
	public void validCompanyId() {

		String getCompanyId = CompanyContext.getCompanyId();
		String resCompanyId = DetailsSalaryTemplateResponse.jsonPath().getString("company_id");
		String reqCompanyId = createSalaryTemplateRequest.getCompany_id();

		if(reqCompanyId == null || reqCompanyId.isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "company_id required");
			String erreMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");
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

	@Test(priority=7, dependsOnMethods="detailsSalaryTemplate")
	public void validSalaryTemplateName() {

		String reqTemplateName= createSalaryTemplateRequest.getName();
		String resTemplateName = DetailsSalaryTemplateResponse.jsonPath().getString("name");

		if(reqTemplateName == null || reqTemplateName.trim().isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "template name required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "template name required", "error message is not match");
			System.out.println("error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resTemplateName, "response template name should not be null");
			Assert.assertFalse(resTemplateName.trim().isEmpty(), "response template name should not be empty");

			Assert.assertEquals(reqTemplateName, resTemplateName, "request name and response template name should not be match");
		}
	}

	@Test(priority=8, dependsOnMethods="validSalaryTemplateName")
	public void validSalaryTemplateType() {

		String reqType = createSalaryTemplateRequest.getType();
		String resType = DetailsSalaryTemplateResponse.jsonPath().getString("type");

		if(reqType == null || reqType.isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "ctc_amount required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("ctc_amount required");
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

	@Test(priority=9, dependsOnMethods="validSalaryTemplateName")
	public void validDayoff() {

		Integer[] reqDayoff = createSalaryTemplateRequest.getDayoff();
		Integer[] resDayOff = DetailsSalaryTemplateResponse.jsonPath().getObject("dayoff", Integer[].class);

		Assert.assertEquals(reqDayoff, resDayOff, "request and response dayoff mismatch");
	}

	@Test(priority=10, dependsOnMethods="validSalaryTemplateName")
	public void validBasicSalaryId() {

		String reqBasicSalaryId = createSalaryTemplateRequest.getBasic().getId();
		String resBasicSalaryId = DetailsSalaryTemplateResponse.jsonPath().getString("basic.id");

		if(reqBasicSalaryId == null || reqBasicSalaryId.trim().isEmpty()) {
			Assert.assertTrue(resBasicSalaryId == null || resBasicSalaryId.trim().isEmpty(), "Response basic salary ID is null/empty");
		} else {
			Assert.assertNotNull(resBasicSalaryId, "response basic salary id should not be null");
			Assert.assertFalse(resBasicSalaryId.trim().isEmpty(), "response basic salary id should not be empty");
			Assert.assertEquals(resBasicSalaryId, reqBasicSalaryId, "Request and response basic salary IDs should match");
		}
	}

//	@Test(priority=11, dependsOnMethods={"validSalaryTemplateName", "validBasicSalaryId"})
//	public void validBasicSalaryIncludeIn() {
//
//		String[] reqBasicSalaryIncludeIn = createSalaryTemplateRequest.getBasic().getInclude_in();
//		String[] resBasicSalaryIncludeIn = DetailsSalaryTemplateResponse.jsonPath()
//		        .getList("basic.include_in", String.class)
//		        .toArray(new String[0]);
//
//		if(reqBasicSalaryIncludeIn == null) {
//			Assert.assertNull(resBasicSalaryIncludeIn, "Response basic salary include_in should be null");
//		} else {
//			
//			 Assert.assertNotNull(resBasicSalaryIncludeIn,
//			            "Response basic salary include_in should not be null");
//
//			Assert.assertNotNull(resBasicSalaryIncludeIn, "Response basic salary include_in should not be null");
//		}
//
//		Assert.assertEquals(reqBasicSalaryIncludeIn, resBasicSalaryIncludeIn, "Request and response basic salary include_in mismatch");
//	}

	@Test(priority=12, dependsOnMethods={"validSalaryTemplateName", "validBasicSalaryId"})
	public void validBasicSalaryName() {

		String reqBasicName = createSalaryTemplateRequest.getBasic().getName();
		String resBasicName = DetailsSalaryTemplateResponse.jsonPath().getString("basic.name");

		if(reqBasicName == null || reqBasicName.isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "basic_name required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("basic_name required");
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

	@Test(priority=13, dependsOnMethods={"validSalaryTemplateName", "validBasicSalaryId"})
	public void validBasicSalaryType() {

		String reqBasicRelationType = createSalaryTemplateRequest.getBasic().getRelation_type();
		String resBasicRelationType = DetailsSalaryTemplateResponse.jsonPath().getString("basic.relation_type");

		if(reqBasicRelationType == null || reqBasicRelationType.isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "basic_relationType required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("basic_relationType required");
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

	@Test(priority=14, dependsOnMethods={"validSalaryTemplateName", "validBasicSalaryId"})
	public void validBasicSalaryValue() {

		String reqBasicSalaryRelationType = createSalaryTemplateRequest.getBasic().getRelation_type();
		Float reqBasicSalaryRelationValue = createSalaryTemplateRequest.getBasic().getRelation_value();
		Double reqCtcAmount = createSalaryTemplateRequest.getCtc_amount();

		String resBasicSalaryRelationType = DetailsSalaryTemplateResponse.jsonPath().getString("basic.relation_type");
		Float resBasicSalaryRelationValue = DetailsSalaryTemplateResponse.jsonPath().getFloat("basic.relation_value");
		Double resCtcAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("ctc_amount");

		Assert.assertNotNull(reqBasicSalaryRelationValue, "request basic relation value should not be null");
		Assert.assertNotNull(resBasicSalaryRelationValue, "response basic relation value should not be null");

		List<String> allowedType = Arrays.asList("fixed", "ctc");

		Assert.assertTrue(allowedType.contains(reqBasicSalaryRelationType), "invalid Request basic relation type :" + reqBasicSalaryRelationType);
		Assert.assertTrue(allowedType.contains(resBasicSalaryRelationType), "invalid response basic relation type" + resBasicSalaryRelationType);

		Assert.assertEquals(reqBasicSalaryRelationType, resBasicSalaryRelationType, "request and response relation tyep missmatch");

		if ("fixed".equalsIgnoreCase(reqBasicSalaryRelationType)) {

			Assert.assertTrue(reqBasicSalaryRelationValue <= reqCtcAmount,
					"Request Basic fixed amount should not be greater than Request CTC amount"
					);
		} else if ("ctc".equalsIgnoreCase(reqBasicSalaryRelationType)) {

			Assert.assertTrue(reqBasicSalaryRelationValue <= 100,
					"Request Basic CTC percentage should not be greater than 100 %"
					);
		}

		if ("fixed".equalsIgnoreCase(resBasicSalaryRelationType)) {

			Assert.assertTrue(resBasicSalaryRelationValue <= resCtcAmount,
					"Response Basic fixed amount should not be greater than Response CTC amount"
					);
		} else if ("ctc".equalsIgnoreCase(resBasicSalaryRelationType)) {

			Assert.assertTrue(resBasicSalaryRelationValue <= 100,
					"Response Basic CTC percentage should not be greater than 100%"
					);
		}

		Assert.assertEquals(reqBasicSalaryRelationValue, resBasicSalaryRelationValue, "request and response basic relation value missmatch");
	}

	@Test(priority=15, dependsOnMethods={"validSalaryTemplateName", "validBasicSalaryId"})
	public void validBasicAmount() {

		Float ExpReqBasicAmount;
		Float expResBasicAmount;

		String reqBasicSalaryRelationType = createSalaryTemplateRequest.getBasic().getRelation_type();
		Double reqCtcAmount = createSalaryTemplateRequest.getCtc_amount();
		Float reqBasicSalaryRelationValue = createSalaryTemplateRequest.getBasic().getRelation_value();
		reqBasicAmount = createSalaryTemplateRequest.getBasic().getAmount();

		String resBasicSalaryRelationType = DetailsSalaryTemplateResponse.jsonPath().getString("basic.relation_type");
		Double resCtcAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("ctc_amount");
		Float resBasicSalaryRelationValue = DetailsSalaryTemplateResponse.jsonPath().getFloat("basic.relation_value");
		resBasicAmount = DetailsSalaryTemplateResponse.jsonPath().getFloat("basic.amount");

		Assert.assertNotNull(reqBasicAmount, "reqponse basic amount should not be null");
		Assert.assertNotNull(resBasicAmount, "response basic amount should not be null");

		List<String> allowedType = Arrays.asList("fixed", "ctc");

		Assert.assertTrue(allowedType.contains(reqBasicSalaryRelationType.toLowerCase()),
				"Invalid request basic relation type: " + reqBasicSalaryRelationType
				);

		Assert.assertTrue(allowedType.contains(resBasicSalaryRelationType.toLowerCase()),
				"Invalid response basic relation type: " + resBasicSalaryRelationType
				);

		if("ctc".equalsIgnoreCase(reqBasicSalaryRelationType)) {

			ExpReqBasicAmount = (float) (reqCtcAmount*reqBasicSalaryRelationValue)/100;

		} else {

			ExpReqBasicAmount = reqBasicSalaryRelationValue;
		}

		Assert.assertEquals(ExpReqBasicAmount, reqBasicAmount, "Request basic amount calculation is incorrect");

		if("ctc".equalsIgnoreCase(resBasicSalaryRelationType)) {

			expResBasicAmount = (float) (resCtcAmount*resBasicSalaryRelationValue)/100;

		} else {

			expResBasicAmount = resBasicSalaryRelationValue;
		}

		Assert.assertEquals(expResBasicAmount, resBasicAmount, "Response basic amount calculation is incorrect");

		Assert.assertEquals(reqBasicAmount, resBasicAmount, "basic amount missmatch");
	}

	@Test(priority = 16, dependsOnMethods = {"validSalaryTemplateName", "validBasicAmount"})
	public void validAllowance() {

		reqTotalAllowanceAmount = 0.0;
		resTotalAllowanceAmount = 0.0;

		Double reqGrossAmount = createSalaryTemplateRequest.getGross_amount();
		Double resGrossAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("gross_amount");

		SalaryTemplateComponant[] reqAllowance = createSalaryTemplateRequest.getAllowances();
		SalaryTemplateComponant[] resAllowance = DetailsSalaryTemplateResponse.jsonPath().
				getObject("allowances", SalaryTemplateComponant[].class);

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
			double expReqAllowanceAmount;
			double expResAllowanceAmount;

			if("basic".equalsIgnoreCase(req.getRelation_type())) {

				expReqAllowanceAmount = ((reqBasicAmount * req.getRelation_value().doubleValue()) / 100);

			} else if("ctc".equalsIgnoreCase(req.getRelation_type())) {

				expReqAllowanceAmount = ((reqGrossAmount * req.getRelation_value().doubleValue()) / 100);

			} else {

				//fixed
				expReqAllowanceAmount = req.getRelation_value().doubleValue();
			}

			if("basic".equalsIgnoreCase(res.getRelation_type())) {

				expResAllowanceAmount = ((resBasicAmount * res.getRelation_value().doubleValue()) / 100);

			} else if("ctc".equalsIgnoreCase(res.getRelation_type())) {

				expResAllowanceAmount = ((resGrossAmount * res.getRelation_value().doubleValue()) / 100);

			} else {

				//fixed
				expResAllowanceAmount = res.getRelation_value().doubleValue();
			}

			Assert.assertEquals(expReqAllowanceAmount, req.getAmount().doubleValue(), 0.01, "requset and expReqAmount missmatch");
			Assert.assertEquals(expResAllowanceAmount, res.getAmount().doubleValue(), 0.01, "response and expResAmount missmatch");
			Assert.assertEquals(req.getAmount(), res.getAmount(), "requset and response allowance amount missmatch");

			// Add each allowance amount to class-level totals
			reqTotalAllowanceAmount += req.getAmount().doubleValue();
			resTotalAllowanceAmount += res.getAmount().doubleValue();

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

	@Test(priority=17, dependsOnMethods = {"validBasicAmount", "validAllowance"})
	public void validFixedAllowanceId() {

		String reqFixedAllowanceId = createSalaryTemplateRequest.getFixed_allowance().getId();
		String resFixedAllowanceId = DetailsSalaryTemplateResponse.jsonPath().getString("fixed_allowance.id");

		if(reqFixedAllowanceId == null || reqFixedAllowanceId.trim().isEmpty()) {

			Assert.assertTrue(resFixedAllowanceId == null || resFixedAllowanceId.trim().isEmpty(), "response fixed allowance id should be null");
		} else {
			Assert.assertNotNull(resFixedAllowanceId, "response fixed allowance id should not be null");
			Assert.assertFalse(resFixedAllowanceId.trim().isEmpty(), "response fixed allowance id should not be empty");
			Assert.assertEquals(reqFixedAllowanceId, resFixedAllowanceId, "request and response fixed allowance id should be null or empty");
		}
	}

	@Test(priority=18 ,dependsOnMethods = "validFixedAllowanceId")
	public void validFixedAllowanceName() {

		String reqFixedAllowanceName = createSalaryTemplateRequest.getFixed_allowance().getName();
		String resFixedAllowanceName = DetailsSalaryTemplateResponse.jsonPath().getString("fixed_allowance.name");

		if(reqFixedAllowanceName == null || reqFixedAllowanceName.trim().isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "fixed_allowance name required");
			String errorMessage= DetailsSalaryTemplateResponse.jsonPath().getString("fixed_allowance required");
			Assert.assertEquals(errorMessage, "fixed_allowance required", "error message missmatch");
			System.out.println("error message : " + errorMessage);
		} else {

			List<String> allowedType = Arrays.asList("fixed");

			Assert.assertTrue(allowedType.contains(reqFixedAllowanceName), "invlaid request basic relation type");
			Assert.assertTrue(allowedType.contains(resFixedAllowanceName), "invalid response basic relation type");
			Assert.assertNotNull(resFixedAllowanceName, "fixed allowance name should not be null");
			Assert.assertFalse(resFixedAllowanceName.trim().isEmpty(), "response fixed allowance should not be empty");

			Assert.assertEquals(reqFixedAllowanceName, resFixedAllowanceName, "request and response fixed allowance type is missmatch");
		}
	}

	@Test(priority=19, dependsOnMethods = "validFixedAllowanceId")
	public void validFixedAllowanceType() {

		String reqFixedAllowanceType = createSalaryTemplateRequest.getFixed_allowance().getRelation_type();
		String resFixedAllowanceType = DetailsSalaryTemplateResponse.jsonPath().getString("fixed_allowance.relation_type");

		if(reqFixedAllowanceType == null || reqFixedAllowanceType.trim().isEmpty()) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "fixed_allowance name required");
			String errorMessage= DetailsSalaryTemplateResponse.jsonPath().getString("fixed_allowance required");
			Assert.assertEquals(errorMessage, "fixed_allowance required", "error message missmatch");
			System.out.println("error message : " + errorMessage);
		} else {

			List<String> allowedType = Arrays.asList("fixed");

			Assert.assertTrue(allowedType.contains(reqFixedAllowanceType), "invlaid request basic relation type");
			Assert.assertTrue(allowedType.contains(resFixedAllowanceType), "invalid response basic relation type");
			Assert.assertNotNull(resFixedAllowanceType, "fixed allowance name should not be null");
			Assert.assertFalse(resFixedAllowanceType.trim().isEmpty(), "response fixed allowance should not be empty");

			Assert.assertEquals(reqFixedAllowanceType, resFixedAllowanceType, "request and response fixed allowance type is missmatch");
		}
	}

	@Test(priority=20 ,dependsOnMethods = "validFixedAllowanceId")
	public void validFixedAllowanceValue() {

		Float reqFixedAllowanceValue = createSalaryTemplateRequest.getFixed_allowance().getRelation_value();
		Float resFixedAllowanceValue = DetailsSalaryTemplateResponse.jsonPath().getFloat("fixed_allowance.relation_value");

		if(reqFixedAllowanceValue == 0) {
			Assert.assertTrue(resFixedAllowanceValue == 0, "respose fixed allowance relation value should not be 0");
		} else {
			Assert.assertNotNull(resFixedAllowanceValue, "response fixed allowance relation value should not be null");
			Assert.assertEquals(reqFixedAllowanceValue, resFixedAllowanceValue, "request and response fixed allowance realtion value should not be null");
		}
	}

	@Test(priority=21, dependsOnMethods = "validFixedAllowanceId")
	public void validFixedAllowanceAmount() {

		Float reqFixedAllowanceAmount = createSalaryTemplateRequest.getFixed_allowance().getAmount();
		Float resFixedAllowanceAmount = DetailsSalaryTemplateResponse.jsonPath().getFloat("fixed_allowance.amount");

		Double reqCtcAmount = createSalaryTemplateRequest.getCtc_amount();
		Double resCtcAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("ctc_amount");
		Float reqBasicAmount = this.reqBasicAmount;
		Float resBasicAmount = this.resBasicAmount;
		Double reqAllowanceAmount = this.reqTotalAllowanceAmount;
		Double resAllowanceAmount = this.resTotalAllowanceAmount;

		Assert.assertNotNull(reqFixedAllowanceAmount, "Request fixed allowance amount should not be null");
		Assert.assertNotNull(resFixedAllowanceAmount, "Response fixed allowance amount should not be null");

		if(reqFixedAllowanceAmount >=0 && resFixedAllowanceAmount >=0) {

			Assert.assertEquals(reqFixedAllowanceAmount.doubleValue(), resFixedAllowanceAmount.doubleValue(), 0.01, "request and response allowance amount mismatch");
		} else {

			Assert.fail("request and response fixed allowance amount should not be negative");
		}

		Double expReqFixedAllowanceAmount = reqCtcAmount-(reqBasicAmount+reqAllowanceAmount);
		Double expResFixedAllowanceAmount = resCtcAmount-(resBasicAmount+resAllowanceAmount);

		if(expReqFixedAllowanceAmount >=0 && expResFixedAllowanceAmount >=0) {

			Assert.assertEquals(expReqFixedAllowanceAmount, reqFixedAllowanceAmount.doubleValue(), 0.01, "Request fixed allowance amount mismatch");
			Assert.assertEquals(expResFixedAllowanceAmount, resFixedAllowanceAmount.doubleValue(), 0.01, "Response fixed allowance amount mismatch");
			Assert.assertEquals(expReqFixedAllowanceAmount.doubleValue(), expResFixedAllowanceAmount.doubleValue(), 0.01, "request and response allowance amount mismatch");
		} else {

			Assert.fail("Expected fixed allowance amount should not be negative");
		}

		reqTotalFixedAllowanceAmount = expReqFixedAllowanceAmount.doubleValue();
		resTotalFixedAllowanceAmount = expResFixedAllowanceAmount.doubleValue();
	}

	@Test(priority=22, dependsOnMethods={"validBasicAmount", "validAllowance", "validFixedAllowanceId"})
	public void validCtcAmount() {

		Double reqCtcAmount = createSalaryTemplateRequest.getCtc_amount();
		Double resCtcAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("ctc_amount");

		Float reqBasicAmount = this.reqBasicAmount;
		Float resBasicAmount = this.resBasicAmount;
		Double reqTotalAllowanceAmount = this.reqTotalAllowanceAmount;
		Double resTotalAllowanceAmount = this.resTotalAllowanceAmount;
		Double reqTotalFixedAllowanceAmount = this.reqTotalFixedAllowanceAmount;
		Double resTotalFixedAllowanceAmount= this.resTotalFixedAllowanceAmount;


		Double expReqCtcAmount = reqBasicAmount + reqTotalAllowanceAmount + reqTotalFixedAllowanceAmount;
		Double expResCtcAmount = resBasicAmount + resTotalAllowanceAmount + resTotalFixedAllowanceAmount;

		if(reqCtcAmount == null) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "ctc_amount required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resCtcAmount, "ctc_amount should not be null");
			Assert.assertEquals(reqCtcAmount, resCtcAmount, "Request and response CTC amount mismatch");

			Assert.assertEquals(expReqCtcAmount, reqCtcAmount, 0.01, "expected and request ctc amount missmatch");
			Assert.assertEquals(expResCtcAmount, resCtcAmount, 0.01, "expected and response ctc amount missmatch");
			Assert.assertEquals(expReqCtcAmount, expResCtcAmount, 0.01, "expected request and response ctc amount missmatch");
		}

		reqTotalCtcAmount = expReqCtcAmount;
		resTotalCtcAmount = expResCtcAmount;
	}

	@Test(priority=23, dependsOnMethods="validCtcAmount")
	public void validGrossAmount() {

		Double reqGrossAmount = createSalaryTemplateRequest.getGross_amount();
		Double resGrossAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("gross_amount");

		Double reqCtcAmount = this.reqTotalCtcAmount;
		Double resCtcAmount = this.resTotalCtcAmount;

		if(reqGrossAmount == null) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "gross_amount required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resGrossAmount, "response gross amount should not be null");

			Assert.assertTrue(reqGrossAmount >0, "request gross amount should not be zero or negative");
			Assert.assertTrue(resGrossAmount >0, "response gross amount should not be zero or negative");
			Assert.assertEquals(reqCtcAmount, resCtcAmount, "request and response ctc amount should match");

			Assert.assertEquals(reqGrossAmount, reqCtcAmount, "request gross amount and ctc amount missmatch");
			Assert.assertEquals(resGrossAmount, resCtcAmount, "response gross amount and response ctc amount missmatch");
			Assert.assertEquals(reqGrossAmount, resGrossAmount, "request and response gross amount should match");
		}

		reqTotalGrossAmount = reqGrossAmount;
		resTotalGrossAmount = resGrossAmount;
	}

	@Test(priority=24, dependsOnMethods="validGrossAmount")
	public void validDeductions() {

		reqTotalDeductionAmount = 0.0;
		resTotalDeductionAmount = 0.0;

		Double reqCtcAmount = this.reqTotalCtcAmount;
		Double resCtcAmount = this.resTotalCtcAmount;
		Double reqGrossAmount = this.reqTotalGrossAmount;
		Double resGrossAmount = this.resTotalGrossAmount;

		SalaryTemplateComponant[] reqDeduction = createSalaryTemplateRequest.getDeductions();
		SalaryTemplateComponant[] resDeduction = DetailsSalaryTemplateResponse.jsonPath().
				getObject("deductions", SalaryTemplateComponant[].class);

		if(reqDeduction == null) {
			reqDeduction = new SalaryTemplateComponant[0];
		}

		if(resDeduction == null) {
			resDeduction = new SalaryTemplateComponant[0];
		}

		Assert.assertEquals(reqDeduction.length, resDeduction.length, 
				"request and response deduction count missmatch");

		for(int i=0; i<reqDeduction.length; i++) {

			SalaryTemplateComponant req = reqDeduction[i];
			SalaryTemplateComponant res = resDeduction[i];

			// allowance id
			Assert.assertNotNull(req.getId(), "request deduction id should not be null");
			Assert.assertNotNull(res.getId(), "response deduction id should not be null");

			Assert.assertEquals(req.getId(), res.getId(), "request and response id missmatch");

			// allowance name
			Assert.assertNotNull(req.getName(), "request deduction name should not be null");
			Assert.assertNotNull(res.getName(), "response deduction name should not be null");
			Assert.assertFalse(req.getName().trim().isEmpty(), "request deduction name should not be empty");
			Assert.assertFalse(res.getName().trim().isEmpty(), "response deduction name should not be empty");

			Assert.assertEquals(req.getName(), res.getName(), "requset and response deduction name missmatch");

			// deduction relation type
			List<String> allowedType = Arrays.asList("ctc", "basic", "fixed", "gross");

			Assert.assertTrue(allowedType.contains(req.getRelation_type()), "invlaid request deduction relation type :" + req.getRelation_type());
			Assert.assertTrue(allowedType.contains(res.getRelation_type()), "invalid response deduction relation type :" + res.getRelation_type());
			Assert.assertNotNull(req.getRelation_value(), "request relation type should not be null");
			Assert.assertNotNull(res.getRelation_type(), "response relation type should not be null");

			Assert.assertEquals(req.getRelation_type(), res.getRelation_type(), "request and response relation type missmatch");

			// deduction relation value
			Assert.assertNotNull(req.getRelation_value(), "requset deduction relation value should not be null");
			Assert.assertNotNull(res.getRelation_value(), "response deduction relation value should not be null");

			Assert.assertEquals(req.getRelation_value(), res.getRelation_value(), "request and response relation value missmatch");

			// deduction amount
			double expReqDeductionAmount;
			double expResDeductionAmount;

			if("basic".equalsIgnoreCase(req.getRelation_type())) {

				expReqDeductionAmount = ((reqBasicAmount * req.getRelation_value().doubleValue()) / 100);

			} else if("ctc".equalsIgnoreCase(req.getRelation_type())) {

				expReqDeductionAmount = ((reqCtcAmount * req.getRelation_value().doubleValue()) / 100);

			} else if("gross".equalsIgnoreCase(req.getRelation_type())) {

				expReqDeductionAmount = ((reqGrossAmount * req.getRelation_value().doubleValue()) / 100);

			} else {

				//fixed
				expReqDeductionAmount = req.getRelation_value().doubleValue();
			}

			if("basic".equalsIgnoreCase(res.getRelation_type())) {

				expResDeductionAmount = ((resBasicAmount * res.getRelation_value().doubleValue()) / 100);

			} else if("ctc".equalsIgnoreCase(res.getRelation_type())) {

				expResDeductionAmount = ((resCtcAmount * res.getRelation_value().doubleValue()) / 100);

			} else if("gross".equalsIgnoreCase(req.getRelation_type())) {

				expResDeductionAmount = ((resGrossAmount * req.getRelation_value().doubleValue()) / 100);

			} else {

				//fixed
				expResDeductionAmount = res.getRelation_value().doubleValue();
			}

			Assert.assertEquals(expReqDeductionAmount, req.getAmount().doubleValue(), 0.01, "requset and expReqAmount missmatch");
			Assert.assertEquals(expResDeductionAmount, res.getAmount().doubleValue(), 0.01, "response and expResAmount missmatch");
			Assert.assertEquals(req.getAmount(), res.getAmount(), "requset and response allowance amount missmatch");

			// Add each deduction amount to class-level totals
			reqTotalDeductionAmount += req.getAmount().doubleValue();
			resTotalDeductionAmount += res.getAmount().doubleValue();
		}
	}

	@Test(priority=25, dependsOnMethods={"validGrossAmount", "validDeductions"})
	public void validNetAmount() {

		Double reqGrossAmount = this.reqTotalGrossAmount;
		Double resGrossAmount = this.resTotalGrossAmount;

		Double reqDeductionAmount = this.reqTotalDeductionAmount;
		Double resDeductionAmount = this.resTotalDeductionAmount;

		Double reqNetAmount = createSalaryTemplateRequest.getNet_amount();
		Double resNetAmount = DetailsSalaryTemplateResponse.jsonPath().getDouble("net_amount");

		if(reqNetAmount == null) {

			Assert.assertEquals(DetailsSalaryTemplateResponse.getStatusCode(), 400, "gross_amount required");
			String errorMessage = DetailsSalaryTemplateResponse.jsonPath().getString("message");
			Assert.assertEquals(errorMessage, "ctc_amount required", "error message missmatch");
			System.out.println("error message :" + errorMessage);
		} else {

			Assert.assertNotNull(resNetAmount, "net amount should not be null");
			Assert.assertEquals(reqNetAmount, resNetAmount, "request and response net amount missmatch");

			Double expReqNetAmount = reqGrossAmount-reqDeductionAmount;
			Double expResNetAmount = resGrossAmount-resDeductionAmount;

			Assert.assertEquals(reqNetAmount, expReqNetAmount, "request net amount and expected net amount missmatch");
			Assert.assertEquals(resNetAmount, expResNetAmount, "response net amount and expected net amount missmatch");
			Assert.assertEquals(expReqNetAmount, expResNetAmount, "expected request and response net amount should be match");
		}		
	}

	@Test(priority=26, dependsOnMethods="detailsSalaryTemplate")
	public void validNotes(){

		String reqNotes = createSalaryTemplateRequest.getNotes();
		String resNotes = DetailsSalaryTemplateResponse.jsonPath().getString("notes");

		if(reqNotes == null || reqNotes.isEmpty()) {

			Assert.assertTrue(resNotes == null || resNotes.isEmpty(),
					"Response notes should be null or empty when request notes is null or empty");

		} else {

			Assert.assertNotNull(resNotes, "Response notes should not be null");
			Assert.assertEquals(reqNotes, resNotes, "Request and response notes mismatch");
		}
	}

	@Test(priority=27, dependsOnMethods="detailsSalaryTemplate")
	public void validIsActive() {

		Integer reqIsActive = createSalaryTemplateRequest.getIs_active();
		Integer resIsActive = DetailsSalaryTemplateResponse.jsonPath().getInt("is_active");

		List<Integer> allowedOption = Arrays.asList(0, 1);

		Assert.assertTrue(allowedOption.contains(reqIsActive), "Request is_active should be either 0 or 1");
		Assert.assertEquals(reqIsActive, Integer.valueOf(1), "Request is_active should be 1 during creation");
		Assert.assertNotNull(resIsActive, "Response is_active should not be null");

		Assert.assertEquals(resIsActive, Integer.valueOf(1), "Response is_active should be 1 during creation");
		Assert.assertEquals(reqIsActive, resIsActive, "Request and response is_active mismatch");
	}

	@Test(priority=28, dependsOnMethods="detailsSalaryTemplate")
	public void validIsEsic() {

		Integer reqIsEsic = createSalaryTemplateRequest.getIs_esic();
		Integer resIsEsic = DetailsSalaryTemplateResponse.jsonPath().getInt("is_esic");

		List<Integer> allowedOption = Arrays.asList(0, 1);

		if(reqIsEsic == null) {

			Assert.assertNull(resIsEsic, "Response is_esic should be null when request is_esic is null");

		} else {

			Assert.assertTrue(allowedOption.contains(resIsEsic), "Request is_esic should be either 0 or 1");
			Assert.assertNotNull(resIsEsic, "Response is_esic should not be null");

			Assert.assertTrue(allowedOption.contains(resIsEsic), "Response is_esic should be either 0 or 1");
			Assert.assertEquals(resIsEsic, reqIsEsic, "Request and response is_esic mismatch");
		}
	}

	@Test(priority=29, dependsOnMethods="detailsSalaryTemplate")
	public void validIsLwf() {

		Integer reqIsLwf = createSalaryTemplateRequest.getIs_lwf();
		Integer resIsLwf = DetailsSalaryTemplateResponse.jsonPath().getInt("is_lwf");

		List<Integer> allowedOption = Arrays.asList(0, 1);

		if(reqIsLwf == null) {

			Assert.assertNull(resIsLwf, "Response is_lwf should be null when request is_esic is null");

		} else {

			Assert.assertTrue(allowedOption.contains(reqIsLwf), "Request is_lwf should be either 0 or 1");
			Assert.assertNotNull(resIsLwf, "Response is_lwf should not be null");

			Assert.assertTrue(allowedOption.contains(resIsLwf), "Response is_lwf should be either 0 or 1");
			Assert.assertEquals(resIsLwf, reqIsLwf, "Request and response is_lwf mismatch");
		}
	}

	@Test(priority=30, dependsOnMethods="detailsSalaryTemplate")
	public void validIsPf() {

		Integer reqIsPf = createSalaryTemplateRequest.getIs_pf();
		Integer resIsPf = DetailsSalaryTemplateResponse.jsonPath().getInt("is_pf");

		List<Integer> allowedOption = Arrays.asList(0, 1);

		if(reqIsPf == null) {

			Assert.assertNull(resIsPf, "Response is_pf should be null when request is_pf is null");

		} else {

			Assert.assertTrue(allowedOption.contains(reqIsPf), "Request is_pf should be either 0 or 1");
			Assert.assertNotNull(resIsPf, "Response is_pf should not be null");

			Assert.assertTrue(allowedOption.contains(resIsPf), "Response is_pf should be either 0 or 1");
			Assert.assertEquals(resIsPf, reqIsPf, "Request and response is_pf mismatch");
		}
	}

	@Test(priority=31, dependsOnMethods="detailsSalaryTemplate")
	public void validIsPt() {

		Integer reqIsPt = createSalaryTemplateRequest.getIs_pt();
		Integer resIsPt = DetailsSalaryTemplateResponse.jsonPath().getInt("is_pt");

		List<Integer> allowedOption = Arrays.asList(0, 1);

		if(reqIsPt == null) {

			Assert.assertNull(resIsPt, "Response is_pt should be null when request is_pt is null");

		} else {

			Assert.assertTrue(allowedOption.contains(reqIsPt), "Request is_pt should be either 0 or 1");
			Assert.assertNotNull(resIsPt, "Response is_pt should not be null");

			Assert.assertTrue(allowedOption.contains(resIsPt), "Response is_pt should be either 0 or 1");
			Assert.assertEquals(resIsPt, reqIsPt, "Request and response is_pt mismatch");
		}
	}

	@Test(priority=32, dependsOnMethods="detailsSalaryTemplate")
	public void validWorkState() {

		String reqWorkState = createSalaryTemplateRequest.getWork_state();
		String resWorkState = DetailsSalaryTemplateResponse.jsonPath().getString("work_state");

		List<String> allowedOption = Arrays.asList("MH", "AP", "AR", "AS", "BR", "CG", "GA", "GJ", "HR", "HP", "JH", 
				"KA", "KL", "MP", "MN", "ML", "OD", "PB", "RJ", "SK", "TN", "TS", "TR", "UP", "UK", "WB");

		if(reqWorkState == null) {

			Assert.assertNull(resWorkState, "Response is_pt should be null when request is_pt is null");

		} else {

			Assert.assertTrue(allowedOption.contains(reqWorkState), "Request is_pt should be either 0 or 1");
			Assert.assertNotNull(resWorkState, "Response is_pt should not be null");

			Assert.assertTrue(allowedOption.contains(resWorkState), "Response is_pt should be either 0 or 1");
			Assert.assertEquals(resWorkState, reqWorkState, "Request and response is_pt mismatch");
		}
	}


}
