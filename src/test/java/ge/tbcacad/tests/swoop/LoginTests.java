package ge.tbcacad.tests.swoop;

import com.codeborne.selenide.Configuration;
import ge.tbcacad.pages.saucedemo.SaucedemoHomePage;
import ge.tbcacad.pages.saucedemo.SaucedemoLoginPage;
import ge.tbcacad.steps.saucedemo.SaucedemoHomeSteps;
import ge.tbcacad.steps.saucedemo.SaucedemoLoginSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static ge.tbcacad.data.Constants.*;
import static org.openqa.selenium.devtools.v122.browser.Browser.close;

@Epic("Saucedemo Login tests")
@Feature("Use Database to get credentials and test different type of users")
public class LoginTests {
    protected static SaucedemoLoginPage saucedemoLoginPage;
    protected static SaucedemoLoginSteps saucedemoLoginSteps;
    protected static SaucedemoHomePage saucedemoHomePage;
    protected static SaucedemoHomeSteps saucedemoHomeSteps;
    private SoftAssert softAssert;

    @BeforeTest
    public void setUp(){
        saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginSteps = new SaucedemoLoginSteps();
        saucedemoHomePage = new SaucedemoHomePage();
        saucedemoHomeSteps = new SaucedemoHomeSteps();

        softAssert = new SoftAssert();

        Configuration.browserSize = RSLT_1080P;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeMethod
    public void setUpWebpage(){
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

    @Test(description = "Make query from Database to get desired credentials and validate if all images are loaded after login.")
    public void successfulLoginTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(1)
                .inputPasswordWithDB(1)
                .loginWithButton();
        softAssert.assertTrue(saucedemoHomeSteps.checkInvLoading(), "All images are not properly loaded");
    }

    @Test(description = "Login with locked out user and validate that proper Messages show up on login page.")
    public void bannedUserLoginTest() throws SQLException {
        saucedemoLoginSteps
                .inputLoginWithDB(2)
                .inputPasswordWithDB(2)
                .loginWithButton();
        softAssert.assertEquals(saucedemoLoginSteps.getSadFaceErrorMsg(), SAD_FACE_ERR_MSG, SAD_FACE_ERR_FAIL);
        softAssert.assertTrue(saucedemoLoginSteps.getRedXBoolean(), RED_X_ERR_MSG);
        Configuration.holdBrowserOpen = true;
    }

    @AfterClass
    public void tearDown(){
        softAssert.assertAll();
        close();
    }
}
