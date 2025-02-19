import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
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
import java.time.Instant;
import java.util.UUID;

public class BaseTest
{
    public WebDriver driver = null;
    public ChromeOptions options = null;
    public String url = "https://qa.koel.app/";
    WebDriverWait wait = null;

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
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
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
    public void clickSubmit()
    {
        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        submitBtn.click();
    }
    public void verifyLoginSuccess()
    {
        WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='avatar']")));
        Assert.assertTrue(avatarIcon.isDisplayed());
    }
    public void waitPlusBtn ()
{
        WebElement waitPlus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='queue']")));
        waitPlus.click();
        WebElement waitPlus1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='songs']")));
        waitPlus1.click();
        WebElement waitPlus2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='albums']")));
        waitPlus2.click();
       // WebElement waitPlus3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='artists active']")));
        //waitPlus3.click();
}
    public void addNewPlaylist()
    {
        WebElement addPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@data-testid='sidebar-create-playlist-btn'][1]")));
        addPlaylist.click();
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.querySelector('#playlists').click();");
    }
    public void chooseNewPlaylist()
    {
        WebElement newPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-testid='playlist-context-menu-create-simple'][1]")));
        newPlaylist.click();
    }
    public static String generateRandomName() {
        return "Playlist_" + UUID.randomUUID().toString().substring(0, 8);
    }
    public void playlistNameInput()
    {   String PlaylistName = "NewPlaylist";
        WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='↵ to save']")));
        textField.sendKeys(PlaylistName + Keys.ENTER);
    }
    public void verifyPlaylistCreated()
    {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
        Assert.assertTrue(notification.isDisplayed());
    }

    public void clickNewPlaylist()
    {
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[starts-with(@href,'#!/playlist/')]")));
        playlist.click();
    }
    public void deletePlaylist() throws InterruptedException
    {
        WebElement deletePlaylistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Delete this playlist']")));
        deletePlaylistBtn.click();
    }
    public void confirmationMessage() throws InterruptedException
    {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='msg']")));
        Assert.assertTrue(message.isDisplayed());
    }
    public void clickOk() throws InterruptedException
    {
        WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ok']")));
        okBtn.click();
    }
    public void messageDisplayed()
    {
        String expectedMessage = "Deleted playlist \"NewPlaylist.\"";
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Deleted playlist')]")));
        String actualMessage = notification.getText();
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}