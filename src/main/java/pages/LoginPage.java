package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Element locators
    By userName = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By errorMessage = By.cssSelector("[data-test='error']");

    public void fillUserName(String username) {
        this.fillElement(userName, username);
    }

    public void fillPassword(String password) {
        this.fillElement(this.password, password);
    }

    public void ClickButton() {
        this.clickOnElement(loginButton);
    }

    public String getErrorMessage() {
        return getText(driver, errorMessage);
    }

    public void login(String visualUser, String secretSauce) {
        // Empty implementation, consider completing or removing
        // Example: fillUserName(visualUser); fillPassword(secretSauce); ClickButton();
    }

    public boolean isLoginSuccessful() {
        return !driver.findElements(By.className("inventory_list")).isEmpty();
    }
}