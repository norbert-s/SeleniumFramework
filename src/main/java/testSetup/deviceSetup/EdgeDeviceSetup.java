package testSetup.deviceSetup;


import org.openqa.selenium.edge.EdgeOptions;
import testSetup.setters.EnvironmentVariables;

public class EdgeDeviceSetup {
    public static EdgeOptions driverBuilder() throws Exception {
        EdgeOptions edgeOptions = new EdgeOptions();

        if (EnvironmentVariables.getHeadless()) {
            edgeOptions.addArguments("--headless");
        }

        return edgeOptions.addArguments("disable-gpu", "window-size=1920,1200", "ignore-certificate-errors", "disable-extensions", "disable-dev-shm-usage", "no-sandbox");

    }
}



