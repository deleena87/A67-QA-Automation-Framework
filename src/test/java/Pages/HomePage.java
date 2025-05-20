package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    @FindBy(xpath = "//img[@class='avatar']")
    WebElement userAvatarIcon;
    @FindBy(xpath = "//a[@class='queue']")
    WebElement currentQueue;
    @FindBy(xpath = "//a[@class='songs']")
    WebElement allSongs;
    @FindBy(xpath = "//a[@class='albums']")
    WebElement albums;
    @FindBy(xpath = "//a[@class='artists']")
    WebElement artists;
    @FindBy(xpath = "//div[@class='side player-controls']//i[@title='Play next song']")
    WebElement playNextSong;
    @FindBy(xpath = "//div[@class='side player-controls']")
    WebElement playerControls;
    @FindBy(xpath = "//div[@class='side player-controls']//span[@title='Play or resume']")
    WebElement playButton;
    @FindBy(xpath = "//span[@class='pause']")
    WebElement pauseButton;
    @FindBy (xpath = "//a[@href='#!/favorites']\n")
    WebElement playlistFavorites;
    @FindBy(xpath = "//button[normalize-space(text())='View All']")
    WebElement viewAll;

    public WebElement getUserAvatar()
    {
        waitForVisibility(userAvatarIcon);
        return userAvatarIcon;
    }

    public HomePage waitPlusBtn() {
        currentQueue.click();
        allSongs.click();
        albums.click();
        artists.click();
        return this;
    }
    public HomePage playNextSongBtn() {
        waitForElementToBeClickable(playNextSong);
        playNextSong.click();
        return this;
    }
    public HomePage hoverSidePlayer() {
        waitForVisibility(playerControls);
        Actions actions = new Actions(driver);
        actions.moveToElement(playerControls).perform();
        Assert.assertTrue(playerControls.isDisplayed());
        return this;
    }
    public HomePage clickPlayBtn() {
        waitForElementToBeClickable(playButton);
        playButton.click();
        return this;
    }
    public HomePage verifySongIsPlaying() {
        waitForVisibility(pauseButton);
        Assert.assertTrue(pauseButton.isDisplayed());
        return this;
    }
    public HomePage clickViewAllButton() {
        viewAll.click();
        return this;
    }
    public HomePage clickAllSongs() {
        allSongs.click();
        return this;
    }
    public HomePage goToFavorites (){
    waitForVisibility(playlistFavorites);
    waitForElementToBeClickable(playlistFavorites);
    playlistFavorites.click();
    return this;
    }
}
