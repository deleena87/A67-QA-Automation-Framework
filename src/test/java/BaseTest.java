import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public ChromeOptions options = null;

    LoginPage loginPage;
    HomePage homePage;
    BasePage basePage;
    SongsPage songsPage;
    PlaylistPage playlistPage;

    @BeforeEach
    public void setUp() {
        homePage = new HomePage(driver);
        basePage = new BasePage(driver);
        songsPage = new SongsPage(driver);
        playlistPage = new PlaylistPage(driver);
    }

    @BeforeSuite
    public void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    @Parameters({"baseURL"})
    public void launchBrowser(String baseURL) {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.emailInput("elena.skrynnikova@testpro.io")
                .passwordInput("12345678")
                .clickSubmit();
                Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() { if (driver != null) { driver.quit(); }}
    }


