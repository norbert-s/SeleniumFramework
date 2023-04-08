package testSetup.deviceSetup.factory;


import testSetup.constants.TypesOfBrowsers;

public class DriverManagerFactory {

    public static DriverManager getManager(TypesOfBrowsers typesOfBrowsers){
        return switch (typesOfBrowsers) {
            case CHROME -> new ChromeDriverManager();
            case FIREFOX -> new FirefoxDriverManager();
            case EDGE -> new EdgeDriverManager();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
