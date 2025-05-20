package tests;

import base.TestBase;
import org.testng.annotations.Test;

public class ProfileTests extends TestBase
{
    @Test
    public void profileNameChange () throws InterruptedException
    {
        login();
       profilePage.clickProfileTab()
               .profileNameInput()
               .currentPasswordInput()
               .clickSaveChanges()
               .verifyProfileUpdated();
    }
    @Test
    public void changeCurrentTheme ()
    {
        login();
        profilePage.clickProfileTab()
                .clickVioletTheme()
            .isVioletThemeSelected();
    }
}
