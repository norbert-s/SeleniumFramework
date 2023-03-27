package utilityClasses.testSetup.deviceSetup.base;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilityClasses.testSetup.configreader.ConfigReader;
import utilityClasses.testSetup.constants.DriverType;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.DriverManagerAbstract;
import utilityClasses.testSetup.deviceSetup.factory.abstractFactory.DriverManagerFactoryAbstract;
import utilityClasses.testSetup.setters.wrapperForSetupClasses.WrapperToCallSetupMethods;



public class BaseClass {
    private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriverManager(DriverManagerAbstract driverManager){
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager(){
        return this.driverManager.get();
    }

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @BeforeSuite
    public void beforeSuite() throws Exception {
        WrapperToCallSetupMethods.initializeAttributes();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void startDriver(@Optional String browser) throws Exception {
        new ConfigReader();
        
        //browser = System.getProperty("browser", browser);
        if(browser == null) browser = "CHROME";
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        setDriverManager(DriverManagerFactoryAbstract.
                getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        if(driver==null) System.out.println("driver is null");
        else System.out.println("driver is no null !!!!!!!!!!!!!!!!!!!");
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());

    }


    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws Exception {
//        Thread.sleep(300);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
//        getDriver().quit();
//        if(result.getStatus() == ITestResult.FAILURE){
//            File destFile = new File("scr" + File.separator + browser + File.separator +
//                    result.getTestClass().getRealClass().getSimpleName() + "_" +
//                    result.getMethod().getMethodName() + ".png");
////            takeScreenshot(destFile);
//
//        }
        getDriverManager().getDriver().quit();
    }

}
