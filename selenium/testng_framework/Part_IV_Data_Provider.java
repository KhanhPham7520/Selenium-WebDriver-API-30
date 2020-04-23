package testng_framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Part_IV_Data_Provider {
	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", "/Users/apple/Desktop/geckodriver");
		}else if(browserName.equals("safari")) {
			
		}else if(browserName.equals("opera")) {
			
		}
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
}
