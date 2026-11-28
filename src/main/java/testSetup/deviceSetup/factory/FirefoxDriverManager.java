package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.FirefoxDeviceSetup;
import testSetup.setters.EnvironmentVariables;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        wdm = WebDriverManager.firefoxdriver();
        if (EnvironmentVariables.getHeadless()) {
            wdm.capabilities(FirefoxDeviceSetup.driverBuilder().addArguments("--headless"));
        }
        driver = wdm.create();
        return driver;
    }
}
