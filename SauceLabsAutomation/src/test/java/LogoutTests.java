import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class LogoutTests extends BaseTest {

    @Test
    public void verifyUserCanLogOut() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        Assertions.assertTrue(loginPage.isLoginButtonDisplayed());

    }
}
