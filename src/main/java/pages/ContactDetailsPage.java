package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactDetailsPage extends BasePage {
	
	public ContactDetailsPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath = "//h2[@class='pageDescription']")
    WebElement contactDetailHeader;
    
	public boolean isContactDetailsPageDisplayed() {
        return waitForVisibility(contactDetailHeader).isDisplayed();
    }

    public String getContactName() {
        return waitForVisibility(contactDetailHeader).getText().trim();
    }

}
