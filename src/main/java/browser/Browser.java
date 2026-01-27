package browser;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	public static String browserName;
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static Actions actions;

	public static void openBrowser() {

		try {
			browserName = Utility.getProperty("browser");

			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else {
				driver = new EdgeDriver();
			}

			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			actions = new Actions(driver);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static boolean navigate(String title) {
		boolean titleMatched = false;
		try {
			driver.manage().window().maximize();
			String url = Utility.getProperty("url");
			driver.get(url);

			titleMatched = wait.until(ExpectedConditions.titleIs(title));

		} catch (IOException e) {

			e.printStackTrace();
		}
		return titleMatched;
	}

	public static void closeBrowser() {
		driver.quit();
	}

}
