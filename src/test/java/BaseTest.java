import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class BaseTest
{
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    public String urlHome = "https://qa.koel.app/#!/home";

    @BeforeSuite
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

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

    public void provideEmail (String email)
    {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.clear();
        emailField.sendKeys("elena.skrynnikova@testpro.io");
    }

    public void providePassword (String password)
    {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.clear();
        passwordField.sendKeys("12345678");
    }

    public void clickSubmit()
    {
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
    }
    public void verifyHomePage()
    {
        Assert.assertEquals(driver.getCurrentUrl(),urlHome);
    }

    public void verifyAvatarIconDisplayed ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatarIcon = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//img[@class='avatar']")));

        Assert.assertTrue(avatarIcon.isDisplayed());
    }


    public void clickViewAllButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewAll = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//button[normalize-space(text())='View All']")));
        viewAll.click();
    }

    public void clickSong()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement song = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("(//td[@class='title' and normalize-space(text())='Ketsa - Beautiful'])[1]")));
        song.click();
    }

    public void clickAddToButton()
    {
        WebElement addToButton = driver.findElement(By.xpath("//button[@class='btn-add-to']"));
        addToButton.click();
    }
    public void choosePlaylist ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[contains(@class, 'playlist') and normalize-space(text())='1st playlist'])[2]")));
        firstPlaylist.click();
    }

    public void verifyNotificationMessage()
    {
        String expectedMessage = "Added 1 song into \"1st playlist.\"";
        WebElement notification = driver.findElement(By.xpath("//div[@class='success show']"));
        String actualMessage = notification.getText();
        Assert.assertEquals(actualMessage, expectedMessage,"Actual message does not match the expected message.");
    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}