package testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.FirefoxDeviceSetup;

public class FirefoxDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        driver = FirefoxDeviceSetup.driverBuilder();
        return driver;
    }
}
