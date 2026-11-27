package testSetup.context;

public record TestExecutionContext(
        String runId,
        String testId,
        String datasetId,
        String jiraId,
        String testName
) {
    public static TestExecutionContext create(
            String runId,
            String testId,
            String datasetId,
            String jiraId,
            String testName
    ) {
        return new TestExecutionContext(runId, testId, datasetId, jiraId, testName);
    }
}
