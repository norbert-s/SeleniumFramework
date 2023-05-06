package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;
import testSetup.setters.SettingUpTimeouts;

public class ChromeDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
        wdm  = WebDriverManager.chromedriver().capabilities(ChromeDeviceSetup.settingUpDevices()).browserInDocker();
        driver= wdm.create();

        return driver;
    }


}
