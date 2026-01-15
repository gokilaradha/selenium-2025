package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ContactsPage extends BasePage{
	public ContactsPage(WebDriver driver) {
	       super(driver);
 } 
	//Create new contact
	
	@FindBy(name = "new")
    WebElement newButton;

	@FindBy(xpath = "//h1[contains(text(),'Contacts')]")
    WebElement contactsHeader;
	
	@FindBy(name = "fcf")
	WebElement viewDropdown;
			
	//Create New View in the Contact Page
	
	@FindBy(linkText="Create New View")
	WebElement createNewViewLink;
	
	@FindBy(id = "hotlist_mode")
    WebElement recentContactsDropdown;

    @FindBy(xpath = "//h3[text()='Recent Contacts']")
    WebElement recentContactsHeader;
    
    //My Contacts View in the contact page
    @FindBy(id = "fcf")
    WebElement contactsViewDropdown;
        
    @FindBy(xpath = "//h1[contains(text(),'Contacts')]")
    WebElement contactsHomeHeader;
    
    //View a contact in the Contact Page
    @FindBy(xpath = "//h3[text()='Recent Contacts']/following::table[1]")
    WebElement recentContactsTable;

    @FindBy(xpath = "//h3[text()='Recent Contacts']/following::table[1]//tr[2]//th/a")
    WebElement firstRecentContactLink;
    
   public boolean isContactsHomePageDisplayed() {
        wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOf(contactsHeader)
        ));
        return contactsHeader.isDisplayed();
    }

    public NewContactPage clickNew() {
    	waitForVisibility(newButton).click();
        return new NewContactPage(driver);
    }

    public CreateNewViewPage clickCreateNewView() {
        waitForVisibility(createNewViewLink).click();;
        return new CreateNewViewPage(driver);
    }

    public String getSelectedViewName() {
        return new Select(viewDropdown).getFirstSelectedOption().getText().trim();
    }
    public void selectRecentlyCreated() {
        selectDropdownByVisibleText(recentContactsDropdown, "Recently Created");
    }

    public boolean isRecentContactsDisplayed() {
        waitForVisibility(recentContactsHeader);
        return recentContactsHeader.isDisplayed();
    }
    public void selectView(String viewName) {
        selectByVisibleText(viewDropdown, viewName);
    }
    public String getSelectedView() {
        return new Select(viewDropdown).getFirstSelectedOption().getText().trim();
    }
    //View a contact in the contact Page
    public ContactDetailsPage clickFirstRecentContact() {
        click(firstRecentContactLink);
        return new ContactDetailsPage(driver);
    }
    //Verify Cancel button
    public boolean isViewPresent(String viewName) {
        waitForVisibility(viewDropdown);
        Select select = new Select(viewDropdown);
        return select.getOptions()
                     .stream()
                     .anyMatch(option -> option.getText().equals(viewName));
    }


}