import Pages.HomePage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public ChromeOptions options = null;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeSuite
    public void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    @Parameters({"baseURL"})
    public void launchBrowser(String baseURL) throws MalformedURLException {
        driver = pickBrowser(System.getProperty("browser"));
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
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
    @AfterMethod
    public void tearDown() { if (driver != null) { driver.quit(); }}

    public WebDriver pickBrowser (String browser) throws MalformedURLException {
        String gridURL = "http://192.168.1.158:4444/ui/";

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                return driver;
            case "explorer":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                driver = new InternetExplorerDriver(ieOptions);
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            case "grid-chrome":
                ChromeOptions gridChromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(URI.create(gridURL).toURL(),gridChromeOptions);
                return driver;
            case "grid-explorer":
                InternetExplorerOptions gridIeOptions = new InternetExplorerOptions();
                gridIeOptions.ignoreZoomSettings();
                gridIeOptions.introduceFlakinessByIgnoringSecurityDomains();
                driver = new RemoteWebDriver(URI.create(gridURL).toURL(), gridIeOptions);
                return driver;
            default:
                WebDriverManager.edgedriver().setup();
                driver = new SafariDriver();
                return driver;
        }

    }



}
