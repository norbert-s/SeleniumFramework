package testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;

public class DockerEdgeDriverManager extends DriverManager {
    @Override
    public WebDriver createDriver() throws Exception {
        wdm = WebDriverManager.edgedriver()
                .browserInDocker()
                .capabilities(EdgeDeviceSetup.driverBuilder());
        driver = wdm.create();
        return driver;
    }
}
