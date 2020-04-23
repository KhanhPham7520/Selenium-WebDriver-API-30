package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() throws InterruptedException {
		driver.get("https://egov.danang.gov.vn/reg");

		// thao tác với city dropdown
		select = new Select(driver.findElement(By.name("tinhThuongTru")));

		// kiểm tra dropdown được phép chọn nhiều
		// Assert.assertTrue(select.isMultiple());
		// select.isMultiple();

		// kiểm tra dropdown không được phép chọn nhiều
		boolean cityDropdownStatus = select.isMultiple();
		System.out.println("City status: " + cityDropdownStatus);
		Assert.assertFalse(cityDropdownStatus);

		/* *///
		// chọn TPHCM
		select.selectByIndex(4);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");

		select.selectByValue("11803");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Cà Mau");

		select.selectByVisibleText("tỉnh Lạng Sơn");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Lạng Sơn");

		// làm sao để biết trong dropdown này có bao nhiêu items
		int cityNumber = select.getOptions().size();
		System.out.println("All city number = " + cityNumber);
		Assert.assertEquals(cityNumber, 65);
		
		//In ra tất cả giá trị có trong dropdownlist 
		List<WebElement> allOptions = select.getOptions();
		for(WebElement option : allOptions) {
			System.out.println(option.getText());
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
