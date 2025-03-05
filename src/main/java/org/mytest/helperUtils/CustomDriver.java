package org.mytest.helperUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomDriver extends Driver {
    private static WebDriver driver;
    public CustomDriver(WebDriver driver){
        super();
        this.driver= driver;
    }

    public CustomWebElement findElement(By by) {
        WebElement element = driver.findElement(by);
        return new CustomWebElement(element);
    }
}