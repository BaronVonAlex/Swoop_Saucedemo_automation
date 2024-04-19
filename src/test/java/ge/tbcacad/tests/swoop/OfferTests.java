package ge.tbcacad.tests.swoop;

import com.codeborne.selenide.Configuration;
import ge.tbcacad.data.swoop.SwoopDataProvider;
import ge.tbcacad.pages.common.CommonPage;
import ge.tbcacad.pages.swoop.*;
import ge.tbcacad.pages.tnet.TnetLoginPage;
import ge.tbcacad.steps.common.CommonSteps;
import ge.tbcacad.steps.swoop.*;
import ge.tbcacad.steps.tnet.TnetLoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbcacad.data.Constants.*;
import static org.openqa.selenium.devtools.v85.browser.Browser.close;

@Epic("Swoop tests")
@Feature("Offers, Filter, Sharing and Favorite offer functionality test")
public class OfferTests {
    protected static CommonSteps commonSteps;
    protected static CommonPage commonPage;
    protected static SwoopHomeSteps swoopHomeSteps;
    protected static SwoopHomePage swoopHomePage;
    protected static SwoopHolidaySteps swoopHolidaySteps;
    protected static SwoopHolidayPage swoopHolidayPage;
    protected static SwoopCarSchoolSteps swoopCarSchoolSteps;
    protected static SwoopCarSchoolPage swoopCarSchoolPage;
    protected static TnetLoginSteps tnetLoginSteps;
    protected static TnetLoginPage tnetLoginPage;
    private SoftAssert softAssert;

    @BeforeTest
    public void setUp() {
        commonSteps = new CommonSteps();
        commonPage = new CommonPage();
        swoopHomeSteps = new SwoopHomeSteps();
        swoopHomePage = new SwoopHomePage();
        swoopHolidaySteps = new SwoopHolidaySteps();
        swoopHolidayPage = new SwoopHolidayPage();
        swoopCarSchoolSteps = new SwoopCarSchoolSteps();
        swoopCarSchoolPage = new SwoopCarSchoolPage();
        tnetLoginSteps = new TnetLoginSteps();
        tnetLoginPage = new TnetLoginPage();
        softAssert = new SoftAssert();

        Configuration.browserSize = RSLT_1080P;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeMethod
    public void setUpWebsite() {
        open(SWOOP_LINK);
        commonSteps.acceptCookies();
    }

    @Test(dataProvider = "SwoopRangeDP", dataProviderClass = SwoopDataProvider.class,
            description = "navigate to Holiday Page, pick low and high bounds, then validate if all offers are within range.")
    public void rangeTest(String lowBound, String highBound) {
        commonSteps.click(swoopHomePage.swoopHolidaysButton);
        swoopHolidaySteps
                .chooseLowerBound(lowBound)
                .chooseHigherBound(highBound)
                .clickOnSearchBtn();
        softAssert.assertTrue(swoopHolidaySteps.priceRangeCheck(swoopHolidaySteps.getOfferPrices(), lowBound, highBound));

        swoopHolidaySteps.scrollUpToBlinks();
    }

    @Test(description = "From one of categories page, add first item to favorite list and verify if it takes us to Login page and vouchers are not sold")
    public void favouriteOfferTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        softAssert.assertTrue(swoopCarSchoolSteps.getVoucherLimit() < 100, VOUCHER_ASRT_MSG);
        swoopCarSchoolSteps.addToFavoriteList();
        softAssert.assertEquals(tnetLoginSteps.getPageName(), AUTH_EXP_TXT);
    }

    @Test(description = "Choose any sub-category and pick any item, verify if Facebook login-page shows up.")
    public void shareOfferTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        commonSteps.click(swoopCarSchoolPage.carOffer);
    }

    @AfterClass
    public void tearDown() {
        softAssert.assertAll();
        close();
    }
}
