package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTests
{
     WebDriver driver;
     LoginPage loginPage;
     HomePage homePage;

     @BeforeMethod
     @Parameters({"baseURL"})
     public void setUp ()
     {
         driver = new ChromeDriver();
         loginPage = new LoginPage(driver);
         homePage = new HomePage(driver);
     }

     @Test
     public void login ()
     {
         loginPage.emailInput("elena.skrynnikova@testpro.io");
         loginPage.passwordInput("12345678");
         loginPage.clickSubmit();
         Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
     }
}
