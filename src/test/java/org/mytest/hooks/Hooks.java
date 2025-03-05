package org.mytest.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.mytest.helperUtils.Driver;
import org.mytest.helperUtils.Logger;
import org.mytest.pages.HomePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class Hooks {

    @Before
    public void setUp() {
        Logger.info("Browser is initiated");
        Driver.openBrowser();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        String stepText = scenario.getName();
        Logger.info("Step: " + stepText + ":" + scenario.getLine());
    }

    @After
    public void tearDown(Scenario scenario) {
        Logger.info("Close browser");
        if (scenario.isFailed()) {
            try {
                File screenshotFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
                final byte[] screenshot = FileUtils.readFileToByteArray(screenshotFile);
                scenario.attach(screenshot, "image/png", scenario.getName() + "_error.png");
            } catch (Exception e) {
                Logger.error("Failed to take screenshot: " + e.getMessage());
            }
        }

        // Perform logout before quitting the driver
        try {
            HomePage homePage = new HomePage();
            homePage.logout();
        } catch (Exception e) {
            Logger.error("Failed to logout: " + e.getMessage());
        }

        Driver.quitDriver();
    }
}