package com.onsite.utilities_page;

import java.io.*;
import java.util.Properties;

public class DataFile {

	private static final String detailsPurcaseFILE_PATH = 
			"src/test/resources/Properties/materialPurchase.properties";

	// WRITE
	public static void setData(String key, String value) {
		try {
			Properties prop = new Properties();
			File file = new File(detailsPurcaseFILE_PATH);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				prop.load(fis);
				fis.close();
			}
			prop.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(file);
			prop.store(fos, "Runtime Data");
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException("Error writing runtime data", e);
		}
	}

	// READ
	public static String getData(String key) {
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(detailsPurcaseFILE_PATH);
			prop.load(fis);
			fis.close();

			return prop.getProperty(key);
		} catch (Exception e) {
			throw new RuntimeException("Error reading runtime data", e);
		}
	}
}
