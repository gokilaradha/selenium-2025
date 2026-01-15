package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonUtils;
import utils.WaitUtils;

public class MyProfilePage extends BasePage {
	
	public MyProfilePage(WebDriver driver) {
		super(driver);
	}
	//Page objects
	@FindBy(xpath= "//a[@class='contactInfoLaunch editLink']")
	WebElement editContactButton;
	
	@FindBy(xpath = "//div/h2[@id='contactInfoTitle']")
	WebElement editProfilePopupWindow;
	
	@FindBy(id = "aboutTab")
	WebElement aboutTab;
	
	@FindBy(xpath = "//input[@id='lastName']")
	WebElement aboutTabLastName;
	
	@FindBy(xpath="//*[@value='Save All']")
	WebElement saveAllButton;
	
	@FindBy(xpath = "//*[@id='tailBreadcrumbNode']")
	WebElement userProfilePageNameDisplay;
	
	//Postlink
	@FindBy(css = "#publishereditablearea")
	WebElement postLink;
	
	@FindBy(xpath="/html/body")
	WebElement postTextArea;
	
	@FindBy(xpath="//input[@id='publishersharebutton']")
	WebElement shareButton;
	
	@FindBy(css="[class=\"view highlight\"]")
	WebElement newPostHighlight;
	
	//fileLink
	@FindBy(xpath="//*[@id='publisherAttachContentPost']")
	WebElement fileLink;
	
	@FindBy(xpath="//[@id='publisherAttachContentPost']/span[1]")
	WebElement contentPost;
	
	@FindBy(css= "#chatterUploadFileAction")
	WebElement uploadFile;
	
	@FindBy(css="#chatterFile")
	WebElement fileOpen;
	
	@FindBy(css= "#publishersharebutton")
	WebElement publishShareButton;
	
	@FindBy(xpath = "//input[@value='Cancel Upload']")
	WebElement cancelUpload;
	
	@FindBy(id="uploadLink")
	WebElement updateButton;
	
	// My Settings
	@FindBy(xpath = "//*[@id=\'PersonalInfo_font\']")
	WebElement personalLink;
	
	@FindBy(xpath = "//*[@id=\"LoginHistory_font\"]")
	WebElement loginHistoryLink;
	
	@FindBy(xpath = "//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a")
	WebElement loginDisplay;
	
	@FindBy(id = "contactInfoContentId")
    WebElement iframeAboutTab;
	
	//Display&layoutlink
	@FindBy(xpath= "//*[@id=\"DisplayAndLayout_font\"]")
	WebElement DisplayLayoutLink;
	
	@FindBy(id = "CustomizeTabs_font")
	WebElement customizedTab;
	
	@FindBy(xpath="//*[@id=\"p4\"]/option[9]")
	WebElement customApp;
	
	@FindBy(xpath = "//*[@id=\"duel_select_0\"]/option[73]")
	WebElement availableTab;
	
	@FindBy(xpath = "//*[@id=\"bottomButtonRow\"]/input[1]")
	WebElement save;
	
	@FindBy(id="tabBar")
	WebElement tabList;
	
	//Emaillink
	@FindBy(xpath = "//*[@id=\"EmailSetup_font\"]")
	WebElement emailLink;
	
	@FindBy(id = "EmailSettings_font")
	WebElement myEmailSettings;
	
	@FindBy(id = "sender_name")
	WebElement emailName;
	
	@FindBy(xpath = "//*[@id=\"sender_email\"]")
	WebElement emailAddress;
	
	@FindBy(xpath = "//*[@id=\"useremailSection\"]/table/tbody/tr[7]/td[2]/div")
	WebElement bccRadioButton;
	
	@FindBy(xpath = "//*[@id=\"bottomButtonRow\"]/input[1]")
	WebElement saveOnEmail;
	
	// Calendar and Remainders
	@FindBy(id = "CalendarAndReminders_font")
	WebElement calendarAndReminders;
	
	@FindBy(xpath = "//*[@id=\"Reminders_font\"]")
	WebElement activityRemainder;
	
	@FindBy(id="testbtn")
	WebElement openaTestRemainder;
	
	@FindBy(xpath = "//*[@id=\"summary\"]")
	WebElement sampleEventPopup;
	
	@FindBy(css = "#displayBadge")
	WebElement moderatorButton;
	
	@FindBy(id= "profileTab_sfdc.ProfilePlatformFeed")
	WebElement profilePage;
	
	@FindBy(id = "contactTab")
	WebElement contactTab;
	
	@FindBy(xpath = "//div[@class='cxfeeditem feeditem'] [1]//p")
	WebElement firstPostText;
	
	@FindBy(xpath = "(//*[@class='contentFileTitle'])[1]")
	WebElement verifyFilePostElement;
	
	@FindBy(xpath = "//form[@name=\"j_id0:waitingForm\"]")
	WebElement spinnerIcon;
	
	@FindBy(id = "cropWaitingPage:croppingForm")
	WebElement spinnerWhileCropping;
	
