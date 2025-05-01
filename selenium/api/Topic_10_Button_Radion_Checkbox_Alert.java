package api;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_10_Button_Radion_Checkbox_Alert {
    private WebDriver driver;
    private Actions actions;

    @BeforeClass
    public void beforeClass() {
        // Compile in Local Macbook Device URL
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        // Compile in Local Windows Device URL
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_Button_JavaScriptExecutor() {
        driver.get("http://live.demoguru99.com/");

        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement footerMyAccount = driver.findElement(By.xpath("//div[@class='footer']//ul//li[@class='first']//a[contains(text(),'My Account')]"));
        je.executeScript("arguments[0].click();", footerMyAccount);

        String loginURL = (String) je.executeScript("return window.top.location.href.toString()");
        Assert.assertEquals(loginURL, "http://live.demoguru99.com/index.php/customer/account/login/");

        WebElement btnCreateAnAccount = driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]"));
        je.executeScript("arguments[0].click();", btnCreateAnAccount);

        String createAccountURL = (String) je.executeScript("return window.top.location.href.toString()");
        Assert.assertEquals(createAccountURL, "http://live.demoguru99.com/index.php/customer/account/create/");

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        WebElement engine3Rad = driver.findElement(By.id("engine3"));
        je.executeScript("arguments[0].click();", engine3Rad);
        Assert.assertTrue(engine3Rad.isSelected());

    }

    @Test
    public void TC_02_Default_Checkbox_Or_RadioButton() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement dualZoneChkBox = driver.findElement(By.id("eq5"));
        je.executeScript("arguments[0].click();", dualZoneChkBox);
        Assert.assertTrue(dualZoneChkBox.isSelected());

        je.executeScript("document.getElementById('eq5').checked=false");
        Assert.assertFalse(dualZoneChkBox.isSelected());

    }

    @Test
    public void TC_03_Custom_Checkbox_Or_Radio_Button() {
        driver.get("https://material.angular.io/components/radio/examples");
        JavascriptExecutor je = (JavascriptExecutor) driver;

        WebElement summerRad = driver.findElement(By.xpath("//input[@id='mat-radio-4-input']"));
        je.executeScript("arguments[0].click();", summerRad);
        Assert.assertTrue(summerRad.isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checkedRad = driver.findElement(By.xpath("//input[@id='mat-checkbox-1-input']"));
        WebElement indetermineRad = driver.findElement(By.xpath("//input[@id='mat-checkbox-2-input']"));

        je.executeScript("arguments[0].click();", checkedRad);
        je.executeScript("arguments[0].click();", indetermineRad);

        Assert.assertTrue(checkedRad.isSelected());
        Assert.assertTrue(indetermineRad.isSelected());

        je.executeScript("document.getElementById('mat-checkbox-1-input').checked=false");
        je.executeScript("document.getElementById('mat-checkbox-2-input').checked=false");

        Assert.assertFalse(checkedRad.isSelected());
        Assert.assertFalse(indetermineRad.isSelected());

    }

    @Test
    public void TC_04_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement btnJSAlert = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]"));
        // je.executeScript("arguments[0].click()", btnJSAlert);
        btnJSAlert.click();

        Alert alert = driver.switchTo().alert();
        String alertMsg = alert.getText();
        alert.accept();
        Assert.assertEquals(alertMsg, "You clicked an alert successfully");
        // Assert.assertEquals(alertMsg, "I am a JS Alert");

    }

    @Test
    public void TC_05_Confirm_Alert() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement btnJSConfirm = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
        btnJSConfirm.click();

        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertMsg = alert.getText();
        Assert.assertEquals(alertMsg, "I am a JS Confirm");

        alert.dismiss();

        WebElement resultMsg = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMsg.getText(), "You clicked: Cancel");

    }

    @Test
    public void TC_06_Prompt_Alert() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement btnJSConfirm = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
        btnJSConfirm.click();

        Alert alert = driver.switchTo().alert();
        String msgJsPrompt = driver.switchTo().alert().getText();
        Assert.assertEquals(msgJsPrompt, "I am a JS prompt");
        Thread.sleep(3000);
        alert.sendKeys("daominhdam");
        Thread.sleep(3000);
        alert.accept();
        WebElement resultMsg = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMsg.getText(), "You entered: daominhdam");

    }

    @Test
    public void TC_07_Authentication_Alert() throws Exception {
        String username = "admin";
        String password = "admin";
        driver.get("http://the-internet.herokuapp.com/basic_auth");

        //WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
        Alert alert = driver.switchTo().alert();
        driver.switchTo().alert().sendKeys(username);
        driver.switchTo().alert().sendKeys(password);
        alert.accept();
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String byPassAuthenticationAlert(String url, String username, String password) {
        String[] split = url.split("//");
        url = split[0] + "//" + username + ":" + password + "@" + split[1];
        System.out.println("New URL : " + url);
        return password;

    }

}
