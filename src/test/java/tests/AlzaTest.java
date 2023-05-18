package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectClasses.testclasses.alza.AlzaMainPageTest;
import pageObjectClasses.testclasses.alza.AlzaSecondPageTest;
import testSetup.deviceSetup.base.DriverBaseClass;
import testSetup.setters.EnvironmentVariables;

import java.io.IOException;

public class AlzaTest extends DriverBaseClass implements EnvironmentVariables {

    AlzaMainPageTest alzaMainPageTest;

    AlzaSecondPageTest alzaSecondPageTest;

    @BeforeMethod
    public void setup() throws IOException {
        this.alzaMainPageTest = getTestFactory().createMainAlzaPageTest();
        this.alzaSecondPageTest = getTestFactory().createAlzaSecondPageTest();
    }

    //example of how to instantiate different pages of alza
    @Test(groups = {"smoke"})
    public void alzaSum() throws Exception {
        alzaMainPageTest.goToPage("http://www.alza.hu");
        alzaSecondPageTest.goToPage("http://www.alza.hu");

    }
}
