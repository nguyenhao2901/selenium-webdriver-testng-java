package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {

	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
		
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		/*driver.findElement(By.cssSelector("span#number-button")).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
		List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
		for (WebElement item : allItems) {
			String text = item.getText();
			if (text.equals("10")) {
				item.click();
			}
		}
		

	}*/
		
		//select speed
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		//select file
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
		//select number
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "12");
		//select title
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Prof.");
	}

	

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String valueSelected) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String text = item.getText();
			if (text.equals(valueSelected)) {
				item.click();
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
