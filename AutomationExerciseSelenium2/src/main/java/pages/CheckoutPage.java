package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class CheckoutPage extends BasePage {

    @FindBy(css = "a[href='/payment']")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage clickPlaceOrder() {
        placeOrderButton.click();
        return new PaymentPage(driver);
    }
}