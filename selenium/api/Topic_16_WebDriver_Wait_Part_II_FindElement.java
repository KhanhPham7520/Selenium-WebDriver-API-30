package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_WebDriver_Wait_Part_II_FindElement {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();

        // driver = new FirefoxDriver();

        // Wait cho element được hiển thị thao tác
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // mở app/aut ra
    }

    @Test
    public void TC_01_FindElement() {
        // 1. Nếu như tìm thấy 1 matching node nó sẽ làm gì -> 1 matching node
        // 2. Nếu như tìm thấy nhiều hơn 1 Element nó sẽ làm gì -> > 1 matching node
        // 3. Nếu như không tìm thấy element nào hết
        // driver.findElement(By.xpath("")).sendKeys("");
        // Nếu không tìm thấy => nó sẽ chờ cho hết implicit wait
        // Trong thời gian chờ thì cứ nửa giây nó tìm lại 1 lần
        // Nếu tìm thấy trong thời gian chờ thì nó sẽ pass step này và ko cần chờ hết timeout
        driver.get("https://automationfc.github.io/multiple-fields/index.html");
        driver.findElement(By.xpath("//input[@id='first_45']")).sendKeys("Automation FC");
        sleepInSecond(2);
    }

    @Test
    public void TC_02_FindElements() {
        // driver.findElements(By.xpath("")).get(0).sendKeys("");
        // List<WebElement> textboxes = driver.findElements(By.xpath(""));
        driver.get("https://automationfc.github.io/multiple-fields/index.html");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@id='first_45']"));
        System.out.println("Size cua Element : " + checkboxes.size());

        for (int i = 0; i < checkboxes.size(); i++) {
            checkboxes.get(i).click();
            sleepInSecond(1);
            Assert.assertTrue(checkboxes.get(i).isSelected());
        }

        for (WebElement checkbox : checkboxes) {
            checkbox.click();
            sleepInSecond(1);
            Assert.assertFalse(checkbox.isSelected());

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

}
