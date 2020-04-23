package api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Topic_10_Window_Exercise {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();

		// driver = new FirefoxDriver();

		// Wait cho element được hiển thị thao tác
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Windows_Tab() {
		driver.get("https://kyna.vn/");

		String parentWindowID = driver.getWindowHandle();
		System.out.println("Parent ID (Kyna.vn) : " + parentWindowID);

		driver.findElement(By.xpath("//img[@alt='android-app-icon']")).click();
		sleepInSecond(2);

		String childID = driver.getWindowHandle();
		System.out.println("Child ID (Kyna Android App) : " + childID);

		swichToWindowsByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.kyna.app");

		swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

		driver.findElement(By.xpath("//img[@alt='apple-app-icon']")).click();
		swichToWindowsByTitle("KYNA on the App Store");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://apps.apple.com/us/app/kyna/id1384374935?ls=1");
		
		closeWindowsWithoutParent(parentWindowID);
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
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
	
	public void closeWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		for(String id : allWindows) {
			if(!id.equals(parentID)) {
				System.out.println(id);
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
