import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    @BeforeSuite
    static void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    public void launchBrowser()
        {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        }
    public void navigateToPage()
    {
       driver.get(url);
    }
    public void emailInput()
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
    public void verifyAvatarIconIsDisplayed()
    {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }
    public void playNextSongBtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement playNextSong = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='side player-controls']//i[@title='Play next song']")));
        Thread.sleep(2000);
        playNextSong.click();
    }
    public void hoverSidePlayer() throws InterruptedException {
        WebElement hoverPlayerControls = driver.findElement(By.xpath("//div[@class='side player-controls']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverPlayerControls).perform();
        Thread.sleep(2000);

        Assert.assertTrue(hoverPlayerControls.isDisplayed());
    }
    public void clickPlayBtn()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='side player-controls']//span[@title='Play or resume']")));
        playButton.click();
    }
    public void verifySongIsPlaying()
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement pauseBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='pause']")));
        Assert.assertTrue(pauseBtn.isDisplayed());
    }
    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}