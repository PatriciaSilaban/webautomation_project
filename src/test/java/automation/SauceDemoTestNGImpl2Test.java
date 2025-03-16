package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.pageobjects.*;

import java.time.Duration;

public class SauceDemoTestNGImpl2Test {
    WebDriver driver;
    LandingPage landingPage;
    ProductListPage productListPage;
    AddToCart cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/afterofficebootcamp/webautomationproject/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] { { "standard_user", "secret_sauce" } };
    }

    @Test(dataProvider = "loginData")
    public void testSauceDemoE2E(String username, String password) throws InterruptedException {
        // Login
        landingPage = new LandingPage(driver);
        landingPage.login(username, password);

        // List Product
        productListPage = new ProductListPage(driver);
        productListPage.addToCart("sauce-labs-backpack");

        // Add Product to cart
        cartPage = new AddToCart(driver);
        cartPage.goToCart();
        cartPage.proceedToCheckout();

        // Checkout
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutDetails("Patricia", "Silaban", "12345");
        checkoutPage.completeCheckout();

        // Confirmation Page
        confirmationPage = new ConfirmationPage(driver);
        String confirmationText = confirmationPage.getConfirmationText();
        Assert.assertEquals(confirmationText, "Thank you for your order!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}