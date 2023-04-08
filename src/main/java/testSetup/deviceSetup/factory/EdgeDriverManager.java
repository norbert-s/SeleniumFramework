package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;

public class EdgeDriverManager implements DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {

        return EdgeDeviceSetup.driverBuilder();
    }
}
