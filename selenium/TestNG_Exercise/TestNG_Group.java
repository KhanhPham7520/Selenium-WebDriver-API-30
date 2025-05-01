package TestNG_Exercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Group {
    WebDriver driver;

    @Test(groups = "customer")
    public void TC_01() {
        System.out.println("Run testcase 01");
    }

    @Test(groups = "customer")
    public void TC_02() {
        System.out.println("Run testcase 02");
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
