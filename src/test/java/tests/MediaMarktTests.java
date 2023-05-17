package tests;


import devices.Product;
import devices.ProductDetails;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class MediaMarktTests extends DriverBaseClass  {

    //another approach how to instantiate the tests
    //instantiating the class instead of an interface declaring the methods to be implemented the mediamarkpage has access to all of the methods in
    //PageObjectGenericmethodsImpl


    //also this test is demonstrating how to dinamically get different features of a product using javascript executor
    //in the mediamarkt.json the product being looked for can be provided and then the different product details will be extracted from the dom
    //and then it will be serialized and a product object created
    //and finally deserialized into a json
    //with this methodology its possible to get all the necessary values of all of the products
    //obviously it is important to optimize the javascript scripts and use it with caution, because as soon as a container changes in the hierarhcy the test will fail
    //it is just a demonstration that it can be used get different values from the dom directly
    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
    public void mediaMarktAllProductsCollection(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage mediaMarktMainPage = getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();

        mediaMarktMainPage.fluentWaitWithVisibilityOfElementLocated(IMediaMarktMainPageLocators.getSearchForm());
        mediaMarktMainPage.fluentWaitWithExpectedCondition(ExpectedConditions.elementToBeClickable(IMediaMarktMainPageLocators.getSearchForm()));
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(), mediaMarktAsData.getDevice());

        List<Product> products = new ArrayList<>();

        //it is the container of all of the products on the page
        List<WebElement> allObjectsInDOM = mediaMarktMainPage.fluentWaitForJsExecutorWithQuerySelectorAll("return document.querySelectorAll(\"div[class='product-wrapper']\");");

        IntStream.range(0, allObjectsInDOM.size()).forEach(i -> {
            Product product = new Product();

            JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
            long length = (long) jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children.length;", allObjectsInDOM.get(i));

            //name of current product
            String nameOfProduct = (String) jsExecutor.executeScript("return arguments[0].children[2].childNodes[3].innerText;", allObjectsInDOM.get(i));
            product.setProductName(nameOfProduct);
            log.info("length : " + length);

            Map<String, String> map = new HashMap<>();
            IntStream.iterate(0, j -> j < length, j -> j + 2).forEach(j -> {
                String productDetailKey = (String) jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children[" + j + "].innerText;", allObjectsInDOM.get(i));
                int temp = j;
                String productDetailValue = (String) jsExecutor.executeScript("return arguments[0].lastChild.previousSibling.children[4].children[" + (temp + 1) + "].innerText;", allObjectsInDOM.get(i));
                map.put(productDetailKey, productDetailValue);

            });
            map.forEach((k, v) -> log.info(k + " " + v));
            log.info("");
            product.setProductDetails(map);
            products.add(product);
        });
        String str = JsonUtil.convertObjectToString(products);
        Generic.outputStreamWriter("src/test/java/outputstream_" + mediaMarktAsData.getTestID() + ".json", str);
    }
}
