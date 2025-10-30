package testSetup.deviceSetup.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pageObjectClasses.test_factory.ITestFactory;
import pageObjectClasses.test_factory.TestFactory;
import testSetup.deviceSetup.factory.DriverManager;

@Slf4j
public abstract class DriverBaseClassAbstract {

    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public DriverBaseClassAbstract() {
        log.info("BaseClass constructor called");
    }

    void setDriverManager(DriverManager driverManager) {
        this.driverManager.set(driverManager);
    }

    DriverManager getDriverManager() {
        return this.driverManager.get();
    }

    void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        return this.driver.get();
    }

    protected final ThreadLocal<ITestFactory> testFactory = new ThreadLocal<>();

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
     * per-thread DriverManager and associated WebDriver, TestFactory
     * implements the stateful driverManager pattern -  manager owns the driver instance.
     */
    public void initDriverManager(DriverManager dm) throws Exception {
        if (dm == null) throw new IllegalArgumentException("DriverManager must not be null");
        setDriverManager(dm);
        WebDriver d = dm.getDriver();
        setDriver(d);
        testFactory.set(new TestFactory(getDriver()));
        DriverProvider.setDriver(getDriver());
    }


    public void quitDriverAndCleanup() {
        DriverManager dm = getDriverManager();
        if (dm != null) {
            try {
                dm.quitDriver();
            } catch (Exception ignored) {
                // ignore
            }
        }

        // Remove all ThreadLocal state for this thread
        driverManager.remove();
        driver.remove();
        testFactory.remove();
        DriverProvider.remove();
    }
}
