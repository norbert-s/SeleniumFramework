package testSetup.setters;


import io.github.bonigarcia.wdm.WebDriverManager;

public class GetDriverPath{

    public static void getPath() {
        String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();
        String firefoxDriverPath = WebDriverManager.firefoxdriver().getDownloadedDriverPath();
        String edgeDriverPath = WebDriverManager.edgedriver().getDownloadedDriverPath();



        System.out.println("ChromeDriver Path: " + chromeDriverPath);
        System.out.println("FirefoxDriver Path: " + firefoxDriverPath);
        System.out.println("EdgeDriver Path: " + edgeDriverPath);
    }


}