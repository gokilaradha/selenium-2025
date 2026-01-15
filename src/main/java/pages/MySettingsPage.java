package pages;
import java.util.logging.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import freemarker.log.Logger;
import utils.FileUtils;

public class MySettingsPage extends BasePage{
     
	public MySettingsPage(WebDriver driver) {
    	super(driver);
    }
    
    @FindBy(xpath = "//a[@id='PersonalSetup_font']")
    WebElement mySettingsHeader;

    // Login History
    @FindBy(id = "PersonalInfo_font")  
    WebElement personalLink;
    
    @FindBy(id = "LoginHistory_font")
    WebElement loginHistoryLink;
    
    @FindBy(xpath = "//a[contains(text(),'Download login history')]")
    WebElement downloadLoginHistory;

    // Customize Tabs
    @FindBy(id = "DisplayAndLayout_font")    
    WebElement displayAndLayoutLink;
    
    @FindBy(xpath="//a[@id='CustomizeTabs_font']")
    WebElement customizeTabsLink;
    
    @FindBy(xpath="//h1[text()='Customize My Tabs']")
    WebElement customizeMyTabsHeader;

    @FindBy(id = "p4")
    WebElement customAppDropdown;
        
    @FindBy(xpath = "//img[@title='Add']")
    WebElement addButton;
    
    @FindBy(xpath = "//select[@id='duel_select_0']/option[text()='Reports']")
    private WebElement availablereportsTab;

    @FindBy(xpath = "//select[@id='duel_select_1']/option[text()='Reports']")
    private WebElement selectedReportsTab;

    // Email Settings
    @FindBy(id = "EmailSetup_font")
    WebElement emailLink;
    
    @FindBy(id = "EmailSettings_font")
    WebElement myEmailSettingsLink;

    @FindBy(id = "sender_name")
    WebElement emailNameField;

    @FindBy(id = "sender_email")
    WebElement emailAddressField;

    @FindBy(id = "auto_bcc1")
    WebElement autoBccRadio;
    
    @FindBy(xpath = "//input[@class='btn primary']")
    WebElement saveEmailSettings;
    
    @FindBy(xpath="//div[text()='Your settings have been successfully saved.']")
    WebElement savedSettingsMessage;

    // Calendar Reminder
    By calendarLink = By.id("CalendarAndReminders_font");
    
    @FindBy(id = "Reminders_font")
    WebElement activityReminderLink;
    
    @FindBy(id = "testbtn")
    WebElement testReminderButton;
    
    @FindBy(id = "summary1")
    WebElement testReminderPopupContainer;
    
    public boolean isSettingsPageLoaded() {
    	waitForVisibility(mySettingsHeader);
        return mySettingsHeader.isDisplayed();
	}
	
	public void openLoginHistory() {
		click(personalLink);
	    click(loginHistoryLink);
	    click(downloadLoginHistory);
	 }
	
	 public boolean isLoginHistoryPageLoaded() {
		    waitForVisibility(loginHistoryLink);
		    return loginHistoryLink.isDisplayed();
	 }
		
	  public boolean isDownloadLinkVisible() {
		    waitForVisibility(downloadLoginHistory);
		    return downloadLoginHistory.isDisplayed();
	  }

	  public boolean waitForLoginHistoryDownload()  {
		    for (int i = 0; i < 10; i++) {
		        if (FileUtils.isLoginHistoryFileDownloaded()) {
		            return true;
		        }
		        		    }
		    return false;
		}

	   public boolean addReportsTab() {
			click(displayAndLayoutLink);
		    click(customizeTabsLink);
		    waitForVisibility(customizeMyTabsHeader);
		    selectDropdownByVisibleText(customAppDropdown, "Salesforce Chatter");
		    click(availablereportsTab);
		    click(addButton);
		    waitForVisibility(selectedReportsTab);
		    return selectedReportsTab.isDisplayed();
        }

        public void setEmailPreferences(String name, String email) {
    	    wait.until(ExpectedConditions.elementToBeClickable(emailLink)).click();
            wait.until(ExpectedConditions.elementToBeClickable(myEmailSettingsLink)).click();
            wait.until(ExpectedConditions.visibilityOf(emailNameField)).clear();
            emailNameField.sendKeys(name);
            wait.until(ExpectedConditions.visibilityOf(emailAddressField)).clear();
            emailAddressField.sendKeys(email);
            wait.until(ExpectedConditions.elementToBeClickable(autoBccRadio)).click();
            wait.until(ExpectedConditions.elementToBeClickable(saveEmailSettings)).click();
         }

        public void testCalendarReminder() {
      	    waitForVisibility(calendarLink);
            click(calendarLink);
            waitForVisibility(activityReminderLink);
            click(activityReminderLink);
            waitForVisibility(testReminderButton);
            click(testReminderButton);
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            switchToNewWindow();
            System.out.println("Window title: " + driver.getTitle());
            System.out.println("summary1 count = " + driver.findElements(By.id("summary1")).size());
         }

       
         public boolean verifyReminderPopupVisible() {
    	    // Look for a window with title containing "Reminders"
            for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains("Reminders")) {
                return true; // popup is displayed
            }
          }
        return false; // popup not found
    }
}

   