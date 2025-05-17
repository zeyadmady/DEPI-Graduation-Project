package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Element locators
    By cartItemPrice = By.className("inventory_item_price");
    By checkoutButton = By.id("checkout");
    By removeBtn = By.id("remove-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");

    // Actions
    public String getCartItemPrice() {
        waitForElementVisibility(cartItemPrice);
        return driver.findElement(cartItemPrice).getText();
    }


    public void clickCheckout() {
        clickOnElement(checkoutButton);
    }

    public void clickOnRemoveBtn() {
        clickOnElement(removeBtn);
    }
    public String getCheckoutTitleText(){
        waitForElementVisibility(By.className("title"));
        return driver.findElement(By.className("title")).getText().trim();
    }


    public boolean isOnCheckoutPage() {
        String pageTitle = getCheckoutTitleText();
        return pageTitle.equalsIgnoreCase("Checkout: Your Information");
    }

    public void openCart() {
        clickOnElement(cartIcon);
    }

    public boolean isRemoveButtonDisplayed() {
        return !driver.findElements(removeBtn).isEmpty();
    }


}
