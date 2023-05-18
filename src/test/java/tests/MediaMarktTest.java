package tests;


import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;
import pageObjectClasses.testclasses.mediamarkt.MediaMarktMainPage;
import pageObjectClasses.testclasses.mediamarkt.Product;
import testSetup.deviceSetup.base.DriverBaseClass;
import testdata.DataProviderClass;
import testdata.MediaMarktAsData;
import utilityClasses.json.json_util.JsonUtil;

import java.io.IOException;
import java.util.List;

@Slf4j
public class MediaMarktTest extends DriverBaseClass {

    //another approach how to instantiate the tests
    //instantiating the class instead of an interface declaring the methods to be implemented the mediamarkpage has access to all of the methods in
    //PageObjectGenericmethodsImpl

    //also this test is demonstrating how to dinamically get different features of a products
    //in the mediamarkt.json the product being looked for can be provided and then the different product details will be extracted from the dom
    //and then it will be serialized and a product object created
    //and finally deserialized into a json
    @Test(groups = "smoke", dataProvider = "mediaMarktTestData", dataProviderClass = DataProviderClass.class)
    public void mediaMarktAllProductsCollection2(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        MediaMarktMainPage mediaMarktMainPage = getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(), mediaMarktAsData.getDevice());
        List<Product> products = mediaMarktMainPage.getProductObjects();
        String str = JsonUtil.convertObjectToString(products);
        JsonUtil.outputStreamWriter("src/test/java/outputstream_" + mediaMarktAsData.getTestID() + ".json", str);
    }


    //experimental version using js Executor
    //it demonstrats that js can be powerful but also should be used when really necessary
    //in this case the jsexecutor version is not optimized enough and it seems to be much more error prone then the other version

    @Test(groups = "runwithjs", dataProvider = "mediaMarktTestData", dataProviderClass = DataProviderClass.class)
    public void mediaMarktAllProductsCollection(MediaMarktAsData mediaMarktAsData) throws IOException, InterruptedException {
        MediaMarktMainPage mediaMarktMainPage = getTestFactory().createMediaMarktMainPage();
        mediaMarktMainPage.goToPage(mediaMarktAsData.getUrl());
        mediaMarktMainPage.acceptCookie();
        mediaMarktMainPage.enterTextToSearchForm(IMediaMarktMainPageLocators.getSearchForm(), mediaMarktAsData.getDevice());
        List<Product> products = mediaMarktMainPage.createProductObjectsWithJs();
        String str = JsonUtil.convertObjectToString(products);
        JsonUtil.outputStreamWriter("src/test/java/outputstream_withjs_" + mediaMarktAsData.getTestID() + ".json", str);
    }
}
