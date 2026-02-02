package browser;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter sparkReporter;
    
    public static void initializeExtentReports(String reportPath) {
        sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Tester");
    }
    
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}