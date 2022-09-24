package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Fixed_Popup {

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
	public void TC_01_Fixed_Popup_In_DOM_Ngoaingu24h() {
		driver.get("https://ngoaingu24h.vn/");
		WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		// verify popup is not displayed
		Assert.assertFalse(loginPopup.isDisplayed());
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		// verify popup is displayed
		Assert.assertTrue(loginPopup.isDisplayed());
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']"))
				.sendKeys("nguyenhaoautomationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']"))
				.sendKeys("12345678");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[text()='Đăng nhập']")).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();

	}

	@Test
	public void TC_02_Fixed_Popup_In_DOM_Kyna() {
		driver.get("https://kyna.vn/");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login")).isDisplayed());
		driver.findElement(By.id("user-login")).sendKeys("haoauto@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("12345678");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login")).isDisplayed());
		
		
		
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
