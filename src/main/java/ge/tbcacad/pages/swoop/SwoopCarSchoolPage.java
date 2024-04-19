package ge.tbcacad.pages.swoop;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class SwoopCarSchoolPage {
    public SelenideElement
        firstOfferToFavorite = $$(".deal-basket-wishlist").get(1);
}
