package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage
{
    public LoginPage (WebDriver givenDriver) { super (givenDriver); }

    By emailField = By.xpath("//input[@type='email']");
    By passwordField = By.xpath("//input[@type='password']");
    By submitBtn = By.xpath("//button[@type='submit']");

    public void emailInput (String email) {findElement(emailField).sendKeys(email);}
    public void passwordInput (String password) {findElement(passwordField).sendKeys(password);}
    public void clickSubmit () { findElement(submitBtn).click(); }
}
