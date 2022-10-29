package webdriver;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.AccountExpiredException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_PI_Element_Condition_Status {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visible() {
		// có trên UI
		// có trong DOM
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

	}

	@Test
	public void TC_02_Invisible() {
		// Không có trên UI
		// có trong DOM
		driver.get("https://www.facebook.com/");
		// Nhấn create new account
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));

		// close popup
		driver.findElement(By.cssSelector("img._8idr")).click();
		// không có trên UI
		// không có trong DOM
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));

	}

	@Test
	public void TC_03_Presence() {
		// có trên UI
		// có trong DOM
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));

		// Không có trên UI
		// có trong DOM
		// Nhấn create new account
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		explicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("input[aria-label='Re-enter email address']")));

	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		//open register form
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		WebElement reEnterEmailTextbox = driver.findElement(By.cssSelector("input[aria-label='Re-enter email address']"));
		//close popup
		driver.findElement(By.cssSelector("img._8idr")).click();
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailTextbox));
		
		
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
