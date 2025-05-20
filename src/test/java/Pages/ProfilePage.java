package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProfilePage extends BasePage
{ public ProfilePage (WebDriver givenDriver) {super(givenDriver);}

    @FindBy (xpath = "//a[@class='view-profile']")
    WebElement profileAvatar;
    @FindBy (css = "#inputProfileName")
    WebElement nameInputField;
    @FindBy (css = "#inputProfileCurrentPassword")
    WebElement currentPasswordField;
    @FindBy (css = ".btn-submit")
    WebElement profileSaveButton;
    @FindBy (css = "[data-testid='theme-card-violet']")
    WebElement violetTheme;
    @FindBy(css = "[data-testid='theme-card-violet'][class='theme selected']")
    WebElement selectedVioletTheme;

    public ProfilePage clickProfileTab ()
    {
        waitForVisibility(profileAvatar);
        waitForElementToBeClickable(profileAvatar);
        profileAvatar.click();
        return this;
    }
    public ProfilePage profileNameInput ()
    {
        String newProfileName = generateRandomName();
        waitForVisibility(nameInputField);
        waitForElementToBeClickable(nameInputField);
        nameInputField.click();
        nameInputField.sendKeys(Keys.BACK_SPACE);
        nameInputField.sendKeys(newProfileName + Keys.ENTER);
        return this;
    }
    public ProfilePage currentPasswordInput ()
    {
        waitForVisibility(currentPasswordField);
        waitForElementToBeClickable(currentPasswordField);
        currentPasswordField.click();
        currentPasswordField.sendKeys("12345678");
        return this;
    }
    public ProfilePage clickSaveChanges ()
    {
        waitForVisibility(profileSaveButton);
        waitForElementToBeClickable(profileSaveButton);
        profileSaveButton.click();
        return this;
    }
    public ProfilePage verifyProfileUpdated() throws InterruptedException {
        waitForVisibility(successMsg);
        waitForTextToBePresentedInElement(successMsg, "Profile updated");
        Thread.sleep(2000);
        Assert.assertTrue(getBannerText().startsWith("Profile updated"));
        return this;
    }
    public ProfilePage clickVioletTheme ()
    {
        scrollToElement(violetTheme);
        waitForVisibility(violetTheme);
        //waitForElementToBeClickable(violetTheme);
        violetTheme.click();
        return this;
    }
    public ProfilePage isVioletThemeSelected ()
    {
        waitForVisibility(selectedVioletTheme);
        Assert.assertTrue(selectedVioletTheme.isDisplayed());
        return this;
    }
}
