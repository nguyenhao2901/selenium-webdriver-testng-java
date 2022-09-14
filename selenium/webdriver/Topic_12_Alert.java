package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

public class Topic_12_Alert {

	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String urlArray[];

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(2);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");

	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(2);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(2);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("nguyenthihao");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: nguyenthihao");

	}

	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example")).getText()
				.contains("Congratulations! You must have the proper credentials."));

	}
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("https://the-internet.herokuapp.com/");
		String basicUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		urlArray = basicUrl.split("//");
		String userName = "admin";
		String passWord = "admin";
		
		basicUrl = urlArray[0] + "//" + userName + ":" + passWord + "@" + urlArray[1];
		driver.get(basicUrl);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example")).getText()
				.contains("Congratulations! You must have the proper credentials."));		
		
		
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
