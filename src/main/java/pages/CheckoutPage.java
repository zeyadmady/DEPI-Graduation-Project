package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends PageBase {

    // Element locators
    By addToCartBtn = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");
    By checkoutBtn = By.id("checkout");
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueBtn = By.id("continue");
    By finishBtn = By.id("finish");
    By errorMsg = By.cssSelector("[data-test='error']");
    By stepTitle = By.className("title");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        clickOnElement(addToCartBtn);
    }

    public void openCart() {
        waitForElementVisibility(cartIcon);
        waitForElementToBeClickable(cartIcon);

        WebElement cartElement = driver.findElement(cartIcon);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartElement);
    }

    public void clickCheckout() {
        waitForElementVisibility(checkoutBtn);
        waitForElementToBeClickable(checkoutBtn);
        clickOnElement(checkoutBtn);
    }

    public void enterCheckoutInformation(String fName, String lName, String zip) {
        fillElement(firstName, fName);
        fillElement(lastName, lName);
        fillElement(postalCode, zip);
        clickOnElement(continueBtn);
    }

    public void finishCheckout() {
        waitForElementVisibility(finishBtn);
        waitForElementToBeClickable(finishBtn);
        clickOnElement(finishBtn);
    }

    public String getCurrentStepTitle() {
        return getText(driver, stepTitle);
    }

    public String getErrorMessage() {
        return getText(driver, errorMsg);
    }
}