package com.assessment.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try (FileInputStream file = new FileInputStream("src/test/resources/config/config.properties")) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
