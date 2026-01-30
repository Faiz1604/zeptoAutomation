package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import browser.Browser;
import locators.AddressLocators;

public class AddressPage extends Browser {
	static AddressLocators addressLocators;

	static {
		addressLocators = PageFactory.initElements(driver, AddressLocators.class);
	}

	public static void clickOnAdressButton() {

		WebElement addressButton = wait.until(ExpectedConditions.elementToBeClickable(addressLocators.addressButton));

		addressButton.click();
	}

	public static String enterAddressInInput(String addressKeyword) {
		String value = null;
		try {
			WebElement addressInput = wait.until(ExpectedConditions.visibilityOf(addressLocators.addressInput));
			addressInput.clear();
			addressInput.sendKeys(addressKeyword);
			value = addressInput.getAttribute("value");

		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return value;

	}

	public static boolean clickOnSuggestion(String addressKeyword, String fullAddress) {

		boolean idealCondition = true;
		String[] addressKeywords = fullAddress.split(" ");

		try {
			List<WebElement> suggesstions = wait
					.until(ExpectedConditions.visibilityOfAllElements(addressLocators.suggesstions));
			
			System.out.println(suggesstions);
			for (WebElement element : suggesstions) {

				if (element.getText().toLowerCase().contains(addressKeyword)) {

					element.click();
					break;

				}

			}

			WebElement addressSpan = wait.until(ExpectedConditions.visibilityOf(addressLocators.address));
			
			for(String keyword : addressKeywords) {
				if(!addressSpan.getText().toLowerCase().contains(keyword)) {
					idealCondition=false;
					break;
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return idealCondition;
	}

}
