package Pages;

import Tests.BaseTest1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest1 {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    @Parameters({"baseURL"})
    public void setUp (String baseURL)
    {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        driver.get(baseURL);
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
