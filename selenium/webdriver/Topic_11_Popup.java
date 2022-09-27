package webdriver;

import java.util.List;
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

public class Topic_11_Popup {

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
		sleepInSecond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");
		sleepInSecond(2);
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
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login")).isDisplayed());

	}

	@Test
	public void TC_03_Fixed_Popup_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
		// verify popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed());

		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(),
				"Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(),
				"Mật khẩu không được để trống");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("img.close-img")).click();
//		verify popup is not displayed
		List<WebElement> loginPopup = driver.findElements(By.xpath("//div[@role='dialog']"));
		Assert.assertEquals(loginPopup.size(), 0);

	}

	@Test
	public void TC_04_Fixed_Popup_Not_In_DOM_Facebook() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		// verify register popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']")).isDisplayed());
		driver.findElement(By.cssSelector("img._8idr.img")).click();
		// verify register popup is not displayed
		List<WebElement> registerPopup = driver.findElements(By.xpath("//div[text()='Sign Up']"));
		Assert.assertEquals(registerPopup.size(), 0);
	}

	@Test
	public void TC_05_random_Popup_In_DOM_Javacodegeek() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(10);
		WebElement popup = driver.findElement(By.xpath("//span[text()='Get the Books']"));
		if (popup.isDisplayed()) {
			driver.findElement(By.xpath("//div[@class='lepopup-form-inner']//input[@type='email']"))
					.sendKeys("anhbu@gmail.com");
			sleepInSecond(3);
			driver.findElement(By.xpath("//span[text()='Get the Books']")).click();
		}
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#s")).sendKeys("selenium");
		// sleepInSecond(3);
		// driver.findElement(By.cssSelector("button.search-button")).click();
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button.search-button")));
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//article[@class='item-list item_1']//h2")).getText()
				.contains("Selenium"));

	}

	@Test
	public void TC_06_Random_Popup_In_DOM_vnkedu() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(4);
		WebElement popup = driver.findElement(By.cssSelector("div#tve_editor"));
		if (popup.isDisplayed()) {
			driver.findElement(By.cssSelector("svg[data-name='closeclose']")).click();
		}
		// chuyển sang bước 3: click vào 'Khóa học ONLINE ' => chọn 'Thiết kế hệ thống
		// Điện'
		driver.findElement(By.xpath("//a[text()='Khóa học ONLINE ']")).click();
		driver.findElement(
				By.xpath("//a[text()='Khóa học ONLINE ']/following-sibling::ul//a[text()='Thiết kế hệ thống Điện']"))
				.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/courses?category=5645");

	}

	@Test
	public void TC_07_Random_Popup_Not_In_DOM_Dehieu() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(3);
		List<WebElement> popup = driver.findElements(By.cssSelector("div.popup-content"));
		if (popup.size() > 0) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
		}
		driver.findElement(By.xpath("//section[@id='header']//a[text()='Tất cả khóa học']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
	}

	@Test
	public void TC_08_KMplayer() {
		driver.get("http://www.kmplayer.com/");
		sleepInSecond(2);
		WebElement popup = driver.findElement(By.cssSelector("div.pop-container"));
		if (popup.isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
		}
		driver.findElement(By.cssSelector("p.donate-coffee")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
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
