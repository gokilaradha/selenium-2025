package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewViewPage extends BasePage{
	
	public CreateNewViewPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//h2[contains(text(),'Create New View')]")
    WebElement createNewViewHeader;

    @FindBy(id = "fname")
    WebElement viewNameField;

    @FindBy(id = "devname")
    WebElement viewUniqueNameField;

    @FindBy(name = "save")
    WebElement saveButton;
    
    //Create New View Page Error
    @FindBy(xpath = "//div[@class='errorMsg']")
    WebElement viewNameError;

    //Cancel button
    @FindBy(name = "cancel")
    WebElement cancelButton;

    public boolean isCreateNewViewPageDisplayed() {
        return waitForVisibility(createNewViewHeader).isDisplayed();
    }

    public void enterViewName(String viewName) {
        waitForVisibility(viewNameField).clear();
        viewNameField.sendKeys(viewName);
    }

    public void enterViewUniqueName(String uniqueName) {
    	waitForVisibility(viewUniqueNameField);
        
        //Click to trigger Salesforce auto-fill
        viewUniqueNameField.click();
        
        //Clear once (Salesforce will re-populate)
        viewUniqueNameField.clear();
        
        //Wait for Salesforce to finish auto-filling again
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        
        //Clear again (this time it stays empty)
        viewUniqueNameField.clear();
        
        //Now type your value
        viewUniqueNameField.sendKeys(uniqueName);

    }

    public ContactsPage clickSave() {
    	waitForVisibility(saveButton).click();
        return new ContactsPage(driver);
    }
    public ContactsPage clickCancel() {
    	waitForVisibility(cancelButton).click();;
        return new ContactsPage(driver);
    }

    public void clickSaveInNewView() {
    	waitForVisibility(saveButton).click();
    }
    public String getErrorMessage() {
        waitForVisibility(viewNameError);
        return viewNameError.getText().trim();
    }

}
