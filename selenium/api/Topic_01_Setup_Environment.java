package api;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_01_Setup_Environment {
	private WebDriver driver;

	// Chạy đầu tiên 1 lần trước các testcases
	// ưu tiên mở cái đầu tiên lên trước
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		// Phóng to trình duyệt
		driver.manage().window().maximize();

		// Wait cho element được hiển thị thao tác
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

		// mở app/aut ra
		driver.get("https://www.google.com/");

	}

	// Xem đây như là 1 testcase
	@Test
	public void TC_01_Check_Google_Title() {
		String googleTitle = driver.getTitle();
		System.out.println(googleTitle);

		// Verify title này có đúng như mong đợi hay không
		Assert.assertEquals(googleTitle, "Google");

	}

	@Test
	public void TC_02_Check_Google_URL() {
		String googleURL = driver.getCurrentUrl();
		System.out.println(googleURL);
		Assert.assertEquals(googleURL, "https://www.google.com/");
	}

	@Test
	public void TC_03_Check_Google_Logo() {
		// Check Google logo is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("#hplogo")).isDisplayed());
	}

	// Chạy cuối cùng ở các testcases
	// Post-condition(Manual)
	@AfterClass
	public void afterClass() {
		// Tắt Brownser
		driver.quit();
	}

}
