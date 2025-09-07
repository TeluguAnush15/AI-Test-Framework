package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createDriver();
        driver = WebDriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.removeDriver();
    }
}
