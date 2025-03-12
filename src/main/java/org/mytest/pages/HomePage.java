package org.mytest.pages;

import org.mytest.helperUtils.CustomWebElement;
import org.mytest.helperUtils.Driver;
import org.mytest.helperUtils.Logger;
import org.mytest.helperUtils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePage extends BasePage {
    private WaitUtils waitUtils;

    public HomePage() {
        super(Driver.getDriver());
        this.waitUtils = new WaitUtils(Driver.getDriver(), 20);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menu_btn;

    @FindBy(xpath = "//*[text()='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[text()='Swag Labs']")
    private WebElement hdr_SwagLabs;
    @FindBy(xpath = "//*[contains(text(), 'Checkout')]")
    private WebElement checkoutButton;
    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartContainer;

    @FindBy(id = "first-name")
    private WebElement firstName;
    @FindBy(id = "last-name")
    private WebElement lastName;
    @FindBy(id = "postal-code")
    private WebElement postalCode;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(id = "finish")
    private WebElement finishButton;


    public void logout() {
        if (menu_btn.isDisplayed()) {
            waitUtils.waitForElementToBeClickable(menu_btn).click();
            waitUtils.waitForElementToBeClickable(logoutButton).click();
        }
    }

    public boolean getHeaderText() {
        return waitUtils.waitForElementToBeVisible(hdr_SwagLabs).isDisplayed();
    }

    public void addItemToCart(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Add to cart')]", itemName);
        WebElement addToCartButton = Driver.getDriver().findElement(By.xpath(xpath));
        waitUtils.waitForElementToBeClickable(addToCartButton).click();
    }

    public void addItemsToCart(List<String> items) {
        if (items.size() > 1) {
            items.subList(1, items.size()).forEach(this::addItemToCart);
        }

    }

    public void clickCheckoutButton() {
        waitUtils.waitForElementToBeClickable(checkoutButton).click();
        waitUtils.setImplicitWait(10);
    }

    public boolean isRemoveButtonDisplayed(String itemName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Remove')]", itemName);
        WebElement removeButton = Driver.getDriver().findElement(By.xpath(xpath));
        return waitUtils.waitForElementToBeVisible(removeButton).isDisplayed();
    }

    public void clickShoppingCartContainer() {
        waitUtils.waitForElementToBeClickable(shoppingCartContainer).click();
        waitUtils.setImplicitWait(30);
    }


    public void verifyItemsInCart(List<String> items) {
        if (items.size() > 1) {
            items.subList(1, items.size()).forEach(itemName -> {
                String xpath = String.format("//div[@class='cart_item']//div[@class='inventory_item_name'][text()='%s']", itemName);
                WebElement item = Driver.getDriver().findElement(By.xpath(xpath));
                waitUtils.waitForElementToBeVisible(item);
            });
        }
    }

    public void enterCheckoutInformation(Map<String, String> map) {
        waitUtils.waitForElementToBeVisible(firstName).sendKeys(map.get("firstName"));
        waitUtils.waitForElementToBeVisible(lastName).sendKeys(map.get("lastName"));
        waitUtils.waitForElementToBeVisible(postalCode).sendKeys(map.get("postalCode"));
    }

    public void clickOnContinueButton() {
        waitUtils.waitForElementToBeClickable(continueButton).click();
    }

    public void clickOnFinishButton() {
        waitUtils.waitForElementToBeClickable(finishButton).click();
    }

    public String getOrderConfirmationMessage() {
        CustomWebElement confirmationMessage = customDriver.findElement(By.xpath("//*[contains(text(), 'Thank you for your order!')]"));
        return confirmationMessage.getText();
    }

    /**
     * This method will return all the item prices
     */
    public List<Double> getAllItemPrices() {
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//*[@class='inventory_item_price']"));
        List<Double> prices = new ArrayList<>();
        for (WebElement element : elements) {
            String itemPrice = element.getText();
            itemPrice = itemPrice.replace("$", "");
            double price = Double.parseDouble(itemPrice);
            prices.add(price);

        }
        return prices;
    }

    public double getSumOfAllItemsPrice() {
        List<Double> prices = getAllItemPrices();
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        Logger.info("Sum of all items is: " + sum);
        return sum;
    }

}