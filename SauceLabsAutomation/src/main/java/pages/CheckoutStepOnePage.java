package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {

    WebDriver driver;
    @FindBy(id = "first-name")
    WebElement firstNameTextbox;

    @FindBy(id = "last-name")
    WebElement lastNameTextbox;

    @FindBy(id = "postal-code")
    WebElement postalCodeTextbox;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    public CheckoutStepOnePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstNameTextbox(String firstName) {
        firstNameTextbox.sendKeys(firstName);
    }

    public void setLastNameTextbox(String lastName){
        lastNameTextbox.sendKeys(lastName);
    }

    public void setPostalCodeTextbox(String postalCode){
        postalCodeTextbox.sendKeys(postalCode);
    }

    public void clickContinueButton(){
        continueButton.click();
    }

    public boolean isErrorMessageCorrect(String expectedErrorMessage){
        String actualErrorMessage = errorMessage.getText();
        return actualErrorMessage.equals(expectedErrorMessage);
    }
}
