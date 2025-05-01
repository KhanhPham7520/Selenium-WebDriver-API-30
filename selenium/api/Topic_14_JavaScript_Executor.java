package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_14_JavaScript_Executor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

    }

    @Test
    public void TC_01() {
        navigateToUrlByJS("http://live.guru99.com/");

        String liveGuruDomain = (String) executeForBrowser("return document.domain;");
        System.out.println("Domain : " + liveGuruDomain);
        Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

        String liveGuruURL = (String) executeForBrowser("return document.URL;");
        System.out.println("URL : " + liveGuruURL);
        Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");

        highlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(isTextInInnerHTML("Samsung Galaxy was added to your shopping cart."));

        highlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String customerServiceTitle = (String) executeForBrowser("return document.title;");
        System.out.println("Live guru domain = " + customerServiceTitle);
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        highlightElement("//input[@id='newsletter']");
        scrollToElement("//input[@id='newsletter']");

        navigateToUrlByJS("http://demo.guru99.com/v4/");

    }

    @Test
    public void TC_02_JS() {
        //driver.get("");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    // Browser
    public Object executeForBrowser(String javaSript) {
        return jsExecutor.executeScript(javaSript);
    }

    public boolean isTextInInnerHTML(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        System.out.println("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    // Element
    public void highlightElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

    }

    public void clickToElementByJS(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(String locator) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(String locator, String value) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        element = driver.findElement(By.xpath(locator));
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }
}
