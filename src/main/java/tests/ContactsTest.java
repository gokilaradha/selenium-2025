package tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import constants.FileConstants;
import pages.ContactDetailsPage;
import pages.ContactsPage;
import pages.CreateNewViewPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NewContactPage;
import utils.FileUtils;

public class ContactsTest {

//@Test
public void createNewContact_TC25() throws Exception {
  	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    // Login
    LoginPage lp = new LoginPage(driver);
    HomePage home = lp.loginToApp(driver);
    Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
    // Contacts Tab
    ContactsPage contactsPage = home.clickContactsTab();
    Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
    // New Contact
    NewContactPage newContact = contactsPage.clickNew();
    Assert.assertTrue(newContact.isNewContactPageDisplayed());
    // Enter details
    newContact.enterLastName("Rajmani");
    newContact.enterAccountName("Cloud");
    // Save
    ContactDetailsPage details = newContact.clickSave();
    // Verify
    Assert.assertTrue(details.isContactDetailsPageDisplayed());
    Assert.assertTrue(details.getContactName().contains("Raj"));
}
//@Test
public void verifyCreateNewView_TC26() throws Exception {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    //Login
    LoginPage lp = new LoginPage(driver);
    HomePage home = lp.loginToApp(driver);
    Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
    //Click Contacts Tab
    ContactsPage contactsPage = home.clickContactsTab();
    Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
    //Click Create New View
    CreateNewViewPage newView = contactsPage.clickCreateNewView();
    Assert.assertTrue(newView.isCreateNewViewPageDisplayed());
    //Enter View Name
    newView.enterViewName("AutomationView");
    //Enter View Unique Name
    String AutomationView = "AutomationView_" + String.valueOf(System.currentTimeMillis());
    newView.enterViewUniqueName(AutomationView);
    //Save
    ContactsPage updatedContacts = newView.clickSave();
    //Verify View Name is selected in dropdown
    String selectedView = updatedContacts.getSelectedViewName();
    Assert.assertEquals(selectedView, "AutomationView");
}
//@Test
public void verifyRecentlyCreatedContacts_TC27() throws InterruptedException {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    //Login
    LoginPage lp = new LoginPage(driver);
    HomePage home = lp.loginToApp(driver);
    Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
    //Click Contacts Tab
    ContactsPage contactsPage = home.clickContactsTab();
    Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
    ContactsPage contacts = new ContactsPage(driver);
    System.out.println("Selecting 'Recently Created' from dropdown");
    contacts.selectRecentlyCreated();
    System.out.println("Step 3: Verifying Recent Contacts section is displayed");
    Assert.assertTrue(contacts.isRecentContactsDisplayed(),
            "Recent Contacts section is not displayed after selecting Recently Created");
}
//@Test
public void verifyMyContactsView_TC28() throws Exception {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    //Login
    LoginPage lp = new LoginPage(driver);
    HomePage home = lp.loginToApp(driver);
    Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
    //Navigate to Contacts
    ContactsPage contactsPage = home.clickContactsTab();
    Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
    // Select "My Contacts"
    contactsPage.selectView("My Contacts");
    // Verify
    Assert.assertEquals(contactsPage.getSelectedView(), "My Contacts");
}
//@Test
public void viewRecentContact_TC29() throws Exception {
	WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
    driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
    //Login
    LoginPage lp = new LoginPage(driver);
    HomePage home = lp.loginToApp(driver);
    Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
    //Navigate to Contacts
    ContactsPage contactsPage = home.clickContactsTab();
    Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
    // Click first recent contact
    ContactDetailsPage details = contactsPage.clickFirstRecentContact();
    // Verify contact details page
    Assert.assertTrue(details.isContactDetailsPageDisplayed(),
            "Contact details page is not displayed");
}
//@Test
 public void verifyErrorWhenViewNameBlank_TC30() throws Exception {
	 WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	 driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	 //Login
	 LoginPage lp = new LoginPage(driver);
	 HomePage home = lp.loginToApp(driver);
	 Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
	 //Navigate to Contacts
	 ContactsPage contactsPage = home.clickContactsTab();
	 Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
     // Open Create New View
     CreateNewViewPage newView = contactsPage.clickCreateNewView();
     // Enter only Unique Name
     newView.enterViewUniqueName("EFGH");
     // Click Save
     newView.clickSave();
     // Verify error message
     Assert.assertEquals(newView.getErrorMessage(), "Error: You must enter a value");
 }
 
//@Test
public void verifyCancelDoesNotCreateView_TC31() throws Exception {
	 WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	 driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	 //Login
	 LoginPage lp = new LoginPage(driver);
	 HomePage home = lp.loginToApp(driver);
	 Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
	 //Navigate to Contacts
	 ContactsPage contactsPage = home.clickContactsTab();
	 Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
     // Open Create New View
     CreateNewViewPage newView = contactsPage.clickCreateNewView();
     // Enter View Name and Unique Name
     newView.enterViewName("ABCD");
     newView.enterViewUniqueName("EFGH");
     // Click Cancel
     ContactsPage contactsAfterCancel = newView.clickCancel();
     // Verify Contacts Home Page
     Assert.assertTrue(contactsAfterCancel.isContactsHomePageDisplayed());
     // Verify view ABCD is NOT created
     Assert.assertFalse(contactsAfterCancel.isViewPresent("ABCD"),
             "View ABCD should NOT be created after clicking Cancel");
 }

//@Test
public void verifySaveAndNewCreatesContact_TC32() throws Exception {
	 WebDriver driver = BaseTest.getDriver(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "browser.name"),false);
	 driver.get(FileUtils.readPropertiesFile(FileConstants.TEST_DATA_FILE_PATH, "login.url"));
	 //Login
	 LoginPage lp = new LoginPage(driver);
	 HomePage home = lp.loginToApp(driver);
	 Assert.assertEquals(home.getLoggedInUserName(), "Radha Abcd");
	 //Navigate to Contacts
	 ContactsPage contactsPage = home.clickContactsTab();
	 Assert.assertTrue(contactsPage.isContactsHomePageDisplayed());
     // Click New
     NewContactPage newContact = contactsPage.clickNew();
     Assert.assertTrue(newContact.isNewContactPageDisplayed());
     // Enter Last Name and Account Name
     newContact.enterLastName("Indian");
     newContact.enterAccountName("Global Media");
     // Click Save & New
     NewContactPage newContactAgain = newContact.clickSaveAndNew();
     // Verify New Contact page is displayed again
     Assert.assertTrue(newContactAgain.isNewContactPageDisplayed(),
             "Save & New did not return to New Contact page");
 }
}