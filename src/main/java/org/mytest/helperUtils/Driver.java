package org.mytest.helperUtils;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

public class Driver {
    @Setter
    @Getter
    private static WebDriver driver;

    public enum Browser {
        CHROME, FIREFOX
    }

    public static void openBrowser() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            Browser selectedBrowser;
            try {
                selectedBrowser = Browser.valueOf(browser.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            switch (selectedBrowser) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    List<String> chromeArguments = ChromeOptionsReader.getChromeArguments();
                    options.addArguments(chromeArguments);
                    driver = new ChromeDriver(options);
                    break;
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        setDriver(driver);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}