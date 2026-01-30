package addressTest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import browser.Browser;
import pages.AddressPage;
import logintest.LoginTest;  // Import LoginTest to access ExtentReports

public class AddressTest {

    // Access the ExtentReports object from LoginTest (because it's static)
    private static ExtentTest test = LoginTest.test;  // Use the test instance from LoginTest

    // DataProvider for address test cases
    @DataProvider(name = "addresses")
    public static Object[][] getAddresses() {
        // Test data for address functionality (keyword, expected address)
        Object[][] data = { { "tcs gitanjali park", "kolkata" } };
        return data;
    }

    // Navigate to the home page before starting the tests
    @BeforeClass
    public static void setUp() {
        // Check if ExtentReports was already initialized in LoginTest
        if (test == null) {
            test = LoginTest.extent.createTest("Address Page Test", "Test Address functionality");
        }

        // Navigate to the homepage and verify the title
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");

        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
        test.pass("Navigated to the homepage and title verified successfully");
    }

    // Test case to verify address functionality using DataProvider
    @Test(dataProvider = "addresses")
    public static void testAddress(String keyWord, String fullAddress) {
        // Log the start of the address test
        test = LoginTest.extent.createTest("Verify Address Functionality", "Test entering and selecting an address");

        // Click on the address button to initiate address input
        AddressPage.clickOnAdressButton();
        test.info("Clicked on the Address button");

        // Enter the address in the input field
        AddressPage.enterAddressInInput(keyWord);
        test.info("Entered address keyword: " + keyWord);

        // Assert that the suggestion is clicked and address is selected correctly
        boolean isAddressSelected = AddressPage.clickOnSuggestion(keyWord, fullAddress);
        Assert.assertTrue(isAddressSelected);
        test.pass("Address suggestion clicked and address selected: " + fullAddress);
    }

    // Flush the report after all tests are complete
    @AfterClass
    public static void tearDown() {
        // Close the browser after the tests are done
        // Flush the Extent report to save the results
        LoginTest.extent.flush();
    }
}
