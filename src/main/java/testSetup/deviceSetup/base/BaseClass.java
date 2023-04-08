package testSetup.deviceSetup.base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import testSetup.configreader.ConfigReader;
import testSetup.constants.TypesOfBrowsers;
import testSetup.deviceSetup.factory.DriverManager;
import testSetup.deviceSetup.factory.DriverManagerFactory;
import testSetup.setters.WrapperToCallSetupMethods;

import java.io.File;
import java.io.IOException;


public class BaseClass {
    static Logger log = LoggerFactory.getLogger(BaseClass.class);
    private final ThreadLocal<DriverManager> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    private void setDriverManager(DriverManager driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManager getDriverManager(){
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

        WrapperToCallSetupMethods.initializeAttributes();
        if (browser == null) browser = "CHROME";
        setDriverManager(DriverManagerFactory.getManager(TypesOfBrowsers.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        log.info(Thread.currentThread().getId() + ", " + getDriver());
    }


    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws Exception {
        log.info(Thread.currentThread().getId() + ", " +    getDriver());
        getDriverManager().quitDriver();
    }
    public static class ScreenshotOnFailureListener extends TestListenerAdapter {
        public void takeScreenshot(WebDriver driver, String fileName) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./screenshots/" + fileName + ".png"));
            } catch (IOException e) {
                log.warn("Failed to save screenshot: " + e.getMessage());
            }
        }
        @Override
        public void onTestFailure(ITestResult result) {
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseClass) {
                BaseClass baseClass = (BaseClass) testInstance;
                String fileName = result.getName() + "_" + System.currentTimeMillis();
                takeScreenshot(baseClass.getDriver(), fileName);
                ITestContext context = result.getTestContext();
                String suite = context.getSuite().getName();
                log.info(suite+" "+result.getName()+" test has Failed " );
            }
        }


        @Override
        public void onTestSkipped(ITestResult result) {
            try{
                ITestContext context = result.getTestContext();
                String suite = context.getSuite().getName();
                log.info(suite+" "+result.getName()+" test has been SKIPPED " );
            }catch(Exception e){

            }
        }
        @Override
        public void onTestStart(ITestResult result) {
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            log.info(suite+" "+result.getName()+"------New Test Started------");
        }
        @Override
        public void onTestSuccess(ITestResult result) {
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            log.info(suite+" "+result.getName()+" test has PASSED " );
            Object testInstance = result.getInstance();
            if (testInstance instanceof BaseClass) {
                BaseClass baseClass = (BaseClass) testInstance;
                String fileName = result.getName() + "_" + System.currentTimeMillis();
                takeScreenshot(baseClass.getDriver(), fileName);
            }
        }
    }

}
