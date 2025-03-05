package org.mytest.pages;

import org.mytest.helperUtils.Driver;
import org.mytest.helperUtils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private WaitUtils waitUtils;

    public LoginPage() {
        super(Driver.getDriver());
        this.waitUtils = new WaitUtils(Driver.getDriver(), 20);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(@class, 'error-message-container')]")
    private WebElement errorMessage;

    public void navigateToApp(String url) {
        Driver.getDriver().get(url);
    }

    public void login(String userName, String passWord) {
        waitUtils.waitForElementToBeVisible(username).sendKeys(userName);
        waitUtils.waitForElementToBeVisible(password).sendKeys(passWord);
        waitUtils.waitForElementToBeClickable(loginButton).click();
    }

    public void verifyErrorMessageIsPresent() {
        waitUtils.waitForElementToBeVisible(errorMessage);
    }
}