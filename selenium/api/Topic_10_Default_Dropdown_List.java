package api;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Dropdown_List {
	private WebDriver driver;
	private Select select;

	@BeforeClass
	public void beforeClass() {
		// Compile in Local Macbook Device URL
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		// Compile in Local Windows Device URL
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	@Test
	public void TC_02_HTML_Dropdown_Part_1() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		boolean isMultipleDropdownJob01 = select.isMultiple();
		Assert.assertFalse(isMultipleDropdownJob01);

		select.selectByVisibleText("Manual Testing");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		// job01Dropdown.selectByIndex(9);
		// Thread.sleep(3000);
		// Assert.assertEquals(job01Dropdown.getFirstSelectedOption().getText(), "Functional UI Testing");

		Assert.assertEquals(select.getOptions().size(), 10);

		// Job Role 02
		WebElement selectJob02List = driver.findElement(By.id("job2"));
		Select job02Dropdown = new Select(selectJob02List);
		boolean isMultipleDropdownJob02 = job02Dropdown.isMultiple();
		assertTrue(isMultipleDropdownJob02);

		WebElement automationSelValue = driver.findElement(By.xpath("//select[@id='job2']//option[contains(text(),'Automation')]"));
		WebElement mobileSelValue = driver.findElement(By.xpath("//select[@id='job2']//option[contains(text(),'Mobile')]"));
		WebElement desktopSelValue = driver.findElement(By.xpath("//select[@id='job2']//option[contains(text(),'Desktop')]"));

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(automationSelValue).click(mobileSelValue).click(desktopSelValue).build().perform();
		
//		Assert.assertEquals(job02Dropdown.getFirstSelectedOption().getText(), "Automation");
//		Assert.assertEquals(job02Dropdown.getFirstSelectedOption().getText(), "Mobile");
//		Assert.assertEquals(job02Dropdown.getFirstSelectedOption().getText(), "Desktop");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
