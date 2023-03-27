package utilityClasses.testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;
import utilityClasses.testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManager implements DriverManager{

    @Override
    public WebDriver createDriver() throws Exception {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
        return ChromeDeviceSetup.driverBuilder();
    }
}
