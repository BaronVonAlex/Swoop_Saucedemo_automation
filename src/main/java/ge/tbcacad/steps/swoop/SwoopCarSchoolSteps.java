package ge.tbcacad.steps.swoop;

import ge.tbcacad.pages.swoop.SwoopCarSchoolPage;
import io.qameta.allure.Step;

public class SwoopCarSchoolSteps {
    SwoopCarSchoolPage swoopCarSchoolPage = new SwoopCarSchoolPage();

    @Step("Click on first item to add in favorite list")
    public SwoopCarSchoolSteps addToFavoriteList() {
        swoopCarSchoolPage.firstOfferToFavorite.click();
        return this;
    }

    @Step("Validate that Voucher isn't sold by checking if Value is less than 100")
    public Double getVoucherLimit() {
        return Double.parseDouble(swoopCarSchoolPage.firstOfferVoucherLimit.getAttribute("data-width"));
    }
}
