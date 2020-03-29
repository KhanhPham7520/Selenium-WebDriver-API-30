package TestNG_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Priority_Description {
	WebDriver driver;

	@Test(enabled = true, description = "Create New Customer")
	public void TC_01_Create_New_Customer() {
		System.out.println("Run testcase 01");
	}

	@Test(enabled = true, description = "Create New Account")
	public void TC_02_Create_New_Account() {
		System.out.println("Run testcase 02");
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
