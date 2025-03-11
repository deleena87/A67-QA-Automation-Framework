import Pages.BasePage;
import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest {
    HomePage homePage;
    BasePage basePage;

@Test
public void renamePlaylist() throws InterruptedException {
    homePage = new HomePage(driver);

    homePage.waitPlusBtn()
            .addNewPlaylistBtn()
            .chooseNewPlaylist()
            .playlistNameInput();
    Thread.sleep(2000);
            homePage.verifyPlaylistCreated()
            .waitPlusBtn()
            .doubleClickPlaylist1()
            .renamePlaylist();
    Thread.sleep(2000);
            homePage.verifyPlaylistUpdated();
}
    @Test
    public void deletePlaylist() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.waitPlusBtn()
                .addNewPlaylistBtn()
                .chooseNewPlaylist()
                .playlistNameInput()
                .verifyPlaylistCreated()
                .click1stPlaylist()
                .deletePlaylist();
        Thread.sleep(2000);
        homePage.messagePlaylistDeletedDisplayed();
    }
    @Test
    public void playSongWithPlayBtn(){
        homePage = new HomePage(driver);
        homePage.hoverSidePlayer()
                .playNextSongBtn()
                .clickPlayBtn()
                .verifySongIsPlaying();
    }
    @Test
    public void addSongToPlaylist() throws InterruptedException {
        homePage = new HomePage(driver);
        basePage = new BasePage(driver);
        basePage.navigateToHomePage();
        homePage.clickAllSongs()
                .clickSong()
                .clickAddToButton()
                .choosePlaylist();
        Thread.sleep(2000);
                homePage.verifyNotificationMessage();
    }
    @Test
    public void playSongWithContextClick () throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.clickAllSongs()
                .clickSongContextClick()
                .choosePlayOption();
        Thread.sleep(2000);
    }

}
