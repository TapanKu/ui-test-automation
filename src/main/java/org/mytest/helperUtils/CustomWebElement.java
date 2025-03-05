package org.mytest.helperUtils;

import org.openqa.selenium.WebElement;

public class CustomWebElement {

    private final WebElement element;

    public CustomWebElement(WebElement element) {
        this.element = element;
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