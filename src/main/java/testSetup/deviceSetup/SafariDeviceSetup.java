package testSetup.deviceSetup;


import org.openqa.selenium.safari.SafariOptions;

public class SafariDeviceSetup {
    public static SafariOptions driverBuilder() throws Exception {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);

        return options;
    }
}


