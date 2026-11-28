package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;

public class DockerChromeDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() throws Exception {
        wdm = WebDriverManager.chromedriver()
                .browserInDocker()
                .capabilities(ChromeDeviceSetup.settingUpDevices());
        driver = wdm.create();
        return driver;
    }
}
