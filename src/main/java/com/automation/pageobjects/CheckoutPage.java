package com.automation.pageobjects;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    public void enterCheckoutDetails(String fName, String lName, String pCode) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postalCode.sendKeys(pCode);
        continueButton.click();
    }

    public void completeCheckout() {
        finishButton.click();
    }
}