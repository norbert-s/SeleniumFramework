package testSetup.deviceSetup;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import testSetup.setters.GlobalSettingsGetterMethods;

public class FirefoxDeviceSetup {
    public static WebDriver driverBuilder() throws Exception {


        FirefoxOptions firefoxOptions = new FirefoxOptions();
//
        if(GlobalSettingsGetterMethods.getHeadless()){
            firefoxOptions.setHeadless(true);
        }

        firefoxOptions.addArguments("window-size=1920,1200");
//

        WebDriver driver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).browserInDocker().create();

        return driver;
    }
}



