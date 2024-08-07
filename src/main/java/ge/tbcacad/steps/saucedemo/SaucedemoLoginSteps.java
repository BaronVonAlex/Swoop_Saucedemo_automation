package ge.tbcacad.steps.saucedemo;

import ge.tbcacad.data.dbconnection.DatabaseSteps;
import ge.tbcacad.pages.saucedemo.SaucedemoLoginPage;
import io.qameta.allure.Step;

public class SaucedemoLoginSteps {
    SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

    @Step("Input login details from Database with ID : {0}")
    public SaucedemoLoginSteps inputLoginWithDB(int ID) {
        saucedemoLoginPage.loginInputField.sendKeys(DatabaseSteps.getLogin(ID));
        return this;
    }

    @Step("Input password details from Databasewith ID : {0}")
    public SaucedemoLoginSteps inputPasswordWithDB(int ID) {
        saucedemoLoginPage.passwordInputField.sendKeys(DatabaseSteps.getPassword(ID));
        return this;
    }

    @Step("Click on login button to authorize user")
    public SaucedemoLoginSteps loginWithButton() {
        saucedemoLoginPage.submitButton.click();
        return this;
    }

    @Step("Get String message from 'SadFace error message box'")
    public String getSadFaceErrorMsg() {
        return saucedemoLoginPage.sadFaceErrMsg.getText();
    }

    @Step("Check if red X icon also appeared")
    public boolean getRedXBoolean() {
        return saucedemoLoginPage.redXIcon.exists();
    }

    @Step("Check if red X icon also appeared for login field")
    public boolean getLoginRedXBoolean() {
        return saucedemoLoginPage.loginXIcon.exists();
    }


    @Step("Check if red X icon also appeared for password field")
    public boolean getPasswordRedXBoolean() {
        return saucedemoLoginPage.passXIcon.exists();
    }


    @Step("Return Value from Login input field")
    public String getLoginValue() {
        return saucedemoLoginPage.loginInputField.getValue();
    }

    @Step("Return Value from Password input field")
    public String getPasswordValue() {
        return saucedemoLoginPage.passwordInputField.getValue();
    }
}
