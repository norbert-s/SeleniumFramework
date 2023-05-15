package pageObjectClasses.testclasses.alza;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.IPageObjectGenericMethods;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.IAlzaPageLocators;

import java.io.IOException;

public class AlzaSecondPageTest extends PageObjectGenericMethodsImpl implements IAlzaPageLocators, IPageObjectGenericMethods {

    public AlzaSecondPageTest(WebDriver driver) throws IOException {
        super(driver);
    }
}
