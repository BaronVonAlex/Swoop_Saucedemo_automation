package ge.tbcacad.tests.swoop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
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
import ge.tbcacad.util.allurelistener.AllureListener;
import ge.tbcacad.util.allurelistener.CustomTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbcacad.data.Constants.*;
import static org.openqa.selenium.devtools.v85.browser.Browser.close;

@Epic("Swoop tests")
@Feature("Offers, Filter, Sharing and Favorite offer functionality test")
@Listeners({AllureListener.class, CustomTestListener.class, ScreenShooter.class})
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

    @BeforeTest(groups = "SwoopRegression")
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

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        softAssert = new SoftAssert();

        Configuration.browserSize = RSLT_1080P;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeMethod(groups = "SwoopRegression")
    public void setUpWebsite() {
        open(SWOOP_LINK);
        commonSteps.acceptCookies();
    }

    @Story("Price range and Filter validation")
    @Test(dataProvider = "SwoopRangeDP", dataProviderClass = SwoopDataProvider.class,
            description = "navigate to Holiday Page, pick low and high bounds, then validate if all offers are within range.",
            groups = "SwoopRegression")
    public void rangeTest(String lowBound, String highBound) {
        commonSteps.click(swoopHomePage.swoopHolidaysButton);
        swoopHolidaySteps
                .chooseLowerBound(lowBound)
                .chooseHigherBound(highBound)
                .clickOnSearchBtn();
        softAssert.assertTrue(swoopHolidaySteps.priceRangeCheck(swoopHolidaySteps.getOfferPrices(), lowBound, highBound), PRICE_RANGE_ERR_MSG);
        swoopHolidaySteps.scrollUpToBlinks();
    }

    @Story("Favorite item button, Login popup and request tests")
    @Test(description = "From one of categories page, add first item to favorite list and verify if it takes us to Login page and vouchers are not sold",
            groups = "SwoopRegression")
    public void favouriteOfferTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        softAssert.assertTrue(swoopCarSchoolSteps.getVoucherLimit() < 100, VOUCHER_ASRT_MSG);
        swoopCarSchoolSteps.addToFavoriteList();
        softAssert.assertEquals(tnetLoginSteps.getPageName(), AUTH_EXP_TXT, AUTH_PAGE_ERR_MSG);
    }

    @Story("Share offer and Facebook login popup tests")
    @Test(description = "Choose any sub-category and pick any item, verify if Facebook login-page shows up.",
            groups = "SwoopRegression")
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

    @Story("Voucher bar tests")
    @Test(description = "Find offer that has 0 sold, validate that Voucher bas has not moved at all(Value is at 0)",
            groups = "SwoopRegression")
    public void noOffersSoldTest() {
        commonSteps.click(swoopHomePage.swoopCategoryButton);
        swoopHomeSteps
                .hoverOverCategory()
                .clickOnCarSchool();
        softAssert.assertEquals(swoopCarSchoolSteps.getOfferWithZeroSold(), 0.0, ZERO_SOLD_ERR_MSG);
    }

    @Story("Price range and Filter validation")
    @Test(description = "Change Location, Payment and price filters, then clean and validate that they are indeed empty",
            groups = "SwoopRegression")
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

    @AfterTest(groups = "SwoopRegression")
    public void tearDown() {
        softAssert.assertAll();
        close();
    }
}
