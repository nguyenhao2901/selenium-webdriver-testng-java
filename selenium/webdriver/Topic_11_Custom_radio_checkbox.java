package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_radio_checkbox {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);

		checkAndVerify("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		uncheckAndVerify("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		checkAndVerify("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

	}

	@Test
	public void TC_02_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		checkAndVerifyByJS("//span[contains(text(),'Summer')]/preceding-sibling::span/input");

		driver.get("https://material.angular.io/components/checkbox/examples");
		checkAndVerifyByJS("//span[text()='Checked']/preceding-sibling::span/input");
		checkAndVerifyByJS("//span[text()='Indeterminate']/preceding-sibling::span/input");
		sleepInSecond(1);

		uncheckAndVerifyByJS("//span[text()='Checked']/preceding-sibling::span/input");
		uncheckAndVerifyByJS("//span[text()='Indeterminate']/preceding-sibling::span/input");
		sleepInSecond(1);

	}

	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		WebElement element = driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']"));
		Assert.assertTrue(element.isDisplayed());
		jsExecutor.executeScript("arguments[0].click();", element);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());

	}

	public void checkAndVerify(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (!element.isSelected()) {
			element.click();
			Assert.assertTrue(element.isSelected());
		}

	}

	public void uncheckAndVerify(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isSelected()) {
			element.click();
			Assert.assertFalse(element.isSelected());
		}

	}

	public void checkAndVerifyByJS(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (!element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", element);
			Assert.assertTrue(element.isSelected());
		}

	}

	public void uncheckAndVerifyByJS(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", element);
			Assert.assertFalse(element.isSelected());
		}

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
