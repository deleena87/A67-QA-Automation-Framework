import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21Test extends BaseTest
{
    @Test
    public void renamePlaylist()
    {
        String updatedPlaylistMsg = "Updated playlist \"NewPlaylist.\"";
        emailInput();
        passwordInput();
        clickSubmit();
        verifyLoginSuccess();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertEquals(getUpdatedPlaylistSuccessMsg(), updatedPlaylistMsg);
    }

}
