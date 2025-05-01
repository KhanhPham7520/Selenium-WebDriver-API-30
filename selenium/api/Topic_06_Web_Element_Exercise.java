package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Web_Element_Exercise {
    public static final String WEBSITE_TEST_URL = "https://automationfc.github.io/basic-form/index.html";
    By radioAgeUnder18 = By.id("under_18");
    By textboxEmail = By.id("mail");
    By textareaEducation = By.id("edu");
    String randomEmail = "jsstaham" + randomNumber() + "hotmail.com";
//	By Under18RadBy = By.id(id);
    private WebDriver driver;

    // Random Method
    public static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt();
    }

    @BeforeClass
    public void beforeClass() {
        // Compile in Local Macbook Device URL
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        // Compile in Local Windows Device URL
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_CheckElementIsDisplayed() {
        driver.get(WEBSITE_TEST_URL);

        // WebElement txtEmail = driver.findElement(By.xpath("//input[@id='mail']"));
        WebElement txtEmail = driver.findElement(textboxEmail);
        // WebElement radAgeUnder18 =
        // driver.findElement(By.xpath("//input[@id='under_18']"));
        WebElement radAgeUnder18 = driver.findElement(radioAgeUnder18);
        WebElement txtEducation = driver.findElement(textareaEducation);

        if (txtEmail.isDisplayed()) {
            txtEmail.sendKeys(randomEmail);
        }
        if (txtEducation.isDisplayed()) {
            txtEducation.sendKeys("Computer Science");
        }
        if (radAgeUnder18.isDisplayed()) {
            radAgeUnder18.click();
        }

    }

    @Test
    public void TC_02_CheckElementIsEnabled() {
        driver.get(WEBSITE_TEST_URL);

        // Element is enabled declaration
        // WebElement txtEmail = driver.findElement(By.xpath("//input[@id='mail']"));
        WebElement txtEmail = driver.findElement(textboxEmail);
        // WebElement radAgeUnder18 =
        // driver.findElement(By.xpath("//input[@id='under_18']"));
        WebElement radAgeUnder18 = driver.findElement(radioAgeUnder18);
        // WebElement txtEducation =
        // driver.findElement(By.xpath("//textarea[@id='edu']"));
        WebElement txtEducation = driver.findElement(textareaEducation);
        WebElement selJobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
        WebElement radDevelopmentInt = driver
                .findElement(By.xpath("//label[text()='Interests:']//following-sibling::input[@id='development']"));
        WebElement slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));

        // Elements is disabled declaration
        WebElement txtPassword = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement radAgeDisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
        WebElement txtAreaBiography = driver.findElement(By.xpath("//textarea[@id='bio']"));
        WebElement selJobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
        WebElement chkInterestDisabled = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
        WebElement slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));

        // check if elements is enabled declaration
        Assert.assertTrue(txtEmail.isEnabled());
        Assert.assertTrue(radAgeUnder18.isEnabled());
        Assert.assertTrue(txtEducation.isEnabled());
        Assert.assertTrue(selJobRole1.isEnabled());
        Assert.assertTrue(radDevelopmentInt.isEnabled());
        Assert.assertTrue(slider01.isEnabled());

        // if check if elements is disabled
        boolean txtPasswordIsDisabled = txtPassword.isEnabled();
        boolean radAgeIsDisabled = radAgeDisable.isEnabled();
        boolean txtAreaBiographyIsDisabled = txtAreaBiography.isEnabled();
        boolean selJobRole2IsDisabled = selJobRole2.isEnabled();
        boolean chkInterestDisabledIsDisabled = chkInterestDisabled.isEnabled();
        boolean slider02IsDisabled = slider02.isEnabled();

        if (txtPasswordIsDisabled != true) {
            System.out.println("Password is disabled");
        }
        if (radAgeIsDisabled != true) {
            System.out.println("Age Radio button is disabled");
        }
        if (txtAreaBiographyIsDisabled != true) {
            System.out.println("Biography is disabled");
        }
        if (selJobRole2IsDisabled != true) {
            System.out.println("Job Role 2 is disabled");
        }
        if (chkInterestDisabledIsDisabled != true) {
            System.out.println("Interests is disabled");
        }
        if (slider02IsDisabled != true) {
            System.out.println("Slider 02 is disabled");
        }

        if (txtPasswordIsDisabled == true) {
            System.out.println("Password is enabled");
        }
        if (radAgeIsDisabled == true) {
            System.out.println("Age Radio button is enabled");
        }
        if (txtAreaBiographyIsDisabled == true) {
            System.out.println("Biography is enabled");
        }
        if (selJobRole2IsDisabled == true) {
            System.out.println("Job Role 2 is enabled");
        }
        if (chkInterestDisabledIsDisabled == true) {
            System.out.println("Interests is enabled");
        }
        if (slider02IsDisabled == true) {
            System.out.println("Slider 02 is enabled");
        }

    }

    @Test
    public void TC_03_CheckElementIsSelected() throws Exception {
        driver.get(WEBSITE_TEST_URL);

        WebElement ageRadUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        WebElement chkDevelopment = driver.findElement(By.xpath("//input[@id='development']"));

        if (ageRadUnder18.isDisplayed()) {
            ageRadUnder18.click();

        }
        if (chkDevelopment.isDisplayed()) {
            chkDevelopment.click();
        }

        Assert.assertTrue(ageRadUnder18.isSelected());
        Assert.assertTrue(chkDevelopment.isSelected());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    // Common Method

    //1. Common isElementDisplayed
    public boolean isElementDisplayed(By by) {
        WebElement element = driver.findElement(by);
        if (element.isDisplayed()) {
            System.out.println("Element----" + by + "----- is displayed");
            return true;
        } else {
            System.out.println("Element----" + by + "----- is displayed");
            return false;
        }
    }

    //2.Common sendKeys Method
    public void sendKeyToElement(By by, String value) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

}
