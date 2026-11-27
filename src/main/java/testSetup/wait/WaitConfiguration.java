package testSetup.wait;

import java.time.Duration;

public record WaitConfiguration(Duration timeout, Duration pollingInterval) {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration DEFAULT_POLLING_INTERVAL = Duration.ofMillis(50);

    public WaitConfiguration {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("Wait timeout must be greater than zero");
        }
        if (pollingInterval == null || pollingInterval.isZero() || pollingInterval.isNegative()) {
            throw new IllegalArgumentException("Polling interval must be greater than zero");
        }
    }

    public static WaitConfiguration fromSystemProperties() {
        String configuredTimeout = System.getProperty("waitfortime");
        Duration timeout = DEFAULT_TIMEOUT;
        if (configuredTimeout != null && !configuredTimeout.isBlank()) {
            try {
                int seconds = Integer.parseInt(configuredTimeout);
                if (seconds < 30 || seconds > 120) {
                    throw new IllegalArgumentException("waitfortime must be between 30 and 120 seconds");
                }
                timeout = Duration.ofSeconds(seconds);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("waitfortime must be an integer number of seconds", e);
            }
        }
        return new WaitConfiguration(timeout, DEFAULT_POLLING_INTERVAL);
    }
}
