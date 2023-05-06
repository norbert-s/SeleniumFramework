package pageObjectClasses.factory;

import pageObjectClasses.testclasses.ISpiceJetTest;

import java.io.IOException;

public interface ITestFactory {
    ISpiceJetTest createSpiceJetTest() throws IOException;

}