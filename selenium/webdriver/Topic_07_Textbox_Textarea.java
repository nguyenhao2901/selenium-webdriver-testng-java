package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, firstNameEdit, lastNameEdit, employeeId;
	String number, comments;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Nguyen";
		lastName = "Hao";
		firstNameEdit = "Luong";
		lastNameEdit = "Bu";
		number = "987654321";
		comments = "186 Ngô Gia Tự\nMở cả ngày\nMua sắm tại cửa hàng\nNhận tại cửa hàng\nGiao hàng";

	}

	@Test
	public void TC_01_Textbox_Textarea() {
		// login
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();

		// open AddEmployee page and type data and click save
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
		employeeId = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input#btnSave")).click();

		// verify data at the personal details screen with data typed at Add employee
		// screen
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"),
				firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"),
				lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"),
				employeeId);

		// verify firstName,lastName,employeeId textbox is disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());

		// click Edit button and type new data
		driver.findElement(By.cssSelector("input#btnSave")).click();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(firstNameEdit);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(lastNameEdit);
		sleepInSecond(2);

		// verify firstName, lastName textbox is enabled and save
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(2);

		// verify new data is updated
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"),
				firstNameEdit);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"),
				lastNameEdit);

		// verify firstName,lastName,employeeId textbox is disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());

		// click immigration tab
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		// click add immigration record, type in required field and save
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(number);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(comments);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input#btnSave")).click();

		// click passport and verify data
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"),
				number);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"),
				comments);

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
