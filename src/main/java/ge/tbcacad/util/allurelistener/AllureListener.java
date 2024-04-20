package ge.tbcacad.util.allurelistener;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot();
        saveLogs(result.getThrowable().toString());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Attachment(value = "Stacktrace", type = "text/plain")
    public String saveLogs(String message) {
        return message;
    }
}
