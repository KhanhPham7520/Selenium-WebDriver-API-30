package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
		// System.out.println(googleTitle);

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
		// WebElement googleLogoXpath = driver.findElement(By.cssSelector("#hplogo"));
		Assert.assertTrue(driver.findElement(By.cssSelector("#hplogo")).isDisplayed());
	}

	// Additional Demo
	@Test
	public void TC_04_Check_Text_Google_Search_Button() {
		String googleSearchTextButtonSelector = "//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]";
		WebElement googleSearchTextButton = driver.findElement(By.xpath(googleSearchTextButtonSelector));
		Assert.assertEquals(googleSearchTextButton, "Google Search");
	}

	@Test
	public void TC_05_Check_Images_Link_isDisplayed() {
		// WebElement imgLinkSel = driver.findElement(By.cssSelector("#gbw > div > div >
		// div.gb_9d.gb_i.gb_yg.gb_pg > div:nth-child(2) > a"));
		String imgLinkSel = "//*[@id=\"gbw\"]/div/div/div[1]/div[2]/a";
		WebElement imgLink = driver.findElement(By.xpath(imgLinkSel));

		Assert.assertTrue(imgLink.isDisplayed());
	}

	// Chạy cuối cùng ở các testcases
	// Post-condition(Manual)
	@AfterClass
	public void afterClass() {
		// Tắt Brownser
		driver.quit();
	}

}
