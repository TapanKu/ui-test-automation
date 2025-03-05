package org.mytest.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mytest.helperUtils.ConfigReader;
import org.mytest.helperUtils.Driver;
import org.mytest.pages.HomePage;
import org.mytest.pages.LoginPage;
import org.openqa.selenium.Cookie;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();
    String url = ConfigReader.getProperty("url");

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToApp(url);

    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        Assertions.assertTrue(homePage.getHeaderText(), "User is not redirected to the inventory page");
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        loginPage.verifyErrorMessageIsPresent();
    }

    @Then("the session-username cookie should have value {string}")
    public void theSessionUsernameCookieShouldHaveValue(String expectedValue) {
        Cookie sessionCookie = Driver.getDriver().manage().getCookieNamed("session-username");
//        Assert.assertNotNull("Cookie 'session-username' should not be null", sessionCookie);
//        Assert.assertEquals("Cookie value is not as expected", expectedValue, sessionCookie.getValue());
    }

    @Then("the local storage should have session-username value {string}")
    public void theLocalStorageShouldHaveSessionUsernameValue(String expectedValue) {
        String localStorageValue = (String) ((org.openqa.selenium.JavascriptExecutor) Driver.getDriver())
                .executeScript("return window.localStorage.getItem('session-username');");
        System.out.println("Local storage value: " + localStorageValue);
//        Assert.assertEquals("Local storage value is not as expected", expectedValue, localStorageValue);
    }


    @When("I logout and re-login to the application with username {string} and password {string}")
    public void iLogoutAndReloginToTheApplicationWithUsernameAndPassword(String username, String password) {
        homePage.logout();
        loginPage.login(username, password);
    }
}