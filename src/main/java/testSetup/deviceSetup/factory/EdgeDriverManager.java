package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;

public class EdgeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        return WebDriverManager.edgedriver().capabilities(EdgeDeviceSetup.driverBuilder()).create();
    }

}
