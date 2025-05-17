package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.HomePage;

public class CartTest extends TestBase {

    WebDriver driver;
    LoginPage login;
    HomePage products;
    CartPage cart;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        login = new LoginPage(driver);
        products = new HomePage(driver);
        cart = new CartPage(driver);

        login.fillUserName("standard_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void validatePriceDisplayInCart() {
        String productPrice = products.getFirstProductPrice();
        products.addFirstProductToCart();
        products.goToCart();
        String cartPrice = cart.getCartItemPrice();

        Assert.assertEquals(cartPrice, productPrice);
    }

    @Test
    public void testLoginInCheckout() {
        cart.openCart();
        cart.clickCheckout();
        Assert.assertTrue(cart.isOnCheckoutPage());

    }

    @Test
    public void  testRemoveProductFunction(){
        products.addFirstProductToCart();
        products.goToCart();
        cart.isRemoveButtonDisplayed();
        cart.clickOnRemoveBtn();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

