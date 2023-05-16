package pageObjectClasses.testclasses.mediamarkt;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;

import java.io.IOException;

public class MediaMarktMainPage extends PageObjectGenericMethodsImpl  {

    public MediaMarktMainPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void acceptCookie(){
        waitForAndClick(IMediaMarktMainPageLocators.getCookieAccept());
    }

    public void lookForProduct(String productName){

    }

    public void goToPageFirstTime(String url){
        goToPage(url);
        acceptCookie();
    }



}
