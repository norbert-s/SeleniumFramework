package testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;

public class EdgeDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        driver = EdgeDeviceSetup.driverBuilder();
        return driver;
    }

}
