import org.testng.annotations.Test;

public class Homework19Test extends BaseTest
{
    @Test
    public void deleteSong() throws InterruptedException {
        launchBrowser(url);
        emailInput();
        passwordInput();
        clickSubmit();
        verifyLoginSuccess();
        clickPlaylist1();
        deletePlaylist();
        confirmationMessage();
        clickOk();
        messageDisplayed();
        closeBrowser();
    }
}
