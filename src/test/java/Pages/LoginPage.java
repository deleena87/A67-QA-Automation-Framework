package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage
{
    public LoginPage (WebDriver givenDriver) { super (givenDriver); }

    @FindBy(xpath ="//input[@type='email']" )
    WebElement emailField;
    @FindBy(xpath ="//input[@type='password']" )
    WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitBtn;
    @FindBy(css = "a[href=\"registration\"]")
    WebElement forgotPassword;
    @FindBy(css = "div.messages")
    WebElement resetPasswordMsg;
    @FindBy(xpath = "//input[@id='button']" )
    WebElement registrationSubmitBtn;


    public LoginPage emailInput (String email)
        { emailField.click();
            emailField.sendKeys(email);
            return this;
        }
    public LoginPage passwordInput (String password)
        { passwordField.click();
            passwordField.sendKeys(password);
            return this;
        }
    public void clickSubmit ()
        {
            submitBtn.click();
        }
        public void clickRegistrationSubmitBtn()
        {
            registrationSubmitBtn.click();
        }
    public void iClickForgotPassword()
    {
        forgotPassword.click();
    }
    public void getForgotPasswordMessage (String expectedMessage)
    {
        waitForVisibility(resetPasswordMsg);
        String actualMessage = resetPasswordMsg.getText().trim();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}

