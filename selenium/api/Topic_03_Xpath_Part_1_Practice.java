package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_1_Practice {

	private WebDriver driver;
	private static String WEBSITE_TEST_URL_EXPECTED = "http://live.demoguru99.com/";
//	private static String WEBSITE_TEST_URL_EXPECTED = ""
	private static String MY_ACCOUNT_LINK_HEADER_XPATH = "//div[@class='page-header-container']//a[@class='skip-link skip-account']";
	private static String MY_ACCOUNT_LINK_FOOTER_XPATH = "//div[@class='footer-container']//a[@title='My Account']";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//div[@class="footer-container"]//a[@title="My Account"]
		
		
	}

	@Test
	public void TC_01() {
//		String WEBSITE_TEST_URL_ACTUAL = driver.getCurrentUrl();
//
//		if (WEBSITE_TEST_URL_ACTUAL.equalsIgnoreCase(WEBSITE_TEST_URL_EXPECTED)) {
//			driver.get(WEBSITE_TEST_URL_EXPECTED);
//		}
//		if (WEBSITE_TEST_URL_ACTUAL != null
//				&& WEBSITE_TEST_URL_ACTUAL.equalsIgnoreCase(WEBSITE_TEST_URL_EXPECTED) == false) {
//			System.out.println("Test case failed");
//		}
		driver.get(WEBSITE_TEST_URL_EXPECTED);
		// Click into My Account Link in Footer
		System.out.println("Click into Account Link in Footer");
		driver.findElement(By.xpath(MY_ACCOUNT_LINK_HEADER_XPATH)).click();
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
