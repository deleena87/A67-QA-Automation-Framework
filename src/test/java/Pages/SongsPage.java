package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SongsPage extends BasePage{
    public SongsPage (WebDriver givenDriver) {super(givenDriver);}

    @FindBy(xpath = "//section[@id='songsWrapper']//tr[@class='song-item'][1]")
    WebElement firstSong;
    @FindBy(xpath = "//button[@class='btn-add-to']")
    WebElement addToButton;
    @FindBy (xpath = "//li[@class='playback']")
    WebElement playOption;
    @FindBy (css = ".items [draggable='true'] .fa-heart-o:nth-of-type(1)")
    WebElement unlikedSongBtn;
    @FindBy (xpath = "//td[@class='title' and text()='M33 Project - Emotional Soundtrack']")
    WebElement songTitle;
    @FindBy (css = ".favorites .virtual-scroller .title")
    WebElement favoriteSongTitle;
    @FindBy(css = ".favorites [draggable='true'] .text-maroon")
    List<WebElement> likedSongs;

    public SongsPage clickSong() {
        waitForVisibility(firstSong);
        waitForElementToBeClickable(firstSong);
        firstSong.click();
        return this;
    }
    public SongsPage clickAddToButton() {
        waitForVisibility(addToButton);
        waitForElementToBeClickable(addToButton);
        addToButton.click();
        return this;
    }
    public SongsPage clickSongContextClick (){
        waitForVisibility(firstSong);
        actions.contextClick(firstSong).perform();
        return this;
    }
    public SongsPage choosePlayOption(){
        waitForVisibility(playOption);
        playOption.click();
        return this;
    }
    public SongsPage addSongToFavorite (){
        waitForVisibility(unlikedSongBtn);
        waitForTextToBePresentedInElement(unlikedSongBtn, "");
        waitForElementToBeClickable(unlikedSongBtn);
        unlikedSongBtn.click();
        return this;
    }
    public String getTextOfSongTitle (){
        waitForVisibility(songTitle);
        return songTitle.getText();
    }
    public String getTextOfFavoritesTitleSong (){
        waitForVisibility(favoriteSongTitle);
        return favoriteSongTitle.getText();
    }
    public SongsPage unselectAllFavorites() throws InterruptedException {
        for (WebElement song : likedSongs) {
            song.click();
            Thread.sleep(500);
        }
        return this;
    }
}
