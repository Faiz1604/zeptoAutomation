import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import browser.Browser;
import pages.AddressPage;

public class AddressTest {

	@DataProvider(name="addresses")

	public static Object[][] getAddresses() {
		Object[][] data = { { "tcs gitanjali park", "kolkata west bengal" } };
		return data;
	}
	
	
    @BeforeClass
    public static void gotToHomePage() {
        boolean isTitleMatched = Browser.navigate("Welcome to Zepto, India's Fastest Online Grocery Delivery App!"
                + " Get all your groceries delivered to you in minutes. | https://www.zepto.com");
        
        // Assert that the title matches the expected value (this checks if we are on the correct page)
        Assert.assertTrue(isTitleMatched);
    }
    
    @Test(dataProvider = "addresses")
    
    public static void testAddress(String keyWord,String fullAddress) {
    	
    	AddressPage.clickOnAdressButton();
    	AddressPage.enterAddressInInput(keyWord);
    	Assert.assertTrue(AddressPage.clickOnSuggestion(keyWord,fullAddress));
    }
	

}
