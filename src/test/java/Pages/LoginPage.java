package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
    public LoginPage (WebDriver givenDriver) { super (givenDriver); }

    @FindBy(xpath ="//input[@type='email']" )
    WebElement emailField;
    @FindBy(xpath ="//input[@type='password']" )
    WebElement passwordField;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

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
    public LoginPage clickSubmit ()
        {
            submitBtn.click();
            return this;
        }
}

