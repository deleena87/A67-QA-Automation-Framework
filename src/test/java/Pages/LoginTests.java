package Pages;

import Tests.BaseTest1;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest1 {
    @Test
    public void login ()
    {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.emailInput("elena.skrynnikova@testpro.io");
        loginPage.passwordInput("12345678");
        loginPage.clickSubmit();
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
}
