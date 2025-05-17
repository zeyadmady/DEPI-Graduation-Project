package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.util.*;

public class ProductTest extends TestBase{
    LoginPage login;
    ProductPage product;

    @BeforeMethod
    public void setUpPages() {
        login = new LoginPage(driver);
        product = new ProductPage(driver);
    }

    //TC_Swag_Product_006
    @Test
    public void testOpenProductDetailsPage() throws InterruptedException{
        login.fillUserName("standard_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        product.clickOnProductTitle();
        String Title = product.getProductDetailsTitle();
        // Add a small delay to ensure the cart updates
        Thread.sleep(5000);
        Assert.assertEquals(Title, "Sauce Labs Backpack");
    }
    @DataProvider(name = "sortOptions")
    public Object[][] sortOptions() {
        return new Object[][] {
                {"Name (A to Z)", true},
                {"Name (Z to A)", true},
                {"Price (low to high)", false},
                {"Price (high to low)", false}
        };
    }
    //TC_Swag_Product_003
    @Test(dataProvider = "sortOptions")
    public void verifySorting(String sortOption, boolean isNameSort) {
        LoginPage login = new LoginPage(driver);
        login.fillUserName("standard_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortOption(sortOption);
        if (isNameSort) {
            List<WebElement> elements = productPage.getProductNameElements();
            List<String> actualNames = new ArrayList<>();
            for (WebElement el : elements) {
                actualNames.add(el.getText());
            }
            List<String> sortedNames = new ArrayList<>(actualNames);
            if (sortOption.equals("Name (Z to A)")) {
                Collections.sort(sortedNames, Collections.reverseOrder());
            } else {
                Collections.sort(sortedNames);
            }
            Assert.assertEquals(actualNames, sortedNames);
        } else {
            List<WebElement> priceElements = productPage.getProductPriceElements();
            List<Double> actualPrices = new ArrayList<>();
            for (WebElement el : priceElements) {
                actualPrices.add(Double.parseDouble(el.getText().replace("$", "")));
            }
            List<Double> sortedPrices = new ArrayList<>(actualPrices);
            if (sortOption.equals("Price (high to low)")) {
                Collections.sort(sortedPrices, Collections.reverseOrder());
            } else {
                Collections.sort(sortedPrices);
            }
            Assert.assertEquals(actualPrices, sortedPrices);
        }
    }
    //TC_Swag_Product_005
    @Test
    public void verifyUniqueImages() {
        LoginPage login = new LoginPage(driver);
        login.fillUserName("problem_user");
        login.fillPassword("secret_sauce");
        login.ClickButton();

        ProductPage productPage = new ProductPage(driver);
        List<WebElement> images = productPage.getProductImageElements();

        List<String> srcList = new ArrayList<>();
        for (WebElement img : images) {
            srcList.add(img.getAttribute("src"));
        }

        Set<String> uniqueImages = new HashSet<>(srcList);
        Assert.assertEquals(uniqueImages.size(), srcList.size(), "Some product images are duplicated.");
    }
    //TC_Swag_Product_7
    @Test
    public void InCorrectProductName() {
        product.fillUserName("standard_user");
        product.fillPassword("secret_sauce");
        product.ClickButton();
        String expectedResult = "Black Backpack";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        Assert.assertNotEquals(actualResult, expectedResult);

    }
    // TC_Swag_Product_9
    @Test
    public void GetProductDescription() {
        product.fillUserName("error_user");
        product.fillPassword("secret_sauce");
        product.ClickButton();
        String expectedResult = "Black Backpack for college";
        String actualResult = driver.findElement(By.cssSelector("div[data-test=\"inventory-item-desc\"]")).getText();
        Assert.assertNotEquals(actualResult, expectedResult);
    }

}
