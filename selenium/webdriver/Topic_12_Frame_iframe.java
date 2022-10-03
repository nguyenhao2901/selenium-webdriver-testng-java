package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Frame_iframe {

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
	public void TC_01_Iframe_Kyna() {
		driver.get("https://kyna.vn/");
		// verify facebook iframe is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		String likeNumber = driver.findElement(By.cssSelector("div._1drq")).getText();
		System.out.println(likeNumber);
		Assert.assertEquals(likeNumber, "166K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("ten nguoi hoc");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("dang ky khoa hoc moi");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		sleepInSecond(5);
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
		List<WebElement> courseTitle = driver.findElements(By.cssSelector("div.content h4"));
		System.out.println(courseTitle.size());
		for (WebElement title : courseTitle) {
			System.out.println(title.getText());
			Assert.assertTrue(title.getText().contains("Excel"));
		}

	}

	@Test
	public void TC_02_Frame_HDFCBank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("haonguyen");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());

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
