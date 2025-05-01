package TestNG_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Priority_Skip {
    WebDriver driver;

    @Test(groups = "shopping", priority = 1, enabled = true)
    public void TC_01() {
        System.out.println("Run testcase 01");
    }

    @Test(priority = 2, enabled = true)
    public void TC_02() {
        System.out.println("Run testcase 02");
    }

    @Test(groups = "buying", priority = 3, enabled = true)
    public void TC_03() {
        System.out.println("Run testcase 03");
    }

    @Test(groups = "shopping", priority = 1, enabled = true)
    public void TC_04() {
        System.out.println("Run testcase 04");
    }

    @Test(priority = 2, enabled = false)
    public void TC_05() {
        System.out.println("Run testcase 05");
    }

    @Test(priority = 3, enabled = false)
    public void TC_06() {
        System.out.println("Run testcase 06");
    }


    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Run beforeClass");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("Run afterClass");
        driver.quit();
    }

}
