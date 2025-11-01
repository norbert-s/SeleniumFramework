package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DockerSafariDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager wdm = WebDriverManager.safaridriver().browserInDocker();
        driver = wdm.create();
        return driver;
    }
}
