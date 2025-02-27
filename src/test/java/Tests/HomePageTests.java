package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

public class HomePageTests
{
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    @Parameters({"baseURL"})
    public void setUp ()
    {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }
    @Test
    public void login ()
    {
        loginPage.emailInput("elena.skrynnikova@testpro.io");
        loginPage.passwordInput("12345678");
        loginPage.clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Test
    public void renamePlaylist ()
    {
        homePage.doubleClickPlaylist1();
        homePage.renamePlaylist("NewPlaylist");
        String successMsg = homePage.getUpdatedPlaylistSuccessMsg();
        Assert.assertEquals(successMsg, "Updated playlist \"NewPlaylist.\"");
    }
    @Test
    public void deletePlaylist()
    {
       homePage.waitPlusBtn();
       homePage.addNewPlaylistBtn();
       homePage.chooseNewPlaylist();
       homePage.playlistNameInput();
       homePage.verifyPlaylistCreated();
       homePage.clickNewPlaylist();
       homePage.deletePlaylist();
       homePage.messagePlaylistDeletedDisplayed();
    }





}

