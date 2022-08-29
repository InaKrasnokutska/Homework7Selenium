import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class part2 {

    private WebDriver driver;

    @BeforeSuite
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions()
                .setLogLevel(ChromeDriverLogLevel.INFO)
                .addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void testLockedUserAuthorization() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        assertEquals(driver.findElement(By.xpath("//div[@class='error-message-container error']/h3[contains(text(), 'user has been locked')]")).getText(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}