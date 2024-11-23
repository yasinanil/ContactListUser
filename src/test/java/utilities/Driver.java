package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    } // Prevents creating an object of this class from other classes using the 'new' keyword.

    public static WebDriver getDriver() {

        if (driver == null) { // If driver is null (not created yet), create a new driver instance.

            String browser = ConfigReader.getProperty("browser"); // Retrieves the 'browser' value from the configuration.properties file as a String.

            switch (browser) { // Opens the corresponding browser based on the value from configuration.properties.

                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "headless":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                default:
                    driver = new ChromeDriver(); // Default to Chrome if no matching value is found.

            }

        }

        driver.manage().window().maximize(); // Maximizes the browser window.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Sets an implicit wait timeout of 10 seconds.

        return driver;
    }

    public static void closeDriver() {

        try {
            Thread.sleep(3000); // Waits for 3 seconds.
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // Throws a runtime exception if the thread is interrupted.
        }

        if (driver != null) { // If driver is not null (already created), call the quit() method.
            driver.quit(); // Closes all browser windows and terminates the WebDriver session.
            driver = null; // Resets the driver to null to allow the getDriver() method to work again.
        }

    }

}