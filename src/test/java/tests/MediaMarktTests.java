package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.IMediaMarktMainPage;
import pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage;
import testSetup.deviceSetup.base.DriverBaseClass;
import testdata.DataProviderClass;
import testdata.MediaMarktAsData;

import java.io.IOException;

public class MediaMarktTests extends DriverBaseClass implements IMediaMarktMainPage {

    //another approach how to instantiate the tests
    //instantiating the class instead of an interface declaring the methods to be implemented the mediamarkpage has access to all of the methods in
    //PageObjectGenericmethodsImpl
    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
    public void basicTest(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        MediaMarktMainPage mediaMarktMainPage =  getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.clickOnCookie();
        mediaMarktMainPage.fluentWaitWithVisibilityOfElementLocated(IMediaMarktMainPage.searchForm);
        mediaMarktMainPage.fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(IMediaMarktMainPage.searchForm));
        mediaMarktMainPage.enterTextToSearchForm(searchForm,mediaMarktAsData.getDevice());
    }
}
