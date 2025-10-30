package testSetup.deviceSetup.base;

import org.openqa.selenium.WebDriver;

/**
 * Small static provider to expose the current thread's WebDriver to listeners and helpers
 * without requiring access to test instance methods.
 */
public final class DriverProvider {
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    private DriverProvider() {}

    public static void setDriver(WebDriver driver) {
        THREAD_DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return THREAD_DRIVER.get();
    }

    public static void remove() {
        THREAD_DRIVER.remove();
    }
}

