package testSetup.deviceSetup.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import testSetup.deviceSetup.EdgeDeviceSetup;
import testSetup.setters.GlobalSettingsGetterMethods;

public class EdgeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() throws Exception {
        driver = EdgeDeviceSetup.driverBuilder();
    }
}
