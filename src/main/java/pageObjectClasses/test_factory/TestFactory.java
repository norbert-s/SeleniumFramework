package pageObjectClasses.test_factory;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.testclasses.alza.AlzaMainPageTest;
import pageObjectClasses.testclasses.alza.AlzaSecondPageTest;
import pageObjectClasses.testclasses.alza.AlzaTest;
import pageObjectClasses.testclasses.alza.IAlzaTest;
import pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage;
import pageObjectClasses.testclasses.spice.ISpiceJetTest;
import pageObjectClasses.testclasses.spice.SpiceJetTest;

import java.io.IOException;

public class TestFactory implements ITestFactory {

    private final WebDriver driver;

    public TestFactory(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public ISpiceJetTest createSpiceJetTest() throws IOException {
        return new SpiceJetTest(driver);
    }

    @Override
    public IAlzaTest createAlzaTest() throws IOException {
        return new AlzaTest(driver);
    }

    @Override
    public AlzaMainPageTest createMainAlzaPageTest() throws IOException {
        return new AlzaMainPageTest(driver);
    }

    @Override
    public AlzaSecondPageTest createAlzaSecondPageTest() throws IOException {
        return new AlzaSecondPageTest(driver);
    }

    @Override
    public MediaMarktMainPage createMediaMarktMainPage() throws IOException {
        return new MediaMarktMainPage(driver);
    }

}
