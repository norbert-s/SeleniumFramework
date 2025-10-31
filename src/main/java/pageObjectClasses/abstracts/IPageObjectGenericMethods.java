package pageObjectClasses.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IPageObjectGenericMethods {
    public IPageObjectGenericMethods waitForElementToBeClickable(By element);

    public IPageObjectGenericMethods waitForElementToBePresent(By element);

    public IPageObjectGenericMethods waitForElementToBePresent(By element, int time);

    public IPageObjectGenericMethods waitForVisibilityOfWebElement(WebElement element);

    public IPageObjectGenericMethods waitForAndClick(By element);

    public <T> T enterTextToSearchForm(By element, String text);

    public IPageObjectGenericMethods clearText(By element);

    public <T> T waitForPageToLoadCompletely();
}
