package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceLabsTwitterPage {
    WebDriver driver;
    public SauceLabsTwitterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isTwitterPageCorrect(String expectedurl){
        String currenturl = driver.getCurrentUrl();
        return expectedurl.equals(currenturl);
    }

}
