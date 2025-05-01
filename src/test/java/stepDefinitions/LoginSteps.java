package stepDefinitions;

import Pages.HomePage;
import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @Given("I open login page")
    public void iOpenLoginPage()
    {
        String url = "https://qa.koel.app/";
        driver.get(url);
    }
    @After public void closeBrowser(){driver.quit();}

    @When("I enter email {string}")
    public void iEnterEmailEmail(String email)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(email);
    }

    @And("I enter password {string}")
    public void iEnterPasswordPassword(String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.passwordInput(password);
    }

    @And("I click submit button")
    public void iClickSubmitButton()
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationSubmitBtn();
    }

    @Then("I logged in")
    public void iLoggedIn()
    {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Given("i click forgot password")
    public void iClickForgotPassword()
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.iClickForgotPassword();
    }

    @Then("I expect message {string}")
    public void iExpectMessage(String expectedMessage)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getForgotPasswordMessage(expectedMessage);
    }


}

