package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class SwoopHolidayPage {
    public SelenideElement
    minRangePrice = $(".category-filter-desk").$("input#minprice"),
    maxRangePrice = $(".category-filter-desk").$("input#maxprice"),
    searchButton = $(".category-filter-desk").$(".submit-button"),
    categoryReblinks = $(".category-reblinks"),
    nextPageButton = $(byAttribute("src", "/Images/NewDesigneImg/categoryIn/arrow-01.png")),
    previousPageButton = $(byAttribute("src", "/Images/NewDesigneImg/categoryIn/arrow-02.png")),
    lastPage = $x("//div[@class='pagination']//img[@src='/Images/NewDesigneImg/categoryIn/arrow-03.png']"),
    lastPageButton = $(".pagination").$(byAttribute("src", "/Images/NewDesigneImg/categoryIn/arrow-03.png"));

    public ElementsCollection holidayOfferPrices = $$x("//p[@class='deal-voucher-price'][1]");
}
