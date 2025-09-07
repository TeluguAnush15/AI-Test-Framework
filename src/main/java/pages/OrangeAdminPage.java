package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import io.qameta.allure.Step;

public class OrangeAdminPage {
    private WebDriver driver;

    private By adminTab = By.xpath("//span[text()='Admin']");
    private By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')]");
    private By searchBtn = By.xpath("//button[@type='submit']");

    public OrangeAdminPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigating to Admin Page")
    public void goToAdminPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(adminTab))
            .click();
    }

    @Step("Searching for user with username: {0} and role: {1}")
    public void searchUser(String username, String role) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
            .sendKeys(username);

        driver.findElement(userRoleDropdown).click();

        // Use dynamic XPath for role
        By roleOption = By.xpath("//div[@role='listbox']//span[text()='" + role + "']");
        wait.until(ExpectedConditions.elementToBeClickable(roleOption)).click();

        driver.findElement(searchBtn).click();
    }
}
