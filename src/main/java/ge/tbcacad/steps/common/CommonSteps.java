package ge.tbcacad.steps.common;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import ge.tbcacad.pages.common.CommonPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    @Step("Click on {0} element/button.")
    public CommonSteps click(SelenideElement element) {
        element.click();

        return this;
    }

    @Step("Accept Cookies, continue if already accepted.")
    public CommonSteps acceptCookies() {
        try {
            commonPage.cookieAcceptButton.shouldBe(visible).click();
        } catch (ElementNotFound _) {
        }
        return this;
    }

    @Step("Scroll up to display hidden navbars.")
    public CommonSteps scrollUp() {
        executeJavaScript("window.scrollBy(0, -10);");
        return this;
    }

    @Step("go back to previous page")
    public CommonSteps goBackwards(){
        back();
        return this;
    }
}
