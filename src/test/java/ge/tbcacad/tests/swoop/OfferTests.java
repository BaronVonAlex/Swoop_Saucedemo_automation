package ge.tbcacad.tests.swoop;

import com.codeborne.selenide.Configuration;
import ge.tbcacad.data.swoop.SwoopDataProvider;
import ge.tbcacad.pages.commonpage.CommonPage;
import ge.tbcacad.pages.swoop.SwoopCarSchoolPage;
import ge.tbcacad.pages.swoop.SwoopHolidayPage;
import ge.tbcacad.pages.swoop.SwoopHomePage;
import ge.tbcacad.pages.swoop.SwoopOfferPage;
import ge.tbcacad.pages.tnet.TnetLoginPage;
import ge.tbcacad.steps.commonsteps.CommonSteps;
import ge.tbcacad.steps.swoop.SwoopCarSchoolSteps;
import ge.tbcacad.steps.swoop.SwoopHolidaySteps;
import ge.tbcacad.steps.swoop.SwoopHomeSteps;
import ge.tbcacad.steps.swoop.SwoopOfferSteps;
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
    protected static SwoopOfferSteps swoopOfferSteps;
    protected static SwoopOfferPage swoopOfferPage;
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
        swoopOfferSteps = new SwoopOfferSteps();
        swoopOfferPage = new SwoopOfferPage();
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
        commonSteps
                .click(swoopCarSchoolPage.carOffer)
                .click(swoopOfferPage.shareButton);
        softAssert.assertTrue(swoopOfferSteps.switchToFBWindow().contains(FB_LOGIN_LINK), FB_LOG_ERR_MSG);
    }

    @Test(description = "Find offer that has 0 sold, validate that Voucher bas has not moved at all(Value is at 0)")
    public void noOffersSoldTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        softAssert.assertEquals(swoopCarSchoolSteps.getOfferWithZeroSold(), 0.0);
    }

    @Test
    public void clearFilterTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        commonSteps
                .click(swoopCarSchoolPage.locationSelectorXpath)
                .click(swoopCarSchoolPage.locationSaburtaloButton)
                .click(swoopCarSchoolPage.locationButtonUpdate);
        swoopCarSchoolSteps
                .pickVoucherAsPayment()
                .chooseLowerBound(LOW_RANGE_PRICE)
                .chooseHigherBound(HIGH_RANGE_PRICE)
                .eraseFilter();
        softAssert.assertTrue(swoopCarSchoolPage.locationSelector.getSelectedOptions().texts().isEmpty(), LOC_ERR_MSG);
        softAssert.assertEquals(swoopCarSchoolPage.minRangePrice.getValue(), VAL_EXP_TXT, MIN_BOUND_ERR_MSG);
        softAssert.assertEquals(swoopCarSchoolPage.maxRangePrice.getValue(), VAL_EXP_TXT, MAX_BOUND_ERR_MSG);
        softAssert.assertEquals(swoopCarSchoolPage.paymentMethodRadio.getValue(), PAYMENT_EXP_TXT, PAYMENT_ERR_MSG);
    }

    @AfterClass
    public void tearDown() {
        softAssert.assertAll();
        close();
    }
}
