package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OrangeLoginPage;
import pages.OrangeAdminPage;
import utils.WebDriverFactory;
import io.qameta.allure.*;

@Epic("OrangeHRM Module")
@Feature("Admin Panel - User Search")
public class OrangeHRMTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createDriver(); // Initializes ThreadLocal driver
        driver = WebDriverFactory.getDriver(); // Fetch current thread's driver
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.removeDriver(); // Clean up driver after test
    }

    @Test
    @Story("Search user by name and role")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in, navigates to Admin panel, searches for a user by username and role")
    public void testSearchInAdminPage() {
        // Step 1: Open login page and log in
        OrangeLoginPage loginPage = new OrangeLoginPage(driver);
        loginPage.open();
        loginPage.login("Admin", "admin123");

        // Step 2: Go to Admin page and search
        OrangeAdminPage adminPage = new OrangeAdminPage(driver);
        adminPage.goToAdminPage();
        adminPage.searchUser("Admin", "ESS");

        System.out.println("✅ OrangeHRM search test completed.");
    }
}
