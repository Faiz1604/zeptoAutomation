package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import browser.Browser;
import locators.LoginLocators;

public class LoginPage extends Browser {
    
    // Static reference to LoginLocators class for identifying elements
    static LoginLocators loginLocator;

    // Initialize LoginLocators using PageFactory
    static {
        loginLocator = PageFactory.initElements(driver, LoginLocators.class);
    }

    // Method to click the Login Button
    public static void clickOnLoginButton() {
        try {
            // Wait until the login button is clickable
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginLocator.loginButton));

            // Click on the login button
            loginButton.click();
        } catch (Exception e) {
            // Print the exception if an error occurs
            System.out.println(e);
        }
    }

    // Method to verify if the Continue button is enabled (default state)
    public static boolean buttonEnabled() {
        boolean isEnabled = false;
        try {
            // Wait until the continue button is visible
            WebElement continueButton = wait.until(ExpectedConditions.visibilityOf(loginLocator.continueButton));

            // Check if the continue button is enabled
            isEnabled = continueButton.isEnabled();
        } catch (Exception e) {
            // Print the exception if an error occurs
            System.out.println(e);
        }
        return isEnabled;
    }

    // Method to enter a phone number in the input field and verify the entered value
    public static String verifyInputField(String number) {
        String fieldValue = null;
        try {
            // Wait until the input field is visible
            WebElement inputField = wait.until(ExpectedConditions.visibilityOf(loginLocator.inputField));

            // Clear the field, enter the phone number, and fetch the entered value
            inputField.clear();
            inputField.sendKeys(number);

            // Get the value of the input field to verify the entered value
            fieldValue = inputField.getAttribute("value");
        } catch (Exception e) {
            // Print the exception if an error occurs
            System.out.println(e);
        }
        return fieldValue;
    }

    // Method to perform a valid login verification using a phone number and OTP heading
    public static boolean validLoginVerification(String number, String otpHead) {
        boolean idealCondition = false;
        try {
            // First, verify the phone number input field value
            String fieldValue = verifyInputField(number);

            // Click on the continue button
            wait.until(ExpectedConditions.elementToBeClickable(loginLocator.continueButton)).click();

            // Wait until the OTP heading is visible
            WebElement otpHeading = wait.until(ExpectedConditions.visibilityOf(loginLocator.otpHeading));
            System.out.println(otpHeading.getText());
            // Check if the entered phone number matches and the OTP heading is correct
            idealCondition = (fieldValue.equals(number)) && (otpHeading.getText().equalsIgnoreCase(otpHead));

            // Click on 'Back to Login' button to reset state
            
        } catch (Exception e) {
            // Print the exception if an error occurs
            System.out.println(e);
        }

        return idealCondition;
    }
}
