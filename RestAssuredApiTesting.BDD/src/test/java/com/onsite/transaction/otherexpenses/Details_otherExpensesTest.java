package com.onsite.transaction.otherexpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.OtherExpenses;
import com.onsite.utilities_page.AuthUtils;

public class Details_otherExpensesTest {

	private Response otherExpDetails;
	private JSONObject objData;
	private JSONObject monkeyPatchInvoice;

	@DataProvider(name="testData")
	public Object[][] getData() throws IOException{

		String getData = new String(Files.readAllBytes(
				Paths.get("src/test/resources/testdata_otherExpenses/details.otherexpenses.json")));

		objData = new JSONObject(getData);
		String otherExpId = objData.getString("id");
		System.out.println("other expenses id :" + otherExpId);
		
		monkeyPatchInvoice = objData.getJSONObject("monkey_patch_invoice");

		return new Object[][] {
			{ otherExpId }
		};
	}

	@Test(dataProvider="testData", priority=1, description="Details Other Expeses")
	public void detailsOtherExpenses(String otherExpenseId) {

		otherExpDetails = 
				given()
				.baseUri(ApiBasePath.BASE_URL)
				.header("Authorization", "Bearer " + AuthUtils.getToken())
				.contentType(ContentType.JSON)
				.pathParam("id", otherExpenseId)
				.log().uri()

				.when()
				.get(OtherExpenses.detailPartyEraning)

				.then()
				.log().all()
				.extract().response();	
	}

	@Test(priority=2, dependsOnMethods="detailsOtherExpenses", description="validate status code")
	public void validateStatusCode() {

		int responseStatusCode = otherExpDetails.getStatusCode();
		if(responseStatusCode == 200) {
			System.out.println("response StatusCode :" + responseStatusCode);
		} else {
			Assert.fail("failure response status code :" + responseStatusCode);
		}
	}

	@Test(priority=3, dependsOnMethods="detailsOtherExpenses", description="validate message")
	public void validateMessage() {

		String responseMessage = otherExpDetails.jsonPath().getString("message");
		
		if(responseMessage != null && !responseMessage.isEmpty()) {
			System.out.println("response message :" + responseMessage);
		} else {
			System.out.println("response message is null or empty");
		}
	}

	@Test(priority=4, dependsOnMethods="detailsOtherExpenses", description="validate Response Time")
	public void validateResponseTime() {

		long responseTime = otherExpDetails.getTime();

		if(responseTime < 2000) {
			System.out.println("responseTime is :" + responseTime);
		} else {
			Assert.fail("response time is too long :" + responseTime);
		}
	}

