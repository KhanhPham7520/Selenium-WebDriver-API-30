package TestNG_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestNG_Dependencies_Test {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {
        driver.get("http://demo.guru99.com/");

    }

    @Test(dependsOnMethods = "TC_01")
    public void TC_02() {
        System.out.println("Run TC_02");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
