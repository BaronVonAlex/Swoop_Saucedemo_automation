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

    @Step("Find element that has 0 sold value")
    public Double getOfferWithZeroSold() {
        return Double.parseDouble(swoopCarSchoolPage.voucherDiagram.getAttribute("data-width"));
    }

    @Step("Change location of offers")
    public SwoopCarSchoolSteps changeLocation() {
        swoopCarSchoolPage.locationSelectorXpath.click();
        System.out.println("text for conflict");
        return this;
    }

    @Step("Choose low {0} price range bound")
    public SwoopCarSchoolSteps chooseLowerBound(String lowBound) {
        swoopCarSchoolPage.minRangePrice.scrollIntoView(false).sendKeys(lowBound);
        return this;
    }

    @Step("Choose high {0} price range bound")
    public SwoopCarSchoolSteps chooseHigherBound(String highBound) {
        swoopCarSchoolPage.maxRangePrice.scrollIntoView(false).sendKeys(highBound);
        return this;
    }

    @Step("Pick voucher as payment method")
    public SwoopCarSchoolSteps pickVoucherAsPayment() {
        swoopCarSchoolPage.paymentMethodRadio.click();
        return this;
    }

    @Step("Erase filter by clicking on delete button")
    public SwoopCarSchoolSteps eraseFilter() {
        swoopCarSchoolPage.filterDeleteButton.click();
        return this;
    }
}
