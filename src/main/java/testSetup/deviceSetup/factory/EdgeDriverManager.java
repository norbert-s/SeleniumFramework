package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import testSetup.deviceSetup.ChromeDeviceSetup;
import testSetup.deviceSetup.EdgeDeviceSetup;

import java.net.URL;

public class EdgeDriverManager extends DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {

//        WebDriverManager wdm = WebDriverManager.edgedriver().capabilities(EdgeDeviceSetup.driverBuilder()).browserInDocker();
//        driver = wdm.create();
        String wdmServerUrl = "http://localhost:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(wdmServerUrl), EdgeDeviceSetup.driverBuilder());
        return driver;
    }

}
