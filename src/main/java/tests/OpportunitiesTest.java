package tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.FileConstants;
import pages.AccountsHomePage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OpportunitiesPage;
import pages.OpportunityPipelinePage;
import pages.QuarterlySummaryPage;
import pages.StuckOpportunitiesPage;
import utils.FileUtils;

public class OpportunitiesTest extends BaseTest {
	
//@Test
public void opportunitiesPage_TC15() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	HomePage homePage =lp.loginToApp(driver);
	Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
	Assert.assertTrue(homePage.isOpportunitiesLinkDisplayed());
    //Click Opportunities
    OpportunitiesPage oppPage = homePage.clickOpportunities();
    Assert.assertTrue(oppPage.isOpportunitiesHomeDisplayed());
    //Verify dropdown values
    List<String> expected = Arrays.asList(
            "All Opportunities",
            "Closing Next Month",
            "Closing This Month",
            "My Opportunities",
            "New This Week",
            "Recently Viewed Opportunities",
            "Won"
    );
    List<String> actual = oppPage.getOpportunitiesDropdownValues();

    Assert.assertTrue(actual.containsAll(expected),
            "Dropdown does not contain all expected values");
}
	
@Test
public void createNewOpportunity_TC16() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	// Login
	LoginPage lp = new LoginPage(driver);
	HomePage homePage = lp.loginToApp(driver);
	Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
	AccountsHomePage accountsHome = new AccountsHomePage(driver);
	//Get recent account name
	String accountName = accountsHome.getAccountNameForOpportunity();
	OpportunitiesPage oppPage = homePage.clickOpportunities();
	Assert.assertTrue(oppPage.isOpportunitiesHomeDisplayed());
	oppPage.clickNewButton();
	oppPage.iseditOpportunitiesDisplayed();
	//Create new opportunity
	oppPage.createNewOpportunity(
	            "Test Opportunity",
	            accountName,
	            "01/15/2026",
	            "Prospecting",
	            "10",
	            "Web"
	 );
	 //Verify Opportunity Detail page
	 Assert.assertTrue(oppPage.isOpportunityDetailDisplayed());
}
	
//@Test
public void testOpportunityPipelineReport_TC17() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    // Login
	LoginPage lp = new LoginPage(driver);
	HomePage homePage = lp.loginToApp(driver);
    Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
    //Click Opportunities
	OpportunitiesPage oppPage = homePage.clickOpportunities();
	Assert.assertTrue(oppPage.isOpportunitiesHomeDisplayed());
    //Click Opportunity Pipeline
	OpportunityPipelinePage pipelinePage = oppPage.clickOpportunityPipeline();
	Assert.assertTrue(pipelinePage.isPipelineReportDisplayed());
}
	
//@Test
public void testStuckOpportunitiesReport_TC18() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    // Login
    LoginPage lp = new LoginPage(driver);
    HomePage homePage = lp.loginToApp(driver);
    Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
    //Click Opportunities
    OpportunitiesPage oppPage = homePage.clickOpportunities();
    Assert.assertTrue(oppPage.isOpportunitiesHomeDisplayed());
    //Click Stuck Opportunities
	StuckOpportunitiesPage stuckPage = oppPage.clickStuckOpportunities();
	Assert.assertTrue(stuckPage.isStuckOpportunitiesReportDisplayed());
}
	
//@Test
public void verifyQuarterlySummaryReport_TC19() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    //Login
    LoginPage lp = new LoginPage(driver);
    HomePage homePage = lp.loginToApp(driver);
    Assert.assertEquals(homePage.getLoggedInUserName(), "Radha Abcd");
    //Click Opportunities
	OpportunitiesPage oppPage = homePage.clickOpportunities();
	Assert.assertTrue(oppPage.isOpportunitiesHomeDisplayed()); 
	//Click Quarterly Summary
	QuarterlySummaryPage qsPage = oppPage.clickQuarterlySummary();
    //Select Interval and Include values
    qsPage.selectInterval("Current FQ");
    qsPage.selectInclude("All Opportunities");
    qsPage.runReport();
    //Verify report is displayed
	Assert.assertTrue(qsPage.isReportDisplayed());
}
}