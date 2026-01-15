package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.WaitUtils;

public class EditProfilePopup extends BasePage {

    public EditProfilePopup(WebDriver driver) {
        super(driver);
   }
    
    @FindBy(id = "contactInfoContentId")
    WebElement editProfileIframe;
    
    @FindBy(xpath = "//div/h2[@id='contactInfoTitle']")
    WebElement editProfilePopupWindow;

    @FindBy(xpath = "//li[@id='contactTab']")
    WebElement contactTab;

    @FindBy(xpath = "//li[@id='aboutTab']")
    WebElement aboutTab;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@value='Save All']")
    WebElement saveAllButton;

    public void clickAboutTab() {
    	driver.switchTo().frame("contactInfoContentId");
        click1(aboutTab);
    }

    public void editLastName(String newLastName) {
        lastNameField.clear();
        lastNameField.sendKeys(newLastName);
    }

    public void clickSaveAll() {
        click1(saveAllButton);
        driver.switchTo().defaultContent();
    }
    
		
    // Verify popup + Contact tab selected
    public boolean verifyPopupWithContactTab() {
    	try {
        waitForVisibility(editProfilePopupWindow);
        System.out.println("Popup window is displayed");
        //Switch to iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(editProfileIframe));

        //Verify Contact tab is selected
        String cls = contactTab.getAttribute("class");
        boolean isSelected = cls.contains("zen-current");

        System.out.println("Contact tab selected: " + isSelected);
        return isSelected;
    	}catch(Exception e) {
    		return false;
    	}finally {
    		driver.switchTo().defaultContent();
    	}
      }     
    //Click About tab and verify focus in First Name
    public boolean clickAboutAndVerifyFocus() {
    	try {
            // Switch to iframe
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(editProfileIframe));

            // Click About tab
            aboutTab.click();

            // Wait for First Name field to be visible
            wait.until(ExpectedConditions.visibilityOf(firstNameField));

            // Click inside the First Name field to bring focus
            firstNameField.click();

            // Now check focus
            WebElement active = driver.switchTo().activeElement();
            return active.equals(firstNameField);

        } catch (Exception e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    //Edit Last Name and Save
    public void updateLastName(String newLastName) {
        driver.switchTo().frame(editProfileIframe);
        lastNameField.clear();
        lastNameField.sendKeys(newLastName);
        saveAllButton.click();
        driver.switchTo().defaultContent();

    }
 // STEP 4: Wait for popup to close
    public void waitForPopupToClose() {
        WaitUtils.waitForElementInvisibility(editProfilePopupWindow,driver);
    }

}
