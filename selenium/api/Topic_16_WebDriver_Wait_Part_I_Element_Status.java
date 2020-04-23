package api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class Topic_16_WebDriver_Wait_Part_I_Element_Status {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		// explicitWait = new WebDriverWait(driver, 10);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_03_Invisible_3_Or_5_Seconds() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");

		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	@Test
	public void TC_05() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']"))).isDisplayed();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(), "No Selected Dates to display.");

		WebElement selectedDate = driver.findElement(By.xpath("//td[@title='Tuesday, March 03, 2020']//a"));
		selectedDate.click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		Assert.assertEquals(selectedDate.getText(), driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDatetimeNow() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		return formatter.format(date);
	}

}
