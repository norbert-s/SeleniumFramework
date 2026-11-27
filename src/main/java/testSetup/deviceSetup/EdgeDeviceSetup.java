package testSetup.deviceSetup;


import org.openqa.selenium.edge.EdgeOptions;
import testSetup.setters.EnvironmentVariables;

import java.util.Map;

public class EdgeDeviceSetup {
    public static EdgeOptions driverBuilder() throws Exception {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setExperimentalOption("prefs",
                Map.of("profile.default_content_setting_values.geolocation", 2));

        if (EnvironmentVariables.getHeadless()) {
            edgeOptions.addArguments("--headless");
        }

        return edgeOptions.addArguments("disable-gpu", "window-size=1920,1200", "ignore-certificate-errors", "disable-extensions", "disable-dev-shm-usage", "no-sandbox");

    }
}


