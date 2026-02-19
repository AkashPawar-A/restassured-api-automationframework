package com.onsite.utilities_page;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonUtils {
	
	private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<String, Object> readJson(String path) {
        try {
            return mapper.readValue(
                new File(path),
                new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("JSON read failed: " + path, e);
        }
    }

    public static <T> T converMaptoPojo(Map<String, Object> data, Class<T> clazz) {
        try {
            return mapper.convertValue(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Map to POJO conversion failed", e);
        }
    }

}
