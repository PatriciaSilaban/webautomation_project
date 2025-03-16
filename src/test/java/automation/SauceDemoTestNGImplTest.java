package automation;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceDemoTestNGImplTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Setup Driver
        System.setProperty("webdriver.chrome.driver", "C:/afterofficebootcamp/webautomationproject/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(dataProvider = "dataTestMapping")
    public void checkoutProcess(HashMap<String, String> input) throws InterruptedException {
        // Login
        driver.findElement(By.id("user-name")).sendKeys(input.get("userName"));
        driver.findElement(By.id("password")).sendKeys(input.get("password"));
        driver.findElement(By.id("login-button")).click();

        // Verifikasi login sukses
        Assert.assertTrue(driver.findElement(By.className("app_logo")).isDisplayed(), "Login gagal!");

        // Menampilkan List Produk
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        System.out.println("Daftar Produk yang Tersedia:");
        for (WebElement product : products) {
            System.out.println("- " + product.getText());
        }

        // Tambah produk ke keranjang
        driver.findElement(By.id("add-to-cart-" + input.get("productId"))).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);

        // Checkout
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("first-name")).sendKeys(input.get("firstName"));
        driver.findElement(By.id("last-name")).sendKeys(input.get("lastName"));
        driver.findElement(By.id("postal-code")).sendKeys(input.get("postalCode"));
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);

        // Verifikasi checkout sukses
        String confirmationText = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(confirmationText, "Thank you for your order!", "Checkout gagal!");
        System.out.println("Checkout berhasil: " + confirmationText);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @DataProvider
    public Object[][] dataTestMapping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "standard_user");
        map.put("password", "secret_sauce");
        map.put("productId", "sauce-labs-backpack");
        map.put("firstName", "Patricia");
        map.put("lastName", "Silaban");
        map.put("postalCode", "12345");

        return new Object[][] { { map } };
    }
}
