package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class PaymentPage extends BasePage {

    @FindBy(name = "name_on_card")
    private WebElement nameOnCard;

    @FindBy(name = "card_number")
    private WebElement cardNumber;

    @FindBy(name = "cvc")
    private WebElement cvc;

    @FindBy(name = "expiry_month")
    private WebElement expiryMonth;

    @FindBy(name = "expiry_year")
    private WebElement expiryYear;

    @FindBy(id = "submit")
    private WebElement payButton;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public OrderConfirmationPage completePayment(String name, String card,
                                                  String cvv, String month, String year) {
        nameOnCard.sendKeys(name);
        cardNumber.sendKeys(card);
        cvc.sendKeys(cvv);
        expiryMonth.sendKeys(month);
        expiryYear.sendKeys(year);
        payButton.click();
        return new OrderConfirmationPage(driver);
    }
}