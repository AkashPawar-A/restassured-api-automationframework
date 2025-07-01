package com.onsite.utilities_page;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static void loadProperties() {
        prop = new Properties();
        try {
        	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/onsite/config/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static String getProperty(String key) {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(key);
    }

    public static String getBaseURI() {
        return getProperty("baseURI");
    }
    
    public static String getApiVersionV3() {
        return getProperty("apis_version3");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getCountryCode() {
        return getProperty("country_code");
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }
}

