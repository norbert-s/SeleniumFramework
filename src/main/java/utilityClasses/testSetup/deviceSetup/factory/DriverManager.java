package utilityClasses.testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;

public interface DriverManager {
    WebDriver createDriver() throws Exception;
}
