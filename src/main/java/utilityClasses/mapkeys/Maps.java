package utilityClasses.mapkeys;

import utilityClasses.testSetup.configreader.ConfigReader;

public class Maps {
    public static String getBaseUrlAlza(){
        return returnConfig("base.url.alza") ;
    }
    public static String getDeviceTypeToLookFor(){
        return returnConfig("device.to.look.for");
    }

    public static String getSpecificDevice(){
        return returnConfig("specific.device");
    }

    static String returnConfig(String str){
        return ConfigReader.getProps().getProperty(str);
    }

}
