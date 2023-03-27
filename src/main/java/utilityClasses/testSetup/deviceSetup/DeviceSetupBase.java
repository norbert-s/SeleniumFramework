package utilityClasses.testSetup.deviceSetup;

public interface DeviceSetupBase {

    public  <T> T settingUpDevices(String browser)throws Exception;
    public <T> T driverServiceFactory()throws Exception;
    public <T> T driverBuilder(String browser)throws Exception;
}
