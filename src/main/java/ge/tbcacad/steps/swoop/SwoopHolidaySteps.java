package ge.tbcacad.steps.swoop;

import com.codeborne.selenide.WebDriverRunner;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.util.swooputil.SwoopOffersUtil;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static ge.tbcacad.data.Constants.JS_SCROLL_MIDDLE;

public class SwoopHolidaySteps {

    SwoopHolidayPage swoopHolidayPage = new SwoopHolidayPage();

    @Step("Choose low price range bound")
    public SwoopHolidaySteps chooseLowerBound(String lowBound){
        swoopHolidayPage.minRangePrice.scrollIntoView(false).sendKeys(lowBound);
        return this;
    }

    @Step("Choose high price range bound")
    public SwoopHolidaySteps chooseHigherBound(String highBound){
        swoopHolidayPage.maxRangePrice.scrollIntoView(false).sendKeys(highBound);
        return this;
    }

    @Step("Return Range Values")
    public String rangeBoundChecker(String lowBound, String highBound){
        return "https://www.swoop.ge/category/24/dasveneba/?minprice=" + lowBound + "&maxprice=" + highBound;
    }

    @Step("Get current URL")
    public String currentURL(){
        return WebDriverRunner.url();
    }

    @Step("Click on Search button")
    public SwoopHolidaySteps clickOnSearchBtn(){
        swoopHolidayPage.searchButton.scrollIntoView(false).click();
        return this;
    }

    @Step("Scroll up to reblinks btn, to display upper navbar")
    public SwoopHolidaySteps scrollUpToBlinks(){
        executeJavaScript(JS_SCROLL_MIDDLE, swoopHolidayPage.categoryReblinks);
        return this;
    }

    @Step("Get all offer prices")
    public List<Double> getOfferPrices(){
        return SwoopOffersUtil.extractPricesFromElements(swoopHolidayPage.holidayOfferPrices);
    }
}
