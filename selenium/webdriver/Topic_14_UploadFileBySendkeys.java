package webdriver;

import java.io.File;
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

public class Topic_14_UploadFileBySendkeys {

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
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		String file1 = projectPath + File.separator + "uploadFiles" + File.separator + "picture1.jpg";
		String file2 = projectPath + File.separator + "uploadFiles" + File.separator + "picture2.jpg";
		String file3 = projectPath + File.separator + "uploadFiles" + File.separator + "picture3.jpg";

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(file1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(file2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(file3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture3.jpg']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.btn-primary.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture3.jpg']")).isDisplayed());

	}

	@Test
	public void TC_02_Upload_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		String file1 = projectPath + File.separator + "uploadFiles" + File.separator + "picture1.jpg";
		String file2 = projectPath + File.separator + "uploadFiles" + File.separator + "picture2.jpg";
		String file3 = projectPath + File.separator + "uploadFiles" + File.separator + "picture3.jpg";

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(file1 + "\n" + file2 + "\n" + file3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='picture3.jpg']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.btn-primary.start"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(3);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture2.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='picture3.jpg']")).isDisplayed());
		
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
