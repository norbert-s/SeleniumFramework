package testSetup.deviceSetup;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDeviceSetup {
    public static WebDriver driverBuilder() throws Exception {


//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//
//        if(GlobalSettingsGetterMethods.getHeadless()){
//            firefoxOptions.setHeadless(true);
//        }
//
//        firefoxOptions.addArguments("window-size=1920,1200");
//
//        return WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}



