//package pageObjectClasses.testclasses;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import pageObjectClasses.abstracts.PageObjectGenericMethodsImpl;
//import pageObjectClasses.pageobjects.AlzaPageLocators;
//import pageObjectClasses.pojo.DevicePojo;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class AlzaTest extends PageObjectGenericMethodsImpl implements AlzaPageLocators {
//    private List<DevicePojo> allPojos = new ArrayList<>();
//    public List<DevicePojo>getPojos(){
//        return allPojos;
//    }
//
//
//
//    public By getDiscountPrice(String specificDevice) {
//        return By.xpath("//*[@class='fb']//a[contains(@data-impression-name,'" + specificDevice + "')]//ancestor::*[@class='top']//following-sibling::*[@class='bottom']//*[@class='price-box__compare-price']");
//    }
//
//    public AlzaTest findAllTarget() {
//
//        returnWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(nameOfDevice));
//        List<WebElement> allDeviceNames = driver.findElements(nameOfDevice).stream().collect(Collectors.toList());
//        List<String> discountPrices = new ArrayList<>();
//        for (WebElement productElement : allDeviceNames) {
//
//            String nameOfDeviceHere = productElement.getText();
//            try {
//
//                returnWait(3000).until(ExpectedConditions.presenceOfElementLocated(getDiscountPrice(nameOfDeviceHere))
//                        .andThen(s-> discountPrices.add(driver.findElement(getDiscountPrice(nameOfDeviceHere)).getText())));
//            } catch (Exception e) {
//                discountPrices.add(null);
//            }
//        }
//
//        List<String> prices = driver.findElements(priceOfDevice).stream().map(s -> s.getText()).collect(Collectors.toList());
//        List<String> ratings = driver.findElements(rating).stream().map(s -> s.getText()).collect(Collectors.toList());
//
//        List<DevicePojo> allPojos = new ArrayList<>();
//        System.out.println(allDeviceNames.size());
//        System.out.println(discountPrices.size());
//        System.out.println(discountPrices);
//
//        IntStream.range(0, allDeviceNames.size())
//                .forEach(index -> {
//                    DevicePojo device = new DevicePojo();
//                    device.setElement(allDeviceNames.get(index));
//                    device.setNameInBald(allDeviceNames.get(index).getText());
//                    device.setPrice(prices.get(index));
//                    device.setRatings(Double.parseDouble(ratings.get(index)));
//                    device.setOriginalPrice(discountPrices.get(index));
//                    allPojos.add(device);
//                    System.out.println(device);
//                });
//
////        allDeviceNames.forEach(s->{
////
////            returnWait().until(ExpectedConditions.visibilityOf( s));
////            if(s.)
////            s.click();
////            try {
////                Thread.sleep(10000);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
////        });
//        this.allPojos = allPojos;
//        return this;
//    }
//
//
//
//    List<WebElement> listOfElements = new ArrayList<>();
//
////    LocatorsOfAlzaProject extractPrice() {
////        String str =
////                System.out.println();
////    }
//
////    public LocatorsOfAlzaProject closeAd() {
////        waitForAndClick(clickHere);
////        return this;
////    }
//
//    public AlzaTest(WebDriver driver) throws IOException {
//        super(driver);
//
//    }
//
//    public AlzaTest getPage(String url) {
//        driver.get(url);
//        return this;
//    }
//
//    public AlzaTest enterTextToSearchForm(String text) {
//        enterTextToSearchForm(searchFieldAlza, text);
//        return this;
//    }
//
//    public AlzaTest clickOnAcceptCookie() {
//        super.clickOnAcceptCookie(cookiePopupAlza, 30000);
//        return this;
//    }
//
//    @Override
//    public AlzaTest waitForPageToLoadCompletely() {
//        super.waitForPageToLoadCompletely();
//        return this;
//    }
//}
