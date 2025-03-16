package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.pageobjects.*;

import hook.Hooks;
import io.cucumber.java.en.*;

public class StepDefinitionsSauceDemo {

    WebDriver driver;
    LandingPage landingPage;
    ProductListPage productListPage;
    AddToCart cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @Given("Buyer navigates to SauceDemo login page")
    public void buyer_navigates_to_sauce_demo_login_page() {
        driver = Hooks.initializeDriver();
        landingPage = new LandingPage(driver);
        driver.get("https://www.saucedemo.com/");
    }

    @Given("Buyer logged to website")
    public void buyer_logged_to_website() {
        landingPage.login("standard_user", "secret_sauce");
    }

    @When("Buyer add a product to the cart")
    public void buyer_add_a_product_to_the_cart() {
        productListPage = new ProductListPage(driver);
        productListPage.addToCart("sauce-labs-backpack");
    }

    @When("Buyer proceeds to checkout")
    public void buyer_proceeds_to_checkout() {
        cartPage = new AddToCart(driver);
        cartPage.goToCart();
        cartPage.proceedToCheckout();
    }

    @When("Buyer completes the order")
    public void buyer_completes_the_order() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutDetails("Patricia", "Silaban", "12345");
        checkoutPage.completeCheckout();
    }

    @Then("Buyer should see the confirmation message {string}")
    public void buyer_should_see_the_confirmation_message(String expectedMessage) {
        confirmationPage = new ConfirmationPage(driver);
        String confirmationText = confirmationPage.getConfirmationText();
        Assert.assertEquals(confirmationText, expectedMessage);
    }
}
