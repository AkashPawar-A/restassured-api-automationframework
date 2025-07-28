package com.onsite.utilities_page;

import java.io.IOException;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class JsonDataProvider {

	public static <T> Object[][] getDataFromJson(String filePath, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        Object[][] data = null;

        try {
            // Try to parse as array first
            T[] array = mapper.readValue(new File(filePath), mapper.getTypeFactory().constructArrayType(clazz));
            data = new Object[array.length][1];
            for (int i = 0; i < array.length; i++) {
                data[i][0] = array[i];
            }
        } catch (MismatchedInputException e) {
            try {
                // If not array, try to parse single object
                T singleObject = mapper.readValue(new File(filePath), clazz);
                data = new Object[1][1];
                data[0][0] = singleObject;
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Failed to read test data from file: " + filePath, ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read test data from file: " + filePath, e);
        }

        return data;
    }
}
