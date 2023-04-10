package testSetup.deviceSetup.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testSetup.deviceSetup.factory.DriverManager;
@Slf4j
public abstract class DriverBaseClassAbstract {

    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public DriverBaseClassAbstract() {
        log.info("BaseClass constructor called");
    }

    void setDriverManager(DriverManager driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManager getDriverManager(){
        return this.driverManager.get();
    }

    void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }
}
