package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;
import testSetup.deviceSetup.FirefoxDeviceSetup;

import java.net.URL;

public class FirefoxDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
//        driver = WebDriverManager.firefoxdriver().capabilities(FirefoxDeviceSetup.driverBuilder()).browserInDocker().create();
        String wdmServerUrl = "http://localhost:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(wdmServerUrl), FirefoxDeviceSetup.driverBuilder());
        return driver;
    }
}
