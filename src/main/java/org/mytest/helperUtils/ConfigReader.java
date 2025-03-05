package org.mytest.helperUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        String userDir = System.getProperty("user.dir");
        String configFilePath = userDir + "/Config.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}