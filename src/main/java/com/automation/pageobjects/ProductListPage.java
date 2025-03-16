package com.automation.pageobjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
    WebDriver driver;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    List<WebElement> products;

    public List<WebElement> getProductList() {
        return products;
    }

    public void addToCart(String productId) {
        driver.findElement(By.id("add-to-cart-" + productId)).click();
    }
}
