public class TestDataProvider
{
    @org.testng.annotations.DataProvider(name = "ValidLoginData")
    public static Object[][] ProvideCorrectLoginData()
    {
        return new Object[][]
                {{"elena.skrynnikova@testpro.io", "12345678"}};
    }
    @org.testng.annotations.DataProvider(name = "InvalidLoginData")
    public static Object[][] provideIncorrectLoginData() {
        return new Object[][]
                {       {"invalidemail@test.io", "invalidPassword"},
                        {"invalidemail@test.io",""},
                        {"","invalid"},
                        {"",""},
                        {"elena.skrynnikova@testpro.io","invalidPassword"},
                        {"invalidemail@test.io","12345678"}
                };
    }
    public static String[] getValidLoginData() {
        return new String[] {"elena.skrynnikova@testpro.io", "12345678"};
    }
}
