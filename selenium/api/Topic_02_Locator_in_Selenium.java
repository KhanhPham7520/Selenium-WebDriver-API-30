package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator_in_Selenium {

	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	

	public void TC_02_Locator() {
		//driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
