package testSetup.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import testSetup.constants.TypesOfBrowsers;
import testSetup.context.TestExecutionContext;
import testSetup.context.TestExecutionContextHolder;
import testSetup.deviceSetup.base.DriverProvider;
import testSetup.deviceSetup.factory.DriverManager;
import testSetup.deviceSetup.factory.DriverManagerFactory;
import testSetup.setters.SettingUpTimeouts;

@Slf4j
public class TestLifecycleService {
    public static final String TEST_CONTEXT_ATTRIBUTE = TestExecutionContext.class.getName();
    private static final String RUN_ID_PROPERTY = "runId";
    private static final String RUN_ID = System.getProperty(RUN_ID_PROPERTY, java.util.UUID.randomUUID().toString());
    private final ThreadLocal<DriverManager> driverManagers = new ThreadLocal<>();

    public TestExecutionContext createContext(
            String testName,
            String testId,
            String datasetId,
            String jiraId
    ) {
        return TestExecutionContext.create(RUN_ID, testId, datasetId, jiraId, testName);
    }

    public void startTest(TestExecutionContext context, TypesOfBrowsers browser) throws Exception {
        java.util.Objects.requireNonNull(context, "Test execution context must not be null");
        if (driverManagers.get() != null
                || DriverProvider.getDriver() != null
                || TestExecutionContextHolder.get() != null) {
            throw new IllegalStateException("A previous test context is still active on this thread");
        }
        TestExecutionContextHolder.set(context);
        log.info("Starting test {}", context.testName());
        try {
            DriverManager driverManager = DriverManagerFactory.getManager(browser);
            driverManagers.set(driverManager);
            WebDriver driver = driverManager.getDriver();
            DriverProvider.setDriver(driver);
            SettingUpTimeouts.timeOutSetup(driver);
            log.info("Browser session started for {}", context.testName());
        } catch (Exception | Error failure) {
            try {
                finishTest();
            } catch (RuntimeException cleanupFailure) {
                failure.addSuppressed(cleanupFailure);
            }
            throw failure;
        }
    }

    public void finishTest() {
        DriverManager driverManager = driverManagers.get();
        try {
            if (driverManager != null) {
                driverManager.quitDriver();
            }
        } finally {
            driverManagers.remove();
            DriverProvider.remove();
            TestExecutionContextHolder.clear();
        }
    }
}
