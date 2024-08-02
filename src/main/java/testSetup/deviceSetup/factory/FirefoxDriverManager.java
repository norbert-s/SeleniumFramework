package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        driver = WebDriverManager.firefoxdriver().create();
        return driver;
    }
}
