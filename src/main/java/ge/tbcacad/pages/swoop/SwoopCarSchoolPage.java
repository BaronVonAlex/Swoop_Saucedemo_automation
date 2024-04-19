package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class SwoopCarSchoolPage {
    public SelenideElement
            firstOfferToFavorite = $$(".deal-basket-wishlist").get(1),
            firstOfferVoucherLimit = $$(".voucher-limit").get(1),
            carOffer = $$(".special-offer").get(1),
            specialOffer = $x("//div[@class='special-offer'][.//p[contains(text(), 'გაყიდულია 0')]]"),
            voucherDiagram = specialOffer.$(byAttribute("class", "voucher-limit")),
            locationSelector = $(".category-filter-desk").$("select.MultipleSelect.districts.ms-offscreen"),
            locationSelectorXpath = $x("//div[@class='category-filter-desk']//span[@class='placeholder']"),
            locationSaburtaloButton = $x("//div[@class='category-filter-desk']").find(By.xpath(".//span[contains(text(), 'საბურთალო')]")),
            locationH2 = $(".special-offer").$(".category-title"),
            minRangePrice = $(".category-filter-desk").$("input#minprice"),
            maxRangePrice = $(".category-filter-desk").$("input#maxprice"),
            locationButtonUpdate = $(".category-filter-desk").$(".ms-choice"),
            paymentMethodRadio = $(".category-filter-desk").$(byAttribute("value", "1")),
            filterDeleteButton = $(".category-filter-desk").$(".delete-search-button");
}
