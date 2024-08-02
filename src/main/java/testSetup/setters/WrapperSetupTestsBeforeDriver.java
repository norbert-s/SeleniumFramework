package testSetup.setters;


import lombok.extern.slf4j.Slf4j;
import testSetup.configreader.ConfigReader;

@Slf4j
public class WrapperSetupTestsBeforeDriver {

    static int counter = 0;

    public static void initializeAttributes() {
        if (counter == 0) {
            new ConfigReader();

            SettingsLogger.printOutSetupInfo();
            SettingsLogger.setupDebuggingLevel();
            counter++;
        }
    }
}
