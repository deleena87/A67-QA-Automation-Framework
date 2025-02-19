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
        waitPlusBtn();
        addNewPlaylist();
        chooseNewPlaylist();
        playlistNameInput();
        verifyPlaylistCreated();
        clickNewPlaylist();
        deletePlaylist();
        //confirmationMessage();
        //clickOk();
        messageDisplayed();
        closeBrowser();
    }
}
