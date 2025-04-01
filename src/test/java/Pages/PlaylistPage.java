package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PlaylistPage extends BasePage {
    public PlaylistPage (WebDriver givenDriver) {super(givenDriver);}

    @FindBy(xpath = "//li[@class='playlist favorites']")
    WebElement playlistFavourites;
    @FindBy(xpath = "//li[@class='playlist recently-played']")
    WebElement recentlyPlayed;
    @FindBy(css = ".playlist:nth-child(3)")
    WebElement playlist1;
    @FindBy(css = "[name='name']")
    WebElement playlistInputField;
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn'][1]")
    WebElement addPlaylistBtn;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple']")
    WebElement newPlaylistOption;
    @FindBy(xpath = "//input[@name='name']")
    WebElement textField;
    @FindBy(xpath = "//button[@title='Delete this playlist']")
    WebElement deletePlBtn;
    @FindBy(xpath = "//div[contains(text(),'Deleted playlist')]")
    WebElement deletedPlaylistMsg;
    @FindBy(xpath = "//li[@tabindex='0' and @class='playlist']")
    WebElement playlist;

    public PlaylistPage doubleClickPlaylist1() {
        playlistFavourites.click();
        recentlyPlayed.click();
        actions.doubleClick(playlist1).perform();
        return this;
    }
    public PlaylistPage renamePlaylist() {
        String newPlaylistName = generateRandomName();
        actions.doubleClick(playlistInputField).perform();
        playlistInputField.sendKeys(Keys.BACK_SPACE);
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
        return this;
    }
    public PlaylistPage addNewPlaylistBtn() {
        waitForVisibility(addPlaylistBtn);
        waitForElementToBeClickable(addPlaylistBtn);
        addPlaylistBtn.click();
        return this;
    }
    public PlaylistPage chooseNewPlaylist() {
        waitForVisibility(newPlaylistOption);
        waitForElementToBeClickable(newPlaylistOption);
        newPlaylistOption.click();
        return this;
    }
    public PlaylistPage playlistNameInput() throws InterruptedException {
        String playlistName = generateRandomName();
        waitForVisibility(textField);
        waitForElementToBeClickable(textField);
        textField.click();
        Thread.sleep(500);
        textField.sendKeys(playlistName + Keys.ENTER);
        return this;
    }
    public PlaylistPage verifyPlaylistCreated () {
        Assert.assertTrue(getBannerText().startsWith("Created playlist"));
        return this;
    }
    public PlaylistPage verifyPlaylistUpdated() throws InterruptedException {
        waitForVisibility(successMsg);
        waitForTextToBePresentedInElement(successMsg, "Updated playlist");
        Thread.sleep(2000);
        System.out.println("Actual Message: " + getBannerText());
        Assert.assertTrue(getBannerText().startsWith("Updated playlist"));
        return this;
    }
    public PlaylistPage click1stPlaylist() {
        playlist1.click();
        return this;
    }
    public PlaylistPage deletePlaylist() {
        deletePlBtn.click();
        return this;
    }
    public PlaylistPage messagePlaylistDeletedDisplayed() {
        String actualMessage = deletedPlaylistMsg.getText();
        waitForVisibility(deletedPlaylistMsg);
        Assert.assertTrue(actualMessage.startsWith("Deleted playlist"));
        return this;
    }
    public PlaylistPage choosePlaylist() {
        waitForVisibility(playlist);
        waitForElementToBeClickable(playlist);
        playlist.click();
        return this;
    }
}
