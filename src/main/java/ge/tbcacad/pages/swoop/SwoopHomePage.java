package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.selector.ByTagAndText;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SwoopHomePage {
    public SelenideElement
    swoopCategoryButton = $(".NewCategories.newcat"),
    swoopHolidaysButton = $x("//li[@class='MoreCategories']//a[@href='/category/24/dasveneba']");

}
