package testSetup.deviceSetup.factory.abstractFactory;

import testSetup.deviceSetup.ChromeDeviceSetup;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() throws Exception {
        driver  = ChromeDeviceSetup.driverBuilder();
    }
}
