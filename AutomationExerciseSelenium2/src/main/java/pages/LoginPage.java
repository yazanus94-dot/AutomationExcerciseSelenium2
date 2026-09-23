package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import java.time.Duration;

public class LoginPage extends BasePage {

    // Signup fields
    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement signupName;

    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement signupEmail;

    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signupButton;

    // Detailed account info
    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumber;

    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

    @FindBy(css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void registerNewUser() {
        // 1. Fill out initial signup form
        signupName.sendKeys("Test User");
        signupEmail.sendKeys("testuser" + System.currentTimeMillis() + "@test.com");
        signupButton.click();

        // 2. Fill out detailed form
        password.sendKeys("Password123");
        firstName.sendKeys("Test");
        lastName.sendKeys("User");
        address1.sendKeys("123 Test Street");
        state.sendKeys("Test State");
        city.sendKeys("Test City");
        zipcode.sendKeys("12345");
        mobileNumber.sendKeys("1234567890");
        createAccountButton.click();

        // 3. Wait for the 'Continue' button to appear, then click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }
}