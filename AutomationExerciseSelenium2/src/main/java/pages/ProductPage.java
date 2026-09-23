package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(id = "quantity")
    private WebElement quantityInput;

    @FindBy(css = "button.btn.btn-default.cart")
    private WebElement addToCartButton;

    @FindBy(css = ".modal-body a[href='/view_cart']")
    private WebElement viewCartLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage setQuantity(String qty) {
        // Wait up to 10 seconds for the quantity box to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(quantityInput));
        
        quantityInput.clear();
        quantityInput.sendKeys(qty);
        return this;
    }

    public ProductPage clickAddToCart() {
        addToCartButton.click();
        return this;
    }

    public CartPage clickViewCart() {
        // Wait for the modal popup to appear, then click the link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(viewCartLink));
        viewCartLink.click();
        return new CartPage(driver);
    }
}