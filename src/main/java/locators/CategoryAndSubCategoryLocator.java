package locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryAndSubCategoryLocator {
	@FindBy(xpath="//div[@class='embla__container no-scrollbar']/button/span")
	
	public List<WebElement> categoryText;

}
