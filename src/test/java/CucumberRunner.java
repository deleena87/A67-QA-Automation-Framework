import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.BeforeClass;

@CucumberOptions
        (features = {"src/test/recources/features/"})

public class CucumberRunner extends AbstractTestNGCucumberTests
{
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass (alwaysRun = true)
    public void setUpCucumber ()
    {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
}
