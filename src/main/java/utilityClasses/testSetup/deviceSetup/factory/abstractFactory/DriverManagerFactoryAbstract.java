package utilityClasses.testSetup.deviceSetup.factory.abstractFactory;


import utilityClasses.testSetup.constants.TypesOfBrowsers;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(TypesOfBrowsers typesOfBrowsers){
        return switch (typesOfBrowsers) {
            case CHROME -> new ChromeDriverManagerAbstract();
            case FIREFOX -> new FirefoxDriverManagerAbstract();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
