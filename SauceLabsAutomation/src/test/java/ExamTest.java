import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;
import utilities.DriverManager;

public class ExamTest extends BaseTest {
    @Test
    public void examtest() throws InterruptedException {

        //Adriana Villarroel Claros
        //62423

        //Ir a: https://www.saucedemo.com/
        //BaseTest

        //Loguearse con las siguientes credenciales: standard_user, secret_sauce
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Agregar 2 o mas productos al carrito desde el home page
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Onesie");
        homePage.addProductToCart("Sauce Labs Bolt T-Shirt");
        homePage.addProductToCart("Sauce Labs Bike Light");

        //Ir al carrito
        homePage.clickOnShoppingCartButton();

        //Verificar que los 3 items agregados estan en el carrito
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Onesie"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bolt T-Shirt"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));

        //Verificar que el precio sea correcto de los items agregados
        Assertions.assertTrue(yourCartPage.isPriceDisplayed("$7.99"));
        Assertions.assertTrue(yourCartPage.isPriceDisplayed("$15.99"));
        Assertions.assertTrue(yourCartPage.isPriceDisplayed("$9.99"));

        //Verificar que el icono del carrito tiene el numero correcto en base a cuantos items fueron seleccionados
        Assertions.assertTrue(yourCartPage.isNumberofElementsEqualtoDisplayed());

        //Remover los items agregados
        yourCartPage.removeProduct("Sauce Labs Onesie");
        yourCartPage.removeProduct("Sauce Labs Bolt T-Shirt");
        yourCartPage.removeProduct("Sauce Labs Bike Light");

        //Verificar que los items fueron removidos
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Onesie")));
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Bolt T-Shirt")));
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Bike Light")));

        //Verificar que el icono del carrito no tiene ningun numero
        Assertions.assertTrue((yourCartPage.isShoppingCartEmpty()));


        //Thread.sleep(5000);
    }
}
