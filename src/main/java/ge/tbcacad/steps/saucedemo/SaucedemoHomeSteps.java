package ge.tbcacad.steps.saucedemo;

import com.codeborne.selenide.SelenideElement;
import ge.tbcacad.pages.saucedemo.SaucedemoHomePage;
import io.qameta.allure.Step;

public class SaucedemoHomeSteps {
    SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();

    @Step("After Authentication validate that all images are loaded for offers")
    public boolean checkInvLoading(){
        for (SelenideElement offer : saucedemoHomePage.inventoryItemDiv){
            if (saucedemoHomePage.inventoryImage == null){
                return false;
            }
        }
        return true;
    }
}
