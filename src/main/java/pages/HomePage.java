package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Elements
    By firstProductPrice = By.cssSelector("[data-test='inventory-item-price']");
    By firstProductAddToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");
    By cartItemPrice = By.className("inventory_item_price");

    // Methods
    public String getFirstProductPrice() {
        waitForElementVisibility(firstProductPrice);
        return driver.findElement(firstProductPrice).getText();
    }

    public void addFirstProductToCart() {
        clickOnElement(firstProductAddToCartBtn);
    }

    public void goToCart() {
        clickOnElement(cartIcon);
    }
}
