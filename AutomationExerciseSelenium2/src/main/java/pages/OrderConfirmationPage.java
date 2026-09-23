package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class OrderConfirmationPage extends BasePage {

    @FindBy(css = "h2[data-qa='order-placed']")
    private WebElement confirmationMessage;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}