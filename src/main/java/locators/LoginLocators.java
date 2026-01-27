package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import browser.Browser;

public class LoginLocators extends Browser {

	@FindBy(xpath = "//button[@aria-label='login']")
	public WebElement loginButton;

	@FindBy(xpath = "//input[@placeholder='Enter Phone Number']")

	public WebElement inputField;

	@FindBy(xpath = "//*[@id=\"radix-_r_0_\"]/div/div/div/div[1]/div/div[1]/form/button")

	public WebElement continueButton;
	
	@FindBy(xpath="//*[@id=\"radix-_r_0_\"]/div/div/div/div[1]/div/div/h2")
	public WebElement otpHeading;
	
	@FindBy(xpath="//*[@id=\"radix-_r_6_\"]/div/div/div/div[1]/div/div/button")
	public WebElement backToLoginButton;
}
