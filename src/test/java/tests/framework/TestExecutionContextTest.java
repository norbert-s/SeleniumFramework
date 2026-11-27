package tests.framework;

import org.slf4j.MDC;
import org.testng.annotations.Test;
import testSetup.context.TestExecutionContext;
import testSetup.context.TestExecutionContextHolder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TestExecutionContextTest {
    @Test(groups = {"smoke"})
    public void clearRemovesContextAndLogIdentifiers() {
        TestExecutionContext context = TestExecutionContext.create(
                "run-1", "SPICE-001", "2", "JIRA-42", "sampleTest");
        TestExecutionContextHolder.set(context);

        assertEquals(TestExecutionContextHolder.get(), context);
        assertEquals(MDC.get("runId"), "run-1");
        assertEquals(MDC.get("testId"), "SPICE-001");
        assertEquals(MDC.get("datasetId"), "2");
        assertEquals(MDC.get("jiraId"), "JIRA-42");

        TestExecutionContextHolder.clear();

        assertNull(TestExecutionContextHolder.get());
        assertNull(MDC.get("runId"));
        assertNull(MDC.get("testId"));
        assertNull(MDC.get("datasetId"));
        assertNull(MDC.get("jiraId"));
    }

    @Test(groups = {"smoke"})
    public void executionContextIsIsolatedBetweenThreads() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Callable<String> first = contextFor("run-a", "test-a");
            Callable<String> second = contextFor("run-b", "test-b");

            var firstResult = executor.submit(first);
            var secondResult = executor.submit(second);

            assertEquals(firstResult.get(), "run-a:test-a");
            assertEquals(secondResult.get(), "run-b:test-b");
            assertNull(TestExecutionContextHolder.get());
            assertNull(MDC.get("runId"));
        } finally {
            executor.shutdownNow();
        }
    }

    private Callable<String> contextFor(String runId, String testCaseId) {
        return () -> {
            TestExecutionContext context = TestExecutionContext.create(
                    runId, "SPICE-001", testCaseId, null, "sampleTest");
            TestExecutionContextHolder.set(context);
            try {
                return MDC.get("runId") + ":" + MDC.get("datasetId");
            } finally {
                TestExecutionContextHolder.clear();
            }
        };
    }
}
