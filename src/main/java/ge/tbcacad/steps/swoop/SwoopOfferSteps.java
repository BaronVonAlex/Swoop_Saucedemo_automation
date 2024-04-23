package ge.tbcacad.steps.swoop;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

public class SwoopOfferSteps {
    @Step("Get all windows and switch to new one, return link as String.")
    public String switchToFBWindow() {
        String activeWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        String newWindowHandle = WebDriverRunner.getWebDriver().getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(activeWindow))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No new window found"));
        WebDriverRunner.getWebDriver().switchTo().window(newWindowHandle);
        return WebDriverRunner.url();
    }
}
