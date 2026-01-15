package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewContactPage extends BasePage{
	public NewContactPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[contains(text(),'New Contact')]")
    WebElement newContactHeader;
	
	@FindBy(id = "name_lastcon2")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@id='con4']")
    WebElement accountNameField;
    
    @FindBy(id = "con4")
    WebElement accountName;

    @FindBy(name = "save_new")
    WebElement saveAndNewButton;

    @FindBy(xpath = "//td[@id='topButtonRow']//input[@title='Save']")
    WebElement saveButton;

    public boolean isNewContactPageDisplayed() {
        return waitForVisibility(newContactHeader).isDisplayed();
    }

    public void enterLastName(String ln) {
        waitForVisibility(lastNameField).sendKeys(ln);
    }

    public void enterAccountName(String acc) {
        waitForVisibility(accountNameField).sendKeys(acc);
    }

    public ContactDetailsPage clickSave() {
    	waitForVisibility(saveButton).click();
        return new ContactDetailsPage(driver);
    }

    public NewContactPage clickSaveAndNew() {
        click(saveAndNewButton);
        return new NewContactPage(driver);
    }

}
