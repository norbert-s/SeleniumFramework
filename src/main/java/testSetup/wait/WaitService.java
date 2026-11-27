package testSetup.wait;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WaitService {
    private final WebDriver driver;
    private final WaitConfiguration configuration;

    public WaitService(WebDriver driver) {
        this(driver, WaitConfiguration.fromSystemProperties());
    }

    public WaitService(WebDriver driver, WaitConfiguration configuration) {
        this.driver = java.util.Objects.requireNonNull(driver, "WebDriver must not be null");
        this.configuration = java.util.Objects.requireNonNull(configuration, "Wait configuration must not be null");
    }

    public Wait<WebDriver> returnWait() {
        return returnWait(configuration.timeout());
    }

    public Wait<WebDriver> returnWait(long timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            throw new IllegalArgumentException("Wait timeout must be greater than zero");
        }
        return returnWait(java.time.Duration.ofSeconds(timeoutSeconds));
    }

    public Wait<WebDriver> returnWait(java.time.Duration timeout) {
        if (timeout == null || timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("Wait timeout must be greater than zero");
        }
        return new FluentWait<>(driver)
                .pollingEvery(configuration.pollingInterval())
                .ignoring(NoSuchElementException.class)
                .withTimeout(timeout);
    }
}
