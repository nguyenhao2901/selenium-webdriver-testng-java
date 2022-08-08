package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown_List {

	WebDriver driver;
	Select select;
	Random rand;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Dropdown_01() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Anh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Bu");

//		select day
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("15");
		
//		select month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("August");
		
//		select year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("2022");
		
		emailAddress = "abubu" + rand.nextInt(999) + "@gmail.net";
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("#register-button")).click();
		
//		verify register success
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		
//		verify data at my account
		driver.findElement(By.cssSelector("a.ico-account")).click();
//		day
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "15");
		
//		month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "August");
		
//		year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "2022");
		
		
//		email
		Assert.assertEquals(driver.findElement(By.cssSelector("#Email")).getAttribute("value"), emailAddress);
		
		
		

	}

	@Test
	public void TC_02_Dropdown_02() {
		driver.get("https://www.rode.com/wheretobuy");
		select = new Select(driver.findElement(By.cssSelector("select#country")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Vietnam");
		
		sleepInSecond(5);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		List<WebElement> dealers = driver.findElements(By.cssSelector("div#map h4"));
		
		for (WebElement element : dealers) {
			System.out.println(element.getText());
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
