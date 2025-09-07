package pages;

  import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.*;

public class OrangeLoginPage {
    WebDriver driver;

    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginBtn = By.cssSelector("button[type='submit']");

    public OrangeLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
    }
}
