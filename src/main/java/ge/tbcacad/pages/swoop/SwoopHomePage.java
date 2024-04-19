package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SwoopHomePage {
    public SelenideElement
            swoopCategoryButton = $(".NewCategories.newcat"),
            swoopHolidaysButton = $x("//li[@class='MoreCategories']//a[@href='/category/24/dasveneba']"),
            swoopSportCategory = $(byAttribute("cat_id", "CatId-7")),
            swoopCarSchoolButton = $(byAttribute("href", "/category/282/avtosamyaro/avtoskola"));
}
