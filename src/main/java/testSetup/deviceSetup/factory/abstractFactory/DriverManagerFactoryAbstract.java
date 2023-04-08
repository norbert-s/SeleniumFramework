package testSetup.deviceSetup.factory.abstractFactory;


import testSetup.constants.TypesOfBrowsers;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(TypesOfBrowsers typesOfBrowsers){
        return switch (typesOfBrowsers) {
            case CHROME -> new ChromeDriverManagerAbstract();
            case FIREFOX -> new FirefoxDriverManagerAbstract();
            case EDGE -> new EdgeDriverManagerAbstract();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
