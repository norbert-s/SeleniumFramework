package pageObjectClasses.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IPageObjectGenericMethods {
    public void waitForElementToBeClickable(By element);

    public void waitForElementToBePresent(By element);

    public void waitForElementToBePresent(By element, int time);

    public void waitForVisibilityOfWebElement(WebElement element);

    public void waitForAndClick(By element);

    public <T> T enterTextToSearchForm(By element, String text);

    public void clearText(By element);

    public <T> T waitForPageToLoadCompletely();
}
