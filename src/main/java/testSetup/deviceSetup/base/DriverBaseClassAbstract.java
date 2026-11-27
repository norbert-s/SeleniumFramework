package testSetup.deviceSetup.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pageObjectClasses.test_factory.ITestFactory;
import pageObjectClasses.test_factory.TestFactory;
import testSetup.deviceSetup.factory.DriverManager;

@Slf4j
public abstract class DriverBaseClassAbstract {

    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    protected final ThreadLocal<ITestFactory> testFactory = new ThreadLocal<>();

    public DriverBaseClassAbstract() {
        log.info("BaseClass constructor called");
    }

    void setDriverManager(DriverManager driverManager) {
        this.driverManager.set(driverManager);
    }

    DriverManager getDriverManager() {
        return this.driverManager.get();
    }

    public WebDriver getDriver() {
        return DriverProvider.getDriver();
    }

    public ITestFactory getTestFactory() {
        ITestFactory tf = testFactory.get();
        if (tf == null) {
            // lazy initialize so callers don't get NPE if called before explicit create
            tf = createTestFactory();
            testFactory.set(tf);
        }
        return tf;
    }

    public ITestFactory createTestFactory() {
        TestFactory tf = new TestFactory(getDriver());
        testFactory.set(tf);
        return tf;
    }

    /**
     * per-thread DriverManager and associated webDriver, testFactory
     * implements the stateful driverManager pattern -  manager owns the driver instance.
     */
    public void initDriverManager(DriverManager dm) throws Exception {
        if (dm == null) throw new IllegalArgumentException("DriverManager must not be null");
        setDriverManager(dm);
        WebDriver d = dm.getDriver();
        DriverProvider.setDriver(d);
        testFactory.set(new TestFactory(d));
    }


    public void quitDriverAndCleanup() {
        DriverManager dm = getDriverManager();
        try {
            if (dm != null) {
                dm.quitDriver();
            }
        } finally {
            driverManager.remove();
            testFactory.remove();
            DriverProvider.remove();
        }
    }
}
