package ge.tbcacad.steps.tnet;

import ge.tbcacad.pages.tnet.TnetLoginPage;
import io.qameta.allure.Step;

public class TnetLoginSteps {
    TnetLoginPage tnetLoginPage = new TnetLoginPage();

    @Step("Verify that we were taken on Tnet Auth page.")
    public String getPageName() {
        return tnetLoginPage.authText.getText();
    }
}
