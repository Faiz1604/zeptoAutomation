package logintest;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import browser.Browser;
import pages.LoginPage;

public class LoginTest {

	// Open browser before starting the suite
	@BeforeSuite
	public static void openPage() {
		// Initialize the browser (launch the browser)
		Browser.openBrowser();
	}

	// Navigate to the login page and verify the title before starting the test
	// class
	@BeforeClass
	public static void loginPage() {
		// Verify that the page title matches the expected title
		boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
				+ " Get all your groceries delivered to you in minutes. | https://www.zepto.com");

		// Assert that the title matches the expected value (this checks if we are on
		// the correct page)
		Assert.assertTrue(isTitleMatched);

		// Click on the login button to proceed with login actions
		LoginPage.clickOnLoginButton();
	}

	// Test case to verify the default state of the 'Continue' button (should be
	// disabled initially)
	@Test(priority = 1)
	public static void VerifyDefaultButtonState() {
		// Assert that the continue button is initially disabled
		Assert.assertFalse(LoginPage.buttonEnabled());
	}

	// DataProvider for test cases with phone numbers greater than 10 digits
	@DataProvider(name = "moreThan10DigitNumbers")
	public static Object[][] moreThan10DigitNumber() {
		// Return test data with phone numbers that are longer than 10 digits
		Object[][] data = { { "962164185612" }, { "962164185623" }, { "962164185634" } };
		return data;
	}

	// Test case to verify the validation of phone numbers longer than 10 digits
	@Test(priority = 2, dataProvider = "moreThan10DigitNumbers")
	public static void greaterLengthPhoneNumberInputFieldValidation(String number) {
		// Verify that the number entered is truncated to 10 digits
		String fieldValue = LoginPage.verifyInputField(number);

		// Check that the field value length is 10 and the entered number is different
		// (truncated)
		boolean idealCondition = fieldValue.length() == 10 && !number.equals(fieldValue);

		// Assert that the field is properly truncated and the continue button is
		// enabled
		Assert.assertTrue(idealCondition);
		Assert.assertTrue(LoginPage.buttonEnabled());
	}

	// DataProvider for test cases with phone numbers less than 10 digits
	@DataProvider(name = "lessThan10DigitNumbers")
	public static Object[][] lessThan10DigitNumber() {
		// Return test data with phone numbers that are shorter than 10 digits
		Object[][] data = { { "9" }, { "962164185" }, { "96216" } };
		return data;
	}

	// Test case to verify the validation of phone numbers shorter than 10 digits
	@Test(priority = 3, dataProvider = "lessThan10DigitNumbers")
	public static void lesserLengthPhoneNumberInputFieldValidation(String number) {
		// Verify that the entered phone number is shown correctly in the input field
		String fieldValue = LoginPage.verifyInputField(number);

		// Assert that the field value matches the input and the continue button is
		// disabled
		Assert.assertEquals(fieldValue, number);
		Assert.assertFalse(LoginPage.buttonEnabled());
	}

	// DataProvider for test case with valid phone number and OTP heading text
	@DataProvider(name = "validPhoneNumber")
	public static Object[][] getValidPhoneNumber() {
		// Return test data with a valid phone number and expected OTP heading text
		Object[][] data = { { "3426963790", "OTP Verification" } };
		return data;
	}

	// Test case to verify a valid phone number and the correct OTP heading text
	@Test(priority = 4, dataProvider = "validPhoneNumber")
	public void validPhoneNumberValidation(String number, String otpText) {
		// Verify that the login process works and the OTP heading matches the expected
		// text
		boolean otpPage = LoginPage.validLoginVerification(number, otpText);

		// Assert that the user is directed to the OTP page with the correct heading
		Assert.assertTrue(otpPage);
	}

	// Close the browser after the suite completes
	@AfterSuite
	public static void tearDown() {
		// Close the browser and clean up resources
		Browser.closeBrowser();
	}
}
