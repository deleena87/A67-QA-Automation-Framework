import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest
{
    public WebDriver driver = null;
    public ChromeOptions options = null;
    public String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass()
    {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"baseURL"})
    public void launchBrowser(String baseURL)
    {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    public void emailInput ()
    {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys("elena.skrynnikova@testpro.io");
    }
    public void passwordInput()
    {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys("12345678");
    }
    public void clickSubmit()
    {
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
    }
    public void verifyLoginSuccess()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }
    public void clickPlaylist1()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement playlist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#!/playlist/102698']")));
        playlist1.click();
    }
    public void deletePlaylist() throws InterruptedException
    {
        Thread.sleep(2000);
        WebElement deletePlaylistBtn = driver.findElement(By.xpath("//button[@title='Delete this playlist']"));
        deletePlaylistBtn.click();
    }
    public void confirmationMessage() throws InterruptedException
    {
        Thread.sleep(5000);
        WebElement message = driver.findElement(By.xpath("//p[@class='msg']"));
        Assert.assertTrue(message.isDisplayed());
    }
    public void clickOk() throws InterruptedException {
        Thread.sleep(2000);
        WebElement okBtn = driver.findElement(By.xpath("//button[@class='ok']"));
        okBtn.click();
    }
    public void messageDisplayed()
    {
        String expectedMessage = "Deleted playlist \"2nd playlist.\"";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
        String actualMessage = notification.getText();
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}