import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import utilities.DriverManager;

import java.sql.Driver;
import java.util.ArrayList;

public class HomeTests extends BaseTest {

    @Test
    public void orderingFromZToA(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.selectSortComboBox("Name (Z to A)");
        Assertions.assertTrue(homePage.areProductsInDescendantOrderByName());
    }

    //SD-21 Product Selection
    @Test
    public void verifyProductSelectionAfterLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");
        Thread.sleep(5000);

        homePage.clickOnBurgerButton();
        Thread.sleep(2000);
        homePage.clickOnLogoutLink();
        Thread.sleep(2000);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        Thread.sleep(2000);

        Assertions.assertTrue(homePage.isProductSelected("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(homePage.isProductSelected("Sauce Labs Bike Light"));
        Thread.sleep(3000);
    }

    //SD-22 Product Selection
    @Test
    public void checkTwitterLink() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        Thread.sleep(2000);
        loginPage.clickOnLoginButton();
        Thread.sleep(5000);
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnTwitterLink();


        ArrayList<String> tabHandles = new ArrayList<>(DriverManager.getDriver().driver.getWindowHandles());
        DriverManager.getDriver().driver.switchTo().window(tabHandles.get(1));

        SauceLabsTwitterPage sauceLabsTwitterPage = new SauceLabsTwitterPage
                (DriverManager.getDriver().driver);

        Assertions.assertTrue(sauceLabsTwitterPage.isTwitterPageCorrect("https://twitter.com/saucelabs"));
        Thread.sleep(10000);

    }

    //SD-23 Product Selection
    @Test
    public void verifyReturntoProductSelectionwithProducts() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        Thread.sleep(6000);
        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Thread.sleep(3000);
        yourCartPage.clickOnCheckoutButton();

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(DriverManager.getDriver().driver);
        Thread.sleep(2000);
        checkoutStepOnePage.setFirstNameTextbox("Juana");
        checkoutStepOnePage.setLastNameTextbox("Pérez");
        checkoutStepOnePage.setPostalCodeTextbox("0000");
        Thread.sleep(3000);
        checkoutStepOnePage.clickContinueButton();

        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(DriverManager.getDriver().driver);
        Thread.sleep(3000);
        checkoutStepTwoPage.clickCancelButton();

        Assertions.assertTrue(homePage.isProductSelected("Sauce Labs Onesie"));
        Assertions.assertTrue(homePage.isProductSelected("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(homePage.isProductSelected("Sauce Labs Bolt T-Shirt"));

        Thread.sleep(5000);
    }

}
