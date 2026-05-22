package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.OtherExpenses;
import com.onsite.pojo_request.OtherExpensesRequest;
import com.onsite.pojo_response.OtherExpenseResponse;
import com.onsite.utilities_page.AuthUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.File;

import static io.restassured.RestAssured.*;

public class Create_otherExpensesTest {

	private static ObjectMapper mapper;
	private static Response otherExpResponse;
	private static OtherExpensesRequest requestPayload;
	private static OtherExpenseResponse responseBody;

	@BeforeClass
	public void setUp() {

		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}

	@DataProvider(name="testData")
	public Object[][] getData() throws IOException{

		String jsonPayload = new String(Files.readAllBytes(
				Paths.get("src/test/resources/testdata_otherExpenses/add_otherExpenses.json")));

		// REQUEST DESERIALIZATION - JSON → Request POJO
		requestPayload = mapper.readValue(jsonPayload, OtherExpensesRequest.class);

		// REQUEST SERIALIZATION - Request POJO → JSON
		String finalPayload = mapper.writeValueAsString(requestPayload);
		System.out.println("final payload :" + requestPayload);

		return new Object[][] {
			{requestPayload}
		};
	}

	@Test(dataProvider="testData", priority = 1, description="other expense creation")
	public void createOtherExpense(OtherExpensesRequest otherRequestPayload) throws IOException {

		// REQUEST SERIALIZATION - Request POJO → JSON
		String finalPayload = mapper.writeValueAsString(otherRequestPayload);
		System.out.println("final payload :" + otherRequestPayload);

		otherExpResponse =
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.body(finalPayload)
				.log().all()

				.when()
				.post(OtherExpenses.addPartyEarning)

				.then()
				.log().all()
				.extract().response();

		// RESPONSE DESERIALIZATION - Response JSON → Response POJO
		responseBody = otherExpResponse.as(OtherExpenseResponse.class);

		// response store in details_OtherResponse.json file
		mapper.registerModule(new JavaTimeModule());
		mapper.writerWithDefaultPrettyPrinter().writeValue(
				new File("src/test/resources/testdata_otherExpenses/details.otherexpenses.json"), responseBody);
	}

	@Test(priority=2, dependsOnMethods="createOtherExpense", description="validateStatusCode")
	public void validateStatusCode() {

		int rsponseStatusCode = otherExpResponse.getStatusCode();

		if(rsponseStatusCode == 200) {
			System.out.println("response status code is :" + rsponseStatusCode);
		} else {
			Assert.fail("failure status code is :" + rsponseStatusCode);
		}
	}

