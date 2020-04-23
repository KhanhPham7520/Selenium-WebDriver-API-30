package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;

public class Topic_16_WebDriver_Wait_Part_VII_Fluent {
	private WebDriver driver;
	// private String rootFolderPath = System.getProperty("user.dir");
	FluentWait<WebDriver> fluentDriver;	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();

		fluentDriver = new FluentWait<WebDriver>(driver);
		// driver = new FirefoxDriver();

		// Wait cho element được hiển thị thao tác
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01() {
	
	}

	@Test
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
