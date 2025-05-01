package api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_User_Interaction_HTML5 {
    Actions action;
    WebElement element;
    String javascriptPath = "";
    String jqueryPath = "";
    private WebDriver driver;

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
    public void TC_01_Hover() {
        driver.get("https://www.myntra.com/");
        element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[contains(text(),'Discover')]"));
        action.moveToElement(element).perform();
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[text()='Lacoste']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']/span[text()='Lacoste Products']")).isDisplayed());

    }

    @Test
    public void TC_03_Click_And_Hold_Random() {
        driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        System.out.println("Item number = " + allItems.size());
        sleepInSecond(3);

        action.keyDown(Keys.CONTROL).perform();

        action.click(allItems.get(0));
        action.click(allItems.get(3));
        action.click(allItems.get(7));
        action.click(allItems.get(9));

        action.keyUp(Keys.CONTROL).perform();
        sleepInSecond(3);

        List<WebElement> allItemSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

        Assert.assertEquals(allItemSelected.size(), 4);

        for (WebElement item : allItemSelected) {
            System.out.println(item.getText());
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

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
