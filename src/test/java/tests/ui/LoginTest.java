package tests.ui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {
	private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.enterUsername("john");
        loginPage.enterPassword("demo");
        loginPage.clickLogin();

        Assert.assertFalse(loginPage.isErrorDisplayed(), "Login error displayed on valid credentials");
        Assert.assertTrue(driver.getCurrentUrl().contains("overview.htm"), "Not navigated to overview page");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
