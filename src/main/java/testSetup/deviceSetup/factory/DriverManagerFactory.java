package testSetup.deviceSetup.factory;


import testSetup.constants.TypesOfBrowsers;
import testSetup.deviceSetup.factory.abstractFactory.DriverManagerAbstract;
import testSetup.deviceSetup.factory.abstractFactory.FirefoxDriverManagerAbstract;
import testSetup.deviceSetup.factory.abstractFactory.ChromeDriverManagerAbstract;

public class DriverManagerFactory {

    public static DriverManagerAbstract getManager(TypesOfBrowsers typesOfBrowsers){
        return switch (typesOfBrowsers) {
            case CHROME -> new ChromeDriverManagerAbstract();
            case FIREFOX -> new FirefoxDriverManagerAbstract();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
