package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage
{
    public HomePage (WebDriver givenDriver) { super(givenDriver); }

    @FindBy (xpath = "//img[@class='avatar']")
    WebElement userAvatarIcon;
    @FindBy (xpath = "//li[@class='playlist favorites']")
    WebElement playlistFavourites;
    @FindBy (xpath = "//li[@class='playlist recently-played']")
    WebElement recentlyPlayed;
    @FindBy (css = ".playlist:nth-child(3)")
    WebElement playlist1;
    @FindBy (css ="[name='name']")
    WebElement playlistInputField;
    @FindBy (xpath = "//div[@class='success show']")
    WebElement updatedPlaylistSuccessMsg;
    @FindBy (xpath = "//a[@class='queue']")
    WebElement currentQueue;
    @FindBy (xpath = "//a[@class='songs']")
    WebElement allSongs;
    @FindBy (xpath = "//a[@class='albums']")
    WebElement albums;
    @FindBy (xpath = "//a[@class='artists']")
    WebElement artists;
    @FindBy (xpath = "//i[@data-testid='sidebar-create-playlist-btn'][1]")
    WebElement addPlaylistBtn;

    public WebElement getUserAvatar () { return userAvatarIcon; }
    public void doubleClickPlaylist1(){
        Actions actions = new Actions(driver);
        playlistFavourites.click();
        recentlyPlayed.click();
        actions.doubleClick(playlist1).perform();
    }
    public void renamePlaylist (String newPlaylistName) {
        actions.doubleClick(playlistInputField).perform();
        playlistInputField.sendKeys(Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getUpdatedPlaylistSuccessMsg () {
        return updatedPlaylistSuccessMsg.getText();
    }
    public void waitPlusBtn () {
        currentQueue.click();
        allSongs.click();
        albums.click();
        artists.click();
    }
    public void addNewPlaylistBtn() {
        addPlaylistBtn.click();
    }


    @FindBy (xpath = "//li[@data-testid='playlist-context-menu-create-simple'][1]")
    WebElement newPlaylist;
    public void chooseNewPlaylist() {
        newPlaylist.click();
    }

    @FindBy (xpath = "//input[@placeholder='↵ to save']")
    WebElement textField;
    public void playlistNameInput() {
        String playlistName = "Above";
        textField.sendKeys(playlistName + Keys.ENTER);
    }

    @FindBy (xpath = "//div[@class='success show']")
    WebElement successMsg;
    public void verifyPlaylistCreated() {
        Assert.assertTrue(successMsg.isDisplayed());
    }

    public void clickNewPlaylist() {
        playlist1.click();
    }

    @FindBy (xpath = "//button[@title='Delete this playlist']")
    WebElement deletePlBtn;
    public void deletePlaylist() {
        deletePlBtn.click();
    }

    @FindBy (xpath = "//div[contains(text(),'Deleted playlist')]")
    WebElement deletedPlaylistMsg;
    public void messagePlaylistDeletedDisplayed() {
        String actualMessage = deletedPlaylistMsg.getText();
        Assert.assertTrue(actualMessage.startsWith("Deleted playlist"));
    }


}
