package pageObjectClasses.pageobjects;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;

public interface IMediaMarktMainPageLocators {

    static By getSearchForm(){
        return cssSelector("input[data-identifier='search-input-searchterm']");
    }

    static By getCookieAccept(){
        return cssSelector(".cookie-info-accept-button");
    }

    static By getAllProductsFromPage(){
        return cssSelector(".product-wrapper");
    }

    static By getProductDetailsKey(){
        return By.cssSelector("dl[class='product-details'] dd");
    }

    static By getProductDetailsValue(){
        return By.cssSelector("dl[class='product-details'] dt");
    }

    static By getProductName(){
        return By.cssSelector("div[class='content '] h2");
    }


}
