package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.FileConstants;
import pages.AllTabsPage;
import pages.CalendarPage;
import pages.EditProfilePopup;
import pages.HomePage;
import pages.LoginPage;
import pages.NewEventPage;
import pages.UserProfilePage;
import utils.FileUtils;

public class RandomScenariosTest extends BaseTest {
//@Test
public void verifyUserProfileNavigation_TC33() throws Exception {
    WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//Login
	LoginPage lp = new LoginPage(driver);
	HomePage home = lp.loginToApp(driver);
    //Click Home tab
    home.clickHomeTab();
    Assert.assertTrue(home.isHomePageDisplayedForHomeTab(), "Home page is not displayed");
    //Verify username link is displayed
    String expectedUserName = "Radha Abcd";
    Assert.assertEquals(home.getDisplayedUserName(), expectedUserName);
    //Click username link
    UserProfilePage profile = home.clickUserNameLink();
    // Verify profile page
    Assert.assertTrue(profile.isProfilePageDisplayed(), "Profile page is not displayed");
    Assert.assertTrue(profile.getProfileHeaderText().contains(expectedUserName),
	            "Profile header does not match username");
}

//@Test
public void verifyEditProfileUpdatesLastName_TC34() throws Exception {
    WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//Login
	LoginPage lp = new LoginPage(driver);
	HomePage home = lp.loginToApp(driver);
    //Click Home tab
    home.clickHomeTab();
    Assert.assertTrue(home.isUserNameLinkDisplayed(), "Username link not displayed");
    //Click username link
    UserProfilePage profile = home.clickUserNameLink();
    Assert.assertTrue(profile.isProfileHeaderDisplayed(), "Profile header not displayed");
    //Click Edit Profile icon
    EditProfilePopup popup = profile.clickEditProfileIcon();
    Assert.assertTrue(popup.verifyPopupWithContactTab(), "Edit Profile popup not loaded");
    //Click About tab + verify focus in First Name
    Assert.assertTrue(popup.clickAboutAndVerifyFocus(),
	            "Focus should be in First Name field");
    //Update Last Name
    popup.updateLastName("Abcd");
    //Verify popup closed
    popup.waitForPopupToClose();
    //Verify updated name (top-left)
    home.waitForUpdatedUserName("Abcd");
    Assert.assertEquals(home.getTopLeftUserName(), "Radha Abcd");
    //Verify top-right username
    Assert.assertEquals(home.getTopRightUserName(), "Radha Abcd");
    // RE-INITIALIZE PROFILE PAGE 
    profile = new UserProfilePage(driver);
    // Step 4: Verify profile header
    Assert.assertTrue(profile.getProfileHeaderText().contains("Radha Abcd"));
}
//@Test
public void removeTabFromCustomizeTabs_TC35() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//Login
	LoginPage lp = new LoginPage(driver);
	HomePage home = lp.loginToApp(driver);
	System.out.println("Home Page is displayed");
	Assert.assertEquals(home.getLoggedInUserName() , "Radha Abcd");
	home.clickAllTabs();
	AllTabsPage allTabs = new AllTabsPage(driver);
	Assert.assertTrue(allTabs.isAllTabsPageDisplayed(),"All Tabs Page is not displayed");
	//Customize My Tabs
	allTabs.clickCustomizeMyTabs();
	Assert.assertTrue(allTabs.isCutomizeMyTabsPageDisplayed(),"Customize My Tabs Page is not displayed");
	String removedTab = allTabs.removeAnyTab();
	Assert.assertFalse(home.isTabPresentInTabBar(removedTab),
	            "Removed tab should not appear in tab bar");
	//Logout
	home.logout();
	LoginPage loginPage1 = new LoginPage(driver);
	//loginPage1.waitForLoginPageToVisible();
	HomePage homePage = loginPage1.loginToAppLogout(driver);
    Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
    Assert.assertFalse(homePage.isTabPresentInTabBar(removedTab),
	            "Removed tab should still not appear after re-login");
}
@Test
public void createNewEventFromHomeCalendar_TC36() throws Exception {
	WebDriver driver = BaseTest.getDriver("chrome", false);
	driver.get("https://login.salesforce.com");
    LoginPage login = new LoginPage(driver);
    HomePage home = login.loginToApp(driver);
    //Home Tab
    home.clickHomeTab();
    Assert.assertTrue(home.isHomePageDisplayedWithCurrentDate(), "Home page is not displayed");
    //Verify Current Date Format
	String dateText = home.getCurrentDateText();
	System.out.println("Current date: " + dateText);
    Assert.assertTrue(
       dateText.matches("^[A-Za-z]+\\s+[A-Za-z]+\\s+\\d{1,2},\\s+\\d{4}$"),
      "Current date is NOT in expected format: Day Month Date, Year"
    );
    CalendarPage calendar = home.clickCurrentDate();
    Assert.assertTrue(calendar.isCalendarPageDisplayed(), "Calendar page is not displayed");
    //Click 8:00 PM
	NewEventPage eventPage = calendar.clickEightPm();
	Assert.assertTrue(eventPage.isNewEventPageDisplayed(),"New Event Page is not Displayed");
	Assert.assertTrue(eventPage.isCursorInSubjectField(), "Cursor is not in Subject field");
	String main = driver.getWindowHandle();
	//Open Subject Combo popup
	String popup = eventPage.openSubjectPopup();
    //Select "Other" inside popup
	eventPage.selectOtherInPopup();
    //Switch back to main window
    eventPage.switchToMainWindow(main);
    //Verify popup is closed
	Assert.assertTrue(eventPage.isPopupClosed(popup),
	            "Combo popup did not close");
    
	Assert.assertEquals(eventPage.getSubjectFieldValue(), "Other", "Subject field does not contain 'Other'");
    //End Time
    eventPage.clickEndTime();
    eventPage.selectEndTime("9:00 PM");
    Assert.assertEquals(eventPage.getEndTimeValue(), "9:00 PM", "End time is not 9:00 PM");
    //Save the event
	eventPage.clickSave1();
	String actualTitle = calendar.getCalendarTitle();
	Assert.assertTrue(actualTitle.contains("Calendar for Radha"),
	        "Calendar title mismatch. Actual title: " + actualTitle);
	Assert.assertTrue(calendar.isOtherEventPresentAt8PM(), 
	        "The 'Other' event is not displayed in the 8:00 PM slot");
}
}

