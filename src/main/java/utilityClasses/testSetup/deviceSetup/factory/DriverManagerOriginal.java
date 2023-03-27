package utilityClasses.testSetup.deviceSetup.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilityClasses.testSetup.constants.DriverType;
import utilityClasses.testSetup.deviceSetup.ChromeDeviceSetup;

public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser) throws Exception {
        WebDriver driver;
        switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                //WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = ChromeDeviceSetup.driverBuilder();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browser);
        }
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
