package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Topic_01_Setup_Environment {
    private WebDriver driver;

    // Chạy đầu tiên 1 lần trước các testcases
    // ưu tiên mở cái đầu tiên lên trước
    @BeforeClass
    public void beforeClass() {
        String projectDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectDir + "/library/chromedriver");
        driver = new ChromeDriver();

//		driver = new FirefoxDriver();

        // Phóng to trình duyệt
        driver.manage().window().maximize();

        // Wait cho element được hiển thị thao tác
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Phóng to trình duyệt
        driver.manage().window().maximize();

        // mở app/aut ra
        driver.get("https://www.google.com/");

    }

    // Xem đây như là 1 testcase
    @Test
    public void TC_01_Check_Google_Title() {
        String googleTitle = driver.getTitle();
        // System.out.println(googleTitle);

        // Verify title này có đúng như mong đợi hay không
        Assert.assertEquals(googleTitle, "Google");

    }

    @Test
    public void TC_02_Check_Google_URL() {
        String googleURL = driver.getCurrentUrl();
        System.out.println(googleURL);
        Assert.assertEquals(googleURL, "https://www.google.com/");
    }

    @Test
    public void TC_03_Check_Google_Logo() {
        // Check Google logo is displayed
        // WebElement googleLogoXpath = driver.findElement(By.cssSelector("#hplogo"));
        Assert.assertTrue(driver.findElement(By.cssSelector("#hplogo")).isDisplayed());
    }

    // Chạy cuối cùng ở các testcases
    // Post-condition(Manual)
    @AfterClass
    public void afterClass() throws IOException {

        Runtime runtime = Runtime.getRuntime();
        String os = System.getProperty("os.name").toLowerCase();

        // Tắt Brownser
        driver.close();
        if (os.contains("win")) {
            // Windows: Dùng taskkill để kill process
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            // macOS/Linux: Dùng pkill
            runtime.exec("pkill -f node");
            runtime.exec("pkill -f appium");
        } else {
            System.out.println("Unsupported OS: " + os);
        }
    }

}
