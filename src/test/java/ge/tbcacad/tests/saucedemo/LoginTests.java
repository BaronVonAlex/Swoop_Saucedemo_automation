package ge.tbcacad.tests.saucedemo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import ge.tbcacad.pages.saucedemo.SaucedemoHomePage;
import ge.tbcacad.pages.saucedemo.SaucedemoLoginPage;
import ge.tbcacad.steps.saucedemo.SaucedemoHomeSteps;
import ge.tbcacad.steps.saucedemo.SaucedemoLoginSteps;
import ge.tbcacad.util.allurelistener.AllureListener;
import ge.tbcacad.util.allurelistener.CustomTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbcacad.data.Constants.*;
import static org.openqa.selenium.devtools.v122.browser.Browser.close;


@Epic("Saucedemo Login tests")
@Feature("Use Database to get credentials and test different type of users")
@Listeners({AllureListener.class, CustomTestListener.class, ScreenShooter.class})
public class LoginTests {
    protected static SaucedemoLoginPage saucedemoLoginPage;
    protected static SaucedemoLoginSteps saucedemoLoginSteps;
    protected static SaucedemoHomePage saucedemoHomePage;
    protected static SaucedemoHomeSteps saucedemoHomeSteps;
    private SoftAssert softAssert;

    @BeforeTest(groups = "SauceDemoLogin")
    public void setUp() {
        saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginSteps = new SaucedemoLoginSteps();
        saucedemoHomePage = new SaucedemoHomePage();
        saucedemoHomeSteps = new SaucedemoHomeSteps();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        softAssert = new SoftAssert();

        Configuration.browserSize = RSLT_1080P;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeMethod(groups = "SauceDemoLogin")
    public void setUpWebpage() {
        open(SAUCEDEMO_LINK);
    }

    /**
     * User ID, Login, Password:
     * 1 - standard_user, secret_sauce
     * 2 - locked_out_user, secret_sauce
     * 3 - problem_user, secret_sauce
     * 4 - performance_glitch_user, secret_sauce
     * 5 - error_user, secret_sauce
     * 6 - visual_user, secret_sauce
     **/

    @Story("Image Loading tests")
    @Test(description = "Make query from Database to get desired credentials and validate if all images are loaded after login.",
            groups = "SauceDemoLogin")
    public void successfulLoginTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(1)
                .inputPasswordWithDB(1)
                .loginWithButton();
        softAssert.assertTrue(saucedemoHomeSteps.checkInvLoading(), IMG_LOAD_ERR);
    }

    @Story("Authentication / Logout Tests")
    @Test(description = "Login with locked out user and validate that proper Messages and Icons show up on login page.",
            groups = "SauceDemoLogin")
    public void bannedUserLoginTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(2)
                .inputPasswordWithDB(2)
                .loginWithButton();
        softAssert.assertEquals(saucedemoLoginSteps.getSadFaceErrorMsg(), SAD_FACE_ERR_MSG, SAD_FACE_ERR_FAIL);
        softAssert.assertTrue(saucedemoLoginSteps.getRedXBoolean(), RED_X_ERR_MSG);
    }

    @Story("Image Loading tests")
    @Test(description = "Login with Problematic user and validate if proper images load, or load at all.",
            groups = "SauceDemoLogin")
    public void problematicLoginTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(3)
                .inputPasswordWithDB(3)
                .loginWithButton();
        softAssert.assertTrue(saucedemoHomeSteps.checkProperImages(), PROPER_IMG_ERR_MSG);
        softAssert.assertTrue(saucedemoHomeSteps.checkInvLoading(), IMG_LOAD_ERR);
    }

    @Story("Authentication / Logout Tests")
    @Test(description = "Login with standard user and then logout, Validate if input fields are empty.",
            groups = "SauceDemoLogin")
    public void logOutTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(1)
                .inputPasswordWithDB(1)
                .loginWithButton();
        saucedemoHomeSteps
                .clickBurgerBtn()
                .clickLogoutBtn();
        softAssert.assertEquals(saucedemoLoginSteps.getLoginValue(), VAL_EXP_TXT);
        softAssert.assertEquals(saucedemoLoginSteps.getPasswordValue(), VAL_EXP_TXT);
    }

    @AfterClass
    public void tearDown() {
        softAssert.assertAll();
        close();
    }
}
