package logintest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;  // Use ExtentSparkReporter
import browser.Browser;
import pages.LoginPage;

public class LoginTest {

	 public static ExtentReports extent;      // ExtentReports object (public static)
	    public static ExtentTest test;           // ExtentTest object (public static)
	    public static ExtentSparkReporter sparkReporter;
 
    // Open browser before starting the suite
    @BeforeSuite
    public static void setUpSuite() {
        // Initialize the Extent Spark Reporter
        sparkReporter = new ExtentSparkReporter("/home/labuser/eclipse-workspace/Zepto/test-output/extendsReport");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info (optional)
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Tester");

        // Initialize the browser (launch the browser)
        Browser.openBrowser();
    }

    // Navigate to the login page and verify the title before starting the test class
    @BeforeClass
    public static void setUp() {
        // Start test logging in ExtentReports
        test = extent.createTest("Login Page Test", "Verify login functionality");

        // Verify that the page title matches the expected title
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");

        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
        test.pass("Page title verified successfully");

        // Click on the login button to proceed with login actions
        LoginPage.clickOnLoginButton();
        test.pass("Login button clicked");
    }

    // Test case to verify the default state of the 'Continue' button (should be disabled initially)
    @Test(priority = 1)
    public static void VerifyDefaultButtonState() {
        test = extent.createTest("Verify Default Button State", "Test default state of continue button");

        // Assert that the continue button is initially disabled
        Assert.assertFalse(LoginPage.buttonEnabled());
        test.pass("Continue button is disabled as expected");
    }

    // DataProvider for test cases with phone numbers greater than 10 digits
    @DataProvider(name = "moreThan10DigitNumbers")
    public static Object[][] moreThan10DigitNumber() {
        return new Object[][] { { "962164185612" }, { "962164185623" }, { "962164185634" } };
    }

    // Test case to verify the validation of phone numbers longer than 10 digits
    @Test(priority = 2, dataProvider = "moreThan10DigitNumbers")
    public static void greaterLengthPhoneNumberInputFieldValidation(String number) {
        test = extent.createTest("Validate Phone Number > 10 Digits", "Test validation of phone numbers with more than 10 digits");

        // Verify that the number entered is truncated to 10 digits
        String fieldValue = LoginPage.verifyInputField(number);

        // Assert that the field value length is 10 and the entered number is different (truncated)
        boolean idealCondition = fieldValue.length() == 10 && !number.equals(fieldValue);
        Assert.assertTrue(idealCondition);
        test.pass("Phone number was truncated correctly");

        // Assert that the continue button is enabled
        Assert.assertTrue(LoginPage.buttonEnabled());
        test.pass("Continue button is enabled after truncation");
    }

    // DataProvider for test cases with phone numbers less than 10 digits
    @DataProvider(name = "lessThan10DigitNumbers")
    public static Object[][] lessThan10DigitNumber() {
        return new Object[][] { { "9" }, { "962164185" }, { "96216" } };
    }

    // Test case to verify the validation of phone numbers shorter than 10 digits
    @Test(priority = 3, dataProvider = "lessThan10DigitNumbers")
    public static void lesserLengthPhoneNumberInputFieldValidation(String number) {
        test = extent.createTest("Validate Phone Number < 10 Digits", "Test validation of phone numbers with less than 10 digits");

        // Verify that the entered phone number is shown correctly in the input field
        String fieldValue = LoginPage.verifyInputField(number);

        // Assert that the field value matches the input and the continue button is disabled
        Assert.assertEquals(fieldValue, number);
        test.pass("Phone number is displayed correctly in the input field");

        Assert.assertFalse(LoginPage.buttonEnabled());
        test.pass("Continue button is disabled as expected");
    }

    // DataProvider for test case with valid phone number and OTP heading text
    @DataProvider(name = "validPhoneNumber")
    public static Object[][] getValidPhoneNumber() {
        return new Object[][] { { "3426963790", "OTP Verification" } };
    }

    // Test case to verify a valid phone number and the correct OTP heading text
    @Test(priority = 4, dataProvider = "validPhoneNumber")
    public void validPhoneNumberValidation(String number, String otpText) {
        test = extent.createTest("Valid Phone Number Test", "Test valid phone number and OTP verification");

        // Verify that the login process works and the OTP heading matches the expected text
        boolean otpPage = LoginPage.validLoginVerification(number, otpText);

        // Assert that the user is directed to the OTP page with the correct heading
        Assert.assertTrue(otpPage);
        test.pass("Valid phone number leads to OTP verification page");
    }

    // Close the browser after the suite completes
    @AfterSuite
    public static void tearDown() {
        // Close the browser and clean up resources
    	Browser.closeBrowser();
        extent.flush();  // Save and flush the report to disk
    }
}
