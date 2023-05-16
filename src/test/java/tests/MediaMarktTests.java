package tests;

import devices.Phone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;
import testSetup.deviceSetup.base.DriverBaseClass;
import testdata.DataProviderClass;
import testdata.MediaMarktAsData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MediaMarktTests extends DriverBaseClass  {

    //another approach how to instantiate the tests
    //instantiating the class instead of an interface declaring the methods to be implemented the mediamarkpage has access to all of the methods in
    //PageObjectGenericmethodsImpl


    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
    public void basicTest(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage mediaMarktMainPage =  getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();

        mediaMarktMainPage.fluentWaitWithVisibilityOfElementLocated(IMediaMarktMainPageLocators.getSearchForm());
        mediaMarktMainPage.fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(IMediaMarktMainPageLocators.getSearchForm()));
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(),mediaMarktAsData.getDevice());

        List<Phone> products = new ArrayList<>();
        List<WebElement> allProductsNames = getDriver().findElements(IMediaMarktMainPageLocators.getProductThatContainsText(mediaMarktAsData.getProductNameToLookFor()));
        System.out.println(allProductsNames.size());
        System.out.println(allProductsNames.get(0).getText());

        List<WebElement> allProductSections = getDriver().findElements(IMediaMarktMainPageLocators.getProductDetails(mediaMarktAsData.getProductNameToLookFor()));
        System.out.println(allProductSections.get(0).getText());
        //WebElement element = getDriver().findElement(IMediaMarktMainPageLocators.getProductDetails(mediaMarktAsData.getProductNameToLookFor()));
        //List<String> productSectionParts = allProductSections.get(0).getText().split("")
//        List<WebElement> allProductSections = mediaMarktMainPage.returnWait().until(new Function<WebDriver, List<WebElement>>() {
//            public List<WebElement> apply(WebDriver driver) {
//                List<WebElement> elements = getDriver().findElements(IMediaMarktMainPageLocators.getProductDetails(mediaMarktAsData.getProductNameToLookFor()));
//                return elements.size() > 0 ? elements : null;
//            }
//        });
        //mediaMarktMainPage.fluentWaitForAllElementsPresence();

        List<WebElement> allObjectsInDOM = mediaMarktMainPage.fluentWaitForJsExecutorWithQuerySelectorAll("return document.querySelectorAll(\"div[class='product-wrapper']\");");
        //List<WebElement> allScripts = getDriver().findElements(IMediaMarktMainPageLocators.getScriptPart(mediaMarktAsData.getProductNameToLookFor()));
//        System.out.println(allScripts.get(0).getText());
        System.out.println(allObjectsInDOM.get(0));
        System.out.println("--------------------------------");
//        String str = elements.get(0).getText();
//        List<String> strList = List.of(str.split(","));
//        System.out.println(strList);
//        WebElement element = elements.get(0).findElement(By.xpath("//div[@class=\"content\"]//h2"));
//        System.out.println("apple iphone: "+element.getText());

    }



//    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
//    public void basicTest2(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
//        MediaMarktMainPage mediaMarktMainPage =  getTestFactory().createMediaMarktMainPage();
//        mediaMarktMainPage.goToPageFirstTime(mediaMarktAsData.getUrl());
//
//        mediaMarktMainPage.fluentWaitWithVisibilityOfElementLocated(IMediaMarktMainPage.searchForm);
//        mediaMarktMainPage.fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(IMediaMarktMainPage.searchForm));
//        mediaMarktMainPage.enterTextToSearchForm(searchForm,mediaMarktAsData.getDevice());
//
//    }
}
