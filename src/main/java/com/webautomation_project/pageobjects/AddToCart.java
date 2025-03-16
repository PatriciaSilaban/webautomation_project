package com.webautomation_project.pageobjects;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCart {
    WebDriver driver;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public void goToCart() {
        cartButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
