package org.mytest.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mytest.helperUtils.Driver;
import org.mytest.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @When("I add the following items to the cart:")
    public void iAddTheFollowingItemsToTheCart(DataTable dataTable) {
        homePage.addItemsToCart(dataTable.asList());

    }

    @Then("I should see the remove button for the following items:")
    public void iShouldSeeTheRemoveButtonForTheFollowingItems() {

    }

    @Then("I should see the following items in the cart:")
    public void iShouldSeeTheFollowingItemsInTheCart(DataTable dataTable) {
        homePage.verifyItemsInCart(dataTable.asList());

    }

    @And("I clicked on the checkout button")
    public void iClickedOnTheCheckoutButton() {
        homePage.clickCheckoutButton();

    }

    @And("I filled the checkout form with the following details:")
    public void iFilledTheCheckoutFormWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        Map<String, String> map = list.get(0);
        homePage.enterCheckoutInformation(map);

    }

    @And("I clicked on the continue button")
    public void iClickedOnTheContinueButton() {
        homePage.clickOnContinueButton();

    }

    @And("I clicked on the finish button")
    public void iClickedOnTheFinishButton() {
        homePage.clickOnFinishButton();
    }

    @Then("I should see the order confirmation message as {string}")
    public void iShouldSeeTheOrderConfirmationMessageAs(String message) {
        Assertions.assertEquals(message, homePage.getOrderConfirmationMessage(), "Expected message not matched");
    }

    @Then("I verify Add to Cart is changed to Remove for the item {string}")
    public void iVerifyAddToCartIsChangedToRemoveForTheItem(String itemName) {
        Assertions.assertTrue(homePage.isRemoveButtonDisplayed(itemName));
    }

    @And("I clicked on shopping cart icon")
    public void iClickedOnShoppingCartIcon() {
        homePage.clickShoppingCartContainer();
    }

    @Then("I should not see the following items in the cart:")
    public void iShouldNotSeeTheFollowingItemsInTheCart(DataTable dataTable) {
        List<String> items = dataTable.asList();
        if (items.size() > 1) {
            items.forEach(itemName -> {
                String xpath = String.format("//div[@class='cart_item']//div[@class='inventory_item_name'][text()='%s']", itemName);
                List<WebElement> itemElements = Driver.getDriver().findElements(By.xpath(xpath));
                Assertions.assertTrue(itemElements.isEmpty(), "Item " + itemName + " should not be present in the cart");
            });
        }

    }

    @When("I get the price of all items")
    public void iGetThePriceOfAllItems() {
        homePage.getAllItemPrices();
    }

    @Then("I should get the sum of all items price is {string}")
    public void iShouldGetTheSumOfAllItemsPriceIs(String price) {
        double expectedPrice = Double.parseDouble(price.replace("$", ""));
        double actualPrice = homePage.getSumOfAllItemsPrice();
        Assertions.assertEquals(expectedPrice, actualPrice, "Sum of all items is not as expected");

    }


}
