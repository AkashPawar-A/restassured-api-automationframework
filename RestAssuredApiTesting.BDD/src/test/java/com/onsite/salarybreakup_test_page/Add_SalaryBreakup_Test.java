package com.onsite.salarybreakup_test_page;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.onsite.context.SalaryBreakupDetails;
import com.onsite.endpoints.ApiBasePath;
import com.onsite.endpoints.SalaryBreakup_Api;
import com.onsite.pojo_request.Salary_BreakupRequest;
import com.onsite.utilities_page.AuthUtils;
import com.onsite.utilities_page.JsonDataProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import static io.restassured.RestAssured.*;

import java.io.FileReader;
import java.io.IOException;

public class Add_SalaryBreakup_Test {
	
//	public Object[][] fileData() {
//		
//		String filePath = "src/test/resources/testdata_salarybreakup/Create_SalaryBreakup.json";
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonData = mapper.readValue(new File(filePath), null);
//	}
}
