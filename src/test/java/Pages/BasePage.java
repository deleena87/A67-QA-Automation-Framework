package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Locale;

public class BasePage {
        WebDriver driver;
        WebDriverWait wait;
        Actions actions;
    public BasePage (WebDriver givenDriver) {
        driver = givenDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='success show']")
    WebElement successMsg;

    public BasePage verifyNotificationMessage() throws InterruptedException {
        waitForVisibility(successMsg);
        waitForTextToBePresentedInElement(successMsg, "Added 1 song into");
        Thread.sleep(2000);
        String actualMessage = successMsg.getText().trim();
        Assert.assertTrue(actualMessage.startsWith("Added 1 song into"));
        return this;
    }
    public String getBannerText() {
        return successMsg.getText().trim();
    }
    public boolean isBannerDisplayed(){
        waitForVisibility(successMsg);
        return successMsg.isDisplayed();
    }
    public String generateRandomName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.name().firstName();
    }
    public String generateRandomCountryName() {
        Faker faker = new Faker(new Locale("en-US"));
        return faker.address().country();
    }
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void navigateToHomePage() {
        driver.get("https://qa.koel.app/#!/home");
    }
    public void waitForTextToBePresentedInElement(WebElement element, String text){
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }
}
