package testSetup.deviceSetup.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import testSetup.deviceSetup.factory.DriverManager;
@Slf4j
public abstract class DriverBaseClassAbstract {

    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //private final ThreadLocal<WebDriverManager> wdm = new ThreadLocal<>();

//    public WebDriverManager getWdm() {
//        return this.wdm.get();
//    }
//
//    public void setWdm(WebDriverManager wdm){
//        this.wdm.set(wdm);
//    }
    protected final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

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

    public SoftAssert getSoftAssert() {
        return this.softAssert.get();
    }
}
