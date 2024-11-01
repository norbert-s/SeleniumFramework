package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


public abstract class DriverManager {
    protected WebDriver driver;
    protected WebDriverManager wdm;

    protected abstract WebDriver createDriver() throws Exception;

    public void quitDriver() {
        if (wdm != null) {
            wdm.quit();
        }
        if (driver != null) {

            driver.quit();
        }
    }

    public WebDriver getDriver() throws Exception {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }
}

