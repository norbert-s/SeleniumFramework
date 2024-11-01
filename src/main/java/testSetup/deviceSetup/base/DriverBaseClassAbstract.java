package testSetup.deviceSetup.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import pageObjectClasses.test_factory.ITestFactory;
import pageObjectClasses.test_factory.TestFactory;
import testSetup.deviceSetup.factory.DriverManager;

@Slf4j
public abstract class DriverBaseClassAbstract {

    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

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

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    public SoftAssert getSoftAssert() {
        return this.softAssert.get();
    }

    protected final ThreadLocal<ITestFactory> testFactory = new ThreadLocal<>();

    public ITestFactory getTestFactory() {
        return testFactory.get();
    }

    public ITestFactory createTestFactory() {
        return new TestFactory(getDriver());
    }
}
