package api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_Files {
	private WebDriver driver;
	private String rootFolderPath = System.getProperty("user.dir");

	String projectNamePath = "SELENIUM_API_14_KHANH_PPN";

	String macProName = "MacPro.png";
	String thinkPadx1Name = "X1.jpg";
	String dellXPSName = "XPS.jpg";

	String macProPath = rootFolderPath + "//uploadFiles//" + macProName;
	String thinkpadX1Path = rootFolderPath + "//uploadFiles//" + thinkPadx1Name;
	String dellXPSPath = rootFolderPath + "//uploadFiles//" + dellXPSName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		// System.setProperty("webdriver.chrome.driver", rootFolderPath + "//library//chromedriver");
		driver = new ChromeDriver();

		// driver = new FirefoxDriver();

		// Wait cho element được hiển thị thao tác
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Sendkeys() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		System.out.println("URL : " + macProPath);
		// Tìm 1 element và lưu nó vào biến uploadFile (A)
		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		uploadFile.sendKeys(macProPath + "\n" + thinkpadX1Path + "\n" + dellXPSPath);
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='MacPro.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='X1.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='XPS.jpg']")).isDisplayed());

		// click in Start Button at each file
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table .start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + macProName + "' and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + thinkPadx1Name + "' and @href]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dellXPSName + "' and @href]")).isDisplayed());
	}

	@Test
	public void TC_04() {
		driver.get("https://gofile.io/?t=uploadFiles");
	//	String parentWindows = driver.getWindowHandle();
		String subWindowHandler = null;
		WebElement uploadFile = driver.findElement(By.name("filesUploaded"));
		uploadFile.sendKeys(macProPath + "\n" + thinkpadX1Path + "\n" + dellXPSPath);
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + macProName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + thinkPadx1Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + dellXPSName + "']")).isDisplayed());

		driver.findElement(By.id("btnUpload")).click();

		sleepInSecond(6);

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window

		Assert.assertEquals(driver.findElement(By.xpath("//h2[@id='swal2-title']//strong[text()='Success !']")).getText(), "Success !");
		
		driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//a[@id='link']")).click();
		
	    List<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		
		driver.switchTo().window(tabs2.get(1));
		
		List<WebElement> downloadButtons = driver.findElements(By.xpath("//a[@class='download mr-1']//button"));
		for(WebElement downloadButtonItem : downloadButtons) {
			Assert.assertTrue(downloadButtonItem.isDisplayed());
		}
		
		
		
		

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
	
	public void swichToWindowsByTitle(String windowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("All windows : " + allWindows);
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if (title.equals(windowTitle)) {
				break;
			}
		}
	}

}
