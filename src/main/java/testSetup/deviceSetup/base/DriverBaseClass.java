package testSetup.deviceSetup.base;


import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testSetup.constants.TypesOfBrowsers;
import testSetup.context.TestCaseId;
import testSetup.context.TestExecutionContext;
import testSetup.context.TestExecutionIdentity;
import testSetup.lifecycle.TestLifecycleService;
import testSetup.setters.WrapperSetupTestsBeforeDriver;

import java.util.Locale;

@Slf4j
public class DriverBaseClass extends DriverBaseClassAbstract {
    private final TestLifecycleService testLifecycleService = new TestLifecycleService();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("driver base class before suite called");
        WrapperSetupTestsBeforeDriver.initializeAttributes();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void startDriver(@Optional String browser, Object[] testParameters, ITestResult result) throws Exception {
        String selectedBrowser = browser == null ? "CHROME" : browser;
        TestExecutionIdentity executionIdentity = findExecutionIdentity(testParameters);
        String testId = executionIdentity == null
                ? result.getMethod().getQualifiedName()
                : executionIdentity.getTestId();
        String datasetId = executionIdentity == null ? null : executionIdentity.getDatasetId();
        TestCaseId annotation = result.getMethod().getConstructorOrMethod()
                .getMethod().getAnnotation(TestCaseId.class);
        String jiraId = annotation == null ? null : annotation.value();
        TypesOfBrowsers browserType = TypesOfBrowsers.valueOf(selectedBrowser.toUpperCase(Locale.ROOT));
        TestExecutionContext context = testLifecycleService.createContext(
                result.getMethod().getMethodName(), testId, datasetId, jiraId, browserType);
        result.setAttribute(TestLifecycleService.TEST_CONTEXT_ATTRIBUTE, context);
        testLifecycleService.startTest(context, browserType);
    }

    private TestExecutionIdentity findExecutionIdentity(Object[] testParameters) {
        for (Object parameter : testParameters) {
            if (parameter instanceof TestExecutionIdentity identity) {
                if (identity.getTestId() == null || identity.getTestId().isBlank()) {
                    throw new IllegalArgumentException("Data-driven testId must not be blank");
                }
                if (identity.getDatasetId() == null || identity.getDatasetId().isBlank()) {
                    throw new IllegalArgumentException("Data-driven datasetId must not be blank");
                }
                return identity;
            }
        }
        return null;
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver(ITestResult result) {
        log.info("Finishing test {}", result.getMethod().getQualifiedName());
        testLifecycleService.finishTest();
    }
}
