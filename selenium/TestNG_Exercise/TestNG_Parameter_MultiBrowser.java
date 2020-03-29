package TestNG_Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_Parameter_MultiBrowser {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='email']");
	By passwordTextbox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("Webdriver.gecko.driver", "/Users/apple/Desktop/geckodriver");
			driver = new FirefoxDriver();
		}
	}

	@Test(dataProvider = "user_pass")
	public void TC_01_Login_To_System_MultiBrowser(String username, String password) throws Exception {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" }, { "selenium_11_03@gmail.com", "111111" } };
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Run afterClass");
		driver.quit();
	}

}
