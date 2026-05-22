package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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

		if(resPartyCompanyUserId != null && !resPartyCompanyUserId.isEmpty()) {
			if(resPartyCompanyUserId.equals(reqPartyCompanyUserId)) {
				System.out.print("party_company_user_id is :" + resPartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
			} else {
				Assert.fail("party_company_user_id is :" + resPartyCompanyUserId + ": does not match with :" + reqPartyCompanyUserId);
			}
		} else {
			Assert.fail("party_company_user_id is null or empty and :" + resPartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
		}
	}

	@Test(priority=9, dependsOnMethods="createOtherExpense", description="validateRemark")
	public void validateRemark() {

		String resRemark = responseBody.getRemark();
		String reqRemark = requestPayload.getRemark();

		if(resRemark != null && !resRemark.isEmpty()) {
			if(resRemark.equals(reqRemark)) {
				System.out.println("resRemark is : " + resRemark + ": match with :" + reqRemark);
			} else{
				Assert.fail("resRemark is : " + resRemark + ": does not match with :" + reqRemark);
			}
		} else {
			System.out.println("remark is option filed is allowed null or empty");
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

		gstAmount = (((unitPrice*quantity)+otherAmount-discount)*gstPercent)/100;

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
		totalAmount = (withoutGstAmount+gstAmount);

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

		if(resPhotoList != null && reqPhotoList != null) {
			if(resPhotoList.length == reqPhotoList.length) {
				if(Arrays.equals(resPhotoList, reqPhotoList)) {
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

	@Test(priority=21, dependsOnMethods="createOtherExpense", description="validateSubCategoryId")
	public void validateSubCategoryId() {

		String resSubcatgoryId = responseBody.getSub_category_id();
		String reqSubcategoryId = requestPayload.getSub_category_id();

		if(resSubcatgoryId != null && !resSubcatgoryId.isEmpty() && reqSubcategoryId != null && !reqSubcategoryId.isEmpty()) {
			if(resSubcatgoryId.equals(reqSubcategoryId)) {
				System.out.println("resSubcatgoryId is :" + resSubcatgoryId + ": match with reqSubcategoryId :" + reqSubcategoryId);
			} else {
				Assert.fail("resSubcatgoryId is :" + resSubcatgoryId + ": is not match with reqSubcatgoryId :" + reqSubcategoryId);
			}
		} else {
			System.out.println("resPhotoList & reqPhotoList is null or empty");
		}
	}

	@Test(priority=22, dependsOnMethods="createOtherExpense", description="validateCategoryId")
	public void validateCategoryId() {

		String resCatgoryId = responseBody.getCategory_id();
		String reqCategoryId = requestPayload.getCategory_id();

		if(resCatgoryId != null && !resCatgoryId.isEmpty() && reqCategoryId != null && !reqCategoryId.isEmpty()) {
			if(resCatgoryId.equals(reqCategoryId)) {
				System.out.println("resCatgoryId is :" + resCatgoryId + ": match with reqCategoryId :" + reqCategoryId);
			} else {
				Assert.fail("resCatgoryId is :" + resCatgoryId + ": is not match with reqCategoryId :" + reqCategoryId);
			}
		} else {
			System.out.println("resCatgoryI & reqCategoryId is null or empty");
		}
	}

	@Test(priority=23, dependsOnMethods="createOtherExpense", description="validatePaymentDate")
	public void validatePaymentDate() {

		String resPaymentDate = responseBody.getPayment_date();
		String reqPaymentDate = requestPayload.getPayment_date();

		if(resPaymentDate != null && !resPaymentDate.isEmpty() && reqPaymentDate != null && !reqPaymentDate.isEmpty()) {
			if(resPaymentDate.equals(reqPaymentDate)) {
				System.out.println("resPaymentDate is :" + resPaymentDate + ": match with reqPaymentDate :" + reqPaymentDate);
			} else {
				Assert.fail("resPaymentDate is :" + resPaymentDate + ": is not match with reqPaymentDate :" + reqPaymentDate);
			}
		} else {
			System.out.println("resPaymentDate & reqPaymentDate is null or empty");
		}
	}

	@Test(priority=24, dependsOnMethods="createOtherExpense", description="validateUnitId")
	public void validateUnitId() {

		String resUnitId = responseBody.getUnit_id();
		String reqUnitId = requestPayload.getUnit_id();

		if(resUnitId != null && !resUnitId.isEmpty() && reqUnitId != null && !reqUnitId.isEmpty()) {
			if(resUnitId.equals(reqUnitId)) {
				System.out.println("resUnitId is :" + resUnitId + ": match with reqUnitId :" + reqUnitId);
			} else {
				Assert.fail("resUnitId is :" + resUnitId + ": is not match with reqUnitId :" + reqUnitId);
			}
		} else {
			Assert.fail("resUnitId & reqUnitId is null or empty");
		}
	}

	@Test(priority=25, dependsOnMethods="createOtherExpense", description="validateEarningType")
	public void validateEarningType() {

		String resEarningType = responseBody.getEarning_type();
		String reqEarningType = requestPayload.getEarning_type();

		if(resEarningType != null && !resEarningType.isEmpty() && reqEarningType != null && !reqEarningType.isEmpty()) {
			if(resEarningType.equals(reqEarningType)) {
				System.out.println("resEarningType is :" + resEarningType + ": match with reqEarningType :" + reqEarningType);
			} else {
				Assert.fail("resEarningType is :" + resEarningType + ": is not match with reqEarningType :" + reqEarningType);
			}
		} else {
			System.out.println("resEarningType & reqEarningType is null or empty");
		}
	}

	@Test(priority=26, dependsOnMethods="createOtherExpense", description="validateDueDays")
	public void validateDueDays() {

		Double resDueDays = responseBody.getDue_days();
		Double reqDueDays = requestPayload.getDue_days();

		if(resDueDays != null && reqDueDays != null) {
			if(resDueDays.equals(reqDueDays)) {
				System.out.println("resDueDays is :" + resDueDays + ": match with reqDueDays :" + reqDueDays);
			} else {
				Assert.fail("resDueDays is :" + resDueDays + ": is not match with reqDueDays :" + reqDueDays);
			}
		} else {
			System.out.println("resDueDays & reqDueDays is null or empty");
		}
	}

	@Test(priority=27, dependsOnMethods="createOtherExpense", description="validateShipToAddressId")
	public void validateShipToAddressId() {

		String resShipToAddressId = responseBody.getShip_to_address_id();
		String reqShipToAddressId = requestPayload.getShip_to_address_id();

		if(resShipToAddressId != null && !resShipToAddressId.isEmpty() && reqShipToAddressId != null && !reqShipToAddressId.isEmpty()) {
			if(resShipToAddressId.equals(reqShipToAddressId)) {
				System.out.println("resShipToAddressId is :" + resShipToAddressId + ": match with reqShipToAddressId :" + reqShipToAddressId);
			} else {
				Assert.fail("resShipToAddressId is :" + resShipToAddressId + ": is not match with reqShipToAddressId :" + reqShipToAddressId);
			}
		} else {
			System.out.println("resShipToAddressId & reqShipToAddressId is null or empty");
		}	
	}

	@Test(priority=28, dependsOnMethods="createOtherExpense", description="validateBillToAddressId")
	public void validateBillToAddressId() {

		String resBillToAddressId = responseBody.getBill_to_address_id();
		String reqBillToAddressId = requestPayload.getBill_to_address_id();

		if(resBillToAddressId != null && !resBillToAddressId.isEmpty() && reqBillToAddressId != null && !reqBillToAddressId.isEmpty()) {
			if(resBillToAddressId.equals(reqBillToAddressId)) {
				System.out.println("resBillToAddressId is :" + resBillToAddressId + ": match with reqBillToAddressId :" + reqBillToAddressId);
			} else {
				Assert.fail("resBillToAddressId is :" + resBillToAddressId + ": is not match with reqBillToAddressId :" + reqBillToAddressId);
			}
		} else {
			System.out.println("resBillToAddressId & reqBillToAddressId is null or empty");
		}
	}

	@Test(priority=29, dependsOnMethods="createOtherExpense", description="validateShipFromAddressId")
	public void validateShipFromAddressId() {

		String resShipFromAddressId = responseBody.getShip_from_address_id();
		String reqShipFromAddressId = requestPayload.getShip_from_address_id();

		if(resShipFromAddressId != null && !resShipFromAddressId.isEmpty() && reqShipFromAddressId != null && !reqShipFromAddressId.isEmpty()) {
			if(resShipFromAddressId.equals(reqShipFromAddressId)) {
				System.out.println("resShipFromAddressId is :" + resShipFromAddressId + ": match with reqShipFromAddressId :" + reqShipFromAddressId);
			} else {
				Assert.fail("resShipFromAddressId is :" + resShipFromAddressId + ": is not match with reqShipFromAddressId :" + reqShipFromAddressId);
			}
		} else {
			System.out.println("resShipFromAddressId & reqShipFromAddressId is null or empty");
		}
	}

	@Test(priority=30, dependsOnMethods="createOtherExpense", description="validateBillFromAddressId")
	public void validateBillFromAddressId() {

		String resBillFromAddressId = responseBody.getBill_from_address_id();
		String reqBillFromAddressId = requestPayload.getBill_from_address_id();

		if(resBillFromAddressId != null && !resBillFromAddressId.isEmpty() && reqBillFromAddressId != null && !reqBillFromAddressId.isEmpty()) {
			if(resBillFromAddressId.equals(reqBillFromAddressId)) {
				System.out.println("resBillFromAddressId is :" + resBillFromAddressId + ": match with reqBillFromAddressId :" + reqBillFromAddressId);
			} else {
				Assert.fail("resBillFromAddressId is :" + resBillFromAddressId + ": is not match with reqBillFromAddressId :" + reqBillFromAddressId);
			}
		} else {
			System.out.println("resBillFromAddressId & reqBillFromAddressId is null or empty");
		}
	}

	@Test(priority=31, dependsOnMethods="createOtherExpense", description="validateIsGstPercent")
	public void validateIsGstPercent() {

		Integer resIsGstPercent = responseBody.getIs_gst_percent();
		Integer reqIsGstPercent = requestPayload.getIs_gst_percent();

		if(reqIsGstPercent != null) {
			if(reqIsGstPercent == 0 || reqIsGstPercent == 1) {
				if(resIsGstPercent != null && resIsGstPercent.equals(reqIsGstPercent)) {
					System.out.println("resIsGstPercent is : " + resIsGstPercent + " : match with reqIsGstPercent : " + reqIsGstPercent);
				} else {
					Assert.fail("resIsGstPercent is : " + resIsGstPercent + " : is not match with reqIsGstPercent : " + reqIsGstPercent);
				}
			} else {
				Assert.fail("Invalid reqIsGstPercent value : " + reqIsGstPercent + " allowed values are only 0 or 1");
			}
		} else {
			System.out.println("is_gst_percent is optional field and not passed in request payload");
		}
	}

	@Test(priority=32, dependsOnMethods="createOtherExpense", description="validateIsRoundOff")
	public void validateIsRoundOff() {

		Integer resIsRoundOff = responseBody.getIs_roundoff();
		Integer reqIsRoundOff = requestPayload.getIs_roundoff();

		if(reqIsRoundOff != null) {
			if(reqIsRoundOff == 0 || reqIsRoundOff == 1) {
				if(resIsRoundOff != null && resIsRoundOff.equals(reqIsRoundOff)) {
					System.out.println("resIsRoundOff is : " + resIsRoundOff + " : match with reqIsRoundOff : " + reqIsRoundOff);
				} else {
					Assert.fail("resIsRoundOff is : " + resIsRoundOff + " : is not match with reqIsRoundOff : " + reqIsRoundOff);
				}
			} else {
				Assert.fail("Invalid reqIsRoundOff value : " + reqIsRoundOff + " allowed values are only 0 or 1");
			}
		} else {
			System.out.println("is_roundoff is optional field and not passed in request payload");
		}
	}

	@Test(priority=33, dependsOnMethods="createOtherExpense", description="validateVendorBillNumber")
	public void validateVendorBillNumber() {

		String resVendorBillNumber = responseBody.getVendor_bill_number();
		String reqVendorBillNumber = requestPayload.getVendor_bill_number();

		if(resVendorBillNumber != null && !resVendorBillNumber.isEmpty() && reqVendorBillNumber != null && !reqVendorBillNumber.isEmpty()) {
			if(resVendorBillNumber.equals(reqVendorBillNumber)) {
				System.out.println("resVendorBillNumber is :" + resVendorBillNumber + ": match with reqVendorBillNumber :" + reqVendorBillNumber);
			} else {
				Assert.fail("resVendorBillNumber is :" + resVendorBillNumber + ": is not match with reqVendorBillNumber :" + reqVendorBillNumber);
			}
		} else {
			System.out.println("resVendorBillNumber & reqVendorBillNumber is null or empty");
		}
	}

	@Test(priority=34, dependsOnMethods="createOtherExpense", description="validateEquipmentStockId")
	public void validateEquipmentStockId() {

		String resEquipmentStockId = responseBody.getEquipment_stock_id();
		String reqEquipmentStockId = requestPayload.getEquipment_stock_id();

		if(resEquipmentStockId != null && !resEquipmentStockId.isEmpty() && reqEquipmentStockId != null && !reqEquipmentStockId.isEmpty()) {
			if(resEquipmentStockId.equals(reqEquipmentStockId)) {
				System.out.println("resEquipmentStockId is :" + resEquipmentStockId + ": match with reqEquipmentStockId :" + reqEquipmentStockId);
			} else {
				Assert.fail("resEquipmentStockId is :" + resEquipmentStockId + ": is not match with reqEquipmentStockId :" + reqEquipmentStockId);
			}
		} else {
			System.out.println("resEquipmentStockId & reqEquipmentStockId is null or empty");
		}
	}

	@Test(priority=35, dependsOnMethods="createOtherExpense", description="validateBillingActivityId")
	public void validateBillingActivityId() {

		String resBillingActivityId = responseBody.getBilling_activity_id();
		String reqBillingActivityId = requestPayload.getBilling_activity_id();

		if(resBillingActivityId != null && !resBillingActivityId.isEmpty() && reqBillingActivityId != null && !reqBillingActivityId.isEmpty()) {
			if(resBillingActivityId.equals(reqBillingActivityId)) {
				System.out.println("resBillingActivityId is :" + resBillingActivityId + ": match with reqBillingActivityId :" + reqBillingActivityId);
			} else {
				Assert.fail("resBillingActivityId is :" + resBillingActivityId + ": is not match with reqBillingActivityId :" + reqBillingActivityId);
			}
		} else {
			System.out.println("resBillingActivityId & reqBillingActivityId is null or empty");
		}
	}

}

