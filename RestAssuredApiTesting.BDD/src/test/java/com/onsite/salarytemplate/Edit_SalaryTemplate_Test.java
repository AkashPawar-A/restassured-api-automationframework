package com.onsite.salarytemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsite.pojo_request.Add_SalaryTemplate;
import com.onsite.utilities_page.SchemaValidator;

public class Edit_SalaryTemplate_Test {
	
	@DataProvider(name="editSalaryTemplateData")
	public Object getData() throws Exception {
		
		String editFilePathpath = "src/test/resources/testdata_salaryTemplate/Edit_salaryTemplate.json";
		String editPayload = new String(Files.readAllBytes(Paths.get("editFilePathpath")));
		
		ObjectMapper mapper = new ObjectMapper();
		Add_SalaryTemplate editSalaryTemplate = mapper.readValue(editPayload, Add_SalaryTemplate.class);
		String finalPayload = mapper.writeValueAsString(editSalaryTemplate);
		
		System.out.println("final payload :" + finalPayload);
		SchemaValidator.validateSchema("requestSchemas_files/SalaryTemplateRequest.json", finalPayload);
		
		return new Object[][] {
			{ finalPayload }
		};
	}
	
	public void editSalaryTemplate() {
		
	}

}
