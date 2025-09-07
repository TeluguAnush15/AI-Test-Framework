package utils;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    public static WebDriver createDriver() {
        WebDriver driver = null;

        String executionEnv = System.getProperty("env", "local"); // "local" or "cloud"
        System.out.println(">>> Environment: " + executionEnv);

        ChromeOptions options = new ChromeOptions();

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        try {
            if (executionEnv.equalsIgnoreCase("cloud")) {
                // Replace with your actual cloud hub URL (e.g., BrowserStack, LambdaTest)
                String hubURL = "https://<username>:<access_key>@hub.browserstack.com/wd/hub";

                driver = new RemoteWebDriver(new URL(hubURL), options);
                System.out.println(">>> Running on cloud");
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                System.out.println(">>> Running locally");
            }
        } catch (Exception e) {
            System.err.println("❌ Error initializing WebDriver: " + e.getMessage());
            e.printStackTrace();
        }

        return driver;
    }
}
