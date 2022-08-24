package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// khoi tao
		jsExecutor = (JavascriptExecutor) driver;
		//driver.manage().window().setSize(new Dimension(1366, 768));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		/*
		 * driver.findElement(By.cssSelector("span#number-button")).click();
		 * explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
		 * cssSelector("ul#number-menu div"))); List<WebElement> allItems =
		 * driver.findElements(By.cssSelector("ul#number-menu div")); for (WebElement
		 * item : allItems) { String text = item.getText(); if (text.equals("10")) {
		 * item.click(); } }
		 * 
		 * 
		 * }
		 */

		// select speed
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		// select file
		selectItemInCustomDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
		// select number
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "12");
		// select title
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Prof.");
	}

	@Test
	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		scrollToElement("div.div-cost-estimates");
		sleepInSecond(1);
		scrollToElement("div.container");
		sleepInSecond(2);

		selectItemInCustomDropdown("button#selectize-input", "div.dropdown-menu.show a", "CITY RS (Đỏ)");
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY RS (Đỏ)");

		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Bắc Giang");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Bắc Giang");

		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực III");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực III");

		sleepInSecond(2);

	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("div.dropdown", "div.dropdown span.text", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");

	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		sleepInSecond(3);
	}

	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		editable("input.search", "div.selected.item span", "Albania");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");
		sleepInSecond(3);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String valueSelected) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String text = item.getText();
			if (text.equals(valueSelected)) {
				item.click();
			}
		}
	}

	public void editable(String parentLocator, String childLocator, String valueSelected) {
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(valueSelected);
		sleepInSecond(3);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String text = item.getText();
			if (text.equals(valueSelected)) {
				item.click();
			}
		}
	}

	public void scrollToElement(String cssLocator) {

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
