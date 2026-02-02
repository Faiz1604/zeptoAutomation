package categoryTest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import browser.ScreenshotOnFailure;
import browser.Browser;
import pages.CategoryAndSubCategory;
import logintest.LoginTest;  // Import LoginTest to access the ExtentReports instance
@Listeners(ScreenshotOnFailure.class)

public class CategoryAndSubCategoryTest {

    // Access the ExtentReports object from LoginTest (because it's static)
    private static ExtentTest test = LoginTest.test;  // Use the test instance from LoginTest

    // Navigate to the home page before starting the tests
    @BeforeClass
    public static void setUp() {
        // Check if ExtentReports was already initialized in LoginTest
        if (test == null) {
            test = LoginTest.extent.createTest("Category and Subcategory Test", "Test Category and Subcategory functionality");
        }

        // Navigate to the homepage and verify the title
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");

        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
        test.pass("Navigated to the homepage and title verified successfully");
    }

    // DataProvider for category test cases
    @DataProvider(name = "categories")
    public static Object[][] categoryData() {
        // Test data for category functionality (category name)
        Object[][] data = { { "all"} };
        return data;
    }

    // Test case to verify category functionality using DataProvider
    @Test(dataProvider = "categories")
    public static void testCategory(String categoryText) {
        // Log the start of the category test
        test = LoginTest.extent.createTest("Verify Category: " + categoryText, "Test if category exists");

        // Check if the category exists and log the result
        boolean condition = CategoryAndSubCategory.checkIfExist(categoryText);
        Assert.assertTrue(condition);
        test.pass("Category '" + categoryText + "' exists and is verified successfully");
    }

    // Flush the report after all tests are complete
    @AfterClass
    public static void tearDown() {

        // Flush the Extent report to save the results
        LoginTest.extent.flush();
    }
}
