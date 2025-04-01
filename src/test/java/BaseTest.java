import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest
{
    LoginPage loginPage;
    HomePage homePage;
    BasePage basePage;
    SongsPage songsPage;
    PlaylistPage playlistPage;

    protected WebDriver driver = null;
    WebDriverWait wait;
    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    @BeforeMethod
    public void setUpDriver() throws MalformedURLException
    {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        threadDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(threadDriver.get(), Duration.ofSeconds(10));
        String url = "https://qa.koel.app/";
        threadDriver.get().get(url);
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public void login()
    {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        basePage = new BasePage(getDriver());
        songsPage = new SongsPage(getDriver());
        playlistPage = new PlaylistPage(getDriver());

        loginPage.emailInput("elena.skrynnikova@testpro.io")
                .passwordInput("12345678")
                .clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed(), "Login failed!");
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver()
    {
        getDriver().quit();
    }

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        String gridUrl = "http://192.168.1.157:4444";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            case "grid-chrome":
                desiredCapabilities.setBrowserName("chrome");
                driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), desiredCapabilities);
                return driver;
            case "grid-firefox":
                desiredCapabilities.setCapability("browserName", "firefox");
                driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), desiredCapabilities);
                return driver;
            case "cloud":
                driver = getLambdaDriver();
                return driver;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
        }
    }
    public WebDriver getLambdaDriver () throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "130.0");

        String hubURL = "https://hub.lambdatest.com/wd/hub";

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "elenakova.es");
        ltOptions.put("accessKey", "LT_2ZrH9olvn5PrjHhkPHUbkbu9fC1rqb0Hz9Dc7gU3kfhEDGG");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("resolution", "1024x768");
        ltOptions.put("build", "CloudTests");
        ltOptions.put("project", "Koel");
        ltOptions.put("name", "Smoke");
        ltOptions.put("console", "info");
        ltOptions.put("selenium_version", "3.8.1");
        ltOptions.put("driver_version", "130.0");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        ltOptions.put("platformName", "Windows 10");
        capabilities.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), capabilities);
    }
}



