package testSetup.context;

import org.slf4j.MDC;

public final class TestExecutionContextHolder {
    private static final ThreadLocal<TestExecutionContext> CONTEXT = new ThreadLocal<>();

    private TestExecutionContextHolder() {
    }

    public static void set(TestExecutionContext context) {
        CONTEXT.set(context);
        MDC.put("runId", context.runId());
        MDC.put("testId", context.testId());
        MDC.put("testName", context.testName());
        MDC.put("browser", context.browser());
        putOrRemove("datasetId", context.datasetId());
        putOrRemove("jiraId", context.jiraId());
    }

    private static void putOrRemove(String key, String value) {
        if (value == null || value.isBlank()) {
            MDC.remove(key);
        } else {
            MDC.put(key, value);
        }
    }

    public static TestExecutionContext get() {
        return CONTEXT.get();
    }

    public static Scope bind(TestExecutionContext context) {
        TestExecutionContext previous = CONTEXT.get();
        set(context);
        return () -> {
            if (previous == null) {
                clear();
            } else {
                set(previous);
            }
        };
    }

    public static void clear() {
        CONTEXT.remove();
        MDC.remove("runId");
        MDC.remove("testId");
        MDC.remove("testName");
        MDC.remove("browser");
        MDC.remove("datasetId");
        MDC.remove("jiraId");
    }

    @FunctionalInterface
    public interface Scope extends AutoCloseable {
        @Override
        void close();
    }
}
