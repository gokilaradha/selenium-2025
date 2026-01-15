package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.AccountsHomePage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MergeAccountsStep1Page;
import pages.MergeAccountsStep2Page;
import utils.FileUtils;
import utils.TestDataHelper;

public class MergeAccountsTest extends BaseTest {
 
@Test
public void verifyAccountMerge_TC13()throws InterruptedException {
    WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	HomePage homePage =lp.loginToApp(driver);
	Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
	homePage.clickAccounts();
	AccountsHomePage accountsHome = new AccountsHomePage(driver);
	Assert.assertTrue(accountsHome.verifyAccountPage("Radha Abcd"),"Accounts page not loaded or username incorrect");
	accountsHome.clickMergeAccounts();  
    MergeAccountsStep1Page step1 = new MergeAccountsStep1Page(driver);
    MergeAccountsStep2Page step2 = new MergeAccountsStep2Page(driver);
    Assert.assertTrue(step1.isMergePageDisplayed());
    // Use Same base name for creation and searching
    String baseName = "Test Account";
    TestDataHelper dataHelper = new TestDataHelper(driver);
    dataHelper.ensureThreeAccountsExist(baseName);
    step1.searchAccount(baseName);
    step1.proceedToStep2(baseName);
    Assert.assertTrue(step2.isStep2FormDisplayed(), "Step 2 form title not displayed");
    Assert.assertTrue(step2.isComparisonTableDisplayed(), "Comparison table not displayed");
    step2.proceedWithMerge();
    Assert.assertTrue(accountsHome.isAccountsHomePageLoaded(), "Accounts Home page did not load");
    String actualTopAccount = accountsHome.getFirstRecentAccountName();
    System.out.println("Actual top recent account: " + actualTopAccount);
    Assert.assertTrue(actualTopAccount.startsWith(baseName),"Merged account does not start with expected base name");
}
}


