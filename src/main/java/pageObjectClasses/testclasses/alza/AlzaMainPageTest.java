package pageObjectClasses.testclasses.alza;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.IPageObjectGenericMethods;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.IAlzaPageLocators;

import java.io.IOException;

public class AlzaMainPageTest extends PageObjectGenericMethodsImpl implements IAlzaPageLocators, IPageObjectGenericMethods {
    public AlzaMainPageTest(WebDriver driver) throws IOException {
        super(driver);
    }

}
