package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Elements {
	WebDriver driver;



	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Nếu chỉ tương tác với element nhiều lần thì nên khai báo variable
		WebElement emailTextBox = driver
				.findElement(By.xpath("//div[@class='container']//descendant::input[@id='mail']"));
		emailTextBox.sendKeys("automationfc.vn@gmail.com");
		emailTextBox.clear();
		emailTextBox.sendKeys("automationfc.vn@gmail.com");
		Assert.assertTrue(emailTextBox.isDisplayed());

		// Thao tác với nhiều elements
		// tương tác all links ở page hiện tại
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		System.out.println("link size: " + links.size());

		// Get all text of links
		for (WebElement link : links) {
			System.out.println("Text = " + link.getText());
		}

		WebElement passwordTextBox = driver.findElement(By.xpath("//input[@id='password']"));
		String passwordTextBoxHint = passwordTextBox.getAttribute("placeholder");
		System.out.println(passwordTextBoxHint);

		// GUI : font/size/color/location/position/....
		// priority : font/color
		WebElement buttonBackground = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		String btnBackground = buttonBackground.getCssValue("color");
		String btnFontSize = buttonBackground.getCssValue("font-size");

		System.out.println(btnBackground);
		System.out.println(btnFontSize);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
