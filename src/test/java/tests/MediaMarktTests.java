package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjectClasses.testclasses.mediamarkt.Product;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;
import pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage;
import testSetup.deviceSetup.base.DriverBaseClass;
import testdata.DataProviderClass;
import testdata.MediaMarktAsData;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    //In this example as shown below the other approach is better
    //it demonstrats that js can be powerful but also should be used when really necessary

//    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
//    public void mediaMarktAllProductsCollection(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
//        MediaMarktMainPage mediaMarktMainPage = getTestFactory().createMediaMarktMainPage();
//        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
//        mediaMarktMainPage.acceptCookie();
//        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(), mediaMarktAsData.getDevice());
//        List<Product> products =mediaMarktMainPage.createProductObjects();
//        String str = JsonUtil.convertObjectToString(products);
//        JsonUtil.outputStreamWriter("src/test/java/outputstream_withjs_" + mediaMarktAsData.getTestID() + ".json", str);
//    }

    //demonstrating how to do the exact same thing can be achieved by using a different approach
    //in this example this approach is more efficient than the js executor one
    //however sometimes the js executor can be used to address issues more effectively

    @Test(groups = "smoke",dataProvider = "mediaMarktTestData",dataProviderClass = DataProviderClass.class)
    public void mediaMarktAllProductsCollection2(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        MediaMarktMainPage mediaMarktMainPage = getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(), mediaMarktAsData.getDevice());
        List<WebElement> allElements = mediaMarktMainPage.returnAllElements(IMediaMarktMainPageLocators.getAllProductsFromPage());
        List<Product> products = new ArrayList<>();
        IntStream.range(0,allElements.size()).forEach(s->{
            List<WebElement> dtElements = allElements.get(s).findElements(By.cssSelector("dl[class='product-details'] dd"));
            List<WebElement> ddElements = allElements.get(s).findElements(By.cssSelector("dl[class='product-details'] dt"));
            String nameOfProduct = allElements.get(s).findElement(By.cssSelector("div[class='content '] h2")).getText();

            log.info(nameOfProduct);
            Map<String, String> productDetailsMap = new LinkedHashMap<>();
            IntStream.range(0,dtElements.size()).forEach(i->{
                String key = dtElements.get(i).getText();
                String value = ddElements.get(i).getText();
                productDetailsMap.put(key, value);
            });
            Product product = new Product();
            product.setProductName(nameOfProduct);
            product.setProductDetails(productDetailsMap);
            products.add(product);

        });
        String str = JsonUtil.convertObjectToString(products);
        JsonUtil.outputStreamWriter("src/test/java/outputstream_" + mediaMarktAsData.getTestID() + ".json", str);
    }
}
