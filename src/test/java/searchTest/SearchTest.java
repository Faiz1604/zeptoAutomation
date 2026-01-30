package searchTest;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;

import browser.Browser;
import pages.ProductSearchPage;
import logintest.LoginTest;  // Import LoginTest to access the ExtentReports instance

public class SearchTest {

    // Access the ExtentReports object from LoginTest (because it's static)
    private static ExtentTest test = LoginTest.test;  // Use the test instance from LoginTest

    // Open browser and navigate to the home page before starting the tests
    @BeforeClass
    public static void setUp() {
        // Check if ExtentReports was already initialized in LoginTest
        if (test == null) {
            test = LoginTest.extent.createTest("Search Page Test", "Test Product Search Functionality");
        }

        // Navigate to the homepage and verify the title (you can call this from Browser or LoginPage)
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");

        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
        test.pass("Navigated to the homepage and title verified successfully");
    }

    // DataProvider for the test cases
    @DataProvider(name = "searchData")
    public static Object[][] dataToSearch() {
        // Test data for search functionality (keyword, expected product)
        Object[][] data = {{"boo", "Boost"}};
        return data;
    }

    // Test case to verify product search functionality
    @Test(dataProvider = "searchData")
    public static void testProductSearch(String keyword, String product) {
        // Log the start of the product search test
        test = LoginTest.extent.createTest("Verify Product Search", "Test searching for a product and verifying the result");

        // Use ProductSearchPage to check if the cart and product match
        boolean assumption = ProductSearchPage.checkCartAndCompare(keyword, product);

        // Assert that the assumption holds true (product found in search)
        Assert.assertTrue(assumption);
        test.pass("Product search successful for keyword: " + keyword + " and found product: " + product);
    }

    // Close the browser and flush the report after all tests are complete
    @AfterClass
    public static void tearDown() {

        // Flush the Extent report to save the results
        LoginTest.extent.flush();
    }
}
