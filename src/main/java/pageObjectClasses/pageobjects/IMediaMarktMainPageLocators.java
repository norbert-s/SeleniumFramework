package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

public interface IMediaMarktMainPageLocators {

    static By getSearchForm(){
        return By.xpath("//input[@data-identifier='search-input-searchterm']");
    }

    static By getCookieAccept(){
        return By.xpath("//*[contains(@class,'cookie-info-accept-button')]");
    }

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

    static By getAllProductsFromPage(){
        return By.xpath("//div[@class='product-wrapper']");
    }
}
