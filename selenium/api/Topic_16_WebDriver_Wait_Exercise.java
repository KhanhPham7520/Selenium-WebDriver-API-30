package api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_WebDriver_Wait_Exercise {
	private WebDriver driver;
	private WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);

		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void TC_01_Visible_Pass() {
		driver.get("https://www.facebook.com/");

		// Email displayed = visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	public void TC_01_Visible_Failed() {
		driver.get("https://www.facebook.com/");

		// Failed => Invisible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));

		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());

	}

	public void TC_02_Invisible_Pass_01() {
		// không xuất hiện trong UI mà có trong DOM
		System.out.println("Start step get = " + getDatetimeNow());
		driver.get("https://www.facebook.com/");
		System.out.println("Start step wait = " + getDatetimeNow());

		// khong xuat hien o UI - co xuat hien trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email_address']")));
		System.out.println("End step get = " + getDatetimeNow());

	}

	public void TC_02_Invisible_Fail() {
		// không xuất hiện trong UI mà có trong DOM
		driver.get("https://www.facebook.com/");
		//có xuất hiện trên UI -> wait invisible fail
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[text()='Invalid email address.']")));

	}

	@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		// Có hoặc không xuất hiện trên UI nhưng phải có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='email']")));
		
		//không xuất hiện trên UI nhưng phải có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		//Nếu như element không có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='address']")));

	}

	@Test
	public void TC_04_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("SubmitCreate")).click();
		WebElement emailErrorMessage = driver.findElement(By.xpath("//li[text()='Invalid email address.']"));
		driver.navigate().refresh();
		// Không xuất hiện trên UI
		explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
	}

	public String getDatetimeNow() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		return formatter.format(date);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
