package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends BasePage
{
    public HomePage (WebDriver givenDriver) { super(givenDriver); }

    By userAvatarIcon = By.xpath("//img[@class='avatar']");
    By playlistFavourites = By.xpath("//li[@class='playlist favorites']");
    By recentlyPlayed = By.xpath("//li[@class='playlist recently-played']");
    By playlist1 = By.cssSelector(".playlist:nth-child(3)");

    public WebElement getUserAvatar(){ return findElement(userAvatarIcon);}

    public void doubleClickPlaylist1 ()
    {
        Actions actions = new Actions(driver);
        WebElement favourites = findElement(playlistFavourites);
        favourites.click();
        WebElement recentPlayed = findElement(recentlyPlayed);
        recentPlayed.click();
        WebElement playlist1Element = findElement(playlist1);
        actions.doubleClick(playlist1Element).perform();
    }

    By playlistInputField = By.cssSelector("[name='name']");

    public void renamePlaylist (String newPlaylistName)
    {
        WebElement inputField = findElement(playlistInputField);
        actions.doubleClick(inputField).perform();
        inputField.sendKeys(Keys.BACK_SPACE);
        inputField.sendKeys(newPlaylistName);
        inputField.sendKeys(Keys.ENTER);
    }

    By updatedPlaylistSuccessMsg = By.xpath("//div[@class='success show']");

    public String getUpdatedPlaylistSuccessMsg ()
    {
        WebElement successMsg = findElement(updatedPlaylistSuccessMsg);
        return successMsg.getText();
    }

        By currentQueue = By.xpath("//a[@class='queue']");
        By allSongs = By.xpath("//a[@class='songs']");
        By albums = By.xpath("//a[@class='albums']");
        By artists = By.xpath("//a[@class='artists']");

    public void waitPlusBtn ()
    {
        WebElement waitPlus = findElement(currentQueue);
        waitPlus.click();
        WebElement waitPlus1 = findElement(allSongs);
        waitPlus1.click();
        WebElement waitPlus2 = findElement(albums);
        waitPlus2.click();
        WebElement waitPlus3 = findElement(artists);
        waitPlus3.click();
    }

    By addPlaylistBtn = By.xpath("//i[@data-testid='sidebar-create-playlist-btn'][1]");
    public void addNewPlaylistBtn()
    {
        WebElement addPlaylist = findElement(addPlaylistBtn);
        addPlaylist.click();
    }

    By newPlaylist = By.xpath("//li[@data-testid='playlist-context-menu-create-simple'][1]");
    public void chooseNewPlaylist()
    {
        WebElement newPlaylistOption = findElement(newPlaylist);
        newPlaylistOption.click();
    }

    By textField = By.xpath("//input[@placeholder='↵ to save']");
    public void playlistNameInput()
    {
        String playlistName = "Above";
        WebElement textFieldE = findElement(textField);
        textFieldE.sendKeys(playlistName + Keys.ENTER);
    }

    By successMsg = By.xpath("//div[@class='success show']");
    public void verifyPlaylistCreated()
    {
        WebElement notificationMsg = findElement(successMsg);
        Assert.assertTrue(notificationMsg.isDisplayed());
    }

    public void clickNewPlaylist()
    {
        WebElement playlist = findElement(playlist1);
        playlist.click();
    }

    By deletePlBtn = By.xpath("//button[@title='Delete this playlist']");
    public void deletePlaylist()
    {
        WebElement deletePlaylistBtn = findElement(deletePlBtn);
        deletePlaylistBtn.click();
    }

    By deletedPlaylistMsg = By.xpath("//div[contains(text(),'Deleted playlist')]");
    public void messagePlaylistDeletedDisplayed()
    {
        WebElement notificationMsg = findElement(deletedPlaylistMsg);
        String actualMessage = notificationMsg.getText();
        Assert.assertTrue(actualMessage.startsWith("Deleted playlist"));
    }
}

