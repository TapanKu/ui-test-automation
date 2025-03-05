package org.mytest.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mytest.pages.HomePage;

import java.util.List;
import java.util.Map;

public class HomeStepDefs {
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
//        Assert.assertEquals("Expected message not matched", message, homePage.getOrderConfirmationMessage());
    }

    @Then("I verify Add to Cart is changed to Remove for the item {string}")
    public void iVerifyAddToCartIsChangedToRemoveForTheItem(String itemName) {
//        Assert.assertTrue(homePage.isRemoveButtonDisplayed(itemName));
    }

    @And("I clicked on shopping cart icon")
    public void iClickedOnShoppingCartIcon() {
        homePage.clickShoppingCartContainer();
    }

    @Then("I should not see the following items in the cart:")
    public void iShouldNotSeeTheFollowingItemsInTheCart(DataTable dataTable) {
        homePage.verifyItemsNotPresentInCart(dataTable.asList());
    }
}
