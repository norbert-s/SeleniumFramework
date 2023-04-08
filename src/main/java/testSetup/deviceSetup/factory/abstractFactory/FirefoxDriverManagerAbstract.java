package testSetup.deviceSetup.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import testSetup.deviceSetup.FirefoxDeviceSetup;

public class FirefoxDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() throws Exception {
        driver = FirefoxDeviceSetup.driverBuilder();
    }
}
