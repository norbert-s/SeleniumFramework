package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DockerChromeDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker();
        driver = wdm.create();
        return driver;
    }
}
