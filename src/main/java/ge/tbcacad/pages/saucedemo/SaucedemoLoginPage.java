package ge.tbcacad.pages.saucedemo;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SaucedemoLoginPage {
    public SelenideElement
            loginInputField = $("#user-name"),
            passwordInputField = $("#password"),
            submitButton = $("#login-button"),
            sadFaceErrMsg = $(byAttribute("data-test", "error")),
            redXIcon = $("button.error-button").$(byAttribute("xmlns", "http://www.w3.org/2000/svg")),
            loginXIcon = $x("//div[@class='form_group'][1]").$(byAttribute("xmlns", "http://www.w3.org/2000/svg")),
            passXIcon = $x("//div[@class='form_group'][2]").$(byAttribute("xmlns", "http://www.w3.org/2000/svg"));
}
