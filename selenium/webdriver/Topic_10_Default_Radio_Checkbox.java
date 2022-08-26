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

public class Topic_10_Default_Radio_Checkbox {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jotform() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// check to Cancer
		checkAndVerify("//input[@value='Cancer']");

		// check to Sleep Apnea
		checkAndVerify("//input[@value='Sleep Apnea']");

		// check to radio button '5+ days'
		checkAndVerify("//input[@value='5+ days']");

	}

	@Test
	public void TC_02_Jotform_SelectAll() {
		selectAll("//input[@type='checkbox']");

		// uncheck all
		uncheckAllAndVerifyElement("//input[@type='checkbox']");

	}

	public void checkAndVerify(String xpathLocator) {
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
			Assert.assertTrue(driver.findElement(By.xpath(xpathLocator)).isSelected());
		}

	}

	public void checkAndVerifyElement(WebElement element) {
		if (!element.isSelected()) {
			element.click();
			Assert.assertTrue(element.isSelected());
		}

	}

	public void uncheckAllAndVerifyElement(String xpathLocator) {
		List<WebElement> allCheckbox = driver.findElements(By.xpath(xpathLocator));
		for (WebElement checkbox : allCheckbox) {
			if (checkbox.isSelected()) {
				checkbox.click();
				Assert.assertFalse(checkbox.isSelected());
			}
		}

	}

	public void selectAll(String xpathLocator) {
		List<WebElement> allCheckbox = driver.findElements(By.xpath(xpathLocator));
		for (WebElement checkbox : allCheckbox) {
			checkAndVerifyElement(checkbox);
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
		// driver.quit();
	}

}
