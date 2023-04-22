package testSetup.deviceSetup.base;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import testSetup.configreader.ConfigReader;
import testSetup.constants.TypesOfBrowsers;
import testSetup.deviceSetup.factory.DriverManagerFactory;
import testSetup.setters.WrapperToCallSetupMethods;

import java.io.File;
import java.io.IOException;
import org.testng.asserts.SoftAssert;
@Slf4j
public  class DriverBaseClass extends DriverBaseClassAbstract{

    @BeforeClass
    public void beforeSuite() throws Exception {
        new ConfigReader();
        WrapperToCallSetupMethods.initializeAttributes();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void startDriver(@Optional String browser) throws Exception {
        if (browser == null) browser = "CHROME";
        setDriverManager(DriverManagerFactory.getManager(TypesOfBrowsers.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        softAssert.set(new SoftAssert()); // Initialize SoftAssert instance
        log.info(Thread.currentThread().getId() + ", " + getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws Exception {
        log.info(Thread.currentThread().getId() + ", " + getDriver());
        getDriverManager().quitDriver();
        softAssert.remove(); // Clean up SoftAssert instance
    }
    public static class TestListener extends TestListenerAdapter {
        public synchronized void takeScreenshot(WebDriver driver, String fileName) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./screenshots/" + fileName + ".png"));
            } catch (IOException e) {
                log.warn("Failed to save screenshot: " + e.getMessage());
            }
        }
        @Override
        public synchronized void onTestFailure(ITestResult result) {
            Object testInstance = result.getInstance();
            if (testInstance instanceof DriverBaseClass) {
                DriverBaseClass driverBaseClass = (DriverBaseClass) testInstance;
                String fileName = result.getName() + "_" + System.currentTimeMillis();
                takeScreenshot(driverBaseClass.getDriver(), fileName);
                ITestContext context = result.getTestContext();
                String suite = context.getSuite().getName();
                log.info(suite+" "+result.getName()+" test has Failed " );
            }
        }


        @Override
        public synchronized void onTestSkipped(ITestResult result) {
            try{
                ITestContext context = result.getTestContext();
                String suite = context.getSuite().getName();
                log.info(suite+" "+result.getName()+" test has been SKIPPED " );
            }catch(Exception e){

            }
        }
        @Override
        public synchronized void onTestStart(ITestResult result) {
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            log.info(suite+" "+result.getName()+"------New Test Started------");

        }
        @Override
        public synchronized void onTestSuccess(ITestResult result) {
            ITestContext context = result.getTestContext();
            String suite = context.getSuite().getName();
            log.info(suite+" "+result.getName()+" test has PASSED " );
//            Object testInstance = result.getInstance();
//            if (testInstance instanceof DriverBaseClass) {
//                DriverBaseClass driverBaseClass = (DriverBaseClass) testInstance;
//                String fileName = result.getName() + "_" + System.currentTimeMillis();
//                takeScreenshot(driverBaseClass.getDriver(), fileName);
//            }
        }
    }

}
