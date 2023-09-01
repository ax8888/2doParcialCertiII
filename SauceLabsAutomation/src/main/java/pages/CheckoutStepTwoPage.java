package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutStepTwoPage {

    WebDriver driver;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> priceTags;

    @FindBy(css = "div.summary_value_label:nth-child(2)")
    WebElement paymentInformation;

    @FindBy(css = "div.summary_value_label:nth-child(4)")
    WebElement shippingInformation;

    @FindBy(className = "summary_subtotal_label")
    WebElement summaryTotal;

    public CheckoutStepTwoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public void clickFinishButton(){
        finishButton.click();
    }

    public boolean isProductDisplayed(String product){
        for (WebElement element: productNames) {
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isPriceDisplayed(String product){
        for (WebElement element: priceTags) {
            System.out.println(element.getText());
            if(element.getText().equals(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isPaymentInformationCorrect(String expectedPaymentInformation){
        String actualPaymentInformation = paymentInformation.getText();
        return actualPaymentInformation.equals(expectedPaymentInformation);
    }

    public boolean isShippingInformationCorrect(String expectedShippingInformation){
        String actualShippingInformation = shippingInformation.getText();
        return actualShippingInformation.equals(expectedShippingInformation);
    }

    public double getTotal(){
        double total = 0;
        String priceText;
        Double priceNumber;
        for(WebElement price : priceTags){
            priceText = price.getText().replaceAll("[^\\d.]", "");
            priceNumber = Double.parseDouble(priceText);
            total += priceNumber;
        }
        return total;
    }

    public boolean isTotalCorrect(){
        String actualTotal = summaryTotal.getText();
        double calculatedTotal = getTotal();
        String expectedTotal = "Item total: $" + String.format("%.2f", calculatedTotal);
        return actualTotal.equals(expectedTotal);
    }

}
