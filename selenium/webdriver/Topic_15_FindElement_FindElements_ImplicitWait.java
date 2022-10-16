package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_FindElement_FindElements_ImplicitWait {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Tìm thấy duy nhất 1 node
		driver.findElement(By.cssSelector("input#email"));

		// Tìm thấy nhiều hơn 1 node
		driver.findElement(By.cssSelector("input[type='email']"));

		// Không tìm thấy
		// driver.findElement(By.cssSelector("input[type='emailAAA']"));

	}

	@Test
	public void TC_02_FindElements() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		// Tìm thấy duy nhất 1 node
		List<WebElement> element = driver.findElements(By.cssSelector("input#email"));
		System.out.println("Size of list is " + element.size());

		// Tìm thấy nhiều hơn 1 node
		element = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("Size of list is " + element.size());

		// Không tìm thấy
//		element = driver.findElements(By.cssSelector("input[type='emailAAA']"));
//		System.out.println("Size of list is" + element.size());

	}

	@Test
	public void TC_03_Implicit() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
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
