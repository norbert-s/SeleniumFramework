package pageObjectClasses.testclasses;


import org.openqa.selenium.WebDriver;
import pageObjectClasses.abstracts.PageObjectGenericMethodsPageObject;
import pageObjectClasses.pojo.DevicePojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DevicePagePageObject extends PageObjectGenericMethodsPageObject {

    public DevicePagePageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    private List<DevicePojo> allPojos = new ArrayList<>();

    public void setPojos(List<DevicePojo> allPojos){
        this.allPojos=allPojos;
    }


    public void visitDevicePage(){

    }
}