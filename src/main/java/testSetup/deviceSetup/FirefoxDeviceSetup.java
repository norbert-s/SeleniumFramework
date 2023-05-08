package testSetup.deviceSetup;


import org.openqa.selenium.firefox.FirefoxOptions;
import testSetup.setters.EnvironmentVariables;

public class FirefoxDeviceSetup {
    public static FirefoxOptions driverBuilder() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if(EnvironmentVariables.getHeadless()){
            firefoxOptions.setHeadless(true);
        }
        return firefoxOptions.addArguments("window-size=1920,1200");
    }
}



