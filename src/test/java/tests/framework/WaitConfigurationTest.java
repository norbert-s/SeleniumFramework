package tests.framework;

import org.testng.annotations.Test;
import testSetup.wait.WaitConfiguration;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

public class WaitConfigurationTest {
    @Test(groups = {"smoke"})
    public void usesDefaultTimeoutWhenNoPropertyIsSet() {
        String oldValue = System.getProperty("waitfortime");
        System.clearProperty("waitfortime");
        try {
            assertEquals(WaitConfiguration.fromSystemProperties().timeout(), Duration.ofSeconds(30));
        } finally {
            restoreProperty(oldValue);
        }
    }

    @Test(groups = {"smoke"})
    public void rejectsMalformedTimeoutProperty() {
        String oldValue = System.getProperty("waitfortime");
        System.setProperty("waitfortime", "not-a-number");
        try {
            expectThrows(IllegalArgumentException.class, WaitConfiguration::fromSystemProperties);
        } finally {
            restoreProperty(oldValue);
        }
    }

    private void restoreProperty(String value) {
        if (value == null) {
            System.clearProperty("waitfortime");
        } else {
            System.setProperty("waitfortime", value);
        }
    }
}
