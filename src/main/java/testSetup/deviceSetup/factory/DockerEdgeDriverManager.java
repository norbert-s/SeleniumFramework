package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DockerEdgeDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager wdm = WebDriverManager.edgedriver().browserInDocker();
        driver = wdm.create();
        return driver;
    }
}
