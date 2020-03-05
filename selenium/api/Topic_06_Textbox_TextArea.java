package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;

	String customerID;
	String username = "mngr243598";
	String password = "bezevEh";

	// Input in New Customer /output (server) data
	String customerName = "Jason Staham";
	String gender = "male";
	String dateOfBirth = "1983-05-05";
	String address = "255 PA Hamlet";
	String city = "Hawaii";
	String state = "New York";
	String pin = "999777";
	String phone = "0988555777";
	String email = "jsstaham" + randomNumber() + "@hotmail.com";

	// Input in Edit Customer
	String editAddress = "255 PO Boxing";
	String editCity = "New Jersey";
	String editState = "Stock";
	String editPin = "888666";
	String editPhone = "0958666777";
	String editEmail = "jsstaham" + randomNumber() + "@gmail.com";

	// Locator for New / Edit Customer form
	By nameTextbox = By.xpath("//input[@name='name']");
	By radioGender = By.xpath("//input[@value='m']");
	By genderTexbox = By.name("gender");
	By dateOfBirthTextbox = By.name("dob");
	By textareaAddress = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailIDTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	By submitButton = By.name("sub");

	// Random Method
	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt();
	}

	@BeforeClass // Pre-condition
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("http://demo.guru99.com/v4/index.php");

		WebElement uID = driver.findElement(By.name("uid"));
		WebElement uPassword = driver.findElement(By.name("password"));
		WebElement btnLogin = driver.findElement(By.name("btnLogin"));

		uID.sendKeys(username);
		uPassword.sendKeys(password);
		btnLogin.click();

		String homePageWelcomeMessage = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homePageWelcomeMessage, "Welcome To Manager's Page of Guru99 Bank");

		Assert.assertTrue(
				driver.findElement(By.xpath("//tr[@class='heading3']//td[text()='Manger Id : " + username + "']"))
						.isDisplayed());

	}

	@Test
	public void TC_01_NewCustomer() throws Exception {

		// input data to New Customer form
		driver.findElement(By.xpath("//li[@class='orange']//following-sibling::li//a[text()='New Customer']")).click();
		driver.findElement(nameTextbox).sendKeys(username);
		driver.findElement(radioGender).sendKeys(gender);
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(textareaAddress).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailIDTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());

		// verify output data = input data
		Assert.assertEquals(customerName,
				driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
		Assert.assertEquals(gender,
				driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth,
				driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
		Assert.assertEquals(address,
				driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
		Assert.assertEquals(state,
				driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
		Assert.assertEquals(phone,
				driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
		Assert.assertEquals(email,
				driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
	}

	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		System.out.println("Customer ID is created in TC01 : " + customerID);

		driver.findElement(By.name("AccSubmit")).click();

		// verify Name/Gender/DOB is disable field
		Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(radioGender).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextbox).isEnabled());

		// Verify output at Edit Customer form = input at New Customer form
		Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTexbox).getAttribute("value"));
		Assert.assertEquals(dateOfBirth, driver.findElement(dateOfBirthTextbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(textareaAddress).getText());
		Assert.assertEquals(city, driver.findElement(cityTextbox).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(stateTextbox).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinTextbox).getAttribute("value"));
		Assert.assertEquals(phone, driver.findElement(phoneTextbox).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailIDTextbox).getAttribute("value"));

		// Edit data at Edit Customer Form
		driver.findElement(textareaAddress).clear();

		driver.findElement(textareaAddress).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();

		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();

		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();

		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(phoneTextbox).clear();

		driver.findElement(phoneTextbox).sendKeys(editPhone);
		driver.findElement(emailIDTextbox).clear();
		driver.findElement(emailIDTextbox).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		
		
		
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"))
						.isDisplayed());

		// verify output data = input data
		Assert.assertEquals(customerID,
				driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText());
		Assert.assertEquals(customerName,
				driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
		Assert.assertEquals(gender,
				driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth,
				driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
		
		Assert.assertEquals(editAddress,
				driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
		Assert.assertEquals(editCity, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
		Assert.assertEquals(editState,
				driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
		Assert.assertEquals(editPin, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
		Assert.assertEquals(editPhone,
				driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
		Assert.assertEquals(editEmail,
				driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
