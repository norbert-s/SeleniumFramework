package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.SafariDeviceSetup;

public class SafariDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        wdm = WebDriverManager.safaridriver().capabilities(SafariDeviceSetup.driverBuilder());
        driver = wdm.create();
        return driver;
    }
}
