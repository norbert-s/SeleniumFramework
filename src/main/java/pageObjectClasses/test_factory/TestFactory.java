package pageObjectClasses.test_factory;

import org.openqa.selenium.WebDriver;
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

}
