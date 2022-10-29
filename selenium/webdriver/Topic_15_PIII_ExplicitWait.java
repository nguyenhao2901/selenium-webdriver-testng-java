package webdriver;

import java.awt.Desktop.Action;
import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_PIII_ExplicitWait {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		action = new Actions(driver);
//		explicitWait = new WebDriverWait(driver, 30);
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		By loadingIcon = By.cssSelector("div#loading");
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		WebElement text = driver.findElement(By.cssSelector("div#finish>h4"));
		Assert.assertEquals(text.getText(), "Hello World!");

	}

	@Test
	public void TC_02_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait = new WebDriverWait(driver, 5);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

	}

	
	@Test
	public void TC_03_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		//chờ cho đến khi date picker visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		//verify chưa có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
				"No Selected Dates to display.");

		// wait cho ngày 20 clickable và chọn ngày 20
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='20']"))).click();

		// Wait cho ajax loading biến mất
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

		action.moveToElement(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"))).perform();
		//Wait cho ngày 20 được clickable trở lại
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='20']")));

		//Verify ngày được chọn là 20/10/2022
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),
				"Thursday, October 20, 2022");
		

	}

	@Test
	public void TC_04_ExplicitWait_UploadFile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add files']")));
		String pic1 = projectPath + File.separator + "uploadFiles" + File.separator + "picture1.jpg";
		String pic2 = projectPath + File.separator + "uploadFiles" + File.separator + "picture2.jpg";
		String pic3 = projectPath + File.separator + "uploadFiles" + File.separator + "picture3.jpg";
		System.out.println(pic1 + "\n" + pic2 + "\n" + pic3);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pic1 + "\n" + pic2 + "\n" + pic3);

		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
				driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success>h5")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.callout-success>h5")).getText(),
				"Your files have been successfully uploaded");
		// Cach 1
		String linkDownload = driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).getText();
		driver.get(linkDownload);
//		cach 2
//		driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).click();
//		switchToWindowns();

		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.contentName")));

		// Verify download button is displayed
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture1.jpg']/ancestor::div[contains(@class,'contentId')]//button[@id='contentId-download']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture2.jpg']/ancestor::div[contains(@class,'contentId')]//button[@id='contentId-download']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture3.jpg']/ancestor::div[contains(@class,'contentId')]//button[@id='contentId-download']"))
				.isDisplayed());

		// Verify play button is displayed
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture1.jpg']/ancestor::div[contains(@class,'contentId')]//button[contains(@class,'contentPlay')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture2.jpg']/ancestor::div[contains(@class,'contentId')]//button[contains(@class,'contentPlay')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[text()='picture3.jpg']/ancestor::div[contains(@class,'contentId')]//button[contains(@class,'contentPlay')]"))
				.isDisplayed());

	}

	public void switchToWindowns() {
		String currentUrl = driver.getWindowHandle();
		Set<String> ipWindowns = driver.getWindowHandles();
		for (String ip : ipWindowns) {
			if (ip != currentUrl) {
				driver.switchTo().window(ip);
			}
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
