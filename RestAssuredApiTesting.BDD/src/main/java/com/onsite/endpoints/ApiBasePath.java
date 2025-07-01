package com.onsite.endpoints;

import com.onsite.utilities_page.ConfigReader;

public class ApiBasePath {

	public static final String BASE_URL;

    static {
        ConfigReader.loadProperties(); // Ensures props are ready
        BASE_URL = ConfigReader.getBaseURI() + ConfigReader.getApiVersionV3();
    }
}
