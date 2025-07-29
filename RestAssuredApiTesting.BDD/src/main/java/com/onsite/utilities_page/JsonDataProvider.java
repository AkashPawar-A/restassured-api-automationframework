package com.onsite.utilities_page;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonDataProvider {

	public static Object[][] getDataFromJson(String filePath, Class<?> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(filePath);

            // Detect array or single object
            if (file.exists()) {
                // Check if file starts with `[` indicating it's an array
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath())).trim();

                if (content.startsWith("[")) {
                    // It's a JSON array
                    CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
                    List<?> dataList = mapper.readValue(content, listType);

                    Object[][] result = new Object[dataList.size()][1];
                    for (int i = 0; i < dataList.size(); i++) {
                        result[i][0] = dataList.get(i);
                    }
                    return result;

                } else {
                    // It's a single JSON object
                    Object singleObj = mapper.readValue(content, clazz);
                    return new Object[][] { { singleObj } };
                }
            } else {
                throw new RuntimeException("File not found: " + filePath);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data from file: " + filePath, e);
        }
    }
}
