package testSetup.deviceSetup.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import testSetup.configreader.ConfigReader;
import testSetup.constants.TypesOfBrowsers;
import testSetup.deviceSetup.factory.DriverManagerFactory;
import testSetup.setters.WrapperToCallSetupMethods;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.testng.asserts.SoftAssert;

import static testSetup.deviceSetup.base.ExtentTestManager.getTest;

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

    public static class TestListener  implements ITestListener {
        private synchronized static String getTestMethodName(ITestResult iTestResult) {
            return iTestResult.getMethod().getConstructorOrMethod().getName();
        }
        @Override
        public synchronized void onStart(ITestContext iTestContext) {
            log.info("I am in onStart method " + iTestContext.getName());
        }

        @Override
        public synchronized void onTestStart(ITestResult iTestResult) {
            log.info(getTestMethodName(iTestResult) + " test is starting.");
            // Set the driver attribute for the test instance
            Object testInstance = iTestResult.getInstance();
            if (testInstance instanceof DriverBaseClass) {
                DriverBaseClass driverBaseClass = (DriverBaseClass) testInstance;
                iTestResult.getTestContext().setAttribute("WebDriver", driverBaseClass.getDriver());
            }
            // Start a new test in ExtentTestManager
            ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
        }

        // ...

        @Override
        public synchronized void onTestFailure(ITestResult iTestResult) {
            log.info(getTestMethodName(iTestResult) + " test is failed.");
            // Get driver from the test instance
            Object testInstance = iTestResult.getInstance();
            if (testInstance instanceof DriverBaseClass) {
                DriverBaseClass driverBaseClass = (DriverBaseClass) testInstance;
                WebDriver driver = driverBaseClass.getDriver();
                // Take base64Screenshot screenshot for extent reports
                String base64Screenshot =
                        "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
                // ExtentReports log and screenshot operations for failed tests.
                getTest().log(Status.FAIL, "Test Failed",
                        getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
            }
        }
        @Override
        public synchronized void onFinish(ITestContext iTestContext) {
            log.info("I am in onFinish method " + iTestContext.getName());
            //Do tier down operations for ExtentReports reporting!
            ExtentManager.extentReports.flush();
        }

        @Override
        public synchronized void onTestSuccess(ITestResult iTestResult) {
            log.info(getTestMethodName(iTestResult) + " test is succeed.");
            //ExtentReports log operation for passed tests.
            getTest().log(Status.PASS, "Test passed");
        }

        @Override
        public synchronized void onTestSkipped(ITestResult iTestResult) {
            log.info(getTestMethodName(iTestResult) + " test is skipped.");
            //ExtentReports log operation for skipped tests.
            getTest().log(Status.SKIP, "Test Skipped");
        }
        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
            log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        }
    }



    public static class TestListener2 extends TestListenerAdapter {
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
