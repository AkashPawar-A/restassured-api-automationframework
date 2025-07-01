package Testing;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

    public static String getProjectName() {
        String generatedName = RandomStringUtils.randomAlphabetic(5);
        return "AkashProject_" + generatedName;
    }
}