	@FindBy(id = "progressIcon")
	WebElement fileUploadSpinner;
	
	//Photo link
	
	@FindBy(xpath = "//*[@id=\"publisherAttachLinkPost\"]/span[1]")
	WebElement photoLink;
	
	@FindBy(id = "j_id0:uploadFileForm:uploadInputFile")
	WebElement uploadPhoto;
	
	@FindBy(name = "j_id0:uploadFileForm:uploadBtn")
	WebElement uploadButton;
	
	@FindBy(id = "publishersharebutton")
	WebElement photoShareButton;
	
	@FindBy(id = "uploadPhotoContentId")
	WebElement photoUploadIframe;
	
	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:uploadBtn']")
	WebElement photoSaveButton;
	
	@FindBy(xpath = "//input[@id='j_id0:j_id7:save']")
	WebElement photoSaveButton2;
		
	public boolean isProfilePageLoaded(WebDriver driver) {
		boolean isProfilePage=false;
		if(postTextArea.isDisplayed()) {
			isProfilePage=true;
		}
		return isProfilePage;
	}
	
	public void waitAndClickOnEditIcon(WebDriver driver) {
		if(WaitUtils.waitForElementVisibility(editContactButton, driver)) {
			editContactButton.click();
		}
	}
	
	public boolean verifyEditProfilePopUpIsVisible(WebDriver driver) {
		boolean verifyEditProfilePopUp = false;
		if(editProfilePopupWindow.isDisplayed() ) {
			driver.switchTo().frame(iframeAboutTab);
			System.out.println("verifyEditProfilePopUpIsVisible : Pop up window is displayed");
			if( WaitUtils.waitForElementVisibility(aboutTab, driver) && contactTab.isDisplayed()) {
				verifyEditProfilePopUp = true;
				System.out.println("verifyEditProfilePopUpIsVisible : About and Contact tab  loaded");
			}
			driver.switchTo().parentFrame();
		}
		return verifyEditProfilePopUp;
	}
	
	public boolean verifyChangeLastNameInAboutTab(WebDriver driver, String lastNameToChange) {
		driver.switchTo().frame(iframeAboutTab);
		if(aboutTab.isDisplayed()) {
			aboutTab.click();
			aboutTabLastName.clear();
			aboutTabLastName.sendKeys(lastNameToChange);
			saveAllButton.click();
		}
		driver.switchTo().parentFrame();
		String[] actualUsername = userProfilePageNameDisplay.getText().split(" ");
		System.out.println("actual username: "+actualUsername[1] +" expected username: "+lastNameToChange);
		return actualUsername[1].equals(lastNameToChange);
	}
	
	public boolean verifyCreatePost(WebDriver driver ,String message) {
		if(WaitUtils.waitForElementVisibility(postLink, driver)) {
			postLink.click();
			//iframe index to be updated to 1 if below doesn't work
			driver.switchTo().frame(0);
			WaitUtils.waitForElementVisibility(postTextArea, driver);
			if(postTextArea.isDisplayed()) {
				postTextArea.sendKeys(message);
			}
			driver.switchTo().defaultContent();
			shareButton.click();
		}
		String xpath = "(//div[@class='cxfeeditemtextwrapper']//p[text()='" + message + "'])[1]";
		if(WaitUtils.waitForElementVisibility(driver.findElement(By.xpath(xpath)), driver)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyFileUpload(WebDriver driver,String filepath) {
		boolean isFileUploaded=false;
		if(fileLink.isDisplayed()) {
			fileLink.click();
			uploadFile.click();
			fileOpen.sendKeys(filepath);
			publishShareButton.click();
		}
		if(WaitUtils.waitForElementInvisibility(cancelUpload, driver)) {
			if(newPostHighlight.isDisplayed()) {
				isFileUploaded=true;
			}
		}
		return isFileUploaded;
	}
    
	public void clickOnUpdatePhotoButton(WebDriver driver )   {
	        CommonUtils.mouseHover(driver, moderatorButton);
	        if(updateButton.isDisplayed()) {
	        	updateButton.click();
	        }
	 }
	public boolean verifyPhotoUpload(WebDriver driver,String imageFilepath) {
		boolean isPhotoUploadSuccess = false;
		this.clickOnUpdatePhotoButton(driver);
		driver.switchTo().frame(photoUploadIframe);
		if(WaitUtils.waitForElement(driver, uploadPhoto)) {
			uploadPhoto.sendKeys(imageFilepath);
			photoSaveButton.click();
		}
		if(WaitUtils.waitForElementToDisappear(driver, spinnerIcon) 
				&& WaitUtils.waitForElement(driver, photoSaveButton2)) {
			photoSaveButton2.click();
			if(WaitUtils.waitForElementToDisappear(driver, spinnerWhileCropping)) {
				isPhotoUploadSuccess = true;
			}
		}
		driver.switchTo().parentFrame();
		return isPhotoUploadSuccess;
	}
}



