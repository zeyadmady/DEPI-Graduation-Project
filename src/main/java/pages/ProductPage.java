package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // ----------------- Element Locators -----------------
    By userName = By.id("user-name");
    By password = By.id("password");
    By logInButton = By.id("login-button");

    By productTitle = By.className("inventory_item_name");
    By productDetailsTitle = By.className("inventory_details_name");
    By addToCartButton = By.cssSelector(".inventory_item button");
    By cartPageCount = By.cssSelector("span[class='shopping_cart_badge']");
    By removeButton = By.name("remove-sauce-labs-backpack");

    By sortDropdown = By.className("product_sort_container");
    By productNames = By.className("inventory_item_name");
    By productImages = By.cssSelector(".inventory_item_img img");
    By productPrices = By.className("inventory_item_price");

    // ----------------- Login Actions -----------------
    public void fillUserName(String username) {
        fillElement(userName, username);
    }

    public void fillPassword(String password) {
        fillElement(this.password, password);
    }

    public void ClickButton() {
        clickOnElement(logInButton);
    }

    // ----------------- Product Actions -----------------
    public void clickAddToCart() {
        waitForElementToBeClickable(addToCartButton);
        clickOnElement(addToCartButton);
    }

    public Integer getCartPageCount() {
        waitForElementVisibility(cartPageCount);
        return Integer.parseInt(getText(driver, cartPageCount));
    }

    public void clickOnProductTitle() {
        waitForElementToBeClickable(productTitle);
        clickOnElement(productTitle);
    }

    public String getProductDetailsTitle() {
        waitForElementVisibility(productDetailsTitle);
        return getText(driver, productDetailsTitle);
    }

    public void removeFromCart() {
        waitForElementToBeClickable(removeButton);
        clickOnElement(removeButton);
    }

    // ----------------- Sorting & Products -----------------
    public void selectSortOption(String optionText) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(optionText);
    }

    public List<WebElement> getProductNameElements() {
        return driver.findElements(productNames);
    }

    public List<WebElement> getProductImageElements() {
        return driver.findElements(productImages);
    }

    public List<WebElement> getProductPriceElements() {
        return driver.findElements(productPrices);
    }
}