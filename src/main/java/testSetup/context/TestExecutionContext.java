package testSetup.context;

public record TestExecutionContext(
        String runId,
        String testId,
        String datasetId,
        String jiraId,
        String testName,
        String browser
) {
    public static TestExecutionContext create(
            String runId,
            String testId,
            String datasetId,
            String jiraId,
            String testName,
            String browser
    ) {
        return new TestExecutionContext(runId, testId, datasetId, jiraId, testName, browser);
    }
}
