package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pageObjectClasses.utilityMethods.DevicePage;
import pageObjectClasses.utilityMethods.testclasses.AlzaTest;
import utilityClasses.mapkeys.Maps;
import utilityClasses.testSetup.deviceSetup.base.BaseClass;

import java.io.IOException;

@Slf4j
public class PracticeClass extends BaseClass {


//    @Test(groups = {"add_to_basket"})
//    public void test(){
//        WebDriver driver = getDriver();
//        driver.navigate().to("http://alza.hu");
//
////        List<WebElement> element =  driver.findElements(By.xpath("//div[@class='pl-labels-slider']"));
////        FluentWait wait = new FluentWait(driver)
////                .pollingEvery(Duration.ofMillis(100))
////                .ignoring(NoSuchElementException.class)
////                .withTimeout(Duration.ofSeconds(60));
////        wait.until(ExpectedConditions.visibilityOfAllElements(element));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String myScript = "return document.querySelectorAll(\"div[class='pl-labels-slider']\")[0].children";
//        List<WebElement> list= (List<WebElement>) js.executeScript(myScript, "");
//
//        //String str = element.getDomAttribute("childNodes");
//        list.forEach(s->{
//            System.out.println(s);
//        });
//
//    }

    @Test(groups = {"add_to_basket"})
    public void addItemsToBasketWithHighestPrice2() throws InterruptedException, IOException {
        //waits to fully load
        //WebDriver driver = getDriver();
        AlzaTest locatorsAlza = new AlzaTest(getDriver());
        locatorsAlza.getPage(Maps.getBaseUrlAlza())
                .waitForPageToLoadCompletely()
                .clickOnAcceptCookie()
                .waitForPageToLoadCompletely()
                .enterTextToSearchForm(Maps.getDeviceTypeToLookFor());
        Thread.sleep(20000);
        locatorsAlza.waitForPageToLoadCompletely()
                //.closeAd()
                .findAllTarget();



        DevicePage devicePage = new DevicePage(getDriver());

        //copy over the result pojos to compare
//        devicePage.setPojos(locatorsAlza.getPojos());
//        devicePage.visitDevicePage().
//        devicePage.waitForPageToLoadCompletely();
        Thread.sleep(5000);

    }
    @Test(groups = {"add_to_basket"})
    public void addItemsToBasketWithHighestPrice() throws InterruptedException, IOException {
        //waits to fully load
        //WebDriver driver = getDriver();
        AlzaTest locatorsAlza = new AlzaTest(getDriver());
        locatorsAlza.getPage(Maps.getBaseUrlAlza())
                .waitForPageToLoadCompletely()
                .clickOnAcceptCookie()
                .waitForPageToLoadCompletely()
                .enterTextToSearchForm(Maps.getDeviceTypeToLookFor());
                Thread.sleep(20000);
                locatorsAlza.waitForPageToLoadCompletely()
                //.closeAd()
                .findAllTarget();



        DevicePage devicePage = new DevicePage(getDriver());

        //copy over the result pojos to compare
//        devicePage.setPojos(locatorsAlza.getPojos());
//        devicePage.visitDevicePage().
//        devicePage.waitForPageToLoadCompletely();
        Thread.sleep(5000);

    }
}

