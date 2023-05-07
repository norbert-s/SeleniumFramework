package pageObjectClasses.factory;

import pageObjectClasses.testclasses.alza.IAlzaTest;
import pageObjectClasses.testclasses.spice.ISpiceJetTest;

import java.io.IOException;

public interface ITestFactory {
    ISpiceJetTest createSpiceJetTest() throws IOException;

    IAlzaTest createAlzaTest() throws IOException;
}