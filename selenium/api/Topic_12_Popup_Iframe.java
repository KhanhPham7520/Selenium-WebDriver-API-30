package api;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_12_Popup_Iframe {

    Actions action;
    WebElement element;
    String randomEmail = "jstaham" + randomNumber() + "@gmail.com";
    private WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Alert alert;

    // Random Method
    public static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt();
    }

    @BeforeClass
    public void beforeClass() {
        // Compile in Local Macbook Device URL
        System.setProperty("webdriver.chrome.driver", "library/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_javascript_alert_popup() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        sleepInSecond(5);
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //alert = driver.switchTo().alert();

        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert Text là : " + alertText);

    }

    @Test
    public void TC_02_html_popup() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        sleepInSecond(5);

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        String popupText = driver.switchTo().alert().getText();
        System.out.println("TC_02 : " + popupText);

    }

     @Test
     public void TC_03_enter_key_into_popup_then_click_ok() {
         driver.get("https://the-internet.herokuapp.com/javascript_alerts");
         sleepInSecond(5);

         driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
         Alert alert = driver.switchTo().alert();

         String alertText = alert.getText();
         System.out.println(alertText);

         alert.sendKeys("Khanh");
         alert.accept(); // Click OK

         Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
                 "You entered: Khanh");
     }

    @Test
    public void TC_03_enter_key_into_popup_then_click_cancel() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        sleepInSecond(5);

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        System.out.println(alertText);

        alert.sendKeys("Khanh");
        alert.dismiss(); // click Cancel

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
                "You entered: null");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
        DriverUtils.killDriverProcess();
    }

    public boolean isElementDisplayed(String locator) {
        try {
            // 1- Element is displayed + existed in DOM
            // 2- Element is not display + existed in DOM
            // 3- Element is not display + no existed in DOM
            WebElement element = driver.findElement(By.xpath(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
