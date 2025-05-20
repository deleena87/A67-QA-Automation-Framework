package stepDefinitions;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBase
{
    @Before
    public void setUpScenario()
    {
        setUpDriver ("https://qa.koel.app/");
    }
    @After
    public void driverQuit()
    {
        if (driver != null) {
            driver.quit();
        }
    }
}
