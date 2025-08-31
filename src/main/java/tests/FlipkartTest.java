package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.FlipkartHomePage;
import pages.FlipkartCartPage;
import io.qameta.allure.*;
import java.util.ArrayList;

@Epic("E-Commerce Module")
@Feature("Flipkart Shopping Flow")

public class FlipkartTest extends BaseTest {

    @Test
    @Story("Search and buy product on Flipkart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Search iPhone 15, open product in new tab, add to cart, remove it, return to homepage.")
    
    public void shoppingScenario() throws InterruptedException {
        FlipkartHomePage home = new FlipkartHomePage(driver);
        FlipkartCartPage cart = new FlipkartCartPage(driver);

        // Step 1: Open Flipkart
        home.open();
        home.closeLoginPopupIfPresent();

        // Step 2: Search iPhone 15
        home.search("iPhone 14");
        Thread.sleep(3000);

        // Step 3: Select first product
        System.out.println("Clicking first product...");
        home.selectFirstProduct();
        Thread.sleep(3000);

        // Step 4: Switch to new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println("Tabs found: " + tabs.size());

        if (tabs.size() > 1) {
        	 driver.switchTo().window(tabs.get(tabs.size() - 1));
            System.out.println("✅ Switched to product detail tab.");

            // Step 5: Add to cart
            cart.addToCart();
            Thread.sleep(6000);

            // Step 6: Open cart and remove product
            cart.openCart();
            Thread.sleep(6000);
            cart.removeFromCart();
            Thread.sleep(6000);

            // Step 7: Return to Flipkart home
            driver.switchTo().window(tabs.get(0));  // original tab
            home.returnToHome();
            Thread.sleep(2000);
        } else {
            System.out.println("❌ Product tab not opened. Skipping rest of test.");
        }

        // Step 8: Browser closes via @AfterMethod
    }
}
