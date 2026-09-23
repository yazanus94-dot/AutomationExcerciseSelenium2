package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(css = ".features_items .product-image-wrapper a")
    private WebElement firstProduct;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickOnRandomProduct() {
        // Wait for the product to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        
        // Use JavaScript to click, bypassing overlapping ads/iframes
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", firstProduct);
        
        return new ProductPage(driver);
    }
}