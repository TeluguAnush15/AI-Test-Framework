package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import io.qameta.allure.Step;

public class OrangeLoginPage {
    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginBtn = By.cssSelector("button[type='submit']");

    public OrangeLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Opening login page")
    public void open() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    @Step("Logging in with username: {0} and password: {1}")
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
    }
}
