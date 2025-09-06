package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WebDriverFactory;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.createDriver();   // ✅ use factory now
        driver.manage().window().maximize();
    }
 // Trigger CI workflow test
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
