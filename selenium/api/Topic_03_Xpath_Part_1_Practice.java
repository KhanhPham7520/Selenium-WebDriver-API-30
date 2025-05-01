package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Part_1_Practice {

    private static String WEBSITE_TEST_URL_EXPECTED = "https://demo.guru99.com/";
    //	private static String WEBSITE_TEST_URL_EXPECTED = ""
    private static String MY_ACCOUNT_LINK_HEADER_XPATH = "//div[@class='page-header-container']//a[@class='skip-link skip-account']";
    private static String MY_ACCOUNT_LINK_FOOTER_XPATH = "//div[@class='footer-container']//a[@title='My Account']";
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        String projectDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectDir + "/library/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {
        driver.get(WEBSITE_TEST_URL_EXPECTED);
        // Click into My Account Link in Footer
        System.out.println("Click into Account Link in Footer");
        driver.findElement(By.xpath(MY_ACCOUNT_LINK_HEADER_XPATH)).click();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
