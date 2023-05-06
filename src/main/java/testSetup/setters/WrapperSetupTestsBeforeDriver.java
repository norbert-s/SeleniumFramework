package testSetup.setters;


import lombok.extern.slf4j.Slf4j;
import testSetup.configreader.ConfigReader;
@Slf4j
public class WrapperSetupTestsBeforeDriver {
    /**
     * @see #counter is static to make sure it is executed only once
     * also xalling diffeerent methods from one place
     */
    static int counter=0;
    public static void initializeAttributes() throws Exception {
        if(counter==0){
            new ConfigReader();

            SettingsLogger.printOutSetupInfo();
            SettingsLogger.setupDebuggingLevel();
            counter++;
        }

    }
}
