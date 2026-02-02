package browser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        // Create screenshots directory if it doesn't exist
        File screenshotDir = new File("/home/labuser/eclipse-workspace/Zepto/test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        
        // Create a timestamped filename for the screenshot
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        
        // Take the screenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        
        // Define the destination path
        String destinationPath = "./screenshots/" + fileName;
        File destinationFile = new File(destinationPath);
        
        try {
            // Save the screenshot
            FileHandler.copy(screenshotFile, destinationFile);
            System.out.println("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Error saving screenshot: " + e.getMessage());
        }
        
        return destinationPath;
    }
}
