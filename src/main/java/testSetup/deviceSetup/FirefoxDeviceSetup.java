package testSetup.deviceSetup;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import testSetup.setters.GlobalSettingsGetterMethods;

public class FirefoxDeviceSetup {
    public static FirefoxOptions driverBuilder() throws Exception {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if(GlobalSettingsGetterMethods.getHeadless()){
            firefoxOptions.setHeadless(true);
        }
        return firefoxOptions.addArguments("window-size=1920,1200");
    }
}



