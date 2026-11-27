package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


public abstract class DriverManager {
    protected WebDriver driver;
    protected WebDriverManager wdm;

    protected abstract WebDriver createDriver() throws Exception;

    public void quitDriver() {
        RuntimeException failure = null;
        try {
            if (wdm != null) {
                wdm.quit();
            }
        } catch (RuntimeException e) {
            failure = e;
        }
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (RuntimeException e) {
            if (failure == null) {
                failure = e;
            } else {
                failure.addSuppressed(e);
            }
        } finally {
            driver = null;
            wdm = null;
        }
        if (failure != null) {
            throw failure;
        }
    }

    public WebDriver getDriver() throws Exception {
        if (driver == null) {
            driver = createDriver();
            if (driver == null) {
                throw new IllegalStateException("Driver creation returned null");
            }
        }
        return driver;
    }
}
