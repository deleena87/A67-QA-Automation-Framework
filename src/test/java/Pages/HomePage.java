package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
//import com.github.javafaker.Faker;
import java.util.Locale;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(xpath = "//img[@class='avatar']")
    WebElement userAvatarIcon;
    @FindBy(xpath = "//li[@class='playlist favorites']")
    WebElement playlistFavourites;
    @FindBy(xpath = "//li[@class='playlist recently-played']")
    WebElement recentlyPlayed;
    @FindBy(css = ".playlist:nth-child(3)")
    WebElement playlist1;
    @FindBy(css = "[name='name']")
    WebElement playlistInputField;
    @FindBy(xpath = "//div[@class='success show']")
    WebElement updatedPlaylistSuccessMsg;
    @FindBy(xpath = "//a[@class='queue']")
    WebElement currentQueue;
    @FindBy(xpath = "//a[@class='songs']")
    WebElement allSongs;
    @FindBy(xpath = "//a[@class='albums']")
    WebElement albums;
    @FindBy(xpath = "//a[@class='artists']")
    WebElement artists;
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn'][1]")
    WebElement addPlaylistBtn;

    public WebElement getUserAvatar() {
        return userAvatarIcon;
    }

    public HomePage doubleClickPlaylist1() {
        Actions actions = new Actions(driver);
        playlistFavourites.click();
        recentlyPlayed.click();
        actions.doubleClick(playlist1).perform();
        return this;
    }

    public HomePage renamePlaylist() {
        String newPlaylistName = "UnderTheSky";
        actions.doubleClick(playlistInputField).perform();
        playlistInputField.sendKeys(Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
        return this;
    }

    public String getUpdatedPlaylistSuccessMsg() {
        return updatedPlaylistSuccessMsg.getText();
    }

    public HomePage waitPlusBtn() {
        currentQueue.click();
        allSongs.click();
        albums.click();
        artists.click();
        return this;
    }

    public HomePage addNewPlaylistBtn() {
        addPlaylistBtn.click();
        return this;
    }


    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple'][1]")
    WebElement newPlaylist;

    public HomePage chooseNewPlaylist() {
        waitForVisibility(newPlaylist);
        newPlaylist.click();
        return this;
    }

    @FindBy(xpath = "//input[@placeholder='↵ to save']")
    WebElement textField;

    /*public String generateRandomName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().firstName();
    }

    public String generateRandomCountryName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.address().country();
    }*/

    public HomePage playlistNameInput() {
        String playlistName = "AboveSky";
        textField.sendKeys(playlistName + Keys.ENTER);
        return this;
    }

    @FindBy(xpath = "//div[@class='success show']")
    WebElement successMsg;

    public HomePage verifyPlaylistCreated () {
        String actualMessage = successMsg.getText();
        Assert.assertTrue(actualMessage.startsWith("Created playlist"));
        return this;
    }

    @FindBy(xpath = "//div[@class='success show']")
    WebElement successMsgUpdated;

    public HomePage verifyPlaylistUpdated(){
        String actualMessage = successMsgUpdated.getText();
        waitForVisibility(successMsgUpdated);
        Assert.assertTrue(actualMessage.startsWith("Updated playlist"));
        return this;
    }

    public HomePage click1stPlaylist() {
        playlist1.click();
        return this;
    }

    @FindBy(xpath = "//button[@title='Delete this playlist']")
    WebElement deletePlBtn;

    public HomePage deletePlaylist() {
        deletePlBtn.click();
        return this;
    }

    @FindBy(xpath = "//div[contains(text(),'Deleted playlist')]")
    WebElement deletedPlaylistMsg;

    public HomePage messagePlaylistDeletedDisplayed() {
        String actualMessage = deletedPlaylistMsg.getText();
        waitForVisibility(deletedPlaylistMsg);
        Assert.assertTrue(actualMessage.startsWith("Deleted playlist"));
        return this;
    }

    @FindBy(xpath = "//div[@class='side player-controls']//i[@title='Play next song']")
    WebElement playNextSong;

    public HomePage playNextSongBtn() {
        waitForElementToBeClickable(playNextSong);
        playNextSong.click();
        return this;
    }

    @FindBy(xpath = "//div[@class='side player-controls']")
    WebElement playerControls;

    public HomePage hoverSidePlayer() {
        waitForVisibility(playerControls);
        Actions actions = new Actions(driver);
        actions.moveToElement(playerControls).perform();
        Assert.assertTrue(playerControls.isDisplayed());
        return this;
    }

    @FindBy(xpath = "//div[@class='side player-controls']//span[@title='Play or resume']")
    WebElement playButton;

    public HomePage clickPlayBtn() {
        waitForElementToBeClickable(playButton);
        playButton.click();
        return this;
    }

    @FindBy(xpath = "//span[@class='pause']")
    WebElement pauseButton;

    public HomePage verifySongIsPlaying() {
        waitForVisibility(pauseButton);
        Assert.assertTrue(pauseButton.isDisplayed());
        return this;
    }

    @FindBy(xpath = "//button[normalize-space(text())='View All']")
    WebElement viewAll;

    public HomePage clickViewAllButton() {
        viewAll.click();
        return this;
    }

    public HomePage clickAllSongs() {
        allSongs.click();
        return this;
    }

    @FindBy(xpath = "//section[@id='songsWrapper']//tr[@class='song-item'][1]")
    WebElement firstSong;

    public HomePage clickSong() {
        waitForVisibility(firstSong);
        firstSong.click();
        return this;
    }

    @FindBy(xpath = "//button[@class='btn-add-to']")
    WebElement addToButton;

    public HomePage clickAddToButton() {
        addToButton.click();
        return this;
    }

    @FindBy(xpath = "//ul[contains(@data-v-2891d9df,'')]//li[contains(text(),'AboveSky')]")
    WebElement playlistFavorites;

    public HomePage choosePlaylist() {
        playlistFavorites.click();
        return this;
    }

    @FindBy(xpath = "//div[@class='success show']")
    WebElement notificationMsg;

    public HomePage verifyNotificationMessage() {
        String actualMessage = notificationMsg.getText();
        waitForVisibility(notificationMsg);
        Assert.assertTrue(actualMessage.startsWith("Added 1 song into"));
        return this;
    }

    @FindBy (xpath = "//li[@class='playback']")
    WebElement playOption;
    public HomePage clickSongContextClick (){
        waitForVisibility(firstSong);
        actions.contextClick(firstSong).perform();
        return this;
    }
    public HomePage choosePlayOption(){
        waitForVisibility(playOption);
        playOption.click();
        return this;
    }
}
