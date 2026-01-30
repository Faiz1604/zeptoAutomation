package locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class AddressLocators {
	
	//button for address
	@FindBy(xpath="//button[@aria-haspopup=\"dialog\"]")
	
	public WebElement addressButton;
	
	
	//input tag to enter address
	
	@FindBy(xpath = "//input[@placeholder=\"Search a new address\"]")
	
	public WebElement addressInput;
	
	//webelement for address suggessted
	
	@FindAll(@FindBy(xpath="//*[@id=\"zui-modal-undefined\"]/div/div/div/div/div/div/div[2]/div/div[2]/div[2]/div/div/div/div/div/span"))
	
	public List<WebElement> suggesstions;
	
	@FindBy(xpath="//button[@aria-haspopup=\"dialog\"]/h3/span[3]")
	public WebElement address;

}
