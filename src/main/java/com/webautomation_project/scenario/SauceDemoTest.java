package com.webautomation_project.scenario;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemoTest {
    public static void main(String[] args) throws InterruptedException {
        // Setup Driver
        System.setProperty("webdriver.chrome.driver", "C:/afterofficebootcamp/webautomationproject/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Scenario Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(4000);

        // Menampilkan List Product
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));

        System.out.println("Daftar Produk yang Tersedia:");
        for (WebElement product : products) {
            System.out.println("- " + product.getText());
        }

        // Checkout Product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("first-name")).sendKeys("Patricia");
        driver.findElement(By.id("last-name")).sendKeys("Silaban");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("finish")).click();
        System.out.println("Checkout berhasil!");

        Thread.sleep(2000);

        String confirmationPage = driver.findElement(By.className("complete-header")).getText();

        System.out.println("Buyer berhasil checkout " + confirmationPage);

        Thread.sleep(3000);

        driver.close();
    }
}
