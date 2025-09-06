package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import io.qameta.allure.*;

import java.time.Duration;

//PUBLIC

public class OrangeAdminPage {
    WebDriver driver;

  //  By adminTab = By.xpath("//span[text()='Admin']");
    By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
    By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')]");
    By userRoleOption = By.xpath("//div[@role='listbox']//span[text()='ESS']"); // or 'Admin'
    By searchBtn = By.xpath("//button[@type='submit']");

    public OrangeAdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAdminPage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();

    }

    public void searchUser(String username, String role) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);

        driver.findElement(userRoleDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='" + role + "']"))).click();

        driver.findElement(searchBtn).click();
    }
}
