package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_10_Window_Tab {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "library/chromedriver");
        driver = new ChromeDriver();

        // driver = new FirefoxDriver();

        // Wait cho element được hiển thị thao tác
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

    }

    @Test
    public void TC_04_Windows_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        swichToWindowsByTitle("https://www.google.com.vn/");
        Assert.assertEquals(driver.getTitle(), "Google");
        swichToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
        sleepInSecond(3);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        swichToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký");
        Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
        sleepInSecond(3);

        swichToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        swichToWindowsByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
        Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

        swichToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
        closeWindowsWithoutParent(parentWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
    }

    @Test
    public void TC_05_Windows_Tab() {
        driver.get("https://kyna.vn/");
        String parentWindow = driver.getWindowHandle();
        sleepInSecond(3);

        driver.findElement(By.xpath("//img[@alt='facebook']//parent::a")).click();
        swichToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.xpath("//img[@alt='youtube']//parent::a")).click();
        swichToWindowsByTitle("Kyna.vn - YouTube");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.xpath("//img[@alt='zalo']//parent::a")).click();
        swichToWindowsByTitle("Kyna.vn");
        Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        sleepInSecond(3);

        driver.switchTo().alert().dismiss();

        driver.findElement(By.xpath("//img[@alt='apple-app-icon']//parent::a")).click();
        swichToWindowsByTitle("KYNA on the App Store");
        Assert.assertEquals(driver.getCurrentUrl(), "https://apps.apple.com/us/app/kyna/id1384374935?ls=1");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.xpath("//a[@title='Android']")).click();
        swichToWindowsByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
        Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.kyna.app");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        WebElement facebookIframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
        driver.switchTo().frame(facebookIframe);
        driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        driver.switchTo().defaultContent();
        // swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");

        driver.findElement(By.xpath("//img[@alt='kyna.vn']//parent::a[@class='img']")).click();
        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
        Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

        driver.findElement(By.xpath("//img[@alt='kynabiz.vn']//parent::a[@class='img']")).click();
        swichToWindowsByTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
        Assert.assertEquals(driver.getCurrentUrl(), "https://kynabiz.vn/");

        swichToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
        closeWindowsWithoutParent(parentWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
    }

    @Test
    public void TC_06_Windows_Tab() {
        driver.get("http://live.demoguru99.com/index.php/");
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();


        driver.findElement(By.xpath("//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//ul[@class='add-to-links']//li//a[text()='Add to Compare']")).click();
        String msgAddSonyExperiaSuccess = "The product Sony Xperia has been added to comparison list.";
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//ul//li//span")).getText(), msgAddSonyExperiaSuccess);


        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//ul[@class='add-to-links']//li//a[text()='Add to Compare']")).click();
        String msgAddSamsungGalaxySuccess = "The product Samsung Galaxy has been added to comparison list.";
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//ul//li//span")).getText(), msgAddSamsungGalaxySuccess);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        swichToWindowsByTitle("Products Comparison List - Magento Commerce");

        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.findElement(By.xpath("//button[@title='Close Window']")).click();

        swichToWindowsByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();

        driver.switchTo().alert().accept();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//ul//li//span")).getText(), "The comparison list was cleared.");
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void swichToWindowsByTitle(String windowTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("All windows : " + allWindows);
        for (String id : allWindows) {
            driver.switchTo().window(id);
            String title = driver.getTitle();
            if (title.equals(windowTitle)) {
                break;
            }
        }
    }

    public void closeWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        // System.out.println(allWindows);
        for (String id : allWindows) {
            if (!id.equals(parentID)) {
                System.out.println(id);
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
        DriverUtils.killDriverProcess();
    }

}
