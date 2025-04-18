import org.testng.annotations.Test;

public class ProfileTests extends TestBase
{
    @Test
    public void profileNameChange () throws InterruptedException
    {
       profilePage.clickProfileTab()
               .profileNameInput()
               .currentPasswordInput()
               .clickSaveChanges()
               .verifyProfileUpdated();
    }
    @Test
    public void changeCurrentTheme ()
    {
    profilePage.clickProfileTab()
            .clickVioletTheme()
            .isVioletThemeSelected();
    }
}
