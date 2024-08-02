package pageObjectClasses.test_factory;


import pageObjectClasses.testclasses.spice.ISpiceJetTest;

import java.io.IOException;

public interface ITestFactory {
    ISpiceJetTest createSpiceJetTest() throws IOException;

}