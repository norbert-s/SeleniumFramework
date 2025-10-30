package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import testSetup.deviceSetup.FirefoxDeviceSetup;
import testSetup.setters.EnvironmentVariables;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        if (EnvironmentVariables.getHeadless()) {
            driver = WebDriverManager.firefoxdriver().capabilities(FirefoxDeviceSetup.driverBuilder().addArguments("--headless")).create();
        } else {
            driver = WebDriverManager.firefoxdriver().create();
        }
        return driver;
    }
}
