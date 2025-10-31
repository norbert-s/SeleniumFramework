//package testSetup.deviceSetup.listeners;
//
//import com.aventstack.extentreports.Status;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import testSetup.deviceSetup.base.DriverProvider;
//import testSetup.deviceSetup.base.ExtentManager;
//import testSetup.deviceSetup.base.ExtentTestManager;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Objects;
//
//@Slf4j
//public class TestListener2 implements ITestListener {
//
//    public synchronized void saveScreenshot(WebDriver driver, String fileName) {
//        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//        try {
//            FileUtils.writeByteArrayToFile(new File("./screenshots/" + fileName + ".png"), screenshotBytes);
//        } catch (IOException e) {
//            log.warn("Failed to save screenshot: " + e.getMessage());
//        }
//    }
//
//    private synchronized static String getTestMethodName(ITestResult iTestResult) {
//        return iTestResult.getMethod().getConstructorOrMethod().getName();
//    }
//
//    @Override
//    public synchronized void onStart(ITestContext iTestContext) {
//        log.info("I am in onStart method " + iTestContext.getName());
//    }
//
//    @Override
//    public synchronized void onTestStart(ITestResult iTestResult) {
//        log.info(getTestMethodName(iTestResult) + " test is starting.");
//        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
//    }
//
//    @Override
//    public synchronized void onTestFailure(ITestResult iTestResult) {
//        log.info(getTestMethodName(iTestResult) + " test is failed.");
//        WebDriver driver = DriverProvider.getDriver();
//        if (driver != null) {
//            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
//            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed",
//                    ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
//        } else {
//            log.warn("No WebDriver available in DriverProvider for onTestFailure");
//        }
//    }
//
//    @Override
//    public synchronized void onFinish(ITestContext iTestContext) {
//        log.info("I am in onFinish method " + iTestContext.getName());
//        ExtentManager.extentReports.flush();
//    }
//
//    @Override
//    public synchronized void onTestSuccess(ITestResult iTestResult) {
//        log.info(getTestMethodName(iTestResult) + " test is succeed.");
//        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
//
//        WebDriver driver = DriverProvider.getDriver();
//        if (driver != null) {
//            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//            String fileName = iTestResult.getName() + "_" + System.currentTimeMillis();
//            saveScreenshot(driver, fileName);
//            ExtentTestManager.getTest().log(Status.PASS, "Test Passed",
//                    ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
//        } else {
//            log.warn("No WebDriver available in DriverProvider for onTestSuccess");
//        }
//    }
//
//    @Override
//    public synchronized void onTestSkipped(ITestResult iTestResult) {
//        log.info(getTestMethodName(iTestResult) + " test is skipped.");
//        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
//    }
//}
