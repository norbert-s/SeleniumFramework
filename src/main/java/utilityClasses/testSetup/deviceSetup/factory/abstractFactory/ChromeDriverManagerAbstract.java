package utilityClasses.testSetup.deviceSetup.factory.abstractFactory;

import utilityClasses.testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() throws Exception {
        driver  = ChromeDeviceSetup.driverBuilder();
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
    }
}
