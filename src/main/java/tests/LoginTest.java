package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.UserMenu;
import utils.FileUtils;
//import utils.GmailUtil;
import utils.GmailUtilForgotPasswrd;

/**
 * All the login module test cases can be found here
 * Add the new
 * 
 */
public class LoginTest extends BaseTest{

/*@BeforeMethod
public void init() {
	BaseTest.setDriver("chrome",false);
}
@AfterMethod
public void quitBrowserInstance() {
	BaseTest.getBrowser().quit();
}*/
//@Test
public void LoginErrorMessage_TC01(){
	//WebDriver driver =BaseTest.getBrowser();
	WebDriver driver = BaseTest.getDriver("chrome",false);
	driver.get("https://login.salesforce.com");
    LoginPage login = new LoginPage(driver);
	login.enterUsername("larasiva24435@agentforce.com");
	login.clearPassword();
	login.clickLogin();
	String actualErrorText = login.getErrorMessage(driver);
	String expectedErrorText = "Error: Please enter your password.";
	Assert.assertEquals(actualErrorText, expectedErrorText);
	driver.close();
}
//@Test
public void loginWithRememberMe_TC03() throws InterruptedException {
    WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	//Logout
	UserMenu userMenu = new UserMenu(driver);
	userMenu.logout();
	//Recreate LoginPage object after logout
	lp = new LoginPage(driver);
	//Validate Remember Me behavior
	Assert.assertTrue(lp.isRememberedUsernameDisplayed(), "Remembered username label should be visible");
	String actualRememberedUsername = lp.getRememberedUsername();
	Assert.assertEquals(actualRememberedUsername, "larasiva24435@agentforce.com", "Username should be remembered correctly");
	driver.close();
}
//@Test
public void loginToSalesForce_TC02() throws InterruptedException{
	//WebDriver driver = BaseTest.getBrowser();
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(driver);
	HomePage homePage = new HomePage(driver);
	Assert.assertTrue(homePage.isHomePageDisplayed(driver), "Home page should be displayed after login");
	driver.close();
}

		
//@Test
public void loginAndRememberMeTest_TC03() throws InterruptedException {
	//	String loginUrl = BaseTest.getSfdcDirectLoginUrl();
	//	driver.get(loginUrl);
	//	String otp=GmailUtil.getOTP();
	//	login.enterVerificationCode(otp);
	//	login.clickOnVerifyButton(driver);
}
	
//@Test
public void forgotPassword_4A() {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage login = new LoginPage(driver);
	Assert.assertTrue(login.isLoginPageVisible());
	//Click Forgot Password
	login.ClickForgotPassword();
	ForgotPasswordPage forgotPage = new ForgotPasswordPage( driver);
	forgotPage.resetPassword("larasiva24435@agentforce.com");
	Assert.assertTrue(forgotPage.isConfirmationDisplayed());
	//Fetch reset link from Gmail
	String emailBody=GmailUtilForgotPasswrd.getResetEmailBody();
	System.out.println("Email body :\n" +emailBody);
	String actualMessage = forgotPage.getEmailSentMessage(driver);
	String expectedMessage="We’ve sent you an email with a link to finish resetting your password.";
	Assert.assertEquals(actualMessage, expectedMessage, "Email sent message validation");
}
@Test
public void forgotPassword_4B()  {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"), false);
	driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	LoginPage login = new LoginPage(driver);
	login.enterUsername("123");
	Assert.assertEquals("123","123","Username is entered");
	login.enterPassword("22131");
	Assert.assertEquals("22131","22131","Password is entered");
	login.clickLogin();
	String actualError=login.getErrorMessage(driver);
	String expectedError="Error: Please check your username and password. "
				+ "If you still can't log in, contact your Salesforce administrator.";
	Assert.assertEquals(actualError,expectedError,"Error message validation");
	driver.quit();
}
}
