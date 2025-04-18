import org.testng.annotations.Test;

public class
HomeTests extends TestBase {

@Test
public void renamePlaylist() throws InterruptedException {
    homePage.waitPlusBtn();
            playlistPage.addNewPlaylistBtn()
            .chooseNewPlaylist()
            .playlistNameInput()
            .verifyPlaylistCreated();
            homePage.waitPlusBtn();
            playlistPage.doubleClickPlaylist1()
            .renamePlaylist();
    Thread.sleep(1000);
            playlistPage.verifyPlaylistUpdated();
}
    @Test
    public void deletePlaylist() throws InterruptedException {
        homePage.waitPlusBtn();
                playlistPage.addNewPlaylistBtn()
                .chooseNewPlaylist()
                .playlistNameInput()
                .verifyPlaylistCreated()
                .click1stPlaylist()
                .deletePlaylist();
        Thread.sleep(2000);
        playlistPage.messagePlaylistDeletedDisplayed();
    }
    @Test
    public void playSongWithPlayBtn(){
        homePage.hoverSidePlayer()
                .playNextSongBtn()
                .clickPlayBtn()
                .verifySongIsPlaying();
    }


}
