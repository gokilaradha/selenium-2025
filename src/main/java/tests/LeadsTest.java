package tests;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.FileConstants;
import pages.HomePage;
import pages.LeadDetailsPage;
import pages.LeadsPage;
import pages.LoginPage;
import pages.NewLeadPage;
import utils.FileUtils;

public class LeadsTest extends BaseTest {

//@Test
public void verifyLeadsTabNavigation_TC20() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	    // Login
	    LoginPage lp = new LoginPage(driver);
	    HomePage homePage = lp.loginToApp(driver);
	    Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
	    // Click Leads tab
	    LeadsPage leadsPage = homePage.clickLeads();
	    Assert.assertTrue(leadsPage.isLeadsHomePageDisplayed());
	}

//@Test
public void leadsSelectView_TC21() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	    // Login
	    LoginPage lp = new LoginPage(driver);
	    HomePage homePage = lp.loginToApp(driver);
	    Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
	    // Click Leads tab
	    LeadsPage leadsPage = homePage.clickLeads();
	    Assert.assertTrue(leadsPage.isLeadsHomePageDisplayed());
	    List<String> expected = Arrays.asList(
	            "All Open Leads",
	            "My Unread Leads",
	            "Recently Viewed Leads",
	            "Today's Leads",
	            "View - Custom 1",
	            "View - Custom 2"
	    );
	    List<String> actual = leadsPage.getLeadsDropdownValues();
	    Assert.assertTrue(actual.containsAll(expected));
}

@Test
public void verifyLeadDefaultView_TC22() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//  Login
	LoginPage loginPage = new LoginPage(driver);
	HomePage homePage = loginPage.loginToApp(driver);
	Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
	// Click Leads tab
	LeadsPage leadsPage = homePage.clickLeads();
	Assert.assertTrue(leadsPage.isLeadsHomePageDisplayed());
	// Select Today's Leads and logout
	leadsPage.selectView("Today's Leads");
	Assert.assertEquals(leadsPage.getSelectedView(), "Today's Leads");
	homePage.logout();
	//  Login
	LoginPage loginPage1 = new LoginPage(driver);
	HomePage homePage1 = loginPage1.loginToAppLogout(driver);
	Assert.assertEquals(homePage1.getLoggedInUserName(), "Radha Abcd");
	// Click Leads tab
	LeadsPage leadsPage1 = homePage1.clickLeads();
	Assert.assertTrue(leadsPage1.isLeadsHomePageDisplayed());
	// Select Today's Leads and logout
	//leadsPage1.selectView("Today's Leads");
	//Click Go without changing the dropdown
	leadsPage1.clickGo();
	//Verify default view is still Today's Leads
	Assert.assertEquals(leadsPage1.getSelectedView(), "Today's Leads");
}

//@Test
public void verifyTodaysLeadsSelection_TC23() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//Login
	LoginPage loginPage = new LoginPage(driver);
	HomePage homePage = loginPage.loginToApp(driver);
	// Click Leads tab
	LeadsPage leadsPage = homePage.clickLeads();
	Assert.assertTrue(leadsPage.isLeadsHomePageDisplayed());
	Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
	//Select Today's Leads
	leadsPage.selectTodaysLeads();
	Assert.assertTrue(leadsPage.isTodaysLeadsSelected(), "Today's Leads is not selected");
	//Click Go button
	leadsPage.clickGo();
	// Step 5: Verify Today's Leads page is displayed
	Assert.assertTrue(leadsPage.isTodaysLeadsSelected(), "Today's Leads page is not displayed");
}
//@Test
public void verifyNewLeadCreation_TC24() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	//Login
	LoginPage loginPage = new LoginPage(driver);
	HomePage homePage = loginPage.loginToApp(driver);
    //Click Leads tab
    LeadsPage leadsPage = homePage.clickLeads();
    Assert.assertTrue(leadsPage.isLeadsHomePageDisplayed());
    //Click New button
    NewLeadPage newLeadPage = leadsPage.clickNewButton();
    //Enter Last Name
    newLeadPage.enterLastName("ABCD");
    //Enter Company Name
    newLeadPage.enterCompany("ABCD");
    //Click Save
    LeadDetailsPage detailsPage = newLeadPage.clickSave();
    Assert.assertTrue(detailsPage.isLeadDetailsDisplayed());
}
}
