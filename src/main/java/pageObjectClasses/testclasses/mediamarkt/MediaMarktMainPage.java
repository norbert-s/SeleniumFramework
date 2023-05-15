package pageObjectClasses.testclasses.mediamarkt;

import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.IMediaMarktMainPage;

import java.io.IOException;

public class MediaMarktMainPage extends PageObjectGenericMethodsImpl implements IMediaMarktMainPage {

    public MediaMarktMainPage(WebDriver driver) throws IOException {
        super(driver);
    }

    public void clickOnCookie(){
        waitForAndClick(cookieAccept);
    }
}
