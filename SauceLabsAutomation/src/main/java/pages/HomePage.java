package pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement sortComboBox;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleIsDisplayed(){
        boolean pageTitleIsDisplayed= pageTitle.isDisplayed();
        return pageTitleIsDisplayed;
    }

    public void selectSortComboBox(String option){
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();

        for( WebElement element: products){
            actualProductNames.add(element.getText());
        }

        boolean isSorted = Ordering.natural().reverse().isOrdered(actualProductNames);

        if(isSorted){
            return true;
        } else {
            return false;
        }

    }

    public void addProductToCart(String productName){
        //sauce-labs-fleece-jacket
        // id button = add-to-cart-sauce-labs-fleece-jacket
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;

        WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        addToCartButton.click();
    }

    //SD-21
    public boolean isProductSelected(String productName){
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String removeButtonId = "remove-";
        removeButtonId = removeButtonId + productNameLowerCase;

        boolean isSelected  = (driver.findElements(By.id(removeButtonId)).size() != 0);
        return isSelected;
    }

    public void clickOnShoppingCartButton(){
        shoppingCartButton.click();
    }

    public void clickOnBurgerButton(){
        burgerButton.click();
    }

    public void clickOnLogoutLink(){
        WebElement logoutLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutLink.click();
    }

    //SD-22
    public void clickOnTwitterLink(){
        WebElement twitterLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Twitter")));

        twitterLink.click();
    }

}
