package ge.tbcacad.steps.swoop;

import ge.tbcacad.pages.swoop.SwoopHomePage;
import io.qameta.allure.Step;

public class SwoopHomeSteps {
    SwoopHomePage swoopHomePage = new SwoopHomePage();

    @Step("Move to holiday page by clicking button")
    public SwoopHomeSteps clickOnHolidayBtn(){
        swoopHomePage.swoopHolidaysButton.click();
        return this;
    }
}
