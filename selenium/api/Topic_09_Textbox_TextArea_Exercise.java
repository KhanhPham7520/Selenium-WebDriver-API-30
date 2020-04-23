package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Textbox_TextArea_Exercise {
	private WebDriver driver;
	
	//Before Login 
	String usernameLogin = "mngr251334";
	String passwordLogin = "jAbEses";

	//After Login
	String marqueeWelcome = "Welcome To Manager's Page of Guru99 Bank";
	
	//New User Registration Mock Data
	String newCustomerName = "Pham Phan Nhat Khanh";
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		// Wait cho element được hiển thị thao tác
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Process_Textbox_TextArea() {
		driver.get("http://demo.guru99.com/v4/index.php");
		driver.findElement(By.name("uid")).sendKeys(usernameLogin);
		driver.findElement(By.name("password")).sendKeys(passwordLogin);
		
	//	Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3']")).isDisplayed());
		WebElement marqueeWelcomeElement = driver.findElement(By.xpath("//marquee[@class='heading3']"));
		Assert.assertEquals(marqueeWelcomeElement.getText(), marqueeWelcome);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
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
