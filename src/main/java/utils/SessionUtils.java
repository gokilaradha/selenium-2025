package utils;

import org.openqa.selenium.WebDriver;

import pages.HomePage;
import pages.LoginPage;

public class SessionUtils {
	public static HomePage logoutAndRelogin(WebDriver driver) throws InterruptedException {

        // Logout using HomePage object
        HomePage home = new HomePage(driver);
        home.logout();

        // Wait for login page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginPage();

        // Login again
        HomePage homePage = loginPage.loginToApp(driver);

        // Return fresh HomePage object
        return homePage;
    }

}
