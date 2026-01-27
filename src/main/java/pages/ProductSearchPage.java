package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import browser.Browser;
import locators.ProductSearchLocators;

/**
 * Page class for handling product search related actions
 */
public class ProductSearchPage extends Browser {

	// Object repository for product search page
	static ProductSearchLocators productSearchLocators;

	// Initialize page elements using PageFactory
	static {
		productSearchLocators = PageFactory.initElements(driver, ProductSearchLocators.class);
	}

	/**
	 * Enters keyword into the search input box
	 * 
	 * @param keyword product name or keyword to search
	 */
	public static void sendKeyInInput(String keyword) {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(productSearchLocators.searchButton)).click();
			;
			WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(productSearchLocators.searchInput));

			searchInput.clear();
			searchInput.sendKeys(keyword);
		}

		catch (Exception e) {
			System.out.println("Error while entering keyword in search input: " + e);
		}
	}

	/**
	 * Selects a product either from suggestions or search results
	 * 
	 * @param product product name to click
	 * @param in      "suggestions" or "results"
	 */
	public static void searchProduct(String product, String in) {

		try {
			List<WebElement> list;

			// Decide whether to search in suggestions or results
			if (in.equalsIgnoreCase("suggestions")) {
				list = wait.until(ExpectedConditions.visibilityOfAllElements(productSearchLocators.suggestions));
			} else {
				list = wait.until(ExpectedConditions.visibilityOfAllElements(productSearchLocators.results));
			}

			// Iterate through the list and click matching product
			
			if(in.equalsIgnoreCase("results")) {
				list.get(0).click();
				return;
			}
			System.err.println(list.size());
			for (WebElement element : list) {
				
				if (element.getText().contains(product)) {
					System.out.println(element.getText());
					actions.moveToElement(element).click().perform();
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Error while selecting product: " + e.getMessage());
		}
	}

	/**
	 * Clicks on Add To Cart button
	 */
	public static void clickOnAddToCart() {
		try {
			WebElement addToCartButton = wait
					.until(ExpectedConditions.elementToBeClickable(productSearchLocators.addToCartButton));
			actions.moveToElement(addToCartButton).click().perform();
		} catch (Exception e) {
			System.out.println("Error while clicking Add to Cart: " + e.getMessage());
		}
	}

	/**
	 * Searches for a product and adds it to the cart
	 * 
	 * @param keyword search keyword
	 * @param product product to be added
	 * @return true if flow is successful, false otherwise
	 */
	public static boolean checkCartAndCompare(String keyword, String product) {

		try {
			sendKeyInInput(keyword);
			searchProduct(product, "suggestions");
			searchProduct(product, "results");
			int previousCount = 0;
			clickOnAddToCart();

			System.out.println("clicked on add to cart button");
			WebElement numbersOfItemInCartAfter = wait
					.until(ExpectedConditions.visibilityOf(productSearchLocators.itemNumbersText));

			int afterCount = Integer.parseInt(numbersOfItemInCartAfter.getText().replaceAll("\\D", ""));

			return afterCount > previousCount;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
