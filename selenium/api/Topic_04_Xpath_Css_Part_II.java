package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.MessageUtilsForTopic04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class Topic_04_Xpath_Css_Part_II {

    By emailTextbox = By.id("email");
    By passwordTextbox = By.id("pass");
    By loginButton = By.id("send2");
    String myAccountHomePage = "//div[@class='footer']//div[@class='links']//ul//li[@class='first']//a[text()='My Account']";
    String firstNameRegister = "//div[@class='input-box']//input[@id='firstname']";
    String middleNameRegister = "//div[@class='input-box']//input[@id='middlename']";
    String lastNameRegister = "//div[@class='input-box']//input[@id='lastname']";
    String emailRegister = "//div[@class='input-box']//input[@id='email_address']";
    String passwordRegister = "//div[@class='input-box']//input[@id='password']";
    String confirmPassword = "//div[@class='input-box']//input[@id='confirmation']";
    String registerButton = "//div[@class='buttons-set']//button[@class='button']";
    String createAccountButton = "//div[@class='buttons-set']//a[@class='button']";
    // After success registration
    String registerSuccessMsg = "//li[@class='success-msg']//ul//li//span[text()='Thank you for registering with Main Website Store.']";
    // After Completed Registration
    String accountLinkHeaderAfterLogin = "//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']//span[text()='Account']";
    String accountLogOutAfterLogin = "//div[@id='header-account']//div[@class='links']//ul//li[@class=' last']//a[text()='Log Out']";
    String imgHomepageAfterLogout = "//div[@class='page-title']//img";
    String randomEmail = "nhatkhanh" + randomNumber() + "@hotmail.com";
    private WebDriver driver;

    // method generate random email
    // Random Method
    public static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt();
    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KhanhPPN-Web\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void runForEachTestMethod() {
        driver.get("http://live.guru99.com/");
        // Click into My Account ( Footer)
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

    }

    @Test
    public void TC_01_LoginWithEmptyEmailAndPassword() {

        // email
        driver.findElement(emailTextbox).sendKeys("");
        driver.findElement(passwordTextbox).sendKeys("");
        driver.findElement(loginButton).click();

        // 2.AssertEquals
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
                MessageUtilsForTopic04.REQUIRED_MESSAGE);
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
                MessageUtilsForTopic04.REQUIRED_MESSAGE);

    }

    @Test
    public void TC_02_LoginWithInvalidEmail() {
        driver.findElement(emailTextbox).sendKeys("12343421123@123421.12341");
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
                MessageUtilsForTopic04.REQUIRED_MESSAGE);
    }

    @Test
    public void TC_03_LoginWithPasswordLessThan6Chars() {
        driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("1234");
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithIncorrectPassword() {
        driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("skkasfksakakfaksak");
        driver.findElement(loginButton).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
                "Invalid login or password.");
    }

    @Test
    public void TC_05_LoginWithValidEmailAndPassword() {
        driver.findElement(emailTextbox).sendKeys("phamphannhatkhanh7520@yopmail.com");
        driver.findElement(passwordTextbox).sendKeys("foster456");
        driver.findElement(loginButton).click();

        // verify into Dashboard
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");
    }

    @Test
    public void TC_06_CreateNewUser() throws Exception {
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
                .click();

        // Log out click
        driver.findElement(By.xpath("//div[@class='links']//ul//li[@class=' last']//a")).click();
        driver.findElement(By.xpath(myAccountHomePage)).click();
        driver.findElement(By.xpath(createAccountButton)).click();

        driver.findElement(By.xpath(firstNameRegister)).sendKeys("Pham Phan");
        driver.findElement(By.xpath(middleNameRegister)).sendKeys("ABC");
        driver.findElement(By.xpath(lastNameRegister)).sendKeys("Nhat Khanh");
        driver.findElement(By.xpath(emailRegister)).sendKeys(randomEmail);

        driver.findElement(By.xpath(passwordRegister)).sendKeys("Abc123456789");
        driver.findElement(By.xpath(confirmPassword)).sendKeys("Abc123456789");
        driver.findElement(By.xpath(registerButton)).click();

        Assert.assertEquals(driver.findElement(By.xpath(registerSuccessMsg)).getText(),
                "Thank you for registering with Main Website Store.");

        driver.findElement(By.xpath(accountLinkHeaderAfterLogin)).click();
        driver.findElement(By.xpath(accountLogOutAfterLogin)).click();

        boolean imgAppearHomePageAfterLogout = driver.findElement(By.xpath(imgHomepageAfterLogout)).isDisplayed();
        assertTrue(imgAppearHomePageAfterLogout);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
