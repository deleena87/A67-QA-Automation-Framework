//TC RegistrationNavigation Koel app
//1. Go to   https://qa.koel.app
//2. Click on "registration" button.
//3. Expected result: https://qa.koel.app/registration page is displayed.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


class Homework16 {

    @Test
    public void registrationNavigation()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        String url=("https://qa.koel.app");
        driver.get(url);
        WebElement registration = driver.findElement(By.xpath("//a[@href='registration']"));
        registration.click();

        String expectedUrl = "https://qa.koel.app/#!/home";
        String actualUrl = driver.getCurrentUrl();


        Assert.assertEquals(actualUrl, expectedUrl);
        driver.quit();


    }
}

