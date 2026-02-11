package com.example;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class EcommerceCheckoutTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void loginAddToCartAndCheckout() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list")));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_list")));

        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assertions.assertEquals("Sauce Labs Backpack", cartItem.getText());

        driver.findElement(By.id("checkout")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_info")));
        driver.findElement(By.id("finish")).click();

        WebElement successHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        );
        Assertions.assertEquals("Thank you for your order!", successHeader.getText());
    }
}
