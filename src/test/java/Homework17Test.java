import org.testng.annotations.Test;

public class Homework17Test extends BaseTest
{
    @Test
    public void addSongToPlaylist() throws InterruptedException
    {
        launchBrowser();
        navigateToPage();
        provideEmail("elena.skrynnikova@testpro.io");
        providePassword("12345678");
        clickSubmit();
        verifyAvatarIconDisplayed();
        clickViewAllButton();
        clickSong();
        clickAddToButton();
        choosePlaylist();
        Thread.sleep( 2000);
        verifyNotificationMessage();
        closeBrowser();
    }
}