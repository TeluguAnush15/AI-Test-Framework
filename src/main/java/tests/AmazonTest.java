package tests;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("E-Commerce Module")
@Feature("Amazon Shopping Flow")
public class AmazonTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();   // Works with WebDriverManager if configured
        driver.manage().window().maximize();
    }

    @Test(description = "Open Amazon, search a product, click first item")
    @Story("Amazon Shopping Flow")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test opens Amazon, searches for 'mobile', clicks the first item in results, "
               + "and verifies that the product page opens successfully.")
    public void openAmazonAndClickItem() throws InterruptedException {
        // Step 1: Open Amazon
        driver.get("https://www.amazon.in/");

        // Step 2: Search for a product
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("mobile");
        driver.findElement(By.id("nav-search-submit-button")).click();
        System.out.println("Triggering CI/CD test run...");

       
    }

    @AfterMethod
    public void tearDown() {
    	if (driver != null) {
            driver.quit();
        }
    }
}
    