package ge.tbcacad.pages.saucedemo;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class SaucedemoLoginPage {
    public SelenideElement
        loginInputField = $("#user-name"),
        passwordInputField = $("#password"),
        submitButton = $("#login-button"),
        sadFaceErrMsg = $(byAttribute("data-test", "error")),
        redXIcon = $(byAttribute("xmlns", "http://www.w3.org/2000/svg"));
}
