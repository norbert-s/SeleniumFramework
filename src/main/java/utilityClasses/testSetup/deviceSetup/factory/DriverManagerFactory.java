package utilityClasses.testSetup.deviceSetup.factory;


import utilityClasses.testSetup.constants.TypesOfBrowsers;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.ChromeDriverManagerAbstract;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.DriverManagerAbstract;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.FirefoxDriverManagerAbstract;

public class DriverManagerFactory {

    public static DriverManagerAbstract getManager(TypesOfBrowsers typesOfBrowsers){
        return switch (typesOfBrowsers) {
            case CHROME -> new ChromeDriverManagerAbstract();
            case FIREFOX -> new FirefoxDriverManagerAbstract();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
