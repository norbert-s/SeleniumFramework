package pageObjectClasses.factory;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.testclasses.ISpiceJetTest;
import pageObjectClasses.testclasses.SpiceJetTest;

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
