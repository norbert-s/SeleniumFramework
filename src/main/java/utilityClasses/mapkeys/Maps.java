package utilityClasses.mapkeys;

import testSetup.configreader.ConfigReader;

public class Maps {
    public static final String getBaseUrlAlza() {
        return returnConfig("base.url.alza");
    }

    public static final String getDeviceTypeToLookFor() {
        return returnConfig("device.to.look.for");
    }

    public static final String getSpecificDevice() {
        return returnConfig("specific.device");
    }

    public static final String returnConfig(String str) {
        return ConfigReader.getProps().getProperty(str);
    }

    public static final String basicSpiceTestPath() {
        return returnConfig("basic.spice.test.json.path");
    }

    public static final String basicSpiceTestPath2() {
        return returnConfig("basic.spice.test.json.path2");
    }


}
