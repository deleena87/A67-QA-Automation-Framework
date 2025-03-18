import org.testng.Assert;
import org.testng.annotations.Test;

public class SongsTests extends BaseTest{

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        basePage.navigateToHomePage();
        homePage.clickAllSongs();
        songsPage.clickSong()
                .clickAddToButton();
        playlistPage.choosePlaylist();
        Thread.sleep(2000);
        homePage.verifyNotificationMessage();
    }
    @Test
    public void playSongWithContextClick () throws InterruptedException {
        basePage.navigateToHomePage();
        homePage.clickAllSongs();
        songsPage.clickSongContextClick()
                .choosePlayOption();
        Thread.sleep(2000);
    }
    @Test
    public void addSongToFavorites () throws InterruptedException {
        basePage.navigateToHomePage();
        //homePage.goToFavorites()
        //.unselectAllFavorites()
        homePage.clickAllSongs();
        String title = songsPage.getTextOfSongTitle();
        songsPage.addSongToFavorite();
        homePage.goToFavorites();
        String favoriteTitle = songsPage.getTextOfFavoritesTitleSong();
        Assert.assertEquals(title,favoriteTitle);
    }
}
