package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    protected WebDriver driver;
    public static final Duration WAIT = Duration.ofSeconds(10);

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void fillElement(By locator, String value) {
        waitForElementVisibility(locator);
        driver.findElement(locator).sendKeys(value);
    }

    public void clickOnElement(By locator) {
        waitForElementVisibility(locator);
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    public String findCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static String getText(WebDriver driver, By locator) {
        return driver.findElement(locator).getText();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Best practice: restore the interrupted status
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}