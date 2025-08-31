package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.OrangeLoginPage;
import io.qameta.allure.*;
import pages.OrangeAdminPage;

@Epic("OrangeHRM Module")
@Feature("Admin Panel - User Search")

public class OrangeHRMTest extends BaseTest {
	
	  @Test
	  @Story("Search user by name and role")
	  @Severity(SeverityLevel.CRITICAL)
	  @Description("Logs in, navigates to Admin panel, searches for a user by username and role")

    public void testSearchInAdminPage() throws InterruptedException {
        OrangeLoginPage loginPage = new OrangeLoginPage(driver);
        OrangeAdminPage adminPage = new OrangeAdminPage(driver);

        // Step 1: Open login page and log in
        loginPage.open();
        loginPage.login("Admin", "admin123");
        Thread.sleep(4000); // Wait for dashboard to load

        // Step 2: Go to Admin page and search
        adminPage.goToAdminPage();
        Thread.sleep(2000);
        adminPage.searchUser("Admin", "ESS");
        Thread.sleep(3000);

        System.out.println("✅ OrangeHRM search test completed.");
    }
}
