package testSetup.setters;


public class WrapperToCallSetupMethods {
    /**
     * @see #counter is static to make sure it is executed only once
     * also xalling diffeerent methods from one place
     */
    static int counter=0;
    public static void initializeAttributes() throws Exception {
        if(counter==0){
            SettingsLogger.printOutSetupInfo();
            SettingsLogger.setupDebuggingLevel();
            counter++;
        }

    }
}
