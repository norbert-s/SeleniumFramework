package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
        return WebDriverManager.chromedriver().capabilities(ChromeDeviceSetup.settingUpDevices()).create();
    }
}
