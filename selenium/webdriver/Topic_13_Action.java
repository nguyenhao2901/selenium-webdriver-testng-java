package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action {

	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_Hover_To_Element() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@href='/shop/kids']"))).perform();
		sleepInSecond(3);
		// action.click(driver.findElement(By.xpath("//a[text()='Home &
		// Bath']"))).perform();
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	}

	@Test
	public void TC_03_Hover_To_Element_Fahasa() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"))).perform();
		List<WebElement> subMenu = driver.findElements(
				By.xpath("//div[@class='fhs_menu_content fhs_column_left']//div[@class='row'][1]//ul[1]//li[1]"));

		Assert.assertEquals(subMenu.get(0).getText(), "Túi - Ví Thời Trang");
		Assert.assertEquals(subMenu.get(1).getText(), "Đèn Bàn");
		Assert.assertEquals(subMenu.get(2).getText(), "Ly, Cốc, Bình Nước");
		action.click(
				driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Đèn Bàn']")))
				.perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong")).getText(), "ĐÈN BÀN");

	}

	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		action.clickAndHold(driver.findElement(By.xpath("//li[text()='1']")))
				.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).release().perform();
		sleepInSecond(3);

		List<WebElement> numberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
		System.out.println("number is selected:" + numberSelected.size());
		Assert.assertEquals(numberSelected.size(), 4);

	}

	@Test
	public void TC_05_Click_And_Select_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> number = driver.findElements(By.xpath("//ol//li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(number.get(0)).click(number.get(2)).click(number.get(5)).click(number.get(10)).release().perform();
		List<WebElement> numberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
		Assert.assertEquals(numberSelected.size(), 4);

	}

	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

	}

	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());
		driver.findElement(By.xpath("//span[text()='Quit']")).click();
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

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
