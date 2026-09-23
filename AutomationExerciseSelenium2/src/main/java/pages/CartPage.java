package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class CartPage extends BasePage {

    @FindBy(css = "a.check_out")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".modal-body a[href='/login']")
    private WebElement registerLoginLink;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickProceedToCheckout() {
        proceedToCheckoutButton.click();
        
        // If the login popup appears, click the login link and return null
        try {
            if (registerLoginLink.isDisplayed()) {
                registerLoginLink.click();
                return null; // We are now on the Login page, not Checkout
            }
        } catch (Exception e) {
            // Popup didn't appear, we are already logged in
        }
        return new CheckoutPage(driver);
    }
}