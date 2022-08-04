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

public class Topic_06_Web_Browser_Element_PIII {

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
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// email Textbox
		WebElement emailTexbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTexbox.isDisplayed()) {
			emailTexbox.sendKeys("Automation testing");
			System.out.println("emailTextbox is displayed");

		} else {
			System.out.println("emailTextbox is not displayed");
		}

		// Age under 18 radio button
		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18.isDisplayed()) {
			ageUnder18.click();
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}

		// Education textarea

		WebElement Education = driver.findElement(By.cssSelector("#edu"));
		if (Education.isDisplayed()) {
			Education.sendKeys("Automation testing learning");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}

		// name:user5 is not displayed
		WebElement img = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (img.isDisplayed()) {
			System.out.println("name:user5 is displayed");
		} else {
			System.out.println("name:user5 is not displayed");
		}

	}

	@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// email
		WebElement emailTexbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTexbox.isEnabled()) {
			System.out.println("emailTexbox is enabled");

		} else {
			System.out.println("emailTexbox is disabled");
		}

		// Age under 18
		WebElement ageUnder18 = driver.findElement(By.cssSelector("[for=under_18]"));
		if (ageUnder18.isEnabled()) {
			System.out.println("ageUnder18 is enabled");

		} else {
			System.out.println("ageUnder18 is disabled");
		}

		// Education
		WebElement Education = driver.findElement(By.cssSelector("#edu"));
		if (Education.isEnabled()) {
			System.out.println("Education is enabled");

		} else {
			System.out.println("Education is disabled");
		}

		// Rob role1/Rob role2
		WebElement job1 = driver.findElement(By.cssSelector("[for=job1]"));
		WebElement job2 = driver.findElement(By.cssSelector("[for=job2]"));
		if (job1.isEnabled()) {
			System.out.println("job1 is enabled");

		} else {
			System.out.println("job1 is disabled");
		}

		if (job2.isEnabled()) {
			System.out.println("job2 is enabled");

		} else {
			System.out.println("job2 is disabled");
		}

		// interests (development)checkbox
		WebElement developmentCheckbox = driver.findElement(By.cssSelector("[for=development]"));
		if (developmentCheckbox.isEnabled()) {
			System.out.println("developmentCheckbox is enabled");

		} else {
			System.out.println("developmentCheckbox is disabled");
		}

		// slider 01
		WebElement slider01 = driver.findElement(By.cssSelector("#slider-1"));
		if (slider01.isEnabled()) {
			System.out.println("slider01 is enabled");

		} else {
			System.out.println("slider01 is disabled");
		}

		// password
		WebElement password = driver.findElement(By.cssSelector("#disable_password"));
		if (password.isEnabled()) {
			System.out.println("password is enabled");

		} else {
			System.out.println("password is disabled");
		}

		// radio button disabled
		WebElement radioButtonDisabled = driver.findElement(By.cssSelector("#radio-disabled"));
		if (radioButtonDisabled.isEnabled()) {
			System.out.println("radioButtonDisabled is enabled");

		} else {
			System.out.println("radioButtonDisabled is disabled");
		}

		// biography
		WebElement biography = driver.findElement(By.cssSelector("#bio"));
		if (biography.isEnabled()) {
			System.out.println("biography is enabled");

		} else {
			System.out.println("biography is disabled");
		}

		// iob role3
		WebElement jobRole3 = driver.findElement(By.cssSelector("#job3"));
		if (jobRole3.isEnabled()) {
			System.out.println("jobRole3 is enabled");

		} else {
			System.out.println("jobRole3 is disabled");
		}

		// interest checkbox is disabled
		WebElement interestCheckboxDisable = driver.findElement(By.cssSelector("#check-disbaled"));
		if (interestCheckboxDisable.isEnabled()) {
			System.out.println("interestCheckboxDisable is enabled");

		} else {
			System.out.println("interestCheckboxDisable is disabled");
		}

		// slider 2 disabled
		WebElement slider02 = driver.findElement(By.cssSelector("#slider-2"));
		if (slider02.isEnabled()) {
			System.out.println("slider02 is enabled");

		} else {
			System.out.println("slider02 is disabled");
		}

	}

	@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement ageUnder18 = driver.findElement(By.cssSelector("#under_18"));
		ageUnder18.click();
		if (ageUnder18.isSelected()) {
			System.out.println("ageUnder18 is selected");
		} else {
			System.out.println("ageUnder18 is de-selected");
		}
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		javaCheckbox.click();

		if (javaCheckbox.isSelected()) {
			System.out.println("javaCheckbox is selected");
		} else {
			System.out.println("javaCheckbox is de-Selected");
		}

		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("javaCheckbox is Selected()");
		} else {
			System.out.println("javaCheckbox is de-Selected");
		}

	}

	@Test
	public void TC_04_mailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.cssSelector("#email")).sendKeys("bubutest@gmail.com");
		sleepInSecond(2);
		WebElement password = driver.findElement(By.cssSelector("#new_password"));
		// lowercase
		password.sendKeys("aaa");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// upercase
		password.clear();
		password.sendKeys("AAA");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// number
		password.clear();
		password.sendKeys("123");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// special char
		password.clear();
		password.sendKeys("!@#");
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		// 8 chars
				password.clear();
				password.sendKeys("123aAaaaaaaa");
				Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
				
				
				//full
				password.clear();
				password.sendKeys("12345aA@123");
				Assert.assertTrue(driver.findElement(By.cssSelector(".av-password.success-check")).isDisplayed());
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
		// driver.quit();
	}

}
