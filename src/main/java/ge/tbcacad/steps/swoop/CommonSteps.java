package ge.tbcacad.steps.swoop;

import com.codeborne.selenide.ex.ElementNotFound;
import ge.tbcacad.pages.swoop.CommonPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CommonSteps {
    CommonPage commonPage = new CommonPage();

    @Step("Accept Cookies")
    public CommonSteps acceptCookies(){
        try{
            commonPage.cookieAcceptButton.shouldBe(visible).click();
        } catch (ElementNotFound _){}
        return this;
    }

    @Step("Scroll up to display hidden navbars")
    public CommonSteps scrollUp(){
        executeJavaScript("window.scrollBy(0, -10);");
        return this;
    }
}
