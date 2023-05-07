package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;
import testSetup.setters.SettingUpTimeouts;

import java.net.URL;

public class ChromeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
//        wdm  = WebDriverManager.chromedriver().capabilities(ChromeDeviceSetup.settingUpDevices()).browserInDocker();
//        driver= wdm.create();
//        driver = new ChromeDriver(ChromeDeviceSetup.settingUpDevices());
//        String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();
//        System.out.println(chromeDriverPath);
//        return driver;
        String wdmServerUrl = "http://localhost:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(wdmServerUrl), ChromeDeviceSetup.settingUpDevices());
        return driver;
    }
}