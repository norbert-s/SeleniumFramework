package pageObjectClasses.utilityMethods.pageobjects;

import org.openqa.selenium.By;
import utilityClasses.mapkeys.Maps;

public interface AlzaPageLocators {
    By cookiePopupAlza = By.xpath("//*[contains(@class,\"cookies-info__buttons\")]//*[contains(text(),\"I understand\")]");

    By searchFieldAlza = By.xpath("//*[@id=\"edtSearch\"]");

    By nameOfDevice = By.xpath("//*[@class='fb']//a[contains(@data-impression-name,'" + Maps.getSpecificDevice() + "')]");

    By priceOfDevice = By.xpath("//*[@class='fb']//a[contains(@data-impression-name,'" + Maps.getSpecificDevice() + "')]" + "//ancestor::div[@class='top']//following-sibling::*[@class='bottom']//*[@class='price-box__price']");

    By closeAd = By.xpath("//*[@id='or-popup']");

    By rating = By.xpath("//*[@class='fb']//a[contains(@data-impression-name,'" + Maps.getSpecificDevice() + "')]//ancestor::*[@class='fb']//*[@class='star-rating-block__value']");

    By discountPrice = By.xpath("//*[@class='fb']//a[contains(@data-impression-name,'" + Maps.getSpecificDevice() + "')]//ancestor::*[@class='top']//following-sibling::*[@class='bottom']//*[@class='price-box__compare-price']");

}
