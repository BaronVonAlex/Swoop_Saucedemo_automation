package ge.tbcacad.tests.swoop;

import com.codeborne.selenide.Configuration;
import ge.tbcacad.data.swoop.SwoopDataProvider;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.pages.swoop.SwoopHomePage;
import ge.tbcacad.steps.swoop.CommonSteps;
import ge.tbcacad.steps.swoop.SwoopHolidaySteps;
import ge.tbcacad.steps.swoop.SwoopHomeSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.C;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbcacad.data.Constants.SWOOP_LINK;

@Epic("Swoop tests")
@Feature("Offers, Filter, Sharing and Favorite offer functionality test")
public class OfferTests {
    protected static CommonSteps commonSteps;
    protected static SwoopHomeSteps swoopHomeSteps;
    protected static SwoopHolidaySteps swoopHolidaySteps;
    private SoftAssert softAssert;

    @BeforeTest
    public void setUp(){
        commonSteps = new CommonSteps();
        swoopHomeSteps = new SwoopHomeSteps();
        swoopHolidaySteps = new SwoopHolidaySteps();
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void setUpWebsite(){
        Configuration.browserSize = "1920x1080";
        open(SWOOP_LINK);
        commonSteps.acceptCookies();
    }

    @Test(dataProvider = "SwoopRangeDP",dataProviderClass = SwoopDataProvider.class,
            description = "navigate to Holiday Page, pick low and high bounds, then validate if all offers are within range.")
    public void rangeTest(String lowBound, String highBound) {
        swoopHomeSteps.clickOnHolidayBtn();
        swoopHolidaySteps
                .chooseLowerBound(lowBound)
                .chooseHigherBound(highBound)
                .clickOnSearchBtn();

        for(Double doite : swoopHolidaySteps.getOfferPrices()){
            System.out.println(doite);
        }

        swoopHolidaySteps.scrollUpToBlinks();
        Configuration.holdBrowserOpen = true;
    }
}
