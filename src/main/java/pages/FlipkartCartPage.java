package pages;

import java.time.Duration;
import io.qameta.allure.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartCartPage {
    WebDriver driver;

    By addToCartButton = By.xpath("//button[text()='Add to cart']");
    By cartIcon = By.xpath("//a[@href='/viewcart?otracker=Cart_Icon_Click']");
    By removeButton = By.xpath("//div[contains(text(), 'Remove') and @role='button']");
    By confirmRemoveButton = By.xpath("//div[@class='_3dsJAO _24d-qY FhkMJZ']");


    public FlipkartCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    
    //public
//    public void removeFromCart() {
//        driver.findElement(removeButton).click();
//        driver.findElement(confirmRemoveButton).click();
    public void removeFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            System.out.println("⏳ Waiting for remove button...");
            WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            remove.click();
            System.out.println("🗑️ Clicked remove.");

            WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(confirmRemoveButton));
            confirm.click();
            System.out.println("✅ Confirmed remove.");
        } catch (Exception e) {
            System.out.println("❌ Failed to remove product from cart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}


