package searchTest;

import org.testng.annotations.Test;

import browser.Browser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import pages.ProductSearchPage;

public class SearchTest {
	
    @BeforeClass
    public static void gotToHomePage() {
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");
        
        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
    }
	
	
	@DataProvider(name="searchData")
	
	public static Object[][] dataToSearch(){
		Object[][] data = {{"boo","Book"}};
		return data;
	}
	
	@Test(dataProvider="searchData")
	
	public static void testProductSearch(String keyword,String product) {
		boolean assumption = ProductSearchPage.checkCartAndCompare(keyword, product);
		
		Assert.assertTrue(assumption);
	}

}
