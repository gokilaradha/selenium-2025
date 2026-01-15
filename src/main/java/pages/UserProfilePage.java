package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePage extends BasePage{
	public UserProfilePage(WebDriver driver) {
        super(driver);
    }

	//Profile Page Header
	@FindBy(id= "tailBreadcrumbNode")
	WebElement profileHeader;
	
	@FindBy(xpath = "//a[@class='contactInfoLaunch editLink']")
    WebElement editProfileIcon;
	private By profileHeaderUpdate = By.xpath("//h2[contains(@class,'topName')]");

	public boolean isProfilePageDisplayed() {
        waitForVisibility(profileHeader);
        return profileHeader.isDisplayed();
    }

    public String getProfileHeaderText() {
        return getText(profileHeader).trim();
    }
    
    public boolean isProfileHeaderDisplayed() {
        return profileHeader.isDisplayed();
    }

    public EditProfilePopup clickEditProfileIcon() {
        click(editProfileIcon);
        return new EditProfilePopup(driver);
    }
    public String getUpdatedProfileHeader() {
        return driver.findElement(profileHeaderUpdate).getText().trim();
    }

}
