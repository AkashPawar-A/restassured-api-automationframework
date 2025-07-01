package com.onsite.utilities_page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

	public static String getJsonData(String filePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load JSON file: " + filePath);
		}
	}
}
