package testSetup.deviceSetup.base;

import org.openqa.selenium.WebDriver;


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

