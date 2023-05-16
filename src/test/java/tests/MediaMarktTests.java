package tests;

import devices.Product;
import devices.ProductDetails;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;
import testSetup.deviceSetup.base.DriverBaseClass;
import testdata.DataProviderClass;
import testdata.MediaMarktAsData;
import utilityClasses.json.json_util.Generic;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MediaMarktTests extends DriverBaseClass  {

    //another approach how to instantiate the tests
    //instantiating the class instead of an interface declaring the methods to be implemented the mediamarkpage has access to all of the methods in
    //PageObjectGenericmethodsImpl



    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
    public void mediaMarktAllProductsCollection(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage mediaMarktMainPage =  getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();

        mediaMarktMainPage.fluentWaitWithVisibilityOfElementLocated(IMediaMarktMainPageLocators.getSearchForm());
        mediaMarktMainPage.fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(IMediaMarktMainPageLocators.getSearchForm()));
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(),mediaMarktAsData.getDevice());

        List<Product> products = new ArrayList<>();
        List<WebElement> allProductsNames = getDriver().findElements(IMediaMarktMainPageLocators.getProductThatContainsText(mediaMarktAsData.getProductNameToLookFor()));

        List<WebElement> allObjectsInDOM = mediaMarktMainPage.fluentWaitForJsExecutorWithQuerySelectorAll("return document.querySelectorAll(\"div[class='product-wrapper']\");");

        IntStream.range(0,allProductsNames.size()).forEach(i-> {
            Product product = new Product();
            product.setProductName(allProductsNames.get(i).getText());

            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            long length = (long)jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children.length;", allObjectsInDOM.get(i));
            System.out.println("lenght : "+length);
            Map<String,String> map = new HashMap<>();
            IntStream.iterate(0, j -> j < length, j -> j + 2).forEach(j -> {
                String value = (String) jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children[" + j + "].innerText;", allObjectsInDOM.get(i));
                int temp = j;
                String value2 = (String) jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children[" + (temp+1) + "].innerText;", allObjectsInDOM.get(i));
                map.put(value,value2);

            });
            map.forEach((k,v)-> System.out.println(k+" "+v));
            System.out.println();
            product.setProductDetails(map);
            products.add(product);
        });
        String str = JsonUtil.convertObjectToString(products);
        Generic.outputStreamWriter("src/test/java/outputstream_"+mediaMarktAsData.getTestID()+".json",str);
    }
}
