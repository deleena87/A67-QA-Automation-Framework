import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test(dataProvider = "InvalidLoginData", dataProviderClass = TestDataProvider.class)

    public void loginIncorrectData(String email, String password) {
        loginPage.emailInput(email)
                .passwordInput(password)
                .clickSubmit();
        Assert.assertTrue(loginPage.submitBtn.isDisplayed());
    }
}
