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

    // Random Method
    public static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt();
    }

    @BeforeClass
    public void beforeClass() {
        // Compile in Local Macbook Device URL
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_popup() {
        driver.get("https://www.javacodegeeks.com/");
        // Nếu hiển thị popup thì close popup
        // Nếu ko hiển thị thì qua step tiếp theo
        sleepInSecond(10);
        WebElement popupStartScreen = driver.findElement(By.xpath("//div[@id='ulp-l3Fov9NjSByYKceZ-overlay']"));
        if (popupStartScreen.isDisplayed()) {
            driver.findElement(By.xpath("//a[contains(text(),'Close Popup')]")).click();
        }
        sleepInSecond(6);
        driver.findElement(By.xpath("//div[@id='ulp-inline-layer-tgXfmyDevZDZosiD-392']//input[@name='ulp-email']")).sendKeys(randomEmail);
        driver.findElement(By.xpath("//div[@id='ulp-inline-layer-tgXfmyDevZDZosiD-384']//a[contains(text(),'Sign up')]")).click();
    }

    @Test
    public void TC_02_Iframe_Kyna() {
        driver.get("https://kyna.vn/");
        WebElement facebookIframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
        WebElement chatbotIframe = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));

        driver.switchTo().frame(facebookIframe);
        String likeCountNumber = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
        Assert.assertEquals(likeCountNumber, "170K likes");
        driver.switchTo().defaultContent();
        Assert.assertTrue(chatbotIframe.isDisplayed());

        driver.switchTo().frame("cs_chat_iframe");
        driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")).sendKeys("Automation FC");
        action.sendKeys(driver.findElement(By.xpath("//div[@ng-show='loggedinFirstTime']/textarea")), Keys.ENTER).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//form[@ng-submit='editUserInfo()']")).isDisplayed());

        driver.findElement(By.xpath("//div[@class='basic-inf']//following-sibling::div/input[@ng-model='userInfo.username']")).sendKeys("Khanh");
        driver.findElement(By.xpath("//div[@class='basic-inf']//following-sibling::div/input[@ng-model='userInfo.email']")).sendKeys(randomEmail);
        driver.findElement(By.xpath("//div[@class='basic-inf']//following-sibling::div/input[@ng-model='userInfo.phone']")).sendKeys("0123456789");
        Select select = new Select(driver.findElement(By.xpath("//div[@class='basic-inf']//following-sibling::div/select[@name='serviceSelect']")));
        select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
        sleepInSecond(3);
        driver.findElement(By.xpath("//div[@class='button_container left']//input")).click();

        // Switch to main page
        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//form[@id='search-form-index']//input")).sendKeys("Java");
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-heading']//h1")).getText(), "'Java'");
        // action.sendKeys(driver.findElement(By.xpath("//form[@id='search-form-index']//input")),Keys.ENTER).perform();

    }
    //
    // @Test
    // public void TC_03_() {
    //
    // }

    @AfterClass
    public void afterClass() {
        driver.quit();
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
