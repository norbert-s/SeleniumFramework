package testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        driver = ChromeDeviceSetup.driverBuilder();
        return driver;
    }
}
