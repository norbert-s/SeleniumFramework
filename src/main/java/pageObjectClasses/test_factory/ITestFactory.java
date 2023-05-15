package pageObjectClasses.test_factory;

import pageObjectClasses.testclasses.alza.AlzaMainPageTest;
import pageObjectClasses.testclasses.alza.AlzaSecondPageTest;
import pageObjectClasses.testclasses.alza.IAlzaTest;
import pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage;
import pageObjectClasses.testclasses.spice.ISpiceJetTest;

import java.io.IOException;

public interface ITestFactory {
    ISpiceJetTest createSpiceJetTest() throws IOException;

    IAlzaTest createAlzaTest() throws IOException;

    AlzaMainPageTest createMainAlzaPageTest() throws IOException;

    AlzaSecondPageTest createAlzaSecondPageTest() throws IOException;

    MediaMarktMainPage createMediaMarktMainPage() throws IOException;
}