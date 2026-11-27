package testSetup.deviceSetup.base;


import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testSetup.constants.TypesOfBrowsers;
import testSetup.deviceSetup.factory.DriverManagerFactory;
import testSetup.setters.SettingUpTimeouts;
import testSetup.setters.WrapperSetupTestsBeforeDriver;

@Slf4j
public class DriverBaseClass extends DriverBaseClassAbstract {
    private static final Object DRIVER_SETUP_LOCK = new Object();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("driver base class before suite called");
        WrapperSetupTestsBeforeDriver.initializeAttributes();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void startDriver(@Optional String browser) throws Exception {
        if (browser == null) browser = "CHROME";
        synchronized (DRIVER_SETUP_LOCK) {
            initDriverManager(DriverManagerFactory.getManager(TypesOfBrowsers.valueOf(browser)));
        }
        log.info(Thread.currentThread().getId() + ", " + getDriver());
        SettingUpTimeouts.timeOutSetup(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) {
        log.info(Thread.currentThread().getId() + ", " + getDriver());
        quitDriverAndCleanup();
    }
}
