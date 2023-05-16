package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;

public interface IMediaMarktMainPage  {
    static By searchForm = By.xpath("//input[@data-identifier=\"search-input-searchterm\"]");
    static By cookieAccept = By.xpath("//*[@id=\"cookie-info-layer\"]//*[text()=\"Elfogad\"]");
}
