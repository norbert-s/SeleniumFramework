package pageObjectClasses.abstracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;

public abstract class PageObjectBaseMethodsAbstract {
    abstract Wait<WebDriver> returnWait();

    abstract Wait<WebDriver> returnWait(long timeOut);

    abstract WebElement convertByToWebElement(By locator);

    abstract void fluentWaitWithExpectedCondition(ExpectedCondition<WebElement> expectedCondition);

    abstract void fluentWaitWithExpectedCondition(ExpectedCondition<WebElement> expectedCondition, long timeOut);
}
