package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import testSetup.deviceSetup.FirefoxDeviceSetup;

public class FirefoxDriverManager implements DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        return FirefoxDeviceSetup.driverBuilder();
    }
}
