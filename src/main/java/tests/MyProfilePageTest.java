package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.UserMenu;
import utils.FileUtils;

public class MyProfilePageTest extends BaseTest{
	
//@Test
public void verifyUserMenuDropdown_TC05() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	UserMenu um = new UserMenu(driver);
	String[] expectedUserMenuOptions = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "user.menu.options").split(",");
	String[] actualUserMenuOptions = um.getUserMenuOptions().toArray(new String[0]);
	Assert.assertEquals(expectedUserMenuOptions, actualUserMenuOptions);
}
//@Test
public void myProfile_TC06() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//driver.get(BaseTest.loginURLOauth);
	LoginPage lp = new LoginPage(driver);//once start with Oauth comment out this
	lp.loginToApp(driver);  //this too
	UserMenu um = new UserMenu(driver);
	String[] expectedUserMenuOptions = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "user.menu.options").split(",");
	String[] actualUserMenuOptions = um.getUserMenuOptions().toArray(new String[0]);
	Assert.assertEquals(expectedUserMenuOptions, actualUserMenuOptions);
	MyProfilePage profile = um.selectMyProfile(driver);
	Assert.assertTrue(profile.isProfilePageLoaded(driver), "Profile page should be loaded");
	profile.waitAndClickOnEditIcon(driver);
	Assert.assertTrue(profile.verifyEditProfilePopUpIsVisible(driver), "Edit profile pop up should be visible");
	Assert.assertTrue(profile.verifyChangeLastNameInAboutTab(driver, "Abcd"), "Last name  should be updated");
	String postMessage = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "post.message");
	Assert.assertTrue(profile.verifyCreatePost(driver, postMessage),"Post should be created");
	Assert.assertTrue(profile.verifyFileUpload(driver,FileConstants.TEST_DATA_FILE_TO_UPLOAD),"File should be uploaded successfully");
	Assert.assertTrue(profile.verifyPhotoUpload(driver, FileConstants.PHOTO_UPLOAD_PATH), "Photo should be uploaded successfully");
	driver.close();
}
//@Test
public void developerConsole_TC08() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	UserMenu um = new UserMenu(driver);
	String[] expectedUserMenuOptions = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "user.menu.options").split(",");
	String[] actualUserMenuOptions = um.getUserMenuOptions().toArray(new String[0]);
	Assert.assertEquals(expectedUserMenuOptions, actualUserMenuOptions);
	um.selectDeveloperConsole();
	Assert.assertTrue(um.isDeveloperConsolePageLoaded(), "Developer Console Window did not load correctly");
}
	
@Test
public void logout_TC09() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	UserMenu um = new UserMenu(driver);
	String[] expectedUserMenuOptions = FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "user.menu.options").split(",");
	String[] actualUserMenuOptions = um.getUserMenuOptions().toArray(new String[0]);
	Assert.assertEquals(expectedUserMenuOptions, actualUserMenuOptions);
	um.logout();
	Assert.assertTrue(um.isLoginPageLoadedFromLogout(),"Logout did not work properly");
}
}
