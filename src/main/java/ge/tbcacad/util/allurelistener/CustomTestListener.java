package ge.tbcacad.util.allurelistener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.screenshot;

public class CustomTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        String time = String.valueOf(System.currentTimeMillis());

        try (InputStream file = Files.newInputStream(Path.of(Objects.requireNonNull(screenshot(OutputType.FILE)).getAbsolutePath()))) {
            Allure.addAttachment(STR."error_screenshot_\{time}.png", "image/png", file.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
