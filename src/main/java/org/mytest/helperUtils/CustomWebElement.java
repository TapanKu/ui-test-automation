package org.mytest.helperUtils;

import org.openqa.selenium.WebElement;

public class CustomWebElement {

    public WebElement element;

    public CustomWebElement(WebElement element) {
        this.element = element;
    }

    public void click() {
        Logger.info("Clicking on element: " + element.toString());
        element.click();
    }

    public void sendKeys(String text) {
        Logger.info("Sending keys to element: " + element.toString());
        element.sendKeys(text);
    }

    public boolean isDisplayed() {
        Logger.info("Checking if element is displayed: " + element.toString());
        return element.isDisplayed();
    }

    public boolean isEnabled() {
        Logger.info("Checking if element is enabled: " + element.toString());
        return element.isEnabled();
    }

    public String getText() {
        Logger.info("Getting text from element: " + element.toString());
        return element.getText();
    }

    @Override
    public String toString() {
        return "CustomWebElement{" +
                "element=" + element +
                '}';
    }
}