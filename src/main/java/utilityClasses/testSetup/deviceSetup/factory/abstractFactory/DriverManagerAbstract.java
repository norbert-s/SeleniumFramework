package utilityClasses.testSetup.deviceSetup.factory.abstractFactory;

import org.openqa.selenium.WebDriver;

public abstract class DriverManagerAbstract {
    protected WebDriver driver;

    protected abstract void startDriver() throws Exception;

    public void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() throws Exception {
        if(driver == null){
            startDriver();
        }
        return driver;
    }
}
