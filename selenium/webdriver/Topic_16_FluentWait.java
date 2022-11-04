package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_16_FluentWait {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#javascript_countdown_time")));
		
		WebElement countdown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
		//khởi tạo
		fluentElement = new FluentWait<WebElement>(countdown);
		//set tổng thời gian và tần số
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(100))
		.ignoring(NoSuchElementException.class);
		
		//apply điều kiện
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement countdown) {
				System.out.println(countdown.getText());
				return countdown.getText().endsWith("00");
			}
		});
		

	}

	@Test
	public void TC_02_FluentWait_automationFC() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//chờ cho Start button visible và nhấn click
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']"))).click();
		
		WebElement textHello = driver.findElement(By.cssSelector("div#finish h4"));
		//Khởi tạo và set thời gian cho fluent wait 
		fluentElement = new FluentWait<WebElement>(textHello);
		fluentElement.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofMillis(1000))
		.ignoring(NoSuchElementException.class);
		
		//apply điều kiện
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement textHello) {
				System.out.println("Time" + textHello.getText());
				return textHello.getText().contains("Hello World!");
			}
		});

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
