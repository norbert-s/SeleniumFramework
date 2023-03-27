package utilityClasses.testSetup.deviceSetup.base;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilityClasses.testSetup.configreader.ConfigReader;
import utilityClasses.testSetup.constants.DriverType;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.DriverManagerAbstract;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.DriverManagerFactoryAbstract;
import utilityClasses.testSetup.setters.wrapperForSetupClasses.WrapperToCallSetupMethods;


@Slf4j
public class BaseClass {
    private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriverManager(DriverManagerAbstract driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager(){
        return this.driverManager.get();
    }

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        WrapperToCallSetupMethods.initializeAttributes();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void startDriver(@Optional String browser) throws Exception {
        new ConfigReader();
        if(browser == null) browser = "CHROME";
        setDriverManager(DriverManagerFactoryAbstract.
                getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        log.info(Thread.currentThread().getId() + ", " +   getDriver());

    }
    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws Exception {
        log.info(Thread.currentThread().getId() + ", " +   getDriver());
        getDriverManager().getDriver().quit();
    }

}
