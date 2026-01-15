package tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.AccountsHomePage;
import pages.HomePage;
import pages.LoginPage;
import pages.ReportPage;
import utils.FileUtils;

public class CreateAccountReportTest extends BaseTest{
	
@Test	
public void createAccountReport_TC14() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	HomePage homePage =lp.loginToApp(driver);
	//HomePage homePage = CommonUtils.OAuthHomePage(driver);
	Assert.assertEquals(homePage.getLoggedInUserName() , "Radha Abcd");
	homePage.clickAccounts();
	AccountsHomePage accountsHome = new AccountsHomePage(driver);
	//Verify Account Home page loaded
	Assert.assertTrue(accountsHome.verifyAccountPage("Radha Abcd"),
			"Accounts page not loaded or username incorrect");
	accountsHome.openLastActivityReport();
	// Step 4: Verify Unsaved Report Page
	ReportPage reportPage =new ReportPage(driver);
	Assert.assertTrue(reportPage.isUnsavedReportPageDisplayed(), "Unsaved Report page not displayed");
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    // Step 5: Select Created Date and today's date
    reportPage.selectDateField("Created Date", today);
    // Step 6: Verify tabular preview
    Assert.assertTrue(reportPage.isPreviewTableDisplayed(), "Preview table not displayed");
    // Step 7: Click Save
    reportPage.clickSave();
    // Step 8: Fill Save Report popup
    String reportname = "Automation Report_" + String.valueOf(System.currentTimeMillis());
    reportPage.enterAndSaveReport(reportname);
    Assert.assertTrue(reportPage.verifyReportHeader(reportname),
        "Saved report page did not display the correct report name");
}
}
