package locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ProductSearchLocators {
	
	@FindBy(xpath = "//a[@aria-label='Search for products' and @data-testId='search-bar-icon']")
	
	public WebElement searchButton;

	@FindBy(xpath = "//input[contains(@id,'input') and contains(@placeholder,'Search')]")

	public WebElement searchInput;

	@FindAll(@FindBy(xpath = "//ul[contains(@id,'suggestions') and @role='listbox']/li"))

	public List<WebElement> suggestions;

	@FindAll(@FindBy(xpath = "//div[@class='EG_gO']/div/a"))

	public List<WebElement> results;

	@FindBy(xpath = "//*[@id=\"left-carousel\"]/div[2]/div/div[2]/button")
	public WebElement addToCartButton;

	@FindBy(xpath = "//button[@aria-label='Cart']/span/span/span[2][@data-testid='cart-items-number']")

	public WebElement itemNumbersText;

}
