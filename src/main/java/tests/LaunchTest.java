package tests;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

@Epic("Sanity Check Module")
@Feature("Browser Launch Test")

public class LaunchTest {
	 @Test
	    @Story("Open browser and load Google homepage")
	    @Severity(SeverityLevel.NORMAL)
	    @Description("Launches Chrome browser, opens Google.com, prints page title, and closes browser.")
	    public void openGoogle() {
      
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}