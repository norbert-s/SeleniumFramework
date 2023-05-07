package pageObjectClasses.testclasses.alza;

import org.openqa.selenium.By;
import pageObjectClasses.abstracts.IPageObjectGenericMethods;

public interface IAlzaTest extends IPageObjectGenericMethods {
    public AlzaTest findAllTarget();
    public By getDiscountPrice(String specificDevice);
    AlzaTest goToAlza();


}

