package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        // لو عندك مسار مخصص للكروم درايفر ممكن تضيفه هنا:
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null; // للتأكد من إنه اتفصل تماما
        }
    }
}
