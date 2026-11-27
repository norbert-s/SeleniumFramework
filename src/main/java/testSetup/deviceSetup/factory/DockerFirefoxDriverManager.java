package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DockerFirefoxDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() {
        wdm = WebDriverManager.firefoxdriver().browserInDocker();
        driver = wdm.create();
        return driver;
    }
}
