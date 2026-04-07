package com.onsite.transaction.materialPurchase_test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.MaterialPurchase;
import com.onsite.utilities_page.AuthUtils;


@Test
public class Details_materialPurchase_Test {
	
	Response detailsMaterialPurchase = 
			given()
			.baseUri(ApiBasePath.BASE_URL)
			.header("Authorization", "Bearer " + AuthUtils.getToken())
			.contentType(ContentType.JSON)
			.log().uri()
			
			.when()
			.get(MaterialPurchase.detail_materialPurchase)
			
			.then()
			.log().all()
			.extract().response();
}
