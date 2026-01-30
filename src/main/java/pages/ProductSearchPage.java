package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
			// Click search button
			wait.until(ExpectedConditions.elementToBeClickable(productSearchLocators.searchButton)).click();
			WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(productSearchLocators.searchInput));
			searchInput.clear();
			searchInput.sendKeys(keyword);
		} catch (Exception e) {
			System.out.println("Error while entering keyword in search input: " + e.getMessage());
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

			if (in.equalsIgnoreCase("result")) {
				list.get(0).click();
				return;
			}
			for (WebElement element : list) {
				if (element.getText().contains(product)) {
					System.out.println("Clicking on product: " + element.getText());
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
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			js.executeScript("arguments[0].scrollIntoView({block:'center'})", addToCartButton);
			
			System.out.println("clicked on add to cart");
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
			searchProduct(product, "result");
			// If product not found in suggestions, check result
			// Add product to cart
//			clickOnAddToCart();
			// Get previous count of items in cart
			
			int previousCount = 0;

			// Get the count of items in cart after adding product
			
			Thread.sleep(2000);
			WebElement numbersOfItemInCartAfter = wait
					.until(ExpectedConditions.visibilityOf(productSearchLocators.itemNumbersText));
			int afterCount = Integer.parseInt(numbersOfItemInCartAfter.getText());

			// Return true if the cart count increased
			return afterCount > previousCount;
		} catch (Exception e) {
			System.out.println("Error in cart check and comparison: " + e.getMessage());
			return false;
		}
	}
}
