package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.AccountsHomePage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;

import utils.FileUtils;

public class CreateAccountPageTest extends BaseTest{
	
@Test
public void createNewAccounts_TC10() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
		driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
		LoginPage lp = new LoginPage(driver);
		HomePage homePage =lp.loginToApp(driver);
		Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
		homePage.clickAccounts();
		AccountsHomePage accountsHome = new AccountsHomePage(driver);
		Assert.assertTrue(accountsHome.verifyAccountPage("Radha Abcd"),"Accounts page not loaded or username incorrect");
		accountsHome.clickNewButton();
		CreateAccountPage accountPage = new CreateAccountPage(driver);
		String accountName="Account Automation";
		accountPage.createAccount(accountName);
		//Verify account created
		Assert.assertTrue(accountPage.verifyAccountCreated(accountName),
				"Account creation verification failed");
		}

//@Test
public void createNewView_TC11() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
		driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
		LoginPage lp = new LoginPage(driver);
		HomePage homePage =lp.loginToApp(driver);
		Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
		homePage.clickAccounts();
		AccountsHomePage accountsHome = new AccountsHomePage(driver);
		Assert.assertTrue(accountsHome.verifyAccountPage("Radha Abcd"),"Accounts page not loaded or username incorrect");
		accountsHome.clickCreateNewView();
		CreateAccountPage accountPage = new CreateAccountPage(driver);
		String viewName="SkyBlue";
		String viewUniqueName="SkyBlue1";
		accountPage.createNewView(viewName,viewUniqueName);
		//Verify Newely added view created
		Assert.assertTrue(accountPage.verifyNewlyAddedViewIsVisible(viewName),
						"Newely added view is not displayed in the account view list");
		
}
//@Test
	public void editView_TC12() throws InterruptedException {
		WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
		driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
		LoginPage lp = new LoginPage(driver);
		HomePage homePage =lp.loginToApp(driver);
		Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
		homePage.clickAccounts();
		AccountsHomePage accountsHome = new AccountsHomePage(driver);
		Assert.assertTrue(accountsHome.verifyAccountPage("Radha Abcd"),
						"Accounts page not loaded or username incorrect");
		CreateAccountPage accountPage = new CreateAccountPage(driver);
		// Step 2: Select view and click Edit
		accountPage.selectViewSafe("SkyBlue", "Test Automation");
	    accountPage.clickEditView();
	    Assert.assertTrue(accountPage.isEditFormDisplayed());
	    accountPage.editView("Test Automation");
	    accountPage.setFilter("Account Name", "contains", "a");
	    accountPage.addFieldToDisplay("Last Activity");
	    accountPage.saveView();
	    accountPage.waitForAccountsTableToLoad();
	    // Step 4: Verify new view name is selected
	    String selectedView = accountPage.getSelectedViewName();
	    Assert.assertTrue(
	            selectedView.equals("SkyZone") || selectedView.equals("Test Automation"),
	            "View name is not selected correctly. Found: " + selectedView
	    );

	    //Step 5:Verify Last Activity column is present
	    Assert.assertTrue(accountPage.isLastActivityColumnPresent(),"Last Activity column is not displayed");
	    //Step 6:Verify all account names contain 'a'
	    Assert.assertTrue(accountPage.allAccountNamesContain("a"),"Some account names do not contain the letter 'a'");
	}
	
	

}
