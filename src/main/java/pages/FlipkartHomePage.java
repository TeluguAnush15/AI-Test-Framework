package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import io.qameta.allure.*;
public class FlipkartHomePage {
    WebDriver driver;

    // Locators
    By closePopup = By.xpath("//button[contains(text(),'✕')]");
    By searchBox = By.name("q");
    By searchButton = By.xpath("//button[@type='submit']");
    By productLinks = By.xpath("//div[@class='KzDlHZ']");
    By flipkartLogo = By.xpath("//img[@title='Flipkart']");

    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.flipkart.com");
    }

    public void closeLoginPopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(closePopup)).click();
        } catch (Exception e) {
            System.out.println("Popup not present");
        }
    }

    public void search(String query) {
        driver.findElement(searchBox).sendKeys(query);
        driver.findElement(searchButton).click();
    }

    
    public void selectFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //By productLinks = By.xpath("//a[@class='_1fQZEK' or @class='s1Q9rs']");
   

        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productLinks));
        System.out.println("🛍️ Products found: " + products.size());

        if (products.size() > 0) {
            System.out.println("✅ Found " + products.size() + " products");
            products.get(0).click(); // click on the first product
        } else {
            System.out.println("❌ No products found.");
        }}

    public void returnToHome() 
    {
        driver.get("https://www.flipkart.com");
    }

    
}
