package com.onsite.salarytemplate;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryTemplate_Api;
import com.onsite.utilities_page.BaseToken;
import com.onsite.utilities_page.CompanyContext;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class List_SalaryTemplate extends BaseToken{
	
	private Response ListSalaryTemplateResponse;
	
	@Test(priority=1)
	public void listSalaryTemplate() {
		
		String companyId = CompanyContext.getCompanyId();
		
		ListSalaryTemplateResponse = RestAssured
				.given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + BaseToken.token)
				.contentType(ContentType.JSON)
				.queryParam("company_id", companyId)
				.log().all()
				
				.when()
				.get(SalaryTemplate_Api.LIST_SALARYTEMPLATE)
				
				.then()
				.log().all()
				.extract().response();		
	}
	
	@Test(priority=2)
	public void validStatusCode() {
		
		int resStatusCode = ListSalaryTemplateResponse.getStatusCode();
		
		Assert.assertEquals(resStatusCode, 200, "list/salary-template api failed so status code is" + resStatusCode);
	}
	
	@Test(priority=3)
	public void validMessage() {
		
		String resMessage = ListSalaryTemplateResponse.jsonPath().getString("message");
		
		if(resMessage != null && !resMessage.isEmpty()) {
			System.out.println("Response message :" + resMessage);
		} else {
			System.out.println("validation message null or empty in response");
		}
	}
	
	@Test(priority=4)
	public void validResponseTime() {

		long responseTime = ListSalaryTemplateResponse.getTime();

		Assert.assertTrue(responseTime <= 2000, "edit/salary-template api response time too much long" + responseTime);
	}
	
	@Test(priority=4)
	public void validSalaryTemplateData() {
		
		Assert.assertEquals(ListSalaryTemplateResponse.getStatusCode(), 200, "List Salary Template API status code mismatch");
		
		List<Map<String, Object>>  SalaryTemplateList = ListSalaryTemplateResponse.jsonPath().getList("data");
		
		Assert.assertNotNull(SalaryTemplateList, "Salary Template list should not be null");
		
		String expectedCompanyId = CompanyContext.getCompanyId();
		
		for(int i=0; i<SalaryTemplateList.size(); i++) {
			
			Map<String, Object> salaryTemplate = SalaryTemplateList.get(i);
			
			Assert.assertNotNull(salaryTemplate, "Salary Template object is null at index: " + i);
			System.out.println("------------Salary template index --------------: " + i);
			
			//Salary template id
			String salaryTemplateId = (String) salaryTemplate.get("id");
			Assert.assertNotNull(salaryTemplateId + ": Salary template id is null at index :" + i);
			Assert.assertFalse(salaryTemplateId.isBlank(), ": salary template id empty at index :" + i);
			System.out.println("Salary Template ID: " + salaryTemplateId);
			
			//Salary template Creator ID
			String salaryTemplateCreatorId = (String) salaryTemplate.get("creator");
			Assert.assertNotNull(salaryTemplateCreatorId + ": Salary template creator id is null at index :" + i);
			Assert.assertFalse(salaryTemplateCreatorId.isBlank(), ": salary template creator id empty at index :" + i);
			System.out.println("Salary Template creator ID: " + salaryTemplateCreatorId);
			
			//Creator company user id
			String salaryTemplateCreatorCompanyUserId = (String) salaryTemplate.get("creator_company_user_id");
			Assert.assertNotNull(salaryTemplateCreatorCompanyUserId + ": Salary template creator company user id is null at index :" + i);
			Assert.assertFalse(salaryTemplateCreatorCompanyUserId.isBlank(), ": salary template creator company user id empty at index :" + i);
			System.out.println("Salary Template creator company user ID: " + salaryTemplateCreatorCompanyUserId);
			
			//company id
			String salaryTemplateCompanyId = (String) salaryTemplate.get("company_id");
			Assert.assertNotNull(salaryTemplateCompanyId + ": Salary template company id is null at index :" + i);
			Assert.assertFalse(salaryTemplateCompanyId.isBlank(), ": salary template company id empty at index :" + i);
			Assert.assertEquals(salaryTemplateCompanyId, expectedCompanyId, "salary template company id missmatch");
			System.out.println("Salary Template company ID: " + salaryTemplateCompanyId);
			
			
			//Salary template Name
			String salaryTemplateName = (String) salaryTemplate.get("name");
			Assert.assertNotNull(salaryTemplateName + ": Salary template name is null at index :" + i);
			Assert.assertFalse(salaryTemplateName.isBlank(), ": salary template name is empty at index :" + i);
			System.out.println("Salary Template name : " + salaryTemplateName);
			
			
			//Salary template Ctc Amount
			Integer salaryTemplateCtcAmount = (Integer) salaryTemplate.get("ctc_amount");
			Assert.assertNotNull(salaryTemplateCtcAmount + ": Salary template ctc amount is null at index :" + i);
			Assert.assertTrue(salaryTemplateCtcAmount >=0, "CTC Amount should not be negative at index: " + i);
			System.out.println("Salary template CTC Amount: " + salaryTemplateCtcAmount);
			
			//Salary template Gross Amount
			Integer salaryTemplateGrossAmount = (Integer) salaryTemplate.get("gross_amount");
			Assert.assertNotNull(salaryTemplateGrossAmount + ": Salary template gross amount is null at index :" + i);
			Assert.assertTrue(salaryTemplateGrossAmount >=0, "Gross Amount should not be negative at index: " + i);
			System.out.println("Salary template Gross Amount: " + salaryTemplateGrossAmount);
			
			//Salary template Net Amount
			Integer salaryTemplateNetAmount = (Integer) salaryTemplate.get("net_amount");
			Assert.assertNotNull(salaryTemplateNetAmount + ": Salary template Net amount is null at index :" + i);
			Assert.assertTrue(salaryTemplateNetAmount >=0, "Net Amount should not be negative at index: " + i);
			System.out.println("Salary template Net Amount: " + salaryTemplateNetAmount);
			
			//Salary template DayOff
			List<String> salaryTemplateDayoffList = (List<String>) salaryTemplate.get("dayoff");
			if(salaryTemplateDayoffList == null && salaryTemplateDayoffList.isEmpty()) {
				System.out.println("Salary template Dayoff list empty or null");
			} else {
				System.out.println("Salary template DayOff :" + salaryTemplateDayoffList);
			}
			
			//Salary template Type
			String salaryTemplateType = (String) salaryTemplate.get("type");
			Assert.assertNotNull(salaryTemplateType + ": Salary template type is null at index :" + i);
			Assert.assertFalse(salaryTemplateType.isBlank(), "Salary template type is empty at index :" + i);
			System.out.println("Salary template type: " + salaryTemplateType);
			
			//Salary template Basic
			Map<String, Object> salaryTemplateBasic = (Map<String, Object>) salaryTemplate.get("basic");
			
			//Salary template Basic.id
			String salaryTemplate_BasicId = (String) salaryTemplateBasic.get("id");
			if(salaryTemplate_BasicId == null || salaryTemplate_BasicId.isEmpty()) {
				System.out.println("Salary template basic id is null or empty:" + salaryTemplate_BasicId);
			} else {
				System.out.println("Salary template basic id : " + salaryTemplate_BasicId);
			}

			//Salary template Basic.name
			String salaryTemplate_BasicName = (String) salaryTemplateBasic.get("name");
			Assert.assertNotNull(salaryTemplate_BasicName + ": Salary template Basic name is null at index :" + i);
			Assert.assertFalse(salaryTemplate_BasicName.isBlank(), "Salary template basic name is empty at index :" + i);
			System.out.println("Salary template basic name : " + salaryTemplate_BasicName);
			
			//Salary template Basic.relation_type
			String salaryTemplate_relationType = (String) salaryTemplateBasic.get("relation_type");
			Assert.assertNotNull(salaryTemplate_relationType + ": Salary template Basic relationType is null at index :" + i);
			Assert.assertFalse(salaryTemplate_relationType.isBlank(), "Salary template basic relationType is empty at index :" + i);
			System.out.println("Salary template basic relationType : " + salaryTemplate_relationType);
			
			//Salary template Basic.relation_value
			Integer salaryTemplate_relationValue = (Integer) salaryTemplateBasic.get("relation_value");
			Assert.assertNotNull(salaryTemplate_relationValue + ": Salary template Basic relationValue is null at index :" + i);
			Assert.assertTrue(salaryTemplate_relationValue >= 0, "salary template basic realtion value should not be negative at  index :" + i);
			System.out.println("Salary template basic relationValue : " + salaryTemplate_relationValue);
			
			//Salary template Basic.amount
			Integer salaryTemplate_basicAmount = (Integer) salaryTemplateBasic.get("amount");
			Assert.assertNotNull(salaryTemplate_basicAmount + ": Salary template Basic amount is null at index :" + i);
			Assert.assertTrue(salaryTemplate_basicAmount >=0, "Basic Amount should not be negative at index :" + i);
			System.out.println("Salary template basic amount : " + salaryTemplate_basicAmount);
			
			//Salary template Basic.include_in
			List<String> salaryTemplate_basicIncludeIn = (List<String>) salaryTemplateBasic.get("include_in");
			if(salaryTemplate_basicIncludeIn == null && salaryTemplate_basicIncludeIn.isEmpty()) {
				System.out.println("salary template basic_include in details null or empty : " + salaryTemplate_basicIncludeIn);
			} else {
				System.out.println("salary template basic include in details is : " + salaryTemplate_basicIncludeIn);
			}
			
			//Salary template Allowance
			List<Map<String, Object>> salaryTemplateAllowance = (List<Map<String, Object>>) salaryTemplate.get("allowances");
			Assert.assertNotNull(salaryTemplateAllowance, "Salary Template Alloawnce object is null : " + i);
			for(int j=0; j<salaryTemplateAllowance.size(); j++) {
				Map<String, Object> salaryTemplateAllowanceIndex = salaryTemplateAllowance.get(j);
				System.out.println("--Salary template Allowance index --: " + j);
				
				//Salary template Allowance id
				String salaryTemplateAllowanceId = (String) salaryTemplateAllowanceIndex.get("id");
				Assert.assertNotNull(salaryTemplateAllowanceId, "salary template allowance id is null at index : " + j);
				Assert.assertFalse(salaryTemplateAllowanceId.isBlank(), "salaey template allowance id is empty at index :" + j);
				System.out.println("salary template allowance id :" + salaryTemplateAllowanceId);
				
				//Salary template Allowance name
				String salaryTemplateAllowanceName = (String) salaryTemplateAllowanceIndex.get("name");
				Assert.assertNotNull(salaryTemplateAllowanceName, "salary template allowance name is null at index : " + j);
				Assert.assertFalse(salaryTemplateAllowanceName.isBlank(), "salaey template allowance name is empty at index :" + j);
				System.out.println("salary template allowance name :" + salaryTemplateAllowanceName);
				
				//Salary template Allowance relation type
				String salaryTemplateAllowanceRelationType = (String) salaryTemplateAllowanceIndex.get("relation_type");
				Assert.assertNotNull(salaryTemplateAllowanceRelationType, "salary template allowance relation type is null at index : " + j);
				Assert.assertFalse(salaryTemplateAllowanceRelationType.isBlank(), "salaey template allowance relation type is empty at index :" + j);
				System.out.println("salary template allowance relation type :" + salaryTemplateAllowanceRelationType);
				
				//Salary template Allowance relation value
				Integer salaryTemplateAllowanceRelationValue = (Integer) salaryTemplateAllowanceIndex.get("relation_value");
				Assert.assertNotNull(salaryTemplateAllowanceRelationValue, "salary template allowance relation value is null at index : " + j);
				Assert.assertTrue(salaryTemplateAllowanceRelationValue >=0, "salaey template allowance relation value is not be negative at index :" + j);
				System.out.println("salary template allowance relation value :" + salaryTemplateAllowanceRelationValue);
				
				//Salary template Allowance relation amount
				Integer salaryTemplateAllowanceRelationAmount = (Integer) salaryTemplateAllowanceIndex.get("amount");
				Assert.assertNotNull(salaryTemplateAllowanceRelationAmount, "salary template allowance relation amount is null at index : " + j);
				Assert.assertTrue(salaryTemplateAllowanceRelationAmount >=0, "salaey template allowance relation amount is not be negative at index :" + j);
				System.out.println("salary template allowance relation amount :" + salaryTemplateAllowanceRelationAmount);
				
				//Salary template Allowance.include_in
				List<String> salaryTemplateAllowance_IncludeIn = (List<String>) salaryTemplateAllowanceIndex.get("include_in");
				if(salaryTemplateAllowance_IncludeIn == null && salaryTemplateAllowance_IncludeIn.isEmpty()) {
					System.out.println("salary template allowance_include in details null or empty : " + salaryTemplateAllowance_IncludeIn);
				} else {
					System.out.println("salary template allowance include in details is : " + salaryTemplateAllowance_IncludeIn);
				}
			}
			
			//Salary Tmeplate Fixed Allowance
			Map<String, Object> SalaryTemplateFixedAllowance =(Map<String, Object>) salaryTemplate.get("fixed_allowance");

			//Salary template fixed allowance.id
			String salaryTemplate_FixedAllowanceId = (String) SalaryTemplateFixedAllowance.get("id");
			if(salaryTemplate_FixedAllowanceId == null || salaryTemplate_FixedAllowanceId.isEmpty()) {
				System.out.println("Salary template fixed allowance id is null or empty:" + salaryTemplate_FixedAllowanceId);
			} else {
				System.out.println("Salary template fixed allowance id : " + salaryTemplate_FixedAllowanceId);
			}

			//Salary template Basic.name
			String salaryTemplate_FixedAllowanceName = (String) SalaryTemplateFixedAllowance.get("name");
			Assert.assertNotNull(salaryTemplate_FixedAllowanceName + ": Salary template fixed allowance name is null at index :" + i);
			Assert.assertFalse(salaryTemplate_FixedAllowanceName.isBlank(), "Salary template fixed allowance name is empty at index :" + i);
			System.out.println("Salary template fixed allowance name : " + salaryTemplate_FixedAllowanceName);
			
			//Salary template Basic.relation_type
			String salaryTemplate_FixedAllowanceRelationType = (String) SalaryTemplateFixedAllowance.get("relation_type");
			Assert.assertNotNull(salaryTemplate_FixedAllowanceRelationType + ": Salary template fixed allowance relationType is null at index :" + i);
			Assert.assertFalse(salaryTemplate_FixedAllowanceRelationType.isBlank(), "Salary template fixed allowance relationType is empty at index :" + i);
			System.out.println("Salary template fixed alloawnce relationType : " + salaryTemplate_FixedAllowanceRelationType);
			
			//Salary template Basic.relation_value
			Integer salaryTemplate_FixedAllowanceRelationValue = (Integer) SalaryTemplateFixedAllowance.get("relation_value");
			Assert.assertNotNull(salaryTemplate_FixedAllowanceRelationValue + ": Salary template fixed allowance relationValue is null at index :" + i);
			Assert.assertTrue(salaryTemplate_FixedAllowanceRelationValue >= 0, "salary template fixed allowance realtion value should not be negative at  index :" + i);
			System.out.println("Salary template fixed allowance relationValue : " + salaryTemplate_FixedAllowanceRelationValue);
			
			//Salary template Basic.amount
			Integer salaryTemplate_FixedAllowanceAmount = (Integer) SalaryTemplateFixedAllowance.get("amount");
			Assert.assertNotNull(salaryTemplate_FixedAllowanceAmount + ": Salary template fixed allowance amount is null at index :" + i);
			Assert.assertTrue(salaryTemplate_FixedAllowanceAmount >=0, "salary template fixed allowance Amount should not be negative at index :" + i);
			System.out.println("Salary template fixed alloawance amount : " + salaryTemplate_FixedAllowanceAmount);
			
			//Salary template Basic.include_in
			List<String> salaryTemplate_FixedAllowanceIncludeIn = (List<String>) SalaryTemplateFixedAllowance.get("include_in");
			if(salaryTemplate_FixedAllowanceIncludeIn == null && salaryTemplate_FixedAllowanceIncludeIn.isEmpty()) {
				System.out.println("salary template fixed allowance_include in details null or empty : " + salaryTemplate_basicIncludeIn);
			} else {
				System.out.println("salary template fixed allowance include in details is : " + salaryTemplate_FixedAllowanceIncludeIn);
			}
			
			//Salary template Deduction
			List<Map<String, Object>> salaryTemplateDeduction = (List<Map<String, Object>>) salaryTemplate.get("deductions");
			Assert.assertNotNull(salaryTemplateDeduction, "Salary Template Deduction object is null : " + i);
			for(int j=0; j<salaryTemplateDeduction.size(); j++) {
				Map<String, Object> salaryTemplateDeductionIndex = salaryTemplateDeduction.get(j);
				System.out.println("--Salary template Deduction index --: " + j);
				
				//Salary template Allowance id
				String salaryTemplate_DeductionId = (String) salaryTemplateDeductionIndex.get("id");
				Assert.assertNotNull(salaryTemplate_DeductionId, "salary template deduction id is null at index : " + j);
				Assert.assertFalse(salaryTemplate_DeductionId.isBlank(), "salaey template deduction id is empty at index :" + j);
				System.out.println("salary template deduction id :" + salaryTemplate_DeductionId);
				
				//Salary template Allowance name
				String salaryTemplate_DeductionName = (String) salaryTemplateDeductionIndex.get("name");
				Assert.assertNotNull(salaryTemplate_DeductionName, "salary template deduction name is null at index : " + j);
				Assert.assertFalse(salaryTemplate_DeductionName.isBlank(), "salaey template deduction name is empty at index :" + j);
				System.out.println("salary template deduction name :" + salaryTemplate_DeductionName);
				
				//Salary template Allowance relation type
				String salaryTemplate_DeductionRelationType = (String) salaryTemplateDeductionIndex.get("relation_type");
				Assert.assertNotNull(salaryTemplate_DeductionRelationType, "salary template deduction relation type is null at index : " + j);
				Assert.assertFalse(salaryTemplate_DeductionRelationType.isBlank(), "salaey template deduction relation type is empty at index :" + j);
				System.out.println("salary template deduction relation type :" + salaryTemplate_DeductionRelationType);
				
				//Salary template Allowance relation value
				Integer salaryTemplate_DeductionRelationValue = (Integer) salaryTemplateDeductionIndex.get("relation_value");
				Assert.assertNotNull(salaryTemplate_DeductionRelationValue, "salary template deduction relation value is null at index : " + j);
				Assert.assertTrue(salaryTemplate_DeductionRelationValue >=0, "salaey template deduction relation value is not be negative at index :" + j);
				System.out.println("salary template deduction relation value :" + salaryTemplate_DeductionRelationValue);
				
				//Salary template Allowance relation amount
				Integer salaryTemplate_DeductionRelationAmount = (Integer) salaryTemplateDeductionIndex.get("amount");
				Assert.assertNotNull(salaryTemplate_DeductionRelationAmount, "salary template deduction relation amount is null at index : " + j);
				Assert.assertTrue(salaryTemplate_DeductionRelationAmount >=0, "salaey template deduction relation amount is not be negative at index :" + j);
				System.out.println("salary template deduction relation amount :" + salaryTemplate_DeductionRelationAmount);
				
				//Salary template Allowance.include_in
				List<String> salaryTemplate_Deduction_IncludeIn = (List<String>) salaryTemplateDeductionIndex.get("include_in");
				if(salaryTemplate_Deduction_IncludeIn == null && salaryTemplate_Deduction_IncludeIn.isEmpty()) {
					System.out.println("salary template deduction_include in details null or empty : " + salaryTemplate_Deduction_IncludeIn);
				} else {
					System.out.println("salary template deduction include in details is : " + salaryTemplate_Deduction_IncludeIn);
				}
			}
			
			//Salary Template Active flag
			Integer salaryTemplateActive = (Integer) salaryTemplate.get("is_active");
			Assert.assertNotNull(salaryTemplateActive, "salary templat active flag is null at index :" + i);
			if(salaryTemplateActive == 1) {
				System.out.println("salary template active flag is 1 :" + salaryTemplateActive);
			} else {
				System.out.println("Salary template active flag is 0 :" + 0);
			}
			
			//Salary Template Notes
			String salaryTemplateNote = (String) salaryTemplate.get("notes");
			if(salaryTemplateNote == null || salaryTemplateNote.isEmpty()) {
				System.out.println("salary template notes is null or empty :" + salaryTemplateNote);
			} else {
				System.out.println("Salary template notes :" + salaryTemplateNote);
			}
			
			//Salary Template Delete flag
			Integer salaryTemplateDelete = (Integer) salaryTemplate.get("delete");
			Assert.assertNotNull(salaryTemplateActive, "salary templat delete flag is null at index :" + i);
			if(salaryTemplateDelete == 1) {
				System.out.println("salary template Delete flag is 1 :" + salaryTemplateDelete);
			} else {
				System.out.println("Salary template Delete flag is 0 :" + 0);
			}
			
			//Salary Template Created Date
			String salaryTemplateCreated = (String) salaryTemplate.get("created");
			if(salaryTemplateCreated == null || salaryTemplateCreated.isEmpty()) {
				System.out.println("salary template created date is null or empty :" + salaryTemplateCreated);
			} else {
				System.out.println("Salary template created date :" + salaryTemplateCreated);
			}
			
			//Salary Template updated Date
			String salaryTemplateUpdatedDate = (String) salaryTemplate.get("updated");
			if(salaryTemplateUpdatedDate == null || salaryTemplateUpdatedDate.isEmpty()) {
				System.out.println("salary template updated date is null or empty :" + salaryTemplateUpdatedDate);
			} else {
				System.out.println("Salary template updated date :" + salaryTemplateUpdatedDate);
			}
			
			//Salary Template PF flag
			Integer salaryTemplatePF = (Integer) salaryTemplate.get("is_pf");
			Assert.assertNotNull(salaryTemplatePF, "salary templat PF flag is null at index :" + i);
			if(salaryTemplatePF == 1) {
				System.out.println("salary template PF flag is 1 :" + salaryTemplatePF);
			} else {
				System.out.println("Salary template PF flag is 0 :" + 0);
			}
			
			//Salary Template ESIC flag
			Integer salaryTemplateESIC = (Integer) salaryTemplate.get("is_esic");
			Assert.assertNotNull(salaryTemplateESIC, "salary templat ESIC flag is null at index :" + i);
			if(salaryTemplateESIC == 1) {
				System.out.println("salary template ESIC flag is 1 :" + salaryTemplateESIC);
			} else {
				System.out.println("Salary template ESIC flag is 0 :" + 0);
			}
			
			//Salary Template PT flag
			Integer salaryTemplatePT = (Integer) salaryTemplate.get("is_pt");
			Assert.assertNotNull(salaryTemplatePT, "salary templat PT flag is null at index :" + i);
			if(salaryTemplatePT == 1) {
				System.out.println("salary template PT flag is 1 :" + salaryTemplatePT);
			} else {
				System.out.println("Salary template PT flag is 0 :" + 0);
			}
			
			//Salary Template LWF flag
			Integer salaryTemplateLWF = (Integer) salaryTemplate.get("is_lwf");
			Assert.assertNotNull(salaryTemplateLWF, "salary templat LWF flag is null at index :" + i);
			if(salaryTemplateLWF == 1) {
				System.out.println("salary template LWF flag is 1 :" + salaryTemplateLWF);
			} else {
				System.out.println("Salary template LWF flag is 0 :" + 0);
			}
			
			//Salary Template Work State
			String salaryTemplateWorkState = (String) salaryTemplate.get("work_state");
			if(salaryTemplateWorkState == null || salaryTemplateWorkState.isEmpty()) {
				System.out.println("salary template work state is null or empty :" + salaryTemplateWorkState);
			} else {
				System.out.println("Salary template work state :" + salaryTemplateWorkState);
			}
		}
	}
}
