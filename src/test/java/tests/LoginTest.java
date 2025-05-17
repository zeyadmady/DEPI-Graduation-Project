package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends TestBase {

    LoginPage login;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        login = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForInventoryPage() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }

    @Test
    public void testLoginWithValidData() {
        login.fillUserName("standard_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        waitForInventoryPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful for standard_user.");
        System.out.println("testLoginWithValidData: PASSED");
    }

    @Test
    public void testLoginWithTechnicalProblems() {
        login.fillUserName("problem_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        waitForInventoryPage();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
        boolean isLoginSuccessful = login.isLoginSuccessful();
        Assert.assertTrue(isLoginSuccessful, "Login should be successful for problem_user.");
        System.out.println("testLoginWithTechnicalProblems: PASSED");
    }

    @Test
    public void testLoginWithPerformanceGlitch() {
        login.fillUserName("performance_glitch_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        waitForInventoryPage();

        Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful despite performance glitches.");
        System.out.println("testLoginWithPerformanceGlitch: PASSED");
    }

    @Test
    public void testLoginWithLockedOutUser() {
        login.fillUserName("locked_out_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();

        // عند المستخدم المغلق، صفحة URL لا تتغير غالبًا، بتفضل في صفحة اللوجين
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");

        // لازم تتأكد من ظهور رسالة الخطأ بدلاً من نجاح الدخول
        String error = login.getErrorMessage();
        Assert.assertTrue(error.contains("locked out"), "Error message should indicate user is locked out.");
        System.out.println("testLoginWithLockedOutUser: PASSED");
    }

    @Test
    public void testLoginWithErrorUser() {
        login.fillUserName("error_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        waitForInventoryPage();

        Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful for error_user.");
        System.out.println("testLoginWithErrorUser: PASSED");
    }

    @Test
    public void testLoginWithVisualUser() {
        login.fillUserName("visual_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        waitForInventoryPage();

        Assert.assertTrue(login.isLoginSuccessful(), "Login should be successful for visual_user.");
        System.out.println("testLoginWithVisualUser: PASSED");
    }
}