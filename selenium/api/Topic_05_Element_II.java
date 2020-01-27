package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Element_II {

	private WebDriver driver;
	private static final String CHAR_LIST = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String randomEmail = generateRandomEmail(10) + "@yopmail.com";

	@Test
	public static String generateRandomEmail(int length) {
		StringBuffer randStr = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();

		for (int i = 0; i < length; i++)
			randStr.append(CHAR_LIST.charAt(secureRandom.nextInt(CHAR_LIST.length())));
		return randStr.toString();
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckElementIsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement radAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement txtEducation = driver.findElement(By.xpath("//textarea[@id='edu']"));

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
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Element is enabled declaration
		WebElement txtEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement radAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement txtEducation = driver.findElement(By.xpath("//textarea[@id='edu']"));
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
		driver.get("https://automationfc.github.io/basic-form/index.html");

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

}
