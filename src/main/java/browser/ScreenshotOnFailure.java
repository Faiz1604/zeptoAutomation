package browser;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

public class ScreenshotOnFailure implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String screenshotPath = ScreenshotUtil.captureScreenshot(Browser.driver, testName);

        if (BaseTest.test != null) {
            try {
                BaseTest.test.fail(
                        "Test Failed: " + result.getThrowable().getMessage(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
                );
            } catch (Exception e) {
                BaseTest.test.fail("Test Failed: " + result.getThrowable().getMessage());
                System.out.println("Could not attach screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (BaseTest.test != null) {
            BaseTest.test.pass(
                    "Test Passed: " + result.getMethod().getMethodName()
            );
        }
    }
}
