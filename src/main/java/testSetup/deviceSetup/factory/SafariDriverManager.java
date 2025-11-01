package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.SafariDeviceSetup;

public class SafariDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
//        if (EnvironmentVariables.getHeadless()) {
//            driver = WebDriverManager.safaridriver().capabilities(SafariDeviceSetup.driverBuilder().setCapability("headless",true)).;
//        } else {
//            driver = WebDriverManager.firefoxdriver().create();
//        }
//        return driver;
        return WebDriverManager.safaridriver().capabilities(SafariDeviceSetup.driverBuilder()).create();
    }
}
