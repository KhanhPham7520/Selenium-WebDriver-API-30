package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_WebDriver_Wait_Part_V_Explicit {
    WebDriverWait explicitWait;
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TC_01_Visible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        // 1- Click vào Start Button
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        // 2- Loading icon hiển thị và biến mất sau 5 giây

        // Chờ cho Hello World hiển thị trong vòng 10s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

        // 3- Hello World text được hiển thị ~ Loading icon biến mất (02)
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Invisible() {
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

        // 1- Click vào Start Button
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
        // 2- Chờ cho Loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        // 3- Hello World text được hiển thị ~ Loading icon biến mất (02)
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_03_Ajax_Loading() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        driver.findElement(By.xpath("//td[@title='Monday, March 23, 2020']")).click();

        //Wait for loading
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected' and @title='Monday, March 23, 2020']")));

        //	selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim(), "Monday, March 23, 2020");
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
