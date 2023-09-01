package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class YourCartPage {
    WebDriver driver;
    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> priceTags;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCart;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String product){
        for (WebElement element: productNames) {
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public void removeProduct(String product){
        //sauce-labs-bike-bight
        //remove-
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }

    //exam functions
    public boolean isNumberofElementsEqualtoDisplayed(){
        int expectedNumberofElements = productNames.size();
        int actualNumberofElements = Integer.parseInt(shoppingCart.getText());
        return actualNumberofElements == expectedNumberofElements;
    }

    public boolean isPriceDisplayed(String product){
        //System.out.println();

        for (WebElement element: priceTags) {
            if(element.getText().equals(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isShoppingCartEmpty(){
        boolean result;

        result = !(driver.findElements(By.className("shopping_cart_badge")).size() != 0);

        return result;

    }

    //end exam functions

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }


}
