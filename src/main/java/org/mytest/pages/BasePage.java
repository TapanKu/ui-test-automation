package org.mytest.pages;

import org.mytest.helperUtils.CustomDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected CustomDriver customDriver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.customDriver = new CustomDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToApp(String url) {
        driver.get(url);
    }

}