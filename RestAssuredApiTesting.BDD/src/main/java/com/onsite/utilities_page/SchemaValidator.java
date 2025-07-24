package com.onsite.utilities_page;

import java.io.InputStream;

import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class SchemaValidator {
	
    public static void validateSchema(String schemaFilePath, String jsonResponse) throws Exception {
    	
        // Load schema from resources
        InputStream schemaStream = SchemaValidator.class.getClassLoader().getResourceAsStream(schemaFilePath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode schemaNode = mapper.readTree(schemaStream);

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaNode);

        // Parse response JSON
        JsonNode responseNode = mapper.readTree(jsonResponse);

        // Validate
        ProcessingReport report = schema.validate(responseNode);

        // Assert validation success
        Assert.assertTrue(report.isSuccess(), "JSON schema validation failed: " + report);
    }

}
