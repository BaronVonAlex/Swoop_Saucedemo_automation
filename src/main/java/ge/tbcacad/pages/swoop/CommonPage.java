package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CommonPage {
    public SelenideElement cookieAcceptButton = $x("//div[@class='cookieButton']//div[@class='acceptCookie']");

}
