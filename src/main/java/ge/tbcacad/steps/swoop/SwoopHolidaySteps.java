package ge.tbcacad.steps.swoop;

import com.codeborne.selenide.WebDriverRunner;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.util.swooputil.SwoopOffersUtil;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

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

    @Step("Check if all items are within price range")
    public boolean priceRangeCheck(List<Double> prices, String lowerBound, String upperBound) {
        // Iterate through each price in the list
        for (Double price : prices) {
            // Check if the price is not within the specified range
            if (price < Double.parseDouble(lowerBound) || price > Double.parseDouble(upperBound)) {
                System.out.println("Price that was out of Price range: " + price);
                return false; // Return false if any price falls outside the range
            }
        }
        return true; // Return true if all prices are within the range
    }
}
