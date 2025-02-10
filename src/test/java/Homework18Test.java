import org.testng.annotations.Test;

public class Homework18Test extends BaseTest
{
    @Test
    public void playSong() throws InterruptedException
    {
        launchBrowser();
        navigateToPage();
        emailInput();
        passwordInput();
        Thread.sleep(2000);
        clickSubmit();
        verifyAvatarIconIsDisplayed();
        hoverSidePlayer();
        playNextSongBtn();
        clickPlayBtn();
        verifySongIsPlaying();
        closeBrowser();
    }
}