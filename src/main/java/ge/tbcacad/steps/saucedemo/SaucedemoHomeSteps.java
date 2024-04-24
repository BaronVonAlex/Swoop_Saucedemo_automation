package ge.tbcacad.steps.saucedemo;

import com.codeborne.selenide.SelenideElement;
import ge.tbcacad.pages.saucedemo.SaucedemoHomePage;
import io.qameta.allure.Step;

import java.util.Objects;

public class SaucedemoHomeSteps {
    SaucedemoHomePage saucedemoHomePage = new SaucedemoHomePage();

    @Step("After Authentication validate that all images are loaded for offers")
    public boolean checkInvLoading() {
        for (SelenideElement inventoryItem : saucedemoHomePage.inventoryItemDiv) {
            SelenideElement inventoryImage = inventoryItem.$x(saucedemoHomePage.inventoryImageXpathExpression);
            if (!inventoryImage.exists()) {
                return false;
            }
        }
        return true;
    }

    @Step("Validate if Offers have appropriate images attached to it, fail test if they have 'Dog' image (fail-case)")
    public boolean checkProperImages() {
        for (SelenideElement offer : saucedemoHomePage.inventoryItemDiv) {
            SelenideElement inventoryImage = offer.$x(saucedemoHomePage.inventoryImageXpathExpression);
            if (Objects.requireNonNull(inventoryImage.getAttribute("src")).contains("/static/media/sl-404.168b1cce.jpg")) {
                return false;
            }
        }
        return true;
    }

    @Step("Click on burger Menu button to access Logout button, otherwise not visible")
    public SaucedemoHomeSteps clickBurgerBtn() {
        saucedemoHomePage.burgerMenuBtn.click();
        return this;
    }

    @Step("After opening burger menu, click on logout button")
    public SaucedemoHomeSteps clickLogoutBtn() {
        saucedemoHomePage.logoutBtn.click();
        return this;
    }
}
