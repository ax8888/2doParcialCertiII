import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverManager;

public class CheckoutTests extends BaseTest{

    //SD-17 Checkout
    @Test
    public void verifyCorrectPurchase() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.addProductToCart("Sauce Labs Backpack");
        Thread.sleep(2000);
        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Thread.sleep(2000);
        yourCartPage.clickOnCheckoutButton();

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(DriverManager.getDriver().driver);
        checkoutStepOnePage.setFirstNameTextbox("Juana");
        checkoutStepOnePage.setLastNameTextbox("Pérez");
        checkoutStepOnePage.setPostalCodeTextbox("0000");
        Thread.sleep(2000);
        checkoutStepOnePage.clickContinueButton();

        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(DriverManager.getDriver().driver);

        Assertions.assertTrue(checkoutStepTwoPage.isProductDisplayed("Sauce Labs Onesie"));
        Assertions.assertTrue(checkoutStepTwoPage.isProductDisplayed("Sauce Labs Backpack"));

        Assertions.assertTrue(checkoutStepTwoPage.isPriceDisplayed("$7.99"));
        Assertions.assertTrue(checkoutStepTwoPage.isPriceDisplayed("$29.99"));

        Assertions.assertTrue(checkoutStepTwoPage.isPaymentInformationCorrect("SauceCard #31337"));

        Assertions.assertTrue(checkoutStepTwoPage.isShippingInformationCorrect("Free Pony Express Delivery!"));

        Assertions.assertTrue(checkoutStepTwoPage.isTotalCorrect());
        Thread.sleep(5000);
        checkoutStepTwoPage.clickFinishButton();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutCompletePage.isCompleteHeaderCorrect("Thank you for your order!"));
        Thread.sleep(4000);
        checkoutCompletePage.clickBackHomeButton();

        Assertions.assertTrue(homePage.pageTitleIsDisplayed());

        Thread.sleep(3000);



    }

    //SD-24 Checkout
    @Test
    public void verifyEmptyFieldsErrorMessages() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Onesie");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.clickOnCheckoutButton();

        CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage(DriverManager.getDriver().driver);
        checkoutStepOnePage.clickContinueButton();
        Thread.sleep(3000);
        Assertions.assertTrue(checkoutStepOnePage.isErrorMessageCorrect("Error: First Name is required"));

        checkoutStepOnePage.setFirstNameTextbox("Juana");
        Thread.sleep(1000);
        checkoutStepOnePage.clickContinueButton();
        Thread.sleep(2000);
        Assertions.assertTrue(checkoutStepOnePage.isErrorMessageCorrect("Error: Last Name is required"));

        checkoutStepOnePage.setLastNameTextbox("Pérez");
        Thread.sleep(1000);
        checkoutStepOnePage.clickContinueButton();
        Thread.sleep(2000);
        Assertions.assertTrue(checkoutStepOnePage.isErrorMessageCorrect("Error: Postal Code is required"));
    }

}
