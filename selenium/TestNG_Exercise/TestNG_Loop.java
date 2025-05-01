package TestNG_Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Loop {
    WebDriver driver;
    By emailTextbox = By.xpath("//input[@id='email']");
    By passwordTextbox = By.xpath("//input[@id='pass']");
    By loginButton = By.xpath("//button[@id='send2']");


    String username = "selenium_11_01@gmail.com";
    String password = "111111";

    @BeforeClass
    public void beforeClass(String browserName) {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
    }

    @Test(invocationCount = 4)
    public void TC_01_Login_To_System_MultiBrowser() throws Exception {
        driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

        driver.findElement(emailTextbox).sendKeys(username);
        driver.findElement(passwordTextbox).sendKeys(password);
        driver.findElement(loginButton).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();

        driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
