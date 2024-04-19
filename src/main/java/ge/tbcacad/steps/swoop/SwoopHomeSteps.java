package ge.tbcacad.steps.swoop;

import ge.tbcacad.pages.swoop.SwoopHomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class SwoopHomeSteps {
    SwoopHomePage swoopHomePage = new SwoopHomePage();

    @Step("Move to holiday page by clicking button")
    public SwoopHomeSteps clickOnHolidayBtn() {
        swoopHomePage.swoopHolidaysButton.click();
        return this;
    }

    @Step("Hover over category")
    public SwoopHomeSteps hoverOverCategory() {
        swoopHomePage.swoopSportCategory.hover();
        return this;
    }

    @Step("Click on cat school button")
    public SwoopHomeSteps clickOnCarSchool() {
        swoopHomePage.swoopCarSchoolButton.shouldBe(visible).click();
        return this;
    }
}
