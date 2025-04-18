import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestBase
{
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public ChromeOptions options = null;

    LoginPage loginPage;
    HomePage homePage;
    BasePage basePage;
    SongsPage songsPage;
    PlaylistPage playlistPage;
    ProfilePage profilePage;

    @BeforeSuite
    public void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    @Parameters({"baseURL"})

    public void setUp(String baseURL)
    {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);

        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        songsPage = new SongsPage(driver);
        playlistPage = new PlaylistPage(driver);
        profilePage = new ProfilePage(driver);

        String[] validCredentials = TestDataProvider.getValidLoginData();
        String email = validCredentials[0];
        String password = validCredentials[1];

        loginPage.emailInput(email)
                .passwordInput(password)
                .clickSubmit();
                Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
}
    @AfterMethod(alwaysRun = true)
    public void tearDown() { if (driver != null) { driver.quit(); }}
    }


