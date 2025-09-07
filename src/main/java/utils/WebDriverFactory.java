package utils;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

    public static void createDriver() {
        String executionEnv = System.getProperty("env", "local");
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
                // Replace with your actual cloud hub URL (e.g. BrowserStack / LambdaTest)
                String hubURL = "https://<username>:<access_key>@hub.browserstack.com/wd/hub";
                driver.set(new RemoteWebDriver(new URL(hubURL), options));
                System.out.println(">>> Running on cloud");
            } else {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver(options));
                System.out.println(">>> Running locally");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver: " + e.getMessage());
        }
    }
}
