package org.mytest.helperUtils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ChromeOptionsReader {
    public static List<String> getChromeArguments() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = ChromeOptionsReader.class
                    .getClassLoader()
                    .getResourceAsStream("chrome_options.yml");
            if (inputStream == null) {
                throw new RuntimeException("chrome_options.yml not found in resources.");
            }
            Map<String, Object> data = yaml.load(inputStream);
            Map<String, Object> chromeData = (Map<String, Object>) data.get("chrome");
            Map<String, Object> optionsData = (Map<String, Object>) chromeData.get("options");
            return (List<String>) optionsData.get("arguments");
        } catch (Exception e) {
            throw new RuntimeException("Error reading chrome options from YAML", e);
        }
    }
}
