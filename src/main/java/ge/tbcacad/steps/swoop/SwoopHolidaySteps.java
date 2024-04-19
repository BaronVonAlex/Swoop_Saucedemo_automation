package ge.tbcacad.steps.swoop;

import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.util.swooputil.SwoopOffersUtil;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static ge.tbcacad.data.Constants.JS_SCROLL_MIDDLE;

public class SwoopHolidaySteps {

    SwoopHolidayPage swoopHolidayPage = new SwoopHolidayPage();

    @Step("Choose low {0} price range bound")
    public SwoopHolidaySteps chooseLowerBound(String lowBound) {
        swoopHolidayPage.minRangePrice.scrollIntoView(false).sendKeys(lowBound);
        return this;
    }

    @Step("Choose high {0} price range bound")
    public SwoopHolidaySteps chooseHigherBound(String highBound) {
        swoopHolidayPage.maxRangePrice.scrollIntoView(false).sendKeys(highBound);
        return this;
    }

    @Step("Click on Search button")
    public SwoopHolidaySteps clickOnSearchBtn() {
        swoopHolidayPage.searchButton.scrollIntoView(false).click();
        return this;
    }

    @Step("Scroll up to reblinks btn, to display upper navbar")
    public SwoopHolidaySteps scrollUpToBlinks() {
        executeJavaScript(JS_SCROLL_MIDDLE, swoopHolidayPage.categoryReblinks);
        return this;
    }

    @Step("Get all offer prices")
    public List<Double> getOfferPrices() {
        return SwoopOffersUtil.extractPricesFromElements(swoopHolidayPage.holidayOfferPrices);
    }

    @Step("Check if all items are within price range of {0}, {1}")
    public boolean priceRangeCheck(List<Double> prices, String lowerBound, String upperBound) {
        for (Double price : prices) {
            if (price < Double.parseDouble(lowerBound) || price > Double.parseDouble(upperBound)) {
                System.out.println("Price was out of filter range: " + price + " | Min Allowed - " + lowerBound + " | Max Allowed - " + upperBound);
                return false;
            }
        }
        return true;
    }
}
