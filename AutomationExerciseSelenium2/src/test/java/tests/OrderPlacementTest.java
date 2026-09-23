package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.*;
import pages.*;
import utils.DriverFactory;

public class OrderPlacementTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    @Test
    public void placeOrderTest() {
        // STEP 2 & 3: Navigate directly to a specific product page.
        driver.get("https://automationexercise.com/product_details/1");
        
        ProductPage productPage = new ProductPage(driver);
        productPage.setQuantity("2");
        productPage.clickAddToCart();

        // Navigate to cart and proceed to checkout
        CartPage cartPage = productPage.clickViewCart();
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        // If checkoutPage is null, it means the Login popup appeared
        if (checkoutPage == null) {
            // We are on the Login page. Register a new user.
            LoginPage loginPage = new LoginPage(driver);
            loginPage.registerNewUser();
            
            // After registering, the website takes us to the Home page.
            // We need to go BACK to the cart to checkout.
            driver.get("https://automationexercise.com/view_cart");
            
            // Now we are logged in, so clicking checkout will go straight to the Checkout page
            CartPage cartPageAfterLogin = new CartPage(driver);
            checkoutPage = cartPageAfterLogin.clickProceedToCheckout();
        }

        // Now that we are finally on the Checkout page, we can Place Order!
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

        OrderConfirmationPage confirmationPage = paymentPage.completePayment(
                "Test User", "123456789", "100", "12", "2025");

        String message = confirmationPage.getConfirmationMessage();
        System.out.println("Order Confirmation: " + message);

        // UPDATED ASSERTION LINE: The website actually says "ORDER PLACED!"
        Assert.assertTrue(message.contains("ORDER PLACED"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // This main method bypasses Eclipse's broken TestNG GUI runner
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { OrderPlacementTest.class });
        testng.run();
    }
}