package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_12_PIII_Windows_Tab {

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
	public void TC_01_AutomationFC() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click and switch to google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		System.out.println(driver.getWindowHandles());
		switchToWindowsByTitle("Google");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Google");

		// switch to parent windows
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
		System.out.println(driver.getTitle());
		sleepInSecond(2);
		// click and switch to facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		switchToWindowsByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		// switch to parent windows
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		// click and switch to tiki
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		// close all tab without parent windows
		closeWithoutParentWindows("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		List<WebElement> popup = driver.findElements(By.cssSelector("div.fancybox-skin"));
		if (popup.size() > 0) {
			driver.findElement(By.cssSelector("a[title='Close']")).click();
		}

		// click and switch to facebook
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//div[@class='hotline']//div[@class='social']//a[1]")));
		sleepInSecond(5);
		switchToWindowsByTitle("Kyna.vn - Home | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		System.out.println(driver.getTitle());

		// back to parent and click and switch to youtube
		switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//div[@class='hotline']//div[@class='social']//a[2]")));
		sleepInSecond(5);
		switchToWindowsByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		System.out.println(driver.getTitle());

		// close all tab without parent tab
		closeWithoutParentWindows("Kyna.vn - Học online cùng chuyên gia");
		switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

	}

	@Test
	public void TC_03_Techpanda() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath(
				"//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(3);

		// switch to new windows
		switchToWindowsByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title h1")).getText(), "COMPARE PRODUCTS");

		driver.findElement(By.cssSelector("button[title='Close Window']")).click();

		// switch to parent windows
		switchToWindowsByTitle("Mobile");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(3);
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages span")).getText(),
				"The comparison list was cleared.");
		sleepInSecond(2);

	}

	@Test
	public void TC_04_Dictionary() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//header//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		switchToWindowsByTitle("Login");
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		Assert.assertEquals(
				driver.findElement(By.cssSelector("div#login_content span[data-bound-to='loginID']")).getText(),
				"This field is required");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("div#login_content span[data-bound-to='password']")).getText(),
				"This field is required");
		driver.findElement(By.cssSelector("div#login_content input[name='username']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.cssSelector("div#login_content input[name='password']")).sendKeys("Automation000***");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		switchToWindowsByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");
		

	}

	public void switchToWindowsByTitle(String title) {
		Set<String> ipWindows = driver.getWindowHandles();
		for (String ip : ipWindows) {
			driver.switchTo().window(ip);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}

	}

	public void closeWithoutParentWindows(String title) {
		Set<String> ipWindows = driver.getWindowHandles();
		for (String ip : ipWindows) {
			driver.switchTo().window(ip);
			if (!driver.getTitle().equals(title)) {
				driver.close();
				sleepInSecond(1);
			}
		}
	}

//	public void switchToWindowsByTitle(String title) {
//		Set<String> ipWindows = driver.getWindowHandles();
//		for (String ip : ipWindows) {
//			driver.switchTo().window(ip);
//			if (driver.getTitle().equals(title)) {
//				break;
//			}
//		}
//
//	}
//
//	public void closeWithoutParentWindows(String title) {
//		Set<String> ipWindows = driver.getWindowHandles();
//		for (String ip : ipWindows) {
//			driver.switchTo().window(ip);
//			if (!driver.getTitle().equals(title)) {
//				driver.close();
//				sleepInSecond(1);
//			}
//		}
//
//	}

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
