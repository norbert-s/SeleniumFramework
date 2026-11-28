package testSetup.deviceSetup.listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testSetup.context.TestExecutionContext;
import testSetup.context.TestExecutionContextHolder;
import testSetup.deviceSetup.base.DriverProvider;
import testSetup.deviceSetup.base.ExtentTestManager;
import testSetup.deviceSetup.base.ExtentManager;
import testSetup.lifecycle.TestLifecycleService;
import testSetup.setters.EnvironmentVariables;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestListener implements ITestListener {

    private byte[] saveScreenshot(WebDriver driver, String fileName) {
        byte[] screenshotBytes;
        try {
            screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (org.openqa.selenium.WebDriverException e) {
            log.warn("Failed to capture screenshot {}", fileName, e);
            return null;
        }
        try {
            FileUtils.writeByteArrayToFile(new File("./screenshots/" + fileName + ".png"), screenshotBytes);
        } catch (IOException e) {
            log.warn("Failed to save screenshot {}", fileName, e);
        }
        return screenshotBytes;
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private void withTestContext(ITestResult result, Runnable action) {
        Object value = result.getAttribute(TestLifecycleService.TEST_CONTEXT_ATTRIBUTE);
        if (value instanceof TestExecutionContext context) {
            try (TestExecutionContextHolder.Scope ignored = TestExecutionContextHolder.bind(context)) {
                action.run();
            }
        } else {
            action.run();
        }
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("Test context started: {}", iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        withTestContext(iTestResult, () -> {
            log.info("Test is starting: {}", getTestMethodName(iTestResult));
            TestExecutionContext context = TestExecutionContextHolder.get();
            String description = context == null ? "" : "runId=" + context.runId()
                    + ", testId=" + context.testId()
                    + ", browser=" + context.browser()
                    + (context.datasetId() == null ? "" : ", datasetId=" + context.datasetId())
                    + (context.jiraId() == null ? "" : ", jiraId=" + context.jiraId());
            ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), description);
        });
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            withTestContext(iTestResult, () -> {
                log.error("Test result: status=FAILED method={} durationMs={}",
                        getTestMethodName(iTestResult),
                        iTestResult.getEndMillis() - iTestResult.getStartMillis(),
                        iTestResult.getThrowable());
                WebDriver driver = DriverProvider.getDriver();
                ExtentTest test = ExtentTestManager.getTest();
                if (test == null) {
                    log.warn("No Extent test is available for failed test {}", getTestMethodName(iTestResult));
                    return;
                }
                if (driver != null) {
                    String fileName = getTestMethodName(iTestResult) + "_" + System.currentTimeMillis();
                    byte[] screenshotBytes = saveScreenshot(driver, fileName);
                    if (screenshotBytes != null) {
                        String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);
                        test.log(Status.FAIL, "Test Failed",
                                test.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
                    } else {
                        test.log(Status.FAIL, "Test Failed");
                    }
                } else {
                    log.warn("No WebDriver available in DriverProvider for onTestFailure");
                    test.log(Status.FAIL, "Test Failed");
                }
            });
        } finally {
            ExtentTestManager.removeTest();
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("Test context finished: {}", iTestContext.getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            withTestContext(iTestResult, () -> {
                log.info("Test result: status=PASSED method={} durationMs={}",
                        getTestMethodName(iTestResult),
                        iTestResult.getEndMillis() - iTestResult.getStartMillis());

                ExtentTest test = ExtentTestManager.getTest();
                if (test == null) {
                    log.warn("No Extent test is available for successful test {}", getTestMethodName(iTestResult));
                    return;
                }
                test.log(Status.PASS, "Test passed");
                WebDriver driver = DriverProvider.getDriver();
                if (driver != null && EnvironmentVariables.isScreenshotOnSuccess()) {
                    String fileName = iTestResult.getName() + "_" + System.currentTimeMillis();
                    byte[] screenshotBytes = saveScreenshot(driver, fileName);
                    if (screenshotBytes != null) {
                        String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);
                        test.log(Status.PASS, "Test Passed",
                                test.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
                    }
                } else if (driver == null) {
                    log.warn("No WebDriver available in DriverProvider for onTestSuccess");
                }
            });
        } finally {
            ExtentTestManager.removeTest();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        try {
            withTestContext(iTestResult, () -> {
                log.warn("Test result: status=SKIPPED method={} durationMs={}",
                        getTestMethodName(iTestResult),
                        iTestResult.getEndMillis() - iTestResult.getStartMillis(),
                        iTestResult.getThrowable());
                ExtentTest test = ExtentTestManager.getTest();
                if (test != null) {
                    test.log(Status.SKIP, "Test Skipped");
                }
            });
        } finally {
            ExtentTestManager.removeTest();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
