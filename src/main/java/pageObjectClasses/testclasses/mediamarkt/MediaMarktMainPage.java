package pageObjectClasses.testclasses.mediamarkt;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
import pageObjectClasses.pageobjects.IMediaMarktMainPageLocators;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j

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
    public List<Product> createProductObjectsWithJs(){
        //it is the container of all of the products on the page
        List<WebElement> allObjectsInDOM = fluentWaitForJsExecutorWithQuerySelectorAll("return document.querySelectorAll(\"div[class='product-wrapper']\");");
        List<Product> products=new ArrayList<>();
        IntStream.range(0, allObjectsInDOM.size()).forEach(i -> {
            Product product = new Product();

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
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
            log.info("");
            product.setProductDetails(map);
            products.add(product);
        });
        return products;
    }

    public List<Product> getProductObjects(){
        List<WebElement> allElements = returnAllElements(IMediaMarktMainPageLocators.getAllProductsFromPage());
        List<Product> products = new ArrayList<>();
        IntStream.range(0,allElements.size()).forEach(s->{
            List<WebElement> dtElements = allElements.get(s).findElements(IMediaMarktMainPageLocators.getProductDetailsKey());
            List<WebElement> ddElements = allElements.get(s).findElements(IMediaMarktMainPageLocators.getProductDetailsValue());
            String nameOfProduct = allElements.get(s).findElement(IMediaMarktMainPageLocators.getProductName()).getText();
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
        return products;
    }
}
