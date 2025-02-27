import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest
{
    public static WebDriver driver = null;
    public static ChromeOptions options = null;
    public static WebDriverWait wait = null;
    public static String url = null;
    public static Actions actions = null;
    @BeforeSuite
    static void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    @Parameters ({"baseURL"})
    public void launchBrowser(String baseURL)
    {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver=new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    public void emailInput ()
    {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        emailField.clear();
        emailField.sendKeys("elena.skrynnikova@testpro.io");
    }

    public void passwordInput()
    {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        passwordField.clear();
        passwordField.sendKeys("12345678");
    }

    public void clickSubmit ()
    {
        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        submitBtn.click();
    }

    public void verifyLoginSuccess()
    {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }

    public void doubleClickPlaylist()
    {
        actions = new Actions(driver);
        WebElement favourites = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='playlist favorites']")));
        favourites.click();
        WebElement recentlyPlayed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='playlist recently-played']")));
        recentlyPlayed.click();
        WebElement playlist1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlist1).perform();
    }

    public void enterNewPlaylistName()
    {
        String newPlaylistName = "NewPlaylist";
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        actions.doubleClick(playlistInputField).perform();
        playlistInputField.sendKeys(Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getUpdatedPlaylistSuccessMsg()
    {
        WebElement notificationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
        return notificationMessage.getText();
    }
}


