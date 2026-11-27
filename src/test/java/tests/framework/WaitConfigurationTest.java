package tests.framework;

import org.testng.annotations.Test;
import testSetup.setters.EnvironmentVariables;
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

    @Test(groups = {"smoke"})
    public void successfulTestScreenshotsAreDisabledByDefaultAndConfigurable() {
        String oldValue = System.getProperty("screenshot-on-success");
        System.clearProperty("screenshot-on-success");
        try {
            assertEquals(EnvironmentVariables.isScreenshotOnSuccess(), false);
            System.setProperty("screenshot-on-success", "true");
            assertEquals(EnvironmentVariables.isScreenshotOnSuccess(), true);
            System.setProperty("screenshot-on-success", "false");
            assertEquals(EnvironmentVariables.isScreenshotOnSuccess(), false);
        } finally {
            restoreScreenshotProperty(oldValue);
        }
    }

    private void restoreProperty(String value) {
        if (value == null) {
            System.clearProperty("waitfortime");
        } else {
            System.setProperty("waitfortime", value);
        }
    }

    private void restoreScreenshotProperty(String value) {
        if (value == null) {
            System.clearProperty("screenshot-on-success");
        } else {
            System.setProperty("screenshot-on-success", value);
        }
    }
}
