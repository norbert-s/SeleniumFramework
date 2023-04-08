package testSetup.deviceSetup.factory;

import org.openqa.selenium.WebDriver;


public abstract class DriverManager {
    protected WebDriver driver;

    protected abstract WebDriver createDriver() throws Exception;

    public void quitDriver(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() throws Exception {
        if(driver == null){
            createDriver();
        }
        return driver;
    }
}

