package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import browser.Browser;
import locators.CategoryAndSubCategoryLocator;

public class CategoryAndSubCategory extends Browser {

    static CategoryAndSubCategoryLocator cnsc;
    static {
        cnsc = PageFactory.initElements(driver, CategoryAndSubCategoryLocator.class);
    }

    // Get all texts of the category and map them to a boolean value indicating their existence.
    public static Map<String, Boolean> getAllTextOfCategory() {
        // Wait for all elements to be visible
        List<WebElement> texts = wait.until(ExpectedConditions.visibilityOfAllElements(cnsc.categoryText));

        // Map to store category texts and their "existence" (true for existing categories)
        Map<String, Boolean> isText = new HashMap<>();

        // Loop through each web element and add it to the map
        for (WebElement element : texts) {
            isText.put(element.getText().toLowerCase(), true); // Mark the category as existing (true)
        }
        
        return isText;  // Return the map with category texts and their existence status
    }

    // Check if a specific category text exists in the list
    public static boolean checkIfExist(String text) {
        try {
            Map<String, Boolean> isText = getAllTextOfCategory();
            // Check if the category exists and return true, otherwise false
            return isText.getOrDefault(text, false);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
