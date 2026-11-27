package tests.framework;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.annotations.Test;
import testSetup.context.TestExecutionContext;
import testSetup.context.TestExecutionContextHolder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

public class TestExecutionContextTest {
    private static final Logger log = LoggerFactory.getLogger(TestExecutionContextTest.class);

    @Test(groups = {"smoke"})
    public void clearRemovesContextAndLogIdentifiers() {
        TestExecutionContext context = TestExecutionContext.create(
                "run-1", "SPICE-001", "2", "JIRA-42", "sampleTest", "CHROME");
        TestExecutionContextHolder.set(context);

        assertEquals(TestExecutionContextHolder.get(), context);
        assertEquals(MDC.get("runId"), "run-1");
        assertEquals(MDC.get("testId"), "SPICE-001");
        assertEquals(MDC.get("testName"), "sampleTest");
        assertEquals(MDC.get("browser"), "CHROME");
        assertEquals(MDC.get("datasetId"), "2");
        assertEquals(MDC.get("jiraId"), "JIRA-42");

        TestExecutionContextHolder.clear();

        assertNull(TestExecutionContextHolder.get());
        assertNull(MDC.get("runId"));
        assertNull(MDC.get("testId"));
        assertNull(MDC.get("testName"));
        assertNull(MDC.get("browser"));
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

    @Test(groups = {"smoke"})
    public void runLogFileContainsBrowserAndDatasetMetadata() throws Exception {
        String runId = "context-test-" + UUID.randomUUID();
        TestExecutionContextHolder.set(TestExecutionContext.create(
                runId, "SPICE-001", "2", null, "sampleTest", "EDGE"));
        try {
            log.info("Result record for log routing test");
        } finally {
            TestExecutionContextHolder.clear();
        }

        String logContent = Files.readString(Path.of(
                "logs", runId, "EDGE", "sampleTest", "dataset-2.jsonl")).trim();
        JsonNode logEvent = new ObjectMapper().readTree(logContent);
        assertTrue(logEvent.get("timestamp").asText().matches(
                "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[+-]\\d{2}:\\d{2}"));
        assertEquals(logEvent.get("level").asText(), "INFO");
        assertEquals(logEvent.get("runId").asText(), runId);
        assertEquals(logEvent.get("testId").asText(), "SPICE-001");
        assertEquals(logEvent.get("datasetId").asText(), "2");
        assertEquals(logEvent.get("browser").asText(), "EDGE");
        assertEquals(logEvent.get("message").asText(), "Result record for log routing test");
        assertTrue(logEvent.has("logger"));
        assertTrue(logEvent.has("thread"));
        assertTrue(!logEvent.has("instant"));
        assertTrue(!logEvent.has("endOfBatch"));
        assertTrue(!logEvent.has("loggerFqcn"));
        assertTrue(!logEvent.has("threadPriority"));
    }

    private Callable<String> contextFor(String runId, String testCaseId) {
        return () -> {
            TestExecutionContext context = TestExecutionContext.create(
                    runId, "SPICE-001", testCaseId, null, "sampleTest", "EDGE");
            TestExecutionContextHolder.set(context);
            try {
                return MDC.get("runId") + ":" + MDC.get("datasetId");
            } finally {
                TestExecutionContextHolder.clear();
            }
        };
    }
}
