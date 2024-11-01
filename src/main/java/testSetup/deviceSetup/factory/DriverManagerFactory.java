package testSetup.deviceSetup.factory;


import testSetup.constants.TypesOfBrowsers;
import testSetup.setters.EnvironmentVariables;

public class DriverManagerFactory {

    public static DriverManager getManager(TypesOfBrowsers typesOfBrowsers) {
        boolean isDocker = EnvironmentVariables.isDockerNeeded();

        return switch (typesOfBrowsers) {
            case CHROME -> isDocker ? new DockerChromeDriverManager() : new ChromeDriverManager();
            case FIREFOX -> isDocker ? new DockerFirefoxDriverManager() : new FirefoxDriverManager();
            case EDGE -> isDocker ? new DockerEdgeDriverManager() : new EdgeDriverManager();
            default -> throw new IllegalStateException("Unexpected value: " + typesOfBrowsers);
        };
    }
}
