package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {

        wdm = WebDriverManager.chromedriver().driverVersion("126").capabilities(ChromeDeviceSetup.settingUpDevices());

        driver = wdm.create();

        return driver;

    }
}
