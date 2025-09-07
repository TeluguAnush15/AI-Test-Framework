package utils;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    // ThreadLocal to handle WebDriver instances across different threads (for parallel test execution)
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();  // Retrieve the current WebDriver instance
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);  // Set the WebDriver instance to ThreadLocal
    }

    public static void removeDriver() {
        driver.get().quit();  // Quit the WebDriver session
        driver.remove();  // Remove the WebDriver instance from ThreadLocal
    }

    // Method to create WebDriver (either local or cloud)
    public static void createDriver() {
        // Get environment property (default to "local")
        String executionEnv = System.getProperty("env", "local");
        System.out.println(">>> Environment: " + executionEnv);

        // Set ChromeOptions for ChromeDriver (headless mode, no-sandbox, etc.)
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        try {
            // If running in cloud (like BrowserStack or LambdaTest)
            if (executionEnv.equalsIgnoreCase("cloud")) {
                // Fetch cloud credentials (BrowserStack username and access key from environment variables)
                String username = System.getenv("BROWSERSTACK_USERNAME");
                String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
                
                if (username == null || accessKey == null) {
                    throw new RuntimeException("Cloud credentials not found! Ensure environment variables are set.");
                }

                // Construct the URL for cloud service (BrowserStack, LambdaTest, etc.)
                String hubURL = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";  // Modify for other cloud services if needed

                // Initialize RemoteWebDriver for BrowserStack (or other cloud service)
                driver.set(new RemoteWebDriver(new URL(hubURL), options));
                System.out.println("BROWSERSTACK_USERNAME: " + System.getenv("BROWSERSTACK_USERNAME"));
                System.out.println("BROWSERSTACK_ACCESS_KEY: " + System.getenv("BROWSERSTACK_ACCESS_KEY"));
                System.out.println(">>> Running on cloud (BrowserStack or other cloud service)");

            } else {
                // If running locally, use WebDriverManager to set up ChromeDriver
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(options));
                System.out.println(">>> Running locally");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver: " + e.getMessage());
        }
    }
}
