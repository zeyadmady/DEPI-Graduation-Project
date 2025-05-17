package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends TestBase {

    private static final String STANDARD_USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    public void testStandardUserCheckoutFlow() {
        // Login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("user-name")).sendKeys(STANDARD_USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();

        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed");

        // Add items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

        // Verify items in cart
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "Cart should contain 2 items");

        // Proceed to checkout
        driver.findElement(By.id("checkout")).click();

        // Fill checkout information
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        driver.findElement(By.id("first-name")).sendKeys("youssef");
        driver.findElement(By.id("last-name")).sendKeys("emad");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        // Verify checkout overview
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout_summary_container")));
        Assert.assertTrue(driver.findElement(By.className("summary_total_label")).getText().contains("Total: $"), "Total price not displayed");

        // Complete checkout
        driver.findElement(By.id("finish")).click();

        // Verify successful checkout
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
//            Assert.assertTrue(driver.findElement(By.className("complete-header")).getText().contains("THANK YOU"), "Order not completed successfully");
    }
    @Test
    public void testStandardUserCheckoutFlow2() {
        // Login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("user-name")).sendKeys(STANDARD_USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();

        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed");

        // Add items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

        // Verify items in cart
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "Cart should contain 2 items");

        // Proceed to checkout
        driver.findElement(By.id("checkout")).click();

        // Fill checkout information
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        driver.findElement(By.id("continue")).click();
    }
}