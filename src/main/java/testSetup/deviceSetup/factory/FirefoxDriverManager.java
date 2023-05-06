package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.FirefoxDeviceSetup;

public class FirefoxDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        driver = WebDriverManager.firefoxdriver().capabilities(FirefoxDeviceSetup.driverBuilder()).browserInDocker().create();
        return driver;
    }
}
