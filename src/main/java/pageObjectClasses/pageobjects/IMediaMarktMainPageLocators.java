package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

public interface IMediaMarktMainPageLocators {

    static By getSearchForm(){
        return By.xpath("//input[@data-identifier='search-input-searchterm']");
    }

    static By getCookieAccept(){
        return By.xpath("//*[contains(@class,'cookie-info-accept-button')]");
    }


//    static By productByText = By.xpath("//div[contains(@class,\"content\")]//*[contains(text(),\""+productName+"\")]");
//
    //static By productPrice = By.xpath("//div[contains(@class,\"content\")]//*[contains(text(),\""+productName+"\")]//parent::*//preceding-sibling::aside[contains(@class,\"product-price\")]");

    //default String getAllProductsThatContainsText(String productName){
    //   return "//div[contains(@class,\"content\")]//*[contains(text(),\""+productName+"\")]";
    //}

    public static By getProductThatContainsText(String productName){
        return By.xpath("//div[contains(@class,'content')]//*[contains(text(),'"+productName+"')]");
    }

    static By getProductPriceSection(String productName){
        return By.xpath("//div[contains(@class,'content')]//*[contains(text(),'"+productName+"')]//parent::*//preceding-sibling::aside[contains(@class,'product-price')]");
    }

    static By getProductDetails(String productName){
        return By.xpath("//div[contains(@class,'content')]//*[contains(text(),'"+productName+"')]//parent::h2//following-sibling::dl");
    }

    static By getScriptPart(String productName){
        return By.xpath("//div[contains(@class,'content')]//*[contains(text(),'"+productName+"')]//ancestor::div[@class='product-wrapper']//preceding-sibling::script");
    }




    //div[contains(@class,"content")]//*[contains(text(),"IPHONE 14")]//parent::*//preceding-sibling::aside[contains(@class,"product-price")]
    //By getAllProductsFromPage(){
//        return By.xpath("//li//*[@class='product-wrapper']");
//    }
}
