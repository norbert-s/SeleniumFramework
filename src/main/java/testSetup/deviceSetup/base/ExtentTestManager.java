package testSetup.deviceSetup.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    static ExtentReports extent = ExtentManager.createExtentReports();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTest.set(test);
        return test;
    }

    public static void removeTest() {
        extentTest.remove();
    }
}
