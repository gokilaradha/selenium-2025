package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import constants.FileConstants;
import pages.LoginPage;
import pages.MySettingsPage;
import pages.UserMenu;
import utils.FileUtils;
public class MySettingsTest extends BaseTest {

@Test
public void mySettings_TC07() throws InterruptedException {
    WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	UserMenu um = new UserMenu(driver);
	String[] expectedUserMenuOptions = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "user.menu.options").split(",");
	String[] actualUserMenuOptions = um.getUserMenuOptions().toArray(new String[0]);
	Assert.assertEquals(expectedUserMenuOptions, actualUserMenuOptions,"User menu options mismatch");
	MySettingsPage settingsPage = um.selectMySettings();
	Assert.assertTrue(settingsPage.isSettingsPageLoaded(), "My Settings page should be loaded");
	settingsPage.openLoginHistory();
	Assert.assertTrue(settingsPage.isLoginHistoryPageLoaded());
	Assert.assertTrue(settingsPage.isDownloadLinkVisible());
	Assert.assertTrue(settingsPage.waitForLoginHistoryDownload(),"CSV file should be downloaded");
    Assert.assertTrue(settingsPage.addReportsTab(),"Reports tab should be added to Selected Tabs list");
    settingsPage.setEmailPreferences("Radha Abcd", "larasiva24@gmail.com");
    settingsPage.storeMainWindow();
    settingsPage.testCalendarReminder();
    Assert.assertTrue(settingsPage.verifyReminderPopupVisible(),"Sample event Popup Window should be displayed.");
    settingsPage.closeChildWindowsAndReturn();

}

@AfterMethod
public void tearDown() {
    if (driver != null) {
       driver.quit();
    }
    }
}