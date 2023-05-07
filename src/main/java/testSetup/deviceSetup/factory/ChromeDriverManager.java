package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;
import testSetup.setters.GlobalSettingsGetterMethods;
import testSetup.setters.SettingUpTimeouts;

import java.net.URL;
@Slf4j
public class ChromeDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() throws Exception {
//        wdm  = WebDriverManager.chromedriver().capabilities(ChromeDeviceSetup.settingUpDevices()).browserInDocker();
//        driver= wdm.create();
//        driver = new ChromeDriver(ChromeDeviceSetup.settingUpDevices());
//        String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();
//        System.out.println(chromeDriverPath);
//        return driver;
        String hubUrl="http://localhost:4444/wd/hub";
        if(!GlobalSettingsGetterMethods.getGridHub().equalsIgnoreCase("localhost")){
            hubUrl = String.format("http://%s:4444/wd/hub", GlobalSettingsGetterMethods.getGridHub());
        }
        else {
            System.out.println("grid hub is nul!!!!!!!!!!!!!!");
        }
        log.info("grid hub is: "+hubUrl);
        driver = new RemoteWebDriver(new URL(hubUrl), ChromeDeviceSetup.settingUpDevices());
        return driver;
    }
}