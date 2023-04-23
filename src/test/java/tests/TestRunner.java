package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.TestNG;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import testSetup.deviceSetup.base.DriverBaseClass;

import java.util.Arrays;
@Slf4j
public class TestRunner {

    @Test(groups = {"runner"})
    public void bla() {
        TestNG testng = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("test suite");

        suite.setParallel(XmlSuite.ParallelMode.METHODS);
        suite.setThreadCount(3);

        String[] browsers = {"CHROME","EDGE"};

        for (String browser : browsers) {
            XmlTest test = new XmlTest(suite);
            test.setName(browser + " tests");
            test.addParameter("browser", browser);
            test.setXmlClasses(Arrays.asList(new XmlClass("tests.Parallel")));
        }

        testng.setXmlSuites(Arrays.asList(suite));
        testng.run();
    }
}