	@Test(priority=5, dependsOnMethods="detailsOtherExpenses", description="valiadet Response Schema")
	public void valiadetResponseSchema() {

		otherExpDetails.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
				"responseSchema_files/OtherExpensesResponseSchema.json"));
	}

	@Test(priority=6, dependsOnMethods="detailsOtherExpenses", description="id validation")
	public void valiadetId() {

		String resOtherExpId = otherExpDetails.jsonPath().getString("id");
		String reqOtherExpId = objData.getString("id");

		if(resOtherExpId != null && !resOtherExpId.isEmpty()) {
			if(resOtherExpId.equals(reqOtherExpId)) {
				System.out.println("other expense id : " + resOtherExpId + ": is equal with :" + resOtherExpId);
			} else {
				Assert.fail("other expense id : " + resOtherExpId + ": is not equal with :" + resOtherExpId);
			}
		} else {
			Assert.fail("other expenses is is null or empty :" + resOtherExpId);
		}
	}

	@Test(priority=7, dependsOnMethods="detailsOtherExpenses", description ="project Id validate")
	public void validateProjectId() {

		String resDetails_ProjectId = otherExpDetails.jsonPath().getString("project_id");
		String reqProjectId = objData.getString("project_id");

		if(resDetails_ProjectId != null && !resDetails_ProjectId.isEmpty() && resDetails_ProjectId.equals(reqProjectId)) {
			System.out.println("resDetails_ProjectId is :" + resDetails_ProjectId + ": match with requestProjectId:" + reqProjectId);
		} else {
			Assert.fail("project id is null or empty and does not match with :" + resDetails_ProjectId + ": with :" + reqProjectId);
		}
	}

	@Test(priority=8, dependsOnMethods="detailsOtherExpenses", description="validate Party CompanyUser Id")
	public void validatePartyCompanyUserId() {

		String resDetails_PartyCompanyUserId = otherExpDetails.jsonPath().getString("party_company_user_id");
		String reqPartyCompanyUserId = objData.getString("party_company_user_id");

		if(resDetails_PartyCompanyUserId != null && !resDetails_PartyCompanyUserId.isEmpty()) {
			if(resDetails_PartyCompanyUserId.equals(reqPartyCompanyUserId)) {
				System.out.print("party_company_user_id is :" + resDetails_PartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
			} else {
				Assert.fail("party_company_user_id is :" + resDetails_PartyCompanyUserId + ": does not match with :" + reqPartyCompanyUserId);
			}
		} else {
			Assert.fail("party_company_user_id is null or empty and :" + resDetails_PartyCompanyUserId + ": match with :" + reqPartyCompanyUserId);
		}
	}

	@Test(priority=9, dependsOnMethods="detailsOtherExpenses", description="validate Remark")
	public void validateRemark() {

		String resDetails_Remark = otherExpDetails.jsonPath().getString("remark");
		String reqRemark = objData.getString("remark");

		if(resDetails_Remark != null && !resDetails_Remark.isEmpty()) {
			if(resDetails_Remark.equals(reqRemark)) {
				System.out.println("resDetails_Remark is : " + resDetails_Remark + ": match with :" + reqRemark);
			} else{
				Assert.fail("resDetails_Remark is : " + resDetails_Remark + ": does not match with :" + reqRemark);
			}
		} else {
			System.out.println("remark is option filed is allowed null or empty");
		}
	}

	@Test(priority=10, dependsOnMethods="detailsOtherExpenses", description="create Other Expense")
	public void validateUnitPrice() {

		Double resDetails_unitPrice = otherExpDetails.jsonPath().getDouble("unit_price");
		Double reqUnitPrice = objData.getDouble("unit_price");

		if(resDetails_unitPrice != null && resDetails_unitPrice.equals(reqUnitPrice)) {
			System.out.println("resDetails_unitPrice is : " + resDetails_unitPrice + ": match with :" + reqUnitPrice);
		} else {
			Assert.fail("resDetails_unitPrice is null or empty and  :" + resDetails_unitPrice + ": does ot match with :" + reqUnitPrice);
		}
	}

	@Test(priority=11, dependsOnMethods="detailsOtherExpenses", description="validate Quantity")
	public void validateQuantity() {

		Double resDetails_Quantity = otherExpDetails.jsonPath().getDouble("quantity");
		Double reqQuantity = objData.getDouble("quantity");

		if(resDetails_Quantity != null && resDetails_Quantity.equals(reqQuantity)) {
			System.out.println("resDetails_Quantity is : " + resDetails_Quantity + ": match with :" + reqQuantity);
		} else {
			Assert.fail("resDetails_Quantity is null or empty and  :" + resDetails_Quantity + ": does ot match with :" + reqQuantity);
		}
	}

	@Test(priority=12, dependsOnMethods="detailsOtherExpenses", description="validate Earning Amount")
	public void validateEarningAmount() {

		Double getUnitPrice = otherExpDetails.jsonPath().getDouble("unit_price");
		Double getquantity = otherExpDetails.jsonPath().getDouble("quantity");

		Double actualEarningAmount = (getUnitPrice*getquantity);

		Double resDetails_EarningAmount = otherExpDetails.jsonPath().getDouble("earning_amount");
		Double reqEarningAmount = objData.getDouble("earning_amount");

		if(resDetails_EarningAmount != null && resDetails_EarningAmount.equals(reqEarningAmount)) {
			if(actualEarningAmount.equals(resDetails_EarningAmount)) {
				System.out.println("actualEarningAmount is : " + actualEarningAmount + ": match with :" + resDetails_EarningAmount);
			} else {
				Assert.fail("actualEarningAmount is : " + actualEarningAmount + ": not match with :" + resDetails_EarningAmount);
			}
		} else {
			Assert.fail("resQuantity is null or empty and  :" + resDetails_EarningAmount + ": does ot match with :" + reqEarningAmount);
		}
	}

	@Test(priority=13, dependsOnMethods="detailsOtherExpenses", description="validate Other Amount")
	public void validateOtherAmount() {

		Double resDetails_OtherAmount = otherExpDetails.jsonPath().getDouble("other_amount");
		Double reqOtherAmount = objData.getDouble("other_amount");

		if(resDetails_OtherAmount != null && resDetails_OtherAmount.equals(reqOtherAmount)) {
			System.out.println("resDetails_OtherAmount is : " + resDetails_OtherAmount + ": match with :" + reqOtherAmount);
		} else {
			Assert.fail("resDetails_OtherAmount is null or empty and  :" + resDetails_OtherAmount + ": does ot match with :" + reqOtherAmount);
		}
	}

	@Test(priority=14, dependsOnMethods="detailsOtherExpenses", description="validate Discount")
	public void validateDiscount() {

		Double resDetails_Discount = otherExpDetails.jsonPath().getDouble("discount");
		Double reqDiscount = objData.getDouble("discount");

		if(resDetails_Discount != null && resDetails_Discount.equals(reqDiscount)) {
			System.out.println("resDetails_Discount is : " + resDetails_Discount + ": match with reqDiscount :" + reqDiscount);
		} else {
			Assert.fail("resDetails_Discount is null or empty and  :" + resDetails_Discount + ": does ot match with reqDiscount :" + reqDiscount);
		}
	}

	@Test(priority=15, dependsOnMethods="detailsOtherExpenses", description="validate GST percent")
	public void validateGSTpercent() {

		Double resDetails_GstPercent = otherExpDetails.jsonPath().getDouble("gst_percent");
		Double reqGSTpercent = objData.getDouble("gst_percent");

		if(resDetails_GstPercent != null && resDetails_GstPercent.equals(reqGSTpercent)) {
			System.out.println("resDetails_GstPercent is : " + resDetails_GstPercent + ": match with reqGSTpercent :" + reqGSTpercent);
		} else {
			Assert.fail("resDetails_GstPercent is null or empty and  :" + resDetails_GstPercent + ": does ot match with reqGSTpercent :" + reqGSTpercent);
		}
	}

	Double gstAmount;
	@Test(priority=16, dependsOnMethods="detailsOtherExpenses", description="validate GST Amount")
	public void validateGSTAmount() {

		Double unitPrice = otherExpDetails.jsonPath().getDouble("unit_price");
		Double quantity = otherExpDetails.jsonPath().getDouble("quantity");
		Double gstPercent = otherExpDetails.jsonPath().getDouble("gst_percent");
		Double otherAmount = otherExpDetails.jsonPath().getDouble("other_amount");
		Double discount = otherExpDetails.jsonPath().getDouble("discount");

		gstAmount = (((unitPrice*quantity)+otherAmount-discount)*gstPercent)/100;

		Double resDetails_GstAmount = otherExpDetails.jsonPath().getDouble("gst_amount");
		Double reqGstAmount = objData.getDouble("gst_amount");

		if(resDetails_GstAmount != null && reqGstAmount != null && resDetails_GstAmount.equals(reqGstAmount)) {
			if(gstAmount.equals(resDetails_GstAmount)) {
				if(gstAmount.equals(reqGstAmount)) {
					System.out.println("gstAmount is :" + gstAmount + ": match with reqGstAmount :" + reqGstAmount);
				} else {
					Assert.fail("gstAmount is :" + gstAmount + ": is not match with reqGstAmount :" + reqGstAmount);
				}
			} else {
				Assert.fail("gstAmount is :" + gstAmount + ": is not match with resGstAmount :" + resDetails_GstAmount);
			} 
		}else {
			Assert.fail("resGstAmount & reqGstAmount is null or empty and " + ": resGstAmount :" + resDetails_GstAmount 
					+ ": not match with reqGstAmount :" + reqGstAmount);
		}
	}

	Double totalAmount;
	@Test(priority=17, dependsOnMethods="detailsOtherExpenses", description="validate Amount")
	public void validateAmount() {

		Double unitPrice = otherExpDetails.jsonPath().getDouble("unit_price");
		Double quantity = otherExpDetails.jsonPath().getDouble("quantity");
		Double otherAmount = otherExpDetails.jsonPath().getDouble("other_amount");
		Double discount = otherExpDetails.jsonPath().getDouble("discount");

		Double withoutGstAmount = ((unitPrice*quantity)+otherAmount-discount);
		totalAmount = (withoutGstAmount+gstAmount);

		Double resDetails_Amount = otherExpDetails.jsonPath().getDouble("amount");
		Double reqAmount = objData.getDouble("amount");

		if(resDetails_Amount !=null && reqAmount != null && resDetails_Amount.equals(reqAmount)) {
			if(totalAmount.equals(resDetails_Amount)) {
				if(totalAmount.equals(reqAmount)) {
					System.out.println("totalAmount is :" + totalAmount + ": match with reqAmount :" + reqAmount);
				} else {
					Assert.fail("totalAmount :" + totalAmount + ": is not match with reqAmount :" + reqAmount);
				}
				System.out.println("totalAmount is :" + totalAmount + ": match with resAmount :" + resDetails_Amount);
			} else {
				Assert.fail("totalAmount :" + totalAmount + ": is not match with resAmount :" + resDetails_Amount);
			}
			System.out.println("resAmount & reqAmount is not null or empty and " + ": resAmount :" + resDetails_Amount 
					+ ": match with reqAmount :" + reqAmount);
		}else {
			Assert.fail("resAmount & reqAmount is null or empty and " + ": resAmount :" + resDetails_Amount 
					+ ": not match with reqAmount :" + reqAmount);
		}
	}

	@Test(priority=18, dependsOnMethods="detailsOtherExpenses", description="validate Post Tax Deduction Amount")
	public void validatePostTaxDeductionAmount() {

		Double resPostTaxDeductionAmount = otherExpDetails.jsonPath().getDouble("post_tax_deduction_amount");
		Double reqPostTaxDeductionAmount = objData.getDouble("post_tax_deduction_amount");

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

	@Test(priority=19, dependsOnMethods="detailsOtherExpenses", description="validate Net Amount")
	public void validateNetAmount() {

		Double amount = otherExpDetails.jsonPath().getDouble("amount");
		Double resPostTaxDeductionAmount = otherExpDetails.jsonPath().getDouble("post_tax_deduction_amount");

		Double netAmount = (amount-resPostTaxDeductionAmount);

		Double resDetails_NetAmount = otherExpDetails.jsonPath().getDouble("net_amount");
		Double reqNetAmount = objData.getDouble("net_amount");

		if(resDetails_NetAmount != null && reqNetAmount != null && resDetails_NetAmount.equals(reqNetAmount)) {
			if(netAmount.equals(resDetails_NetAmount) || netAmount.equals(amount)) {
				if(netAmount.equals(reqNetAmount) || netAmount.equals(amount)) {
					System.out.println("netAmount is :" + netAmount + ": match with reqNetAmount :" + reqNetAmount);
				} else {
					Assert.fail("netAmount is :" + netAmount + ": is not match with reqNetAmount :" + reqNetAmount);
				}
				System.out.println("netAmount is :" + netAmount + ": match with resNetAmount :" + resDetails_NetAmount);
			} else {
				Assert.fail("netAmount is :" + netAmount + ": is not match with resNetAmount :" + resDetails_NetAmount);
			}
		} else {
			Assert.fail("resNetAmount & reqNetAmount is null or empty and " + ": resNetAmount :" + resDetails_NetAmount 
					+ ": not match with reqNetAmount :" + reqNetAmount);
		}
	}

	@Test(priority=20, dependsOnMethods="detailsOtherExpenses", description="validate Photo")
	public void validatePhoto() {

		List<String> resDetails_PhotoList = otherExpDetails.jsonPath().get("photos");
		
		JSONArray photosArray = objData.getJSONArray("photos");
		List<String> reqlistPhoto = new ArrayList<>();
		
		for(int i=0; i<photosArray.length(); i++) {
			reqlistPhoto.add(photosArray.getString(i));
		}

		if(resDetails_PhotoList != null && reqlistPhoto != null) {
			if(resDetails_PhotoList.size() == reqlistPhoto.size()) {
				Assert.assertEquals(resDetails_PhotoList, reqlistPhoto);
			}else{
				Assert.fail("resDetails_PhotoList is :" + resDetails_PhotoList + ": length is not match with reqlistPhoto :" + reqlistPhoto);
			}
		} else {
			Assert.fail("resPhotoList & reqlistPhoto is null or empty");
		}
	}

	@Test(priority=21, dependsOnMethods="detailsOtherExpenses", description="validateSubCategoryId")
	public void validateSubCategoryId() {

		String resDetails_SubcatgoryId = otherExpDetails.jsonPath().getString("sub_category_id");
		String reqSubcategoryId = objData.getString("sub_category_id");

		if(resDetails_SubcatgoryId != null && !resDetails_SubcatgoryId.isEmpty() && reqSubcategoryId != null && !reqSubcategoryId.isEmpty()) {
			if(resDetails_SubcatgoryId.equals(reqSubcategoryId)) {
				System.out.println("resDetails_SubcatgoryId is :" + resDetails_SubcatgoryId + ": match with reqSubcategoryId :" + reqSubcategoryId);
			} else {
				Assert.fail("resDetails_SubcatgoryId is :" + resDetails_SubcatgoryId + ": is not match with reqSubcatgoryId :" + reqSubcategoryId);
			}
		} else {
			System.out.println("resPhotoList & reqPhotoList is null or empty");
		}
	}

	@Test(priority=22, dependsOnMethods="detailsOtherExpenses", description="validate CategoryId")
	public void validateCategoryId() {

		String resDetails_CatgoryId = otherExpDetails.jsonPath().getString("category_id");
		String reqCategoryId = objData.getString("category_id");

		if(resDetails_CatgoryId != null && !resDetails_CatgoryId.isEmpty() && reqCategoryId != null && !reqCategoryId.isEmpty()) {
			if(resDetails_CatgoryId.equals(reqCategoryId)) {
				System.out.println("resDetails_CatgoryId is :" + resDetails_CatgoryId + ": match with reqCategoryId :" + reqCategoryId);
			} else {
				Assert.fail("resDetails_CatgoryId is :" + resDetails_CatgoryId + ": is not match with reqCategoryId :" + reqCategoryId);
			}
		} else {
			System.out.println("resDetails_CatgoryId & reqCategoryId is null or empty");
		}
	}

	@Test(priority=23, dependsOnMethods="detailsOtherExpenses", description="validate PaymentDate")
	public void validatePaymentDate() {

		String resDetails_PaymentDate = otherExpDetails.jsonPath().getString("payment_date");
		String reqPaymentDate = objData.getString("payment_date");

		if(resDetails_PaymentDate != null && !resDetails_PaymentDate.isEmpty() && reqPaymentDate != null && !reqPaymentDate.isEmpty()) {
			if(resDetails_PaymentDate.equals(reqPaymentDate)) {
				System.out.println("resDetails_PaymentDate is :" + resDetails_PaymentDate + ": match with reqPaymentDate :" + reqPaymentDate);
			} else {
				Assert.fail("resDetails_PaymentDate is :" + resDetails_PaymentDate + ": is not match with reqPaymentDate :" + reqPaymentDate);
			}
		} else {
			System.out.println("resDetails_PaymentDate & reqPaymentDate is null or empty");
		}
	}

	@Test(priority=24, dependsOnMethods="detailsOtherExpenses", description="validate UnitId")
	public void validateUnitId() {

		String resDetails_UnitId = otherExpDetails.jsonPath().getString("unit_id");
		String reqUnitId = objData.getString("unit_id");

		if(resDetails_UnitId != null && !resDetails_UnitId.isEmpty() && reqUnitId != null && !reqUnitId.isEmpty()) {
			if(resDetails_UnitId.equals(reqUnitId)) {
				System.out.println("resDetails_UnitId is :" + resDetails_UnitId + ": match with reqUnitId :" + reqUnitId);
			} else {
				Assert.fail("resDetails_UnitId is :" + resDetails_UnitId + ": is not match with reqUnitId :" + reqUnitId);
			}
		} else {
			Assert.fail("resDetails_UnitId & reqUnitId is null or empty");
		}
	}

	@Test(priority=25, dependsOnMethods="detailsOtherExpenses", description="validate EarningType")
	public void validateEarningType() {

		String resDetails_EarningType = otherExpDetails.jsonPath().getString("earning_type");
		String reqEarningType = objData.getString("earning_type");

		if(resDetails_EarningType != null && !resDetails_EarningType.isEmpty() && reqEarningType != null && !reqEarningType.isEmpty()) {
			if(resDetails_EarningType.equals(reqEarningType)) {
				System.out.println("resDetails_EarningType is :" + resDetails_EarningType + ": match with reqEarningType :" + reqEarningType);
			} else {
				Assert.fail("resDetails_EarningType is :" + resDetails_EarningType + ": is not match with reqEarningType :" + reqEarningType);
			}
		} else {
			System.out.println("resDetails_EarningType & reqEarningType is null or empty");
		}
	}

	@Test(priority=26, dependsOnMethods="detailsOtherExpenses", description="validate DueDays")
	public void validateDueDays() {

		Double resDetails_DueDays = otherExpDetails.jsonPath().getDouble("due_days");
		Double reqDueDays = objData.getDouble("due_days");

		if(resDetails_DueDays != null && reqDueDays != null) {
			if(resDetails_DueDays.equals(reqDueDays)) {
				System.out.println("resDetails_DueDays is :" + resDetails_DueDays + ": match with reqDueDays :" + reqDueDays);
			} else {
				Assert.fail("resDetails_DueDays is :" + resDetails_DueDays + ": is not match with reqDueDays :" + reqDueDays);
			}
		} else {
			System.out.println("resDetails_DueDays & reqDueDays is null or empty");
		}
	}

	@Test(priority=27, dependsOnMethods="detailsOtherExpenses", description="validate ShipToAddressId")
	public void validateShipToAddressId() {

		String resDetails_ShipToAddressId = otherExpDetails.jsonPath().getString("ship_to_address_id");
		String reqShipToAddressId = objData.getString("ship_to_address_id");

		if(resDetails_ShipToAddressId != null && !resDetails_ShipToAddressId.isEmpty() && reqShipToAddressId != null && !reqShipToAddressId.isEmpty()) {
			if(resDetails_ShipToAddressId.equals(reqShipToAddressId)) {
				System.out.println("resDetails_ShipToAddressId is :" + resDetails_ShipToAddressId + ": match with reqShipToAddressId :" + reqShipToAddressId);
			} else {
				Assert.fail("resDetails_ShipToAddressId is :" + resDetails_ShipToAddressId + ": is not match with reqShipToAddressId :" + reqShipToAddressId);
			}
		} else {
			System.out.println("resDetails_ShipToAddressId & reqShipToAddressId is null or empty");
		}	
	}

	@Test(priority=28, dependsOnMethods="detailsOtherExpenses", description="validate BillToAddressId")
	public void validateBillToAddressId() {

		String resDetails_BillToAddressId = otherExpDetails.jsonPath().getString("bill_to_address_id");
		String reqBillToAddressId = objData.getString("bill_to_address_id");

		if(resDetails_BillToAddressId != null && !resDetails_BillToAddressId.isEmpty() && reqBillToAddressId != null && !reqBillToAddressId.isEmpty()) {
			if(resDetails_BillToAddressId.equals(reqBillToAddressId)) {
				System.out.println("resDetails_BillToAddressId is :" + resDetails_BillToAddressId + ": match with reqBillToAddressId :" + reqBillToAddressId);
			} else {
				Assert.fail("resDetails_BillToAddressId is :" + resDetails_BillToAddressId + ": is not match with reqBillToAddressId :" + reqBillToAddressId);
			}
		} else {
			System.out.println("resDetails_BillToAddressId & reqBillToAddressId is null or empty");
		}
	}

	@Test(priority=29, dependsOnMethods="detailsOtherExpenses", description="validate ShipFromAddressId")
	public void validateShipFromAddressId() {

		String resDetails_ShipFromAddressId = otherExpDetails.jsonPath().getString("ship_from_address_id");
		String reqShipFromAddressId = objData.getString("ship_from_address_id");

		if(resDetails_ShipFromAddressId != null && !resDetails_ShipFromAddressId.isEmpty() && reqShipFromAddressId != null && !reqShipFromAddressId.isEmpty()) {
			if(resDetails_ShipFromAddressId.equals(reqShipFromAddressId)) {
				System.out.println("resDetails_ShipFromAddressId is :" + resDetails_ShipFromAddressId + ": match with reqShipFromAddressId :" + reqShipFromAddressId);
			} else {
				Assert.fail("resDetails_ShipFromAddressId is :" + resDetails_ShipFromAddressId + ": is not match with reqShipFromAddressId :" + reqShipFromAddressId);
			}
		} else {
			System.out.println("resDetails_ShipFromAddressId & reqShipFromAddressId is null or empty");
		}
	}

	@Test(priority=30, dependsOnMethods="detailsOtherExpenses", description="validate BillFromAddressId")
	public void validateBillFromAddressId() {

		String resDetails_BillFromAddressId = otherExpDetails.jsonPath().getString("bill_from_address_id");
		String reqBillFromAddressId = objData.getString("bill_from_address_id");

		if(resDetails_BillFromAddressId != null && !resDetails_BillFromAddressId.isEmpty() && reqBillFromAddressId != null && !reqBillFromAddressId.isEmpty()) {
			if(resDetails_BillFromAddressId.equals(reqBillFromAddressId)) {
				System.out.println("resDetails_BillFromAddressId is :" + resDetails_BillFromAddressId + ": match with reqBillFromAddressId :" + reqBillFromAddressId);
			} else {
				Assert.fail("resDetails_BillFromAddressId is :" + resDetails_BillFromAddressId + ": is not match with reqBillFromAddressId :" + reqBillFromAddressId);
			}
		} else {
			System.out.println("resDetails_BillFromAddressId & reqBillFromAddressId is null or empty");
		}
	}

	@Test(priority=31, dependsOnMethods="detailsOtherExpenses", description="validate IsGstPercent")
	public void validateIsGstPercent() {

		Integer resDetails_IsGstPercent = otherExpDetails.jsonPath().getInt("is_gst_percent");
		Integer reqIsGstPercent = objData.getInt("is_gst_percent");

		if(reqIsGstPercent != null) {
			if(reqIsGstPercent == 0 || reqIsGstPercent == 1) {
				if(resDetails_IsGstPercent != null && resDetails_IsGstPercent.equals(reqIsGstPercent)) {
					System.out.println("resDetails_IsGstPercent is : " + resDetails_IsGstPercent + " : match with reqIsGstPercent : " + reqIsGstPercent);
				} else {
					Assert.fail("resDetails_IsGstPercent is : " + resDetails_IsGstPercent + " : is not match with reqIsGstPercent : " + reqIsGstPercent);
				}
			} else {
				Assert.fail("Invalid reqIsGstPercent value : " + reqIsGstPercent + " allowed values are only 0 or 1");
			}
		} 
	}

	@Test(priority=32, dependsOnMethods="detailsOtherExpenses", description="validate IsRoundOff")
	public void validateIsRoundOff() {

		Integer resDetails_IsRoundOff = otherExpDetails.jsonPath().getInt("is_roundoff");
		Integer reqIsRoundOff = objData.getInt("is_roundoff");

		if(reqIsRoundOff != null) {
			if(reqIsRoundOff == 0 || reqIsRoundOff == 1) {
				if(resDetails_IsRoundOff != null && resDetails_IsRoundOff.equals(reqIsRoundOff)) {
					System.out.println("resDetails_IsRoundOff is : " + resDetails_IsRoundOff + " : match with reqIsRoundOff : " + reqIsRoundOff);
				} else {
					Assert.fail("resDetails_IsRoundOff is : " + resDetails_IsRoundOff + " : is not match with reqIsRoundOff : " + reqIsRoundOff);
				}
			} else {
				Assert.fail("Invalid reqIsRoundOff value : " + reqIsRoundOff + " allowed values are only 0 or 1");
			}
		}
	}

	@Test(priority=33, dependsOnMethods="detailsOtherExpenses", description="validate VendorBillNumber")
	public void validateVendorBillNumber() {

		String resDetails_VendorBillNumber = otherExpDetails.jsonPath().getString("vendor_bill_number");
		String reqVendorBillNumber = objData.getString("vendor_bill_number");

		if(resDetails_VendorBillNumber != null && !resDetails_VendorBillNumber.isEmpty() && reqVendorBillNumber != null && !reqVendorBillNumber.isEmpty()) {
			if(resDetails_VendorBillNumber.equals(reqVendorBillNumber)) {
				System.out.println("resDetails_VendorBillNumber is :" + resDetails_VendorBillNumber + ": match with reqVendorBillNumber :" + reqVendorBillNumber);
			} else {
				Assert.fail("resDetails_VendorBillNumber is :" + resDetails_VendorBillNumber + ": is not match with reqVendorBillNumber :" + reqVendorBillNumber);
			}
		} else {
			System.out.println("resDetails_VendorBillNumber & reqVendorBillNumber is null or empty");
		}
	}

	@Test(priority=34, dependsOnMethods="detailsOtherExpenses", description="validate EquipmentStockId")
	public void validateEquipmentStockId() {

		String resDetails_EquipmentStockId = otherExpDetails.jsonPath().getString("equipment_stock_id");
		String reqEquipmentStockId = objData.getString("equipment_stock_id");

		if(resDetails_EquipmentStockId != null && !resDetails_EquipmentStockId.isEmpty() && reqEquipmentStockId != null && !reqEquipmentStockId.isEmpty()) {
			if(resDetails_EquipmentStockId.equals(reqEquipmentStockId)) {
				System.out.println("resDetails_EquipmentStockId is :" + resDetails_EquipmentStockId + ": match with reqEquipmentStockId :" + reqEquipmentStockId);
			} else {
				Assert.fail("resDetails_EquipmentStockId is :" + resDetails_EquipmentStockId + ": is not match with reqEquipmentStockId :" + reqEquipmentStockId);
			}
		} else {
			System.out.println("resDetails_EquipmentStockId & reqEquipmentStockId is null or empty");
		}
	}

	@Test(priority=35, dependsOnMethods="detailsOtherExpenses", description="validate BillingActivityId")
	public void validateBillingActivityId() {

		String resDetails_BillingActivityId = otherExpDetails.jsonPath().getString("billing_activity_id");
		String reqBillingActivityId =  objData.getString("billing_activity_id");

		if(resDetails_BillingActivityId != null && !resDetails_BillingActivityId.isEmpty() && reqBillingActivityId != null && !reqBillingActivityId.isEmpty()) {
			if(resDetails_BillingActivityId.equals(reqBillingActivityId)) {
				System.out.println("resDetails_BillingActivityId is :" + resDetails_BillingActivityId + ": match with reqBillingActivityId :" + reqBillingActivityId);
			} else {
				Assert.fail("resDetails_BillingActivityId is :" + resDetails_BillingActivityId + ": is not match with reqBillingActivityId :" + reqBillingActivityId);
			}
		} else {
			System.out.println("resDetails_BillingActivityId & reqBillingActivityId is null or empty");
		}
	}

	@Test(priority=36, dependsOnMethods="detailsOtherExpenses", description="validate MonkeyPatchInvoice Id")
	public void validateMonkeyPatchInvoiceId() {

		String resDetails_MonkeyPatchId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.invoice_id");
		String resInvoiceId = monkeyPatchInvoice.getString("id");

		if(resDetails_MonkeyPatchId != null && !resDetails_MonkeyPatchId.isEmpty() && resInvoiceId != null && !resInvoiceId.isEmpty()) {
			if(resDetails_MonkeyPatchId.equals(resInvoiceId)) {
				System.out.println("resInvoiceId is :" + resInvoiceId + ": match with resDetails_MonkeyPatchId :" + resDetails_MonkeyPatchId);
			} else {
				Assert.fail("resInvoiceId is :" + resInvoiceId + ": is not match with resDetails_MonkeyPatchId :" + resDetails_MonkeyPatchId);
			}
		} else {
			System.out.println("resInvoiceId & resDetails_MonkeyPatchId is null or empty");
		}
	}

	@Test(priority=37, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoice CompanyId")
	public void validateMonkeyPatchInvoiceCompanyId() {

		String resDetails_MonkeyPatchInvoiceCompanyId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.company_id");
		String resCompanyId = monkeyPatchInvoice.getString("company_id");

		if(resDetails_MonkeyPatchInvoiceCompanyId != null && !resDetails_MonkeyPatchInvoiceCompanyId.isEmpty() && resCompanyId != null && !resCompanyId.isEmpty()) {
			if(resDetails_MonkeyPatchInvoiceCompanyId.equals(resCompanyId)) {
				System.out.println("resDetails_MonkeyPatchInvoiceCompanyId is :" + resDetails_MonkeyPatchInvoiceCompanyId + ": match with resCompanyId :" + resCompanyId);
			} else {
				Assert.fail("resDetails_MonkeyPatchInvoiceCompanyId is :" + resDetails_MonkeyPatchInvoiceCompanyId + ": is not match with resCompanyId :" + resCompanyId);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceCompanyId & resCompanyId is null or empty");
		}
	}

	@Test(priority=38, dependsOnMethods="detailsOtherExpenses", description="validate MonkeyPatchInvoice CreatorCompanyUserId")
	public void validateMonkeyPatchInvoiceCreatorCompanyUserId() {

		String resMonkeyPatchInvoiceCreatorCompanyUserId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.creator_company_user_id");
		String resCreatorCompanyUserId = monkeyPatchInvoice.getString("creator_company_user_id");

		if(resMonkeyPatchInvoiceCreatorCompanyUserId != null && !resMonkeyPatchInvoiceCreatorCompanyUserId.isEmpty() && resCreatorCompanyUserId != null && !resCreatorCompanyUserId.isEmpty()) {
			if(resMonkeyPatchInvoiceCreatorCompanyUserId.equals(resCreatorCompanyUserId)) {
				System.out.println("resMonkeyPatchInvoiceCreatorCompanyUserId is :" + resMonkeyPatchInvoiceCreatorCompanyUserId + ": match with resCreatorCompanyUserId :" + resCreatorCompanyUserId);
			} else {
				Assert.fail("resMonkeyPatchInvoiceCreatorCompanyUserId is :" + resMonkeyPatchInvoiceCreatorCompanyUserId + ": is not match with resCreatorCompanyUserId :" + resCreatorCompanyUserId);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceCreatorCompanyUserId & resCreatorCompanyUserId is null or empty");
		}
	}

	@Test(priority=39, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoice PartyCompanyUserId")
	public void validateMonkeyPatchInvoicePartyCompanyUserId() {

		String resMonkeyPatchInvoicePartyCompanyUserId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.party_company_user_id");
		String resPartyCompanyUserId = monkeyPatchInvoice.getString("party_company_user_id");

		if(resMonkeyPatchInvoicePartyCompanyUserId != null && !resMonkeyPatchInvoicePartyCompanyUserId.isEmpty() && resPartyCompanyUserId != null && !resPartyCompanyUserId.isEmpty()) {
			if(resMonkeyPatchInvoicePartyCompanyUserId.equals(resPartyCompanyUserId)) {
				System.out.println("resMonkeyPatchInvoicePartyCompanyUserId is :" + resPartyCompanyUserId + ": match with resPartyCompanyUserId :" + resPartyCompanyUserId);
			} else {
				Assert.fail("resMonkeyPatchInvoicePartyCompanyUserId is :" + resMonkeyPatchInvoicePartyCompanyUserId + ": is not match with resPartyCompanyUserId :" + resPartyCompanyUserId);
			}
		} else {
			System.out.println("resMonkeyPatchInvoicePartyCompanyUserId & resPartyCompanyUserId is null or empty");
		}
	}

	@Test(priority=40, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceProjectId")
	public void validateMonkeyPatchInvoiceProjectId() {

		String resMonkeyPatchInvoiceProjectId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.project_id");
		String resProjectId = monkeyPatchInvoice.getString("project_id");

		if(resMonkeyPatchInvoiceProjectId != null && !resMonkeyPatchInvoiceProjectId.isEmpty() && resProjectId != null && !resProjectId.isEmpty()) {
			if(resMonkeyPatchInvoiceProjectId.equals(resProjectId)) {
				System.out.println("resMonkeyPatchInvoiceProjectId is :" + resMonkeyPatchInvoiceProjectId + ": match with resProjectId :" + resProjectId);
			} else {
				Assert.fail("resMonkeyPatchInvoiceProjectId is :" + resMonkeyPatchInvoiceProjectId + ": is not match with resProjectId :" + resProjectId);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceProjectId & resProjectId is null or empty");
		}
	}

	@Test(priority=41, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceSubCategoryId")
	public void validateMonkeyPatchInvoiceSubCategoryId() {

		String resMonkeyPatchInvoiceSubCategoryId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.sub_category_id");
		String resSubCategoryId = monkeyPatchInvoice.getString("sub_category_id");

		if(resMonkeyPatchInvoiceSubCategoryId != null && !resMonkeyPatchInvoiceSubCategoryId.isEmpty() && resSubCategoryId != null && !resSubCategoryId.isEmpty()) {
			if(resMonkeyPatchInvoiceSubCategoryId.equals(resSubCategoryId)) {
				System.out.println("resMonkeyPatchInvoiceSubCategoryId is :" + resMonkeyPatchInvoiceSubCategoryId + ": match with resSubCategoryId :" + resSubCategoryId);
			} else {
				Assert.fail("resMonkeyPatchInvoiceSubCategoryId is :" + resMonkeyPatchInvoiceSubCategoryId + ": is not match with resSubCategoryId :" + resSubCategoryId);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceSubCategoryId & resSubCategoryId is null or empty");
		}
	}

	@Test(priority=42, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceFaturesType")
	public void validateMonkeyPatchInvoiceFaturesType() {

		String resMonkeyPatchInvoiceFeatureType = otherExpDetails.jsonPath().getString("monkey_patch_invoice.feature_type");
		String resFeatureType = monkeyPatchInvoice.getString("feature_type");

		if(resMonkeyPatchInvoiceFeatureType != null && !resMonkeyPatchInvoiceFeatureType.isEmpty()) {
			Assert.assertEquals(resMonkeyPatchInvoiceFeatureType, "partyearning", "Invalid feature_type");
			if(resMonkeyPatchInvoiceFeatureType.equals(resFeatureType)) {
				System.out.println("resMonkeyPatchInvoiceFeatureType is :" + resMonkeyPatchInvoiceFeatureType + ": match with resFeatureType :" + resFeatureType);
			} else {
				Assert.fail("resMonkeyPatchInvoiceFeatureType is :" + resMonkeyPatchInvoiceFeatureType + ": is not match with resFeatureType :" + resFeatureType);
			}
		} else {
			System.out.println("validateMonkeyPatchInvoiceFaturesType is null or empty");
		}
	}

	@Test(priority=43, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceFeatureId")
	public void validateMonkeyPatchInvoiceFeatureId() {

		String resMonkeyPatchInvoiceFeatureId = otherExpDetails.jsonPath().getString("monkey_patch_invoice.feature_id");
		String reqFeatureId = monkeyPatchInvoice.getString("feature_id");

		if(resMonkeyPatchInvoiceFeatureId != null && !resMonkeyPatchInvoiceFeatureId.isEmpty() && reqFeatureId != null && !reqFeatureId.isEmpty()) {
			System.out.println("resMonkeyPatchInvoiceFeatureId does not null or empty : " + resMonkeyPatchInvoiceFeatureId);
			if(resMonkeyPatchInvoiceFeatureId.equals(reqFeatureId)) {
				System.out.println("resMonkeyPatchInvoiceFeatureId is :" + resMonkeyPatchInvoiceFeatureId + ": match with resFeatureId :" + reqFeatureId);
			} else {
				Assert.fail("resMonkeyPatchInvoiceFeatureId is :" + resMonkeyPatchInvoiceFeatureId + ": is not match with resFeatureId :" + reqFeatureId);
			}
		} else {
			Assert.fail("resMonkeyPatchInvoiceFeatureId is null or empty  : " + resMonkeyPatchInvoiceFeatureId);
		}
	}

	@Test(priority=44, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceInvoiceType")
	public void validateMonkeyPatchInvoiceInvoiceType() {

		String resMonkeyPatchInvoiceInvoiceType = otherExpDetails.jsonPath().getString("monkey_patch_invoice.invoice_type");
		String reqInvoiceType = monkeyPatchInvoice.getString("invoice_type");

		if(resMonkeyPatchInvoiceInvoiceType != null && !resMonkeyPatchInvoiceInvoiceType.isEmpty() && reqInvoiceType != null && !reqInvoiceType.isEmpty()) {
			Assert.assertEquals(resMonkeyPatchInvoiceInvoiceType, "expense", "Invalid feature_type");
			if(resMonkeyPatchInvoiceInvoiceType.equals(reqInvoiceType)) {
				System.out.println("resMonkeyPatchInvoiceInvoiceType is :" + resMonkeyPatchInvoiceInvoiceType + ": match with resInvoiceType :" + reqInvoiceType);
			} else {
				Assert.fail("resMonkeyPatchInvoiceInvoiceType is :" + resMonkeyPatchInvoiceInvoiceType + ": is not match with resInvoiceType :" + reqInvoiceType);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceInvoiceType is null or empty");
		}
	}

	@Test(priority=45, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceStatus")
	public void validateMonkeyPatchInvoiceStatus() {

		String resMonkeyPatchInvoiceStatus = otherExpDetails.jsonPath().getString("monkey_patch_invoice.status");
		String reqInvoiceStatus = monkeyPatchInvoice.getString("status");
		
		if(resMonkeyPatchInvoiceStatus != null && !resMonkeyPatchInvoiceStatus.isEmpty()) {
			if(resMonkeyPatchInvoiceStatus.equals(reqInvoiceStatus)) {
				System.out.println("resMonkeyPatchInvoiceStatus is :" + resMonkeyPatchInvoiceStatus + ": match with reqInvoiceStatus :" + reqInvoiceStatus);
			} else {
				Assert.fail("resMonkeyPatchInvoiceStatus is :" + resMonkeyPatchInvoiceStatus + ": is not match with reqInvoiceStatus :" + reqInvoiceStatus);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceStatus is null or empty");
		}
	}

	@Test(priority=46, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceTotalPayable")
	public void validateMonkeyPatchInvoiceTotalPayable() {

		Double resMonkeyPatchInvoiceTotalPayable = otherExpDetails.jsonPath().getDouble("monkey_patch_invoice.total_payable");
		Double resTotalPayable = monkeyPatchInvoice.getDouble("total_payable");

		if(resMonkeyPatchInvoiceTotalPayable != null && resTotalPayable != null) {
			if(resMonkeyPatchInvoiceTotalPayable.equals(resTotalPayable)) {
				System.out.println("resMonkeyPatchInvoiceTotalPayable is :" + resMonkeyPatchInvoiceTotalPayable + ": match with resTotalPayable :" + resTotalPayable);
			} else {
				Assert.fail("resMonkeyPatchInvoiceTotalPayable is :" + resMonkeyPatchInvoiceTotalPayable + ": is not match with resTotalPayable :" + resTotalPayable);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceTotalPayable & resTotalPayable is null or empty");
		}
	}

	@Test(priority=47, dependsOnMethods="detailsOtherExpenses", description="resMonkeyPatchInvoiceSequence")
	public void validateMonkeyPatchInvoiceSequence() {

		Double resMonkeyPatchInvoiceSequence = otherExpDetails.jsonPath().getDouble("monkey_patch_invoice.sequence");
		Double reqInvoiceSequence = monkeyPatchInvoice.getDouble("sequence");
		
		if(resMonkeyPatchInvoiceSequence != null && reqInvoiceSequence != null) {
			System.out.println("monkey patch sequnce is :" + resMonkeyPatchInvoiceSequence);
			if(resMonkeyPatchInvoiceSequence.equals(reqInvoiceSequence)) {
				System.out.println("resMonkeyPatchInvoiceSequence is :" + resMonkeyPatchInvoiceSequence + ": match with reqInvoiceSequence :" + reqInvoiceSequence);
			} else {
				Assert.fail("resMonkeyPatchInvoiceSequence is :" + resMonkeyPatchInvoiceSequence + ": is not match with reqInvoiceSequence :" + reqInvoiceSequence);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceSequence is null or empty");
		}
	}

	@Test(priority=48, dependsOnMethods="detailsOtherExpenses", description="validateMonkeyPatchInvoiceApproval")
	public void validateMonkeyPatchInvoiceApproval() {

		String resMonkeyPatchInvoiceApproval = otherExpDetails.jsonPath().getString("monkey_patch_invoice.approval_flag");
		String reqInvoiceApprovalFlag = monkeyPatchInvoice.getString("approval_flag");
		
		if(resMonkeyPatchInvoiceApproval != null && !resMonkeyPatchInvoiceApproval.isEmpty() && reqInvoiceApprovalFlag != null && !reqInvoiceApprovalFlag.isEmpty()) {
			if(resMonkeyPatchInvoiceApproval.equals(reqInvoiceApprovalFlag)) {
				System.out.println("resMonkeyPatchInvoiceApproval is :" + resMonkeyPatchInvoiceApproval + ": match with reqInvoiceApprovalFlag :" + reqInvoiceApprovalFlag);
			} else {
				Assert.fail("resMonkeyPatchInvoiceApproval is :" + resMonkeyPatchInvoiceApproval + ": is not match with reqInvoiceApprovalFlag :" + reqInvoiceApprovalFlag);
			}
		} else {
			System.out.println("resMonkeyPatchInvoiceApproval is null or empty");
		}
	}

}