	@Test(priority=3, dependsOnMethods="createOtherExpense", description="valiadateResponseMessage")
	public void valiadateResponseMessage() {

		String responseMessage = otherExpResponse.jsonPath().getString("message");

		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("responseMessage is :" + responseMessage);
		} else {
			System.out.println("responseMessage is null or empty");
		}
	}

	@Test(priority=4, dependsOnMethods="createOtherExpense", description="validateResponseTime")
	public void validateResponseTime() {

		long responseTime = otherExpResponse.getTime();

		if(responseTime < 2000) {
			System.out.println("responseTime is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
	}

	@Test(priority=5, dependsOnMethods="createOtherExpense", description="valiadetResponseSchema")
	public void valiadetResponseSchema() {

		otherExpResponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				"responseSchema_files/OtherExpensesResponseSchema.json"));
	}

	@Test(priority=6, dependsOnMethods="createOtherExpense", description="id validation")
	public void valiadetId() {

		String otherExpId = responseBody.getId();

		if(otherExpId != null && !otherExpId.isEmpty()) {
			System.out.println("other expense id : " + otherExpId);
		} else {
			Assert.fail("other expenses is is null or empty :" + otherExpId);
		}
	}

	@Test(priority=7, dependsOnMethods="createOtherExpense", description ="projectId validate")
	public void validateProjectId() {

		String resProjectId = responseBody.getProject_id();
		String reqProjectId = requestPayload.getProject_id();

		if(resProjectId != null && !resProjectId.isEmpty() && resProjectId.equals(reqProjectId)) {
			System.out.println("resProjectId is :" + resProjectId + ": match with requestProjectId:" + reqProjectId);
		} else {
			Assert.fail("project id is null or empty and does not match with :" + resProjectId + ": with :" + reqProjectId);
		}
	}

	@Test(priority=8, dependsOnMethods="createOtherExpense", description="validatePartyCompanyUserId")
	public void validatePartyCompanyUserId() {

		String resPartyCompanyUserId = responseBody.getParty_compay_user_id();
		String reqPartyCompanyUserId = requestPayload.getParty_company_user_id();

		if(resPartyCompanyUserId != null && !resPartyCompanyUserId.isEmpty() && resPartyCompanyUserId.equals(reqPartyCompanyUserId)) {
			System.out.print("party_company_user_id is :" + resPartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
		} else {
			Assert.fail("party_company_user_id is null or empty and :" + resPartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
		}
	}

	@Test(priority=9, dependsOnMethods="createOtherExpense", description="validateRemark")
	public void validateRemark() {

		String resRemark = responseBody.getRemark();
		String reqRemark = requestPayload.getRemark();

		if(resRemark != null && !resRemark.isEmpty() && resRemark.equals(reqRemark)) {
			System.out.println("resRemark is : " + resRemark + ": match with :" + reqRemark);
		} else {
			Assert.fail("remark is null or empty and  :" + resRemark + ": does ot match with :" + reqRemark);
		}
	}

	@Test(priority=10, dependsOnMethods="createOtherExpense", description="createOtherExpense")
	public void validateUnitPrice() {

		Double resUnitPrice = responseBody.getUnit_price();
		Double reqUnitPrice = requestPayload.getUnit_price();

		if(resUnitPrice != null && resUnitPrice.equals(reqUnitPrice)) {
			System.out.println("resUnitPrice is : " + resUnitPrice + ": match with :" + reqUnitPrice);
		} else {
			Assert.fail("resUnitPrice is null or empty and  :" + resUnitPrice + ": does ot match with :" + reqUnitPrice);
		}
	}

	@Test(priority=11, dependsOnMethods="createOtherExpense", description="validateQuantity")
	public void validateQuantity() {

		Double resQuantity = responseBody.getQuantity();
		Double reqQuantity = requestPayload.getQuantity();

		if(resQuantity != null && resQuantity.equals(reqQuantity)) {
			System.out.println("resUnitPrice is : " + resQuantity + ": match with :" + reqQuantity);
		} else {
			Assert.fail("resQuantity is null or empty and  :" + resQuantity + ": does ot match with :" + reqQuantity);
		}
	}

	@Test(priority=12, dependsOnMethods="createOtherExpense", description="validateEarningAmount")
	public void validateEarningAmount() {

		Double getUnitPrice = responseBody.getUnit_price();
		Double getquantity = responseBody.getQuantity();

		Double actualEarningAmount = (getUnitPrice*getquantity);

		Double resEarningAmount = responseBody.getEarning_amount();
		Double reqEarningAmount = requestPayload.getEarning_amount();

		if(resEarningAmount != null && resEarningAmount.equals(reqEarningAmount)) {
			if(actualEarningAmount.equals(resEarningAmount)) {
				System.out.println("actualEarningAmount is : " + actualEarningAmount + ": match with :" + resEarningAmount);
			} else {
				Assert.fail("actualEarningAmount is : " + actualEarningAmount + ": not match with :" + resEarningAmount);
			}
		} else {
			Assert.fail("resQuantity is null or empty and  :" + resEarningAmount + ": does ot match with :" + reqEarningAmount);
		}
	}

	@Test(priority=13, dependsOnMethods="createOtherExpense", description="validateOtherAmount")
	public void validateOtherAmount() {

		Double resOtherAmount = responseBody.getOther_amount();
		Double reqOtherAmount = requestPayload.getOther_amount();

		if(resOtherAmount != null && resOtherAmount.equals(reqOtherAmount)) {
			System.out.println("resOtherAmount is : " + resOtherAmount + ": match with :" + reqOtherAmount);
		} else {
			Assert.fail("resQuantity is null or empty and  :" + resOtherAmount + ": does ot match with :" + reqOtherAmount);
		}
	}

	@Test(priority=14, dependsOnMethods="createOtherExpense", description="validateDiscount")
	public void validateDiscount() {

		Double resDiscount = responseBody.getDiscount();
		Double reqDiscount = requestPayload.getDiscount();

		if(resDiscount != null && resDiscount.equals(reqDiscount)) {
			System.out.println("resDiscount is : " + resDiscount + ": match with reqDiscount :" + reqDiscount);
		} else {
			Assert.fail("resDiscount is null or empty and  :" + resDiscount + ": does ot match with reqDiscount :" + reqDiscount);
		}
	}

	@Test(priority=15, dependsOnMethods="createOtherExpense", description="validateGSTpercent")
	public void validateGSTpercent() {

		Double resGSTpercent = responseBody.getGst_percent();
		Double reqGSTpercent = requestPayload.getGst_percent();

		if(resGSTpercent != null && resGSTpercent.equals(reqGSTpercent)) {
			System.out.println("resGSTpercent is : " + resGSTpercent + ": match with reqGSTpercent :" + reqGSTpercent);
		} else {
			Assert.fail("resGSTpercent is null or empty and  :" + resGSTpercent + ": does ot match with reqGSTpercent :" + reqGSTpercent);
		}
	}

	Double gstAmount;
	@Test(priority=16, dependsOnMethods="createOtherExpense", description="validateGSTAmount")
	public void validateGSTAmount() {

		Double unitPrice = responseBody.getUnit_price();
		Double quantity = responseBody.getQuantity();
		Double gstPercent = responseBody.getGst_percent();
		Double otherAmount = responseBody.getOther_amount();
		Double discount = responseBody.getDiscount();

		Double gstAmount = (((unitPrice*quantity)+otherAmount-discount)*gstPercent)/100;

		Double resGstAmount = responseBody.getGst_amount();
		Double reqGstAmount = requestPayload.getGst_amount();

		if(resGstAmount != null && reqGstAmount != null && resGstAmount.equals(reqGstAmount)) {
			if(gstAmount.equals(resGstAmount)) {
				if(gstAmount.equals(reqGstAmount)) {
					System.out.println("gstAmount is :" + gstAmount + ": match with reqGstAmount :" + reqGstAmount);
				} else {
					Assert.fail("gstAmount is :" + gstAmount + ": is not match with reqGstAmount :" + reqGstAmount);
				}
			} else {
				Assert.fail("gstAmount is :" + gstAmount + ": is not match with resGstAmount :" + resGstAmount);
			} 
		}else {
			Assert.fail("resGstAmount & reqGstAmount is null or empty and " + ": resGstAmount :" + resGstAmount 
					+ ": not match with reqGstAmount :" + reqGstAmount);
		}
	}

	Double totalAmount;
	@Test(priority=17, dependsOnMethods="createOtherExpense", description="validateAmount")
	public void validateAmount() {

		Double unitPrice = responseBody.getUnit_price();
		Double quantity = responseBody.getQuantity();
		Double otherAmount = responseBody.getOther_amount();
		Double discount = responseBody.getDiscount();

		Double withoutGstAmount = ((unitPrice*quantity)+otherAmount-discount);
		Double totalAmount = (withoutGstAmount+gstAmount);

		Double resAmount = responseBody.getAmount();
		Double reqAmount = requestPayload.getAmount();

		if(resAmount !=null && reqAmount != null && resAmount.equals(reqAmount)) {
			if(totalAmount.equals(resAmount)) {
				if(totalAmount.equals(reqAmount)) {
					System.out.println("totalAmount is :" + totalAmount + ": match with reqAmount :" + reqAmount);
				} else {
					Assert.fail("totalAmount :" + totalAmount + ": is not match with reqAmount :" + reqAmount);
				}
				System.out.println("totalAmount is :" + totalAmount + ": match with resAmount :" + resAmount);
			} else {
				Assert.fail("totalAmount :" + totalAmount + ": is not match with resAmount :" + resAmount);
			}
			System.out.println("resAmount & reqAmount is not null or empty and " + ": resAmount :" + resAmount 
					+ ": match with reqAmount :" + reqAmount);
		}else {
			Assert.fail("resAmount & reqAmount is null or empty and " + ": resAmount :" + resAmount 
					+ ": not match with reqAmount :" + reqAmount);
		}
	}

	@Test(priority=18, dependsOnMethods="createOtherExpense", description="validatePostTaxDeductionAmount")
	public void validatePostTaxDeductionAmount() {

		Double resPostTaxDeductionAmount = responseBody.getPost_tax_deduction_amount();
		Double reqPostTaxDeductionAmount = requestPayload.getPost_tax_deduction_amount();

		if(resPostTaxDeductionAmount != null && reqPostTaxDeductionAmount != null) {
			if(resPostTaxDeductionAmount.equals(reqPostTaxDeductionAmount)) {
				System.out.println("resPostTaxDeductionAmount is :" + resPostTaxDeductionAmount + ": match with reqPostTaxDeductionAmount :" + reqPostTaxDeductionAmount);
			} else {
				Assert.fail("resPostTaxDeductionAmount :" + resPostTaxDeductionAmount + ": is not match with reqPostTaxDeductionAmount :" + reqPostTaxDeductionAmount);
			}
		} else {
			Assert.fail("resPostTaxDeductionAmount & reqPostTaxDeductionAmount is null or empty");
		}
	}

	@Test(priority=19, dependsOnMethods="createOtherExpense", description="validateNetAmount")
	public void validateNetAmount() {

		Double amount = responseBody.getAmount();
		Double resPostTaxDeductionAmount = responseBody.getPost_tax_deduction_amount();

		Double netAmount = (amount-resPostTaxDeductionAmount);

		Double resNetAmount = responseBody.getNet_amount();
		Double reqNetAmount = requestPayload.getNet_amount();

		if(resNetAmount != null && reqNetAmount != null && resNetAmount.equals(reqNetAmount)) {
			if(netAmount.equals(resNetAmount)) {
				if(netAmount.equals(reqNetAmount)) {
					System.out.println("netAmount is :" + netAmount + ": match with reqNetAmount :" + reqNetAmount);
				} else {
					Assert.fail("netAmount is :" + netAmount + ": is not match with reqNetAmount :" + reqNetAmount);
				}
				System.out.println("netAmount is :" + netAmount + ": match with resNetAmount :" + resNetAmount);
			} else {
				Assert.fail("netAmount is :" + netAmount + ": is not match with resNetAmount :" + resNetAmount);
			}
		} else {
			Assert.fail("resNetAmount & reqNetAmount is null or empty and " + ": resNetAmount :" + resNetAmount 
					+ ": not match with reqNetAmount :" + reqNetAmount);
		}
	}

	@Test(priority=20, dependsOnMethods="createOtherExpense", description="validatePhoto")
	public void validatePhoto() {

		String[] resPhotoList = responseBody.getPhotos();
		String[] reqPhotoList = requestPayload.getPhotos();

		if(resPhotoList != null && resPhotoList != null) {
			if(resPhotoList.length == resPhotoList.length) {
				if(resPhotoList.equals(reqPhotoList)) {
					System.out.println("resPhotoList is :" + resPhotoList + ": match with reqPhotoList :" + reqPhotoList);
				} else{
					Assert.fail("resPhotoList is :" + resPhotoList + ": is not match with reqPhotoList :" + reqPhotoList);
				}
			}else{
				Assert.fail("resPhotoList is :" + resPhotoList + ": length is not match with reqPhotoList :" + reqPhotoList);
			}
		} else {
			Assert.fail("resPhotoList & reqPhotoList is null or empty");
		}
	}
	
	public void validateSubCategoryId() {
		
		String resSubcatgoryI = responseBody.getSub_category_id();
		String reqSubcategoryId = requestPayload.getSub_category_id();
		
		if(resSubcatgoryI != null && reqSubcategoryId != null) {
			if(resSubcatgoryI.equals(reqSubcategoryId)) {
				System.out.println("resSubcatgoryI is :" + resSubcatgoryI + ": match with reqSubcategoryId :" + reqSubcategoryId);
			} else {
				Assert.fail("resSubcatgoryI is :" + resSubcatgoryI + ": is not match with resSubcatgoryI :" + resSubcatgoryI);
			}
		} else {
			System.out.println("resPhotoList & reqPhotoList is null or empty");
		}
		
	}

}

